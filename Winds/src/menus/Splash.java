package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import account.Profile;
import addon.AddonManager;
import display.Window;


public class Splash extends JFrame {
	private static final long serialVersionUID = -7831305789171898879L;
	
	private ImageIcon image;
	private JLabel lblTop, lblBottom;
	
	public Splash(){
		initStructure();
		initFrame();
		EventQueue.invokeLater(()->{ initLoad(); });
	}
	
	private void initStructure(){
		JPanel pnl = new JPanel();
		
		lblTop = new JLabel("Welcome  into  Winds  world");
		try { lblTop.setFont( Font.createFont(0, getClass()
    			    .getResourceAsStream("/resources/font/bubble.ttf"))
    			    .deriveFont(Font.PLAIN,23F) );
		} catch (Exception e) {
			lblTop.setFont( new Font("Serif", Font.BOLD, 23) );
		}
		pnl.add(lblTop, BorderLayout.NORTH);
		
		
		try {
			image = new ImageIcon( getClass().getResource("/resources/background/splash.jpg") );
		} catch (Exception e){}
		if(image != null) pnl.add(new JLabel(image), BorderLayout.CENTER);
		
		lblBottom = new JLabel("Please wait the loading files.");
		pnl.add(lblBottom, BorderLayout.SOUTH);
		
		add(pnl);		
	}
	private void initFrame() {
		try {
			setIconImage( ImageIO.read(Window.class.getClass().getResource("/resources/bubble.png")) );
		} catch (IOException e) {}
		
		setSize(Window.DIM_SPLASH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setUndecorated(true);
		setAlwaysOnTop(true);
		toFront();
		setVisible(true);
	}
	private void initLoad(){
		launchMethod("Loading GUI", Window.class, "init");
		launchMethod("Loading profile", Profile.class, "init");
		launchMethod("Loading add-ons", AddonManager.class, "init");
		launchMethod("Starting game", Window.class, "start");
		dispose();		
	}
	
	private void launchMethod(String txt, Class<?> cls, String methodName) {
		try {
			lblBottom.setText(txt);
			Method m = cls.getDeclaredMethod(methodName);
			m.invoke(null);
			lblBottom.repaint();
		} catch (Exception e) {}
	}
	
	
	//region Application launcher
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{ new Splash(); });
	}
	//endregion
	
}
