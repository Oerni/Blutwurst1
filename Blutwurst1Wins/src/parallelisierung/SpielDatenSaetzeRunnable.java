package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import datenhaltung.HSQLConnection;
import datenhaltung.Satz;
import datenhaltung.Spiel;
import datenhaltung.Strings;

public class SpielDatenSaetzeRunnable implements Runnable{
	/**
	 * Runnable, um parallel Spieldaten (S�tze, Z�ge) zu laden
	 */
	
	private Spiel spiel;
	
	public SpielDatenSaetzeRunnable(Spiel spiel){
		this.spiel = spiel;
	}
	
	public void run(){
		SemaphorManager.getInstance().lesezugriffAnmelden();
		ResultSet saetzeSQL = HSQLConnection.getInstance().executeQuery(String.format(Strings.SAETZE_EINES_SPIELS,spiel.getID()));
		SemaphorManager.getInstance().lesezugriffAbmelden();
		
		try{
			while(saetzeSQL.next()){
				try{
					Satz satz = new Satz(saetzeSQL.getInt("id"),spiel.getSpieler(saetzeSQL.getString("beginner")),spiel,spiel.getSpieler(saetzeSQL.getString("gewinner")));
					spiel.satzHinzufuegen(satz);
					System.out.println(satz);
					ThreadExecutor.getInstance().execute(new SpielDatenZuegeRunnable(satz));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}catch(SQLException ex){
			
		}
	}
}