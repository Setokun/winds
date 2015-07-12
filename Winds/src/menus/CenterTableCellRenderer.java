package menus;

import javax.swing.table.DefaultTableCellRenderer;

public class CenterTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 7795301494758045081L;

	/**
	 * allows to center the cell content
	 */
	public CenterTableCellRenderer() {
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
}
