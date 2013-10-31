package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import datenhaltung.HSQLConnection;
import datenhaltung.Highscore;
import datenhaltung.Spieler;
import datenhaltung.StatistikModel;
import datenhaltung.Strings;

public class BestenlisteRunnable implements Runnable {
	private StatistikModel model;
	
	public BestenlisteRunnable(StatistikModel model){
		this.model = model;
	}
	
	public void run(){
		ResultSet bestenlisteSQL = HSQLConnection.getInstance().executeQuery(Strings.HIGHSCORE);
		ObservableList<Highscore> bestenliste = FXCollections.observableArrayList();
		try{
			while(bestenlisteSQL.next()){
				try{
					bestenliste.add(new Highscore(new Spieler(bestenlisteSQL.getString("name")),bestenlisteSQL.getInt("anzahlsiege")));
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			model.setBestenliste(bestenliste);
		}catch(SQLException ex){
			model.bestenlisteFertig();
			ex.printStackTrace();
		}
	}
}
