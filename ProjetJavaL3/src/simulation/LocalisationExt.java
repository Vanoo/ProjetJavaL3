package simulation;

/**
 *	@Javadoc
 *	Classe qui gère les coordonnées GPS, cette classe hérite de la classe Localisation.
 *
 */

public class LocalisationExt extends Localisation
{
	
		/**
		 *  Variable double latitude
		 */
		double latitude;
		/**
		 *  Variable double longitude
		 */
		double longitude;
		
		/**
		 * Constructeur
		 * @param latitude
		 * @param longitude
		 */
		public  LocalisationExt(double latitude,double longitude)
		{
			this.latitude = latitude;
			this.longitude = longitude;
		}

		/**
		 * @return double latitue
		 */
		public double getLatitude() 
		{
			return latitude;
		}

		/**
		 * @return double longitude
		 */
		public double getLongitude() 
		{
			return longitude;
		}
}