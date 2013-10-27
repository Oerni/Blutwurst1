package parallelisierung;

import java.util.concurrent.Callable;

import datenhaltung.HSQLConnection;
import datenhaltung.Satz;
import datenhaltung.Strings;

public class SpeichereSatzCallable implements Callable<Number> {
	private Satz satz;
	
	public SpeichereSatzCallable(Satz satz){
		this.satz = satz;
	}
	
	public Number call(){
		if(satz.getID() == -1){
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			int satzNr = HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"satz","spielnr,beginner",satz.getSpiel().getID()+",'"+satz.spielerBegonnen().getName()+"'"),String.format(Strings.LETZTE_SATZ_NR,satz.getSpiel().getID()));
			SemaphorManager.getInstance().schreibzugriffAbmelden();

			return satzNr;
		}
		return -1;
	}
}
