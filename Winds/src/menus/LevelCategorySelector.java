package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import addon.level.Type;
import display.Window;

public class LevelCategorySelector extends JPanel{
	private static final long serialVersionUID = 9194645999792575062L;
	
	private Font windsPolice24 = null, windsPolice36 = null;
    private JButton jBtnBack, jBtnBasicLevels, jBtnCustomLevels, jBtnLevelsToModerate, jBtnMyLevels;
    private JLabel jLblTitle;
    private JPanel north, jNorthWest, jNorthEast, middle, center;
    private boolean isModoAdmin;
	
	public LevelCategorySelector() {
        //AddonManager.getJarLevels();
		
		this.isModoAdmin = Window.profile.getUserType().equals("administrator") 
        				|| Window.profile.getUserType().equals("moderator");
		
		initializeFont();
    	initTitle();
    	initBackButton();

    	this.setLayout(new BorderLayout());
    	
    	//this.add(north, BorderLayout.NORTH);
    	//this.add(middle, BorderLayout.CENTER);
    	
        initBasicLevelsButton();
        initCustomLevelsButton();
        initMyLevelsButton();
        initToModerateButton();

        createNorth();
    	createMiddle();
    	
        /*initHGroup();
        initVGroup();*/
    }

	private void createNorth() {
		
		jNorthWest = new JPanel();
		FlowLayout flNorthWest = new FlowLayout();
		flNorthWest.setHgap(20);
		flNorthWest.setVgap(25);
		jNorthWest.setLayout(flNorthWest);
		jNorthWest.add(jLblTitle);
		
		jNorthEast = new JPanel();
		FlowLayout flNorthEast = new FlowLayout();
		flNorthEast.setHgap(10);
		flNorthEast.setVgap(10);
		jNorthEast.setLayout(flNorthEast);
		jNorthEast.add(jBtnBack);
		
		
		north = new JPanel();
		BorderLayout northLayout = new BorderLayout();
		north.setLayout(northLayout);
		
		north.add(jNorthWest, BorderLayout.WEST);
		north.add(jNorthEast, BorderLayout.EAST);
		this.add(north, BorderLayout.NORTH);
	}
	private void createMiddle() {
		
		middle = new JPanel();
		
		FlowLayout flMiddle = new FlowLayout();
		flMiddle.setHgap(30);
		flMiddle.setVgap(30);
		middle.setLayout(flMiddle);
		
		
		center = new JPanel();
		BorderLayout gridLayout = new BorderLayout();
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		center.setLayout(gridLayout);
		center.add(jBtnBasicLevels, BorderLayout.WEST);
		center.add(jBtnCustomLevels, BorderLayout.CENTER);
		center.add(jBtnMyLevels, BorderLayout.EAST);
		
		if(isModoAdmin)	center.add(jBtnLevelsToModerate, BorderLayout.SOUTH);
		
		middle.add(center);
    	this.add(middle, BorderLayout.CENTER);
    	
	}

	private void initTitle() {
    	jLblTitle = new JLabel("Please select a category :");
        jLblTitle.setFont(windsPolice36);
	}

	private void initToModerateButton() {
    	jBtnLevelsToModerate = new JButton();
        jBtnLevelsToModerate.setFont(windsPolice24);
        jBtnLevelsToModerate.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/LevelsToModerate.png")));
        jBtnLevelsToModerate.setBorder(new SoftBevelBorder(0));
        jBtnLevelsToModerate.setBorderPainted(false);
        jBtnLevelsToModerate.setContentAreaFilled(false);
        jBtnLevelsToModerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnLevelsToModerateActionPerformed(evt);
            }
        });
        jBtnLevelsToModerate.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnLevelsToModerate.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/LevelsToModerate.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnLevelsToModerate.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/LevelsToModerate_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initMyLevelsButton() {
    	jBtnMyLevels = new JButton();
    	jBtnMyLevels.setFont(windsPolice24);
        jBtnMyLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/MyLevels.png")));
        jBtnMyLevels.setBorder(new SoftBevelBorder(0));
        jBtnMyLevels.setBorderPainted(false);
        jBtnMyLevels.setContentAreaFilled(false);
        jBtnMyLevels.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnMyLevelsActionPerformed(evt);
            }
        });
        jBtnMyLevels.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnMyLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/MyLevels.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnMyLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/MyLevels_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initCustomLevelsButton() {
		jBtnCustomLevels = new JButton();
		jBtnCustomLevels.setFont(windsPolice24);
        jBtnCustomLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/CustomLevels.png")));
        jBtnCustomLevels.setBorder(new SoftBevelBorder(0));
        jBtnCustomLevels.setBorderPainted(false);
        jBtnCustomLevels.setContentAreaFilled(false);
        jBtnCustomLevels.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnCustomLevelsActionPerformed(evt);
            }
        });
        jBtnCustomLevels.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnCustomLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/CustomLevels.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnCustomLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/CustomLevels_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initBasicLevelsButton() {
    	jBtnBasicLevels = new JButton();
        jBtnBasicLevels.setFont(windsPolice24);
        jBtnBasicLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/BasicLevels.png")));
        jBtnBasicLevels.setBorder(new SoftBevelBorder(0));
        jBtnBasicLevels.setBorderPainted(false);
        jBtnBasicLevels.setContentAreaFilled(false);
        jBtnBasicLevels.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnBasicLevelsActionPerformed(evt);
            }
        });
        jBtnBasicLevels.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnBasicLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/BasicLevels.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBasicLevels.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/BasicLevels_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initBackButton() {
    	jBtnBack = new JButton();
        jBtnBack.setFont(windsPolice24);
        jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
        jBtnBack.setBorder(new SoftBevelBorder(0));
        jBtnBack.setBorderPainted(false);
        jBtnBack.setContentAreaFilled(false);
        jBtnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnBackActionPerformed(evt);
            }
        });
        jBtnBack.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initializeFont() {
    	try {
    		windsPolice24 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,24F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice24 = new Font ("Serif", Font.BOLD, 24);
    		windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
	}

	private void jBtnBackActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
    }                                        
    private void jBtnBasicLevelsActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.basic));
    }                                               
    private void jBtnCustomLevelsActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.custom));
    }                                                
    private void jBtnMyLevelsActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.my));
    }                                            
    private void jBtnLevelsToModerateActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.toModerate));
    }                                                    

}
