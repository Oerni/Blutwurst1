package datenhaltung;

import javafx.stage.Stage;
import logik.Feld;

public class SpielModel {
	/**
	 * SpielModel. Zentraler Ansprechpartner des SpielViewController zur Vereinheitlichung
	 * der Kommunikation. ViewController hat keinen Einblick, was die Quelle der jeweiligen
	 * Daten ist.
	 */
	private Stage stage;
	private Spiel spiel;
	private Spieler gegner;
	private Spieler selbst;
	private Spieler aktuellerSpieler;
	private DateiVerwaltung dateiverwaltung;
	private Feld feld = new Feld();
	
	public SpielModel(Stage stage){
		this.stage = stage;
	}
	
	public Feld getFeld(){
		return feld;
	}
	
	public Spieler getSelbst(){
		return selbst;
	}
	
	public Spieler getGegner(){
		return gegner;
	}
	
	public Spieler getSieger(char kennzeichnung){
		return selbst.getKennzeichnung() == kennzeichnung ? selbst : gegner;
	}
	
	public void init(String pfad,String gegnerName,char eigeneKennzeichnung){
		selbst = new Spieler(Strings.NAME,eigeneKennzeichnung);
		selbst.speichern();
		gegner = new Spieler(gegnerName,getGegnerKennzeichnung(eigeneKennzeichnung));
		gegner.speichern();
		aktuellerSpieler = getBeginnendenSpieler();
		dateiverwaltung.setPfad(pfad);
		spiel = new Spiel(gegner,selbst);
		spiel.speichern();
		Satz ersterSatz = new Satz(aktuellerSpieler,spiel);
		ersterSatz.speichern();
		spiel.satzHinzufuegen(ersterSatz);
	}
	
	public void initDateiverwaltung(){
		dateiverwaltung = new DateiVerwaltung(this);
	}
	
	private char getGegnerKennzeichnung(char eigeneKennzeichnung){
		return eigeneKennzeichnung == 'x' ? 'o' : 'x';
	}
	
	private Spieler getBeginnendenSpieler(){
		return selbst.getKennzeichnung()=='O' ? selbst : gegner;
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
	
//	public int zugDurchfuehren(int spalte){
//		int ergebnis = spielfeld.einfuegen(spalte,aktuellerSpieler);
//		zuege.push(new Zug(ergebnis,spalte,aktuellerSpieler,saetze.lastElement()));
//		if(aktuellerSpieler == gegner)
//			aktuellerSpieler = selbst;
//		else
//			aktuellerSpieler = gegner;
//		return ergebnis;
//	}
	
	public void spielerWechsel(){
		if(aktuellerSpieler == selbst)
			aktuellerSpieler = gegner;
		else
			aktuellerSpieler = selbst;
	}
	
	public Spieler getAktuellerSpieler(){
		return aktuellerSpieler;
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
		dateiverwaltung.dateiSchreiben(zug);
	}
	
	public void allesSpeichern(){

	}
	
	public DateiVerwaltung getDateiVerwaltung(){
		return dateiverwaltung;
	}
}