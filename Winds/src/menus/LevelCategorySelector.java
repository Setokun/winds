package menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.SoftBevelBorder;

import addon.AddonManager;
import addon.level.Type;
import display.Window;

public class LevelCategorySelector extends JPanel{
	private static final long serialVersionUID = 9194645999792575062L;
	
	private Font windsPolice24 = null, windsPolice36 = null;
    private JButton jBtnBack, jBtnBasicLevels, jBtnCustomLevels, jBtnLevelsToModerate, jBtnMyLevels;
    private JLabel jLblTitle;
    private GroupLayout layout;
    private boolean isModoAdmin;
	
	public LevelCategorySelector() {
        AddonManager.getJarLevels();
		
		this.isModoAdmin = Window.profile.getUserType().equals("administrator") 
        				|| Window.profile.getUserType().equals("moderator");
		
		initializeFont();
    	initTitle();
    	initBackButton();

        initBasicLevelsButton();
        initCustomLevelsButton();
        initMyLevelsButton();
        initToModerateButton();
        
        layout = new GroupLayout(this);
        this.setLayout(layout);
        
        initHGroup();
        initVGroup();
    }                      

    private void initVGroup() {
    	SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBtnBack, 63, 63, 63))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitle)));
        vGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jBtnBasicLevels, 319, 319, 319)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnCustomLevels, 319, 319, 319)
                .addComponent(jBtnMyLevels, 319, 319, 319)))
        .addGap(18, 18, 18);
        if(isModoAdmin){
        vGroup.addComponent(jBtnLevelsToModerate, 38, 38, 38);
        }
        vGroup.addGap(39, 39, 39);
        
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vGroup)
        );
	}
	private void initHGroup() {
    	ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        
        hGroup.addGroup(layout.createSequentialGroup());
        if(isModoAdmin){
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addComponent(jBtnLevelsToModerate, GroupLayout.Alignment.LEADING, 762, 762, 762));
        }
        hGroup.addGroup(layout.createSequentialGroup()
            .addComponent(jBtnBasicLevels, 234, 234, 234)
            .addGap(30, 30, 30)
            .addComponent(jBtnCustomLevels, 234, 234, 234)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnMyLevels, 234, 234, 234))
            .addGap(0, 10, Short.MAX_VALUE);
        hGroup.addGroup(layout.createSequentialGroup()
            .addComponent(jLblTitle)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnBack, 131, 131, 131));
        
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(hGroup)
                .addContainerGap())
        );
	}

	private void initTitle() {
    	jLblTitle = new JLabel("Please select a category :");
        jLblTitle.setFont(windsPolice36);
	}

	private void initToModerateButton() {
    	jBtnLevelsToModerate = new JButton();
        jBtnLevelsToModerate.setFont(windsPolice24);
        jBtnLevelsToModerate.setIcon(new ImageIcon("resources/Buttons/LevelsToModerate.png"));
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
				jBtnLevelsToModerate.setIcon(new ImageIcon("resources/Buttons/LevelsToModerate.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnLevelsToModerate.setIcon(new ImageIcon("resources/Buttons/LevelsToModerate_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initMyLevelsButton() {
    	jBtnMyLevels = new JButton();
    	jBtnMyLevels.setFont(windsPolice24);
        jBtnMyLevels.setIcon(new ImageIcon("resources/Buttons/MyLevels.png"));
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
				jBtnMyLevels.setIcon(new ImageIcon("resources/Buttons/MyLevels.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnMyLevels.setIcon(new ImageIcon("resources/Buttons/MyLevels_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initCustomLevelsButton() {
		jBtnCustomLevels = new JButton();
		jBtnCustomLevels.setFont(windsPolice24);
        jBtnCustomLevels.setIcon(new ImageIcon("resources/Buttons/CustomLevels.png"));
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
				jBtnCustomLevels.setIcon(new ImageIcon("resources/Buttons/CustomLevels.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnCustomLevels.setIcon(new ImageIcon("resources/Buttons/CustomLevels_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initBasicLevelsButton() {
    	jBtnBasicLevels = new JButton();
        jBtnBasicLevels.setFont(windsPolice24);
        jBtnBasicLevels.setIcon(new ImageIcon("resources/Buttons/BasicLevels.png"));
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
				jBtnBasicLevels.setIcon(new ImageIcon("resources/Buttons/BasicLevels.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBasicLevels.setIcon(new ImageIcon("resources/Buttons/BasicLevels_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	private void initBackButton() {
    	jBtnBack = new JButton();
        jBtnBack.setFont(windsPolice24);
        jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
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
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back_hover.png"));
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

	private void jBtnBackActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
    }                                        
    private void jBtnBasicLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.basic));
    }                                               
    private void jBtnCustomLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.custom));
    }                                                
    private void jBtnMyLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.my));
    }                                            
    private void jBtnLevelsToModerateActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(Type.toModerate));
    }                                                    



	
}
