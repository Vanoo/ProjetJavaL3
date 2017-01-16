package visualisation;

/**
 * 
 * Class Capteur
 * Un capteur possède un identifiant(String),
 * un type (String),une localisation(Localisation) et une valeur(double)
 *
 */
public class Capteur 
{	
	private String id;
	private String type;
	private visualisation.Localisation loc;
	private double valeur;
	
	/**
	 * Constructeur pour un capteur d'exterieur
	 * par defaut valeur vaut 0.0
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
		this.valeur = 0.0;
	}
	
	/**
	 * Constructeur pour un capteur d'interieur
	 * par defaut valeur vaut 0.0
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
		this.loc = new visualisation.LocalisationInt(batiment, etage, salle, positionrelative);
		this.valeur = 0.0;
	}
	
	/**
	 * Getter sur l attribut id du capteur 
	 * @return identifiant du capteur
	 */
	public String getId () 
	{
		return this.id;
	}
	
	/**
	 * Getter sur l attribut type du capteur
	 * @return type du capteur
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Getter sur l attribut loc du capteur
	 * @return Localisation du capteur
	 */
	public visualisation.Localisation getLoc()
	{
		return this.loc;
	}
	
	/**
	 * Getter sur l attribut valeur du capteur
	 * @return Valeur du capteur
	 */
	public double getValeur()
	{
		return this.valeur;
	}
	
	/**
	 * Setter sur l attribut valeur du capteur
	 * @param la nouvelle valeur du capteur
	 */
	public void setValeur(double val)
	{
		this.valeur = val;
	}
	
	/**
	 * Retourne un String qui represente l objet capteur,
	 * le string contient les attributs du capteurs
	 */
	public String toString()
	{
		return this.id+this.type+this.loc.toString()+this.valeur;
	}
}
