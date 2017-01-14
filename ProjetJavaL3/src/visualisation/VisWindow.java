package visualisation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	private List<Capteur> ListCapteurPresent = new ArrayList<Capteur>();
	private List<String>  capteursEnAttente = new ArrayList<String>();
	
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
	    
	    
	    
	    // Listener sur le bouton d'inscription
	    this.choixCapteur.getInscriptionButton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				EnvoiInscription();
			}
		});
	    
	    // Listener sur le bouton de desinscription
	    this.choixCapteur.getDesinscriptionButton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				tab_capteur.supprCapteur(null);
			}
		});

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
		System.out.println("Demande Inscription");
		ArrayList<String> arrayListIdCapteurASuivre;
		
		arrayListIdCapteurASuivre = this.choixCapteur.getSelected();
		
		String[] tabId = new String[arrayListIdCapteurASuivre.size()];
		tabId = arrayListIdCapteurASuivre.toArray(tabId);
		
		// System.out.println("Inscription Capteur : "+tabIdCapteurASuivre.toString());
		
		res.inscription(tabId);
		this.choixCapteur.getInscriptionButton().setEnabled(false);
		this.choixCapteur.getDesinscriptionButton().setEnabled(false);
	}
	
	/**
	 * Rajoute les capteurs correspondant au identifiants donne en parametre
	 * dans le panel TableauCapteur
	 * @param tabIdCapteurSuccessInscr
	 */
	public void InscriptionOk(List<String> capteursEnAttente2)
	{
		JOptionPane.showMessageDialog(null, "InscriptionOK");
		
		for(int i=0;i<capteursEnAttente2.size();i++)
		{
			// Transformation id -> capteur
			
			
			this.tab_capteur.ajouterCapteur(ListCapteurPresent.get(i));
		}		
	}
	
	/**
	 * Suppression des capteurs selectionnes dans le panel ChoixCapteur
	 * a la liste des capteurs suivis dans le panel TableauCapteur
	 */
	public void desincription()
	{
		JOptionPane.showMessageDialog(null, "InscriptionOK");
		
		ArrayList<String> tabIdCapteurAsuppr;
		
		tabIdCapteurAsuppr = this.choixCapteur.getSelected();
		
		
		
		String[] tabId = new String[tabIdCapteurAsuppr.size()];
		tabId = tabIdCapteurAsuppr.toArray(tabId);

		// res.desinscription(tabId);s
		
		for(int i=0;i<tabId.length;i++)
		{
			Capteur cap;
			
			// Transformation id -> capteur
			cap = getCapteurFromId(tabId[i]);
			
			this.tab_capteur.supprCapteur(cap);
		}
	}
	
	/**
	 * Renvoi la liste des objects capteurs correspondant au tableau des identifiants des capteurs
	 * 
	 * @param tabIdCapteur tableau d identifiant de capteur
	 * @return liste de Capteurs
	 */
	private Capteur getCapteurFromId(String idCapteur)
	{
		Capteur capCurrent = null;
		boolean found = false;
		for(int i=0;! found;i++ )
		{
			capCurrent = ListCapteurPresent.get(i);
			if( capCurrent.getId().equals(idCapteur) )
			{
				found = true;
			}
		}
		
		return capCurrent;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{	    
	    String message = res.getRetour();
	    
	    // InscriptionOK / KO
	    if(message.startsWith("InscriptionCapteur"))
	    {
	    	String [] splittedString = message.split(";"); // Test sur l'attribut length d'un tableau Java
	    	
	    	if(splittedString.length != 1)
	    	{
	    		for(int i = 0; i < splittedString.length; i++)
	    		{
	    			if(capteursEnAttente.contains(splittedString[i]))
	    			{
	    				capteursEnAttente.remove(splittedString[i]);
	    			}
	    		}
	    	}
	    	System.out.println("CapteursSuccess : "+capteursEnAttente.toArray().toString());
	    	InscriptionOk(capteursEnAttente);
	    	this.choixCapteur.getInscriptionButton().setEnabled(true);
	    	this.choixCapteur.getDesinscriptionButton().setEnabled(true);
	    }
	    
	
	// Appartition Nouveau Capteur
	    if(message.startsWith("CapteurPresent"))
	    {
	    	Capteur cap = null;
	    	String [] splittedString = message.split(";");
	    	
	    	if(splittedString.length == 7)
	    	System.out.println("Message : "+message);
	    	System.out.println("Length : "+splittedString.length);
	    	System.out.println("splittedString[4] :"+splittedString[4]);
	    	
	    	if(splittedString.length == 7) // Capteur d'interieur
	    	{
	    		cap = new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4], splittedString[5], splittedString[6]);
	    	}
	    	else // Capteur d'exterieur
	    	{
	    		cap = new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4]);
	    	}
	    	
			System.out.println("AjoutCapteur dans ListCapteurPresent");
	    	ListCapteurPresent.add(cap);
	    	System.out.println("AjoutCapteur dans Jtree");
	    	choixCapteur.modifListCapteur(cap, 0);
	    }
	
	// Disparition Capteur
	    if(message.startsWith("CapteurDeco"))
	    {
	    	String [] splittedString = message.split(";");
	    	// choixCapteur.supprCapteur(getCapteurFromId(splittedString[1]));
	    	choixCapteur.modifListCapteur(getCapteurFromId(splittedString[1]), 1);
	    }
	
	// Data Capteur
	    if(message.startsWith("ValeurCapteur"))
	    {
	    	String [] splittedString = message.split(";");
	    	// tab_capteur.changeValue(splittedString[1], Double.parseDouble(splittedString[2]));
	    }
	    
	}
}
