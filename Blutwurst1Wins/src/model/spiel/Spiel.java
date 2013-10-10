package model.spiel;

import java.sql.ResultSet;
import java.sql.SQLException;

import runnable.SpeichereSpielRunnable;
import runnable.ThreadExecutor;

public class Spiel extends DBObject{
	private int spielNr;
	private Spieler gegner;
	private Spieler selbst;
	private int punkteHeim;
	private int punkteGegner;
	private Spieler gewinner;
	private String spielstand;
	
	public Spiel(Spieler gegner,int punkteHeim,int punkteGegner){
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		if(punkteHeim>punkteGegner){
			this.gewinner = selbst;
		}
		else
			this.gewinner = gegner;
	}
	
	public Spiel(int spielNr,Spieler gegner,int punkteHeim,int punkteGegner){
		this.spielNr = spielNr;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		if(punkteHeim>punkteGegner)
			this.gewinner = selbst;
		else
			this.gewinner = gegner;
	}
	
	public int getSpielNr(){
		return spielNr;
	}
	public Spieler getGegner(){
		return gegner;
	}
	public int getPunkteHeim(){
		return punkteHeim;
	}
	public int getPunkteGegner(){
		return punkteGegner;
	}
	public Spieler getGewinner(){
		return gewinner;
	}
	public String getSpielstand(){
		return spielstand;
	}
	
//	public static void main(String[] args){
//		new Spiel("Gegner 1",3,5).speichern();
//	}
	
	public void speichern(){
		SpeichereSpielRunnable speichern = new SpeichereSpielRunnable(gegner.getID(), punkteHeim, punkteGegner);
		ThreadExecutor.getInstance().execute(speichern);
	}
	
	public int ladeIDausDB(){
		return 1;
	}
}
