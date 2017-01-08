package visualisation;

import java.awt.Color;
import java.awt.Dimension;

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
		Dimension dim = new Dimension(500,100);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setPreferredSize(dim);
		this.setBackground(Color.lightGray);
		
		JPanel capteur_tree = new JPanel();
		capteur_tree.setPreferredSize(new Dimension(100,100));
		capteur_tree.setBackground(Color.blue);
		
		JPanel option_tree = new JPanel();
		option_tree.setPreferredSize(new Dimension(100,100));
		option_tree.setBackground(Color.yellow);
		
		this.add(new JLabel("#################### Capteur Actif ####################"));
		this.add(capteur_tree);
		this.add(option_tree);
	}
	
	
}
