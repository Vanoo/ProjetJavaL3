package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;


/* TODO 
 * Positionnement
 * Gestion des event :  - Clique Bouton lance vérification des infos rentrées dans InfoCapteur
 *  					Si valide lance demande de connection
 *  						Si connection OK, on grise InfoCapteur et IP/Port ( aucune modif possible )
 *  					Si non valide affichage pop-up erreur
 *  TextField IP, Formatage du texte ( seulement int )
 *  TextField PORT, formatage du texte et limitation ( seulement int < MAX_PORT )
 *  */
public class Connection_panel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Dimension dim = new Dimension(500,60);
	
	JLabel ip_label;
	JLabel port_label;
	
	JFormattedTextField ip_textField;
	JFormattedTextField port_textField;
	JButton connection_button;
	
	public Connection_panel() throws ParseException 
	{
		super();
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(dim);
		// this.setLayout(new FlowLayout());
		
		JPanel jp = new JPanel();
		jp.setBackground(Color.gray);
		jp.add(new JLabel("################## Envoi des donnees ##################"));
		this.add(jp);
		this.ip_label = new JLabel("IP :");
		
		MaskFormatter ip_formatter;
		ip_formatter = new MaskFormatter("###.###.###.###");
		ip_formatter.setPlaceholderCharacter('0');
		  
		this.ip_textField = new JFormattedTextField(ip_formatter);
		// this.ip_textField.setPreferredSize(new Dimension(115, 20));
		this.add(this.ip_label);
		this.add(this.ip_textField);
		
		this.port_label = new JLabel("Port :");
		
		MaskFormatter port_formatter;
		port_formatter = new MaskFormatter("######");
		port_formatter.setPlaceholderCharacter('0');
		this.port_textField = new JFormattedTextField(port_formatter);
		// port_textField.setPreferredSize(new Dimension(55, 20));
		
		this.connection_button = new JButton("Connection"); 
		this.add(port_label);
		this.add(this.port_textField);
		
		this.add(connection_button);
	}
	
	public JLabel getIp_label() {
		return ip_label;
	}

	public void setIp_label(JLabel ip_label) {
		this.ip_label = ip_label;
	}

	public JFormattedTextField getIp_textField() {
		return ip_textField;
	}

	public void setIp_textField(JFormattedTextField ip_textField) {
		this.ip_textField = ip_textField;
	}

	public JLabel getPort_label() {
		return port_label;
	}

	public void setPort_label(JLabel port_label) {
		this.port_label = port_label;
	}

	public JFormattedTextField getPort_textField() {
		return port_textField;
	}

	public void setPort_textField(JFormattedTextField port_textField) {
		this.port_textField = port_textField;
	}

	public JButton getConnection_button() {
		return connection_button;
	}

	public void setConnection_button(JButton connection_button) {
		this.connection_button = connection_button;
	}



}
