package visualisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class NetworkThread extends Thread {
	
	private Reseaux res;
	private BufferedReader in;
	private BufferedWriter w;
	
	/**
	 * Constructeur
	 * 
	 * @param res Reseaux qui cree le thread
	 */
    public NetworkThread(Reseaux res) {
		this.res = res;
		this.in = res.in;
		
		
		
		try
		{
			File logs = new File("logs.txt");
			logs.createNewFile();
			
			
			w = new BufferedWriter(new FileWriter(logs));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Methode lancee a l'execution du thread
	 * /!\ boucle infinie
	 */
	public void run()
	{
		
		String message = "plop";
		
		
		
		
	    while(true) {
	    	
	    	try
	    	{
	    		message = in.readLine();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	try
			{
	    		
				w.write(message);
				w.write("\r\n");
				w.flush();
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	    	
	    	if(message.startsWith("CapteurPresent;"))
	    	{
	    		String[] splittedString = message.split(";");
	    		if(splittedString.length == 5)
	    		{
	    			//ajouterCapteur(new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4]));
	    			
	    			System.out.println("Capteur ajoute : "+splittedString[1]);
	    			// popup
	    			JOptionPane.showMessageDialog(null, "Capteur ajoute : "+splittedString[1]);
	    		}
	    		else if (splittedString.length == 7)
	    		{
	    			//ajouterCapteur(new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4], splittedString[5], splittedString[6]));
	    			System.out.println("Capteur ajoute : "+splittedString[1]);
	    			// popup
	    			JOptionPane.showMessageDialog(null, "Capteur ajoute : "+splittedString[1]);
	    		}
	    		else
	    			System.out.println("Message erroné");
	    	}
	    	else if (message.startsWith("InscriptionCapteur"))
	    	{
	    		res.retourInscription = message;
	    	}
	    }
		

	}

}
