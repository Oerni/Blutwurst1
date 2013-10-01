package model.spiel;

public class Spieler extends DBObject{
	private String name;
	private int kennzeichnung;		// Spieler X oder Spieler O
	private int punktzahlSatz;
	
	public static final int SPIELER_X = 0;
	public static final int SPIELER_O = 1;
	
	public Spieler(String name,int kennzeichnung){
		this.name = name;
		punktzahlSatz = 0;
		this.kennzeichnung = kennzeichnung;
	}
	
	public int getKennzeichnung(){
		return kennzeichnung;
	}
	
	public String getName(){
		return name;
	}
	
	public int getSatzpunkte(){
		return punktzahlSatz;
	}
	
	public void speichern(){
		
	}
}
