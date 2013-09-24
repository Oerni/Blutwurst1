package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import model.Spieler;
import model.Zug;
import view.SpielView;

public class SpielVC {
	private Model model;
	private SpielView view;
	
	public SpielVC(Model model){
		this.model = model;
		this.view = new SpielView();
		
//		SpielerKennzeichnung setzen
		
//		view.getEigenerNameLabel().setText(model.getEigenenNamen());
//		view.getGegnerNameLabel().setText(model.getGegnerNamen());
//		view.getEigenerPunktestandLabel().setText(""+model.getEigeneSatzpunkte());
//		view.getGegnerPunktestandLabel().setText(""+model.getGegnerSatzpunkte());
//		
		view.getZuruecksetzenButton().setOnAction(new ZuruecksetzenBtnEventHandler());
		
		spielstart();
	}
	
	public void spielstart() {
		
	}

	public void show(){
		view.show(model.getPrimaryStage());
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
		
		// berechneten Zug an Server übergeben
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

	class ZuruecksetzenBtnEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
		}
	}
}
