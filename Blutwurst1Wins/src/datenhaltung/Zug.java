package datenhaltung;

import parallelisierung.SpeichereZugRunnable;
import parallelisierung.ThreadExecutor;

public class Zug extends DBObject{
	/**
	 * Model-Klasse: Zug
	 */
	private Spieler spieler;
	private Satz satz;
	private boolean freigabe;
	private String satzstatus;
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
	}
	
	public Zug(int spalte,Spieler spieler){
		this.spalte = spalte;
		this.spieler = spieler;
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
	
	@Override
	public int speichern(){
		SpeichereZugRunnable speichern = new SpeichereZugRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
		return -1;
	}
	
	public int ladeIDausDB(){
		return -1;
	}
	
	public Zug(boolean freigabe,int satzstatus,int spalte,int sieger,Spieler spieler){
		this.freigabe = freigabe;
		this.spieler = spieler;
		this.spalte = spalte;
		
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
	
	public String getSieger(){
		return sieger;
	}

	public void setZeile(int zeile){
		this.zeile = zeile;
	}
}
