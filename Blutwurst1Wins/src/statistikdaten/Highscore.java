package statistikdaten;

import spieldaten.Spieler;


public class Highscore {
	private Spieler spieler;
	private int anzahlSiege;
	
	public Highscore(Spieler spieler,int anzahlSiege){
		this.spieler = spieler;
		this.anzahlSiege = anzahlSiege;
	}
	
	public String getSpielerName(){
		return spieler.getName();
	}
	
	public String getAnzahlSiege(){
		return ""+anzahlSiege;
	}
}
