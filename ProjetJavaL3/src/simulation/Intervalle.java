package simulation;
/**
 * 
 * @Javadoc
 * Classe qui gère les intervalles et empêche la saisie de mauvaises entrées.
 *
 */
public class Intervalle {

	/**
	 *  Variable int min
	 */
	int min;
	/**
	 *  Variable int max
	 */
	int max;
	
	/**
	 * Constructeur
	 * @param min
	 * @param max
	 */
	public Intervalle(int min,int max)
	{
		this.min = min;
		this.max = max;
	}

	/**
	 * @return int min
	 */
	public int getMin() 
	{
		return min;
	}

	/**
	 * @param min
	 */
	public void setMin(int min) 
	{
		this.min = min;
	}

	/**
	 * @return int max
	 */
	public int getMax() 
	{
		return max;
	}

	/**
	 * @param max
	 */
	public void setMax(int max) 
	{
		this.max = max;
	}
}