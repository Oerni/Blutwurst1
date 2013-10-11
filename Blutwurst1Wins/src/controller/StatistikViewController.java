package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import datenhaltung.Spiel;
import datenhaltung.StatistikModel;

public class StatistikViewController {
	private Scene scene;
	private StatistikModel model;
	@FXML
	private ImageView spielfeldButtonOrange, resetButtonOrange, spielhistorieButtonOrange, startGewinnVerlustKuchenButtonOrange, gewinnVerlustKuchenButtonOrange;
	@FXML
	private ImageView spielfeldButton, anzahlGewinnVerlustButtonOrange, startGewinnVerlustKuchenButton;
	@FXML
	private PieChart gewinnVerlustKuchenDiagramm, startGewinnVerlustKuchenDiagramm;
	@FXML
	private BarChart anzahlGewinneVerlusteDiagramm;
	@FXML
	private TableView spielhistorieTable;
	@FXML
	private TableColumn spielnummerSpalte,spielstandSpalte,gegnerSpalte,spielzuegeSpalte,siegerSpalte;
	@FXML
	private Text infoSpielhistorie, infoStartGewinnVerlustKuchen, infoAnzahlGewinneVerluste, infoGewinnVerlustKuchen;
	
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
		
//		CategoryAxis xAchse = new CategoryAxis();
//		NumberAxis yAchse = new NumberAxis();
		anzahlGewinneVerlusteDiagramm.setTitle("Anzahl Gewinne/Verluste");
		
		int anzahlSiege = model.getAnzahlSiege();
		int anzahlNiederlagen = model.getAnzahlNiederlagen();
		ObservableList<Spiel> spieldaten = model.getSpieldaten();
		
		XYChart.Series<String,Number> ergebnisse = new XYChart.Series<String,Number>();
		ergebnisse.setName("Anzahl Spiele");
		ergebnisse.getData().add(new XYChart.Data<String,Number>("Siege",anzahlSiege));
		ergebnisse.getData().add(new XYChart.Data<String,Number>("Niederlagen",anzahlNiederlagen));
		
		anzahlGewinneVerlusteDiagramm.getData().add(ergebnisse);
		
//		Gewinn-Verlust-Kuchen Diagramm
		ObservableList<PieChart.Data> gewinnVerlustData = FXCollections.observableArrayList(
				new PieChart.Data("Gewonnen", anzahlSiege),
				new PieChart.Data("Verloren", anzahlNiederlagen));
		gewinnVerlustKuchenDiagramm.setData(gewinnVerlustData);
		
		spielhistorieTable.setEditable(false);
		spielnummerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("spielNr"));
		spielstandSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("spielstand"));
		gegnerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("gegnerName"));
		siegerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("gewinnerName"));
		spielhistorieTable.setItems(spieldaten);
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
				startGewinnVerlustKuchenButton.setVisible(true);
				gewinnVerlustKuchenButtonOrange.setVisible(true);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				gewinnVerlustKuchenDiagramm.setVisible(true);
				anzahlGewinneVerlusteDiagramm.setVisible(false);
				spielhistorieTable.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(false);
				infoStartGewinnVerlustKuchen.setVisible(false);
				infoSpielhistorie.setVisible(false);
				infoAnzahlGewinneVerluste.setVisible(false);
				infoGewinnVerlustKuchen.setVisible(true);
				
			}
			
			
			//Start Gewinn Verlust Kuchen anzeigen
			@FXML
			public void startGewinnVerlustKuchenAnzeigen(){
				startGewinnVerlustKuchenButton.setVisible(false);
				startGewinnVerlustKuchenButtonOrange.setVisible(true);
				spielhistorieButtonOrange.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(true);
				spielhistorieTable.setVisible(false);
				anzahlGewinneVerlusteDiagramm.setVisible(false);
				gewinnVerlustKuchenDiagramm.setVisible(false);
				infoStartGewinnVerlustKuchen.setVisible(true);
				infoSpielhistorie.setVisible(false);
				infoAnzahlGewinneVerluste.setVisible(false);
				infoGewinnVerlustKuchen.setVisible(false);
			}
			
			//Anzahl Gewinne Verluste anzeigen
			@FXML
			public void anzahlGewinneVerlusteAnzeigen(){
				spielhistorieButtonOrange.setVisible(false);
				startGewinnVerlustKuchenButton.setVisible(true);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(true);
				anzahlGewinneVerlusteDiagramm.setVisible(true);
				spielhistorieTable.setVisible(false);
				gewinnVerlustKuchenDiagramm.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(false);
				infoStartGewinnVerlustKuchen.setVisible(false);
				infoSpielhistorie.setVisible(false);
				infoAnzahlGewinneVerluste.setVisible(true);
				infoGewinnVerlustKuchen.setVisible(false);
			}
				
			//Spielhistorie anzeigen
			@FXML
			public void spielhistorieAnzeigen(){
				spielhistorieButtonOrange.setVisible(true);
				startGewinnVerlustKuchenButton.setVisible(true);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				spielhistorieTable.setVisible(true);
				gewinnVerlustKuchenDiagramm.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(false);
				anzahlGewinneVerlusteDiagramm.setVisible(false);
				infoStartGewinnVerlustKuchen.setVisible(false);
				infoSpielhistorie.setVisible(true);
				infoAnzahlGewinneVerluste.setVisible(false);
				infoGewinnVerlustKuchen.setVisible(false);
			}
	
	
}