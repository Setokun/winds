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
	
	private void initNorth() {
		north = new JPanel();
        north.setLayout(new FlowLayout());
        north.add(title);
        this.add(north, BorderLayout.NORTH);
	}
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

	private void initializeFont(){
    	try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice48 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,48F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice48 = new Font ("Serif", Font.BOLD, 48);
		}
    }    
    private void initTitle(){
    	title = new JLabel("Winds");
        title.setFont(windsPolice48);
    }
    
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
	
    private void jBtnPlayActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
    }
    private void jBtnScoresActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new Scores());
    }
    private void jBtnConfigurationActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new ConfigMenu());
    }
    private void jBtnShopActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new Shop());
    }
    private void jBtnLevelEditorActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelEditorList());
    }
    private void jBtnChangeProfileActionPerformed(java.awt.event.ActionEvent evt) {
    	try {
			DBClass.executeQuery("UPDATE users SET current = false");
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
    	Window.profile = null;
    	Window.resize(new Dimension(800, 550));
		Window.affect(new Login());
    }
    private void jBtnQuitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(1);
    }
}
