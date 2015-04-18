package com.winds.display;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.winds.menus.MainMenu;

public	 class Window {

	private static JFrame main;
	
	public static void affect(Component c){
		main.remove(main.getContentPane().getComponent(0));
		main.add(c);
		main.setVisible(true);
	}
	
	public static void resize(Dimension dim){
		main.setSize(dim);
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
	
	public static void main(String[] args) {
		main = new JFrame("Winds");
		main.pack();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(new Dimension(800, 550));
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		
		main.add(new MainMenu());
		main.setVisible(true);
		
		
	}
	
}
