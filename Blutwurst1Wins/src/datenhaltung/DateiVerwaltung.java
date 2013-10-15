package datenhaltung;

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
	private String lesePfad;
	private String schreibePfad;
	private SpielModel model;
	
	public DateiVerwaltung(String lesePfad,String schreibePfad,SpielModel model){		
		this.lesePfad = lesePfad+"\\server2spieler"+model.getSelbst().getKennzeichnung()+".xml";
//		this.lesePfad = lesePfad+"server2spielero.xml";
		System.out.println(this.lesePfad);
		this.schreibePfad = schreibePfad;
		this.model = model;
	}
	
	public static void main(String[] args){
		DateiVerwaltung dateiv = new DateiVerwaltung("D:\\temp\\","",null);
		Zug zug = dateiv.dateiLesen();
		System.out.println(zug.getFreigabe()+","+zug.getSatzstatus());
	}
	
	public boolean dateiSchreiben(Zug zug){
		try{
			fileWriter = new FileWriter(schreibePfad);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(zug.getSpalte());
			return true;
		}catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public Zug dateiLesen(){
		File file = new File(lesePfad);
		while(!file.exists()){
			try{
				Thread.sleep(300);
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
					case 2:
						String freigabeStringArray = zeile.trim().split("<freigabe>|</freigabe>")[1];
						if(freigabeStringArray.equalsIgnoreCase("true"))
							freigabe = true;
						else
							freigabe = false;
						i++;
						break;
					case 3:
						String satzstatusString = zeile.trim().split("<satzstatus>|</satzstatus>")[1];
						if(satzstatusString.trim().equalsIgnoreCase("beendet"))
							satzstatus = Zug.BEENDET;
						else
							satzstatus = Zug.OFFEN;
						i++;
						break;
					case 4:
						gegnerzug = Integer.parseInt(zeile.trim().split("<gegnerzug>|</gegnerzug>")[1].trim());
						i++;
						break;
					case 5:
						String siegerString = zeile.trim().split("<sieger>|</sieger>")[1];
						if(siegerString.trim().equalsIgnoreCase("Spieler O"))
							sieger = Zug.SIEGER_O;
						else if(siegerString.trim().equalsIgnoreCase("Spieler X"))
							sieger = Zug.SIEGER_X;
						else
							sieger = Zug.SIEGER_OFFEN;
						i++;
						break;
					default:
						i++;
						break;
				}
			}
			
			bufferedReader.close();
			fileReader.close();
			
			return new Zug(freigabe,satzstatus,gegnerzug,sieger,model.getGegner());
			
		}catch(IOException ex){
			return null;
		}
	}
}
