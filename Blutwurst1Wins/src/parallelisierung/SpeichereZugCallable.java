package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Strings;
import datenhaltung.Zug;

public class SpeichereZugCallable implements Callable<Number>{
	private Zug zug;
	
	public SpeichereZugCallable(Zug zug){
		this.zug = zug;
	}
	
	public Number call(){
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		int zugNr = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"zug","satznr,spielnr,spalte,zeile,spieler",zug.getSatz().getID()+","+zug.getSatz().getSpiel().getID()+","+zug.getSpalte()+","+zug.getZeile()+","+zug.getSpieler().getName()),String.format(Strings.LETZTER_ZUG_NR,zug.getSatz().getID(),zug.getSatz().getSpiel().getID()));
		SemaphorManager.getInstance().schreibzugriffAbmelden();
		
		return zugNr;
	}
}
