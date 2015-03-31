package com.winds.window;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {

	public Window(int w, int h, String title, Game game){
		
		game.setPreferredSize(new Dimension(w * Game.SCALE, h * Game.SCALE));
		game.setMaximumSize(new Dimension(w * Game.SCALE, h * Game.SCALE));
		game.setMinimumSize(new Dimension(w * Game.SCALE, h * Game.SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

		game.start();
		
	}
	
}
