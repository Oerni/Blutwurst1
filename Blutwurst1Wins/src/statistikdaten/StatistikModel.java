
package statistikdaten;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import parallelisierung.AnzahlSiegeNiederlagenRunnable;
import parallelisierung.BegonneneSaetzeGewonnenRunnable;
import parallelisierung.BestenlisteRunnable;
import parallelisierung.SpieldatenRunnable;
import parallelisierung.ThreadExecutor;
import spieldaten.Spiel;

public class StatistikModel {
	private Stage stage;
	private ObservableList<Spiel> spiele = FXCollections.observableArrayList();
	private ObservableList<Highscore> bestenliste = FXCollections.observableArrayList();
	private BegonneneSaetzeGewonnen begonnenUndGewonnen;
	private SiegeNiederlagen siegeNiederlagen;
	
//	Indikator, ob parallel initialisierte Attribute fertiggestellt sind
	private boolean spieleFertig = false;
	private boolean bestenlisteFertig = false;
	private boolean begonnenUndGewonnenFertig = false;
	private boolean siegeNiederlagenFertig = false;
	
	public StatistikModel(Stage stage){
		this.stage = stage;
		ThreadExecutor.getInstance().execute(new AnzahlSiegeNiederlagenRunnable(this));
		ThreadExecutor.getInstance().execute(new BegonneneSaetzeGewonnenRunnable(this));
		ThreadExecutor.getInstance().execute(new BestenlisteRunnable(this));
		ThreadExecutor.getInstance().execute(new SpieldatenRunnable(this));
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void siegeNiederlagenFertig(){
		this.siegeNiederlagenFertig = true;
	}
	public void spieleFertig(){
		this.spieleFertig = true;
	}
	public void bestenlisteFertig(){
		this.bestenlisteFertig = true;
	}
	public void begonnenUndGewonnenFertig(){
		this.begonnenUndGewonnenFertig = true;
	}
	
	public SiegeNiederlagen getSiegeNiederlagen(){
		while(!siegeNiederlagenFertig){}
		return siegeNiederlagen;
	}
	
	public void setSiegeNiederlagen(SiegeNiederlagen siegeNiederlagen){
		this.siegeNiederlagen = siegeNiederlagen;
		this.siegeNiederlagenFertig = true;
	}
	
//	Bestenliste
	public ObservableList<Highscore> getBestenliste(){
		while(!bestenlisteFertig){}
		return bestenliste;
	}
	
	public void setBestenliste(ObservableList<Highscore> bestenliste){
		this.bestenliste = bestenliste;
		bestenlisteFertig = true;
	}
	
	public BegonneneSaetzeGewonnen getBegonneneSaetzeGewonnen(){
		while(!begonnenUndGewonnenFertig){}
		return begonnenUndGewonnen;
	}
	
	public void setBegonneneSaetzeGewonnen(BegonneneSaetzeGewonnen begonnenenUndGewonnen){
		this.begonnenUndGewonnen = begonnenenUndGewonnen;
		this.begonnenUndGewonnenFertig = true;
	}
	
//	Spieldaten zur Anzeige aller gespielten Spiele
	public ObservableList<Spiel> getSpieldaten(){
		while(!spieleFertig){}
		return spiele;
	}
	
	public void setSpieldaten(ObservableList<Spiel> spiele){
		this.spiele = spiele;
		this.spieleFertig = true;
	}
}