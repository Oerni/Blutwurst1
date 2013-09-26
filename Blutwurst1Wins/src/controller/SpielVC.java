package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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
	@FXML
	private Circle a1;
	@FXML
	private Circle a2;
	@FXML
	private Circle a3;
	@FXML
	private Circle a4;
	@FXML
	private Circle a5;
	@FXML
	private Circle a6;
	@FXML
	private Circle b1;
	@FXML
	private Circle b2;
	@FXML
	private Circle b3;
	@FXML
	private Circle b4;
	@FXML
	private Circle b5;
	@FXML
	private Circle b6;
	@FXML
	private Circle c1;
	@FXML
	private Circle c2;
	@FXML
	private Circle c3;
	@FXML
	private Circle c4;
	@FXML
	private Circle c5;
	@FXML
	private Circle c6;
	@FXML
	private Circle d1;
	@FXML
	private Circle d2;
	@FXML
	private Circle d3;
	@FXML
	private Circle d4;
	@FXML
	private Circle d5;
	@FXML
	private Circle d6;
	@FXML
	private Circle e1;
	@FXML
	private Circle e2;
	@FXML
	private Circle e3;
	@FXML
	private Circle e4;
	@FXML
	private Circle e5;
	@FXML
	private Circle e6;
	@FXML
	private Circle f1;
	@FXML
	private Circle f2;
	@FXML
	private Circle f3;
	@FXML
	private Circle f4;
	@FXML
	private Circle f5;
	@FXML
	private Circle f6;
	@FXML
	private Circle g1;
	@FXML
	private Circle g2;
	@FXML
	private Circle g3;
	@FXML
	private Circle g4;
	@FXML
	private Circle g5;
	@FXML
	private Circle g6;
	
	public SpielVC(Model model){
		this.model = model;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SpielView.fxml"));
		fxmlLoader.setController(this);
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
		}catch(IOException ex){
			ex.printStackTrace();
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
		
		// Prüfen auf Spezialfälle
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
	
//	Drücken des Spiel-Starten Buttons
	@FXML
	public void spielStarten(){
		System.out.println("Spiel gestartet");
	}
	
//	Drücken des Spiel zurücksetzen Buttons
	@FXML
	public void spielZuruecksetzen(){
		System.out.println("Spiel zurückgesetzt");
	}
	
//	Drücken des Statistik anzeigen Buttons
	@FXML
	public void statistikAnzeigen(){
		System.out.println("Statistik anzeigen");
	}
}
