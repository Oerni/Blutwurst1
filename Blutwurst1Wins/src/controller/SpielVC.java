package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Model;
import model.Zug;
import view.SpielView;

public class SpielVC {
	private Model model;
	private SpielView view;
	
	public SpielVC(Model model){
		this.model = model;
		this.view = new SpielView();
		
//		view.getEigenerNameLabel().setText(model.getEigenenNamen());
//		view.getGegnerNameLabel().setText(model.getGegnerNamen());
//		view.getEigenerPunktestandLabel().setText(""+model.getEigeneSatzpunkte());
//		view.getGegnerPunktestandLabel().setText(""+model.getGegnerSatzpunkte());
//		
		view.getZuruecksetzenButton().setOnAction(new ZuruecksetzenBtnEventHandler());
	}
	
	public void show(){
		view.show(model.getPrimaryStage());
	}
	
	public void zugDurchfuehren(){
		// Zug des Gegners interpretieren
		Zug gegnerzug = model.zugVonServer();
		Zug eigenerZug = null;
		
		// Zug berechnen
		
		// berechneten Zug an Server übergeben
		model.zugAnServer(eigenerZug);
	}
	
	class ZuruecksetzenBtnEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
		}
	}
}
