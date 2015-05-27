package menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.SoftBevelBorder;

import display.Window;
import annotation.wLevel;

public class LevelCategorySelector extends JPanel{
	private static final long serialVersionUID = 9194645999792575062L;
	
    //region Variables declaration
    private javax.swing.JButton jBtnBack;
    private javax.swing.JButton jBtnBasicLevels;
    private javax.swing.JButton jBtnCustomLevels;
    private javax.swing.JButton jBtnLevelsToModerate;
    private javax.swing.JButton jBtnMyLevels;
    private javax.swing.JLabel jLblTitle;
    //endregion
	
	public LevelCategorySelector() {
        initComponents();
    }

	private void initComponents() {

        jBtnBack = new JButton();
        jBtnBasicLevels = new JButton();
        jBtnCustomLevels = new JButton();
        jBtnMyLevels = new JButton();
        jBtnLevelsToModerate = new JButton();
        jLblTitle = new JLabel();

        setPreferredSize(new Dimension(800, 550));

        jBtnBack.setFont(new Font("bubble & soap", 0, 24));
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
        
        
        jBtnBasicLevels.setFont(new Font("bubble & soap", 0, 24));
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
        
        jBtnCustomLevels.setFont(new Font("bubble & soap", 0, 24));
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
        
        jBtnMyLevels.setFont(new Font("bubble & soap", 0, 24));
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
        
        jBtnLevelsToModerate.setFont(new Font("bubble & soap", 0, 24));
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
        
        jLblTitle.setFont(new Font("bubble & soap", 0, 36));
        jLblTitle.setText("Please select a category :");

        javax.swing.GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jBtnLevelsToModerate, GroupLayout.Alignment.LEADING, 762, 762, 762)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnBasicLevels, 234, 234, 234)
                                .addGap(30, 30, 30)
                                .addComponent(jBtnCustomLevels, 234, 234, 234)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtnMyLevels, 234, 234, 234)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnBack, 131, 131, 131)))
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBtnBack, 63, 63, 63))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLblTitle)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnBasicLevels, 319, 319, 319)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnCustomLevels, 319, 319, 319)
                        .addComponent(jBtnMyLevels, 319, 319, 319)))
                .addGap(18, 18, 18)
                .addComponent(jBtnLevelsToModerate, 38, 38, 38)
                .addGap(39, 39, 39))
        );
    }                        

    private void jBtnBackActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
    }                                        

    private void jBtnBasicLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(wLevel.TYPE_BASIC));
    }                                               

    private void jBtnCustomLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(wLevel.TYPE_CUSTOM));
    }                                                

    private void jBtnMyLevelsActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(wLevel.TYPE_MY));
    }                                            

    private void jBtnLevelsToModerateActionPerformed(java.awt.event.ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelSelector(wLevel.TYPE_TOMODERATE));
    }                                                    



	
}
