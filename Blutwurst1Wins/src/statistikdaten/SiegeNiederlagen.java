package statistikdaten;

public class SiegeNiederlagen {
	private int anzahlSiege,anzahlNiederlagen;
	
	public SiegeNiederlagen(int anzahlSiege,int anzahlNiederlagen){
		this.anzahlSiege = anzahlSiege;
		this.anzahlNiederlagen = anzahlNiederlagen;
	}
	
	public int getAnzahlSiege(){
		return anzahlSiege;
	}
	
	public int getAnzahlNiederlagen(){
		return anzahlNiederlagen;
	}
	
}
