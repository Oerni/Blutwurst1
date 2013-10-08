package model.spiel;

public class Satz {
	private int satzNr;
	private int spielerBegonnen;
	private Spiel spiel;
	
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
}
