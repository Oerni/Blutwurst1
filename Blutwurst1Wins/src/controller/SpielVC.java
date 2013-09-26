package controller;

import java.io.IOException;

import view.SpielView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import model.Model;
import model.Spieler;
import model.Zug;


public class SpielVC {
	private Model model;
	private SpielView view;
	
//	public SpielVC(Model model){
//		this.model = model;
//		this.view = new SpielView();
//		
////		SpielerKennzeichnung setzen
//		
////		view.getEigenerNameLabel().setText(model.getEigenenNamen());
////		view.getGegnerNameLabel().setText(model.getGegnerNamen());
////		view.getEigenerPunktestandLabel().setText(""+model.getEigeneSatzpunkte());
////		view.getGegnerPunktestandLabel().setText(""+model.getGegnerSatzpunkte());
////		
////		view.getZuruecksetzenButton().setOnAction(new ZuruecksetzenBtnEventHandler());
//		
//		spielstart();
//	}
	public SpielVC(){
		this.view = new SpielView();
		this.model = new Model();
		
	}
	

//	@FXML
//	private Label runde;
//	
//	public SpielVC(Model model){
//		
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpielView.fxml"));
////		fxmlLoader.setController(this);
//		
//		try{
//			fxmlLoader.load();
//			System.out.println(runde.getText());
//		}catch(IOException ex){
//			ex.printStackTrace();
//		}
//	}

	public void spielstart() {
		
	}

//	public void show(){
//		view.show(model.getPrimaryStage());
//	}
	
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

}
