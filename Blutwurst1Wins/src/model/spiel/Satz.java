package model.spiel;

import runnable.SpeichereSatzRunnable;
import runnable.ThreadExecutor;

public class Satz extends DBObject{
	private int satzNr;
	private int spielerBegonnen;
	private Spiel spiel;
	private Spieler gewonnen;
	
	public Satz(int satzNr,int spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
	public int spielerBegonnen(){
		return spielerBegonnen;
	}
	public int getSatzNr(){
		return satzNr;
	}

	@Override
	public void speichern() {
		SpeichereSatzRunnable speichern = new SpeichereSatzRunnable(spiel.getSpielNr(), gewonnen.getID());
		ThreadExecutor.getInstance().execute(speichern);
	}

	@Override
	public int ladeIDausDB() {

		return 0;
	}
}
