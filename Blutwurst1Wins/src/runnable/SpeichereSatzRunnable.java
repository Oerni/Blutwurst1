package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Queries;

public class SpeichereSatzRunnable implements Runnable{
	private int spielNr,gewinner;
	
	public SpeichereSatzRunnable(int spielNr,int gewinner){
		this.spielNr = spielNr;
		this.gewinner = gewinner;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Queries.INSERT,"satz","spielnr,gewinner",spielNr+","+gewinner);
//		Semaphor Entry
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphor Exit
	}

}
