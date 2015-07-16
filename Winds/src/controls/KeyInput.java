package controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import display.Game;


/**
 * Class used to catch the input key in game.
 */
public class KeyInput extends KeyAdapter{
	
	/**
	 * determines actions to be done in game when pressing a key
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Q && Game.getPause()) 
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game ?") == JOptionPane.YES_OPTION)
				System.exit(0);
		if(key == KeyEvent.VK_ESCAPE)				Game.setPause();
	}
}
