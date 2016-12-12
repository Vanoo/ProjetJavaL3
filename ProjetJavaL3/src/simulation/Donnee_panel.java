package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class Donnee_panel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Dimension dim = new Dimension(500,150);
	
	JLabel titre;
	JLabel freq_lab;
	JFormattedTextField freq_field;
	JButton freq_button;
	JLabel val_lab;
	JButton val_button;
	JFormattedTextField val_field;
	JCheckBox random;
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
		
		// this.freq_slider = new JSlider(1,1000,1000);
		this.freq_field = new JFormattedTextField(frequence_formatter);
		this.freq_field.setMaximumSize(new Dimension(120,20));
		this.freq_field.setValue("00001000");
		// this.freq_lab = new JLabel();
	    
	    MaskFormatter value_formatter = new MaskFormatter("###.##");
		value_formatter.setPlaceholderCharacter('0');
	    
		this.val_lab = new JLabel();
		this.val_field = new JFormattedTextField(value_formatter);
		this.val_field.setMaximumSize(new Dimension(100,20));
		this.val_field.setText("001.00");
		this.val_field.setValue("001.00");
		
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
	
	public void changementIntervalle(int min,int max)
	{
		this.min=min;
		this.max=max;
	}
	
	public int getDelay()
	{
		System.out.println("this.freq_field.getValue().toString() : "+ this.freq_field.getValue().toString());
		int pat = Integer.parseInt((this.freq_field.getValue().toString()));
		return pat;
	}
	
	public double getValeur()
	{
		double pat = 0;
		if(this.random.isSelected()) 
		{
			pat = min + (Math.random() * (max - min));
		}
		else
		{
			pat = Double.parseDouble((this.val_field.getValue().toString()));
		}
		
		if( pat > max )
		{
			pat = max;
		}
		else if( pat < min )
		{
			pat = min;
		}
		System.out.println("Recup value");
		return pat;
	}
}