package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.TreePath;

import com.sun.crypto.provider.DESCipher;

import simulation.XmlJTree;
import sun.security.krb5.internal.crypto.DesCbcCrcEType;

public class Choix_capteur_panel extends JPanel
{
	/**
	 * 
	 */
	
	private JButton inscription_button;
	private JButton desinscription_button;
	
	private static final long serialVersionUID = 1L;

	public Choix_capteur_panel() 
	{
		Dimension dim = new Dimension(500,275);
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		this.setPreferredSize(dim);
		this.setBackground(Color.lightGray);
		
		/*=============  Titre Panel =============*/
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("#################### Capteur Actif ####################");
		title.add(title_label);
		
		/*=============  Tree Panel =============*/
		JPanel capteur_tree = new JPanel();
		capteur_tree.setPreferredSize(new Dimension(250,260));
		capteur_tree.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
		capteur_tree.setBackground(Color.lightGray);
		
		
		final XmlJTree arbre = new XmlJTree(null);
		arbre.setPath("./config.xml");
		
		JScrollPane scroll_arbre = new JScrollPane(arbre);
		scroll_arbre.setPreferredSize(new Dimension(200,250));
		
		capteur_tree.add(scroll_arbre);
		
		/*=============  Bouton =============*/
		JPanel bouton_panel = new JPanel();
		
		bouton_panel.setPreferredSize(new Dimension(150,60));
		bouton_panel.setBackground(Color.lightGray);
		
		JButton inscription = new JButton("Inscription");
		inscription.setPreferredSize(new Dimension(150,20));
		JButton desinscription = new JButton("Desinscription");
		desinscription.setPreferredSize(new Dimension(150,20));
		
		this.inscription_button = inscription;
		this.desinscription_button = desinscription;
		
		bouton_panel.add(this.inscription_button);
		bouton_panel.add(this.desinscription_button);
		
		/*=============  Ajout Panel =============*/
		
		this.add(title);
		this.add(capteur_tree);
		this.add(bouton_panel);
	}
	
	/**
	 * Retourne le bouton d inscription
	 * @return JButton
	 */
	public JButton getInscriptionButton()
	{
		return this.inscription_button;
	}
	
	/**
	 * Retourne le bouton de desinscription
	 * @return JButton
	 */
	public JButton getDesinscriptionButton()
	{
		return this.desinscription_button;
	}
	
}
