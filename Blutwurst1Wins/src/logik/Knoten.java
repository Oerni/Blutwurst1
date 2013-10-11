package logik;

public class Knoten {
	private char besetztVon;
	private int zeile;
	private int spalte;
	
	public Knoten(int zeile,int spalte){
		this.zeile = zeile;
		this.spalte = spalte;
		besetztVon = ' ';
	}
	
	public char getBesetztVon(){
		return besetztVon;
	}
	public void besetzen(char spieler) throws Exception{
		if(spieler == 'O' || spieler == 'X')
			this.besetztVon = spieler;
		else throw new Exception("Bitte Spieler X oder O eintragen!");
	}
	public int getZeile(){
		return zeile;
	}
	public int getSpalte(){
		return spalte;
	}
}