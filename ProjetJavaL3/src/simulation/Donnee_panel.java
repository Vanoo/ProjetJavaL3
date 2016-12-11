package simulation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

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
		this.setPreferredSize(new Dimension(300,550));

		this.freq_lab = new JLabel("==========Fréquence==========");
		this.add(freq_lab);
		this.freq_slider = new JSlider();
		this.freq_slider.setMinimum(0);
		this.freq_slider.setMaximum(100);
		this.freq_slider.setMajorTickSpacing(10);
		this.freq_slider.setMinorTickSpacing(1);
		this.freq_slider.setPaintTicks(true);
	    this.add(freq_slider);
	    
	    this.val_lab = new JLabel("===========Valeur===========");
		this.add(val_lab);
		this.val_slider = new JSlider();
	    this.add(val_slider);

		this.val_slider.setMinimum((int)min-1);
		this.val_slider.setMaximum((int)max+1);
		this.val_slider.setMajorTickSpacing(10);
		this.val_slider.setMinorTickSpacing(1);
		this.val_slider.setPaintTicks(true);
	    this.add(val_slider);
	    
	    
	}
	
	public int getDelay()
	{
		return this.freq_slider.getValue();
	}
	
	public double getValeur()
	{
		return this.val_slider.getValue();
	}

}
