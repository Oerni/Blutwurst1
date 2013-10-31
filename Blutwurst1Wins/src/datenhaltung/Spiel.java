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
	public Spiel(){
		
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
	
	public Spieler getSieger(char kennzeichnung){
		return selbst.getKennzeichnung() == kennzeichnung ? selbst : gegner;
	}
	
	public void erhoehePunkteHeim(){
		this.punkteHeim++;
	}
	
	public void erhoehePunkteGegner(){
		this.punkteGegner++;
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
	public Spiel(int id,Spieler gegner,int punkteHeim,int punkteGegner){
		this.id = id;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
		this.sieger = punkteHeim > punkteGegner ? selbst : gegner;
	}
	
	public Spieler getSelbst(){
		return selbst;
	}
	
	public void setSelbst(Spieler spieler){
		this.selbst = spieler;
	}
	
	public String getIdString(){
		return ""+id;
	}
	
	public Spieler getSpieler(String name){
		return selbst.getName().equals(name) ? selbst : gegner;
	}
	
	public Spieler getGegner(){
		return gegner;
	}
	
	public void setGegner(Spieler spieler){
		this.gegner = spieler;
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
	public String getSiegerName(){
		return sieger.getName();
	}
	public void setSieger(Spieler sieger){
		this.sieger = sieger;
	}
	public String getSpielstand(){
		return spielstand;
	}
	
//	Statistik
	public String getGegnerName(){
		return gegner.getName();
	}
	
	@Override
	public void speichern(){
		String gegnerName = gegner != null ? gegner.getName() : null;
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		this.id = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"spiel","gegner,punkteheim,punktegegner","'"+gegnerName+"',"+punkteHeim+","+punkteGegner),String.format(Strings.LETZTES_SPIEL_NR,"'"+gegnerName+"'"));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
	
	@Override
	public void aktualisieren(){
		String gegnerName = gegner != null ? gegner.getName() : "";
		String siegerName = sieger != null ? sieger.getName() : "";
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		HSQLConnection.getInstance().update(String.format(Strings.SPIEL_AKTUALISIEREN,gegnerName,this.punkteHeim,this.punkteGegner,siegerName,this.id));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}
}
