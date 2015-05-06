package controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import display.Game;
import display.Handler;

public class KeyInput extends KeyAdapter{
Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	
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
