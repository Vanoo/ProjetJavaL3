package visualisation;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 	@Javadoc
 * 	XmlJTree class qui permet la création d'un JTree à partir d'un fichier XML
 */
public class XmlJTree extends JTree
{

	private static final long serialVersionUID = 1L;
	DefaultTreeModel dtModel=null;

    /**
     * XmlJTree constructor
     * @param filePath
     */
    public XmlJTree(String filePath)
    {
        if(filePath!=null)
        setPath(filePath);
    }

    public void setPath(String filePath)
    {
        Node root = null;
        /**
         *    "Parse" le fichier XML
         */
        try 
        {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filePath);
            root = (Node) doc.getDocumentElement();
        } 
        catch (IOException | ParserConfigurationException | SAXException ex) 
        {
            JOptionPane.showMessageDialog(null,"Can't parse file","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        /**
         *   Si un on obtient un résultat, met en place le model pour le JTree
         */
        if(root!=null)
        {
            dtModel= new DefaultTreeModel(builtTreeNode(root));
            this.setModel(dtModel);
        }
    }

    /**
     * @Javadoc
     * 	Constructeur d'arbre de noeud de manière récursive.
     * @param root
     * @return DefaultMutableTreeNode
     */
    private DefaultMutableTreeNode builtTreeNode(Node root)
    {
    	DefaultMutableTreeNode dmtNode;
        dmtNode = new DefaultMutableTreeNode(root.getNodeName());
        NodeList nodeList = root.getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) 
        {
        	Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
            {
            	if (tempNode.hasChildNodes()) 
            	{
                        // loop again if has child nodes
                        dmtNode.add(builtTreeNode(tempNode));
                }
            }
        }
        return dmtNode;
    }
}

