package visualisation;

public class Capteur {
	
	private String id;
	private String type;
	private visualisation.Localisation loc;
	
	/**
	 * Constructeur pour un capteur d'exterieur
	 * 
	 * @param id identifiant du capteur
	 * @param type type de mesure du capteur
	 * @param latitude latitude du capteur
	 * @param longitude longitude du capteur
	 */
	public Capteur(String id, String type, String latitude, String longitude)
	{
		this.id = id;
		this.type = type;
		this.loc = new visualisation.LocalisationExt(Double.parseDouble(latitude), Double.parseDouble(longitude)); 
	}
	
	/**
	 * Constructeur pour un capteur d'interieur
	 * 
	 * @param id identifiant du capteur
	 * @param type type de mesure du capteur
	 * @param batiment batiment dans lequel est situe le capteur 
	 * @param etage etage dans lequel est situe le capteur
	 * @param salle salle dans laquelle est situe le capteur
	 * @param positionrelative commentaire pour situer le capteur dans la salle
	 */
	public Capteur(String id, String type, String batiment, String etage, String salle, String positionrelative)
	{
		this.id = id;
		this.type = type;
		this.loc = new visualisation.LocalisationInt(batiment, Integer.parseInt(etage), salle, positionrelative);
	}
	
	/**
	 * 
	 * @return identifiant du capteur
	 */
	public String getId () 
	{
		return this.id;
	}
	
	public visualisation.Localisation getLoc()
	{
		return this.loc;
	}
}
