package display;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import menus.Login;
import menus.MainMenu;
import account.Profile;
import database.DBClass;

/**
 * Class used to centralize and hold all JPanel and Canvas of the Winds application.
 */
public class Window {
	public static final Dimension DIM_STANDARD = new Dimension(800, 550);
	public static final Dimension DIM_EDITOR = new Dimension(1024, 600);
	public static final Dimension DIM_SPLASH = new Dimension(400, 300);
	public static final boolean debug = false;
	
	private static JFrame main;
	
	/**
	 * Forbid to instantiate by the default way.
	 */
	private Window(){}
	
	/**
	 * affects a new component to the main JFrame and resizes it 
	 * @param c the component to affect into the JFrame
	 * @param dim the new dimension
	 */
	public static void affect(Component c, Dimension dim){
		if(main.getContentPane().getComponentCount() > 0) main.getContentPane().removeAll();
		main.add(c);
		main.toFront();
		main.setSize(dim);
		main.setVisible(true);
		main.revalidate();
	}
	/**
	 * returns the main frame
	 * @return JFrame
	 */
	public static JFrame getFrame(){
		return main;
	}
	/**
	 * Builds the Winds main frame.<br>
	 * This method must be called only by the splash screen.
	 */
	public static void init(){
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
		} catch (IOException e) {}
	}
	/**
	 * Displays the main frame.<br>
	 * This method must be called only by the splash screen.
	 */
	public static void start(){
		affect( Profile.getCurrentPlayer() == null ? new Login() : new MainMenu(), DIM_STANDARD);
	}
	
}
