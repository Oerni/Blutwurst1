package spieldaten;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
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
	private Spieler selbst;
	private DateiVerwaltung dateiverwaltung;
	private Stack<Spieler> alleSpieler = new Stack<Spieler>();
	private String satzStatus = "";
	private int runde = 1;
	
	public SpielModel(Stage stage){
		this.stage = stage;
		ResultSet eigenerSpieler = HSQLConnection.getInstance().executeQuery(Strings.EIGENER_SPIELER);
		try{
			eigenerSpieler.next();
			this.selbst = new Spieler(eigenerSpieler.getString("name"),eigenerSpieler.getInt("punktzahl"));
		}catch(SQLException ex){
			this.selbst = new Spieler(Strings.NAME);
			ThreadExecutor.getInstance().execute(new SpeichernRunnable(selbst));
		}
	}
	
	public int getRunde(){
		return runde;
	}
	
	public void naechsteRunde(){
		this.runde++;
	}
	
	public void setSatzStatus(String satzStatus){
		this.satzStatus = satzStatus;
	}
	
	public String getSatzStatus(){
		return satzStatus;
	}
	
	public Spieler getSelbst(){
		return selbst;
	}
	
	public Stack<Spieler> getAlleSpieler(){
		if(alleSpieler.isEmpty()){
			ResultSet alleSpielerSQL = HSQLConnection.getInstance().executeQuery(Strings.ALLE_GEGNER);
			try{
				while(alleSpielerSQL.next()){
					try{
						alleSpieler.add(new Spieler(alleSpielerSQL.getString("name"),alleSpielerSQL.getInt("punktzahl")));
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
			spiel.setSelbst(selbst);
			spiel.setGegner(gegner);
			ThreadExecutor.getInstance().execute(new SpeichernRunnable(spiel));
		}
		selbst.setKennzeichnung(eigeneKennzeichnung);
		gegner.setKennzeichnung(getGegnerKennzeichnung(eigeneKennzeichnung));
		dateiverwaltung.setPfad(pfad);
		Satz ersterSatz = new Satz(spiel);
		System.out.println("Spiel ID: " + spiel.getID());
		System.out.println("Satz speichern. Spiel-ID: " + ersterSatz.getSpiel().getID());
		ThreadExecutor.getInstance().execute(new SpeichernRunnable(ersterSatz));
		spiel.satzHinzufuegen(ersterSatz);
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(spiel));
	}
	
	public void spielFortsetzen(Spiel spiel,String pfad,char eigeneKennzeichnung,boolean neuerSatz){
		this.spiel = spiel;
		this.spiel.getSelbst().setKennzeichnung(eigeneKennzeichnung);
		this.spiel.getGegner().setKennzeichnung(getGegnerKennzeichnung(eigeneKennzeichnung));
		this.dateiverwaltung.setPfad(pfad);
		if(neuerSatz){
			spiel.satzHinzufuegen(new Satz(this.spiel));
			ThreadExecutor.getInstance().execute(new SpeichernRunnable(this.spiel.getAktuellenSatz()));
		}
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
	
	public void allesZuruecksetzen(){
		HSQLConnection.getInstance().loeschen(Strings.SPIELER_ZURUECKSETZEN);
		HSQLConnection.getInstance().loeschen(Strings.SPIEL_ZURUECKSETZEN);
		HSQLConnection.getInstance().loeschen(Strings.SATZ_ZURUECKSETZEN);
		HSQLConnection.getInstance().loeschen(Strings.ZUG_ZURUECKSETZEN);
		this.spiel = null;
		this.runde = 1;
	}
	
	public DateiVerwaltung getDateiVerwaltung(){
		return dateiverwaltung;
	}
}