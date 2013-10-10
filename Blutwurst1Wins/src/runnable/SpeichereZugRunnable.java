package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Queries;

public class SpeichereZugRunnable implements Runnable {
	private int satzNr,spielNr,zeile,spalte;
	
	public SpeichereZugRunnable(int satzNr, int spielNr, int zeile, int spalte){
		this.satzNr = satzNr;
		this.spielNr = spielNr;
		this.zeile = zeile;
		this.spalte = spalte;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Queries.INSERT,"zug","satznr,spielnr,zeile,spalte",satzNr+","+spielNr+","+zeile+","+spalte);
//		Semaphor Entry
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphor Exit
	}

}
