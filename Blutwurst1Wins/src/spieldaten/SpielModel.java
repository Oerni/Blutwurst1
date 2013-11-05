package spieldaten;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import logik.Feld;
import parallelisierung.AktualisierenRunnable;
import parallelisierung.SpeichernRunnable;
import parallelisierung.ThreadExecutor;

public class SpielModel {
	/**
	 * SpielModel. Zentraler Ansprechpartner des SpielViewController zur Vereinheitlichung
	 * der Kommunikation. ViewController hat keinen Einblick, was die Quelle der jeweiligen
	 * Daten ist.
	 */
	private Stage stage;
	private Spiel spiel;
	private DateiVerwaltung dateiverwaltung;
	private Feld feld = new Feld();
	private Stack<Spieler> alleSpieler = new Stack<Spieler>();
	
	public SpielModel(Stage stage){
		this.stage = stage;
	}
	
	public Feld getFeld(){
		return feld;
	}
	
	public void feldZuruecksetzen(){
		feld = new Feld();
	}
	
	public Stack<Spieler> getAlleSpieler(){
		if(alleSpieler.isEmpty()){
			ResultSet alleSpielerSQL = HSQLConnection.getInstance().executeQuery(Strings.ALLE_GEGNER);
			try{
				while(alleSpielerSQL.next()){
					try{
						alleSpieler.add(new Spieler(alleSpielerSQL.getString("name")));
					}catch(SQLException ex){
						ex.printStackTrace();
					}
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return alleSpieler;
	}
	
	public ObservableList<Spiel> getOffeneSpiele(Spieler gegner){
		ObservableList<Spiel> offeneSpiele = FXCollections.observableArrayList();
		ResultSet spieleSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.OFFENE_SPIELE_MIT_GEGNER,gegner.getName()));
		try{
			while(spieleSQL.next()){
				try{
					offeneSpiele.add(new Spiel(spieleSQL.getInt("id"),this.getGegner(gegner.getName()),spieleSQL.getInt("punkteheim"),spieleSQL.getInt("punktegegner")));
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return offeneSpiele;
	}
	
	public void setSpiel(Spiel spiel){
		this.spiel = spiel;
	}
	
	public boolean spielerRegistrieren(Spieler spieler){
		for(Spieler vorhanden : alleSpieler){
			if(vorhanden.getName().equals(spieler.getName()))
				return false;
		}
		alleSpieler.add(spieler);
		return true;
	}
	
	public void init(String pfad,Spieler gegner,char eigeneKennzeichnung){
//		System.out.println("Spiel-ID: " + spiel.getID());
		if(spiel == null){
			spiel = new Spiel();
			ThreadExecutor.getInstance().execute(new SpeichernRunnable(spiel));
		}
		spiel.setSelbst(new Spieler(Strings.NAME,eigeneKennzeichnung));

		ThreadExecutor.getInstance().execute(new SpeichernRunnable(spiel.getSelbst()));
		gegner.setKennzeichnung(getGegnerKennzeichnung(eigeneKennzeichnung));
		spiel.setGegner(gegner);
		dateiverwaltung.setPfad(pfad);
		Satz ersterSatz = new Satz(spiel);
		ThreadExecutor.getInstance().execute(new SpeichernRunnable(ersterSatz));
		spiel.satzHinzufuegen(ersterSatz);
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(spiel));
	}
	
	public Spieler getGegner(String name){
		for(Spieler gegner : alleSpieler)
			if(gegner.getName().equals(name))
				return gegner;
		return null;
	}
	
	public void initDateiverwaltung(){
		dateiverwaltung = new DateiVerwaltung(this);
	}
	
	private char getGegnerKennzeichnung(char eigeneKennzeichnung){
		return eigeneKennzeichnung == 'x' ? 'o' : 'x';
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
	
	public Stage getPrimaryStage(){
		return stage;
	}
	
	public Zug zugVonServer(){
		Zug zug = dateiverwaltung.dateiLesen();
		zug.setSpieler(spiel.getGegner());
		return zug;
	}
	public void zugAnServer(Zug zug){
		zug.setSpieler(spiel.getSelbst());
		dateiverwaltung.dateiSchreiben(zug);
	}
	
	public void allesSpeichern(){

	}
	
	public DateiVerwaltung getDateiVerwaltung(){
		return dateiverwaltung;
	}
}