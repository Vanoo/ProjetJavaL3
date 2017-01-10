package visualisation;

public class Capteur {
	
	String id;
	String type;
	visualisation.Localisation loc;
	
	public Capteur(String id, String type, String latitude, String longitude)
	{
		this.id = id;
		this.type = type;
		this.loc = new visualisation.LocalisationExt(Double.parseDouble(latitude), Double.parseDouble(longitude)); 
	}
	
	public Capteur(String id, String type, String batiment, String etage, String salle, String positionrelative)
	{
		this.id = id;
		this.type = type;
		this.loc = new visualisation.LocalisationInt(batiment, Integer.parseInt(etage), salle, positionrelative);
	}
	
}
