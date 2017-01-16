package visualisation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * 
 * JFrame affiche un graphique en fonction du fichier donner dans le constructeur
 *
 */
public class Graphe extends JFrame
{
	private static final long serialVersionUID = 1L;
	private BufferedReader in;
	
	/**
	 * Constructeur : affiche une graphique representant les donnees stockees sur un fichier
	 * 
	 * @param filePath le chemin du fichier de donnees
	 */
	public Graphe (String filePath)
	{
		super();
		String[] splittedString = null;
		try
		{
			in = new BufferedReader(new FileReader(new File(filePath)));
			splittedString = in.readLine().split(";");
			
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "File not found");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Can't read from file");
			e.printStackTrace();
		}
		
		
		
		JFreeChart chart = ChartFactory.createXYLineChart(
				splittedString[0],
				"Time",
				splittedString[1],
				createDatasetFromFile(filePath),
				PlotOrientation.VERTICAL,
				true, true, false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().add(chartPanel);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Cree un dataset approprie pour le XYLineChart
	 * 
	 * @param filePath le chemin du fichier a lire
	 * @return dataset
	 */
	private DefaultXYDataset createDatasetFromFile(String filePath)
	{
		String read = "";
		String [] splittedString;
		DefaultXYDataset dataset = new DefaultXYDataset();
		int lineCount = 0;
		ArrayList<Double> plop_1 = new ArrayList<Double>();
		ArrayList<Double> plop_2 = new ArrayList<Double>();
		try
		{
			for (lineCount = 0; (read = this.in.readLine()) != null; lineCount++)
			{
				splittedString = read.split(":");
				plop_1.add(Double.parseDouble(splittedString[0]));
				plop_2.add(Double.parseDouble(splittedString[1]));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		double[][] data = new double[2][lineCount];
		
		for(int i = 0; i < lineCount; i++)
		{
			data[0][i] = plop_1.get(i);
			data[1][i] = plop_2.get(i);
		}
		
		
		dataset.addSeries("series", data);
		return dataset;
	}
}
