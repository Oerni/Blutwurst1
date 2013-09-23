package model;

public class Spieler {
	private String name;
	private int punktzahlSatz;
	
	public Spieler(String name){
		this.name = name;
		punktzahlSatz = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getSatzpunkte(){
		return punktzahlSatz;
	}
}
