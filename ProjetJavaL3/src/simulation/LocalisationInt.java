package simulation;

/**
 *	@Javadoc
 *	Classe qui gère tous les éléments de la localisation en intérieur suivant le format bâtiment/étage/salle/position relative.
 *
 */

public class LocalisationInt extends Localisation
{

	String batiment;
	String salle;
	int etage;
	String commentaire;
	
	public LocalisationInt(String batiment,int etage,String salle,String commentaire)
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
	public int getEtage() 
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
