package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import visualisation.DoubleTextField;

/**
 * @Javadoc
 * Classe qui gère les données à envoyer lors qu'on est connecté.
 */
public class Donnee_panel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Dimension dim = new Dimension(500,150);
	
	private DoubleTextField freq_field;
	private DoubleTextField val_field;
	private JCheckBox random;
	double min;
	double max;
	
	public Donnee_panel(double min,double max) throws ParseException
	{
		super();
		this.min = min;
		this.max = max;

		this.setBackground(Color.gray);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.setBackground(Color.GRAY);
		
		this.random = new JCheckBox("Random", false);

	    MaskFormatter frequence_formatter = new MaskFormatter("########");
		frequence_formatter.setPlaceholderCharacter('0');
		
		String patternFrequence = "[0-9]{0,7}+";
		
		this.freq_field = new DoubleTextField(6,patternFrequence);
		this.freq_field.setMaximumSize(new Dimension(120,20));
		this.freq_field.setText("1000");
	    
	    MaskFormatter value_formatter = new MaskFormatter("###.##");
		value_formatter.setPlaceholderCharacter('0');
	    
		String patternValeur = "[-+]?[0-9]{0,3}+(\\.[0-9]{0,3}+)?";
		this.val_field = new DoubleTextField(6,patternValeur);
		this.val_field.setMaximumSize(new Dimension(100,20));
		this.val_field.setText("001.00");
		
		JPanel frequence_panel = new JPanel();
		frequence_panel.setBackground(Color.LIGHT_GRAY);
		frequence_panel.add(new JLabel("Frequence :"));
		frequence_panel.add(this.freq_field);
		frequence_panel.add(new JLabel(" ms"));
		
		JPanel valeur_panel = new JPanel();
		valeur_panel.setBackground(Color.LIGHT_GRAY);
		valeur_panel.add(new JLabel("Valeur :"));
		valeur_panel.add(val_field);
		valeur_panel.add(new JLabel(" unite   "));;
		valeur_panel.add(random);
		
		JPanel jp = new JPanel();
		jp.setBackground(Color.gray);
		jp.setPreferredSize(new Dimension(500,20));
		jp.add(new JLabel("################## Envoi des donnees ##################"));
		this.add(jp);
		this.add(frequence_panel);
		this.add(valeur_panel);
	}
	
	public void changementIntervalle(double min,double max)
	{
		this.min=min;
		this.max=max;
	}
	
	public int getDelay()
	{
		// System.out.println("this.freq_field.getValue().toString() : "+ this.freq_field.getValue().toString());
		int pat = (int) this.freq_field.getValeur();
		return pat;
	}
	
	public double getValeur()
	{
		double pat = 0;
		if(this.random.isSelected()) 
		{
			pat = min + (Math.random() * (max - (min)));
		}
		else
		{
			pat = this.val_field.getValeur();
		}
		
		if( pat > max )
		{
			pat = max;
		}
		else if( pat < min )
		{
			pat = min;
		}
		// System.out.println("Recup value");
		return pat;
	}
}
