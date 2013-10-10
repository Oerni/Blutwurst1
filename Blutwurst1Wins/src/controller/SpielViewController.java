package controller;

import java.io.IOException;

import datenhaltung.SpielModel;
import datenhaltung.Spieler;
import datenhaltung.StatistikModel;
import datenhaltung.Zug;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class SpielViewController {
	private SpielModel model;
	private Scene scene;
	
	private final Color eigeneFarbe = Color.web("0x33CC66");
	private final Color gegnerFarbe = Color.web("0xFF3333");
	
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
	
	private Circle feld[][] = new Circle[7][6];
	@FXML
	private Button aButton;
	@FXML
	private Button bButton;
	@FXML
	private Button cButton;
	@FXML
	private Button dButton;
	@FXML
	private Button eButton;
	@FXML
	private Button fButton;
	@FXML
	private Button gButton;
	
	@FXML
	private AnchorPane resetMenu;
	@FXML
	private ImageView spielfeldButtonOrange, resetButtonOrange;
	@FXML
	private ImageView spielfeldButton;
	
	
	public SpielViewController(SpielModel model){
		
		this.model = model;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SpielView.fxml"));
		fxmlLoader.setController(this);
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		feld[0][0] = a1;
		feld[1][0] = b1;
		feld[2][0] = c1;
		feld[3][0] = d1;
		feld[4][0] = e1;
		feld[5][0] = f1;
		feld[6][0] = g1;
		feld[0][1] = a2;
		feld[1][1] = b2;
		feld[2][1] = c2;
		feld[3][1] = d2;
		feld[4][1] = e2;
		feld[5][1] = f2;
		feld[6][1] = g2;
		feld[0][2] = a3;
		feld[1][2] = b3;
		feld[2][2] = c3;
		feld[3][2] = d3;
		feld[4][2] = e3;
		feld[5][2] = f3;
		feld[6][2] = g3;
		feld[0][3] = a4;
		feld[1][3] = b4;
		feld[2][3] = c4;
		feld[3][3] = d4;
		feld[4][3] = e4;
		feld[5][3] = f4;
		feld[6][3] = g4;
		feld[0][4] = a5;
		feld[1][4] = b5;
		feld[2][4] = c5;
		feld[3][4] = d5;
		feld[4][4] = e5;
		feld[5][4] = f5;
		feld[6][4] = g5;
		feld[0][5] = a6;
		feld[1][5] = b6;
		feld[2][5] = c6;
		feld[3][5] = d6;
		feld[4][5] = e6;
		feld[5][5] = f6;
		feld[6][5] = g6;
	}
	
	public void show(){
		Stage primaryStage = model.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println(runde.getText()+", "+satzstatus.getText());
	}
	@FXML
	public void spielStarten() {
		model.init();
	}
	
	public void zugDurchfuehren(){
		// Zug des Gegners interpretieren
		Zug gegnerzug = model.zugVonServer();
		Zug eigenerZug = null;
		
		// Pruefen auf Spezialfaelle
		if(!gegnerzug.getFreigabe() && gegnerzug.getSatzstatus().trim().equalsIgnoreCase("beendet")){
			//Spielfeld voll
			int eigeneKennzeichnung = model.getEigeneKennzeichnung();
			switch(eigeneKennzeichnung){
				case 0:
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

	
//	Druecken des Statistik anzeigen Buttons
	@FXML
	public void statistikAnzeigen(){
		StatistikModel sModel = new StatistikModel(new Stage());
		new StatistikViewController(sModel).show();
		
	}
	
	Color aktuelleFarbe;
	@FXML
	public void inAHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(0);
		faerben(0,ergebnis);
	}
	@FXML
	public void inBHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(1);
		faerben(1,ergebnis);
	}
	@FXML
	public void inCHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(2);
		faerben(2,ergebnis);
	}
	@FXML
	public void inDHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(3);
		faerben(3,ergebnis);
	}
	@FXML
	public void inEHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(4);
		faerben(4,ergebnis);
	}
	@FXML
	public void inFHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(5);
		faerben(5,ergebnis);
	}
	@FXML
	public void inGHinzufuegen(){
		if(model.getAktuellerSpieler().getKennzeichnung() == model.getEigeneKennzeichnung())
			aktuelleFarbe = eigeneFarbe;
		else
			aktuelleFarbe = gegnerFarbe;
		int ergebnis = model.zugDurchfuehren(6);
		faerben(6,ergebnis);
	}
	
	
	private void faerben(int spalte,int zeile){
		feld[spalte][zeile].setFill(aktuelleFarbe);
//		a1.setFill(aktuelleFarbe);
	}
	
	//Oeffnen des ResetMenus
	@FXML
	public void resetMenuAnzeigen(){
		resetButtonOrange.setVisible(true);
		spielfeldButton.setVisible(true);
		resetMenu.setVisible(true);
		
	}
	
	//Schliessen des ResetMenus
	@FXML
	public void resetMenuSchliessen(){
		resetButtonOrange.setVisible(false);
		spielfeldButtonOrange.setVisible(true);
		resetMenu.setVisible(false);
	}
	
	//	Satz zuruecksetzen
	@FXML
	public void resetSatz(){
		
		resetMenuSchliessen();
	}
	
	//Spiel zuruecksetzen
	@FXML
	public void resetSpiel(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				feld[i][j].setFill(Color.WHITE);
		model.zuruecksetzen();
		
	 resetMenuSchliessen();
	}
	
	//Runde zuruecksetzen
	@FXML
	public void resetRunde(){
		
		resetMenuSchliessen();
	}
	
	//Hilfe anzeigen
	@FXML
	public void hilfeAnzeigen(){
		
	}
	
	//Spielfeld anzeigen
	@FXML
	public void spielfeldAnzeigen(){
		
	}
	//Spielregeln anzeigen
	@FXML
	public void spielregelnAnzeigen(){
			
	}
	

	
		
		


	
	@FXML
	public void mouseOver(){
		scene.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void mouseExit(){
		scene.setCursor(Cursor.DEFAULT);
	}
}
