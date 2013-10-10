package datenhaltung;

import parallelisierung.SpeichereSatzRunnable;
import parallelisierung.ThreadExecutor;

public class Satz extends DBObject{
	
	/**
	 * Model-Klasse: Satz
	 */
	private int satzNr;
	private Spieler spielerBegonnen;
	private Spiel spiel;
	private Spieler gewonnen;
	
	public Satz(int satzNr,Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.satzNr = satzNr;
	}
	public Satz(int satzNr,Spieler spielerBegonnen,Spiel spiel,Spieler gewinner){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.satzNr = satzNr;
		this.gewonnen = gewinner;
	}
	public Satz(Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
	}
	
	public void setGewinner(Spieler gewinner){
		this.gewonnen = gewinner;
	}
	public Spiel getSpiel(){
		return spiel;
	}
	public Spieler spielerBegonnen(){
		return spielerBegonnen;
	}
	public int getSatzNr(){
		return satzNr;
	}
	public Spieler getGewinner(){
		return gewonnen;
	}

	@Override
	public void speichern() {
		SpeichereSatzRunnable speichern = new SpeichereSatzRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
	}

	@Override
	public int ladeIDausDB() {

		return 0;
	}
}
