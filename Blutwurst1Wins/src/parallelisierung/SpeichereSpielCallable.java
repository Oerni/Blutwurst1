package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Spiel;
import datenhaltung.Strings;

public class SpeichereSpielCallable implements Callable<Number>{
	private Spiel spiel;
	
	public SpeichereSpielCallable(Spiel spiel){
		this.spiel = spiel;
	}
	
	public Number call(){
		if(spiel.getID() == -1){
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			int spielNr = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"spiel","gegner,punkteheim,punktegegner","'"+spiel.getGegner().getName()+"',"+spiel.getPunkteHeim()+","+spiel.getPunkteGegner()),String.format(Strings.LETZTES_SPIEL_NR,"'"+spiel.getGegner().getName()+"'"));
			SemaphorManager.getInstance().schreibzugriffAbmelden();
			
			return spielNr;
		}
		return -1;
	}
}
