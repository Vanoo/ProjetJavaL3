package visualisation;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class DataCapteur extends AbstractTableModel 
{
	// TODO Initialisation d'un set capteur ou list
    private final ArrayList<Capteur> capteurs = new ArrayList<Capteur>();
    
    private final String[] headers = {"Identifiant", "Type", "Localisation", "Valeur"};

    public DataCapteur() 
    {
        super();

        capteurs.add(new Capteur("Lampe1", "Luminosite","U1","2","S101",""));
        capteurs.add(new Capteur("Lampe2", "Luminosite","U1","2","S101",""));
	    capteurs.add(new Capteur("Lampe3", "Luminosite","U1","2","S102",""));
	    capteurs.add( new Capteur("Lampe4", "Luminosite","U1","2","S103",""));
    }

    public void addCapteur(Capteur capteur) 
    {
    	capteurs.add(capteur);

        fireTableRowsInserted(capteurs.size() -1, capteurs.size() -1);
    }

    public void removeCapteur(int rowIndex) 
    {
    	capteurs.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public int getRowCount() 
    {
        return capteurs.size();
    }

    public int getColumnCount() 
    {
        return headers.length;
    }

    public String getColumnName(int columnIndex) 
    {
        return headers[columnIndex];
    }

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