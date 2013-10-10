package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Strings;
import model.spiel.Zug;

public class SpeichereZugRunnable implements Runnable {
	private Zug zug;

	public SpeichereZugRunnable(Zug zug){
		this.zug = zug;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Strings.INSERT,"zug","satznr,spielnr,zeile,spalte",zug.getSatz().getSatzNr()+","+zug.getSatz().getSpiel().getSpielNr()+","+zug.getZeile()+","+zug.getSpalte());
//		Semaphor Entry
		SemaphorManager.getInstance().schreibzugriffAbmelden();
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphor Exit
		SemaphorManager.getInstance().schreibzugriffAbmelden();
	}

}