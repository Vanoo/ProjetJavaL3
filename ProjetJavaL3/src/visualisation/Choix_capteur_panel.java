package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Choix_capteur_panel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Choix_capteur_panel() 
	{
		Dimension dim = new Dimension(500,200);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setPreferredSize(dim);
		this.setBackground(Color.lightGray);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		
		
		/*=============  Titre Panel =============*/
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,2));
		title.setBackground(Color.gray);
		JLabel title_label = new JLabel("#################### Capteur Actif ####################");
		title.add(title_label);
		
		/*=============  Tree Panel =============*/
		JPanel capteur_tree = new JPanel();
		capteur_tree.setPreferredSize(new Dimension(100,100));
		capteur_tree.setBackground(Color.blue);
		
		/*=============  Option Panel =============*/
		JPanel option_tree = new JPanel();
		option_tree.setPreferredSize(new Dimension(100,100));
		option_tree.setBackground(Color.yellow);
		
		/*=============  Interactif Panel =============*/
		
		JPanel tree_option = new JPanel();
		tree_option.setPreferredSize(new Dimension(500,180));
		tree_option.setBackground(Color.lightGray);
		
		/* Rassemblement Composant */

		tree_option.add(capteur_tree);
		tree_option.add(option_tree);
		
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(tree_option);
	}
	
	
}
