package visualisation;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class DataCapteur extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	// TODO Initialisation d'un set capteur ou list
    private final ArrayList<Capteur> capteurs = new ArrayList<Capteur>();
    
    private final String[] headers = {"Identifiant", "Type", "Localisation", "Valeur"};

    public DataCapteur() 
    {
        super();
    }

    /**
     * Add a Capteur to this class
     * 
     * @param capteur
     */
    public void addCapteur(Capteur capteur) 
    {
    	capteurs.add(capteur);

        fireTableRowsInserted(capteurs.size() -1, capteurs.size() -1);
    }

    /**
     * Remove a Capteur, notify the correspondant tables
     * 
     * @param cap the Capteur to be removed
     */
    public void removeCapteur(Capteur cap) 
    {
    	capteurs.remove(cap);
        // fireTableRowsDeleted(rowIndex, rowIndex);
        fireTableDataChanged();
    }
    
    /**
     * Remove all Capteur from this class
     */
    public void removeAll()
    {
    	capteurs.clear();
    	fireTableDataChanged();
    }
    
    /**
     * Return how many Capteur are stored
     */
    public int getRowCount() 
    {
        return capteurs.size();
    }

    /**
     * 
     */
    public int getColumnCount() 
    {
        return headers.length;
    }

    public String getColumnName(int columnIndex) 
    {
        return headers[columnIndex];
    }

    /**
     * 
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
                return null; //Must never happens
        }
    }
    
    /**
     * Change the value of a Capteur
     * 
     * @param idCapteur the Capteur of whom value will be change
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