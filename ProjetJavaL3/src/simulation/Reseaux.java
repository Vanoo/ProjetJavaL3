package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Reseaux {
	
	Socket socket;
	InetAddress server_address;
	int port;
    BufferedReader in;
    PrintWriter out;
    boolean noerror;
    
    
    // Test  
/*    public static void main(String[] args)
	{
    	boolean bool;
    	
		Reseaux res = new Reseaux("127.0.0.1", 7888);
		bool = res.connexionExt("Thermomètre", "Temperature", 50, 65);
		if(bool) {
			res.sendData(22.5);
			res.sendData(23.5);
			res.deconnexion("Thermomètre");
			res.deconnexion("Thermomètre");
		}
		System.out.println("plop");
	} */
    
    
    
	// Creeation d'une classe reseaux, connection au serveur, et ouverture des entrees/sorties
	public Reseaux()
	{
		this.noerror = true;
	}
	
	
	// debug fonction
	//private int send(String message)
	//{
	//    out.println(message);
	//	return 0;
	//}
	
	
/*	public String receive ()
	{
		String message = "";
		try
		{
			message = in.readLine();
		}
		catch (IOException e)
		{	
			e.printStackTrace();
		}
		return message;
	} */
	
	
	public boolean connection (String adress, int port) {
		// Avoir l'adresse grace a l'ip fournie 
		try
		{
			server_address = InetAddress.getByName(adress);
			this.port = port;
			System.out.println("Tentative de connection a"+server_address+":"+port);
		}
		catch (UnknownHostException e)
		{
			// popup
			noerror = false;
			System.out.println("Error wrong IP");
			JOptionPane.showMessageDialog(null, "Error : Invalid IP adress");
		}

		// Ouverture de la connexion
		if(noerror) 
		{
			try
			{
				System.out.println("Patate 1.25");
				socket = new Socket(server_address, port);
				System.out.println("Patate 1.75");
			}
			catch (IOException e)
			{	
				noerror = false;
				JOptionPane.showMessageDialog(null, "Error : can't reach server");
			}
		}

		if(noerror) 
		{
			try
			{
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "Fatal error : can't create network listener/writer");
				noerror = false;
			}
		}
		
		return noerror;
	}
	
	
	
	
	// Pour deconnecter un capteur
	public boolean deconnexion(String id) {
		String str = "plop";
		
		out.println("DeconnexionCapteur;"+id);
		
		try
        {
			str = in.readLine();
        }
        catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Affichage debug
		System.out.println(str);

		if(str.compareTo("DeconnexionOK") == 0) {
			try
			{
				this.socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error : Unallowed Disconnection");
		}

		return false;
	}
	
	
	// Connexion d'un capteur d'exterieur
	public boolean connexionExt(String id, String type, double latitutde, double longitude) 
	{		
		String str = "plop";
		
		// Verification de parametre
		if(id == null || type == null)
		{
			return false;
		}
		
		System.out.println("ConnexionCapteur;"+id+";"+type+";"+latitutde+";"+longitude+"plopplop");
		
		// Envoi de la demande
        out.println("ConnexionCapteur;"+id+";"+type+";"+latitutde+";"+longitude+"");
        
        // Reponse du serveur
        try
        {
			str = in.readLine();
        }
        catch (IOException e)
		{
			e.printStackTrace();
		}
        
        // Affichage debug
        System.out.println(str);

        if( str.compareTo("ConnexionOK") != 0 )
        {
        	JOptionPane.showMessageDialog(null, "Error : Reject Connection from server ");
        	return false;
        }
        
        return (true);
	}
	
	// Connexion d'un capteur d'interieur
	public boolean connexionInt(String id, String type, String batiment, int etage, String salle, String pos) {
		String str = "plop";
		
		if( id == null || batiment == null || salle == null )
		{
			JOptionPane.showMessageDialog(null, "Error : No location selected ");
			return false;
		}
		
		// Envoi de la demande
        out.println("ConnexionCapteur;"+id+";"+type+";"+batiment+";"+etage+";"+salle+";"+pos);
        
        // Reponse du serveur
        try
        {
			str = in.readLine();
        }
        catch (IOException e)
		{
			e.printStackTrace();
		}
        
        // Affichage debug
        System.out.println(str);

        if( str.compareTo("ConnexionOK") != 0 )
        {
        	JOptionPane.showMessageDialog(null, "Error : Reject Connection from server ");
        	return false;
        }
        
        return true;
	}
	
	
	// Envoi de la donnee d'un capteur
	public void sendData (double data) 
	{
			out.println("ValeurCapteur;"+data);
			System.out.println("Donnee envoyee : "+data);
	}
	

}