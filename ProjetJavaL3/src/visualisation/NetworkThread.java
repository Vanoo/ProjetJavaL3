package visualisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * 
 *
 */
public class NetworkThread extends Thread
{
	private Reseaux res;
	private BufferedReader in;
	private BufferedWriter w;
	
	/**
	 * Constructeur
	 * 
	 * @parametres Reseaux qui cree le thread
	 */
    public NetworkThread(Reseaux res) 
    {
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
		
	    while(res.connected) 
	    {
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
				w.write(message+message.split(";").length);
				w.write("\r\n");
				w.flush();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

	    	res.setRetour(message);
	    	
	    	if(message.startsWith("DeconnexionOK"))
	    	{
	    		res.connected = false;
	    	}	    	
	    }
	    
	    System.out.println("NetworkThread Terminated");
		

	}

}
