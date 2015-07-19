package display;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class CloseWindowEvent extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
		int rep = JOptionPane.showConfirmDialog(e.getComponent(), "Are you sure you want to quit game ?");
		if(rep == 0) System.exit(0);
	}

	
}
