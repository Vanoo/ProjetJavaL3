package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GrapheFrame extends JFrame
{

	public GrapheFrame()
	{
	    Dimension dim = new Dimension(500,400);
	    
		this.setTitle("Interface Graphe");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setPreferredSize(dim);
	    
	    JPanel fenetre = new JPanel();
	    fenetre.setLayout(new FlowLayout(0,0,0));
	    fenetre.setPreferredSize(dim);
	    fenetre.setMinimumSize(dim);
	    fenetre.setMaximumSize(dim);
	    fenetre.setBackground(Color.lightGray);
	    
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("###################### Graphe ######################");
		title.add(title_label);
		
		fenetre.add(title);
		
		this.add(fenetre);
		pack();
	}

}
