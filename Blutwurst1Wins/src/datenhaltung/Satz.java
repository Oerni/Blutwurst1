package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import parallelisierung.SpeichereSatzRunnable;
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
	public Satz(Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
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
	public void speichern() {
		SpeichereSatzRunnable speichern = new SpeichereSatzRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
	}

	@Override
	public int ladeIDausDB() {

		return 0;
	}
}
