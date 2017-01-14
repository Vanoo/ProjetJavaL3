package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	
	private DataCapteur dataCapteur = new DataCapteur();
	private JTable table;
	
	private JCheckBox loc_filter;
	private JCheckBox type_filter;
	private JCheckBox alarm;
	private JFormattedTextField min_alarm;
	private JFormattedTextField max_alarm;
	private JComboBox<Object> type_combo;

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

		JTable table = new JTable(dataCapteur);
        this.table = table;
		
		JScrollPane scrollTab= new JScrollPane(this.table);
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
		
		JButton filter_bouton = new JButton("Filtre");
		filter_bouton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{				
				/*
				ArrayList<Capteur> capteuraAffichee = new ArrayList<Capteur>();
				Iterator<Capteur> iter = capteurSuivis.iterator();
				Capteur current;
				
				String typeSelected = (String) type_combo.getSelectedItem();
				// TODO recuperation localisation selectionee
				// Localisation locSelected = .....
				String locSelected = "S101";
				
				while( iter.hasNext() )
				{
					current = iter.next();
					String currentType = current.getType();
					// TODO recuperation localisation du capteur
					// Localisation currentLoc = .....
					String currentLoc = "S101";
					boolean capteurOk = true;
					
					if( type_filter.isSelected() && ! currentType.equals(typeSelected))
					{
						capteurOk = false;
					}
					if( type_filter.isSelected() && ! currentLoc.equals(locSelected))
					{
						capteurOk = false;
					}

					if( capteurOk )
					{
						capteuraAffichee.add(current);
					}
				} 
				*/
			}
			
		});
		
		title_filtre.add(filter_bouton);
		
		// TYPE
		String[] type_string = { "Temperature", "Humidite",
				"Luminosite", "VolumeSonore","ConsoEclairage","EauFroide"
				,"EauChaude","VitesseVent","PressionAtm"};

		final JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setSelectedItem(type_string[0]);
		this.type_combo = type_combo;
		
		JCheckBox type_checkbox = new JCheckBox("Type");

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
	 * Ajoute de nouveaux capteurs dans le set des capteurs deja suivis
	 * modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs nouvellement suivis
	 */
	public void ajouterCapteur(Capteur newCapteur)
	{
		dataCapteur.addCapteur(newCapteur);
	}
	
	/**
	 * Supprime des capteurs du set des capteurs suivis
	 * modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs qui ne seront plus suivis
	 */
	public void supprCapteur(Capteur capteur)
	{
		dataCapteur.removeCapteur(capteur);
	}
	/**
	 * Modifie la valeur du capteur dans la table avec l identifiant idCapteur
	 * 
	 * @param idCapteur
	 * @param value nouvelle valeur du capteur
	 */
	public void changeValue(String idCapteur,double value)
	{
		dataCapteur.changeValue(idCapteur, value);
	}
}
