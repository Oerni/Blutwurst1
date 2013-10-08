package model.spiel;

public class Spieler extends DBObject{
	private int id;
	private String name;
	private char kennzeichnung;		// Spieler X oder Spieler O
	
	private static int amZug = 0;
	public static final int SPIELER_X = 0;
	public static final int SPIELER_O = 1;
	
	public Spieler(int id,String name,char kennzeichnung){
		this.id = id;
		this.name = name;
		this.kennzeichnung = kennzeichnung;
	}
	
	public static int getSpielerAmZug(){
		return amZug;
	}
	
	public int getID(){
		return id;
	}
	
	public char getKennzeichnung(){
		return kennzeichnung;
	}
	
	public String getName(){
		return name;
	}
	
	public void speichern(){
		
	}
}
