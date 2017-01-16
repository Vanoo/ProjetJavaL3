package visualisation;

/**
 * 
 *	Extends Localisation
 *	Double attribute latitude and longitude
 *	getter on attribute and toString
 *
 */
public class LocalisationExt extends Localisation
{
	
		double latitude;
		double longitude;
		
		/**
		 * Constructor set attribute latitude and longitude with given double
		 * 
		 * @param latitude double
		 * @param longitude double
		 */
		public  LocalisationExt(double latitude,double longitude)
		{
			this.latitude = latitude;
			this.longitude = longitude;
		}

		/**
		 * Getter on latitude attribute
		 * 
		 * @return latitude
		 */
		public double getLatitude() 
		{
			return latitude;
		}

		/**
		 * Getter on longitude attribute
		 * @return longitude
		 */
		public double getLongitude() 
		{
			return longitude;
		}
		
		/**
		 * Retourne un String qui represente l objet LocalisationExt
		 * le string contient les attributs de l objet
		 * @return String
		 */
		public String toString()
		{
			return this.latitude+"/"+this.longitude;
		}
}
