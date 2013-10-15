package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.concurrent.Future;

import parallelisierung.SpeichereSatzCallable;
import parallelisierung.ThreadExecutor;

public class Satz extends DBObject{
	
	/**
	 * Model-Klasse: Satz
	 */
	private int satzNr;
	private Spieler spielerBegonnen;
	private Spiel spiel;
	private Spieler gewonnen;
	private Stack<Zug> zuege = new Stack<Zug>();
	
	public Satz(int satzNr,Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.satzNr = satzNr;
	}
	public Satz(Spiel spiel,int satzNr){
		this.spiel = spiel;
		this.satzNr = satzNr;
	}
//	Simulation
	public Satz(Spiel spiel,int satzNr,Spieler spielerBegonnen){
		this.spiel = spiel;
		this.satzNr = satzNr;
		this.spielerBegonnen = spielerBegonnen;
		
		ResultSet zuegeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.ZUEGE_EINES_SATZES,satzNr,spiel.getSpielNr()));
		try{
			while(zuegeSQL.next()){
				zuege.add(new Zug(zuegeSQL.getInt("zeile"),zuegeSQL.getInt("spalte"),spiel.getSpieler(zuegeSQL.getInt("spieler")),this));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public Satz(int satzNr,Spieler spielerBegonnen,Spiel spiel,Spieler gewinner){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.satzNr = satzNr;
		this.gewonnen = gewinner;
	}
	
//	Initiales Instanziieren im Spielverlauf
	public Satz(Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.satzNr = speichern();
	}
	
	public Stack<Zug> getZuege(){
		return zuege;
	}
	public void setGewinner(Spieler gewinner){
		this.gewonnen = gewinner;
	}
	public Spiel getSpiel(){
		return spiel;
	}
	public Spieler spielerBegonnen(){
		return spielerBegonnen;
	}
	public int getSatzNr(){
		return satzNr;
	}
	public Spieler getGewinner(){
		return gewonnen;
	}

	@Override
	public int speichern() {
		Future<Number> satzNrFuture = ThreadExecutor.getInstance().getNumber(new SpeichereSatzCallable(this));
		while(!satzNrFuture.isDone()){}
		try{
			Number satzNr = satzNrFuture.get();
			return satzNr.intValue();
		}catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
}
