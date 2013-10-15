package datenhaltung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import parallelisierung.SpeichereSpielRunnable;
import parallelisierung.ThreadExecutor;

public class Spiel extends DBObject{
	/**
	 * Model-Klasse: Spiel
	 */
	private int spielNr;
	private Spieler gegner;
	private Spieler selbst;
	private int punkteHeim;
	private int punkteGegner;
	private String spielstand;
	private Stack<Satz> saetze = new Stack<Satz>();
	
	public Spiel(Spieler gegner){
		this.gegner = gegner;
	}
	
//	Simulation
	public Spiel(int spielnr){
		ResultSet spiel = HSQLConnection.getInstance().executeQuery(String.format(Strings.SPIEL,spielnr));
		try{
			spiel.next();
			gegner = new Spieler(spiel.getString("name"));
			this.spielNr = spielnr;
			this.punkteHeim = spiel.getInt("punkteheim");
			this.punkteGegner = spiel.getInt("punkteGegner");
			ResultSet saetzeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.SAETZE_EINES_SPIELS,spielnr));
			while(saetzeSQL.next()){
				saetze.add(new Satz(this,saetzeSQL.getInt("satznr")));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
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
		this.spielNr = spielNr;
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
		this.spielstand = punkteHeim + ":" + punkteGegner;
		this.selbst = new Spieler(Strings.NAME,'O');
	}
	
	public int getSpielNr(){
		return spielNr;
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
	public String getGewinnerName(){
		return punkteHeim > punkteGegner ? selbst.getName() : gegner.getName();
	}
	public String getSpielstand(){
		return spielstand;
	}
	public String getGegnerName(){
		return gegner.getName();
	}
	
	public void speichern(){
		SpeichereSpielRunnable speichern = new SpeichereSpielRunnable(this);
		ThreadExecutor.getInstance().execute(speichern);
	}
	
	public int ladeIDausDB(){
		return 1;
	}
}
