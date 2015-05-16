package leveleditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class EditorListener {
	
	public static class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}
	
	public static class TimeMaxListener extends MouseAdapter implements KeyListener {
		public void mousePressed(MouseEvent e) {

		}
		
		public void keyTyped(KeyEvent e) {

		}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}

	}
	
	public static class DescriptionListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {

		}
	}
	
	public static class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

	public static class EmptyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

		}
	}

	public static class TileLegendListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {

		}
	}
	
	public static class TileMatrixListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {

		}
	}

}
