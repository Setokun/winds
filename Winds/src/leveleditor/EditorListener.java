package leveleditor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import menus.LevelEditorList;
import addon.AddonManager;
import addon.JarLevel;
import display.Window;

public class EditorListener {
	
	/*OK*/public static class SaveListener implements ActionListener {
		private EditorGUI gui;
		
		public SaveListener(EditorGUI gui){
			this.gui = gui;
		}
		public void actionPerformed(ActionEvent e) {
			JarLevel jar = gui.saveJarLevel();
			if(jar != null)  AddonManager.addJarLevel(jar.getFile());
		}
	}
	
	/*OK*/public static class TimeMaxListener extends KeyAdapter implements FocusListener {
		private final int maxChar = 3;
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
				JOptionPane.showMessageDialog(null, "Specified time must be greater than "
						+ EditorGUI.MINIMUM_TIME +" seconds", "Invalid time",
						JOptionPane.INFORMATION_MESSAGE);
				field.setText( String.valueOf(EditorGUI.MINIMUM_TIME) );
			}			
		}
	}
	
	/*OK*/public static class DescriptionListener extends KeyAdapter implements FocusListener {
		private final int maxChar = 255;
		private JTextArea area;
		
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
			int response = JOptionPane.showConfirmDialog(Window.getFrame(),
				"Do you really want to quit the level editor ?\nThe"
				+ " unsaved modifications are going to be lost.",
				"Need confirmation", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION){
				Window.resize(Window.DIM_STANDARD);
				Window.affect(new LevelEditorList());
			}
		}
	}
	
	/*OK*/public static class EmptyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			EditorGUI.tileCurrent.updateFrom(Tile.createEmptyLegend());
		}
	}
	
	/*OK*/public static class TileLegendListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			Tile source = (Tile) e.getSource();
			EditorGUI.tileCurrent.updateFrom(source);
		}
	}
	
	/*OK*/public static class TileMatrixListener extends MouseAdapter {
		private final int delay = 200;	// milliseconds
		
		public void mouseReleased(MouseEvent e) {
			Tile current = EditorGUI.tileCurrent;
			Tile source = (Tile) e.getSource();
			Tile[] neighboors = EditorGUI.getNeighboors(source.getPosition());
			
			if( allowedTile(current, neighboors) ){
				source.updateFrom(current);
				return;
			}
			
			notifyForbiddenTile();
		}
		private void notifyForbiddenTile(){
			JPanel pnl = EditorGUI.current;
			Color oldColor = pnl.getBackground();
			
			pnl.setBackground(new Color(240,125,125));
			new Timer(delay, new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					pnl.setBackground(oldColor);
					((Timer) e.getSource()).stop();
				}
			}).start();
		}
		private boolean allowedTile(Tile current, Tile[] neighboors){
			int currentIndex = current.getIndex();
			if(currentIndex == 0)  return true;
			
			Map<Point, Integer[]> compatibility = EditorGUI.compatibility;
			for(int i=0; i<neighboors.length; i++){
				Tile side = neighboors[i];
				
				// out of matrix bounds : all tiles allowed
				if(side == null)  continue;
				// side is an empty tile
				if(side.getIndex() == 0)  continue;
				
				Integer[] compatibles = compatibility.get(new Point(currentIndex,i));
				boolean found = false;
				for(int j=0; j<compatibles.length; j++){
					if(compatibles[j].intValue() == side.getIndex()){
						found = true;
						break;
					}
				}
				if( !found )  return false;
			}
			return true;
		}
	}

}
