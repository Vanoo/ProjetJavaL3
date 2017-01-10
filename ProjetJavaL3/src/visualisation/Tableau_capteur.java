package visualisation;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;

public class Tableau_capteur extends JPanel{

	/**
	 * 
	 */
	private Set<Capteur> capteurSuivis;
	
	private final String[] entetes = {"Identifiant", "Type", "Localisation", "Valeur"};
	
	private JButton loc_button;
	private Checkbox type_filter;
	private Checkbox alarm;
	private JFormattedTextField min_alarm;
	private JFormattedTextField max_alarm;
	private JComboBox<Object> type_combo;
	
	private JTable tabCapteurSuivis;

	private static final long serialVersionUID = 1L;

	public Tableau_capteur() 
	{
		Dimension dim = new Dimension(500,350);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		this.setPreferredSize(dim);
		this.setBackground(Color.lightGray);
		
		/*=============  Titre Panel =============*/
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("#################### Capteur Suivis ####################");
		title.add(title_label);
		
		/*=============  Tableau Panel =============*/
		Object[][] donnees = {};
        
		this.tabCapteurSuivis = new JTable(donnees, this.entetes);
		JScrollPane scrollTab= new JScrollPane(this.tabCapteurSuivis);
		scrollTab.setPreferredSize(new Dimension(500,250));
		
		/*=============  Option Panel =============*/
	
		/* Alert Panel */
		JPanel alert_panel = new JPanel();
		alert_panel.setPreferredSize(new Dimension(500,40));
		alert_panel.setBackground(Color.lightGray);
		alert_panel.setLayout(new FlowLayout(FlowLayout.CENTER,15,0));
		
		// Title
		JPanel title_alert = new JPanel();
		title_alert.setPreferredSize(new Dimension(500,15));
		title_alert.setBackground(Color.lightGray);
		title_alert.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title_alert.add(new JLabel("Alerte"));
		
		// MIN MAX
		MaskFormatter int_formatter = null;
		try 
		{
			int_formatter = new MaskFormatter("####");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		int_formatter.setPlaceholderCharacter('0');
		
		JFormattedTextField alert_min_texField = new JFormattedTextField(int_formatter);
		JFormattedTextField alert_max_texField = new JFormattedTextField(int_formatter);
		
		alert_min_texField.setPreferredSize(new Dimension(40, 20));
		alert_max_texField.setPreferredSize(new Dimension(40, 20));
		
		// CheckBox
		
		Checkbox alert_checkbox = new Checkbox();
		
		// Assemblage alert
		alert_panel.add(title_alert);
		alert_panel.add(new JLabel("Min :"));
		alert_panel.add(alert_min_texField);
		alert_panel.add(new JLabel("Max :"));
		alert_panel.add(alert_max_texField);
		alert_panel.add(alert_checkbox);
		
		/* Filtre Panel */
		
		JPanel filtre_panel = new JPanel();
		filtre_panel.setPreferredSize(new Dimension(500,40));
		filtre_panel.setBackground(Color.lightGray);
		filtre_panel.setLayout(new FlowLayout(FlowLayout.CENTER,15,0));
		
		// Title
		JPanel title_filtre = new JPanel();
		title_filtre.setPreferredSize(new Dimension(500,15));
		title_filtre.setBackground(Color.lightGray);
		title_filtre.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title_filtre.add(new JLabel("Filtre"));
		
		// TYPE
		String[] type_string = { "Temperature", "Humidite",
				"Luminosite", "VolumeSonore","ConsoEclairage","EauFroide"
				,"EauChaude","VitesseVent","PressionAtm"};

		JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setSelectedItem(type_string[0]);
		
		Checkbox type_checkbox = new Checkbox();
		
		// LOCALISATION
		JButton filtre_loc_button = new JButton("Localisation");
		
		// Assemblage
		filtre_panel.add(title_filtre);
		filtre_panel.add(new JLabel("Type :"));
		filtre_panel.add(type_combo);
		filtre_panel.add(type_checkbox);
		filtre_panel.add(filtre_loc_button);
			
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(scrollTab);
		this.add(alert_panel);
		this.add(filtre_panel);
	}
	
	public JButton getLoc_button() 
	{
		return loc_button;
	}

	public Checkbox getType_filter() 
	{
		return type_filter;
	}

	public Checkbox getAlarm() 
	{
		return alarm;
	}

	public JFormattedTextField getMin_alarm() 
	{
		return min_alarm;
	}
	
	public JFormattedTextField getMax_alarm() 
	{
		return max_alarm;
	}

	public JComboBox<Object> getType_combo() 
	{
		return type_combo;
	}
	
	private Object[][] transfSetCapteur(Set<Capteur> listCapteur)
	{
		Object[][] donnees = {};
		return donnees;
	}
	
	/**
	 * Ajoute de nouveaux capteurs dans le set des capteurs deja suivis
	 * puis modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs nouvellement suivis
	 */
	public void ajouterCapteur(Set<Capteur> newCapteur)
	{
		System.out.println("Ajout Capteur dans tableau_capteur panel");
		this.capteurSuivis.addAll(newCapteur);
		this.tabCapteurSuivis = new JTable(transfSetCapteur(newCapteur), this.entetes);
	}
	
	/**
	 * Supprime des capteurs du set des capteurs suivis
	 * puis modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs qui ne seront plus suivis
	 */
	public void supprCapteur(Set<Capteur> capteurAsuppr)
	{
		System.out.println("Suppresion Capteur dans tableau_capteur panel");
		this.capteurSuivis.removeAll(capteurAsuppr);
		this.tabCapteurSuivis = new JTable(transfSetCapteur(capteurAsuppr), this.entetes);
	}
	
}
