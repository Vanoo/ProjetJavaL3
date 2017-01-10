package visualisation;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRend extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		// Mettre une couleur (ou autre) en fonction de la case
		setBackground(new Color(row*(255/10), column*(255/10), 128));
		
		// Afficher le texte de la cellule (IMPORTANT)
		setText(value.toString());
		// Changer la couleur du texte
		int plop = (18 - (row + column)) * (255/18);
		// System.out.println(plop);
		setForeground(new Color(plop, plop, plop));
		
		return this;
	}
}
