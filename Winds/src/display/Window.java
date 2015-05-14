package display;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import menus.Login;
import menus.MainMenu;
import accounts.Profile;

public	 class Window {

	public static Profile profile = null;
	
	public static final boolean debug = true;
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
		//main.pack();
		main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		main.addWindowListener(new CloseWindowEvent());
		main.setSize(new Dimension(800, 550));
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		
		
		//DBClass.deleteTestData(); 
		//DBClass.createStructures(); DBClass.createTestData();
		
		
		profile = Profile.getCurrentPlayer();
		if(profile == null){
			main.add(new Login());
		}else{
			main.add(new MainMenu());
		}
		
		main.setVisible(true);
		
		
	}
	
}
