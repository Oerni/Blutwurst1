package model.spiel;

import runnable.SpeichereZugRunnable;
import runnable.ThreadExecutor;

public class Zug extends DBObject{
	private Spieler spieler;
	private Satz satz;
	private boolean freigabe;
	private String satzstatus;
	private int gegnerzug;
	private String sieger;
	private int zeile;
	private int spalte;
	
	public static final int OFFEN = 1;
	public static final int BEENDET = 2;
	
	public static final int SIEGER_OFFEN = 0;
	public static final int SIEGER_O = 1;
	public static final int SIEGER_X = 2;
	
	public Zug(int zeile,int spalte,Spieler spieler,Satz satz){
		this.zeile = zeile;
		this.spalte = spalte;
		this.spieler = spieler;
		this.satz = satz;
		speichern();
	}
	
	public Satz getSatz(){
		return satz;
	}
	public int getZeile(){
		return zeile;
	}
	public int getSpalte(){
		return spalte;
	}
	
	public void speichern(){
		SpeichereZugRunnable speichern = new SpeichereZugRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
	}
	
	public int ladeIDausDB(){
		return -1;
	}
	
	public Zug(boolean freigabe,int satzstatus,int gegnerzug,int sieger){
		this.freigabe = freigabe;
		switch(satzstatus){
			case OFFEN:
				this.satzstatus = "Satz spielen";
				break;
			case BEENDET:
				this.satzstatus = "beendet";
				break;
		}
		switch(sieger){
			case SIEGER_OFFEN:
				this.sieger = "offen";
				break;
			case SIEGER_O:
				this.sieger = "SpielerO";
				break;
			case SIEGER_X:
				this.sieger = "SpielerX";
				break;
		}
		this.gegnerzug = gegnerzug;
	}
	public Spieler getSpieler(){
		return spieler;
	}
	public void setSpieler(Spieler spieler){
		this.spieler = spieler;
	}
	
	public boolean getFreigabe(){
		return freigabe;
	}
	
	public String getSatzstatus(){
		return satzstatus;
	}
	
	public int getGegnerzug(){
		return gegnerzug;
	}
	
	public String getSieger(){
		return sieger;
	}

}
