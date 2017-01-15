package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class Choix_capteur_panel extends JPanel
{
	/**
	 * 
	 */
	private JButton inscription_button;
	private JButton desinscription_button;
	
	private DefaultTreeModel dataTree;
	private JTree selectionTree;
	
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
	    this.selectionTree = arbre;	    
	    
	    selectionTree.addTreeSelectionListener(new TreeSelectionListener() 
	    {
	        public void valueChanged(TreeSelectionEvent e) 
	        {
	        	/*
	        	e.isAddedPath();
	        	String path = e.getPath().toString();
	        	String [] splittedString = path.split(";");
	        	
	        	DefaultMutableTreeNode root = (DefaultMutableTreeNode) dataTree.getRoot();
	        	
	        	modelSelecTree.addSelectionPath(paths[0]);
	        	
	        	System.out.println("Selection :"+path);
	        	*/
	        }
	    });
	    
	    DefaultTreeModel modelJtree = (DefaultTreeModel) arbre.getModel();
	    
	    cleanTree((DefaultMutableTreeNode) modelJtree.getRoot());
	    
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
	
	
	public void reset(ArrayList<Capteur> listCapteur)
	{
		Iterator<Capteur> iter = listCapteur.iterator();
		while(iter.hasNext())
		{
			modifListCapteur(iter.next(), 1);
		}
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
	
	/**
	 * Modifie l attribut dataTree
	 * Si ajout la methode rajoute le capteur dans dataTree
	 * Si suppresion retirer le capteur de dataTree 
	 * 
	 * @param cap le capteur a modifie ( ajouter ou supprime )
	 * @param typeModif si ajout(0) ou suppresion(1) 
	 * @return boolean indiquant le succes de l operation
	 */
	public boolean modifListCapteur(Capteur cap,int typeModif)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) dataTree.getRoot();
		DefaultMutableTreeNode nodeChild;
		DefaultMutableTreeNode capteurNode;
		String identifiantCapteur = cap.getId();
		
		if( cap.getLoc() instanceof LocalisationExt )
		{	
			nodeChild = (DefaultMutableTreeNode) root.getChildAt(1);
			
			// 0 -> Ajout     1 -> Suppr
			if( typeModif == 0 )
			{
				dataTree.insertNodeInto(new DefaultMutableTreeNode(identifiantCapteur), nodeChild, nodeChild.getChildCount());
				return true;
			}
			else
			{				
				for (int bat = 0; bat < nodeChild.getChildCount(); bat++)
				{
					capteurNode = (DefaultMutableTreeNode) nodeChild.getChildAt(bat);
					if( capteurNode.toString().equals(cap.getId()) )
					{
						dataTree.removeNodeFromParent(capteurNode);
						return true;
					}
				}
			}
		}
		else
		{			
			nodeChild =(DefaultMutableTreeNode) root.getChildAt(0);
			
			DefaultMutableTreeNode batiment;
			DefaultMutableTreeNode etage;
			DefaultMutableTreeNode salle;
			
			LocalisationInt locCapteurInt = (LocalisationInt) cap.getLoc();
			
			// System.out.println("Localisation Capteur "+locCapteur.toString());
			
			// System.out.println("Tentative Modif Capteur Interieur");
			// System.out.println("Capteur :"+cap.toString());
			
			for (int bat = 0; bat < nodeChild.getChildCount(); bat++)
			{
				batiment = (DefaultMutableTreeNode) nodeChild.getChildAt(bat);
				// System.out.println("Batiment :"+batiment.toString());
				
				// System.out.println("Recherche dans batiment : "+batiment.toString());
				
				if( batiment.toString().equals(locCapteurInt.getBatiment()))
				{
					// System.out.println("Batiment OK");
					for(int et = 0; et < batiment.getChildCount(); et++  )
					{
						etage = (DefaultMutableTreeNode) batiment.getChildAt(et);
						// System.out.println("Etage :"+etage.toString());
						
						// System.out.println("Recherche dans etage : "+etage.toString());
						
						if( etage.toString().equals(locCapteurInt.getEtage()))
						{
							// System.out.println("etage OK");
							for(int sal = 0; sal < etage.getChildCount(); sal++)
							{
								salle = (DefaultMutableTreeNode) etage.getChildAt(sal);
								// System.out.println("Salle :"+salle.toString());
								
								// System.out.println("Recherche dans salle : "+salle.toString());
								
								if( salle.toString().equals(locCapteurInt.getSalle()) )
								{
									// System.out.println("Salle OK");
									if( typeModif == 0 )
									{
										// System.out.println("Ajout Capteur");
										dataTree.insertNodeInto(new DefaultMutableTreeNode(identifiantCapteur), salle, salle.getChildCount());
										return true;
									}
									else
									{
										for (int capt = 0; capt < salle.getChildCount(); capt++)
										{
											// System.out.println("Tour : ");
											
											capteurNode = (DefaultMutableTreeNode) salle.getChildAt(capt);
											
											// System.out.println("Verification "+capt+" : "+capteurNode.toString());
											
											if( capteurNode.toString().equals(identifiantCapteur) )
											{
												// System.out.println("Suppresion Capteur");
												dataTree.removeNodeFromParent(capteurNode);
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Enleve les feuilles de l'abre donnee en parametre
	 * @param arbre a nettoyer
	 */
	private void cleanTree(DefaultMutableTreeNode root)
	{
		DefaultMutableTreeNode nodeChild;
		
		for (int bat = 0; bat < root.getChildCount(); bat++)
		{
			nodeChild = (DefaultMutableTreeNode) root.getChildAt(bat);
			
			if( nodeChild.isLeaf() )
			{
				root.removeAllChildren();
				break;
			}
			else
			{
				cleanTree(nodeChild);
			}
		}
	}
	
	/**
	 * Recupere les capteurs selectionne dans le JTree
	 * et renvoi leur identifiants dans un Arraylist
	 * @return Array d identifiants de capteurs
	 */
	public ArrayList<String> getSelected()
	{
		ArrayList<String> idCapteur = new ArrayList<String>();
		
		TreePath[] paths = selectionTree.getSelectionPaths();	
		String[] splittedString;
		
		
		for(int i=0; i < paths.length; i++)
		{
			splittedString = paths[i].toString().split(", ");
			
			if( splittedString.length == 3 )
			{
				idCapteur.add(splittedString[2].split("]")[0]);
			}
			else if( splittedString.length == 6 )
			{
				idCapteur.add(splittedString[5].split("]")[0]);
			}
		}		
		
		return idCapteur;
	}
	
	public String getPathSelected()
	{
		TreePath[] paths = selectionTree.getSelectionPaths();
		if( paths.length > 1)
		{
			return "Erreur";
		}
		else
		{
			return paths[0].toString();
		}
	}
}
