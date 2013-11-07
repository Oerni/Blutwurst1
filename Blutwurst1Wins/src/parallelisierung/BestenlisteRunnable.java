package parallelisierung;

import java.sql.ResultSet;
import java.sql.SQLException;

import spieldaten.HSQLConnection;
import spieldaten.Spieler;
import spieldaten.Strings;
import statistikdaten.Highscore;
import statistikdaten.StatistikModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BestenlisteRunnable implements Runnable {
	private StatistikModel model;
	
	public BestenlisteRunnable(StatistikModel model){
		this.model = model;
	}
	
	public void run(){
		ResultSet bestenlisteSQL = HSQLConnection.getInstance().executeQuery(Strings.HIGHSCORE);
		ObservableList<Highscore> bestenliste = FXCollections.observableArrayList();
		try{
			int rang = 1;
			while(bestenlisteSQL.next()){
				try{
					bestenliste.add(new Highscore(rang,new Spieler(bestenlisteSQL.getString("name")),bestenlisteSQL.getInt("anzahlsiege")));
					rang++;
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
