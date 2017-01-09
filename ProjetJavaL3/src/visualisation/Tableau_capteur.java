package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Tableau_capteur extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tableau_capteur() 
	{
		Dimension dim = new Dimension(500,250);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		this.setPreferredSize(dim);
		this.setBackground(Color.white);
		
		/*=============  Titre Panel =============*/
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("#################### Capteur Suivis ####################");
		title.add(title_label);
		
		/*=============  Tableau Panel =============*/
		
		Object[][] donnees = 
		{
				
        };
 
        String[] entetes = {"Id", "Type", "Localisation", "Valeur"};
 
		JTable capteur_tab = new JTable(donnees, entetes);
		capteur_tab.setPreferredSize(new Dimension(500,150));
		
		JScrollPane scrollTab= new JScrollPane(capteur_tab);
		scrollTab.setPreferredSize(new Dimension(500,150));
		
		/*=============  Option Panel =============*/
		
		JPanel alert_panel = new JPanel();
		alert_panel.add(new JLabel());
		
		JTextField alert_min = new JTextField();
		JTextField alert_max = new JTextField();		
		
		JPanel filtre_panel = new JPanel();
		
		String[] type_string = { "Temperature", "Humidite",
				"Luminosite", "VolumeSonore","ConsoEclairage","EauFroide"
				,"EauChaude","VitesseVent","PressionAtm"};
		 //Create the combo box, select item at index 4.
		JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setSelectedItem(type_string[0]);

		filtre_panel.add(type_combo);
		
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(scrollTab);
	}
}
