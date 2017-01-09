package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import simulation.Connection_panel;
import simulation.Localisation;
import simulation.LocalisationExt;
import simulation.LocalisationInt;
import simulation.Reseaux;

public class VisWindow extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static String idInterface = "groupe BARBEUMONTRAN";
	
	visualisation.Connection_panel connection_panel;
	visualisation.Reseaux res;
	Choix_capteur_panel choixCapteur;
	Tableau_capteur tab_capteur;
	
	public VisWindow(Dimension dim) throws ParseException
	{		
		/*============= Initialisation de la fenetre =============*/
		
		this.setTitle("Interface Visualisation");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    this.setResizable(false);
	    // this.setMinimumSize(dim);
	    // this.setMaximumSize(dim);
	    this.setPreferredSize(dim);
	    
	    /*============= Initialisation des JPanel =============*/
	    JPanel fenetreVisualisation = new JPanel();
	    fenetreVisualisation.setPreferredSize(dim);
	    fenetreVisualisation.setMinimumSize(dim);
	    fenetreVisualisation.setMaximumSize(dim);
	    fenetreVisualisation.setBackground(Color.GRAY);
	    
	    this.connection_panel = new visualisation.Connection_panel();
	    this.choixCapteur = new Choix_capteur_panel();
	    this.tab_capteur = new Tableau_capteur();
	    
	    /*============= Ajout Listener =============*/
	    this.connection_panel.getBouton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean success;
				String text_Bouton_Connection = typeBoutonConnection();
				
				// On n'est pas encore connectee au serveur
				if( text_Bouton_Connection.equals("Connection")  )
				{
					success = connection();
					if( success )
					{
						changementBouton();
						// LANCEMENT RECEPTION DONNEE
					}
					else
					{
						System.out.println(" VisWindows : connection error ");
					}
				}
				// 
				else
				{
					// ARRET RECEPTION
					success = deconnection();
					if( success )
					{
						changementBouton();
					}
					else
					{
						System.out.println(" VisWindows : deconnection error ");
					}
				}
			}
		});
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    fenetreVisualisation.add(this.connection_panel);
	    fenetreVisualisation.add(this.choixCapteur);
	    fenetreVisualisation.add(this.tab_capteur);
	    
	    this.add(fenetreVisualisation);
	    pack();
	    
	    /*============= Initialisation Reseau =============*/
	    this.res = new visualisation.Reseaux();
	    
	}
	/**
	 * 
	 * @return texte du bouton de connection
	 */
	private String typeBoutonConnection()
	{
		return connection_panel.getBouton().getText();
	}
	
	/**
	 * appel la methode changeButton de connection_panel
	 * qui modifie le texte du button de connection
	 * 
	 */
	private void changementBouton()
	{
		this.connection_panel.changeButton();
	}
	
	/**
	 * Connecte l'interface de visualisation au serveur
	 * 
	 * @return true si connection reussi false sinon
	 */
	private boolean connection()
	{
		String ip;
		int port;
		boolean success;
		
		ip = this.connection_panel.getIp();
		port = this.connection_panel.getPort();
		
		success = this.res.connection(ip, port, idInterface);
		
		return success;
	}
	
	/**
	 * Deconnecte l'interface de visualisation du serveur
	 * 
	 * @return true si connection reussi false sinon
	 */
	private boolean deconnection()
	{
		boolean success;
		success = this.res.deconnexion();
		return success;
	}
}
