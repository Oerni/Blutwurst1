package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Model;
import model.Zug;


public class SpielVC {
	private Model model;

	@FXML 
    private Label satzstatus;
	private Label runde;
	private Label spiel;
	private Label satz;
	private Label spielstandHeim;
	private Label spielstandGast;
	private Label gesamt;


		
	
	public SpielVC(Model model){
		this.model = model;
	
			
	}
	
		public void zugDurchfuehren(){
		// Zug des Gegners interpretieren
		Zug gegnerzug = model.zugVonServer();
		Zug eigenerZug = null;
		
		// Zug berechnen
		
		// berechneten Zug an Server uebergeben
		model.zugAnServer(eigenerZug);
	}
	
	class ZuruecksetzenBtnEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
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
