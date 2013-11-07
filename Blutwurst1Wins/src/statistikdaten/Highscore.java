package statistikdaten;

import spieldaten.Spieler;


public class Highscore {
	private Spieler spieler;
	private int anzahlSiege;
	private int rang;
	
	public Highscore(int rang,Spieler spieler,int anzahlSiege){
		this.rang = rang;
		this.spieler = spieler;
		this.anzahlSiege = anzahlSiege;
	}
	
	public String getRang(){
		return ""+rang;
	}
	
	public String getSpielerName(){
		return spieler.getName();
	}
	
	public String getAnzahlSiege(){
		return ""+anzahlSiege;
	}
}
