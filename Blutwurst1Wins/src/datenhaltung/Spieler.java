package datenhaltung;

import parallelisierung.SemaphorManager;

public class Spieler extends DBObject{
	/**
	 * Model-Klasse: Spieler
	 */
	private String name;
	private char kennzeichnung;		// Spieler X oder Spieler O
	
	public Spieler(String name,char kennzeichnung){
		this.name = name;
		this.kennzeichnung = kennzeichnung;
	}
	public Spieler(String name){
		this.name = name;
	}
	
	public char getKennzeichnung(){
		return kennzeichnung;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public void speichern(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		this.id = HSQLConnection.getInstance().insert(String.format(Strings.INSERT, "spieler", "name", "'"+name+"'"),"ohne_id");
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	@Override
	public void aktualisieren(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().update(String.format(Strings.SPIELER_AKTUALISIEREN,this.name,this.id));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
}
