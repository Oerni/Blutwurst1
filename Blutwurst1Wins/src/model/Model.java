package model;

import java.util.Stack;

import javafx.stage.Stage;

public class Model {
	private Stage stage;
	private HSQLConnection dbconnect = new HSQLConnection();
	private Spieler gegner;
	private Spieler selbst;
	private DateiVerwaltung dateiverwaltung = new DateiVerwaltung("");
	private Stack<Zug> spielverlauf = new Stack<Zug>();
	private char aktuellerSpieler = 'X';
	private Spielfeld spielfeld = new Spielfeld();
	
	public Model(Stage stage){
		this.stage = stage;
	}
	
	public int chipEinwerfen(int spalte){
		int ergebnis = spielfeld.einfuegen(spalte,aktuellerSpieler);
		if(aktuellerSpieler == 'X')
			aktuellerSpieler = 'O';
		else
			aktuellerSpieler = 'X';
		return ergebnis;
	}
	public char getAktuellerSpieler(){
		return aktuellerSpieler;
	}
	
	public void zuruecksetzen(){
		spielfeld = new Spielfeld();
	}
	public void setSpieler(int eigeneKennzeichnung){
		selbst = new Spieler("blutwurst1",eigeneKennzeichnung);
		switch(eigeneKennzeichnung){
			case Spieler.SPIELER_O:
				gegner = new Spieler("Gegner",Spieler.SPIELER_X);
				break;
			case Spieler.SPIELER_X:
				gegner = new Spieler("Gegner",Spieler.SPIELER_O);
				break;
		}
	}
	
	public int getEigeneKennzeichnung(){
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
	
//	Punktzahlen ausgeben
	public int getEigeneSatzpunkte(){
		return selbst.getSatzpunkte();
	}
	public int getGegnerSatzpunkte(){
		return gegner.getSatzpunkte();
	}
	
	public Zug zugVonServer(){
		Zug zug = dateiverwaltung.dateiLesen();
		zug.setSpieler(gegner);
		spielverlauf.push(zug);
		return zug;
	}
	public void zugAnServer(Zug zug){
		zug.setSpieler(selbst);
		dateiverwaltung.dateiSchreiben(""+zug.getGegnerzug());
	}
	
	public void speichern(){
		selbst.speichern();
		gegner.speichern();
	}
}