package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.DateiVerwaltung;
import datenhaltung.SpielModel;
import datenhaltung.Zug;

public class ServerZugLesenCallable implements Callable<Zug> {
	private DateiVerwaltung dateiverwaltung;
	
	public ServerZugLesenCallable(SpielModel model){
		this.dateiverwaltung = model.getDateiVerwaltung();
	}
	
	public Zug call(){
		return dateiverwaltung.dateiLesen();
	}
}
