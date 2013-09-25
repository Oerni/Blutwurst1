package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpielView {

	@FXML 
    private Label satzstatus;
	private Label runde;
	private Label spiel;
	private Label satz;
	private Label spielstandHeim;
	private Label spielstandGast;
	private Label gesamt;
	
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
