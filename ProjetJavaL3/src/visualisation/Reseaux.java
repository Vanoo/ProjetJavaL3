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
import java.util.TreeSet;

import javax.swing.JOptionPane;

/**
 * 
 * Class gerant la partie reseau
 * 
 * ouvre un socket et initie les communications avec le serveurs
 * une fois connecte lance un Thread qui s'occupe de la reception des messages
 * seul reseau envoi des messages au serveurs
 *
 */
public class Reseaux extends java.util.Observable
{
	Socket socket;
	InetAddress server_address;
	int port;
    BufferedReader in;
    PrintWriter out;
    private boolean noerror;
    boolean connected;
    private NetworkThread thread;
    
    private String retour;
    
    /**
     * Constructor
     * set noerror to true
     */
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
	        	this.connected = true;
	        	listen();
	        }	
		}
		return noerror;
	}
	
	/**
	 * 
	 * Envoi une demande de deconnexion au serveur
	 * 
	 */
	public void EnvoiDemandeDeconnexion() 
	{
		out.println("DeconnexionVisu");
	}
	
	/**
	 * Cree un thread qui receptionne les messages en continu
	 */
	public void listen() 
	{
		this.thread = new NetworkThread(this);
		this.thread.start();
	}
	
	// TODO javadoc
	public void stopListen()
	{
		try 
		{
			out.close();
			socket.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	// TODO javadoc
	public String getRetour() 
	{
		return retour;
	}

	// TODO javadoc
	public void setRetour(String retourInscription) 
	{
		synchronized (this) {
			this.retour = retourInscription;
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Inscrit l'interface de visualisation a un ensemble de capteurs
	 * 
	 * @param tabIdCapteur tableau des noms des capteurs auxquels il faut s'inscrire
	 */
	public void inscription(String[] tabIdCapteur)
	{
		// String check = this.retourInscription;
		String message = "InscriptionCapteur";
		TreeSet<String> capteursSuivis = new TreeSet<String>();
		
		for(int i = 0; i < tabIdCapteur.length; i++)
		{
			capteursSuivis.add(tabIdCapteur[i]);
			message = message+";"+tabIdCapteur[i];
		}
		
		// On envoie le message decrivant les capteurs auxquels on souhaite s'inscrire
		
		System.out.println("MessageInterface :"+message);
		out.println(message);
	}

	/**
	 * Desinscrit l'interface de visualisation a un ensemble de capteurs
	 * 
	 * @param tabIdCapteur tableau des noms des capteurs auxquels il faut se desinscrire
	 */
	public void desinscription(String[] tabIdCapteur)
	{
		// String check = this.retourInscription;
		String message = "DesinscriptionCapteur";
		TreeSet<String> capteursSuivis = new TreeSet<String>();
		
		for(int i = 0; i < tabIdCapteur.length; i++)
		{
			capteursSuivis.add(tabIdCapteur[i]);
			message = message+";"+tabIdCapteur[i];
		}
		
		// On envoie le message decrivant les capteurs auxquels on souhaite s'inscrire
		
		System.out.println("MessageEnvoye :"+message);
		out.println(message);
	}
	
}

