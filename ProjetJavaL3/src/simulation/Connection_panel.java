package simulation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;

import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/** 
*	@Javadoc
*	Classe qui gère le "panel" de connexion.
*
*/
public class Connection_panel extends JPanel
{
	/**
	 *  Variable nécessaire au bon fonctionnement de la classe sous eclipse.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Variable JTextField
	 */
	private JFormattedTextField ip_textField;
	/**
	 * Variable JTextField 2
	 */
	private JFormattedTextField port_textField;
	/**
	 *  Variable JButton
	 */
	private JButton connection_button;
	
	/**
	 * @throws ParseException
	 */
	public Connection_panel() throws ParseException
	{
		/*=============  OptionPanelConnexion =============*/
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		this.setPreferredSize(new Dimension(500,50));
		
		/*=============  Titre Panel =============*/
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		title.setBackground(Color.gray);
		title.setPreferredSize(new Dimension(500,15));
		JLabel title_label = new JLabel("################## Information Serveur ##################");
		title.add(title_label);
		
		/*=============  InteractifPanel =============*/
		
		/* IP */
		JLabel ip_label = new JLabel("IP :");
		MaskFormatter ip_formatter;
		ip_formatter = new MaskFormatter("###.###.###.###");
		ip_formatter.setPlaceholderCharacter('0');
		this.ip_textField = new JFormattedTextField(ip_formatter);
		
		/* Port */
		JLabel port_label = new JLabel("Port :");
		MaskFormatter port_formatter;
		port_formatter = new MaskFormatter("######");
		port_formatter.setPlaceholderCharacter('0');
		this.port_textField = new JFormattedTextField(port_formatter);
		
		/* Bouton */
		
		this.connection_button = new JButton("Connection");
		this.connection_button.setMargin(new Insets(0, 5, 0, 5));
		
		/* Rassemblement composants */
		
		JPanel ip_port_bouton = new JPanel();
		ip_port_bouton.setPreferredSize(new Dimension(500,35));
		ip_port_bouton.setLayout(new FlowLayout(FlowLayout.CENTER,5,7));
		ip_port_bouton.setBackground(Color.lightGray);
		
		ip_port_bouton.add(ip_label);
		ip_port_bouton.add(ip_textField);
		ip_port_bouton.add(port_label);
		ip_port_bouton.add(port_textField);
		ip_port_bouton.add(this.connection_button);
		
		/*============= AjoutPanel =============*/
		
		this.add(title);
		this.add(ip_port_bouton);
	}


	/**
	 * Renvoie l'IP demandée
	 * @return String
	 */
	public String getIp()
	{
		String ip = this.ip_textField.getText();
		if( ip.compareTo("0.0.0.0") == 0 )
		{
			ip = "127.000.000.001";
		}		
		return ip;
	}

	/**
	 * 
	 * @return la valeur du port_textField, si egale a 0 renvoi une valeur par defaut
	 */
	public int getPort()
	{
		int port = Integer.parseInt(this.port_textField.getText());
		if( port == 0 )
		{
			port = 7888;
		}
		return port;
	}
	
	/**
	 * 
	 * @return connection_button
	 */
	public JButton getConnection_button()
	{
		return connection_button;
	}
	
	/**
	 *  Change le texte du bouton
	 *  si le texte vaut "Connection" transforme en "Deconnection"
	 *  et inversement
	 * 
	 */
	public void changeButton()
	{
		if( connection_button.getText().equals("Connection") )
		{
			connection_button.setText("Deconnection");
		}
		else
		{
			connection_button.setText("Connection");
		}
		
	}
	
	
}