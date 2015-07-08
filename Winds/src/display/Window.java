package display;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import database.DBClass;
import menus.Login;
import menus.MainMenu;
import account.Profile;


public class Window {
	public static final Dimension DIM_STANDARD = new Dimension(800, 550);
	public static final Dimension DIM_EDITOR = new Dimension(1024, 600);
	public static final boolean debug = true;
	
	public static Profile profile = null;
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
		main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		main.addWindowListener(new CloseWindowEvent());
		main.setSize(new Dimension(800, 550));
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		
		if(!DBClass.existStructure()){
			DBClass.createStructures();
			DBClass.createStartData();
		}
		
		
		try {
			main.setIconImage(ImageIO.read(Window.class.getClass().getResource("/bubulle.png")));
		} catch (IOException e) { e.printStackTrace(); }
		
		profile = Profile.getCurrentPlayer();
		main.add(profile == null ? new Login() : new MainMenu());
		main.setVisible(true);		
	}
	
	public static JFrame getFrame(){
		return main;
	}
	
}
