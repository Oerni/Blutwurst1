package parallelisierung;

import spieldaten.SpielModel;
import javafx.scene.control.Label;

public class LabelAendernRunnable implements Runnable{
	private Label gegnerPunkte;
	private SpielModel model;
	
	public LabelAendernRunnable(Label gegnerPunkte,SpielModel model){
		this.gegnerPunkte = gegnerPunkte;
		this.model = model;
	}
	
	public void run(){
		gegnerPunkte.setText(""+model.getSpiel().getPunkteGegner());
	}
}
