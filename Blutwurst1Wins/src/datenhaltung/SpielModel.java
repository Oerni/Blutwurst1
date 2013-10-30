package datenhaltung;

import javafx.stage.Stage;
import logik.Feld;
import parallelisierung.SpeichernRunnable;
import parallelisierung.ThreadExecutor;

public class SpielModel {
	/**
	 * SpielModel. Zentraler Ansprechpartner des SpielViewController zur Vereinheitlichung
	 * der Kommunikation. ViewController hat keinen Einblick, was die Quelle der jeweiligen
	 * Daten ist.
	 */
	private Stage stage;
	private Spiel spiel;
	private DateiVerwaltung dateiverwaltung;
	private Feld feld = new Feld();
	
	public SpielModel(Stage stage){
		this.stage = stage;
	}
	
	public Feld getFeld(){
		return feld;
	}
	
	public void feldZuruecksetzen(){
		feld = new Feld();
	}
	
	public void init(String pfad,String gegnerName,char eigeneKennzeichnung){
		spiel = new Spiel();
		spiel.setSelbst(new Spieler(Strings.NAME,eigeneKennzeichnung));
		spiel.getSelbst().speichern();
		spiel.setGegner(new Spieler(gegnerName,getGegnerKennzeichnung(eigeneKennzeichnung)));
		spiel.getGegner().speichern();
		dateiverwaltung.setPfad(pfad);
		Satz ersterSatz = new Satz(spiel);
		ThreadExecutor.getInstance().execute(new SpeichernRunnable(ersterSatz));
		spiel.satzHinzufuegen(ersterSatz);
		ThreadExecutor.getInstance().execute(new SpeichernRunnable(spiel));
	}
	
	public void initDateiverwaltung(){
		dateiverwaltung = new DateiVerwaltung(this);
	}
	
	private char getGegnerKennzeichnung(char eigeneKennzeichnung){
		return eigeneKennzeichnung == 'x' ? 'o' : 'x';
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
	
	public Stage getPrimaryStage(){
		return stage;
	}
	
	public Zug zugVonServer(){
		Zug zug = dateiverwaltung.dateiLesen();
		zug.setSpieler(spiel.getGegner());
		return zug;
	}
	public void zugAnServer(Zug zug){
		zug.setSpieler(spiel.getSelbst());
		dateiverwaltung.dateiSchreiben(zug);
	}
	
	public void allesSpeichern(){

	}
	
	public DateiVerwaltung getDateiVerwaltung(){
		return dateiverwaltung;
	}
}