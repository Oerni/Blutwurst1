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
import datenhaltung.SimulationModel;
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
	private ImageView simulationButtonBlau, simulationButtonGrau, simulationButtonOrange;
	@FXML
	private PieChart gewinnVerlustKuchenDiagramm, startGewinnVerlustKuchenDiagramm;
	@FXML
	private BarChart anzahlGewinneVerlusteDiagramm;
	@FXML
	private TableView spielhistorieTable, highscoreTabelle;
	@FXML
	private TableColumn spielnummerSpalte,spielstandSpalte,gegnerSpalte,spielzuegeSpalte,siegerSpalte;
	@FXML
	private TableColumn spielernameSpalte, anzahlSiegeSpalte;
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
		spielnummerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("idString"));
		spielstandSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("spielstand"));
		gegnerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("gegnerName"));
		siegerSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("siegerName"));
		spielhistorieTable.setItems(spieldaten);
	}
	
	public void show(){
		Stage stage = model.getStage();
		stage.setScene(scene);
		stage.show();
	
	}
	
	@FXML
	public void simulationStarten(){
		int spielnr = 0;
//		SimulationModel sModel = new SimulationModel(new Stage(),spielnr,model.getSelbst(),model.getGegner());
//		new SimulationViewController(sModel).show();
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
				simulationButtonBlau.setVisible(false);
				highscoreTabelle.setVisible(false);
				
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
				simulationButtonBlau.setVisible(false);
				highscoreTabelle.setVisible(false);
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
				simulationButtonBlau.setVisible(false);
				highscoreTabelle.setVisible(false);
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
				simulationButtonBlau.setVisible(true);
				highscoreTabelle.setVisible(false);
				
			}
			
			@FXML
			public void highscoreAnzeigen(){
				spielhistorieButtonOrange.setVisible(false);
				startGewinnVerlustKuchenButton.setVisible(false);
				gewinnVerlustKuchenButtonOrange.setVisible(false);
				anzahlGewinnVerlustButtonOrange.setVisible(false);
				spielhistorieTable.setVisible(false);
				gewinnVerlustKuchenDiagramm.setVisible(false);
				startGewinnVerlustKuchenDiagramm.setVisible(false);
				anzahlGewinneVerlusteDiagramm.setVisible(false);
				infoStartGewinnVerlustKuchen.setVisible(false);
				infoSpielhistorie.setVisible(false);
				infoAnzahlGewinneVerluste.setVisible(false);
				infoGewinnVerlustKuchen.setVisible(false);
				simulationButtonBlau.setVisible(false);
				highscoreTabelle.setVisible(true);
			}
	
	
}