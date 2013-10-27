package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import parallelisierung.SemaphorManager;

public class Spiel extends DBObject{
	/**
	 * Model-Klasse: Spiel
	 */
	private Spieler gegner;
	private Spieler selbst;
	private int punkteHeim = 0;
	private int punkteGegner = 0;
	private String spielstand;
	private Spieler sieger;
	private Stack<Satz> saetze = new Stack<Satz>();
	
//	Initiales Instanziieren im Spielverlauf
	public Spiel(Spieler gegner,Spieler selbst){
		this.gegner = gegner;
		this.selbst = selbst;
	}
	
//	Simulation
	public Spiel(int spielnr){
		ResultSet spiel = HSQLConnection.getInstance().executeQuery(String.format(Strings.SPIEL,spielnr));
		try{
			spiel.next();
			gegner = new Spieler(spiel.getString("name"));
			this.id = spielnr;
			this.punkteHeim = spiel.getInt("punkteheim");
			this.punkteGegner = spiel.getInt("punkteGegner");
			ResultSet saetzeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.SAETZE_EINES_SPIELS,spielnr));
			while(saetzeSQL.next()){
				Spieler beginner = saetzeSQL.getInt("id") == selbst.getID() ? selbst : gegner;
				saetze.add(new Satz(this,saetzeSQL.getInt("satznr"),beginner));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public Stack<Satz> getSaetze(){
		return saetze;
	}
	
	public void satzHinzufuegen(Satz satz){
		saetze.add(satz);
	}
	
	public Satz getAktuellenSatz(){
		return saetze.lastElement();
	}
	
	public Spieler getSpieler(int id){
		return selbst.getID() == id ? selbst : gegner;
	}
	
	public Spiel(Spieler gegner,int punkteHeim,int punkteGegner){
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
	}
	
	
//	Statistik-Konstruktor
	public Spiel(int spielNr,Spieler gegner,int punkteHeim,int punkteGegner){
		this.id = spielNr;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
	}
	
	public Spieler getGegner(){
		return gegner;
	}
	public int getPunkteHeim(){
		return punkteHeim;
	}
	public int getPunkteGegner(){
		return punkteGegner;
	}
	public Spieler getSieger(){
		return sieger;
	}
	public void setSieger(Spieler sieger){
		this.sieger = sieger;
	}
	public String getSpielstand(){
		return spielstand;
	}
	public String getGegnerName(){
		return gegner.getName();
	}
	
	@Override
	public void speichern(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		this.id = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"spiel","gegner,punkteheim,punktegegner","'"+gegner.getName()+"',"+punkteHeim+","+punkteGegner),String.format(Strings.LETZTES_SPIEL_NR,"'"+gegner.getName()+"'"));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	@Override
	public void aktualisieren(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().update(String.format(Strings.SPIEL_AKTUALISIEREN,this.gegner.getName(),this.punkteHeim,this.punkteGegner,this.sieger.getName(),this.id));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
}
