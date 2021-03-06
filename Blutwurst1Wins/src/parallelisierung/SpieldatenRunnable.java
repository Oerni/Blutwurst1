package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import spieldaten.HSQLConnection;
import spieldaten.Spiel;
import spieldaten.Spieler;
import spieldaten.Strings;
import statistikdaten.StatistikModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpieldatenRunnable implements Runnable{
	private StatistikModel model;
	
	public SpieldatenRunnable(StatistikModel model){
		this.model = model;
	}
	
	public void run(){
		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Strings.SPIELDATEN);
		ObservableList<Spiel> spiele = FXCollections.observableArrayList();
		try{
			while(spieldatenSQL.next()){
				Spiel spiel = new Spiel(spieldatenSQL.getInt("id"),new Spieler(spieldatenSQL.getString("gegner")),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punkteGegner"));
				spiele.add(spiel);
				ThreadExecutor.getInstance().execute(new SpielDatenSaetzeRunnable(spiel));
			}
			model.setSpieldaten(spiele);
		}catch(SQLException ex){
			model.spieleFertig();
			ex.printStackTrace();
		}
	}
}
