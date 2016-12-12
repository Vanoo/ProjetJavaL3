package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * @Javadoc
 * Classe centrale qui regroupe et met en application les autres classes.
 */
public class SimWindows extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	static Dimension dim = new Dimension(500,600);
	
	JPanel fenetre;
	
	Reseaux res;
	Connection_panel connection_panel;
	InfoCapteur_panel infoCapteur_panel;
	Donnee_panel donnee_panel;
	
	Timer envoiData;
	
	public SimWindows() throws ParseException
	{		
		/*============= Initialisation de la fenetre =============*/
		this.setTitle("Simulation Capteur");
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
	    fenetre = new JPanel();
	    fenetre.setPreferredSize(dim);
	    fenetre.setMinimumSize(dim);
	    fenetre.setMaximumSize(dim);

	    this.connection_panel = new Connection_panel();
	    this.infoCapteur_panel = new InfoCapteur_panel();
	    this.donnee_panel = new Donnee_panel(0, 100);
	    
	    /*============= On grise les elements de la fenetre =============*/
	    // fifty_shade_of_gray(true);
	    
	    /*============= Ajout actionListener sur bouton =============*/
	    boutonConnection();
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    fenetre.add(connection_panel);
	    fenetre.add(infoCapteur_panel);
	    fenetre.add(donnee_panel);
	    fenetre.setBackground(Color.lightGray);
	    this.add(fenetre);
	    pack();
	}
	
	public void sendData()
	{	
		this.envoiData.setDelay(this.donnee_panel.getDelay());
		this.res.sendData(this.donnee_panel.getValeur());
	}

	public void lancementEnvoi()
	{
		//this.envoiData = new EnvoiData(1,4.4,"envoi",res);
		//envoiData.start();
		int delay = this.donnee_panel.getDelay(); // millisecond
		System.out.println("Delay : "+delay);
		ActionListener taskPerformer = new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
			    sendData();
			}
		};
		this.envoiData = new Timer(delay, taskPerformer);
		this.envoiData.start();
		
		fifty_shade_of_gray(false,this.fenetre);
	}
	
	public void arretEnvoi()
	{
		this.envoiData.stop();
		fifty_shade_of_gray(true,this.fenetre);
	}
	
	public void changementPanel()
	{
		int min = this.infoCapteur_panel.getInfoMin();
		int max = this.infoCapteur_panel.getInfoMax();
		this.donnee_panel.changementIntervalle(min,max);
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
						changementPanel();
						// connection success ou fail
						changementBouton(1);
						System.out.printf("CHANGEMENT BOUTON");
						lancementEnvoi();
						// Affichage bouton déconnection a la place du bouton connection
						// envoi donnée recu de donnee panel
						//JOptionPane.showMessageDialog(,ip_textField.getValue());
					}
				}
				else
				{
					arretEnvoi();
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
		
		this.res = new Reseaux();
		
		String ip;
		int port;
		
		ip = this.connection_panel.getIp();
		port = this.connection_panel.getPort();
		
		if( this.res.connection(ip, port) == false )
		{
			return false;
		}
		
		System.out.printf("Plop 1\n");
		System.out.printf("Plop 2\n");
		Localisation loc = this.infoCapteur_panel.getInfoLocalisation();
		System.out.printf("Plop 3\n");
		String id = this.infoCapteur_panel.getInfoId();
		String type = this.infoCapteur_panel.getInfoType();
		System.out.printf("Plop 4\n");
		if(  loc instanceof LocalisationInt )
		{
			System.out.printf("Plop 5\n");
			success = this.res.connexionInt(id,type,
					((LocalisationInt) loc).getBatiment(),((LocalisationInt) loc).getEtage(),((LocalisationInt) loc).getSalle(),((LocalisationInt) loc).getCommentaire());
		}
		else
		{
			System.out.printf("Plop 6\n");
			success = this.res.connexionExt(id, type,((LocalisationExt) loc).getLatitude(), ((LocalisationExt) loc).getLongitude());
		}
		System.out.printf("fin interne");
		return success;
	}
	
	public boolean deconnection()
	{
		boolean success = false;
		String id = "Temperature";
		success = this.res.deconnexion(id);
		return success;
	}
	
	public void fifty_shade_of_gray(boolean bool,JPanel panel)
	{
		panel.setEnabled(bool);
		Component tab_component[] = panel.getComponents();
		
		for(int i = 0; i < tab_component.length; i++) 
	    {
	        if(tab_component[i] instanceof JPanel ) 
	        {
	        	fifty_shade_of_gray(bool,(JPanel) tab_component[i]);
	        }

	        tab_component[i].setEnabled(bool);
	    }
		
		
	}	
}
