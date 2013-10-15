package parallelisierung;

import datenhaltung.DateiVerwaltung;
import datenhaltung.SpielModel;
import datenhaltung.Zug;

public class ServerZugSchreibenRunnable implements Runnable {
	private DateiVerwaltung dateiverwaltung;
	private Zug zug;
	
	public ServerZugSchreibenRunnable(SpielModel model){
		this.dateiverwaltung = model.getDateiVerwaltung();
	}
	
	public void setZug(Zug zug){
		this.zug = zug;
	}
	public void run(){
		dateiverwaltung.dateiSchreiben(this.zug);
	}
}
