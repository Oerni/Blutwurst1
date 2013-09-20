package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SpielView {
	private Group group;
	private Scene scene;
	
	//Spielfeld mit 7 Spalten und 6 Zeilen
	private Circle[][] spielfeld = new Circle[7][6];
	private Rectangle rahmen;
	
	Region information;
	
	//Angaben zum Spielfeld
	private int aussenabstand;
	private int innenabstand;
	private int radius;
	private int breite;
	private int hoehe;
	
	public SpielView(){
		GridPane grid = new GridPane();
		
		group = new Group();
		grid.add(group, 1, 1);
		scene = new Scene(grid,500,500,Color.BLUE);
		
		Rectangle r2 = new Rectangle();
		r2 = new Rectangle();
		r2.setFill(Color.RED);
		r2.setX(0);
		r2.setY(0);
		r2.setWidth(breite);
		r2.setHeight(hoehe);
		grid.add(r2, 2, 1);
		
		aussenabstand = 25;
		innenabstand = 10;
		radius = 25;
		breite = 7*radius+8*innenabstand;
		hoehe = 6*radius+7*innenabstand;
		
		rahmen = new Rectangle();
		rahmen.setFill(Color.BLUE);
		rahmen.setX(aussenabstand);
		rahmen.setY(aussenabstand);
		rahmen.setWidth(breite);
		rahmen.setHeight(hoehe);
		group.getChildren().add(rahmen);
		
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				spielfeld[i][j] = new Circle();
				spielfeld[i][j].setCenterX(rahmen.getX()+innenabstand+i*2*(radius+innenabstand/2));
				spielfeld[i][j].setCenterY(rahmen.getX()+innenabstand+j*2*(radius+innenabstand/2));
				spielfeld[i][j].setRadius(radius);
				spielfeld[i][j].setFill(Color.WHITE);
				group.getChildren().add(spielfeld[i][j]);
			}
		}
	}
	
	public void show(Stage stage){
		stage.setTitle("4 Gewinnt");
		stage.setScene(scene);
		stage.show();
	}
}
