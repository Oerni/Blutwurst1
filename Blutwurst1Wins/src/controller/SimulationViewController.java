package controller;

import java.util.concurrent.Future;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import parallelisierung.SpielCallable;
import parallelisierung.ThreadExecutor;
import datenhaltung.SimulationModel;
import datenhaltung.Spiel;



public class SimulationViewController {
		
		private Scene scene;
		private SimulationModel model;
		@FXML
		private Label simulationSpielnummer, simulationEndergebnis, simulationZug;
		@FXML
		private Label simulationSatzstatus, simulationRunde, simulationSpiel, simulationSatz;
		@FXML
		private Label simulationSpielstandHeim, simulationSpielstandGast;
		@FXML
		private Button simulationBeendenButton;
		private int spielnr;
		
		public SimulationViewController(SimulationModel sModel){
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
		simulationStarten();
	}
	
	public void simulationStarten(){
		Future<Spiel> spielFuture = ThreadExecutor.getInstance().getSpiel(new SpielCallable(spielnr));
		while(!spielFuture.isDone()){}
		try{
			Spiel spiel = spielFuture.get();
			
		}catch(Exception ex){
			
		}
	}
	
	//Simulation Beenden und Fenster schliessen
	@FXML
	public void simulationBeenden(){
		
	}
}
