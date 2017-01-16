package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.TreePath;

import visualisation.DoubleTextField;


/**
 * @Javadoc
 * Classe qui gère le panel "capteur" où les informations concernant le capteur sont entrées. 
 * 
 */
public class InfoCapteur_panel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Dimension dim = new Dimension(500,400);
	JTextField identifiant;
	JComboBox<Object> type;
	
	DoubleTextField min;
	DoubleTextField max;
	
	DoubleTextField latitude;
	DoubleTextField longitude;
	
	JPanel localisationBouton;
	JPanel localisationChoix;
	
	boolean isInterieur;
	
	String batiment;
	String etage;
	String salle;
	JTextField commentaire;
	
	/**
	 * @param args
	 */
	public InfoCapteur_panel()
	{
		/*============= Creation Panel infoCapteur =============*/
		
		super();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentY(CENTER_ALIGNMENT);
		
		/*============= Initialisation des composant du Jpanel =============*/
		// this.add(infoCapteur,BorderLayout.CENTER);
		
		JPanel jp = new JPanel();
		jp.setBackground(Color.gray);
		jp.setPreferredSize(new Dimension(500,20));
		jp.add(new JLabel("#################### Info Capteur ####################"));
		jp.setMaximumSize(new Dimension(500,10));
		this.add(jp);
		
		Init_id_panel();
		Init_type_panel();
		Init_intervalle_panel();
		Init_localisation_panel();
		exterieur();
	}
	
	public void Init_id_panel()
	{
		JPanel id = new JPanel();
		id.setAlignmentY(CENTER_ALIGNMENT);
		id.setBackground(Color.LIGHT_GRAY);
		id.setMaximumSize(new Dimension(130,45));
		id.setPreferredSize(new Dimension(130,45));
		JTextField id_text_field = new JTextField();
		id_text_field.setText("Default");
		id_text_field.setPreferredSize(new Dimension(130, 20));
		
		JLabel identifiant = new JLabel("==Identifiant==");
		
		id.add(identifiant);
		id.add(id_text_field);
		
		this.add(id);

		this.identifiant = id_text_field;
	}
	
	public void Init_type_panel()
	{
		JPanel typ = new JPanel();
		typ.setBackground(Color.LIGHT_GRAY);
		typ.add(new JLabel("====Type===="));
		typ.setMaximumSize(new Dimension(130,45));
		typ.setPreferredSize(new Dimension(130,45));
		String[] type_string = { "Temperature", "Humidite",
				"Luminosite", "VolumeSonore","ConsoEclairage","EauFroide"
				,"EauChaude","VitesseVent","PressionAtm"};
		 //Create the combo box, select item at index 4.
		JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		// type_combo.setPreferredSize(new Dimension(130,20));
		type_combo.setSelectedItem(type_string[0]);
		typ.add(type_combo);		
		
		this.add(typ);
		this.type = type_combo;
	}
	
	public void Init_intervalle_panel()
	{
		JPanel inter = new JPanel();
		inter.setBackground(Color.LIGHT_GRAY);
		inter.add(new JLabel("====Intervalle===="));
		inter.setLayout(new FlowLayout());
		inter.setMaximumSize(new Dimension(200,50));
		inter.setPreferredSize(new Dimension(200,50));
		
		JPanel min_max_panel = new JPanel();		
		
		String patternValeur = "[-+]?[0-9]{0,3}+(\\.[0-9]{0,3}+)?";
		
		DoubleTextField min_texField = new DoubleTextField(6,patternValeur);
		min_texField.setText("0");
		
		DoubleTextField max_texField = new DoubleTextField(6,patternValeur);
		max_texField.setText("100");
		
		min_max_panel.add(new JLabel("Min :"));
		min_max_panel.add(min_texField);
		
		min_max_panel.add(new JLabel("Max :"));
		min_max_panel.add(max_texField);
		min_max_panel.setBackground( Color.LIGHT_GRAY );
		
		this.min = min_texField;
		this.max = max_texField;
		
		inter.add(min_max_panel);
		
		this.add(inter);
		// this.intervalle = inter;
	}
	
	public void Init_localisation_panel()
	{
		
		JPanel localPanel = new JPanel();
		localPanel.setMaximumSize(new Dimension(250,30));
		localPanel.add(new JLabel("==Localisation=="));
		
		JPanel localisationBouton = new JPanel();
		// localisationBouton.setLayout(new FlowLayout());
		localisationBouton.setMaximumSize(new Dimension(250,30));
		// localisationBouton.setLayout(new GridLayout(2,1));
		
		// Bouton "extérieur"
		JButton bExt = new JButton("Exterieur");
		
		bExt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exterieur();
			}
		});
		
		// Bouton intérieur
		JButton bInt = new JButton("Interieur");
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
		localisationBouton.setBackground(Color.LIGHT_GRAY);

		localPanel.setBackground(Color.LIGHT_GRAY);
		
		this.localisationChoix = localisationChoix;
		this.localisationBouton = localisationBouton;
		
		this.add(localPanel);
		this.add(localisationBouton);
	    this.add(localisationChoix);
	}
	
	// Callback quand on clique sur "Extérieur".
		private void exterieur() 
		{
			remove(this.localisationChoix);
			this.isInterieur = false;
			
			JPanel ext_panel = new JPanel();
			ext_panel.setBackground(Color.LIGHT_GRAY);
			JLabel gps_label = new JLabel("Coordonnees GPS");


			JPanel lat_long_panel = new JPanel();
			lat_long_panel.setLayout(new FlowLayout());
			lat_long_panel.setBackground(Color.lightGray);
			
			String patternLat = "[-+]?[0-9]{0,2}+(\\.[0-9]{0,7}+)?";
			String patternLong = "[-+]?[0-9]{0,3}+(\\.[0-9]{0,7}+)?";
			
			DoubleTextField latitude = new DoubleTextField(9,patternLat);
			latitude.setText("43.5603781");

			DoubleTextField  longitude = new DoubleTextField(9,patternLong);
			longitude.setText("1.4688585");
			
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
			// arbre.setSize(new Dimension(100,200));
			JPanel int_panel = new JPanel();
			
			JPanel arbre_panel = new JPanel();
			arbre_panel.setPreferredSize(new Dimension(500,140));
			arbre_panel.setBackground(Color.LIGHT_GRAY);
			arbre_panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
			
			int_panel.setBackground(Color.LIGHT_GRAY);
			JScrollPane scroll_arbre = new JScrollPane(arbre);
			scroll_arbre.setPreferredSize(new Dimension(280,140));
			
			arbre_panel.add(scroll_arbre);
			arbre.setPath("./config.xml");

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
			            	 
			            	 System.out.println(temp.toString());
			            	 
			            	 if(temp.getPathCount() == 5)
			            	 {
			            		 salle = temp.getLastPathComponent().toString();
			            		 TreePath temp2 = temp.getParentPath();
			            		 System.out.println(temp2.getLastPathComponent().toString());
			            		 
			            		 etage = temp2.getLastPathComponent().toString();
                                 
			            		 TreePath temp3 = temp2.getParentPath();
			            		 batiment = temp3.getLastPathComponent().toString();
			            		 System.out.println("Batiment : "+batiment);
			            		 System.out.println("Etage : "+etage);
			            		 System.out.println("Salle : "+salle);
			            	 }
			            	 else
			            	 {
			            		 salle = null;
			            	 }
			             }
			         }
			         
			         
			     }
			 };
			 arbre.addMouseListener(ml);


			JTextField commentaire = new JTextField(20);
			commentaire.setText("noComment");
			commentaire.setPreferredSize(new Dimension(80,20));
			
			
			int_panel.add(arbre_panel);
			int_panel.add(new JLabel("Info complementaire :"));
			int_panel.add(commentaire);
			
			this.commentaire = commentaire;
			this.localisationChoix = int_panel;

			this.add(int_panel);
			
			revalidate();
			repaint();
		}
		
		public Localisation getInfoLocalisation()
		{
			this.localisationChoix.getComponents();
			
			Localisation loc;
			if( isInterieur )
			{
				loc = new LocalisationInt(batiment,etage,salle,this.commentaire.getText());
			}
			else
			{			
				loc = new LocalisationExt(getLatitude(),getLongitude());
			}
			return loc;
		}
		
		private double getLatitude()
		{
			return this.latitude.getValeur();
		}
		
		private double getLongitude()
		{
			return this.longitude.getValeur();
		}
		
		public double getInfoMax()
		{
			return this.max.getValeur();
		}
		
		public double getInfoMin()
		{
			return this.min.getValeur();
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
