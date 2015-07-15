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
	
	/**
	 * Class to listen actions onto "Save" button in EditorGUI.
	 */
	public static class SaveListener implements ActionListener {
		private EditorGUI gui;
		
		/**
		 * Instanciate a new listener.
		 */
		public SaveListener(EditorGUI gui){
			this.gui = gui;
		}
		/**
		 * Save the JarLevel used in the EditorGUI and store it by AddonManager 
		 * when the button is performed.
		 */
		public void actionPerformed(ActionEvent e) {
			JarLevel jar = gui.saveJarLevel();
			if(jar != null)  AddonManager.addJarLevel(jar.getFile());
		}
	}
	
	/**
	 * Class to listen actions onto "TimeMax" field in EditorGUI.
	 */
	public static class TimeMaxListener extends KeyAdapter implements FocusListener {
		private final int maxChar = 3;
		private JTextField field;
		
		/**
		 * Restricts key input.
		 */
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
		/**
		 * Checks the navigation key input.
		 */
		public void keyReleased(KeyEvent e){
			boolean transferKey = e.getKeyCode() == KeyEvent.VK_ESCAPE
							   || e.getKeyCode() == KeyEvent.VK_ENTER;
			if(transferKey)  field.transferFocus();
		}

		/**
		 * Manage the prompt text displayed in the field when it gains focus.
		 */
		public void focusGained(FocusEvent e) {
			field = (JTextField) e.getSource();
			if( field.getText().equals(EditorGUI.PROMPT_TIMEMAX) ){
				field.setText(null);
				field.setForeground(Color.BLACK);
			}
		}
		/**
		 * Manage the prompt text displayed in the field when it loses focus.
		 */
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
	
	/**
	 * Class to listen actions onto "Description" field in EditorGUI.
	 */
	public static class DescriptionListener extends KeyAdapter implements FocusListener {
		private final int maxChar = 255;
		private JTextArea area;
		
		/**
		 * Restricts key input.
		 */
		public void keyTyped(KeyEvent e) {
			if(area.getText().length() == maxChar)	e.consume();
		}
		/**
		 * Checks the navigation key input.
		 */
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				area.transferFocus();
		}
		
		/**
		 * Manage the prompt text displayed in the field when it gains focus.
		 */
		public void focusGained(FocusEvent e) {
			area = (JTextArea) e.getSource();
			if( area.getText().equals(EditorGUI.PROMPT_DESCRIPTION) ){
				area.setForeground(Color.BLACK);
				area.setText(null);
			}
		}		
		/**
		 * Manage the prompt text displayed in the field when it loses focus.
		 */
		public void focusLost(FocusEvent e) {
			if( area.getText().equals("") ){
				area.setForeground(Color.GRAY);
				area.setText(EditorGUI.PROMPT_DESCRIPTION);
			}
			
		}
	}
	
	/**
	 * Class to listen actions onto "Back" button in EditorGUI.
	 */
	public static class BackListener implements ActionListener {
		/**
		 * Back to the LevelEditorList when the button is performed.
		 */
		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(Window.getFrame(),
				"Do you really want to quit the level editor ?\nThe"
				+ " unsaved modifications are going to be lost.",
				"Need confirmation", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION)
				Window.affect(new LevelEditorList(),Window.DIM_STANDARD);
		}
	}
	
	/**
	 * Class to listen actions onto "Empty" button in EditorGUI.
	 */
	public static class EmptyListener extends MouseAdapter {
		/**
		 * Affect an empty tile to the current tile when the button is performed.
		 */
		public void mousePressed(MouseEvent e) {
			EditorGUI.tileCurrent.updateFrom(Tile.createEmptyCurrent());
		}
	}
	
	/**
	 * Class to listen actions onto legend tile in EditorGUI.
	 */
	public static class TileLegendListener extends MouseAdapter {
		/**
		 * Update the current tile from the clicked legend tile.
		 */
		public void mouseReleased(MouseEvent e) {
			Tile source = (Tile) e.getSource();
			EditorGUI.tileCurrent.updateFrom(source);
		}
	}
	
	/**
	 * Class to listen actions onto matrix tile in EditorGUI.
	 */
	public static class TileMatrixListener extends MouseAdapter {
		private final int delay = 200;	// milliseconds
		
		/**
		 * Update the clicked matrix tile from the current tile.
		 */
		public void mouseReleased(MouseEvent e) {
			Tile current = EditorGUI.tileCurrent;
			Tile source = (Tile) e.getSource();
			Tile[] neighboors = EditorGUI.getNeighbors(source.getPosition());
			
			if( !allowedBackTile(current, neighboors) ){
				notifyForbiddenTile();
				return;
			}
			
			source.updateFrom(current);
			updateInteractionSingletons(source);
		}
		
		/**
		 * Checks the sprite compatibility between the current tile and his neighbors.
		 * @param current	Tile which will be compared with his neighbors
		 * @param neighbors	the neighbors of the current tile
		 * @return boolean
		 */
		private boolean allowedBackTile(Tile current, Tile[] neighbors){
			int currentIndex = current.getBackIndex();
			if(currentIndex == 0)  return true;
			
			Map<Point, Integer[]> compatibility = EditorGUI.spritesComp;
			for(int i=0; i<neighbors.length; i++){
				Tile side = neighbors[i];
				
				// out of matrix bounds : all tiles allowed
				if(side == null)  continue;
				// side is an empty tile
				if(side.getBackIndex() == 0)  continue;
				
				Integer[] compatibles = compatibility.get(new Point(currentIndex,i));
				if(compatibles != null){
					boolean found = false;
					for(int j=0; j<compatibles.length; j++){
						if(compatibles[j].intValue() == side.getBackIndex()){
							found = true;
							break;
						}
					}
					if( !found )  return false;
				}
			}
			return true;
		}
		/**
		 * Alerts the user the current tile is unauthorized.
		 */
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
		/**
		 * Updates the departure and arrival tiles if needed
		 * @param current Tile
		 */
		private void updateInteractionSingletons(Tile current){
			// updates departure
			if( current.isDeparture() )
				EditorGUI.setDeparture(current);
			
			// updates arrival
			else if( current.isArrival() )
				EditorGUI.setArrival(current);
			
			// checks for updating departure or arrival
			else if( current.isEmptyInteraction() ){
				boolean isDeparturePosition = EditorGUI.getDeparture() != null &&
						current.getPosition() == EditorGUI.getDeparture().getPosition();
				boolean isArrivalPosition = EditorGUI.getArrival() != null &&
						current.getPosition() == EditorGUI.getArrival().getPosition();
				
				if(isDeparturePosition)			EditorGUI.setDeparture(null);
				else if(isArrivalPosition)		EditorGUI.setArrival(null);
			}
		}
	}

}
