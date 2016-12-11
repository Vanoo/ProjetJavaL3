package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.TreePath;


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

	JTextField identifiant;
	JComboBox<Object> type;
	
	JFormattedTextField min;
	JFormattedTextField max;
	
	JFormattedTextField latitude;
	JFormattedTextField longitude;
	
	JPanel localisationBouton;
	JPanel localisationChoix;
	
	XmlJTree arbre;
	TreePath arbreChoix;
	
	boolean isInterieur;
	
	String batiment;
	int etage;
	String salle;
	
	/**
	 * @param args
	 */
	public InfoCapteur_panel()
	{
		/*============= Création Panel infoCapteur =============*/
		
		new JPanel();
		this.setBackground(Color.yellow);
		this.setPreferredSize(new Dimension(160,550));
		this.setLayout(new FlowLayout());
		this.add(new JLabel("Information Capteur"));
		
		/*============= Initialisation des composant du Jpanel =============*/
		
		// Identifiant
		this.add(new JLabel("===Identifiant===="));
		Init_id_panel();
		// Type
		this.add(new JLabel("=====Type======"));
		Init_type_panel();
		// Intervalle
		this.add(new JLabel("====Intervalle===="));
		Init_intervalle_panel();
		// Localisation
		this.add(new JLabel("===Localisation==="));
		Init_localisation_panel();
		exterieur();
	}
	
	public void Init_id_panel()
	{
		JPanel id = new JPanel();
		
		JTextField id_text_field = new JTextField();
		id_text_field.setText("Default");
		id_text_field.setPreferredSize(new Dimension(130, 20));
		
		id.add(id_text_field);
		this.add(id);
		this.identifiant = id_text_field;
	}
	
	public void Init_type_panel()
	{
		JPanel typ = new JPanel();
		String[] type_string = { "Temperature", "Humidité","ConsoElec", "Autre" };
		 //Create the combo box, select item at index 4.
		JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setPreferredSize(new Dimension(130,20));
		type_combo.setSelectedItem(type_string[0]);
		typ.add(type_combo);
		this.add(typ);
		this.type = type_combo;
	}
	
	public void Init_intervalle_panel()
	{
		JPanel inter = new JPanel();
		inter.setLayout(new GridLayout(2,1));
		
		JPanel min_panel = new JPanel();
		JPanel max_panel = new JPanel();
		
		MaskFormatter int_formatter = null;
		try {
			int_formatter = new MaskFormatter("#####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int_formatter.setPlaceholderCharacter('0');
		
		JFormattedTextField min_texField = new JFormattedTextField(int_formatter);;
		min_texField.setValue("00000");
		min_texField.setPreferredSize(new Dimension(60, 20));
		
		JFormattedTextField max_texField = new JFormattedTextField(int_formatter);;
		max_texField.setPreferredSize(new Dimension(60, 20));
		max_texField.setValue("00100");
		
		min_panel.add(new JLabel("Min :"),BorderLayout.WEST);
		min_panel.add(min_texField,BorderLayout.EAST);
		min_panel.setBackground( Color.red );
		
		max_panel.add(new JLabel("Max :"),BorderLayout.WEST);
		max_panel.add(max_texField,BorderLayout.EAST);
		max_panel.setBackground( Color.MAGENTA );
		
		this.min = min_texField;
		this.max = max_texField;
		
		inter.add(min_panel);
		inter.add(max_panel);
		
		this.add(inter);
		// this.intervalle = inter;
	}
	
	public void Init_localisation_panel()
	{
		JPanel localisationBouton = new JPanel();
		localisationBouton.setLayout(new GridLayout(2,1));
		
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
		
		localisationBouton.add(bExt);
		localisationBouton.add(bInt);
		
		JPanel localisationChoix = new JPanel();
		
		
		this.localisationChoix = localisationChoix;
		this.localisationBouton = localisationBouton;
		
		this.add(localisationBouton);
		this.add(localisationChoix);
	}
	
	// Callback quand on clique sur "Extérieur"
		private void exterieur() 
		{
			remove(this.localisationChoix);
			this.isInterieur = false;
			
			JPanel ext_panel = new JPanel();
			ext_panel.setLayout(new GridLayout(2,1));
			
			JLabel gps_label = new JLabel("Coordonnées GPS :");
			
			JPanel lat_long_panel = new JPanel();
			lat_long_panel.setLayout(new FlowLayout());
			
			MaskFormatter gps_formatter = null;
			try {
				gps_formatter = new MaskFormatter("###.######");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gps_formatter.setPlaceholderCharacter('0');
			
			JFormattedTextField latitude = new JFormattedTextField(gps_formatter);
			latitude.setValue("000.000000");
			latitude.setPreferredSize(new Dimension(20,20));
			JFormattedTextField longitude = new JFormattedTextField(gps_formatter);
			longitude.setPreferredSize(new Dimension(20,20));
			longitude.setValue("000.000000");
			
			ext_panel.add(gps_label);
			
			lat_long_panel.add(latitude);
			lat_long_panel.add(longitude);
			
			ext_panel.add(lat_long_panel);

			this.latitude = latitude;
			this.longitude = longitude;
			
			this.localisationChoix = ext_panel;
			this.add(ext_panel);
			
			revalidate();
			repaint();
		}

		// Callback quand on clique sur "Intérieur"
		
		private void interieur() 
		{
			remove(this.localisationChoix);
			this.isInterieur = true;
			// String path ="../ProjetJavaL3/config.xml";
			final XmlJTree arbre = new XmlJTree(null);
			JPanel int_panel = new JPanel();
			int_panel.setLayout(new GridLayout(8,1));
			int_panel.add(new JScrollPane(arbre));
			arbre.setPath("../ProjetJavaL3/config.xml");

			MouseAdapter ml = new MouseAdapter() 
			{
			     public void mousePressed(MouseEvent e) 
			     {
			         int selRow = arbre.getRowForLocation(e.getX(), e.getY());
			         // TreePath selPath = arbre.getPathForLocation(e.getX(), e.getY());
			         if(selRow != -1) 
			         {
			        	 if(e.getClickCount() == 1) 
			             {
			            	 TreePath temp = arbre.getPathForLocation(e.getX(), e.getY());
		            		 setLocalisationIntern(temp);
			            	 if(temp.getPathCount() == 4)
			            	 { //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			            		 salle = temp.getLastPathComponent().toString();
			            		 TreePath temp2 = temp.getParentPath();
			            		 System.out.println(temp2.getLastPathComponent().toString());
			            		 
			            		 String stage = temp2.getLastPathComponent().toString().substring(5);
                                 etage = Integer.parseInt(stage);
                                 
			            		 TreePath temp3 = temp2.getParentPath();
			            		 batiment = temp3.getLastPathComponent().toString();
			            		 System.out.println("Batiment : "+batiment);
			            		 System.out.println("Etage : "+etage);
			            		 System.out.println("Salle : "+salle);
			            	 }
			             }
			         }
			     }
			 };
			 arbre.addMouseListener(ml);


			JTextField commentaire = new JTextField(5);
			commentaire.setPreferredSize(new Dimension(20,20));

			
			int_panel.add(new JLabel("Info complémentaire :"));
			int_panel.add(commentaire);
			
			this.localisationChoix = int_panel;
			this.add(int_panel);
			
			revalidate();
			repaint();
		}
		
		public void setLocalisationIntern(TreePath arbre)
		{
			this.arbreChoix = arbre;
		}
		
		public Localisation getInfoLocalisation()
		{
			this.localisationChoix.getComponents();
			
			Localisation loc;
			if( isInterieur )
			{
				// int numEtage = this.arbreChoix[3].charAt(6);
				// this.arbreChoix.toString();
				loc = new LocalisationInt(batiment,etage,salle,"Les licornes sont roses");
			}
			else
			{			
				loc = new LocalisationExt(getLatitude(),getLongitude());
			}

			return loc;
		}
		
		public double getLatitude()
		{
			return Double.parseDouble(this.latitude.getValue().toString());
		}
		
		public double getLongitude()
		{
			return Double.parseDouble(this.longitude.getValue().toString());
		}
		
		public int getInfoMax()
		{
			return Integer.parseInt(this.max.getValue().toString());
		}
		
		public int getInfoMin()
		{
			return Integer.parseInt(this.min.getValue().toString());
		}
		
		public String getInfoId()
		{
			return this.identifiant.getText();
		}
		
		public String getInfoType()
		{
			return this.type.getSelectedItem().toString();
		}

}