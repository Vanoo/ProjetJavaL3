package visualisation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import simulation.Connection_panel;
import simulation.Localisation;
import simulation.LocalisationExt;
import simulation.LocalisationInt;
import simulation.Reseaux;

public class VisWindows extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	Connection_panel connection_panel;
	Reseaux res;
	
	
	
	Dimension dim = new Dimension(500,600);
	
	public VisWindows() throws ParseException
	{		
		/*============= Initialisation de la fenetre =============*/
		this.setTitle("Visualisation Data");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
	    // this.setMinimumSize(new Dimension());
	    this.setVisible(true);
	    this.setResizable(false);
	    // this.setPreferredSize(dim);
	    this.setMinimumSize(dim);
	    this.setMaximumSize(dim);
	    this.setPreferredSize(dim);
	    
	    /*============= Initialisation des JPanel =============*/
	    this.connection_panel = new Connection_panel();
	    
	    
	    /*============= Ajout actionListener sur bouton =============*/
	    boutonConnection();
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    // fenetre.add(connection_panel);
	    this.add(connection_panel);
	    // this.add(fenetre);
	    pack();
	    
	    /*============= On grise les elements de la fenetre =============*/

	}
	
	
	public void boutonConnection()
	{
		this.connection_panel.getConnection_button().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean success;
				int boutonConnection = typeBouton();
				
				if( boutonConnection == 1  )
				{
					// Vérifie info
					// établit connection
					System.out.printf("Connection");
					success = connection();
					System.out.printf("Fin Connection");
					if( success )
					{
						// changementPanel();
						// connection success ou fail
						changementBouton(1);
						System.out.printf("CHANGEMENT BOUTON");
						lancementReception();
						// Affichage bouton déconnection a la place du bouton connection
						// envoi donnée recu de donnee panel
						//JOptionPane.showMessageDialog(,ip_textField.getValue());
					}
				}
				else
				{
					arretReception();
					// Deconnection
					success = deconnection();
					if( success )
					{
						changementBouton(0);
					}
				}
			}
		});
	}
	
	protected void lancementReception() {
		// TODO Auto-generated method stub
		
	}


	protected void arretReception() {
		// TODO Auto-generated method stub
		
	}


	public void changementBouton(int typeBouton)
	{
		if( typeBouton == 1 )
		{
			// CHangment en deco
			this.connection_panel.getConnection_button().setText("Deconnection");
		}
		else
		{
			// Changement en connection
			this.connection_panel.getConnection_button().setText("Connection");
		}
	}
	
	public int typeBouton()
	{
		String label = this.connection_panel.getConnection_button().getText();
		if( label.equals("Connection") )
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public boolean connection()
	{
		boolean success = false;
		
		this.res = new Reseaux();
		
		String ip;
		int port;
		
		ip = this.connection_panel.getIp();
		port = this.connection_panel.getPort();
		
		if( this.res.connection(ip, port) == false )
		{
			return false;
		}
		
		return success;
	}
	
	public boolean deconnection()
	{
		boolean success = false;
		String id = "Temperature";
		success = this.res.deconnexion(id);
		return success;
	}
}
