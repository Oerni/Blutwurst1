
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