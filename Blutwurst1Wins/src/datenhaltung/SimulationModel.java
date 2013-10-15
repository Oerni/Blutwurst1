package datenhaltung;

import java.util.concurrent.Future;

import javafx.stage.Stage;
import parallelisierung.SpielCallable;
import parallelisierung.ThreadExecutor;

public class SimulationModel {
	private Stage stage;
	private Spiel spiel;
	private Spieler selbst;
	private Spieler gegner;
	
	public SimulationModel(Stage stage,int spielnr,Spieler selbst,Spieler gegner){
		this.stage = stage;
		Future<Spiel> spielFuture = ThreadExecutor.getInstance().getSpiel(new SpielCallable(spielnr));
		while(!spielFuture.isDone()){}
		try{
			this.spiel = spielFuture.get();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Spieler getSelbst(){
		return selbst;
	}
	public Spieler getGegner(){
		return gegner;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public Spiel getSpiel(){
		return spiel;
	}
}
