package visualisation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NetworkThread extends Thread {
	
	private BufferedReader in;
	private DataOutputStream w;
	
    public NetworkThread(BufferedReader in) {
		this.in = in;
		
		
		
		try
		{
			File plop = new File("plop.txt");
			plop.createNewFile();
			
			w = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(plop)));
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
	    		
				w.writeBytes(message);
				w.writeChar(10);
				w.writeChar(13);
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
	    			//JOptionPane.showMessageDialog(null, "Capteur ajoute : "+splittedString[1]);
	    		}
	    		else if (splittedString.length == 7)
	    		{
	    			//ajouterCapteur(new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4], splittedString[5], splittedString[6]));
	    			System.out.println("Capteur ajoute : "+splittedString[1]);
	    			// popup
	    			//JOptionPane.showMessageDialog(null, "Capteur ajoute : "+splittedString[1]);
	    		}
	    		else
	    			System.out.println("Message erron�");
	    	}
	    }
		

	}

}
