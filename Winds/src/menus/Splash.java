package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import account.Profile;
import addon.AddonManager;
import display.Window;


/**
 * Class used to display the Winds splash <br>
 * screen during the loadings of main items.
 */
public class Splash extends JFrame {
	private static final long serialVersionUID = -7831305789171898879L;
	private JLabel lblLoad;
	
	/**
	 * Instantiates a new Winds splash screen.
	 */
	public Splash(){
		initStructure();
		initFrame();
		EventQueue.invokeLater(()->{ initLoad(); });
	}
	
	//region Methods 
	/**
	 * Initializes the splash screen's structure.
	 */
	private void initStructure(){
		JLabel lblBack = new JLabel("Welcome into Winds");
		lblBack.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBack.setVerticalTextPosition(SwingConstants.CENTER);
		lblBack.setIcon(new ImageIcon(getClass().getResource("/resources/background/splash.png")));
		try { lblBack.setFont( Font.createFont(0, getClass()
    			    .getResourceAsStream("/resources/font/bubble.ttf"))
    			    .deriveFont(Font.PLAIN,34F) );
		} catch (Exception e) {
			lblBack.setFont( new Font("Serif", Font.BOLD, 34) );
		}
		add(lblBack, BorderLayout.CENTER);
				
		lblLoad = new JLabel("Please wait the load of files. Starting soon.");
		lblLoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoad.setVerticalAlignment(SwingConstants.TOP);
		add(lblLoad, BorderLayout.SOUTH);
		
	}
	/**
	 * Initializes the splash screen's frame.
	 */
	private void initFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/bubble.png")));
		setSize(Window.DIM_SPLASH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setUndecorated(true);
		setAlwaysOnTop(true);
		toFront();
		pack();
		setVisible(true);
	}
	/**
	 * Loads the main items of Winds.
	 */
	private void initLoad(){
		launchMethod("Loading GUI", Window.class, "init");
		launchMethod("Loading profile", Profile.class, "init");
		launchMethod("Loading add-ons", AddonManager.class, "init");
		launchMethod("Starting", Window.class, "start");
		dispose();		
	}
	/**
	 * Launch the method of a main item of Winds.
	 * @param txt The text to display in the splash screen
	 * @param cls The class of the method to launch 
	 * @param methodName The method's name to launch
	 */
	private void launchMethod(String txt, Class<?> cls, String methodName) {
		try {
			lblLoad.setText(txt);
			Method m = cls.getDeclaredMethod(methodName);
			m.invoke(null);
		} catch (Exception e) {}
	}
	//endregion	
	
	//region Application launcher 
	/**
	 * Main method to launch the Winds application.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{ new Splash(); });
	}
	//endregion
	
}
