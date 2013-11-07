package parallelisierung;

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
		controller.setGegnerPunkte(""+model.getSpiel().getPunkteGegner());
		controller.setHeimPunkte(""+model.getSpiel().getPunkteHeim());
		controller.setSatzStatus(model.getSatzStatus());
		controller.setRunde(""+model.getRunde());
		controller.setSpielNr(""+model.getSpiel().getID());
		controller.setSatz(""+model.getSpiel().getAnzahlSaetze());
	}
}
