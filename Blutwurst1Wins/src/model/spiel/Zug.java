package model.spiel;

public class Zug {
	private Spieler spieler;
	private boolean freigabe;
	private String satzstatus;
	private int gegnerzug;
	private String sieger;
	
	public static final int OFFEN = 1;
	public static final int BEENDET = 2;
	
	public static final int SIEGER_OFFEN = 0;
	public static final int SIEGER_O = 1;
	public static final int SIEGER_X = 2;
	
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
