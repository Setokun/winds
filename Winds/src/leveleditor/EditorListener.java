package leveleditor;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditorListener {
	
	public static class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static class TimeMaxListener extends MouseAdapter implements KeyListener {
		private final int maxChar = 3;
		private JTextField field;
		
		public void mousePressed(MouseEvent e) {
			field = (JTextField) e.getSource();
			if( field.getText().equals(EditorGUI.PROMPT_TIMEMAX) ){
				field.setText(null);
				field.setForeground(Color.BLACK);
			}
		}
		
		public void keyTyped(KeyEvent e) {
			field = (JTextField) e.getSource();
			if(e.getKeyCode() == e.VK_ESCAPE){ field.transferFocus(); }

		}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}

	}
	
	public static class DescriptionListener extends KeyAdapter {
		private final int maxChar = 255;
		
		public void keyTyped(KeyEvent e) {
			JTextArea area = (JTextArea) e.getSource();
			if(e.getKeyCode() == e.VK_ESCAPE){ area.transferFocus(); }
			
			if(area.getText().length() != maxChar){
				
			}
			
			
		}
	}
	
	public static class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/*OK*/public static class EmptyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			EditorGUI.tileCurrent.updateFrom(Tile.getEmptyLegend());
		}
	}

	/*OK*/public static class TileLegendListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			Tile source = (Tile) e.getSource();
			EditorGUI.tileCurrent.updateFrom(source);
		}
	}
	
	/*OK*/public static class TileMatrixListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			Tile current = EditorGUI.tileCurrent;
			int index = current.getIndex();
			Image img = index != 0 ? EditorGUI.images32[index] : Tile.getEmptyMatrix().getIcon().getImage();
			
			Tile source = (Tile) e.getSource();
			source.updateFrom(index, img);
		}
	}

}
