package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SpielView {
	private Group group;
	private Scene scene;
	private FlowPane spielflaechePanel;
	private GridPane informationPanel;
	private FlowPane statusPanel;
	private GridPane statusInnerPanel;
	private FlowPane punkteStandPanel;
	
	private Label satzStatusLabel;
	private Label satzStatusAktuell;
	private Label rundeLabel;
	private Label rundeAktuell;
	private Label spielLabel;
	private Label spielAktuell;
	private Label satzLabel;
	private Label satzAktuell;
	
	private Font standardFont;
	
	//Spielfeld mit 7 Spalten und 6 Zeilen
	private Circle[][] spielfeld = new Circle[7][6];
	private Rectangle rahmen;
	private Rectangle spielfeldRahmen;
	private Color spielfeldFarbe;
	private Color rahmenFarbe;
	
	Region information;
	
	//Angaben zum Spielfeld
	private int aussenabstand;
	private int innenabstand;
	private int radius;
	private int breite;
	private int hoehe;
	
	//Status Elemente
	private Circle eigeneFarbe;
	private Circle gegnerFarbe;
	private HBox eigenePunkte;
	private HBox gegnerPunkte;
	private Label eigenerNameLabel;
	private Label gegnerNameLabel;
	private Label eigenerPunktestandLabel;
	private Label gegnerPunktestandLabel;
	
	private Button zuruecksetzen;
	
	public SpielView(){		
		group = new Group();
		
		zuruecksetzen = new Button("Zurücksetzen");
		
		standardFont = Font.font("Comic Sans", FontWeight.NORMAL, 16);
		spielfeldFarbe = Color.rgb(126, 192, 238);
		rahmenFarbe = Color.rgb(58,95,205);
		
		spielflaechePanel = new FlowPane(Orientation.HORIZONTAL);
		spielflaechePanel.setColumnHalignment(HPos.LEFT);
		spielflaechePanel.setRowValignment(VPos.TOP);
		spielflaechePanel.setHgap(10);
		

		informationPanel = new GridPane();
		scene = new Scene(spielflaechePanel,800,500);
		spielflaechePanel.getChildren().add(group);
		spielflaechePanel.getChildren().add(informationPanel);
		
		statusPanel = new FlowPane(Orientation.VERTICAL);
		statusInnerPanel = new GridPane();
		statusInnerPanel.setStyle("-fx-background-color: rgb(126,192,238);");
		statusInnerPanel.setPrefWidth(170);
		statusInnerPanel.setHgap(13);
		statusInnerPanel.setPadding(new Insets(10, 0, 10, 0));
		
		satzStatusLabel = new Label("Satzstatus:");
		satzStatusLabel.setFont(standardFont);
		satzStatusAktuell = new Label("Spielen");
		satzStatusAktuell.setFont(standardFont);
		
		rundeLabel = new Label("Runde:");
		rundeLabel.setFont(standardFont);
		rundeAktuell = new Label("1");
		rundeAktuell.setFont(standardFont);
		
		spielLabel = new Label("Spiel:");
		spielLabel.setFont(standardFont);
		spielAktuell = new Label("3");
		spielAktuell.setFont(standardFont);
		
		satzLabel = new Label("Satz:");
		satzLabel.setFont(standardFont);
		satzAktuell = new Label("2");
		satzAktuell.setFont(standardFont);
		
		statusInnerPanel.add(satzStatusLabel, 1, 1);
		statusInnerPanel.add(satzStatusAktuell, 2, 1);
		statusInnerPanel.add(rundeLabel, 1, 2);
		statusInnerPanel.add(rundeAktuell, 2, 2);
		statusInnerPanel.add(spielLabel, 1, 3);
		statusInnerPanel.add(spielAktuell, 2, 3);
		statusInnerPanel.add(satzLabel, 1, 4);
		statusInnerPanel.add(satzAktuell, 2, 4);
		statusPanel.getChildren().add(statusInnerPanel);
		informationPanel.add(statusPanel,1,1);
		
		eigeneFarbe = new Circle();
		eigeneFarbe.setFill(Color.rgb(0,255,0));
		eigeneFarbe.setRadius(15);
		eigenePunkte = new HBox(10);
		eigenePunkte.setAlignment(Pos.CENTER_LEFT);
		eigenerPunktestandLabel = new Label("0");
		eigenerPunktestandLabel.setFont(standardFont);
		eigenerNameLabel = new Label("blutwurst1");
		eigenerNameLabel.setFont(standardFont);
		eigenePunkte.getChildren().addAll(eigeneFarbe,eigenerNameLabel,eigenerPunktestandLabel);
		punkteStandPanel = new FlowPane(Orientation.HORIZONTAL);
		punkteStandPanel.getChildren().add(eigenePunkte);
		informationPanel.add(punkteStandPanel,1,2);
		
		aussenabstand = 25;
		innenabstand = 10;
		radius = 25;
		breite = 7*2*radius+8*innenabstand;
		hoehe = 6*2*radius+7*innenabstand;
		
		rahmen = new Rectangle();
		rahmen.setFill(spielfeldFarbe);
		rahmen.setX(aussenabstand);
		rahmen.setY(aussenabstand);
		rahmen.setWidth(breite);
		rahmen.setHeight(hoehe);

		spielfeldRahmen = new Rectangle();
		spielfeldRahmen.setFill(rahmenFarbe);
		spielfeldRahmen.setX(aussenabstand-2);
		spielfeldRahmen.setY(aussenabstand-2);
		spielfeldRahmen.setWidth(rahmen.getWidth()+4);
		spielfeldRahmen.setHeight(rahmen.getHeight()+4);
		group.getChildren().add(spielfeldRahmen);
		group.getChildren().add(rahmen);
		
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				spielfeld[i][j] = new Circle();
				spielfeld[i][j].setCenterX(rahmen.getX()+radius+innenabstand+i*2*(radius+innenabstand/2));
				spielfeld[i][j].setCenterY(rahmen.getX()+radius+innenabstand+j*2*(radius+innenabstand/2));
				spielfeld[i][j].setRadius(radius);
				spielfeld[i][j].setFill(Color.WHITE);
				group.getChildren().add(spielfeld[i][j]);
			}
		}
	}
	
	public void show(Stage stage){
		stage.setTitle("4 Gewinnt - blutwurst1");
		stage.setScene(scene);
		stage.show();
	}
	
	public Label getEigenerPunktestandLabel(){
		return eigenerPunktestandLabel;
	}
	public Label getGegnerPunktestandLabel(){
		return gegnerPunktestandLabel;
	}
	public Button getZuruecksetzenButton(){
		return zuruecksetzen;
	}
	public Label getEigenerNameLabel(){
		return eigenerNameLabel;
	}
	public Label getGegnerNameLabel(){
		return gegnerNameLabel;
	}
}
