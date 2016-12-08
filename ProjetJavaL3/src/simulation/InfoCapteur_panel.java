package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


	boolean exter;
	boolean intern;
	
	JLabel id_label;
	JLabel titre;
	JLabel type_label;
	JLabel interval_label;
	JLabel min_label;
	JLabel max_label;
	JLabel loc_label;
	JLabel gps_label;
	
	JTextField id_texField;
	public JTextField min_texField;
	JTextField max_texField;
	JTextField loc_texField;
	JTextField latitude;
	JTextField longitude;
	
	JComboBox<Object> type_combo;
	
	JPanel min_panel;
	JPanel max_panel;
	JPanel exterieur;
	JPanel interieur;
	
	JButton bExt;
	JButton bInt;
	
	/**
	 * @param args
	 */
	public InfoCapteur_panel()
	{
		new JPanel();
		this.setBackground(Color.yellow);
		this.setPreferredSize(new Dimension(150,550));
		this.setLayout(new GridLayout(18,1));
		 
		this.titre = new JLabel("Information Capteur");
		this.add(titre);
		 
		 this.id_label = new JLabel("===Identifiant====");
		 this.id_texField = new JTextField();		 

		 this.id_texField.setPreferredSize(new Dimension(20, 20));
		 
		 this.type_label = new JLabel("=====Type======");
		 String[] type_string = { "Temperature", "Humidité","Consommation Electrique", "etc" };
		 //Create the combo box, select item at index 4.
		this.type_combo = new JComboBox<Object>(type_string);
		 
		 this.interval_label = new JLabel("====Intervalle====");
		 
		 this.min_panel = new JPanel();
		 this.max_panel = new JPanel();
		 
		 this.min_panel.setLayout(new BorderLayout(0,0));
		 this.max_panel.setLayout(new BorderLayout(0,0));
		 
		 this.min_texField = new JTextField();
		 this.min_texField.setPreferredSize(new Dimension(60, 20));
		 this.min_label = new JLabel("Min :");
		 this.max_label = new JLabel("Max :");
		 this.max_texField = new JTextField();
		 this.max_texField.setPreferredSize(new Dimension(60, 20));

		 this.min_panel.add(min_label,BorderLayout.WEST);
		 this.min_panel.add(min_texField,BorderLayout.EAST);
		 this.min_panel.setBackground( Color.red );
		 
		 
		 this.max_panel.add(max_label,BorderLayout.WEST);
		 this.max_panel.add(max_texField,BorderLayout.EAST);
		 this.max_panel.setBackground( Color.MAGENTA );
		 
		 
		 this.loc_label = new JLabel("===Localisation===");
		// Choix intérieur/extérieur
		// Bouton "extérieur"
		this.bExt = new JButton("Extérieur");
		this.bExt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				exterieur();
			}
		});
		
		// Bouton intérieur
		this.bInt = new JButton("Intérieur");
		this.bInt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				interieur();
			}
		});
		this.exter = false;
		this.intern = false;
		
		 this.loc_texField = new JTextField();
		 
		 this.add(id_label);
		 this.add(id_texField);
		 this.add(type_label);
		 this.add(type_combo);
		 this.add(interval_label);
		 this.add(min_panel);
		 this.add(max_panel);
		 this.add(loc_label);
		 this.add(bExt);
		 this.add(bInt);
	}
	
	
	// Callback quand on clique sur "Extérieur"
	private void exterieur() 
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
	}
	
	



	// Callback quand on clique sur "Intérieur"
	private void interieur() 
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
		JComboBox batiment = new JComboBox();
		interieur.add(batiment);
		this.add(interieur);
		revalidate();
		repaint();
		
	}
	
	public JTextField getLatitude() {
		return latitude;
	}


	public void setLatitude(JTextField latitude) {
		this.latitude = latitude;
	}


	public JTextField getLongitude() {
		return longitude;
	}


	public void setLongitude(JTextField longitude) {
		this.longitude = longitude;
	}
	
	public boolean isExter() {
		return exter;
	}


	public void setExter(boolean exter) {
		this.exter = exter;
	}


	public boolean isIntern() {
		return intern;
	}


	public void setIntern(boolean intern) {
		this.intern = intern;
	}


	public JLabel getId_label() {
		return id_label;
	}


	public void setId_label(JLabel id_label) {
		this.id_label = id_label;
	}


	public JLabel getTitre() {
		return titre;
	}


	public void setTitre(JLabel titre) {
		this.titre = titre;
	}


	public JTextField getId_texField() {
		return id_texField;
	}


	public void setId_texField(JTextField id_texField) {
		this.id_texField = id_texField;
	}


	public JLabel getType_label() {
		return type_label;
	}


	public void setType_label(JLabel type_label) {
		this.type_label = type_label;
	}


	public JComboBox<Object> getType_combo() {
		return type_combo;
	}


	public void setType_combo(JComboBox<Object> type_combo) {
		this.type_combo = type_combo;
	}


	public JLabel getInterval_label() {
		return interval_label;
	}


	public void setInterval_label(JLabel interval_label) {
		this.interval_label = interval_label;
	}


	public JPanel getMin_panel() {
		return min_panel;
	}


	public void setMin_panel(JPanel min_panel) {
		this.min_panel = min_panel;
	}


	public JPanel getMax_panel() {
		return max_panel;
	}


	public void setMax_panel(JPanel max_panel) {
		this.max_panel = max_panel;
	}


	public JTextField getMin_texField() {
		return min_texField;
	}


	public void setMin_texField(JTextField min_texField) {
		this.min_texField = min_texField;
	}


	public JLabel getMin_label() {
		return min_label;
	}


	public void setMin_label(JLabel min_label) {
		this.min_label = min_label;
	}


	public JLabel getMax_label() {
		return max_label;
	}


	public void setMax_label(JLabel max_label) {
		this.max_label = max_label;
	}


	public JTextField getMax_texField() {
		return max_texField;
	}


	public void setMax_texField(JTextField max_texField) {
		this.max_texField = max_texField;
	}


	public JLabel getLoc_label() {
		return loc_label;
	}


	public void setLoc_label(JLabel loc_label) {
		this.loc_label = loc_label;
	}


	public JButton getbExt() {
		return bExt;
	}


	public void setbExt(JButton bExt) {
		this.bExt = bExt;
	}


	public JButton getbInt() {
		return bInt;
	}


	public void setbInt(JButton bInt) {
		this.bInt = bInt;
	}


	public JTextField getLoc_texField() {
		return loc_texField;
	}


	public void setLoc_texField(JTextField loc_texField) {
		this.loc_texField = loc_texField;
	}


	public JLabel getGps_label() {
		return gps_label;
	}


	public void setGps_label(JLabel gps_label) {
		this.gps_label = gps_label;
	}


	public JPanel getExterieur() {
		return exterieur;
	}


	public void setExterieur(JPanel exterieur) {
		this.exterieur = exterieur;
	}


	public JPanel getInterieur() {
		return interieur;
	}


	public void setInterieur(JPanel interieur) {
		this.interieur = interieur;
	}


}
