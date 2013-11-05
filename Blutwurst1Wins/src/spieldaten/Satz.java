package spieldaten;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import parallelisierung.SemaphorManager;

public class Satz extends DBObject{
	
	/**
	 * Model-Klasse: Satz
	 */
	private Spieler spielerBegonnen;
	private Spiel spiel;
	private Spieler gewonnen;
	private Stack<Zug> zuege = new Stack<Zug>();
	
	public Satz(int satzNr,Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
		this.id = satzNr;
	}
	public Satz(Spiel spiel,int satzNr){
		this.spiel = spiel;
		this.id = satzNr;
	}
//	Simulation
	public Satz(Spiel spiel,int satzNr,Spieler spielerBegonnen){
		this.spiel = spiel;
		this.id = satzNr;
		this.spielerBegonnen = spielerBegonnen;
		
		ResultSet zuegeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.ZUEGE_EINES_SATZES,satzNr,spiel.getID()));
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
		this.id = satzNr;
		this.gewonnen = gewinner;
	}
	
//	Initiales Instanziieren im Spielverlauf
	public Satz(Spieler spielerBegonnen,Spiel spiel){
		this.spielerBegonnen = spielerBegonnen;
		this.spiel = spiel;
	}
	
	public Satz(Spiel spiel){
		this.spiel = spiel;
	}
	
	public Stack<Zug> getZuege(){
		return zuege;
	}
	public void setSieger(Spieler sieger){
		this.gewonnen = sieger;
	}
	public Spiel getSpiel(){
		return spiel;
	}
	public Spieler spielerBegonnen(){
		return spielerBegonnen;
	}
	public Spieler getSieger(){
		return gewonnen;
	}
	
	public void setBeginnendenSpieler(Spieler spieler){
		spielerBegonnen = spieler;
	}
	
	public void zugEinfuegen(Zug zug){
		this.zuege.add(zug);
	}

	@Override
	public void speichern() throws SQLException{
		int spielnr = spiel != null ? spiel.getID() : -1;
		String spielerName = spielerBegonnen != null ? spielerBegonnen.getName() : "";
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		this.id = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"satz","spielnr,beginner",spielnr+",'"+spielerName+"'"),String.format(Strings.LETZTE_SATZ_NR,spielnr));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	@Override
	public void aktualisieren(){
		int spielnr = spiel != null ? spiel.getID() : -1;
		String beginnerName = spielerBegonnen != null ? spielerBegonnen.getName() : "";
		String gewinnerName = gewonnen != null ? gewonnen.getName() : "";
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().update(String.format(Strings.SATZ_AKTUALISIEREN,spielnr,beginnerName,gewinnerName,this.id));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	public void ladeZuege(){
		ResultSet zuegeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.ZUEGE_EINES_SATZES,this.id,spiel.getID()));
		try{
			while(zuegeSQL.next()){
				int zeile = zuegeSQL.getInt("zeile");
				int spalte = zuegeSQL.getInt("spalte");
				String spielerName = zuegeSQL.getString("spieler");
				Spieler spieler = spiel.getSelbst().getName().equals(spielerName) ? spiel.getSelbst() : spiel.getGegner();
				zuege.add(new Zug(zeile,spalte,spieler,this));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
