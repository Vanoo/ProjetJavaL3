package simulation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/* TODO 
 * Positionnement
 * Lecture Fichier
 * Communication Serveur
 */
public class SimWindows extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	JPanel fenetre;
	
	Reseaux res;
	Connection_panel connection_panel;
	InfoCapteur_panel infoCapteur_panel;
	Donnee_panel donnee_panel;
	
	Timer envoiData;
	
	public SimWindows() throws ParseException
	{
		setResizable(false);
		
		/*============= Initialisation de la fenetre =============*/
		this.setTitle("Simulation Capteur");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    this.setPreferredSize(new Dimension(500,600));
	    
	    /*============= Initialisation des JPanel =============*/
	    JPanel content_panel = new JPanel();
	    
	    this.connection_panel = new Connection_panel();
	    this.infoCapteur_panel = new InfoCapteur_panel();
	    this.donnee_panel = null;
	    /*============= On grise les elements de la fenetre =============*/
	    // fifty_shade_of_gray(true);
	    
	    /*============= Ajout actionListener sur bouton =============*/
	    boutonConnection();
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    content_panel.setLayout(new BorderLayout(0,0));
	    content_panel.add(this.connection_panel, BorderLayout.NORTH);
	    // content_panel.add(this.donnee_panel, BorderLayout.CENTER);
	    content_panel.add(this.infoCapteur_panel, BorderLayout.EAST);
	    
	    this.fenetre = content_panel;
	    this.getContentPane().add(content_panel);
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
	}
	
	public void arretEnvoi()
	{
		this.envoiData.stop();
	}
	
	public void creationDonneePanel()
	{
		// recup intervalle min et max
		Intervalle interv = this.infoCapteur_panel.getInfoIntervalle();
		
		this.donnee_panel = new Donnee_panel(interv.getMin(),interv.getMax());
		this.fenetre.add(donnee_panel,BorderLayout.WEST);
		remove(this.getContentPane());
		this.getContentPane().add(fenetre);
		
		revalidate();
		repaint();
		pack();
	}
	
	public void suppresionDonneePanel()
	{
		// TODO
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
						creationDonneePanel();
						// connection success ou fail
						changementBouton(1);
						System.out.printf("CHANGEMENT BOUTON");
						// Grise tous les saisie utilisateurs
						// 
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
						suppresionDonneePanel();
						changementBouton(0);
						// fifty_shade_of_gray(true);
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
}
