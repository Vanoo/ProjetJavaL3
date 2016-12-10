package simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;
import java.text.ParseException;
import java.time.Clock;

import javax.swing.*;
import javax.swing.text.MaskFormatter;


/* TODO 
 * Positionnement
 * Lecture Fichier
 * Communication Serveur
 */
public class SimWindows extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Reseaux res;
	Connection_panel connection_panel;
	InfoCapteur_panel infoCapteur_panel;
	Donnee_panel donnee_panel;
	
	EnvoiData envoiData;
	
	public void lancementEnvoi()
	{
		this.envoiData = new EnvoiData(1,4.4,"envoi",res);
		envoiData.start();
	}
	
	public SimWindows() throws ParseException
	{
		setResizable(false);
		
		/*============= Initialisation de la fenetre =============*/
		this.setTitle("Simulation Capteur");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    this.setSize(500, 600);
	    
	    /*============= Initialisation des JPanel =============*/
	    JPanel content_panel = new JPanel();
	    
	    this.connection_panel = new Connection_panel();
	    this.infoCapteur_panel = new InfoCapteur_panel();
	    this.donnee_panel = new Donnee_panel();
	    
	    /*============= On grise les elements de la fenetre =============*/
	    fifty_shade_of_gray(true);
	    
	    /*============= Ajout actionListener sur bouton =============*/
	    boutonConnection();
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    content_panel.setLayout(new BorderLayout(0,0));
	    content_panel.add(this.connection_panel, BorderLayout.NORTH);
	    content_panel.add(this.donnee_panel, BorderLayout.CENTER);
	    content_panel.add(this.infoCapteur_panel, BorderLayout.EAST);
	    
	    this.getContentPane().add(content_panel);
	    pack();
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
					success = connection();
					if( success )
					{
						// connection success ou fail
						changementBouton(1);
						// Grise tous les saisie utilisateurs
						fifty_shade_of_gray(false);
						
						lancementEnvoi();
						
						// Affichage bouton déconnection a la place du bouton connection
						// envoi donnée recu de donnee panel
						//JOptionPane.showMessageDialog(,ip_textField.getValue());
					}
				}
				else
				{
					// Deconnection
					success = deconnection();
					if( success )
					{
						changementBouton(0);
						fifty_shade_of_gray(true);
					}
				}
			}
		});
	}
	
	public void changementBouton(int typeBouton)
	{
		if( typeBouton == 1 )
		{
			// CHangment en deco
			this.connection_panel.connection_button.setText("Deconnection");
		}
		else
		{
			// Changement en connection
			this.connection_panel.connection_button.setText("Connection");
		}
	}
	
	public int typeBouton()
	{
		String label = this.connection_panel.connection_button.getText();
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
		
		this.res = new Reseaux("127.0.0.1", 7888);
		
		Intervalle inter = this.infoCapteur_panel.getInfoIntervalle();
		
		Localisation loc = this.infoCapteur_panel.getInfoLocalisation();
		
		String id = this.infoCapteur_panel.getInfoId();
		String type = this.infoCapteur_panel.getInfoType();
		
		if(  loc instanceof LocalisationInt )
		{
			success = this.res.connexionInt(id,type,
					((LocalisationInt) loc).getBatiment(),((LocalisationInt) loc).getEtage(),((LocalisationInt) loc).getSalle(),((LocalisationInt) loc).getCommentaire());
		}
		else
		{
			success = this.res.connexionExt(id, type,((LocalisationExt) loc).getLatitude(), ((LocalisationExt) loc).getLongitude());
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
	
	public void gray(Component tab_component[],boolean bool)
	{
		for(int i=0;i<tab_component.length;i++)
		{
			if( ! tab_component[i].equals(this.connection_panel.connection_button) )
			{
				tab_component[i].setEnabled(bool);
			}
		}
	}
	
	public void fifty_shade_of_gray(boolean bool)
	{				
		gray(this.connection_panel.getComponents(),bool);
		gray(this.infoCapteur_panel.getComponents(),bool);
		gray(this.infoCapteur_panel.getComponents(),bool);
		gray(this.infoCapteur_panel.getComponents(),bool);
		
		gray(this.donnee_panel.getComponents(),!bool);
	}
	
	public void sendData()
	{
		this.res.sendData(14.14);
	}

}
