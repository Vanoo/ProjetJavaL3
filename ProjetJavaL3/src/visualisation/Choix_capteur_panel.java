package visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Choix_capteur_panel extends JPanel
{
	/**
	 * 
	 */
	
	private JButton inscription_button;
	private JButton desinscription_button;
	
	private JTree capteur_tree;	
	private DefaultTreeModel dataTree;
	
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
		
		
		/* Deprecated
		final XmlJTree arbre = new XmlJTree(null);
		arbre.setPath("./config.xml");
		*/
		
		XMLParser parseur = new XMLParser();
	    
		
		
	    JTree arbre = new JTree(parseur.parse());
	    
	    DefaultTreeModel modelJtree = (DefaultTreeModel) arbre.getModel();
	    
	    this.dataTree = modelJtree;
	    
		// JTree arbre = new JTree(parseur.parse());
		
		// Set<Capteur> capteurs = null;
		
		// verifierSalle(parseur.parse(), capteurs);
		
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
	
	
	private void insertNode(String IdentifiantCapteur,Localisation locCapteur,DefaultMutableTreeNode node)
	{
		
		for (int i = 0; i < node.getChildCount(); i++)
		{
			
			System.out.println("Node :"+node.getChildAt(i).toString());
			
			if (node.getChildAt(i).isLeaf())
			{
				System.out.println("Leaf :"+node.getChildAt(i));
			}
				
			else 
			{
				insertNode(IdentifiantCapteur,locCapteur,(DefaultMutableTreeNode)node.getChildAt(i));
			}
				
			/*
			if( node.getChildAt(i).toString().equals(arg0) )
			{	
				
			}
			

			*/
		}
	}
	
	/**
	 * Ajout dans le JTree le capteur voulus
	 * 
	 * @param IdentifiantCapteur
	 * @param locCapteur localisation du capteur a ajouter
	 */
	public void newCapteur(String IdentifiantCapteur,Localisation locCapteur)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) dataTree.getRoot();
		
		insertNode(IdentifiantCapteur, locCapteur, root);
		
		/*
		if( locCapteur instanceof LocalisationInt)
		{
			while( notfound )
			{
				if()
				{
					
				}
			}
			dataTree.insertNodeInto(new DefaultMutableTreeNode("another_child"), root, root.getChildCount());
		}
		else
		{
			while( notfound )
			{
				if()
				{
					
				}
			}
			dataTree.insertNodeInto(new DefaultMutableTreeNode("another_child"), root, root.getChildCount());
		}
		*/
	}
	
	/**
	 * Supprime du JTree le capteur voulus
	 * 
	 * @param IdentifiantCapteur
	 */
	public void supprCapteur(String IdentifiantCapteur)
	{
		// TODO
	}
	
	/**
	 * 
	 * Verifie si une salle contient un capteur et l'affiche en consequence
	 * 
	 */
	private void verifierSalle(DefaultMutableTreeNode arbre, Set<Capteur> capteurs)
	{
		// TODO
		System.out.println(arbre);
		// checker les fils r�cursivement
		// v�rifier qu'il n'y a pas de capteurs avec les set
		// si pas de capteur ni de fils, enlever de l'arbre
	}
	
	public String[] getSelected()
	{
		// TODO
		return null;
	}
}
