package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import model.Spieler;
import model.Zug;


public class SpielVC {
	private Model model;
//	private SpielView view;
	private Scene scene;
	
	@FXML
	private Label satzstatus;
	@FXML
	private Label runde;
	@FXML
	private Label spiel;
	@FXML
	private Label satz;
	@FXML
	private Label spielstandHeim;
	@FXML
	private Label spielstandGast;
	@FXML
	private Label gesamt;
	
	public SpielVC(Model model){
		this.model = model;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SpielView.fxml"));
		fxmlLoader.setController(this);
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
		}catch(IOException ex){
			
		}
	}
	
	public void show(){
		Stage primaryStage = model.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println(runde.getText()+", "+satzstatus.getText());
	}
	public void spielstart() {
		
	}
	
	public void zugDurchfuehren(){
		// Zug des Gegners interpretieren
		Zug gegnerzug = model.zugVonServer();
		Zug eigenerZug = null;
		
		// Pr�fen auf Spezialf�lle
		if(!gegnerzug.getFreigabe() && gegnerzug.getSatzstatus().trim().equalsIgnoreCase("beendet")){
			//Spielfeld voll
			int eigeneKennzeichnung = model.getEigeneKennzeichnung();
			switch(eigeneKennzeichnung){
				case Spieler.SPIELER_O:
					//als Gewinner eingetragen?
					if(gegnerzug.getSieger().equalsIgnoreCase("Spieler O")){
						if(gegnerzug.getGegnerzug() == -1)
//							Spiel gewonnen
							reagiereAufGewinnSituation();
						else
//							Spielfeld voll
							reagiereAufVollesSpielfeld();
					}else{
						if(gegnerzug.getGegnerzug() != -1)
//							Spiel verloren
							reagiereAufVerlustSituation();
						else
//							Spielfeld voll
							reagiereAufVollesSpielfeld();
					}
			}
		}
		
		// Zug berechnen
		berechneEigenenZug();
		
		// berechneten Zug an Server uebergeben
		model.zugAnServer(eigenerZug);
	}
	
	private void berechneEigenenZug() {
		
	}

	private void reagiereAufVerlustSituation() {
		
	}

	private void reagiereAufVollesSpielfeld() {
		
	}

	private void reagiereAufGewinnSituation() {

	}
	
//	Dr�cken des Spiel-Starten Buttons
	@FXML
	public void spielStarten(){
		System.out.println("Spiel gestartet");
	}
	
//	Dr�cken des Spiel zur�cksetzen Buttons
	@FXML
	public void spielZuruecksetzen(){
		System.out.println("Spiel zur�ckgesetzt");
	}
	
//	Dr�cken des Statistik anzeigen Buttons
	@FXML
	public void statistikAnzeigen(){
		System.out.println("Statistik anzeigen");
	}
}
