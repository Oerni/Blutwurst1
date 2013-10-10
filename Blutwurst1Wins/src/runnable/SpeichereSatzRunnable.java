package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Satz;
import model.spiel.Spieler;
import model.spiel.Strings;

public class SpeichereSatzRunnable implements Runnable{
	private int spielNr;
	private Spieler gewinner;
	
	public SpeichereSatzRunnable(Satz satz){
		this.spielNr = satz.getSpiel().getSpielNr();
		this.gewinner = satz.getGewinner();
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Strings.INSERT,"satz","spielnr,gewinner",spielNr+","+gewinner.getID());
//		Semaphor Entry
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphor Exit
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}

}
