package visualisation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Interface  
 * @author r00t
 *
 */
public class VisWindow extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;

	private static String idInterface = "groupe BARBEUMONTRAN";
	
	private visualisation.Connection_panel connection_panel;
	private visualisation.Reseaux res;
	private Choix_capteur_panel choixCapteur;
	private Tableau_capteur tab_capteur;
	private JPanel graphePanel;
	private JPanel fenetre;
	private ArrayList<Capteur> ListCapteurPresent = new ArrayList<Capteur>();
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
	    
		/* Graphe Panel */
		JPanel graphe_panel = new JPanel();
		graphe_panel.setPreferredSize(new Dimension(500,50));
		graphe_panel.setBackground(Color.lightGray);
		graphe_panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
		
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("###################### Graphe ######################");
		title.add(title_label);
		
		JButton graphe_button = new JButton("Visualisation Data Graphe");
		
		graphe_panel.add(title);
		graphe_panel.add(graphe_button);
		
		this.graphePanel = graphe_panel;
	    
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
					}
					else
					{
						// TODO changer affichage
						System.out.println(" VisWindows : connection error ");
					}
				}
				// on est connectee on envoi une demande de dexonnection
				else
				{
					res.EnvoiDemandeDeconnexion();
				}
			}
		});
	    
	    // Listener sur le bouton d'inscription
	    this.choixCapteur.getInscriptionButton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				EnvoiInscDesc(true);
			}
		});
	    
	    // Listener sur le bouton de desinscription
	    this.choixCapteur.getDesinscriptionButton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				EnvoiInscDesc(false);
			}
		});

	    // Listener sur le bouton de filtrage
	    this.tab_capteur.getFilterButton().addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				String path = choixCapteur.getPathSelected();
				if( path == null )
				{
					tab_capteur.filter(path);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Choose Correct location");
				}
			}
			
		});
	    
	    graphe_button.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				new GrapheFrame();
			}
		});
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    fenetreVisualisation.add(this.connection_panel);
	    fenetreVisualisation.add(this.choixCapteur);
	    fenetreVisualisation.add(this.tab_capteur);
	    fenetreVisualisation.add(graphe_panel);
	    
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
		
		if( ! panel.equals(graphePanel) )
		{
			for(int i = 0; i < tab_component.length; i++) 
		    {
		        if(tab_component[i] instanceof JPanel ) 
		        {
		        	if(! tab_component[i].equals(connection_panel) )
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
	}	
	
	
	public void EnvoiInscDesc(boolean Insc)
	{
		ArrayList<String> listIdCapteurSelected = new ArrayList<String>();
		ArrayList<String> listIdCapteurToSend = new ArrayList<String>();
		String currentIdCapteur;
		
		listIdCapteurSelected = this.choixCapteur.getSelected();
		
		System.out.println("EnvoiInscDesc listIdCapteurSelected :"+listIdCapteurSelected.toString());
		
		Iterator<String> iter = listIdCapteurSelected.iterator();
		
		while(iter.hasNext())
		{
			currentIdCapteur = iter.next();
			if( (Insc && ! tab_capteur.isSuivi(getCapteurFromId( currentIdCapteur ) ) )
					|| (! Insc && tab_capteur.isSuivi(getCapteurFromId(currentIdCapteur))) )
			{
				listIdCapteurToSend.add(currentIdCapteur);
			}
		}
		
		String[] tabId = new String[listIdCapteurToSend.size()];
		tabId = listIdCapteurToSend.toArray(tabId);
		
		System.out.println("EnvoiInscDesc listIdCapteurToSend :"+listIdCapteurToSend.toString());
		
		if( tabId.length > 0 )
		{
			if( Insc )
			{
				res.inscription(tabId);
			}
			else
			{
				res.desinscription(tabId);
			}
			this.capteursEnAttente = listIdCapteurToSend;
			// this.choixCapteur.getInscriptionButton().setEnabled(false);
			// this.choixCapteur.getDesinscriptionButton().setEnabled(false);
		}
	}
	
	
	public void InscDescOk(boolean Insc,List<String> capteursSuccess)
	{
		System.out.println("InscDescOk capteursSuccess :"+capteursSuccess.toString());
		
		for(int i=0;i<capteursSuccess.size();i++)
		{
			if( Insc )
			{
				this.tab_capteur.ajouterCapteur(getCapteurFromId(capteursSuccess.get(i)));
			}
			else
			{
				this.tab_capteur.supprCapteur(getCapteurFromId(capteursSuccess.get(i)));
			}
			
		}
		this.capteursEnAttente.clear();
		System.out.println("InscDescOk capteursEnAttente :"+capteursEnAttente.toString());
		// this.choixCapteur.getInscriptionButton().setEnabled(true);
		// this.choixCapteur.getDesinscriptionButton().setEnabled(true);
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
		for(int i=0;! found && i < ListCapteurPresent.size();i++ )
		{
			capCurrent = ListCapteurPresent.get(i);
			if( capCurrent.getId().equals(idCapteur) )
			{
				found = true;
			}
		}
		
		return capCurrent;
	}
	
	// TODO AJOUT CLEAN JTREE ET JTABLE A LA DECONNEXION
	
	@Override
	public void update(Observable o, Object arg) 
	{	    
	    String message = res.getRetour();
	    
	    System.out.println("MessageServeur:"+message);
	    
	    // InscriptionOK / KO
	    if(message.startsWith("InscriptionCapteur"))
	    {
	    	/*
	    	for(int i=0; i < capteursEnAttente.size(); i++)
	    	{
	    		System.out.println("CapteurEnAttente["+i+"] :"+capteursEnAttente.get(i));
	    	}
	    	*/
	    	String [] splittedString = message.split(";"); // Test sur l'attribut length d'un tableau Java
	    	
	    	if(splittedString.length != 1)
	    	{
	    		for(int i = 1; i < splittedString.length; i++)
	    		{
	    			// System.out.println("splittedString["+i+"] :"+splittedString[i]);
	    			if(capteursEnAttente.contains(splittedString[i]))
	    			{
	    				// System.out.println("Remove");
	    				capteursEnAttente.remove(splittedString[i]);
	    			}
	    		}
	    	}
	    	
			String[] tabId = new String[capteursEnAttente.size()];
			tabId = capteursEnAttente.toArray(tabId);
	    	
	    	/*System.out.println("TabId "+tabId);
	    	
	    	for(int i=0; i < capteursEnAttente.size(); i++)
	    	{
	    		// System.out.println("CapteurSucess["+i+"] :"+capteursEnAttente.get(i));
	    	}
	    	*/
			InscDescOk(true,capteursEnAttente);
	    }
	    
	 // DesinscriptionOK / KO
	    if(message.startsWith("DesinscriptionCapteur"))
	    {
	    	/*
	    	for(int i=0; i < capteursEnAttente.size(); i++)
	    	{
	    		System.out.println("CapteurEnAttente["+i+"] :"+capteursEnAttente.get(i));
	    	}
	    	*/
	    	String [] splittedString = message.split(";"); // Test sur l'attribut length d'un tableau Java
	    	
	    	if(splittedString.length != 1)
	    	{
	    		for(int i = 1; i < splittedString.length; i++)
	    		{
	    			// System.out.println("splittedString["+i+"] :"+splittedString[i]);
	    			if(capteursEnAttente.contains(splittedString[i]))
	    			{
	    				// System.out.println("Remove");
	    				capteursEnAttente.remove(splittedString[i]);
	    			}
	    		}
	    	}
	    	
			String[] tabId = new String[capteursEnAttente.size()];
			tabId = capteursEnAttente.toArray(tabId);
	    	
	    	// System.out.println("TabId "+tabId);
	    	
			/*
	    	for(int i=0; i < capteursEnAttente.size(); i++)
	    	{
	    		System.out.println("CapteurSucess["+i+"] :"+capteursEnAttente.get(i));
	    	}
	    	*/
	    	
			InscDescOk(false,capteursEnAttente);
	    }
	
	// Appartition Nouveau Capteur
	    if(message.startsWith("CapteurPresent"))
	    {
	    	Capteur cap = null;
	    	String [] splittedString = message.split(";");
	    	
	    	// System.out.println("Message : "+message);
	    	// System.out.println("Length : "+splittedString.length);
	    	
	    	if(splittedString.length == 7) // Capteur d'interieur
	    	{
	    		cap = new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4], splittedString[5], splittedString[6]);
	    	}
	    	else // Capteur d'exterieur
	    	{
	    		cap = new Capteur(splittedString[1], splittedString[2], splittedString[3], splittedString[4]);
	    	}
	    	
			// System.out.println("AjoutCapteur dans ListCapteurPresent");
	    	ListCapteurPresent.add(cap);
	    	// System.out.println("AjoutCapteur dans Jtree");
	    	choixCapteur.modifListCapteur(cap, 0);
	    }
	
	// Disparition Capteur
	    if(message.startsWith("CapteurDeco"))
	    {
	    	String [] splittedString = message.split(";");
	    	Capteur cap = getCapteurFromId(splittedString[1]);
	    	ListCapteurPresent.remove(cap);
	    	choixCapteur.modifListCapteur(cap, 1);
	    	tab_capteur.supprCapteur(cap);
	    }
	
	// Data Capteur
	    if(message.startsWith("ValeurCapteur"))
	    {
	    	String [] splittedString = message.split(";");
	    	tab_capteur.changeValue(splittedString[1], Double.parseDouble(splittedString[2]));
	    }
	    
	// DeconnexionOK / KO
	    if(message.startsWith("DeconnexionOK"))
	    {
	    	// envoi sur destruction thread
	    	// Suppresion Capteurs
	    	// this.choixCapteur
	    	this.choixCapteur.reset(ListCapteurPresent);
	    	this.tab_capteur.reset();
	    	this.res.stopListen();
		    this.res.deleteObservers();
			changementBouton();
			fifty_shade_of_gray(false,fenetre);
	    }
	    
	    if(message.startsWith("DeconnexionKO"))
	    {
	    	JOptionPane.showMessageDialog(null, "Deconnexion Impossible");
	    }
	    
	}
}
