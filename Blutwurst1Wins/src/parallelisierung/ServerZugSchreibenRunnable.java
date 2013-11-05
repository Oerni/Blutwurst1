package parallelisierung;

import spieldaten.DateiVerwaltung;
import spieldaten.SpielModel;
import spieldaten.Zug;

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
