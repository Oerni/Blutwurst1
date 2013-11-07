package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import spieldaten.HSQLConnection;
import spieldaten.Satz;
import spieldaten.Spiel;
import spieldaten.Strings;

public class SpielDatenSaetzeRunnable implements Runnable{
	/**
	 * Runnable, um parallel Spieldaten (Sätze, Züge) zu laden
	 */
	
	private Spiel spiel;
	
	public SpielDatenSaetzeRunnable(Spiel spiel){
		this.spiel = spiel;
	}
	
	public void run(){
		ResultSet saetzeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.SAETZE_EINES_SPIELS,spiel.getID()));
		
		try{
			while(saetzeSQL.next()){
				try{
					Satz satz = new Satz(saetzeSQL.getInt("id"),spiel.getSpieler(saetzeSQL.getString("beginner")),spiel,spiel.getSpieler(saetzeSQL.getString("gewinner")));
					spiel.satzHinzufuegen(satz);
					ThreadExecutor.getInstance().execute(new SpielDatenZuegeRunnable(satz));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}catch(SQLException ex){
			
		}
	}
}
