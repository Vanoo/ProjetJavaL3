package visualisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

import javax.swing.JOptionPane;
/**
 * 
 * @Javadoc
 * Classe reseau qui s'occupe de la communication avec le serveur.
 *
 */
public class Reseaux {
	
	
	Socket socket;
	InetAddress server_address;
	int port;
    BufferedReader in;
    PrintWriter out;
    boolean noerror;
    Set<Capteur> capteurs;
    
    
	public Reseaux()
	{
		this.noerror = true;
	}
	
	/**
	 * 
	 * Etablit la connection entre l'interface de visualisation et le serveur
	 * 
	 * @param adress, Adresse IP du serveur
	 * @param port, numero de port de communication
	 * @param idInterface, identifiant de l'interface de visualisation
	 * @return true si connection reussi, false si probleme rencontree
	 */
	public boolean connection (String adress, int port,String idInterface) 
	{
		this.noerror = true;
		// Avoir l'adresse grace a l'ip fournie 
		try
		{
			server_address = InetAddress.getByName(adress);
			this.port = port;
			System.out.println("Tentative de connection a"+server_address+":"+port);
		}
		catch (UnknownHostException e)
		{
			noerror = false;
			System.out.println("Error wrong IP");
			
			// popup
			JOptionPane.showMessageDialog(null, "Error : Invalid IP adress");
		}

		// Ouverture de la connexion
		if(noerror) 
		{
			try
			{
				socket = new Socket();
				socket.connect(new InetSocketAddress(server_address, port), 5000);
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
			
			out.println("ConnexionVisu;"+idInterface);
			
			String str = "plop";
			
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
	        else
	        {
	        	listen();
	        }
			
		}
		
		return noerror;
	}
	
	/**
	 * 
	 * Etablit la connection entre l'interface de visualisation et le serveur
	 * 
	 * @return true si deconnection reussi, false si probleme rencontree
	 */
	public boolean deconnexion() 
	{
		String str = "plop";
		
		out.println("DeconnexionVisu");
		
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
			
			// casser le thread
			
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error : Unallowed Disconnection");
		}
		return false;
	}
	
	
	
	/**
	 * Cree un thread qui receptionne les messages en continu
	 */
	public void listen() {
		new NetworkThread(in).start();
	}
}

