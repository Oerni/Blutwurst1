package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Spieler;
import datenhaltung.Strings;

public class SpeichereSpielerCallable implements Callable<Number> {
	private Spieler spieler;
	public SpeichereSpielerCallable(Spieler spieler){
		this.spieler = spieler;
	}
	
	public Number call(){
		try{
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			int spielerNr = HSQLConnection.getInstance().insert(String.format(Strings.INSERT, "spieler", "name", "'"+spieler.getName()+"'"),"spieler");
			SemaphorManager.getInstance().schreibzugriffAbmelden();
			return spielerNr;
		}catch(Exception ex){
			return -1;
		}

	}
}