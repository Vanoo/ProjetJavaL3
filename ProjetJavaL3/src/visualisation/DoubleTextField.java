package visualisation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * 
 * Custom JTextField with Special PatternFilter for input set in the constructor
 *
 */
public class DoubleTextField extends JTextField
{	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new DoubleTextField with special pattern and size
	 * 
	 * @param taille taille du TextField voulu
	 * @param pattern le pattern a respecter pour les inputs
	 */
	public DoubleTextField(int taille,String pattern)
	{
		super(taille);
		
		AbstractDocument doc = (AbstractDocument) this.getDocument();
		doc.setDocumentFilter(new PatternFilter(pattern));
	}
	
	/**
	 * Getter qui renvoi la valeur entrer dans le textField sous forme de double
	 * si saisi vide renvoi 0
	 * 
	 * @return double
	 */
	public double getValeur()
	{	
		if( getText().length() == 0 )
		{
			return 0;
		}
		
	    return Double.parseDouble(getText());
	}

	/**
	 * 
	 * Special DocumentFilter, filter with a pattern define in this class
	 *
	 */
	class PatternFilter extends DocumentFilter 
	{
		private Pattern pattern;
		
		public PatternFilter(String pat) 
		{
		    pattern = Pattern.compile(pat);
		}
		
		/**
		 * 
		 * insertString if the the string match the pattern
		 * 
		 */
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException 
        {
		    String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
		    
		    Matcher m = pattern.matcher(newStr);
		    if (m.matches()) 
		    {
		        super.insertString(fb, offset, string, attr);
		    }
		}
		
		/**
		 * 
		 * 
		 * 
		 */
		@Override
		public void replace(FilterBypass fb, int offset,int length, String string, AttributeSet attr) throws BadLocationException 
		{
		    if (length > 0) 
	    	{
	    		fb.remove(offset, length);
	    	}
		    insertString(fb, offset, string, attr);
		}
	}
}
