package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tableau_capteur extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tableau_capteur() 
	{
		Dimension dim = new Dimension(500,275);
		
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
		
		/*=============  Tree Panel =============*/
		JPanel capteur_tree = new JPanel();
		capteur_tree.setPreferredSize(new Dimension(300,260));
		capteur_tree.setBackground(Color.blue);
		
		/*=============  Option Panel =============*/
		JPanel option_tree = new JPanel();
		option_tree.setPreferredSize(new Dimension(200,260));
		option_tree.setBackground(Color.yellow);
		
		/*=============  Interactif Panel =============*/
		
		JPanel tree_option = new JPanel();
		tree_option.setPreferredSize(new Dimension(500,260));
		tree_option.setBackground(Color.lightGray);
		tree_option.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		/* Rassemblement Composant */

		tree_option.add(capteur_tree);
		tree_option.add(option_tree);
		
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(tree_option);

	}
}
