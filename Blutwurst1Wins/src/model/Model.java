package model;

import javafx.stage.Stage;

public class Model {
	private Stage stage = new Stage();
	
	public Model(Stage stage){
		this.stage = stage;
	}
	
	public Stage getPrimaryStage(){
		return stage;
	}
}
