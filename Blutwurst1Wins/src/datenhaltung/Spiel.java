package datenhaltung;

import parallelisierung.SpeichereSpielRunnable;
import parallelisierung.ThreadExecutor;

public class Spiel extends DBObject{
	/**
	 * Model-Klasse: Spiel
	 */
	private int spielNr;
	private Spieler gegner;
	private Spieler selbst;
	private int punkteHeim;
	private int punkteGegner;
	private String spielstand;
	
	public Spiel(Spieler gegner){
		this.gegner = gegner;
	}
	
	public Spiel(Spieler gegner,int punkteHeim,int punkteGegner){
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
	}
	
	
//	Statistik-Konstruktor
	public Spiel(int spielNr,Spieler gegner,int punkteHeim,int punkteGegner){
		this.spielNr = spielNr;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
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
	public String getGewinnerName(){
		return punkteHeim > punkteGegner ? selbst.getName() : gegner.getName();
	}
	public String getSpielstand(){
		return spielstand;
	}
	public String getGegnerName(){
		return gegner.getName();
	}
	
	public void speichern(){
		SpeichereSpielRunnable speichern = new SpeichereSpielRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
	}
	
	public int ladeIDausDB(){
		return 1;
	}
}
