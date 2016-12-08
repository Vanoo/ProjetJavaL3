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
	
	Connection_panel connection_panel;
	JPanel infoCapteur_panel;
	JPanel donnee_panel;
	
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
	    
	    
	    /*============= Ajout des JPanel dans la fenetre =============*/
	    content_panel.setLayout(new BorderLayout(0,0));
	    content_panel.add(this.connection_panel, BorderLayout.NORTH);
	    content_panel.add(this.donnee_panel, BorderLayout.CENTER);
	    content_panel.add(this.infoCapteur_panel, BorderLayout.EAST);
	    
	    this.getContentPane().add(content_panel);
	    pack();
	}

}
