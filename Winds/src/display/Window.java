package display;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import database.DBClass;
import menus.Login;
import menus.MainMenu;
import account.Profile;


public class Window {
	public static final Dimension DIM_STANDARD = new Dimension(800, 550);
	public static final Dimension DIM_EDITOR = new Dimension(1024, 600);
	public static final boolean debug = false;
	
	private static JFrame main;
	
	/**
	 * Constructor of Window Class, creates a JFrame which will hold all JPanel and Canvas of the Program
	 */
	public Window(){
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
	
	/**
	 * affects a new component to the main JFrame and resizes it 
	 * @param c the component to affect into the JFrame
	 * @param dim the new dimensionz
	 */
	public static void affect(Component c, Dimension dim){
		main.remove(main.getContentPane().getComponent(0));
		main.add(c);
		main.setSize(dim);
		main.setVisible(true);
	}
	/**
	 * Main method to launch the program with a Runnable
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable r  = new Runnable() {
			public void run() {
				new Window();
			}
		};
		
		SwingUtilities.invokeLater(r);
	}
	/**
	 * returns the main frame
	 * @return JFrame
	 */
	public static JFrame getFrame(){
		return main;
	}
	
}
