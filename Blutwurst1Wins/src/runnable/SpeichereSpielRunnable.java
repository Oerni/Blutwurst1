package runnable;

import java.sql.Statement;

import model.spiel.HSQLConnection;

public class SpeichereSpielRunnable implements Runnable{
	private int gegner,punkteHeim,punkteGegner;
	
	public SpeichereSpielRunnable(int gegner,int punkteHeim,int punkteGegner){
		this.gegner = gegner;
		this.punkteHeim = punkteHeim;
		this.punkteGegner = punkteGegner;
	}
	
	@Override
	public void run() {
		Statement statement = HSQLConnection.getInstance().getStatement();
		String insert = String.format("spiel","gegner,punkteheim,punktegegner",gegner+","+punkteHeim+","+punkteGegner);
//		Semaphore Entry
		try{
			statement.execute(insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		Semaphore Exit
	}

}
