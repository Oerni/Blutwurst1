package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.statistik.StatistikModel;

public class StatistikViewController {
	private Scene scene;
	private StatistikModel model;
	
	public StatistikViewController(StatistikModel sModel){
		this.model = sModel;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/statistikViewSGVKuchen.fxml"));
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
}