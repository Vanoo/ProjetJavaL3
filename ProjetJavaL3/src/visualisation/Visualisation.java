package visualisation;

import java.awt.Dimension;
import java.text.ParseException;

import simulation.SimWindows;

public class Visualisation {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException 
	{
		VisWindows fenetre = new VisWindows(new Dimension(500,600));
		fenetre.isActive();
	}

}
