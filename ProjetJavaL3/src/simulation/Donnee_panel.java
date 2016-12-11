package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/* TODO
 * Gestion des events : - Tant que non connecte tout le pannel doit etre grise
 * 						- ChangeListener sur les deux Slider, avec affichage valeur actuel
 * 						- En fonction du type changer val_lab
 * 						- En fonction de l'intervalle changée les min et max du slider
 * 							et fonction du type changer le TickSpacing ???
 * 						- CheckBox fonctionnel qui lance une fonction random
 * Positionnement a revoir
 */
public class Donnee_panel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel titre;
	JLabel freq_lab;
	/*JSlider freq_slider; */
	JFormattedTextField freq_field;
	JButton freq_button;
	JLabel val_lab;
	JButton val_button;
	JFormattedTextField val_field;
	/* JSlider val_slider; */
	
	public Donnee_panel(double min,double max) throws ParseException
	{
		new JPanel();
		this.setBackground(Color.gray);
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(350,550));
		this.setSize(new Dimension(350,550));

		this.add(new JLabel("######### Envoi des données #########"));
		
		
	    MaskFormatter frequence_formatter = new MaskFormatter("##m##s####ms");
		frequence_formatter.setPlaceholderCharacter('0');
		
		// this.freq_slider = new JSlider(1,1000,1000);
		this.freq_field = new JFormattedTextField(frequence_formatter);
		this.freq_field.setText("00010000");
		this.freq_field.setValue("00010000");
		this.freq_button = new JButton("Change");
		
		/*this.freq_slider.setMajorTickSpacing(10);
		this.freq_slider.setMinorTickSpacing(1);*/
		this.freq_lab = new JLabel();
		
		this.add(new JLabel("=============Fréquence============"));
	    this.add(freq_field);
	    this.add(freq_button);
	    this.add(freq_lab);
	    
	    this.freq_button.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent e)
			{
				// changeLabelFrequence(Integer.valueOf(freq_field.getText()));
			}
		});
	    
		/* this.val_slider = new JSlider((int)min,(int)max,(int)(min+max)/2);
		this.val_slider.setMajorTickSpacing(10);
		this.val_slider.setMinorTickSpacing(1);
		this.val_slider.setPaintTicks(true); */
	    
	    MaskFormatter value_formatter = new MaskFormatter("###.##");
		value_formatter.setPlaceholderCharacter('0');
	    
		this.val_lab = new JLabel();
		this.val_button = new JButton("Change");
		this.val_field = new JFormattedTextField(value_formatter);
		this.val_field.setText("001.00");
		this.val_field.setValue("001.00");
		this.val_button.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent e)
			{
				changeLabelValeur();
			}
		});
		
		this.add(new JLabel("===============Valeur==============="));
		this.add(val_field);
		this.add(val_button);
		this.add(val_lab);
	}
	
	public void changeLabelFrequence()
	{
		// Integer.parseInt(this.freq_field.getValue().toString());		
		System.out.println("Changement Frequence :"+this.freq_field.getValue().toString());
        this.freq_lab.setText(1 + " micro Seconde                                     ");
	}
	
	public void changeLabelValeur()
	{
		// Integer.parseInt(this.freq_field.getValue().toString());
		System.out.println("Changement Valeur :"+this.val_field.getValue().toString());
        this.val_lab.setText(1 + " Something                                              ");
	}
	
	public int getDelay()
	{
		System.out.println("this.freq_field.getValue().toString() : "+ this.freq_field.getValue().toString());
		// int pat = Integer.parseInt((this.freq_field.getValue().toString()));
		// System.out.println("Recup delay : "+pat);
		// int val = Integer.valueOf(this.val_field.getText());
		return 1000;
	}
	
	public double getValeur()
	{
		double pat = Double.parseDouble((this.val_field.getValue().toString()));
		System.out.println("Recup value");
		// int val = Integer.valueOf(this.val_field.getText());
		return pat;
	}
}