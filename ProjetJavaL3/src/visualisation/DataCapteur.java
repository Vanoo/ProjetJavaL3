package visualisation;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * AbstractTableModel contenant des capteurs
 * 
 */
public class DataCapteur extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
    private final ArrayList<Capteur> capteurs = new ArrayList<Capteur>();
    
    private final String[] headers = {"Identifiant", "Type", "Localisation", "Valeur"};

    public DataCapteur()
    {
        super();
    }

    /**
     * Add a Capteur to attributes capteurs
     * and call fireTableDataChange
     *  
     * @param capteur
     */
    public void addCapteur(Capteur capteur) 
    {
    	capteurs.add(capteur);
    	fireTableDataChanged();
    }

    /**
     * Remove a Capteur to attributes capteurs
     * and call fireTableDataChange
     * 
     * @param cap the Capteur to be removed
     */
    public void removeCapteur(Capteur cap) 
    {
    	capteurs.remove(cap);
        fireTableDataChanged();
    }
    
    /**
     * Remove all Capteur from attributes capteurs
     * and call fireTableDataChange
     */
    public void removeAll()
    {
    	capteurs.clear();
    	fireTableDataChanged();
    }
    
    /**
     * Return number of capteur in attributes capteurs
     * @return numberRow
     */
    public int getRowCount() 
    {
        return capteurs.size();
    }

    /**
     * Return size of attributes headers
     * @return numberColumn
     */
    public int getColumnCount() 
    {
        return headers.length;
    }

    /**
     * Return ColumnName for columnIndex
     * 
     * @return ColumnName
     */
    public String getColumnName(int columnIndex) 
    {
        return headers[columnIndex];
    }

    /**
     * Return object at rowIndex/columnIndex
     *  
     * @return Object
     */
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch(columnIndex)
        {
            case 0:
                return capteurs.get(rowIndex).getId();
            case 1:
                return capteurs.get(rowIndex).getType();
            case 2:
                return capteurs.get(rowIndex).getLoc().toString();
            case 3:
            	return capteurs.get(rowIndex).getValeur();
            default:
                return null; // Erreur
        }
    }
    
    /**
     * Change the value of a Capteur in attributes capteurs
     * and fireTableDataChanged
     * 
     * @param idCapteur the Capteur id of whom value will be change
     * @param valeur the new value
     */
    public void changeValue(String idCapteur,double valeur) 
    {
    	Iterator<Capteur> iter = capteurs.iterator();
    	Capteur cap;
    	
    	for(int i=0; iter.hasNext();i++)
    	{
    		cap = iter.next();
    		if( cap.getId().equals(idCapteur))
    		{
    			capteurs.get(i).setValeur(valeur);
    			break;
    		}
    	}
    	fireTableDataChanged();
    }
}
