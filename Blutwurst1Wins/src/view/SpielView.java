package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SpielView {

	@FXML 
    private Label satzstatus;
	private Label runde;
	private Label spiel;
	private Label satz;
	private Label spielstandHeim;
	private Label spielstandGast;
	private Label gesamt;
	
	public SpielView(){
		
	}
	
	public void show(Stage stage){
		try{
			stage.setTitle("4 Gewinnt");
			Parent root = FXMLLoader.load(getClass().getResource("SpielView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//Methoden um Daten der FXML auszulesen	
		public Label getSatzstatus(){
			return   satzstatus;
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
		
		public Label getSpielstandHeim() {
			return spielstandHeim;
		}
		
		public Label getSpielstandGast() {
			return spielstandGast;
		}
		

		public Label getGesamt() {
			return gesamt;
		}
		
		
		//Methoden um Daten der FXML zu aendern
		
		public void setSatzstatus(String neuerStatus){
			  this.satzstatus.setText(neuerStatus);;
		}
		
		
		public void setRunde(String neueRunde){
			this.runde.setText(neueRunde);
		}
		
		
		public void setSpiel(String neuesSpiel){
			this.spiel.setText(neuesSpiel);
		}
		
		
		public void setSatz(String neuerSatz){
			this.satz.setText(neuerSatz);
		}
		
		
		public void setSpielstandHeim(String neuerSpielstandHeim) {
			this.spielstandHeim.setText(neuerSpielstandHeim);
		}
		
		
		
		public void setSpielstandGast(String neuerSpielstandGast) {
			this.spielstandGast.setText(neuerSpielstandGast);
		}
		
		
		public void setGesamt(String neuGesamt) {
			this.gesamt.setText(neuGesamt);;
		}
}
