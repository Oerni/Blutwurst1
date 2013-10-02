package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.statistik.StatistikModel;

public class StatistikViewController {
	private Scene scene;
	private StatistikModel model;
	@FXML
	private ImageView spielfeldButtonOrange, resetButtonOrange, spielhistorieButtonOrange, startGewinnVerlustKuchenButtonOrange, gewinnVerlustKuchenButtonOrange;
	@FXML
	private ImageView spielfeldButton, anzahlGewinnVerlustButtonOrange;
	@FXML
	private PieChart gewinnVerlustKuchenDiagramm, startGewinnVerlustKuchenDiagramm;
	@FXML
	private BarChart anzahlGewinneVerlusteDiagramm;
	@FXML
	private TableView spielhistorieTable;
	
	
	
	public StatistikViewController(StatistikModel sModel){
		this.model = sModel;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/statistikViewSGVKuchen.fxml"));
		fxmlLoader.setController(this);
		
		
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
			gewinnVerlustKuchenAnzeigen();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void show(){
		Stage stage = model.getStage();
		stage.setScene(scene);
		stage.show();
	
	}
	
	public void hilfeAnzeigen(){
		
	}
	
	@FXML
	public void mouseOver(){
		scene.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void mouseExit(){
		scene.setCursor(Cursor.DEFAULT);
	}
	
	//Spielfeld anzeigen
	@FXML
	public void spielfeldAnzeigen(){
		
	}
	//Spielregeln anzeigen
	@FXML
	public void spielregelnAnzeigen(){
			
	}
	
	//Gewinn Verlust Kuchen anzeigen
			@FXML
			public void gewinnVerlustKuchenAnzeigen(){
				spielhistorieTable.setVisible(false);
				spielhistorieButtonOrange.setVisible(false);
				startGewinnVerlustKuchenButtonOrange.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(true);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				gewinnVerlustKuchenDiagramm.setVisible(true);
				
			}
			
			
			//Start Gewinn Verlust Kuchen anzeigen
			@FXML
			public void startGewinnVerlustKuchenAnzeigen(){
				startGewinnVerlustKuchenButtonOrange.setVisible(true);
				spielhistorieButtonOrange.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(true);
				spielhistorieTable.setVisible(false);
			}
			
			//Anzahl Gewinne Verluste anzeigen
			@FXML
			public void anzahlGewinneVerlusteAnzeigen(){
				spielhistorieButtonOrange.setVisible(false);
				startGewinnVerlustKuchenButtonOrange.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(true);
				anzahlGewinneVerlusteDiagramm.setVisible(true);
				spielhistorieTable.setVisible(false);
			}
				
			//Spielhistorie anzeigen
			@FXML
			public void spielhistorieAnzeigen(){
				spielhistorieButtonOrange.setVisible(true);
				startGewinnVerlustKuchenButtonOrange.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				spielhistorieTable.setVisible(true);
			}
	
	
}