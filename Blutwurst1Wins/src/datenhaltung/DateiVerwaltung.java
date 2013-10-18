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
	
	public DateiVerwaltung(String pfad,SpielModel model){		
		this.lesePfad = pfad+"\\server2spieler"+model.getSelbst().getKennzeichnung()+".xml";
		this.schreibePfad = pfad+"\\spieler"+model.getSelbst().getKennzeichnung()+"2server.txt";
		System.out.println(this.lesePfad);
		this.model = model;
	}
	
	public DateiVerwaltung(SpielModel model){
		this.model = model;
	}
	
	public void setPfad(String pfad){
		this.lesePfad = pfad+"\\server2spieler"+model.getSelbst().getKennzeichnung()+".xml";
		this.schreibePfad = pfad+"\\spieler"+model.getSelbst().getKennzeichnung()+"2server.txt";
	}
	
	public boolean dateiSchreiben(Zug zug){
		try{
			File file = new File(schreibePfad);
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(""+zug.getSpalte());
			bufferedWriter.close();
			fileWriter.close();
			return true;
		}catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public void pfadSchreiben(String pfad){
		if(pfad != null){
			File file = new File("pfad.txt");
			try{
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(pfad);
				bufferedWriter.close();
				fileWriter.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public String pfadLesen(){
		File file = new File("pfad.txt");
		if(!file.exists())
			return "";
		else{
			try{
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				String pfad = bufferedReader.readLine();
				bufferedReader.close();
				fileReader.close();
				if(pfad!=null)
					return pfad;
				else
					return "";
			}catch(IOException ex){
				ex.printStackTrace();
				return "";
			}
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
		Spieler sieger = null;
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
							sieger = model.getSieger('o');
						else if(siegerString.trim().equalsIgnoreCase("Spieler X"))
							sieger = model.getSieger('x');
						i++;
						break;
					default:
						i++;
						break;
				}
			}

			bufferedReader.close();
			fileReader.close();
			
//			Serverfile löschen
			boolean geloescht = file.delete();
			System.out.println(geloescht ? "gelöscht" : "nicht gelöscht");
			
			return new Zug(freigabe,satzstatus,gegnerzug,sieger,model.getGegner());
			
		}catch(IOException ex){
			return null;
		}
	}
}
