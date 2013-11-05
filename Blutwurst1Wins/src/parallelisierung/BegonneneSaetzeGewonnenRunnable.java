package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import spieldaten.HSQLConnection;
import spieldaten.Strings;
import statistikdaten.BegonneneSaetzeGewonnen;
import statistikdaten.StatistikModel;

public class BegonneneSaetzeGewonnenRunnable implements Runnable {
	private StatistikModel model;
	
	public BegonneneSaetzeGewonnenRunnable(StatistikModel model){
		this.model = model;
	}
	
	public void run(){
		ResultSet begonnenUndGewonnenSQL = HSQLConnection.getInstance().executeQuery(Strings.ANZAHL_BGONNENER_GEWONNENER_SAETZE);
		ResultSet alleSaetzeSQL = HSQLConnection.getInstance().executeQuery(Strings.ALLE_SAETZE);
		try{
			begonnenUndGewonnenSQL.next();
			int begonnenUndGewonnenAnzahl = begonnenUndGewonnenSQL.getInt("anzahlsiege");
			alleSaetzeSQL.next();
			int alleSaetze = alleSaetzeSQL.getInt("anzahlsaetze");
			model.setBegonneneSaetzeGewonnen(new BegonneneSaetzeGewonnen(begonnenUndGewonnenAnzahl,alleSaetze));
		}catch(SQLException ex){
			model.begonnenUndGewonnenFertig();
			ex.printStackTrace();
		}
	}
}
