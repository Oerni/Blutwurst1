package datenhaltung;

public class BegonneneSaetzeGewonnen {
	private int gewonnen,andere;
	
	public BegonneneSaetzeGewonnen(int gewonnen,int gesamt){
		this.gewonnen = gewonnen;
		this.andere = gesamt-gewonnen;
	}
	
	public int getGewonneneSaetze(){
		return gewonnen;
	}
	public int getAnzahlRestSaetze(){
		return andere;
	}
}
