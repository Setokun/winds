package leveleditor;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import menus.LevelEditorList;
import addon.AddonManager;
import addon.JarLevel;
import display.Window;

public class EditorListener {
	
	/*OK*/public static class SaveListener implements ActionListener {
		EditorGUI gui;
		
		public SaveListener(EditorGUI gui){
			this.gui = gui;
		}
		
		public void actionPerformed(ActionEvent e) {
			JarLevel jar = gui.saveJarLevel();
			if(jar != null)  AddonManager.addJarLevel(jar.getFile());
		}
	}
	
	/*OK*/public static class TimeMaxListener extends KeyAdapter implements FocusListener {
		private static final int maxChar = 3;
		private JTextField field;
		
		public void keyTyped(KeyEvent e) {
			char car = e.getKeyChar();
			int code = e.getKeyCode();
			
			boolean isMaxLength = field.getText().length() == maxChar,
					isZeroFirstChar = field.getText().length() == 0 && car == '0',
					allowedKey = Character.isDigit(car)
							  || code == KeyEvent.VK_LEFT	|| code == KeyEvent.VK_RIGHT
							  || code == KeyEvent.VK_DELETE || code == KeyEvent.VK_BACK_SPACE;
			
			if(isMaxLength || !allowedKey || isZeroFirstChar)	e.consume();
		}
		public void keyReleased(KeyEvent e){
			boolean transferKey = e.getKeyCode() == KeyEvent.VK_ESCAPE
							   || e.getKeyCode() == KeyEvent.VK_ENTER;
			if(transferKey)  field.transferFocus();
		}

		public void focusGained(FocusEvent e) {
			field = (JTextField) e.getSource();
			if( field.getText().equals(EditorGUI.PROMPT_TIMEMAX) ){
				field.setText(null);
				field.setForeground(Color.BLACK);
			}
		}
		public void focusLost(FocusEvent e) {
			if( field.getText().equals("") ){
				field.setText(EditorGUI.PROMPT_TIMEMAX);
				field.setForeground(Color.GRAY);
				return;
			}
			
			int time = Integer.valueOf( field.getText() ).intValue();
			if(time < EditorGUI.MINIMUM_TIME){
				JOptionPane.showMessageDialog(null, "Specified time must be greater than 30 seconds", "Invalid time", JOptionPane.INFORMATION_MESSAGE);
				field.setText( String.valueOf(EditorGUI.MINIMUM_TIME) );
			}			
		}

	}
	
	/*OK*/public static class DescriptionListener extends KeyAdapter implements FocusListener {
		private final int maxChar = 255;
		JTextArea area;
		
		public void keyTyped(KeyEvent e) {
			if(area.getText().length() == maxChar)	e.consume();
		}
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				area.transferFocus();
		}
		
		public void focusGained(FocusEvent e) {
			area = (JTextArea) e.getSource();
			if( area.getText().equals(EditorGUI.PROMPT_DESCRIPTION) ){
				area.setForeground(Color.BLACK);
				area.setText(null);
			}
		}		
		public void focusLost(FocusEvent e) {
			if( area.getText().equals("") ){
				area.setForeground(Color.GRAY);
				area.setText(EditorGUI.PROMPT_DESCRIPTION);
			}
			
		}
	}
	
	/*OK*/public static class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Window.resize(Window.DIM_STANDARD);
			Window.affect(new LevelEditorList());
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
