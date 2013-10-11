package datenhaltung;

import java.util.Stack;

import javafx.stage.Stage;
import logik.Spielfeld;

public class SpielModel {
	/**
	 * SpielModel. Zentraler Ansprechpartner des SpielViewController zur Vereinheitlichung
	 * der Kommunikation. ViewController hat keinen Einblick, was die Quelle der jeweiligen
	 * Daten ist.
	 */
	private Stage stage;
	private Spiel spiel;
	private Stack<Satz> saetze = new Stack<Satz>();
	private Stack<Zug> zuege = new Stack<Zug>();
	private Spieler gegner;
	private Spieler selbst;
	private Spieler aktuellerSpieler;
	private DateiVerwaltung dateiverwaltung = new DateiVerwaltung("");
	private Spielfeld spielfeld = new Spielfeld();
	
	public SpielModel(Stage stage){
		this.stage = stage;
	}
	
	public void init(){
		selbst = new Spieler(Strings.NAME,'X');
		gegner = new Spieler("Gegner",'O');
		aktuellerSpieler = getBeginnendenSpieler();
		spiel = new Spiel(gegner);
		saetze.add(new Satz(aktuellerSpieler,spiel));
	}
	
	private Spieler getBeginnendenSpieler(){
		return selbst.getKennzeichnung()=='X' ? selbst : gegner;
	}
	
	public int zugDurchfuehren(int spalte){
		int ergebnis = spielfeld.einfuegen(spalte,aktuellerSpieler);
		zuege.push(new Zug(ergebnis,spalte,aktuellerSpieler,saetze.lastElement()));
		if(aktuellerSpieler == gegner)
			aktuellerSpieler = selbst;
		else
			aktuellerSpieler = gegner;
		return ergebnis;
	}
	
	public Spieler getAktuellerSpieler(){
		return aktuellerSpieler;
	}
	
	public void zuruecksetzen(){
		spielfeld = new Spielfeld();
	}
	
	public char getEigeneKennzeichnung(){
		return selbst.getKennzeichnung();
	}
//	Zug-Informationen
	

	public Stage getPrimaryStage(){
		return stage;
	}
//	Namen ausgeben
	public String getEigenenNamen(){
		return selbst.getName();
	}
	public String getGegnerNamen(){
		return gegner.getName();
	}
	
	public Zug zugVonServer(){
		Zug zug = dateiverwaltung.dateiLesen();
		zug.setSpieler(gegner);
//		spielverlauf.push(zug);
		return zug;
	}
	public void zugAnServer(Zug zug){
		zug.setSpieler(selbst);
		dateiverwaltung.dateiSchreiben(""+zug.getGegnerzug());
	}
	
	public void allesSpeichern(){
		selbst.speichern();
		gegner.speichern();
		saetze.lastElement().speichern();
	}
}