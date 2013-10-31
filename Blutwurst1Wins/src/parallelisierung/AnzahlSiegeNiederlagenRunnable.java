package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import datenhaltung.HSQLConnection;
import datenhaltung.SiegeNiederlagen;
import datenhaltung.StatistikModel;
import datenhaltung.Strings;

public class AnzahlSiegeNiederlagenRunnable implements Runnable{
	private StatistikModel model;
	public AnzahlSiegeNiederlagenRunnable(StatistikModel model){
		this.model = model;
	}
	public void run(){
		SemaphorManager.getInstance().lesezugriffAnmelden();
		ResultSet anzahlNiederlagenSQL = HSQLConnection.getInstance().executeQuery(Strings.ANZAHL_NIEDERLAGEN);
		ResultSet anzahlSiegeSQL = HSQLConnection.getInstance().executeQuery(Strings.ANZAHL_SIEGE);
		SemaphorManager.getInstance().lesezugriffAbmelden();
		
		try{
			anzahlNiederlagenSQL.next();
			anzahlSiegeSQL.next();
			int anzahlNiederlagen = anzahlNiederlagenSQL.getInt("anzahlniederlagen");
			int anzahlSiege = anzahlSiegeSQL.getInt("anzahlsiege");
			model.setSiegeNiederlagen(new SiegeNiederlagen(anzahlSiege,anzahlNiederlagen));
		}catch(SQLException ex){
			model.siegeNiederlagenFertig();
		}
	}
}
