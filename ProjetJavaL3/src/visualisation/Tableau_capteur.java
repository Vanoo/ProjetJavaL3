package visualisation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tableau_capteur extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tableau_capteur() 
	{
		Dimension dim = new Dimension(500,100);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setPreferredSize(dim);
		
		JPanel capteur_tree = new JPanel();
		capteur_tree.setPreferredSize(new Dimension(100,100));
		capteur_tree.setBackground(Color.green);
		
		JPanel option_tree = new JPanel();
		option_tree.setPreferredSize(new Dimension(100,100));
		option_tree.setBackground(Color.magenta);
		
		this.add(new JLabel("#################### Capteur Suivis ####################"));
		this.setBackground(Color.lightGray);
		this.add(capteur_tree);
		this.add(option_tree);
	}
}
