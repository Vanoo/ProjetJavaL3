package simulation;

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
