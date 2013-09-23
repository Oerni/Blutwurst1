package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DateiVerwaltung {
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public DateiVerwaltung(String pfad){
		try{
			fileWriter = new FileWriter(pfad);
			fileReader = new FileReader(pfad);
			bufferedReader = new BufferedReader(fileReader);
			bufferedWriter = new BufferedWriter(fileWriter);
		}catch(IOException ex){
			
		}
	}
	
	public boolean dateiSchreiben(Zug zug){
		String ergebnis = "<freigabe>" + zug.getFreigabe() + "</freigabe>";
		ergebnis += "\r\n<satzstatus>" + zug.getSatzstatus() + "</satzstatus>";
		ergebnis += "\r\n<gegnerzug>" + zug.getGegnerzug() + "</gegnerzug>";
		ergebnis += "\r\n<sieger>" + zug.getSieger() + "</sieger>";
		try{
			bufferedWriter.write(ergebnis);
			return true;
		}catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public Zug dateiLesen(){
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
			
			return new Zug(freigabe,satzstatus,gegnerzug,sieger);
			
		}catch(IOException ex){
			return null;
		}
	}
}
