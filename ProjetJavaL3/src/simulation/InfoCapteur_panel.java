package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
public class InfoCapteur_panel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel identifiant;
	JPanel type;
	JPanel intervalle;
	JPanel Localisation;
	
	boolean extern;
	boolean intern;
	
	/**
	 * @param args
	 */
	public InfoCapteur_panel()
	{
		/*============= Création Panel infoCapteur =============*/
		
		new JPanel();
		this.setBackground(Color.yellow);
		this.setPreferredSize(new Dimension(150,550));
		this.setLayout(new FlowLayout());
		this.add(new JLabel("Information Capteur"));
		
		/*============= Initialisation des composant du Jpanel =============*/
		
		// Identifiant
		Init_id_panel();
		// Type
		Init_type_panel();
		// Intervalle
		Init_intervalle_panel();
		// Localisation
		Init_localisation_panel();
	}
	
	public void Init_id_panel()
	{
		JPanel id = new JPanel();
		id.setLayout(new GridLayout(2,1));
		
		JTextField id_text_field = new JTextField();
		id_text_field.setPreferredSize(new Dimension(50, 20));
		
		id.add(new JLabel("===Identifiant===="));
		id.add(id_text_field);
		this.add(id);
		this.identifiant = id;
	}
	
	public void Init_type_panel()
	{
		JPanel typ = new JPanel();
		typ.setLayout(new GridLayout(2,1));
		String[] type_string = { "Temperature", "Humidité","Consommation Electrique", "etc" };
		 //Create the combo box, select item at index 4.
		JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setPreferredSize(new Dimension(50,20));
		
		typ.add(new JLabel("=====Type======"));
		typ.add(type_combo);
		this.add(typ);
		this.type = typ;
	}
	
	public void Init_intervalle_panel()
	{
		JPanel inter = new JPanel();
		inter.setLayout(new GridLayout(3,1));
		
		inter.add(new JLabel("====Intervalle===="));
		
		JPanel min_panel = new JPanel();
		JPanel max_panel = new JPanel();
		
		JTextField min_texField = new JTextField();
		min_texField.setPreferredSize(new Dimension(60, 20));
		
		JTextField max_texField = new JTextField();
		max_texField.setPreferredSize(new Dimension(60, 20));
		
		min_panel.add(new JLabel("Min :"),BorderLayout.WEST);
		min_panel.add(min_texField,BorderLayout.EAST);
		min_panel.setBackground( Color.red );
		
		max_panel.add(new JLabel("Max :"),BorderLayout.WEST);
		max_panel.add(max_texField,BorderLayout.EAST);
		max_panel.setBackground( Color.MAGENTA );
		
		
		inter.add(min_panel);
		inter.add(max_panel);
		
		this.add(inter);
		this.intervalle = inter;
	}
	
	public void Init_localisation_panel()
	{
		JPanel localisation = new JPanel();
		localisation.setLayout(new GridLayout(5,1));
		
		localisation.add(new JLabel("===Localisation==="));
		// Bouton "extérieur"
		JButton bExt = new JButton("Extérieur");
		
		bExt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				exterieur();
			}
		});
		
		// Bouton intérieur
		JButton bInt = new JButton("Intérieur");
		bInt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				interieur();
			}
		});
		
		localisation.add(bExt);
		localisation.add(bInt);
		this.add(localisation);
		this.Localisation = localisation;
	}
	
	// Callback quand on clique sur "Extérieur"
		private void exterieur() 
		{
			/*
			if( this.exter)
			{
				remove(this.gps_label);
				remove(this.exterieur);
				this.exter = false;
			}
			if( this.intern )
			{
				remove(this.interieur);
				this.intern = false;
			}
			this.exter = true;
			this.exterieur = new JPanel(); 		
			
			this.latitude = new JTextField(5);
			this.longitude = new JTextField(5);
			
			this.exterieur.add(latitude);
			this.exterieur.add(longitude);

			this.gps_label = new JLabel("Coordonnées GPS :");
			this.add(this.gps_label);
			this.add(this.exterieur);
			revalidate();
			repaint();
			*/
		}

		// Callback quand on clique sur "Intérieur"
		private void interieur() 
		{
			/*
			if( this.extern )
			{
				remove(this.gps_label);
				remove(this.exterieur);
				this.extern = false;
			}
			if( this.intern )
			{
				remove(this.Localisation.);
				this.intern = false;
			}
			this.intern = true;
			
			
			this.interieur = new JPanel();
			interieur.setLayout(new FlowLayout(FlowLayout.LEFT));
			interieur.add(new JLabel("Bâtiment : "));
			JComboBox batiment = new JComboBox();
			interieur.add(batiment);
			this.add(interieur);
			revalidate();
			repaint();
			*/
		}
		
		public String getInfoId()
		{
			return "Patate";
		}
		
		public String getInfoType()
		{
			return "Patate";
		}
		
		public Localisation getInfoLocalisation()
		{
			this.Localisation.getComponents();
			
			Localisation loc;
			if( true )
			{
				loc = new LocalisationInt("U4","203",2,"Les licornes sont roses");
			}
			else
			{
				loc = new LocalisationExt(80,80);
			}

			return loc;
		}
		
		public Intervalle getInfoIntervalle()
		{
			Intervalle inter = new Intervalle(-80,80);
			
			return inter;
		}
		/*
		private void interieur() throws IOException, ClassNotFoundException, Exception
		{
			if( this.exter)
			{
				remove(this.gps_label);
				remove(this.exterieur);
				this.exter = false;
			}
			if( this.intern )
			{
				remove(this.interieur);
				this.intern = false;
			}
			this.intern = true;
			
			
			this.interieur = new JPanel();
			interieur.setLayout(new FlowLayout(FlowLayout.LEFT));
			interieur.add(new JLabel("Bâtiment : "));
			
			//	Gets XML
			String filename = "../ProjetJavaL3/config.xml";
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			org.w3c.dom.Document documento = docBuilder.parse(filename);
			// Search All text
			documento.getDocumentElement().normalize();

			// Get elements from xml 
			Element raiz = documento.getDocumentElement();
			NodeList listeBatiments = raiz.getElementsByTagName("batiment");
			NodeList listeEtages = raiz.getElementsByTagName("etage");
			NodeList listeSalles = raiz.getElementsByTagName("salle");
			//String listePosRel = raiz.getElementsByTagName("posRel").item(0).getTextContent();
			// Search everything
			int bat = listeBatiments.getLength();
			int eta = listeEtages.getLength();
			int salleL = listeSalles.getLength();
			//int posRelL = listePosRel.length();
			String[] vecBatiments = new String[bat];
			String[] vecEtages = new String[eta];
			String[] vecSalles = new String[salleL];
			//String[] vecPosRel = new String[posRelL];
			for (int i = 0; i < bat; i++) {
			    Element elem = (Element) listeBatiments.item(i);           
			    vecBatiments[i] =  elem.getAttribute("id");
			}
			for (int i = 0; i < eta; i++) {
			    Element elem = (Element) listeEtages.item(i);           
			    vecEtages[i] =  elem.getAttribute("id");
			}
			for (int i = 0; i < salleL; i++) {
			    Element elem = (Element) listeSalles.item(i);           
			    vecSalles[i] =  elem.getAttribute("id");
			}
			for (int i = 0; i < posRelL; i++) {
			    Element elem = (Element) listePosRel.item(i);           
			    vecPosRel[i] =  elem.getElement;
			}
			JComboBox batiment = new JComboBox(vecBatiments);
			JComboBox etage = new JComboBox(vecEtages);
			JComboBox salle = new JComboBox(vecSalles);
			//JComboBox posRel = new JComboBox(vecPosRel);
			
			interieur.add(batiment);
			this.add(interieur);
			this.add(new JLabel("Etage : "));
			this.add(etage);
			this.add(new JLabel("Salle : "));
			this.add(salle);
			
			
			revalidate();
			repaint();
		}
		*/

	
	
}
