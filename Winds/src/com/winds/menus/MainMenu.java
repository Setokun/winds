package com.winds.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import com.winds.display.Window;

public class MainMenu extends JPanel{

	private JButton jBtnChangeProfile, jBtnConfiguration, jBtnLevelEditor, jBtnPlay, jBtnQuit, jBtnScores, jBtnShop;
    private JLabel jLblTitle;
	
	public MainMenu() {
        initComponents();
    }
                        
    private void initComponents() {

        jLblTitle = new JLabel();
        jBtnPlay = new JButton();
        jBtnScores = new JButton();
        jBtnConfiguration = new JButton();
        jBtnShop = new JButton();
        jBtnLevelEditor = new JButton();
        jBtnChangeProfile = new JButton();
        jBtnQuit = new JButton();

        jLblTitle.setFont(new Font("bubble & soap", 0, 48));
        jLblTitle.setText("Winds");

        jBtnPlay.setFont(new Font("bubble & soap", 0, 18));
        jBtnPlay.setText("Play");
        jBtnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnPlayActionPerformed(evt);
            }
        });

        jBtnScores.setFont(new Font("bubble & soap", 0, 18));
        jBtnScores.setText("Scores");
        jBtnScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnScoresActionPerformed(evt);
            }
        });

        jBtnConfiguration.setFont(new Font("bubble & soap", 0, 18));
        jBtnConfiguration.setText("Configuration");
        jBtnConfiguration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnConfigurationActionPerformed(evt);
            }
        });

        jBtnShop.setFont(new Font("bubble & soap", 0, 18));
        jBtnShop.setText("shop");
        jBtnShop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnShopActionPerformed(evt);
            }
        });

        jBtnLevelEditor.setFont(new Font("bubble & soap", 0, 18));
        jBtnLevelEditor.setText("level editor");
        jBtnLevelEditor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnLevelEditorActionPerformed(evt);
            }
        });

        jBtnChangeProfile.setFont(new Font("bubble & soap", 0, 18));
        jBtnChangeProfile.setText("Change Profile");
        jBtnChangeProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnChangeProfileActionPerformed(evt);
            }
        });

        jBtnQuit.setFont(new Font("bubble & soap", 0, 18));
        jBtnQuit.setText("Quit");
        jBtnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnQuitActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addGap(241, 241, 241))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnScores, 300, 300, 300)
                            .addComponent(jBtnPlay, 300, 300, 300)
                            .addComponent(jBtnConfiguration, 300, 300, 300)
                            .addComponent(jBtnShop, 300, 300, 300)
                            .addComponent(jBtnLevelEditor, 300, 300, 300)
                            .addComponent(jBtnChangeProfile, 300, 300, 300)
                            .addComponent(jBtnQuit, 300, 300, 300))
                        .addGap(167, 167, 167))))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnPlay, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnScores, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnConfiguration, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnShop, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLevelEditor, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnChangeProfile, 50, 50, 50)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnQuit, 50, 50, 50)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }

    private void jBtnPlayActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
    }                                        

    private void jBtnScoresActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	// TODO add your handling code here:
    }  
    
    private void jBtnConfigurationActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	// TODO add your handling code here:
    }                                                 
    
    private void jBtnShopActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jBtnLevelEditorActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void jBtnChangeProfileActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	Window.resize(new Dimension(640, 500));
		Window.affect(new Login());
    }                                                 

    private void jBtnQuitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(1);
    }                                        


}
