package datenhaltung;

import java.util.Stack;

import parallelisierung.ThreadExecutor;
import javafx.stage.Stage;

public class SimulationModel {
	private Stage stage;
	private Spiel spiel;
	private Stack<Satz> saetze = new Stack<Satz>();
	private Stack<Zug> zuege = new Stack<Zug>();
	
	public SimulationModel(Stage stage,int spielnr){
		this.stage = stage;
		ThreadExecutor.getInstance();
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
}
