
package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Future;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import parallelisierung.AnzahlNiederlagenCallable;
import parallelisierung.AnzahlSiegeCallable;
import parallelisierung.SpielDatenSaetzeRunnable;
import parallelisierung.ThreadExecutor;

public class StatistikModel {
	private Stage stage;
	private ObservableList<Spiel> spiele = FXCollections.observableArrayList();
	private ObservableList<Highscore> bestenliste = FXCollections.observableArrayList();
	private BegonneneSaetzeGewonnen begonnenUndGewonnen;
	
	public StatistikModel(Stage stage){
		this.stage = stage;
	}
	
	public Stage getStage(){
		return stage;
	}
//	Rückgabe der Anzahl der erspielten Siege
	public int getAnzahlSiege(){
		Future<Number> anzahlSiege = ThreadExecutor.getInstance().getNumber(new AnzahlSiegeCallable());
		while(!anzahlSiege.isDone()){}
		try{
			return anzahlSiege.get().intValue();
		}catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
//	Rückgabe der Anzahl der erspielten Niederlagen
	public int getAnzahlNiederlagen(){
		Future<Number> anzahlNiederlagen = ThreadExecutor.getInstance().getNumber(new AnzahlNiederlagenCallable());
		while(!anzahlNiederlagen.isDone()){}
		try{
			return anzahlNiederlagen.get().intValue();
		}catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
//	Bestenliste
	public ObservableList<Highscore> getBestenliste(){
		ResultSet bestenlisteSQL = HSQLConnection.getInstance().executeQuery(Strings.HIGHSCORE);
		
		try{
			while(bestenlisteSQL.next()){
				try{
					bestenliste.add(new Highscore(new Spieler(bestenlisteSQL.getString("name")),bestenlisteSQL.getInt("anzahlsiege")));
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			return bestenliste;
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return bestenliste;
	}
	
	public BegonneneSaetzeGewonnen getBegonneneSaetzeGewonnen(){
		if(begonnenUndGewonnen==null){
			ResultSet begonnenUndGewonnenSQL = HSQLConnection.getInstance().executeQuery(Strings.ANZAHL_BGONNENER_GEWONNENER_SAETZE);
			ResultSet alleSaetzeSQL = HSQLConnection.getInstance().executeQuery(Strings.ALLE_SAETZE);
			try{
				begonnenUndGewonnenSQL.next();
				int begonnenUndGewonnenAnzahl = begonnenUndGewonnenSQL.getInt("anzahlsiege");
				alleSaetzeSQL.next();
				int alleSaetze = alleSaetzeSQL.getInt("anzahlsaetze");
				begonnenUndGewonnen = new BegonneneSaetzeGewonnen(begonnenUndGewonnenAnzahl,alleSaetze);
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return begonnenUndGewonnen;
	}
	
//	Spieldaten zur Anzeige aller gespielten Spiele
	public ObservableList<Spiel> getSpieldaten(){
		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Strings.SPIELDATEN);
		try{
			while(spieldatenSQL.next()){
				Spiel spiel = new Spiel(spieldatenSQL.getInt("id"),new Spieler(spieldatenSQL.getString("gegner")),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punkteGegner"));
				spiele.add(spiel);
				ThreadExecutor.getInstance().execute(new SpielDatenSaetzeRunnable(spiel));
			}
			return spiele;
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
//		Future<ObservableList<Spiel>> spieldaten = ThreadExecutor.getInstance().getSpieldaten(new SpieldatenCallable());
//		while(!spieldaten.isDone()){}
//		try{
//			return spieldaten.get();
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return null;
//		}
		
	}
}