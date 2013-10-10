package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Spieler;
import model.spiel.Strings;

public class SpeichereSpielerRunnable implements Runnable{
	private Spieler spieler;
	
	public SpeichereSpielerRunnable(Spieler spieler){
		this.spieler = spieler;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Strings.INSERT,"spieler","name","'"+spieler.getName()+"'");
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
