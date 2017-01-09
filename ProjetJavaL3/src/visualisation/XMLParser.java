package visualisation;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
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

   public static DefaultMutableTreeNode parse () {
	   
	   DefaultMutableTreeNode root = null;
      // On r�cup�re une instance de factory qui se chargera de nous fournir un parseur
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setIgnoringElementContentWhitespace(true);
      try 
      {
         // Cr�ation de notre parseur via une factory
         DocumentBuilder builder = factory.newDocumentBuilder();
         File fileXML = new File("./config.xml");

         // parsing de notre fichier via un objet File et r�cup�ration d'un objet Document
         Document xml = builder.parse(fileXML);
         
         // Via notre objet Document, nous pouvons r�cup�rer un objet Element
         // Ce dernier repr�sente un �l�ment XML mais, avec la m�thode ci-dessous,
         // cet �l�ment sera la racine du document
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
    * M�thode qui va g�rer le contenu d'un noeud
    * @param n
    * @param tab
    * @return
    */
   public static void createJTree(Node n, DefaultMutableTreeNode treeNode){
      
      if(n instanceof Element)
      {
         
         // On contr�le la liste des attributs pr�sents
         if(n.getAttributes() != null && n.getAttributes().getLength() > 0)
         {
            
            // On r�cup�re la liste des attributs
            NamedNodeMap att = n.getAttributes();
            int nbAtt = att.getLength();
            
            // On parcours tous les noeuds
            for(int j = 0; j < nbAtt; j++)
            {
               Node noeud = att.item(j);
               //On r�cup�re le nom de l'attribut et sa valeur
               DefaultMutableTreeNode attribut = new DefaultMutableTreeNode ("ATTRIBUT : " + noeud.getNodeName() + "=" + noeud.getNodeValue());
               treeNode.add(attribut);
            }
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
                  DefaultMutableTreeNode value = new DefaultMutableTreeNode("VALEUR : " + n2.getTextContent());
                  noeud.add(value);
               }    
               //appel r�cursif � la m�thode pour le traitement du noeud et de ses enfants 
               createJTree(n2, noeud);
               treeNode.add(noeud);
            }            
         }
      }
   }  
}