package simulation;

public class LocalisationExt extends Localisation
{
	
		int latitude;
		int longitude;
		
		public  LocalisationExt(int latitude,int longitude)
		{
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public int getLatitude() 
		{
			return latitude;
		}

		public int getLongitude() 
		{
			return longitude;
		}
}
