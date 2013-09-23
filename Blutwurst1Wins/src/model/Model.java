package model;

import java.util.Stack;

import javafx.stage.Stage;

public class Model {
	private Stage stage = new Stage();
	private HSQLConnection dbconnect = new HSQLConnection();
	private Spieler gegner = new Spieler("Gegner");
	private Spieler selbst = new Spieler("blutwurst1");
	private DateiVerwaltung dateiverwaltung = new DateiVerwaltung("");
	private String pfad;
	private Stack<Zug> spielverlauf = new Stack<Zug>();
	
	public Model(Stage stage){
		this.stage = stage;
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
		dateiverwaltung.dateiSchreiben(zug);
	}
	
	public void speichern(){
		
	}
}