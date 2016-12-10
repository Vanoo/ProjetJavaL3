package simulation;

public class LocalisationInt extends Localisation
{

	String batiment;
	String salle;
	int etage;
	String commentaire;
	
	public LocalisationInt(String batiment,String salle,int etage,String commentaire)
	{
		this.batiment = batiment;
		this.salle = salle;
		this.etage = etage;
		this.commentaire = commentaire;
	}

	public String getBatiment() 
	{
		return batiment;
	}

	public String getSalle() 
	{
		return salle;
	}

	public int getEtage() 
	{
		return etage;
	}

	public String getCommentaire() 
	{
		return commentaire;
	}
}
