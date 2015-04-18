package com.winds.menus;

import javax.swing.table.DefaultTableCellRenderer;

public class CenterTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 7795301494758045081L;

	public CenterTableCellRenderer() {
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
}
