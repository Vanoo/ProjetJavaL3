package visualisation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VisWindow extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;

	private static String idInterface = "groupe BARBEUMONTRAN";
	
	private visualisation.Connection_panel connection_panel;
	private visualisation.Reseaux res;
	private Choix_capteur_panel choixCapteur;
	private Tableau_capteur tab_capteur;
	private JPanel fenetre;
	private Set<Capteur> ListCapteurPresent;
	
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
	    fenetreVisualisation.setLayout(new FlowLayout(0,0,0));
	    fenetreVisualisation.setPreferredSize(dim);
	    fenetreVisualisation.setMinimumSize(dim);
	    fenetreVisualisation.setMaximumSize(dim);
	    fenetreVisualisation.setBackground(Color.lightGray);
	    
	    this.connection_panel = new visualisation.Connection_panel();
	    this.choixCapteur = new Choix_capteur_panel();
	    this.tab_capteur = new Tableau_capteur();
	    this.ListCapteurPresent = null;
	    
	    /*============= Ajout Listener =============*/
	    
	    // Listener sur bouton de connexion
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
						fifty_shade_of_gray(true,fenetre);
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
						fifty_shade_of_gray(false,fenetre);
					}
					else
					{
						System.out.println(" VisWindows : deconnection error ");
					}
				}
			}
		});
	    
	    
	    // TODO Recuperation de la localisation est envoi dans tableau_capteur
		
		

			// TODO cree methode qui gere la modif des valeurs des capteurs
	    

	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    fenetreVisualisation.add(this.connection_panel);
	    fenetreVisualisation.add(this.choixCapteur);
	    fenetreVisualisation.add(this.tab_capteur);
	    
	    this.add(fenetreVisualisation);
	    this.fenetre = fenetreVisualisation;
	    
	    this.fifty_shade_of_gray(false,this.fenetre);
	    
	    pack();
	    
	    /*============= Initialisation Reseau =============*/
	    this.res = new visualisation.Reseaux();
	    res.addObserver(this);
	    
	    
	    
	    // ATTENTION ! : penser à faire la méthode update()
	    // chercher le warning à la fin pour la retrouver
	    
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
	
	/**
	 * Permet de grise les composants de la fenetre
	 * En fonction de la connexion au serveur
	 * 
	 * @param bool true grise,false degrise
	 * @param panel panel a grise
	 */
	private void fifty_shade_of_gray(boolean bool,JPanel panel)
	{
		panel.setEnabled(bool);
		Component tab_component[] = panel.getComponents();
		
		for(int i = 0; i < tab_component.length; i++) 
	    {	        
	        if(tab_component[i] instanceof JPanel ) 
	        {
	        	if(! tab_component[i].equals(connection_panel))
	        	{
	        		fifty_shade_of_gray(bool,(JPanel) tab_component[i]);
	        	}
	        	else
	        	{
	        		fifty_shade_of_gray(!bool,(JPanel) tab_component[i]);
	        	}
	        }
        	tab_component[i].setEnabled(bool);        	
	    }
		connection_panel.getBouton().setEnabled(true);
	}	
	
	/**
	 * Envoi la demande d'inscription aux capteurs selectionnee dans le panel ChoixCapteur
	 *
	 */
	public void EnvoiInscription()
	{
		String[] tabIdCapteurASuivre;
		tabIdCapteurASuivre = this.choixCapteur.getSelected();
		res.inscription(tabIdCapteurASuivre);
	}
	
	/**
	 * Rajoute les capteurs correspondant au identifiants donne en parametre
	 * dans le panel TableauCapteur
	 * @param tabIdCapteurSuccessInscr
	 */
	public void InscriptionOk(String[] tabIdCapteurSuccessInscr)
	{
		Set<Capteur> newCapteur = this.getCapteurFormId(tabIdCapteurSuccessInscr);
		this.tab_capteur.ajouterCapteur(newCapteur);
	}
	
	/**
	 * Suppression des capteurs selectionnes dans le panel ChoixCapteur
	 * a la liste des capteurs suivis dans le panel TableauCapteur
	 */
	public void desincription()
	{
		String[] tabIdCapteurAsuppr;
		tabIdCapteurAsuppr = this.choixCapteur.getSelected();
		// res.desinscription(tabIdCapteurAsuppr);
		Set<Capteur> listCapteur = this.getCapteurFormId(tabIdCapteurAsuppr);
		this.tab_capteur.supprCapteur(listCapteur);
	}
	
	/**
	 * Ajout d un capteur dans la liste des capteurs suivis
	 * 
	 */
	public void newCapteur(Capteur cap)
	{
		this.ListCapteurPresent.add(cap);
	}
	
	/**
	 * Suppresion d un capteur de la liste des capteurs suivis
	 * 
	 */
	public void supprCapteur(Capteur cap)
	{
		this.ListCapteurPresent.remove(cap);
	}
	
	/**
	 * Renvoi la liste des objects capteurs correspondant au tableau des identifiants des capteurs
	 * 
	 * @param tabIdCapteur tableau d identifiant de capteur
	 * @return liste de Capteurs
	 */
	private Set<Capteur> getCapteurFormId(String[] tabIdCapteur)
	{
		return null;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	    
	    String message = res.getRetour();
	    
		// TODO Rajout ICI d'un listener active par la reception d un message recu du serveur
		// On repartis ensuite les infos du message recu dans les differentes methode de ViwWindows
		// en fonction du type de message recu
	
	    
	    // InscriptionOK / KO
		// VisWindowd.InscriptionOK(tabIdCapteurSuccessInscr)
	    if(message.startsWith("Inscription"))
	    {
	    	String temp = "";
	    	String [] splittedString = message.split(";"); // Test sur l'attribut length d'un tableau Java
	    	String [] capteurAcceptes = null;
	    	
	    	for(int i = 1; i < splittedString.length; i++)
	    	{
	    		temp = temp+splittedString[i];
	    	}
	    	
	    	InscriptionOk(capteurAcceptes);
	    }
	    
	
	// Appartition Nouveau Capteur
		// VisWindows.newCapteur
	
	// Disparition Capteur
		// VisWindows.supprCapteur
	
	// Data Capteur
	    
	}
}
