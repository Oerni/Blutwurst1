package model.spiel;

import java.sql.ResultSet;
import java.sql.SQLException;

import runnable.SpeichereSpielerRunnable;
import runnable.ThreadExecutor;

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
	public Spieler(String name,char kennzeichnung){
		this.name = name;
		this.kennzeichnung = kennzeichnung;
		speichern();
		this.id = ladeIDausDB();
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
		SpeichereSpielerRunnable speichern = new SpeichereSpielerRunnable(name);
		ThreadExecutor.getInstance().execute(speichern);
	}
	
	public int ladeIDausDB(){
		ResultSet nameSQL = HSQLConnection.getInstance().executeQuery("SELECT id FROM spieler WHERE name = '" + name + "'");
		try{
			nameSQL.next();
			return nameSQL.getInt("id");
		}catch(SQLException ex){
			ex.printStackTrace();
			return -1;
		}
	}
}
