
package model.statistik;

import java.util.concurrent.Future;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.spiel.Spiel;
import runnable.AnzahlNiederlagenCallable;
import runnable.AnzahlSiegeCallable;
import runnable.SpieldatenCallable;
import runnable.ThreadExecutor;

public class StatistikModel {
	private Stage stage;
	
	public StatistikModel(Stage stage){
		this.stage = stage;
	}
	
	public Stage getStage(){
		return stage;
	}
//	Rückgabe der Anzahl der erspielten Siege
	public int getAnzahlSiege(){
		Future<Number> anzahlSiege = ThreadExecutor.getInstance().getAggregate(new AnzahlSiegeCallable());
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
		Future<Number> anzahlNiederlagen = ThreadExecutor.getInstance().getAggregate(new AnzahlNiederlagenCallable());
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
		Future<ObservableList<Spiel>> spieldaten = ThreadExecutor.getInstance().getSpieldaten(new SpieldatenCallable());
		while(!spieldaten.isDone()){}
		try{
			return spieldaten.get();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
}