package parallelisierung;

import datenhaltung.SpielModel;

public class PfadSchreibenRunnable implements Runnable{
	private String pfad;
	private SpielModel model;
	
	public PfadSchreibenRunnable(String pfad,SpielModel model){
		this.pfad = pfad;
		this.model = model;
	}
	
	public void run(){
		model.getDateiVerwaltung().pfadSchreiben(pfad);
	}
}
