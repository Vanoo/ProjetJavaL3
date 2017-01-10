package visualisation;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Object[][] capteur;
	
	private JCheckBox loc_filter;
	private JCheckBox type_filter;
	private JCheckBox alarm;
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
        this.capteur = donnees;
		
		this.tabCapteurSuivis = new JTable(this.capteur, this.entetes);
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
		
		JCheckBox alert_checkbox = new JCheckBox();
		
		alert_checkbox.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent event) 
			{
				if(event.getStateChange()==ItemEvent.SELECTED)
				{
					// TODO setAlarm sur le tableau
				}
				else
				{
					// TODO retire l'alarm sur le tableau
					
				}
			}
		});
		
		this.alarm = alert_checkbox;
		
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

		final JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setSelectedItem(type_string[0]);
		this.type_combo = type_combo;
		
		JCheckBox type_checkbox = new JCheckBox("Type");
		type_checkbox.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent event) 
			{
				if(event.getStateChange()==ItemEvent.SELECTED)
				{
					// TODO filtre les capteurs en fonction du type choisi dans la comboBox
					filterType( (String)type_combo.getSelectedItem() );
				}
				else
				{
					// TODO remettre les capteurs suivis par defaut	( en fonction des filtres actif )
					resetFilter();
				}
			}
		});
		
		// LOCALISATION
		JCheckBox loc_combo = new JCheckBox("Localisation");
		this.loc_filter = loc_combo;
		
		// Assemblage
		filtre_panel.add(title_filtre);
		filtre_panel.add(type_combo);
		filtre_panel.add(type_checkbox);
		filtre_panel.add(loc_combo);
			
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(scrollTab);
		this.add(alert_panel);
		this.add(filtre_panel);
	}	
	
	/**
	 * Filtre la liste des capteurs suivis en fonction de leur type
	 * 
	 * @param type
	 */
	private void filterType(String type)
	{
		// JOptionPane.showMessageDialog(null, "Filtre type"+type);
		Set<Capteur> listFiltre = null ;
		Iterator<Capteur> iter = capteurSuivis.iterator();
		Capteur current;
		Object[][] donnee;
		
		while(iter.hasNext())
		{
			current = iter.next();
			if(current.getType().equals(type))
			{
				listFiltre.add(current);
			}
		}
		
		//this.donnee = transfSet(listFiltre);
	}
	
	/**
	 * Filtre la liste des capteurs suivis en fonction de leur localisation
	 * @param loc localisation du capteur ( interieur ou exterieur )
	 */
	private void filterLoc(Localisation loc)
	{
		
	}
	
	/**
	 * Recree la liste des capteurs suivis a afficher en fonction des filtres present
	 */
	private void resetFilter()
	{
				
	}
	
	public JCheckBox getLocFilter()
	{
		return this.loc_filter;
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
