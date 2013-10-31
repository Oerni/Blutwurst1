
package datenhaltung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import parallelisierung.AnzahlSiegeNiederlagenRunnable;
import parallelisierung.BegonneneSaetzeGewonnenRunnable;
import parallelisierung.BestenlisteRunnable;
import parallelisierung.SpieldatenRunnable;
import parallelisierung.ThreadExecutor;

public class StatistikModel {
	private Stage stage;
	private ObservableList<Spiel> spiele = FXCollections.observableArrayList();
	private ObservableList<Highscore> bestenliste = FXCollections.observableArrayList();
	private BegonneneSaetzeGewonnen begonnenUndGewonnen;
	private SiegeNiederlagen siegeNiederlagen;
	
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
//		ResultSet bestenlisteSQL = HSQLConnection.getInstance().executeQuery(Strings.HIGHSCORE);
//		
//		try{
//			while(bestenlisteSQL.next()){
//				try{
//					bestenliste.add(new Highscore(new Spieler(bestenlisteSQL.getString("name")),bestenlisteSQL.getInt("anzahlsiege")));
//				}catch(SQLException ex){
//					ex.printStackTrace();
//				}
//			}
//			return bestenliste;
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}
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
//		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Strings.SPIELDATEN);
//		try{
//			while(spieldatenSQL.next()){
//				Spiel spiel = new Spiel(spieldatenSQL.getInt("id"),new Spieler(spieldatenSQL.getString("gegner")),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punkteGegner"));
//				spiele.add(spiel);
//				ThreadExecutor.getInstance().execute(new SpielDatenSaetzeRunnable(spiel));
//			}
//			return spiele;
//		}catch(SQLException ex){
//			ex.printStackTrace();
//			return null;
//		}
//		Future<ObservableList<Spiel>> spieldaten = ThreadExecutor.getInstance().getSpieldaten(new SpieldatenCallable());
//		while(!spieldaten.isDone()){}
//		try{
//			return spieldaten.get();
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return null;
//		}
		while(!spieleFertig){}
		return spiele;
	}
	
	public void setSpieldaten(ObservableList<Spiel> spiele){
		this.spiele = spiele;
		this.spieleFertig = true;
	}
}