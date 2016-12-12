package simulation;
/**
 * 
 * @Javadoc
 * Classe qui gère les intervalles et empêche la saisie de mauvaises entrées.
 *
 */
public class Intervalle {

	int min;
	int max;
	
	public Intervalle(int min,int max)
	{
		this.min = min;
		this.max = max;
	}

	public int getMin() 
	{
		return min;
	}

	public void setMin(int min) 
	{
		this.min = min;
	}

	public int getMax() 
	{
		return max;
	}

	public void setMax(int max) 
	{
		this.max = max;
	}
	
	

}
