package visualisation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class DoubleTextField extends JTextField
{	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Document createDefaultModel()
	{
	    return new NumericDocument();
	}
	 
	private static class NumericDocument extends PlainDocument
	{
		 /**
		 * 
		 */
		
		
		private static final long serialVersionUID = 1L;
		// The regular expression to match input against (zero or more digits)
		private final static Pattern DIGITS = Pattern.compile("\\d\\d\\d\\[.]");
		
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
		{
			// Only insert the text if it matches the regular expression
			System.out.println("off :"+offs);
			System.out.println("Char : ["+str+"]");
			
			
			if( str.equals("-") )
			{
				if( offs == 0 )
				{
					super.insertString(offs, str, a);
				}
			}
			else
			{
				if (str != null && DIGITS.matcher(str).matches() || true)
				{
					super.insertString(offs, str, a);
				}
			}
			
			
		}
		
		public void replace(int offs, String str, AttributeSet a) throws BadLocationException
		{
			System.out.println("off :"+offs);
			System.out.println("Char : ["+str+"]");
		}
	}

}
