package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import view.SpielfeldView;

public class SpielfeldVC {
	//Model
	private DataBean dataBean;
	
	//View
	private SpielfeldView spielfeldView;
	
	public SpielfeldVC(DataBean dataBean){
		this.dataBean = dataBean;
		this.spielfeldView = new SpielfeldView();
		
		//EventHandler registrieren
		spielfeldView.getAddButton().setOnAction(new AddBtnEventHandler());
		spielfeldView.getOkButton().setOnAction(new OkBtnEventHandler());
	}
	
	public void show(){
		spielfeldView.show(dataBean.getPrimaryStage());
	}
	
	class AddBtnEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			
		}
	}
	
	class OkBtnEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			//Meldung reset
			spielfeldView.getMeldungT().setText("");
			
			//Daten aus Textfeldern holen
			String vorname = spielfeldView.getVornameTX().getText();
			String nachname = spielfeldView.getNachnameTX().getText();
			
			//Daten testen
			if(vorname.isEmpty()){
				spielfeldView.getMeldungT().setText("Der Vorname fehlt!");
				return;
			}
			if(nachname.isEmpty()){
				spielfeldView.getMeldungT().setText("Der Nachname fehlt!");
				return;
			}
			
			//Daten hinzufügen
			String erg = null;
			erg = dataBean.getNamePwMap().put(vorname, nachname);
			
			//Meldung ausgeben
			if(erg == null){
				spielfeldView.getMeldungT().setText("Leser hinzugefügt!");
			}else{
				spielfeldView.getMeldungT().setText("Leser wurde bereits hinzugefügt!");
			}
		}
	}
}
