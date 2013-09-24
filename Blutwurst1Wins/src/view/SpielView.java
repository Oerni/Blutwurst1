package view;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SpielView {

	private Label satzstatus;
	private Label runde;
	private Label spiel;
	private Label satz;
	private Label punktestandHeim;
	private Label punktestandGast;
	private Label gesamtPunkte;
	

	
	//Methoden um Daten der Anzeige zu bekommen
	public Label getSatzstatus(){

		return satzstatus;
	}
	public Label getRunde(){
		return runde;
	}
	public Label getSpiel(){
		return spiel;
	}
	public Label getSatz(){
		return satz;
	}
	
	//Methoden um aktuellen Spielstand zu bekommen
	public Label getPunktestandHeim(){
		return punktestandHeim;
	}
	public Label getPunktestandGast(){
		return punktestandGast;
	}

	//Methode um die gesamten Punkte zu bekommen
	public Label getPunktestandGesamt(){
		return gesamtPunkte;
	}

	
}
