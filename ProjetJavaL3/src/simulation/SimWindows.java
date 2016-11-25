package simulation;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeListener;




/* TODO 
 * Positionnement
 * Lecture Fichier
 * Gestion Event
 * Communication Serveur
 */
public class SimWindows extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel connection_panel;
	JPanel infoCapteur_panel;
	JPanel donnee_panel;
	
	
	public SimWindows()
	{
		setResizable(false);
		
		/*============= Initialisation de la fenetre =============*/
		this.setTitle("Simulation Capteur");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    this.setSize(500, 600);
	    
	    /*============= Initialisation des JPanel =============*/
	    JPanel content_panel = new JPanel();
	    
	    this.initconnection_panel();
	    this.initInfoCapteur_panel();
	    this.donnee_panel();
	    
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    content_panel.setLayout(new BorderLayout(0,0));
	    content_panel.add(this.connection_panel, BorderLayout.NORTH);
	    content_panel.add(this.donnee_panel, BorderLayout.CENTER);
	    content_panel.add(this.infoCapteur_panel, BorderLayout.EAST);
	    
	    this.getContentPane().add(content_panel);
	    pack();
	}
	
	/* TODO 
	 * Positionnement
	 * Gestion des event :  - Clique Bouton lance vérification des infos rentrées dans InfoCapteur
	 *  					Si valide lance demande de connection
	 *  						Si connection OK, on grise InfoCapteur et IP/Port ( aucune modif possible )
	 *  					Si non valide affichage pop-up erreur
	 *  TextField IP, Formatage du texte ( seulement int )
	 *  TextField PORT, formatage du texte et limitation ( seulement int < MAX_PORT )
	 *  */
	private void initconnection_panel()
	{
		this.connection_panel = new JPanel();
		this.connection_panel.setBackground(Color.lightGray);
		this.connection_panel.setPreferredSize(new Dimension(this.getWidth(),40));
		this.connection_panel.setLayout(new FlowLayout());
		
		JLabel ip_label = new JLabel("IP :");
		JTextField ip_textField = new JTextField();
		ip_textField.setPreferredSize(new Dimension(20, 20));
		
		this.connection_panel.add(ip_label);
		this.connection_panel.add(ip_textField);
		
		JLabel port_label = new JLabel("Port :");
		JTextField port_textField = new JTextField();
		port_textField.setPreferredSize(new Dimension(20, 20));
		
		this.connection_panel.add(port_label);
		this.connection_panel.add(port_textField);
		
		JButton connection_button = new JButton("Connection"); 
		this.connection_panel.add(connection_button);

	}
	
	/* TODO 
	 * Gestion du GridBagLayout
	 * TextField identifiant vérification identifiant possible
	 * TextField Type vérification type possible ??????
	 * Intervalle mettre deux TextField avec formatage int
	 * 
	 * Gestion des events : - Localisation affichage deux boutons Interieur Exterieur
	 * Clique sur Interieur -> Affichage Quatre menus déroulants pour sélection Batiment / Etage / Salle / info
	 * 						   Recuperation des infos dans un fichier de conf
	 * Clique sur Exterieur -> Affichage deux textFields pour coordonnées GPD Lat/Long
	 * 							( Vérification position possible ???? sur campus .... )
	 */
	private void initInfoCapteur_panel()
	{
		 this.infoCapteur_panel = new JPanel();
		 this.infoCapteur_panel.setBackground(Color.yellow);
		 this.infoCapteur_panel.setPreferredSize(new Dimension(150,550));
		 this.infoCapteur_panel.setLayout(new GridLayout(9,1));
		 
		JLabel titre = new JLabel(" Information Capteur");
		this.infoCapteur_panel.add(titre);
		 
		 JLabel id_label = new JLabel("Identifiant");
		 JTextField id_texField = new JTextField();
		 // id_texField.setPreferredSize(new Dimension(20, 20));
		 
		 JLabel type_label = new JLabel("Type");
		 JTextField type_texField = new JTextField();
		 
		 JLabel loc_label = new JLabel("Localisation");
		 JTextField loc_texField = new JTextField();
		 
		 
		 JLabel interval_label = new JLabel("Intervalle");
		 JTextField interval_texField = new JTextField();
		 
		 this.infoCapteur_panel.add(id_label);
		 this.infoCapteur_panel.add(id_texField);
		 this.infoCapteur_panel.add(type_label);
		 this.infoCapteur_panel.add(type_texField);
		 this.infoCapteur_panel.add(loc_label);
		 this.infoCapteur_panel.add(loc_texField);
		 this.infoCapteur_panel.add(interval_label);
		 this.infoCapteur_panel.add(interval_texField);
		 
	}
	
	/* TODO
	 * Gestion des events : - Tant que non connectée tout le pannel doit être grisé
	 * 						- ChangeListener sur les deux Slider, avec affichage valeur actuel
	 * 						- En fonction du type changer val_lab
	 * 						- En fonction de l'intervalle changée les min et max du slider
	 * 							et fonction du type changer le TickSpacing ???
	 * 						- CheckBox fonctionnel qui lance une fonction random
	 * Positionnement a revoir
	 */
	private void donnee_panel()
	{
		this.donnee_panel = new JPanel();
		this.donnee_panel.setBackground(Color.gray);
		this.donnee_panel.setPreferredSize(new Dimension(350,550));
		
		JLabel titre = new JLabel("######### Envoi des données #########");
		this.donnee_panel.add(titre);
		
		JLabel freq_lab = new JLabel("==========Fréquence==========");
		this.donnee_panel.add(freq_lab);
		JSlider freq_slider = new JSlider();
		freq_slider.setMinimum(0);
		freq_slider.setMaximum(100);
		freq_slider.setMajorTickSpacing(10);
		freq_slider.setMinorTickSpacing(1);
		freq_slider.setPaintTicks(true);
		freq_slider.setPaintLabels(true);
	    this.donnee_panel.add(freq_slider);
	    
		JLabel val_lab = new JLabel("===========Valeur===========");
		this.donnee_panel.add(val_lab);
		JSlider val_slider = new JSlider();
	    this.donnee_panel.add(val_slider);
	}
}
