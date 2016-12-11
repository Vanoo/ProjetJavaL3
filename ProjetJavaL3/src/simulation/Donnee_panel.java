package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* TODO
 * Gestion des events : - Tant que non connectée tout le pannel doit être grisé
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
	JSlider freq_slider;
	JLabel val_lab;
	JSlider val_slider;
	
	public Donnee_panel(double min,double max)
	{
		new JPanel();
		this.setBackground(Color.gray);
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(350,550));
		this.setSize(new Dimension(350,550));

		this.add(new JLabel("######### Envoi des données #########"));
		
		this.freq_slider = new JSlider(1,1000,1000);
		this.freq_slider.setMajorTickSpacing(10);
		this.freq_slider.setMinorTickSpacing(1);
		this.freq_lab = new JLabel();
		
		this.add(new JLabel("==========Fréquence=========="));
	    this.add(freq_slider);
	    this.add(freq_lab);
	    
	    this.freq_slider.addChangeListener(new ChangeListener() 
	    {
			public void stateChanged(ChangeEvent arg0) 
			{
				changeLabelFrequence(freq_slider.getValue());
			}
		});
		
		this.val_slider = new JSlider((int)min,(int)max,(int)(min+max)/2);
		this.val_slider.setMajorTickSpacing(10);
		this.val_slider.setMinorTickSpacing(1);
		this.val_slider.setPaintTicks(true);
		this.val_lab = new JLabel();
		
		this.val_slider.addChangeListener(new ChangeListener() 
	    {
			public void stateChanged(ChangeEvent arg0) 
			{
				changeLabelValeur(val_slider.getValue());
			}
		});
		
		this.add(new JLabel("===========Valeur==========="));
		this.add(val_slider);
		this.add(val_lab);
	}
	
	public void changeLabelFrequence(int frequence)
	{
		System.out.println("Changement Frequence");
        this.freq_lab.setText(frequence + " micro Seconde                                     ");
	}
	
	public void changeLabelValeur(int valeur)
	{
		System.out.println("Changement Valeur");
        this.val_lab.setText(valeur + " Something                                              ");
	}
	
	public int getDelay()
	{
		System.out.println("Recup Delay");
		return this.freq_slider.getValue();
	}
	
	public double getValeur()
	{
		System.out.println("Recup value");
		return this.val_slider.getValue();
	}
}
