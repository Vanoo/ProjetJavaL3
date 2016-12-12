package simulation;

import java.text.ParseException;

public class Simulation {

	/**
	 * @Javadoc
	 * Création d'une instance de SimWindows, la classe principale.
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException 
	{
		SimWindows fenetre = new SimWindows();
		fenetre.isActive();
	}

}
