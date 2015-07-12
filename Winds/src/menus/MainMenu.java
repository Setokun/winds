package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import account.Profile;
import database.DBClass;
import display.Window;

public class MainMenu extends JPanel{
	private static final long serialVersionUID = 2331229056450441371L;
	
	private Font windsPolice48 = null, windsPolice18 = null;
	private JButton btnChangeProfile, btnConfiguration, btnLevelEditor, btnPlay, btnQuit, btnScores, btnShop;
    private JLabel title;
    private JPanel north, middle;
    private Dimension btnDimension = new Dimension(300, 50);
    private int gap = 5;
    
	public MainMenu() {
		
		this.setLayout(new BorderLayout());
		
		initializeFont();
    	initTitle();
    	
    	initNorth();
    	initSouth();
    }
	
	/**
	 * create the north section of this GUI
	 */
	private void initNorth() {
		north = new JPanel();
        north.setLayout(new FlowLayout());
        north.add(title);
        this.add(north, BorderLayout.NORTH);
	}
	/**
	 * create the south section of this GUI
	 */
	private void initSouth() {
		middle = new JPanel();
        BoxLayout flMiddle = new BoxLayout(middle, BoxLayout.PAGE_AXIS);
        middle.setLayout(flMiddle);
        
        initBtnPlay();
        initBtnScores();
        initBtnConfiguration();
        initBtnShop();
        initBtnLevelEditor();
        initBtnChangeProfile();
        initBtnQuit();
        initMarginBottom();

        this.add(middle, BorderLayout.SOUTH);
	}

	/**
	 * initialize custom font
	 */
	private void initializeFont(){
    	try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice48 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,48F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice48 = new Font ("Serif", Font.BOLD, 48);
		}
    }    
	/**
	 * initialize title of this GUI
	 */
    private void initTitle(){
    	title = new JLabel("Winds");
        title.setFont(windsPolice48);
    }
    
    /**
     * initialize "Play" button
     */
    private void initBtnPlay(){
    	
    	btnPlay = new JButton("Play");
    	btnPlay.setPreferredSize(btnDimension);
        btnPlay.setFont(windsPolice18);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnPlayActionPerformed(evt);
            }
        });
        
    	JPanel jpBtnPlay = new JPanel();
    	FlowLayout flBtnPlay = new FlowLayout();
    	flBtnPlay.setVgap(gap);
    	jpBtnPlay.setLayout(flBtnPlay);
        jpBtnPlay.add(btnPlay);
        middle.add(jpBtnPlay);
    }
	/**
	 * initialize "Scores" button
	 */
    private void initBtnScores(){
		btnScores = new JButton("Scores");
		btnScores.setPreferredSize(btnDimension);
		btnScores.setFont(windsPolice18);
        btnScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnScoresActionPerformed(evt);
            }
        });
        
        JPanel jpBtnScores = new JPanel();
    	FlowLayout flBtnScores = new FlowLayout();
    	flBtnScores.setVgap(gap);
    	jpBtnScores.setLayout(flBtnScores);
    	jpBtnScores.add(btnScores);
        middle.add(jpBtnScores);
	}
    /**
     * initialize "Configuration" button
     */
	private void initBtnConfiguration(){
		btnConfiguration = new JButton("Configuration");
		btnConfiguration.setPreferredSize(btnDimension);
		btnConfiguration.setFont(windsPolice18);
        btnConfiguration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnConfigurationActionPerformed(evt);
            }
        });
        
        JPanel jpBtnConfig = new JPanel();
    	FlowLayout flBtnConfig = new FlowLayout();
    	flBtnConfig.setVgap(gap);
    	jpBtnConfig.setLayout(flBtnConfig);
    	jpBtnConfig.add(btnConfiguration);
        middle.add(jpBtnConfig);
	}
	/**
	 * initialize "Shop" button
	 */
	private void initBtnShop(){
		btnShop = new JButton("shop");
		btnShop.setPreferredSize(btnDimension);
		btnShop.setFont(windsPolice18);
        btnShop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnShopActionPerformed(evt);
            }
        });
        
        JPanel jpBtnShop = new JPanel();
    	FlowLayout flBtnShop = new FlowLayout();
    	flBtnShop.setVgap(gap);
    	jpBtnShop.setLayout(flBtnShop);
    	jpBtnShop.add(btnShop);
        middle.add(jpBtnShop);
	}
	/**
	 * initialize "Level Editor" button
	 */
	private void initBtnLevelEditor(){
		btnLevelEditor = new JButton("level editor");
		btnLevelEditor.setPreferredSize(btnDimension);
		btnLevelEditor.setFont(windsPolice18);
        btnLevelEditor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnLevelEditorActionPerformed(evt);
            }
        });
        
        JPanel jpBtnLevelEditor = new JPanel();
    	FlowLayout flBtnLevelEditor= new FlowLayout();
    	flBtnLevelEditor.setVgap(gap);
    	jpBtnLevelEditor.setLayout(flBtnLevelEditor);
    	jpBtnLevelEditor.add(btnLevelEditor);
        middle.add(jpBtnLevelEditor);
	}
	/**
	 * initialize "Change Profile" button
	 */
	private void initBtnChangeProfile(){
		btnChangeProfile = new JButton("Change Profile");
		btnChangeProfile.setPreferredSize(btnDimension);
		btnChangeProfile.setFont(windsPolice18);
        btnChangeProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnChangeProfileActionPerformed(evt);
            }
        });
        
        JPanel jpBtnChangeProfile = new JPanel();
    	FlowLayout flBtnChangeProfile = new FlowLayout();
    	flBtnChangeProfile.setVgap(gap);
    	jpBtnChangeProfile.setLayout(flBtnChangeProfile);
    	jpBtnChangeProfile.add(btnChangeProfile);
        middle.add(jpBtnChangeProfile);
	}
	/**
	 * initialize "Quit" button
	 */
	private void initBtnQuit(){
		btnQuit = new JButton("Quit");
		btnQuit.setPreferredSize(btnDimension);
        btnQuit.setFont(windsPolice18);
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnQuitActionPerformed(evt);
            }
        });
        
        JPanel jpBtnQuit = new JPanel();
    	FlowLayout flBtnQuit = new FlowLayout();
    	flBtnQuit.setVgap(gap);
    	jpBtnQuit.setLayout(flBtnQuit);
    	jpBtnQuit.add(btnQuit);
        middle.add(jpBtnQuit);
	}
	/**
	 * initialize an empty button to create a south margin to the buttons
	 */
	private void initMarginBottom() {
    	JPanel blankButton = new JPanel();
		FlowLayout flBlankButton = new FlowLayout();
		flBlankButton.setHgap(10);
		flBlankButton.setVgap(10);
		blankButton.setLayout(flBlankButton);
		JButton b = new JButton();
		b.setPreferredSize(new Dimension(300,20));
		b.setBorder(new SoftBevelBorder(0));
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		blankButton.add(b);
		middle.add(blankButton);
	}
	
	/**
	 * determines what action has to be done when clicking on the "Play" button
	 * @param evt
	 */
    private void jBtnPlayActionPerformed(ActionEvent evt) {
		Window.affect(new LevelCategorySelector(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Scores" button
     * @param evt
     */
    private void jBtnScoresActionPerformed(ActionEvent evt) {
		Window.affect(new Scores(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Configuration" button
     * @param evt
     */
    private void jBtnConfigurationActionPerformed(ActionEvent evt) {
		Window.affect(new ConfigMenu(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Shop" button
     * @param evt
     */
    private void jBtnShopActionPerformed(ActionEvent evt) {
		Window.affect(new Shop(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Level Editor" button
     * @param evt
     */
    private void jBtnLevelEditorActionPerformed(ActionEvent evt) {
    	Window.affect(new LevelEditorList(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Change Profile" button
     * @param evt
     */
    private void jBtnChangeProfileActionPerformed(ActionEvent evt) {
    	try {
    		DBClass.connect();
			DBClass.executeQuery("UPDATE users SET current = false");
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			DBClass.disconnect();
		}
    	Profile.current = null;
		Window.affect(new Login(),Window.DIM_STANDARD);
    }
    /**
     * determines what action has to be done when clicking on the "Quit" button
     * @param evt
     */
    private void jBtnQuitActionPerformed(ActionEvent evt) {
        System.exit(1);
    }
}
