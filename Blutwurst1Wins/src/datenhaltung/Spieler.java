package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Future;

import parallelisierung.SpeichereSpielerCallable;
import parallelisierung.ThreadExecutor;

public class Spieler extends DBObject{
	/**
	 * Model-Klasse: Spieler
	 */
	private int id;
	private String name;
	private char kennzeichnung;		// Spieler X oder Spieler O
	
	public Spieler(int id,String name,char kennzeichnung){
		this.id = id;
		this.name = name;
		this.kennzeichnung = kennzeichnung;
	}
	public Spieler(String name,char kennzeichnung){
		this.name = name;
		this.kennzeichnung = kennzeichnung;
//		this.id = speichern();
//		this.id = ladeIDausDB();
	}
	public Spieler(String name){
		this.name = name;
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
	
	public int speichern(){
		Future<Number> idFuture = ThreadExecutor.getInstance().getNumber(new SpeichereSpielerCallable(this));
		while(!idFuture.isDone()){}
		try{
			Number id = idFuture.get();
			return id.intValue();
		}catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
}
