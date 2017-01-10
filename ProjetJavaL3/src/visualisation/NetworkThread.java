package visualisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class NetworkThread implements Runnable {
	
	private BufferedReader in;
	
    public NetworkThread(BufferedReader in) {
		this.in = in;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message = "plop";
		
	    while(true) {
	    	
	    	System.out.println("debug dans boucle");
	    	
	    	try
	    	{
	    		message = in.readLine();
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
	    }
		

	}

}
