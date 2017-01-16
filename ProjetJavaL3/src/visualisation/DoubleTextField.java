package visualisation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DoubleTextField extends JTextField
{	
	private static final long serialVersionUID = 1L;
	
	public DoubleTextField(int taille,String pattern)
	{
		super(taille);
		
		AbstractDocument doc = (AbstractDocument) this.getDocument();
		
		doc.setDocumentFilter(new PatternFilter(pattern));
	}
	
	public double getValeur()
	{	
		if( getText().length() == 0 )
		{
			return 0;
		}
		
	    return Double.parseDouble(getText());
	}

	class PatternFilter extends DocumentFilter 
	{
		private Pattern pattern;
		
		public PatternFilter(String pat) 
		{
		    pattern = Pattern.compile(pat);
		}
		
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException 
        {
		    String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
		    
		    Matcher m = pattern.matcher(newStr);
		    if (m.matches()) 
		    {
		        super.insertString(fb, offset, string, attr);
		    }
		}
		
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
