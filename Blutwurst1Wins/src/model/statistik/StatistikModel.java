package model.statistik;

import javafx.stage.Stage;

public class StatistikModel {
	private Stage stage;
	
	public StatistikModel(Stage stage){
		this.stage = stage;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public int getAnzahlGewinne(){
		return 5;
	}
	public int getAnzahlVerluste(){
		return 3;
	}
}