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
	public static final boolean debug = false;
	
	private static JFrame main;
	
	
	public static void affect(Component c, Dimension dim){
		main.remove(main.getContentPane().getComponent(0));
		main.add(c);
		main.setSize(dim);
		main.setVisible(true);
	}
	
	public static void main(String[] args) {
		main = new JFrame("Winds");
		main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		main.addWindowListener(new CloseWindowEvent());
		main.setSize(DIM_STANDARD);
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		
		if(!DBClass.existStructure()){
			DBClass.createStructures();
			DBClass.createStartData();
		}
		
		
		try {
			main.setIconImage(ImageIO.read(Window.class.getClass().getResource("/resources/collectables/bubulle.png")));
		} catch (IOException e) { e.printStackTrace(); }
		
		Profile.current = Profile.getCurrentPlayer();
		main.add(Profile.current == null ? new Login() : new MainMenu());
		main.setVisible(true);		
	}
	
	public static JFrame getFrame(){
		return main;
	}
	
}
