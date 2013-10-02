package model.spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DateiVerwaltung {
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String pfad;
	
	public DateiVerwaltung(String pfad){		
		this.pfad = pfad;
	}
	
	public boolean dateiSchreiben(String zug){
		try{
			bufferedWriter.write(zug);
			return true;
		}catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public Zug dateiLesen(){
		File file = new File(pfad);
		while(!file.exists()){
			try{
				Thread.sleep(30);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
		try{
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		String zeile = "";
		boolean freigabe = false;
		int satzstatus = -1;
		int gegnerzug = -2;
		int sieger = -1;
		try{
			int i = 0;
			while((zeile=bufferedReader.readLine())!=null){
				switch(i){
					case 0:
						String freigabeStringArray = zeile.trim().split("<freigabe>|</freigabe>")[1];
						if(freigabeStringArray.equalsIgnoreCase("true"))
							freigabe = true;
						else
							freigabe = false;
						break;
					case 1:
						String satzstatusString = zeile.trim().split("<satzstatus>|</satzstatus>")[1];
						if(satzstatusString.trim().equalsIgnoreCase("beendet"))
							satzstatus = Zug.BEENDET;
						else
							satzstatus = Zug.OFFEN;
						break;
					case 2:
						gegnerzug = Integer.parseInt(zeile.trim().split("<gegnerzug>|</gegnerzug>")[1].trim());
						break;
					case 3:
						String siegerString = zeile.trim().split("<sieger>|</sieger>")[1];
						if(siegerString.trim().equalsIgnoreCase("Spieler O"))
							sieger = Zug.SIEGER_O;
						else if(siegerString.trim().equalsIgnoreCase("Spieler X"))
							sieger = Zug.SIEGER_X;
						else
							sieger = Zug.SIEGER_OFFEN;
						break;
				}
			}
			
			bufferedReader.close();
			fileReader.close();
			
			return new Zug(freigabe,satzstatus,gegnerzug,sieger);
			
		}catch(IOException ex){
			return null;
		}
	}
}