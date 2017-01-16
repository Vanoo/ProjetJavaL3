package visualisation;

/**
 * 
 *	Extends Localisation
 *	Four String Attribute
 *	getter on each
 *
 */
public class LocalisationInt extends Localisation
{

	String batiment;
	String salle;
	String etage;
	String commentaire;
	
	/**
	 * Constructor set all attribute with given String
	 * 
	 * @param batiment
	 * @param etage
	 * @param salle
	 * @param commentaire
	 */
	public LocalisationInt(String batiment,String etage,String salle,String commentaire)
	{
		this.batiment = batiment;
		this.salle = salle;
		this.etage = etage;
		this.commentaire = commentaire;
	}
	
	/**
	 * 	Getter on Batiment attribute
	 * 	Retourne un String d'un bâtiment.  
	 */
	public String getBatiment() 
	{
		return batiment;
	}
	
	/**
	 * 	Getter on salle attribute
	 * 	Retourne un String d'une salle.  
	 */
	public String getSalle() 
	{
		return salle;
	}
	
	/**
	 * 	Getter on etage attribute
	 * 	Retourne un String d'un étage.  
	 */
	public String getEtage() 
	{
		return etage;
	}
	
	/**
	 * 	Getter on commentaire attribute
	 * 	Retourne un String d'un commentaire.  
	 */
	public String getCommentaire() 
	{
		return commentaire;
	}
	
	/**
	 * Retourne un String qui represente l objet LocalisationInt
	 * le string contient les attributs de l objet
	 * @return String
	 */
	public String toString()
	{
		return this.batiment+" "+this.etage+" "+this.salle+" "+this.commentaire;
	}
}
