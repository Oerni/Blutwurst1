package model.statistik;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
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
	public ObservableList<Spiel> getSpieldaten(){
		ObservableList<Spiel> spieldaten = FXCollections.observableArrayList();
		ResultSet spieldatenSQL = HSQLConnection.getInstance().executeQuery(Queries.SPIELDATEN);
		try{
			while(spieldatenSQL.next()){
				spieldaten.add(new Spiel(spieldatenSQL.getInt("spielnr"),spieldatenSQL.getString("name"),spieldatenSQL.getInt("punkteheim"),spieldatenSQL.getInt("punktegegner")));
			}
			return spieldaten;
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
}