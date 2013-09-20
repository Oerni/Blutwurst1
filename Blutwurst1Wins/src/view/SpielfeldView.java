package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpielfeldView {
	private Scene scene;
	
	private GridPane grid;
	private Text sceneTitle;
	
	private Label vornameLB;
	private TextField vornameTX;
	private Label nachnameLB;
	private TextField nachnameTX;
	
	private Text meldungT;
	
	private Button okBtn;
	private Button addBtn;
	private HBox hbBtn;
	
	public SpielfeldView(){
		// Layout
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//Ueberschrift
		sceneTitle = new Text("Hier seht ihr den Titel");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle,0,0,2,1);
		
		//Vorname
		vornameLB = new Label("Vorname: ");
		grid.add(vornameLB, 0, 1);
		
		vornameTX = new TextField();
		grid.add(vornameTX, 1, 1);
		
		//Nachname
		nachnameLB = new Label("Nachname: ");
		grid.add(nachnameLB, 0, 2);
		
		nachnameTX = new TextField();
		grid.add(nachnameTX, 1, 2);
		
		// Buttons
		addBtn = new Button("Eintragen");
		okBtn = new Button("Alle anzeigen");
		
		// ButtonGruppe
		hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_RIGHT);
		hbBtn.getChildren().add(addBtn);
		hbBtn.getChildren().add(okBtn);
		grid.add(hbBtn, 1, 4);
		
		// Meldung
		meldungT = new Text();
		grid.add(meldungT, 1, 6);
		
		scene = new Scene(grid, 300, 300);
	}
	
	public void show(Stage stage){
		stage.setTitle("Titel der Stage");
		stage.setScene(scene);
		stage.show();
	}
	
	public TextField getVornameTX(){
		return vornameTX;
	}
	public TextField getNachnameTX(){
		return nachnameTX;
	}
	public Text getMeldungT(){
		return meldungT;
	}
	public Button getOkButton(){
		return okBtn;
	}
	public Button getAddButton(){
		return addBtn;
	}
}
