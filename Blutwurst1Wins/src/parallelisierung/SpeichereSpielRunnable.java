package parallelisierung;

import java.sql.Statement;

import datenhaltung.HSQLConnection;
import datenhaltung.Spiel;

public class SpeichereSpielRunnable implements Runnable{
	private Spiel spiel;
	
	public SpeichereSpielRunnable(Spiel spiel){
		this.spiel = spiel;
	}
	
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format("spiel","gegner,punkteheim,punktegegner",spiel.getGegner().getID()+","+spiel.getPunkteHeim()+","+spiel.getPunkteGegner());
//		Semaphore Entry
		SemaphorManager.getInstance().schreibzugriffAnmelden();
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphore Exit
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}

}
