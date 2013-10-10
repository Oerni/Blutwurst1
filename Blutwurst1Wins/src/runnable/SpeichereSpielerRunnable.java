package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;
import model.spiel.Queries;

public class SpeichereSpielerRunnable implements Runnable{
	private String name;
	
	public SpeichereSpielerRunnable(String name){
		this.name = name;
	}
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format(Queries.INSERT,"spieler","name","'"+name+"'");
//		Semaphor Entry
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphor Exit
	}

}
