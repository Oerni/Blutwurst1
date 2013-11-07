package parallelisierung;

import spieldaten.Spiel;
import spieldaten.SpielModel;
import controller.SpielViewController;

public class LabelAendernRunnable implements Runnable{
	private SpielViewController controller;
	private SpielModel model;
	
	public LabelAendernRunnable(SpielViewController controller,SpielModel model){
		this.controller = controller;
		this.model = model;
	}
	
	public void run(){
		Spiel spiel = model.getSpiel();
		if(spiel != null){
			controller.setGegnerPunkte(""+model.getSpiel().getPunkteGegner());
			controller.setHeimPunkte(""+model.getSpiel().getPunkteHeim());
			controller.setSpielNr(""+model.getSpiel().getID());
			controller.setSatz(""+model.getSpiel().getAnzahlSaetze());
			controller.setRunde(""+model.getRunde());
		}else{
			controller.setGegnerPunkte("");
			controller.setHeimPunkte("");
			controller.setSpielNr("");
			controller.setSatz("");
			controller.setRunde("");
		}
		controller.setSatzStatus(model.getSatzStatus());
		
		
	}
}
