package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import datenhaltung.HSQLConnection;
import datenhaltung.Satz;
import datenhaltung.Strings;
import datenhaltung.Zug;

public class SpielDatenZuegeRunnable implements Runnable{
	/**
	 * Runnable, um parallel Spieldaten (Sätze, Züge) zu laden
	 */
	private Satz satz;
	
	public SpielDatenZuegeRunnable(Satz satz){
		this.satz = satz;
	}
	
	public void run(){
		ResultSet zuegeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.ZUEGE_EINES_SATZES,satz.getID(),satz.getSpiel().getID()));
		try{
			while(zuegeSQL.next()){
				try{
					Zug zug = new Zug(zuegeSQL.getInt("zeile"),zuegeSQL.getInt("spalte"),satz.getSpiel().getSpieler(zuegeSQL.getString("spieler")),satz);
					satz.zugEinfuegen(zug);
					System.out.println(zug);
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
