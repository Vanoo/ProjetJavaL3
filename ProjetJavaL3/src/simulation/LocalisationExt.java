package simulation;

/**
 *	@Javadoc
 *	Classe qui gère les coordonnées GPS, cette classe hérite dde la classe Localisation.
 *
 */

public class LocalisationExt extends Localisation
{
	
		double latitude;
		double longitude;
		
		public  LocalisationExt(double latitude,double longitude)
		{
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public double getLatitude() 
		{
			return latitude;
		}

		public double getLongitude() 
		{
			return longitude;
		}
}
