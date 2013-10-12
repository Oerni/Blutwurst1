package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import datenhaltung.StatistikModel;



public class SimulationViewController {
		
		private Scene scene;
		private StatistikModel model;
		@FXML
		private Label simulationSpielnummer, simulationEndergebnis, simulationZug;
		@FXML
		private Label simulationSatzstatus, simulationRunde, simulationSpiel, simulationSatz;
		@FXML
		private Label simulationSpielstandHeim, simulationSpielstandGast;
		@FXML
		private Button simulationBeendenButton;
		
		public SimulationViewController(StatistikModel sModel){
			this.model = sModel;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/simulationView.fxml"));
			fxmlLoader.setController(this);
			
			
			try{
				Pane pane = (Pane)fxmlLoader.load();
				scene = new Scene(pane);
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		
	}
	
	public void show(){
		Stage stage = model.getStage();
		stage.setScene(scene);
		stage.show();
	
	}
	
	//Simulation Beenden und Fenster schliessen
	@FXML
	public void simulationBeenden(){
		
	}
}
