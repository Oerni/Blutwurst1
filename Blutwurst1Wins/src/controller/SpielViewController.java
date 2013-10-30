package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import parallelisierung.AktualisierenRunnable;
import parallelisierung.PfadSchreibenRunnable;
import parallelisierung.ServerZugSchreibenRunnable;
import parallelisierung.SpeichernRunnable;
import parallelisierung.ThreadExecutor;
import datenhaltung.Satz;
import datenhaltung.SpielModel;
import datenhaltung.Spieler;
import datenhaltung.StatistikModel;
import datenhaltung.Strings;
import datenhaltung.Zug;



public class SpielViewController extends Thread{
	private SpielModel model;
	private Scene scene;
	
	private final Color eigeneFarbe = Color.web("0x33CC66");
	private final Color gegnerFarbe = Color.web("0xFF3333");
	
	private final int WEITERSPIELEN = 0;
	private final int SPIEL_GEWONNEN = 1;
	private final int SPIEL_VERLOREN = 2;
	private final int SPIELFELD_VOLL = 3;
	
	@FXML
	private Label satzstatus;
	@FXML
	private Label runde;
	@FXML
	private Label spiel;
	@FXML
	private Label satz;
	@FXML
	private Label spielstandHeim;
	@FXML
	private Label spielstandGast;
	@FXML
	private Label gesamt;
	@FXML
	private Circle a1;
	@FXML
	private Circle a2;
	@FXML
	private Circle a3;
	@FXML
	private Circle a4;
	@FXML
	private Circle a5;
	@FXML
	private Circle a6;
	@FXML
	private Circle b1;
	@FXML
	private Circle b2;
	@FXML
	private Circle b3;
	@FXML
	private Circle b4;
	@FXML
	private Circle b5;
	@FXML
	private Circle b6;
	@FXML
	private Circle c1;
	@FXML
	private Circle c2;
	@FXML
	private Circle c3;
	@FXML
	private Circle c4;
	@FXML
	private Circle c5;
	@FXML
	private Circle c6;
	@FXML
	private Circle d1;
	@FXML
	private Circle d2;
	@FXML
	private Circle d3;
	@FXML
	private Circle d4;
	@FXML
	private Circle d5;
	@FXML
	private Circle d6;
	@FXML
	private Circle e1;
	@FXML
	private Circle e2;
	@FXML
	private Circle e3;
	@FXML
	private Circle e4;
	@FXML
	private Circle e5;
	@FXML
	private Circle e6;
	@FXML
	private Circle f1;
	@FXML
	private Circle f2;
	@FXML
	private Circle f3;
	@FXML
	private Circle f4;
	@FXML
	private Circle f5;
	@FXML
	private Circle f6;
	@FXML
	private Circle g1;
	@FXML
	private Circle g2;
	@FXML
	private Circle g3;
	@FXML
	private Circle g4;
	@FXML
	private Circle g5;
	@FXML
	private Circle g6;
	
	private Circle feld[][] = new Circle[7][6];
	@FXML
	private Button aButton;
	@FXML
	private Button bButton;
	@FXML
	private Button cButton;
	@FXML
	private Button dButton;
	@FXML
	private Button eButton;
	@FXML
	private Button fButton;
	@FXML
	private Button gButton;
	
	@FXML
	private AnchorPane resetMenu, spielstartMenu, gewinnAnzeige, verlustAnzeige, spielfeldVollAnzeige;
	@FXML
	private AnchorPane spielhilfeAnzeige, spielregelnAnzeige;
	@FXML
	private ImageView spielfeldButtonOrange, resetButtonOrange, spielregelnButtonOrange;
	@FXML
	private ImageView spielfeldButton, startButtonOrange, spielregelnButton;
	@FXML
	private ImageView hilfeButton, hilfeButtonOrange;
	@FXML
	private TextField gegnerNameEingabe;
	@FXML
	private TextField pfadEingabe;
	@FXML
	private Text gegnerNameText;
	@FXML
	private RadioButton radioButtonX, radioButtonO;
	@FXML
	private Label gewinnAnzeigenLabel, verlustAnzeigenLabel;
	@FXML
	private ChoiceBox<String> zugzeitAuswahlBox;

	
	public SpielViewController(SpielModel model){
		
		this.model = model;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SpielView.fxml"));
		fxmlLoader.setController(this);
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		feld[0][0] = a1;
		feld[1][0] = b1;
		feld[2][0] = c1;
		feld[3][0] = d1;
		feld[4][0] = e1;
		feld[5][0] = f1;
		feld[6][0] = g1;
		feld[0][1] = a2;
		feld[1][1] = b2;
		feld[2][1] = c2;
		feld[3][1] = d2;
		feld[4][1] = e2;
		feld[5][1] = f2;
		feld[6][1] = g2;
		feld[0][2] = a3;
		feld[1][2] = b3;
		feld[2][2] = c3;
		feld[3][2] = d3;
		feld[4][2] = e3;
		feld[5][2] = f3;
		feld[6][2] = g3;
		feld[0][3] = a4;
		feld[1][3] = b4;
		feld[2][3] = c4;
		feld[3][3] = d4;
		feld[4][3] = e4;
		feld[5][3] = f4;
		feld[6][3] = g4;
		feld[0][4] = a5;
		feld[1][4] = b5;
		feld[2][4] = c5;
		feld[3][4] = d5;
		feld[4][4] = e5;
		feld[5][4] = f5;
		feld[6][4] = g5;
		feld[0][5] = a6;
		feld[1][5] = b6;
		feld[2][5] = c6;
		feld[3][5] = d6;
		feld[4][5] = e6;
		feld[5][5] = f6;
		feld[6][5] = g6;
	}
	
	public void show(){
		Stage primaryStage = model.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println(runde.getText()+", "+satzstatus.getText());
		
	}
	
	@FXML
	public void spielstartMenuAnzeigen(){
		model.initDateiverwaltung();
		String pfad = model.getDateiVerwaltung().pfadLesen();
		if(!pfad.equals(""))
			pfadEingabe.setText(pfad);
		
		spielstartMenu.setVisible(true);
		startButtonOrange.setVisible(true);
	}
	
	@FXML
	public void spielstartMenuSchliessen(){
		spielstartMenu.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
	public void run(){
				while(true){
					Zug gegnerZug = model.getDateiVerwaltung().dateiLesen();
					
					try{
						int weiterspielen = sonderfaellePruefen(gegnerZug);
						
						if(weiterspielen == this.WEITERSPIELEN){
//							Gegnerzug einfuegen
							if(gegnerZug.getSpalte() != -1){
								int gegnerZeile = model.getFeld().einfuegen(gegnerZug);
								System.out.println("Gegner Zug (Spalte,Zeile): " + gegnerZug.getSpalte() + gegnerZeile);
								gegnerZug.setZeile(gegnerZeile);
								if(gegnerZeile != -1)
									faerben(gegnerZug.getSpalte(),gegnerZug.getZeile(),gegnerZug.getSpieler());
							}else{
//								Satzstart!
								model.getSpiel().getAktuellenSatz().setBeginnendenSpieler(model.getSpiel().getSelbst());
//								ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
							}
							
							model.getSpiel().getAktuellenSatz().zugEinfuegen(gegnerZug);
							gegnerZug.satzZuordnen(model.getSpiel().getAktuellenSatz());
//							Parallel kann der Zug gespeichert werden
							if(gegnerZug.getSpalte()!=-1)
								ThreadExecutor.getInstance().execute(new SpeichernRunnable(gegnerZug));
//							gegnerZug.speichern();
//							Eigenen zug berechnen
							int berechneteSpalte = model.getFeld().zugBerechnen(model.getSpiel().getSelbst());
							Zug eigenerZug = new Zug(berechneteSpalte,model.getSpiel().getSelbst());
							eigenerZug.satzZuordnen(model.getSpiel().getAktuellenSatz());
							int eigeneZeile = model.getFeld().einfuegen(eigenerZug);
							eigenerZug.setZeile(eigeneZeile);
							ServerZugSchreibenRunnable schreibenRunnable = new ServerZugSchreibenRunnable(model);
							schreibenRunnable.setZug(eigenerZug);
							ThreadExecutor.getInstance().execute(schreibenRunnable);
							if(zugzeitAuswahlBox.getValue()!=null)
								Thread.sleep(Integer.parseInt(zugzeitAuswahlBox.getValue())*1000/2);
							else
								Thread.sleep(Strings.STANDARD_ZUGZEIT_S*1000/2);
							faerben(eigenerZug.getSpalte(),eigenerZug.getZeile(),eigenerZug.getSpieler());
							
							System.out.println("Eigener Zug: " + eigenerZug.getZeile());
							
//							ueberpruefen, ob beginnender Spieler schon gesetzt wurde
//							Wenn nicht, muss der beginnende Spieler der Gegner gewesen sein.
							if(model.getSpiel().getAktuellenSatz().spielerBegonnen()==null){
								model.getSpiel().getAktuellenSatz().setBeginnendenSpieler(model.getSpiel().getGegner());
//								ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
							}
							
//							Parallel kann der eigene Zug gespeichert werden
							ThreadExecutor.getInstance().execute(new SpeichernRunnable(eigenerZug));
//							eigenerZug.speichern();
						}else{
							switch(weiterspielen){
							case SPIEL_GEWONNEN:
								spielGewonnen();
								break;
							case SPIEL_VERLOREN:
								spielVerloren();
								break;
							case SPIELFELD_VOLL:
								spielfeldVoll();
								break;
							}
						}
					}catch(InterruptedException ex){
						System.out.println("InterruptedException");
						ex.printStackTrace();
					}
				}
	}
	
	private void spielGewonnen(){
		model.getSpiel().getAktuellenSatz().setSieger(model.getSpiel().getSelbst());
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
		model.getSpiel().erhoehePunkteHeim();
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel()));

		if(model.getSpiel().getPunkteHeim() >= 2){
//			Spiel gewonnen
			model.getSpiel().setSieger(model.getSpiel().getSelbst());
			model.getSpiel().aktualisieren();
		}
		else{
//			Satz gewonnen
		}
		System.out.println("Spiel gewonnen");
		gewinnAnzeige.setVisible(true);
		//gewinnAnzeigenLabel
	}
	private void spielVerloren(){
		model.getSpiel().getAktuellenSatz().setSieger(model.getSpiel().getGegner());
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
		model.getSpiel().erhoehePunkteGegner();
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel()));
		
		if(model.getSpiel().getPunkteGegner() >= 2){
			//		Spiel  verloren
			model.getSpiel().setSieger(model.getSpiel().getGegner());
			model.getSpiel().aktualisieren();
		}else{
			//		Satz verloren
		}
		System.out.println("Spiel verloren");
		verlustAnzeige.setVisible(true);
		//verlustAnzeigenLabel
	}
	private void spielfeldVoll(){
		System.out.println("Spielfeld voll");
		spielfeldVollAnzeige.setVisible(true);
	}
	
	@FXML
	public void spielStarten() {
		char eigeneKennzeichnung = radioButtonO.isSelected() ? 'o' : 'x'; 
		spielstartMenu.setVisible(false);
		String pfad = pfadEingabe.getText();
		// Eingabe des Gegnernamens lesen: gegnerNameEingabe.getText();
		String gegnerName = gegnerNameEingabe.getText();
		gegnerNameText.setText(gegnerName);
		ThreadExecutor.getInstance().execute(new PfadSchreibenRunnable(pfad,model));
		model.init(pfad,gegnerName,eigeneKennzeichnung);
		start();
	}
	
	public int sonderfaellePruefen(Zug gegnerzug){		
		// Pruefen auf Spezialfaelle
		if(!gegnerzug.getFreigabe() && gegnerzug.getSatzstatus().trim().equalsIgnoreCase("beendet")){
			if(gegnerzug.getSieger() == model.getSpiel().getSelbst()){
				if(gegnerzug.getSpalte() == -1){
//					Spiel gewonnen
					return this.SPIEL_GEWONNEN;
				}else{
//					Spielfeld voll
					return this.SPIELFELD_VOLL;
				}
			}else{
				if(gegnerzug.getSpalte() != -1){
//					Spiel verloren
					return this.SPIEL_VERLOREN;
				}else{
//					Spielfeld voll
					return this.SPIELFELD_VOLL;
				}
			}
		}
		return this.WEITERSPIELEN;
	}

	//gewinnAnzeigeSchliessenButton pressed
	@FXML
	public void gewinnAnzeigeSchliessen(){
		gewinnAnzeige.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
	//verlustAnzeigeSchliessenButton pressed
	@FXML
	public void verlustAnzeigeSchliessen(){
		verlustAnzeige.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
	//spielfeldVollAnzeigeSchliessenButton pressed
	public void spielfeldVollAnzeigeSchliessen(){
		spielfeldVollAnzeige.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
//	Druecken des Statistik anzeigen Buttons
	@FXML
	public void statistikAnzeigen(){
		StatistikModel sModel = new StatistikModel(new Stage());
		new StatistikViewController(sModel).show();
	}
	
	private void faerben(int spalte,int zeile,Spieler spieler){
		if(spieler == model.getSpiel().getSelbst())
			feld[spalte][zeile].setFill(eigeneFarbe);
		else if(spieler == model.getSpiel().getGegner())
			feld[spalte][zeile].setFill(gegnerFarbe);
		else
			feld[spalte][zeile].setFill(Color.WHITE);
	}
	
	//Oeffnen des ResetMenus
	@FXML
	public void resetMenuAnzeigen(){
		resetButtonOrange.setVisible(true);
		spielfeldButton.setVisible(true);
		resetMenu.setVisible(true);
	}
	
	//Schliessen des ResetMenus
	@FXML
	public void resetMenuSchliessen(){
		resetButtonOrange.setVisible(false);
		startButtonOrange.setVisible(false);
		spielfeldButtonOrange.setVisible(true);
		spielfeldButton.setVisible(false);
		resetMenu.setVisible(false);
	}
	
	//	Satz zuruecksetzen
	@FXML
	public void resetSatz(){
		
		resetMenuSchliessen();
	}
	
	//Spiel zuruecksetzen
	@FXML
	public void resetSpiel(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				feld[i][j].setFill(Color.WHITE);
//		model.zuruecksetzen();
		
	 resetMenuSchliessen();
	}
	
	//Runde zuruecksetzen
	@FXML
	public void resetRunde(){
		
		resetMenuSchliessen();
	}
	
	//neuen Satz starten
	@FXML
	public void gewinnAnzeigeNeuerSatzStarten(){
//		Neuen Satz starten
		gewinnAnzeige.setVisible(false);
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				this.faerben(i,j,null);
		model.feldZuruecksetzen();
		Satz satz = new Satz(model.getSpiel());
		model.getSpiel().satzHinzufuegen(satz);
		satz.speichern();
	}
	
	@FXML
	public void verlustAnzeigeNeuerSatzStarten(){
//		Neuen Satz starten
		verlustAnzeige.setVisible(false);
		Satz satz = new Satz(model.getSpiel());
		satz.speichern();
	}
	
	@FXML
	public void spielfeldVollAnzeigeNeuerSatzStarten(){
//		Neuen Satz starten
		spielfeldVollAnzeige.setVisible(false);
		Satz satz = new Satz(model.getSpiel());
		satz.speichern();
	}
	
	
	//Hilfe anzeigen
	@FXML
	public void hilfeAnzeigen(){
		spielhilfeAnzeige.setVisible(true);
		spielregelnAnzeige.setVisible(false);
		spielfeldButtonOrange.setVisible(false);
		spielfeldButton.setVisible(true);
		spielregelnButtonOrange.setVisible(false);
		spielregelnButton.setVisible(true);
		hilfeButtonOrange.setVisible(true);
		hilfeButton.setVisible(false);
	}
	
	//Spielfeld anzeigen
	@FXML
	public void spielfeldAnzeigen(){
		spielhilfeAnzeige.setVisible(false);
		spielregelnAnzeige.setVisible(false);
		spielfeldButtonOrange.setVisible(true);
		spielfeldButton.setVisible(false);
		spielregelnButtonOrange.setVisible(false);
		spielregelnButton.setVisible(true);
		hilfeButtonOrange.setVisible(false);
		hilfeButton.setVisible(true);
	}
	
	//Spielregeln anzeigen
	@FXML
	public void spielregelnAnzeigen(){
		spielregelnAnzeige.setVisible(true);
		spielhilfeAnzeige.setVisible(false);
		spielfeldButtonOrange.setVisible(false);
		spielfeldButton.setVisible(true);
		spielregelnButtonOrange.setVisible(true);
		spielregelnButton.setVisible(false);
		hilfeButtonOrange.setVisible(false);
		hilfeButton.setVisible(true);
	}
	
	@FXML
	public void mouseOver(){
		scene.setCursor(Cursor.HAND);
	}
	
	@FXML
	public void mouseExit(){
		scene.setCursor(Cursor.DEFAULT);
	}
}