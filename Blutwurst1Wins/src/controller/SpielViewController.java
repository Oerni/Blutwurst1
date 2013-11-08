package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Stack;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logik.Feld;
import parallelisierung.AktualisierenRunnable;
import parallelisierung.LabelAendernRunnable;
import parallelisierung.PfadSchreibenRunnable;
import parallelisierung.ServerZugSchreibenRunnable;
import parallelisierung.SpeichernRunnable;
import parallelisierung.ThreadExecutor;
import spieldaten.Satz;
import spieldaten.Spiel;
import spieldaten.SpielModel;
import spieldaten.Spieler;
import spieldaten.Strings;
import spieldaten.Zug;
import statistikdaten.StatistikModel;

public class SpielViewController implements Runnable{
	private SpielModel model;
	private Scene scene;
	
	private final Color eigeneFarbe = Color.web("0x33CC66");
	private final Color gegnerFarbe = Color.web("0xFF3333");
	
	private final int WEITERSPIELEN = 0;
	private final int SPIEL_GEWONNEN = 1;
	private final int SPIEL_VERLOREN = 2;
	private final int SPIELFELD_VOLL = 3;

	private Feld spielfeld;
	
	@FXML
	private Button gewinnAnzeigeNeuerSatzButton;
	@FXML
	private Button verlustAnzeigeNeuerSatzButton;
	@FXML
	private Button spielfeldVollAnzeigeNeuerSatzButton;
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
	private AnchorPane spielhilfeAnzeige, spielregelnAnzeige, neuenGegnerAnlegenMenu, offeneSpieleMenu;
	@FXML
	private ImageView spielfeldButtonOrange, resetButtonOrange, spielregelnButtonOrange;
	@FXML
	private ImageView spielfeldButton, startButtonOrange, spielregelnButton;
	@FXML
	private ImageView hilfeButton, hilfeButtonOrange;
	@FXML
	private TextField gegnerNameEingabe, neuerSpielerName;
	@FXML
	private TextField pfadEingabe;
	@FXML
	private RadioButton radioButtonX, radioButtonO;
	@FXML
	private Label gewinnAnzeigenLabel, verlustAnzeigenLabel;
	@FXML
	private ChoiceBox<String> zugzeitAuswahlBox;
	@FXML
	private ComboBox<Spieler> gegnerAuswahlBox;
	@FXML
	private Text gegnerNameText;
	@FXML
	private TableColumn spielnummerOffeneSpieleSpalte, spielstandOffeneSpieleSpalte;
	@FXML
	private TableView offeneSpieleTabelle;

//	Zwischenspeichern von Pfad, Gegnernamen und kennzeichnung
	private String pfad;
	private char kennzeichnung;
	private Spieler gegner;
	
	public SpielViewController(SpielModel sModel){
		this.model = sModel;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SpielView.fxml"));
		fxmlLoader.setController(this);
		try{
			Pane pane = (Pane)fxmlLoader.load();
			scene = new Scene(pane);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		this.spielfeld = new Feld(model);
		this.gesamt.setText(""+model.getSelbst().getPunktzahl());
		this.satzstatus.setText(model.getSatzStatus());
		
		gegnerAuswahlBox.setConverter(new StringConverter<Spieler>() {
			
			@Override
			public String toString(Spieler spieler) {
				return spieler.getName();
			}
			
			@Override
			public Spieler fromString(String name) {
				return model.getGegner(name);
			}
		});
		
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
//		Pfad eintragen
		model.initDateiverwaltung();
		String pfad = model.getDateiVerwaltung().pfadLesen();
		
//		Spieler-Auswahlbox faellen
		Stack<Spieler> alleSpieler = model.getAlleSpieler();
		
		gegnerAuswahlBox.setCellFactory(new Callback<ListView<Spieler>, ListCell<Spieler>>() {
			
			@Override
			public ListCell<Spieler> call(ListView<Spieler> listview) {
				final ListCell<Spieler> cell = new ListCell<Spieler>(){
					@Override
					protected void updateItem(Spieler spieler,boolean bln){
						super.updateItem(spieler, bln);
						setContentDisplay(ContentDisplay.TEXT_ONLY);
						if(spieler != null)
							setText(spieler.getName());
						else
							setText(null);
					}
					
					
				};
				
				return cell;
			}
		});
		gegnerAuswahlBox.getItems().removeAll(model.getAlleSpieler());

		for(Spieler spieler : alleSpieler)
			gegnerAuswahlBox.getItems().add(spieler);
		
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
	
	public void setGegnerPunkte(String gegnerPunkte){
		this.spielstandGast.setText("" + gegnerPunkte);
	}
	public void setHeimPunkte(String heimPunkte){
		this.spielstandHeim.setText("" + heimPunkte);;
	}
	public void setSatzStatus(String satzStatus){
		this.satzstatus.setText(satzStatus);
	}
	public void setRunde(String runde){
		this.runde.setText(runde);
	}
	public void setSpielNr(String spielNr){
		this.spiel.setText(spielNr);
	}
	public void setSatz(String satz){
		this.satz.setText(satz);
	}
	
	public void run(){
				model.setSatzStatus("spielen");
				Platform.runLater(new LabelAendernRunnable(this,model));
				while(true){
					Zug gegnerZug = model.getDateiVerwaltung().dateiLesen();
					this.model.setSatzStatus(gegnerZug.getSatzstatus());
					Platform.runLater(new LabelAendernRunnable(this,model));
					try{
						int weiterspielen = sonderfaellePruefen(gegnerZug);
						
						if(weiterspielen == this.WEITERSPIELEN){
//							Gegnerzug einfuegen
							if(gegnerZug.getSpalte() != -1){
								int gegnerZeile = spielfeld.einfuegen(gegnerZug);
								System.out.println("Gegner Zug (Spalte,Zeile): " + gegnerZug.getSpalte() + gegnerZeile);
								gegnerZug.setZeile(gegnerZeile);
								if(gegnerZeile != -1)
									faerben(gegnerZug.getSpalte(),gegnerZug.getZeile(),gegnerZug.getSpieler());
							}else{
//								Satzstart!
								model.getSpiel().getAktuellenSatz().setBeginnendenSpieler(model.getSpiel().getSelbst());
								ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
							}
							
							model.getSpiel().getAktuellenSatz().zugEinfuegen(gegnerZug);
							gegnerZug.satzZuordnen(model.getSpiel().getAktuellenSatz());
//							Parallel kann der Zug gespeichert werden
							if(gegnerZug.getSpalte()!=-1)
								ThreadExecutor.getInstance().execute(new SpeichernRunnable(gegnerZug));
//							Eigenen zug berechnen
//							int berechneteSpalte = model.getFeld().zugBerechnen(model.getSpiel().getSelbst());
							int berechneteSpalte = spielfeld.zugBerechnen();
							Zug eigenerZug = new Zug(berechneteSpalte,model.getSpiel().getSelbst());
							eigenerZug.satzZuordnen(model.getSpiel().getAktuellenSatz());
							int eigeneZeile = spielfeld.einfuegen(eigenerZug);
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
						}else{
							switch(weiterspielen){
							case SPIEL_GEWONNEN:
								spielGewonnen();
								break;
							case SPIEL_VERLOREN:
//								Stein des Gegners muss nun noch gesetzt werden
								int gegnerZeile = spielfeld.einfuegen(gegnerZug);
								System.out.println("Gegner Zug (Spalte,Zeile): " + gegnerZug.getSpalte() + gegnerZeile);
								gegnerZug.setZeile(gegnerZeile);
								if(gegnerZeile != -1)
									faerben(gegnerZug.getSpalte(),gegnerZug.getZeile(),gegnerZug.getSpieler());
								spielVerloren();
								this.wait();
								this.labelsAktualisieren();
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
	
	private void labelsAktualisieren(){
		this.spielstandGast.setText(""+model.getSpiel().getPunkteGegner());
	}
	
	private void spielGewonnen(){
		model.getSpiel().getAktuellenSatz().setSieger(model.getSpiel().getSelbst());
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
		model.getSpiel().erhoehePunkteHeim(2);
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel()));
//		Thread-Wechsel
		Platform.runLater(new LabelAendernRunnable(this,model));
		
		Spieler sieger = ermittleSieger();
		if(sieger!=null){
			model.getSpiel().setSieger(sieger);
			gewinnAnzeigeNeuerSatzButton.setVisible(false);
			Platform.runLater(new Runnable(){
				@Override
				public void run(){
					gewinnAnzeigenLabel.setText("das Spiel!");
				}
			});
		}else{
			if(model.getRunde() == 1 && model.getSpiel().getAnzahlSaetze() == 2){
//				Ende der 1. Runde -> Neue Runde
//				Schließen oder neue Runde klicken
			}else if(model.getRunde() == 2 && model.getSpiel().getAnzahlSaetze() == 3){
//				Ende der 2. Runde; kein Sieger
			}else{
//				weiterspielen
//				Schließen oder neuer Satz
				gewinnAnzeigeNeuerSatzButton.setVisible(true);
			}
		}

		gewinnAnzeige.setVisible(true);
		
	}
	
//	Sieger des Spiels ermitteln
	public Spieler ermittleSieger(){
		if(model.getRunde() == 1){
			if(model.getSpiel().getAnzahlSaetze() == 2){
				if(model.getSpiel().getPunkteHeim() > model.getSpiel().getPunkteGegner())
					return model.getSpiel().getSelbst();
				if(model.getSpiel().getPunkteHeim() < model.getSpiel().getPunkteGegner())
					return model.getSpiel().getGegner();
			}
		}else if(model.getRunde() == 2){
			if(model.getSpiel().getAnzahlSaetze() == 3){
				if(model.getSpiel().getPunkteHeim() > model.getSpiel().getPunkteGegner())
					return model.getSpiel().getSelbst();
				if(model.getSpiel().getPunkteHeim() < model.getSpiel().getPunkteGegner())
					return model.getSpiel().getGegner();
			}
		}
		return null;
	}
	
	private void spielVerloren(){
		model.getSpiel().getAktuellenSatz().setSieger(model.getSpiel().getGegner());
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel().getAktuellenSatz()));
		model.getSpiel().erhoehePunkteGegner(2);
		ThreadExecutor.getInstance().execute(new AktualisierenRunnable(model.getSpiel()));
		
		Spieler sieger = ermittleSieger();
		if(sieger!=null){
			model.getSpiel().setSieger(sieger);
			verlustAnzeigeNeuerSatzButton.setVisible(false);
			Platform.runLater(new Runnable(){
				@Override
				public void run(){
					verlustAnzeigenLabel.setText("das Spiel!");
				}
			});
		}else{
			if(model.getRunde() == 1 && model.getSpiel().getAnzahlSaetze() == 2){
//				Ende der 1. Runde -> Neue Runde
				
			}else if(model.getRunde() == 2 && model.getSpiel().getAnzahlSaetze() == 3){
//				Ende der 2. Runde; kein Sieger
			}else{
//				weiterspielen
				verlustAnzeigeNeuerSatzButton.setVisible(true);
			}
		}

		verlustAnzeige.setVisible(true);
		//verlustAnzeigenLabel
	}
	private void spielfeldVoll(){
		model.getSpiel().erhoehePunkteHeim(1);
		model.getSpiel().erhoehePunkteGegner(1);
		if(model.getRunde() == 1 && model.getSpiel().getAnzahlSaetze() == 2){
//				Ende der 1. Runde -> Neue Runde
			ThreadExecutor.getInstance().spielStarten(this);
		}else if(model.getRunde() == 2 && model.getSpiel().getAnzahlSaetze() == 3){
//				Ende der 2. Runde; kein Sieger
				
		}else{
//				weiterspielen
			spielfeldVollAnzeigeNeuerSatzButton.setVisible(true);
		}

		spielfeldVollAnzeige.setVisible(true);
	}
	
	@FXML
	public void spielStarten() {
		char eigeneKennzeichnung = radioButtonO.isSelected() ? 'o' : 'x'; 
		spielstartMenu.setVisible(false);
		String pfad = pfadEingabe.getText();

		if(gegnerAuswahlBox.getSelectionModel().getSelectedItem()!=null)
			this.gegner = gegnerAuswahlBox.getSelectionModel().getSelectedItem();
		else{
			Spieler unbenannterSpieler = model.getGegner("Unbenannt");
			if(unbenannterSpieler != null)
				this.gegner = unbenannterSpieler;
			else{
				this.gegner = new Spieler("Unbenannt");
				System.out.println(this.gegner);
				ThreadExecutor.getInstance().execute(new SpeichernRunnable(this.gegner));
			}
		}
		
		gegnerNameText.setText(this.gegner.getName());
		ThreadExecutor.getInstance().execute(new PfadSchreibenRunnable(pfad,model));
//		Pruefen, ob noch offene Spiele abgelegt sind
		ObservableList<Spiel> offeneSpiele = model.getOffeneSpiele(this.gegner);
		if(offeneSpiele.isEmpty()){
			model.init(pfad,this.gegner,eigeneKennzeichnung);
			ThreadExecutor.getInstance().spielStarten(this);
		}else{
			this.pfad = pfad;
			this.kennzeichnung = eigeneKennzeichnung;
//			Neues Fenster zur Auswahl, ob ein Spiel fortgesetzt werden oder ein neues gestartet werden soll
			offeneSpieleMenu.setVisible(true);
			spielnummerOffeneSpieleSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("idString"));
			spielstandOffeneSpieleSpalte.setCellValueFactory(new PropertyValueFactory<Spiel,String>("spielstand"));
			offeneSpieleTabelle.setItems(offeneSpiele);
		}
	}
	
	public int sonderfaellePruefen(Zug gegnerzug){	
		boolean freigabe = gegnerzug.getFreigabe();
		String satzstatus = gegnerzug.getSatzstatus();
		int spalte = gegnerzug.getSpalte();
		String sieger = gegnerzug.getSieger() != null ? gegnerzug.getSieger().getName() : "kein Sieger";
		System.out.println(freigabe +","+satzstatus + "," + spalte + "," + sieger);
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
		model.spielZuruecksetzen();
		gewinnAnzeige.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
	//verlustAnzeigeSchliessenButton pressed
	@FXML
	public void verlustAnzeigeSchliessen(){
		model.spielZuruecksetzen();
		verlustAnzeige.setVisible(false);
		startButtonOrange.setVisible(false);
	}
	
	//spielfeldVollAnzeigeSchliessenButton pressed
	public void spielfeldVollAnzeigeSchliessen(){
		model.spielZuruecksetzen();
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
		if(model.getSpiel()!=null){
		if(spieler == model.getSpiel().getSelbst())
			feld[spalte][zeile].setFill(eigeneFarbe);
		else if(spieler == model.getSpiel().getGegner())
			feld[spalte][zeile].setFill(gegnerFarbe);
		else
			feld[spalte][zeile].setFill(Color.WHITE);
		}else
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
		feldZuruecksetzen();
		Satz satz = new Satz(model.getSpiel());
		model.getSpiel().satzHinzufuegen(satz);
		try{
			satz.speichern();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void feldZuruecksetzen(){
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				this.faerben(i,j,null);
		this.spielfeld = new Feld(model);
	}
	
	@FXML
	public void verlustAnzeigeNeuerSatzStarten(){
//		Neuen Satz starten
		verlustAnzeige.setVisible(false);
		feldZuruecksetzen();
		Satz satz = new Satz(model.getSpiel());
		try{
			satz.speichern();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void spielfeldVollAnzeigeNeuerSatzStarten(){
//		Neuen Satz starten
		spielfeldVollAnzeige.setVisible(false);
		feldZuruecksetzen();
		Satz satz = new Satz(model.getSpiel());
		try{
			satz.speichern();
		}catch(SQLException ex){
			
		}

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
	
	//Neuen Gegenspieler anlegen
	@FXML
	public void neuenGegnerAnlegenMenuOeffnen(){
		neuenGegnerAnlegenMenu.setVisible(true);
		
	}
	
	//Neuen Gegenspieler anlegen abbrechen
	@FXML
	public void neuenGegnerAnlegenMenuSchliessen(){
		neuenGegnerAnlegenMenu.setVisible(false);
	}
	
	//Neu angelegten Spieler speichern
	@FXML
	public void neuenGegnerSpeichern(){
		if(!neuerSpielerName.getText().isEmpty()){
			Spieler neuerSpieler = new Spieler(neuerSpielerName.getText());
			if(model.spielerRegistrieren(neuerSpieler)){
				ThreadExecutor.getInstance().execute(new SpeichernRunnable(neuerSpieler));
				gegnerAuswahlBox.getItems().add(neuerSpieler);
				gegnerAuswahlBox.setValue(neuerSpieler);
			}
		}
		neuenGegnerAnlegenMenuSchliessen();
	}
	
	//Tabelle mit offenen Spielen schliessen
	@FXML
	public void offeneSpieleMenuSchliessen(){
		this.spielZuruecksetzen();
		model.init(this.pfad, this.gegner, this.kennzeichnung);
		ThreadExecutor.getInstance().spielStarten(this);
		offeneSpieleMenu.setVisible(false);
	}
	
	//Offenes Spiel fortsetzen
	@FXML
	public void offenesSpielFortsetzen(){
		Spiel spiel = (Spiel)offeneSpieleTabelle.getSelectionModel().getSelectedItem();
		spiel.ladeSaetze();
		model.spielFortsetzen(spiel,this.pfad,this.kennzeichnung,false);
		offeneSpieleMenu.setVisible(false);
		if(model.getSpiel().getAktuellenSatz().getSieger()==null){
			for(int i=0;i<7;i++)
				for(int j=0;j<6;j++)
					faerben(i,j,null);
			for(Zug zug : model.getSpiel().getAktuellenSatz().getZuege()){
				faerben(zug.getSpalte(),zug.getZeile(),zug.getSpieler());
				spielfeld.einfuegen(zug);
			}
			spielstandHeim.setText(""+model.getSpiel().getPunkteHeim());
			spielstandGast.setText(""+model.getSpiel().getPunkteGegner());
			ThreadExecutor.getInstance().spielStarten(this);
		}else{
			Satz neuerSatz = new Satz(model.getSpiel());
			ThreadExecutor.getInstance().execute(new SpeichernRunnable(neuerSatz));
			model.getSpiel().satzHinzufuegen(neuerSatz);
			spielstandHeim.setText(""+model.getSpiel().getPunkteHeim());
			spielstandGast.setText(""+model.getSpiel().getPunkteGegner());
			ThreadExecutor.getInstance().spielStarten(this);
		}
	}
	
	public void spielZuruecksetzen(){
		model.spielZuruecksetzen();
		spielfeld = new Feld(model);
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				faerben(i,j,null);
	}
	
	@FXML
	public void allesZuruecksetzen(){
		model.allesZuruecksetzen();
		this.spielfeld = new Feld(model);
		Platform.runLater(new LabelAendernRunnable(this,model));
		resetMenuSchliessen();
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