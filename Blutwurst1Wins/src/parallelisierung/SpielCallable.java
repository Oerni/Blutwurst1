package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.Spiel;

public class SpielCallable implements Callable<Spiel> {
	private int spielnr;
	
	public SpielCallable(int spielnr){
		this.spielnr = spielnr;
	}
	
	public Spiel call(){
		return new Spiel(spielnr);
	}
}