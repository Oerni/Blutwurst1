package model.statistik;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.spiel.HSQLConnection;
import model.spiel.Queries;
import model.spiel.Spiel;

public class StatistikModel {
	private Stage stage;
	
	public StatistikModel(Stage stage){
		this.stage = stage;
	}
	
	public Stage getStage(){
		return stage;
	}
//	Rückgabe der Anzahl der erspielten Siege
	public int getAnzahlSiege(){
		ResultSet anzahlSiegeSQL = HSQLConnection.getInstance().executeQuery(Queries.ANZAHL_SIEGE);
		try{
			anzahlSiegeSQL.next();
			return anzahlSiegeSQL.getInt("anzahlsiege");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return -1;
	}
	
//	Rückgabe der Anzahl der erspielten Niederlagen
	public int getAnzahlNiederlagen(){
		ResultSet anzahlSiegeSQL = HSQLConnection.getInstance().executeQuery(Queries.ANZAHL_NIEDERLAGEN);
		try{
			anzahlSiegeSQL.next();
			return anzahlSiegeSQL.getInt("anzahlniederlagen");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return -1;
	}
	
//	Spieldaten zur Anzeige aller gespielten Spiele
	public ObservableList<Spiel> getSpieldaten(){
		return null;
//		ObservableList<Spiel> spieldaten = FXCollections.observableArrayList();
//		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Queries.SPIELDATEN);
//		try{
//			while(spieldatenSQL.next()){
//				spieldaten.add(new Spiel(spieldatenSQL.getInt("spielnr"),spieldatenSQL.getString("name"),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punktegegner")));
//			}
//			return spieldaten;
//		}catch(SQLException ex){
//			ex.printStackTrace();
//			return null;
//		}
	}
}