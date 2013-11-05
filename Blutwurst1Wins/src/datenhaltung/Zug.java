package datenhaltung;

import java.sql.SQLException;

import parallelisierung.SemaphorManager;

public class Zug extends DBObject{
	/**
	 * Model-Klasse: Zug
	 */
	private Spieler spieler;
	private Satz satz;
	private boolean freigabe;
	private String satzstatus;
	private Spieler sieger;
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
	
	public Zug(boolean freigabe,int satzstatus,int spalte,Spieler sieger,Spieler spieler){
		this.freigabe = freigabe;
		this.spieler = spieler;
		this.spalte = spalte;
		this.sieger = sieger;
		
		switch(satzstatus){
			case OFFEN:
				this.satzstatus = "Satz spielen";
				break;
			case BEENDET:
				this.satzstatus = "beendet";
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
	
	public Spieler getSieger(){
		return sieger;
	}

	public void setZeile(int zeile){
		this.zeile = zeile;
	}
	
	public void satzZuordnen(Satz satz){
		this.satz = satz;
	}
	
	@Override
	public void speichern() throws SQLException{
		int satznr = satz != null ? satz.getID() : -1;
		int spielnr = satz.getSpiel() != null ? satz.getSpiel().getID() : -1;
		String spielerName = spieler != null ? spieler.getName() : "";
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		String query1 = String.format(Strings.INSERT,"zug","satznr,spielnr,spalte,zeile,spieler",satznr+","+spielnr+","+spalte+","+zeile+",'"+spielerName+"'");
		String query2 = String.format(Strings.LETZTER_ZUG_NR,satz.getID(),satz.getSpiel().getID());
		this.id = HSQLConnection.getInstance().insert(query1,query2);
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	@Override
	public void aktualisieren(){
		int satznr = satz != null ? satz.getID() : -1;
		int spielnr = satz.getSpiel() != null ? satz.getSpiel().getID() : -1;
		String spielerName = spieler != null ? spieler.getName() : "";
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().update(String.format(Strings.ZUG_AKTUALISIEREN,satznr,spielnr,this.spalte,this.zeile,spielerName,this.id));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
}
