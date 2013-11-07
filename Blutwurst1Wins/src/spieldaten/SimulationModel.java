package spieldaten;

import javafx.stage.Stage;

public class SimulationModel {
	private Stage stage;
	private Spiel spiel;
	
	public SimulationModel(Stage stage,Spiel spiel){
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
