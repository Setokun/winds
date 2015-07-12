package controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import display.Game;

public class KeyInput extends KeyAdapter{
	
	public KeyInput(){}
	
	/**
	 * determines actions to be done in game when pressing a key
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_Q && Game.getPause()){
			System.exit(1);
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			Game.setPause();
		}
	}
}
