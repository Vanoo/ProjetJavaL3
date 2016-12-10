package simulation;

public class EnvoiData extends Thread 
{

	int time;
	double valeur;
	Reseaux res;
	
	public EnvoiData(int time,double valeur,String name,Reseaux res)
	{
		super(name);
		this.time = time;
		this.res = res;
		this.valeur = valeur;
	}

	  public void run()
	  {
		  while( true )
		  {
			  this.res.sendData(valeur);
			  // sleep(this.time);
		  }
		 
	  }  

}
