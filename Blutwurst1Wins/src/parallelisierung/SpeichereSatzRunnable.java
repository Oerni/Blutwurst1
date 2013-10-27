package parallelisierung;

import java.sql.Statement;

import datenhaltung.HSQLConnection;
import datenhaltung.Satz;
import datenhaltung.Spieler;
import datenhaltung.Strings;

public class SpeichereSatzRunnable implements Runnable{
	private Satz satz;
	
	public SpeichereSatzRunnable(Satz satz){
		this.satz = satz;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
//		String insert = String.format(Strings.INSERT,"satz","spielnr,gewinner",satz.getSpiel().getID()+","+satz.getID());
//		Semaphor Entry
		SemaphorManager.getInstance().schreibzugriffAnmelden();
//		try{
//			statement.execute(insert);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		Semaphor Exit
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}

}
