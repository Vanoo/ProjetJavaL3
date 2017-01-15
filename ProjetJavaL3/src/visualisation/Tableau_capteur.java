package visualisation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

public class Tableau_capteur extends JPanel{

	/**
	 * 
	 */
	private ArrayList<Capteur> capteurSuivis = new ArrayList<Capteur>();
	
	private DataCapteur dataCapteur = new DataCapteur();
	
	private JTable table;
	
	private JCheckBox loc_filter;
	private JCheckBox type_filter;
	
	private JCheckBox alarm;
	private JFormattedTextField min_alarm;
	private JFormattedTextField max_alarm;
	
	private double minAlarm;
	private double maxAlarm;
	
	private JComboBox<Object> type_combo;
	
	private JButton filter_button;

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

		JTable table = new JTable(dataCapteur)
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);
				
				//  Alternate row color
				
				int modelRow = convertRowIndexToModel(row);
				
				double val = (double) getModel().getValueAt(modelRow, 3);
				
				if( alarm.isSelected() )
				{
					double min = minAlarm;
					double max = maxAlarm;
					
					if( val < min || val > max )
					{
						c.setBackground(Color.red);
					}
					else
					{
						c.setBackground(Color.white);
					}
				}
				else
				{
					c.setBackground(Color.white);
				}

				return c;
			}
		};
		
		table.setAutoCreateRowSorter(true);
		
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
					setMinMax();
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
		
		this.filter_button = filter_bouton;
		
		title_filtre.add(filter_bouton);
		
		// TYPE
		String[] type_string = { "Temperature", "Humidite",
				"Luminosite", "VolumeSonore","ConsoEclairage","EauFroide"
				,"EauChaude","VitesseVent","PressionAtm"};

		final JComboBox<Object> type_combo = new JComboBox<Object>(type_string);
		type_combo.setSelectedItem(type_string[0]);
		this.type_combo = type_combo;
		
		JCheckBox type_checkbox = new JCheckBox("Type");
		this.type_filter = type_checkbox;

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
	
	private void setMinMax()
	{
		this.minAlarm = 0;
		this.maxAlarm = 50;
	}
	
	public void reset()
	{
		this.dataCapteur.removeAll();
		this.capteurSuivis.clear();
	}
	
	/**
	 * Verifie si cap se trouve dans l arrayList des capteurs suivis
	 * @param cap le capteurs a qui on veut verifier la presence dans la liste
	 * @return true si contient cap, sinon false
	 */
	public boolean isSuivi(Capteur cap)
	{
		return capteurSuivis.contains(cap);
	}
	
	/**
	 * Ajoute de nouveaux capteurs dans la list des capteurs deja suivis
	 * modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs nouvellement suivis
	 */
	public void ajouterCapteur(Capteur newCapteur)
	{
		capteurSuivis.add(newCapteur);
		dataCapteur.addCapteur(newCapteur);
	}
	
	/**
	 * Supprime des capteurs de la list des capteurs suivis
	 * modifie la JTable affichant les capteurs suivis
	 * @param newCapteur set des capteurs qui ne seront plus suivis
	 */
	public void supprCapteur(Capteur capteur)
	{
		capteurSuivis.remove(capteur);
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
	
	public JButton getFilterButton()
	{
		return this.filter_button;
	}
	
	public void filter(String localisation)
	{		
		this.dataCapteur.removeAll();
		String type = type_combo.getSelectedItem().toString();
		
		String[] newLocalisation = localisation.split("]");
		localisation = newLocalisation[0];
		
		System.out.println("############## Start FILTER ##############");
		System.out.println("Type :"+type);
		System.out.println("Localisation :"+localisation);
		
		if( this.loc_filter.isSelected() && localisation.equals("Erreur"))
		{
			JOptionPane.showMessageDialog(null, 
					"Erreur : Veuillez selectionne une seule localisation dans l'arbre");
		}
		else
		{
			Iterator<Capteur> iter = capteurSuivis.iterator();
			boolean conforme = true;
			while(iter.hasNext())
			{
				Capteur current = iter.next();
				System.out.println("Capteur :"+current.toString());

				if( type_filter.isSelected() && ! current.getType().equals(type) )
				{
					System.out.println("Type non conforme");
					continue;
				}
				
				if( loc_filter.isSelected() )
				{
					String [] splittedLoc = localisation.split(", ");
					
					if( splittedLoc.length > 1 )
					{
						if( current.getLoc() instanceof LocalisationExt )
						{
							if( ! splittedLoc[1].equals("Exterieur") )
							{
								System.out.println("Loc non conforme : need INTERIEUR not EXTERIEUR");
								continue;
							}
						}
						else
						{
							if( splittedLoc[1].equals("Exterieur") )
							{
								System.out.println("Loc non conforme : need EXTERIEUR not INTERIEUR");
								continue;
							}
							
							LocalisationInt locCaptInt = (LocalisationInt) current.getLoc();
							String locCapt = locCaptInt.toString();
							String[] splittedLocCapteur = locCapt.split(" ");
							
							conforme = true;
							for(int i=2;i<splittedLoc.length && conforme;i++)
							{
								System.out.println("splittedLoc["+i+"] :"+splittedLoc[i]);
								System.out.println("splittedLocCapteur["+(i-2)+"] :"+splittedLocCapteur[i-2]);
								if( ! splittedLoc[i].equals(splittedLocCapteur[i-2]) )
								{
									System.out.println("Loc non conforme Bat/Etage/sal");
									conforme = false;
								}
							}
							
							if( ! conforme )
							{
								continue;
							}	
						}
					}
				}
				
				System.out.println("Capteur Conforme Ajout");
				this.dataCapteur.addCapteur(current);
			}			
		}
		System.out.println("############## END FILTER ##############");
	}
}
