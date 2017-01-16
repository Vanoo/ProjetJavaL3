package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * JFrame affichant une JComboBox contenant les fichiers disponibles
 * et un bouton pour lancer un Graphe
 *
 */
public class GrapheFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private String currentCapteur;

	/**
	 * 
	 * Constructeur construit les elements et les positionnes dans la JFrame
	 * 
	 */
	public GrapheFrame()
	{
	    Dimension dim = new Dimension(400,150);
	    
		this.setTitle("Interface Graphe");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setPreferredSize(dim);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    
	    JPanel fenetre = new JPanel();
	    fenetre.setLayout(new GridLayout(4, 1));
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
		
		String [] capteursAvailable = readingRepertory();
		final JComboBox<String> selectCapteur = new JComboBox<>(capteursAvailable);
		
		JButton bouton = new JButton("Montrer");
		bouton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				currentCapteur = (String)selectCapteur.getSelectedItem();
				System.out.println("Printing : charts/"+currentCapteur);
				new Graphe("charts/"+currentCapteur);
			}
		});
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		
		p1.add(new JLabel("Capteur a afficher"));
		p2.add(selectCapteur);
		p3.add(bouton);
		
		
		fenetre.add(p1);
		fenetre.add(p2);
		fenetre.add(p3);
	}
	
	/**
	 * Open Repertory charts and get all child
	 * @return liste des fichiers disponibles
	 */
	public String[] readingRepertory()
	{
		String[] listefichiers = null;
		listefichiers = new File("charts").list();

		return listefichiers;
	}

}
