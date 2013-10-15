package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		if(satz.getSatzNr() == -1){
			SemaphorManager.getInstance().schreibzugriffAnmelden();
			HSQLConnection.getInstance().insert(String.format(Strings.INSERT,"satz","spielnr,beginner",satz.getSpiel().getSpielNr()+","+satz.spielerBegonnen()));
			SemaphorManager.getInstance().schreibzugriffAbmelden();
			SemaphorManager.getInstance().lesezugriffAnmelden();
			ResultSet satzNrSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.LETZTE_SATZ_NR,satz.getSatzNr()));
			SemaphorManager.getInstance().lesezugriffAbmelden();
			try{
				satzNrSQL.next();
				return satzNrSQL.getInt("satznr");
			}catch(SQLException ex){
				ex.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
}
