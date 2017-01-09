package visualisation;

import java.awt.Dimension;
import java.text.ParseException;

public class Visualisation {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException 
	{
		VisWindow fenetre = new VisWindow(new Dimension(500,700));
		fenetre.isActive();
	}

}
