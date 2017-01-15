package visualisation;
import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

   public DefaultMutableTreeNode parse () 
   {
	   
	   DefaultMutableTreeNode root = null;
      // On recupere une instance de factory qui se chargera de nous fournir un parseur
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setIgnoringElementContentWhitespace(true);
      try 
      {
         // Creation de notre parseur via une factory
         DocumentBuilder builder = factory.newDocumentBuilder();
         File fileXML = new File("./config.xml");

         // parsing de notre fichier via un objet File et recuperation d'un objet Document
         Document xml = builder.parse(fileXML);
         
         // Via notre objet Document, nous pouvons recuperer un objet Element
         // Ce dernier represente un element XML mais, avec la methode ci-dessous,
         // cet element sera la racine du document
         Element racine = xml.getDocumentElement();
         System.out.println(racine.getNodeName());
         
         root = new DefaultMutableTreeNode(racine.getNodeName());
         
         createJTree(racine, root);

      }
      catch (ParserConfigurationException e)
      {
         e.printStackTrace();
      }
      catch (SAXException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      
      /* Affichage de test
      JFrame fenetre = new JFrame();
      fenetre.setLocationRelativeTo(null);
      fenetre.setSize(300, 400);
      fenetre.add(new JScrollPane(new JTree(root)));
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setVisible(true);
      */
      
      return root;
      
   }
   
   /**
    * Methode qui va gerer le contenu d'un noeud
    * @param n
    * @param tab
    * @return
    */
   private static void createJTree(Node n, DefaultMutableTreeNode treeNode){
      
      if(n instanceof Element)
      {
    	  
    	// On recupere la liste des attributs
          NamedNodeMap att = n.getAttributes();
          int nbAtt = att.getLength();
         
		// On contrele la liste des attributs presents
		if(att != null && nbAtt > 0)
		{
			// On parcours tous les noeuds
			int j = 0;
			do
			{
				Node noeud = att.item(j);
				//On recupere le nom de l'attribut et sa valeur
				DefaultMutableTreeNode attribut = new DefaultMutableTreeNode (noeud.getNodeName() + "=" + noeud.getNodeValue());
				treeNode.add(attribut);
				j++;
			} while(j < nbAtt);
		}
        
         // On traite les enfants du noeud en cours
         int nbChild = n.getChildNodes().getLength();
         NodeList list = n.getChildNodes();
         
         // en parcourant la liste des noeuds
         for(int i = 0; i < nbChild; i++)
         {
            Node n2 = list.item(i);
            
            //si le noeud enfant est un Element, nous le traitons
            if (n2 instanceof Element)
            {
               System.out.println("VALUE : " + n2.getNodeValue() + " " + n2.getNodeName());
               DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(n2.getNodeName());
              
               //Pour afficher les valeurs des noeuds
               if(n2.getTextContent() != null && n2.getTextContent().trim() != "" && n2.getChildNodes().getLength() == 1)
               {
                  DefaultMutableTreeNode value = new DefaultMutableTreeNode(n2.getTextContent());
                  noeud.add(value);
               }    
               //appel recursif a la methode pour le traitement du noeud et de ses enfants 
               createJTree(n2, noeud);
               treeNode.add(noeud);
            }            
         }
      }
   }  
}
