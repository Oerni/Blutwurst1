package spieldaten;

import javafx.stage.Stage;

public class GespielteSpieleModel {
	private Stage stage;
	private Spiel spiel;
	
	public GespielteSpieleModel(Stage stage,Spiel spiel){
		this.stage = stage;
		this.spiel = spiel;
	}

	public Stage getStage(){
		return stage;
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
}
