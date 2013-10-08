package model.spiel;

import javafx.stage.Stage;
import Logik.Spielfeld;

public class SpielModel {
	private Stage stage;
	private Spieler gegner;
	private Spieler selbst;
	private Spieler aktuellerSpieler;
	private DateiVerwaltung dateiverwaltung = new DateiVerwaltung("");
	private Satz aktuellerSatz;
	private Spielfeld spielfeld = new Spielfeld();
	
	public SpielModel(Stage stage){
		this.stage = stage;
	}
	
	public int chipEinwerfen(int spalte){
		int ergebnis = spielfeld.einfuegen(spalte,aktuellerSpieler);
		new Zug(ergebnis,spalte,aktuellerSpieler,aktuellerSatz);
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
//	public void setSpieler(int eigeneKennzeichnung){
//		selbst = new Spieler("blutwurst1",eigeneKennzeichnung);
//		switch(eigeneKennzeichnung){
//			case Spieler.SPIELER_O:
//				gegner = new Spieler("Gegner",Spieler.SPIELER_X);
//				break;
//			case Spieler.SPIELER_X:
//				gegner = new Spieler("Gegner",Spieler.SPIELER_O);
//				break;
//		}
//	}
	
	public void spielerRegistrieren(String eigenerName,String gegnerName,boolean heimBeginnt){
		selbst = new Spieler(0,eigenerName,'X');
		gegner = new Spieler(1,gegnerName,'O');
		if(heimBeginnt)
			aktuellerSpieler = selbst;
		else
			aktuellerSpieler = gegner;
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
	
//	Punktzahlen ausgeben
//	public int getEigeneSatzpunkte(){
//		return selbst.getSatzpunkte();
//	}
//	public int getGegnerSatzpunkte(){
//		return gegner.getSatzpunkte();
//	}
	
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
	
	public void speichern(){
		selbst.speichern();
		gegner.speichern();
	}
}