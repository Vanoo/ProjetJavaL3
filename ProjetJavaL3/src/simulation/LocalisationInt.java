package simulation;

import simulation.Localisation;

/**
 *	@Javadoc
 *	Classe qui gère tous les éléments de la localisation en intérieur suivant le format bâtiment/étage/salle/position relative.
 *
 */

public class LocalisationInt extends Localisation
{

	/**
	 *  Variable String batiment
	 */
	String batiment;
	/**
	 *  Variable String salle
	 */
	String salle;
	/**
	 *  Variable String etage
	 */
	String etage;
	/**
	 *  Variable String commentaire
	 */
	String commentaire;
	
	/**
	 * Constructeur
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
	 * 	@Javadoc
	 * 	Retourne un String d'un bâtiment.  
	 */
	public String getBatiment() 
	{
		return batiment;
	}
	
	/**
	 * 	@Javadoc
	 * 	Retourne un String d'une salle.  
	 */
	public String getSalle() 
	{
		return salle;
	}
	
	/**
	 * 	@Javadoc
	 * 	Retourne un String d'un étage.  
	 */
	public String getEtage() 
	{
		return etage;
	}
	
	/**
	 * 	@Javadoc
	 * 	Retourne un String d'un commentaire.  
	 */
	public String getCommentaire() 
	{
		return commentaire;
	}
}