package simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;



/* TODO 
 * Positionnement
 * Lecture Fichier
 * Gestion Event
 * Communication Serveur
 */
public class SimWindows extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Reseaux res;
	Connection_panel connection_panel;
	InfoCapteur_panel infoCapteur_panel;
	Donnee_panel donnee_panel;
	
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
	    
	    preminilary_gray(false);
	    mouahahah();
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    content_panel.setLayout(new BorderLayout(0,0));
	    content_panel.add(this.connection_panel, BorderLayout.NORTH);
	    content_panel.add(this.donnee_panel, BorderLayout.CENTER);
	    content_panel.add(this.infoCapteur_panel, BorderLayout.EAST);
	    
	    this.getContentPane().add(content_panel);
	    pack();
	}
	
	public void mouahahah()
	{
		this.connection_panel.getConnection_button().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				getData();
				// Vérifie info
				// établit connection
				
					// connection success ou fail
				// Grise tous les saisie utilisateurs
				fifty_shade_of_gray(false);
				preminilary_gray(true);
				// Affichage bouton déconnection a la place du bouton connection
				// envoi donnée recu de donnee panel
				//JOptionPane.showMessageDialog(,ip_textField.getValue());
			}
		});
	}
	
	public void getData()
	{	
		//default title and icon
		JOptionPane.showMessageDialog(this,
		    "IP :"+this.connection_panel.getIp_textField().getValue()+"Port :"+this.connection_panel.getPort_textField().getValue()+"");
		int port = 8888;
		String ip = this.connection_panel.getIp_textField().getValue().toString();
		this.res = new Reseaux(ip,port);
		
	}
	
	public void preminilary_gray(boolean bool)
	{
		Component tab_component[];

		tab_component=this.donnee_panel.getComponents();
		for( int i=0;i<tab_component.length;i++ )
		{
			tab_component[i].setEnabled(bool);
		}
	}
	
	public void fifty_shade_of_gray(boolean bool)
	{		
		Component tab_component[];

		tab_component=this.connection_panel.getComponents();
		for( int i=0;i<tab_component.length;i++ )
		{
			tab_component[i].setEnabled(bool);
		}
		tab_component=this.infoCapteur_panel.getComponents();
		for( int i=0;i<tab_component.length;i++ )
		{
			tab_component[i].setEnabled(bool);
		}
		tab_component=this.infoCapteur_panel.getMin_panel().getComponents();
		for(int i=0;i<tab_component.length;i++)
		{
			tab_component[i].setEnabled(bool);
		}
		tab_component=this.infoCapteur_panel.getMax_panel().getComponents();
		for(int i=0;i<tab_component.length;i++)
		{
			tab_component[i].setEnabled(bool);
		}
		if(this.infoCapteur_panel.isExter())
		{
			tab_component=this.infoCapteur_panel.getExterieur().getComponents();
			for(int i=0;i<tab_component.length;i++)
			{
				tab_component[i].setEnabled(bool);
			}
		}
		if(this.infoCapteur_panel.isIntern())
		{
			tab_component=this.infoCapteur_panel.getInterieur().getComponents();
			for(int i=0;i<tab_component.length;i++)
			{
				tab_component[i].setEnabled(bool);
			}
		}
	}

}
