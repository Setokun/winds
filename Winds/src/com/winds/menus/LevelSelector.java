package com.winds.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.SoftBevelBorder;

import com.winds.addons.Level;
import com.winds.display.Game;
import com.winds.display.Window;

public class LevelSelector extends JPanel{
	private static final long serialVersionUID = 9194645999792575062L;
	
    //region Variables declaration
    private JButton jBtnNext, jBtnPrevious, jBtnBack;
    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8;
    private JButton jButton9, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15;
    
    private JLabel jLblTitle, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private JLabel jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLblNumPage;
    
    private int compteur, nbElements = 117, nbPages, numPage = 0;
    
    private Level[] levels = new Level[nbElements];
    private ArrayList<Level> levelsToDisplay = new ArrayList<Level>();
    
    private String[] adressesLogo = {"res/logo-ice-2.png", "res/logo-pirate-2.png", "res/logo-ronces-2.png", "res/logo-honey-2.png"};
    Random rand = new Random();
    int indexRand;
    
    String title;
    
    //endregion
    
	public LevelSelector(String title) {
        
		this.title = title;
		
		nbPages = (nbElements / 15) ;
    	compteur = nbElements % 15;
    	if(compteur == 0 && nbElements != 0) compteur = 15;
		
		
		for(int i=0; i<nbElements; i++){
			indexRand = rand.nextInt(adressesLogo.length);
			Level bl = new Level(adressesLogo[indexRand], "level pirate n°" + (i+1));
			levels[i] = bl;
		}
		
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(levels[i]);
		}
		this.removeAll();
		initComponents(title, numPage, nbPages, levelsToDisplay);
    }

    private void initComponents(String title, int currentPage, int nbPages, ArrayList<Level> levels) {

    	setPreferredSize(new Dimension(800, 550));
    	
    	int nbLvlsToDisplay = levelsToDisplay.size();
    	
    	//region : instantiations
        jBtnBack = new JButton();
        jLblTitle = new JLabel();
        
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
        jButton9 = new JButton();
        jButton10 = new JButton();
        jButton11 = new JButton();
        jButton12 = new JButton();
        jButton13 = new JButton();
        jButton14 = new JButton();
        jButton15 = new JButton();
        
        
        jBtnNext = new JButton();
        jBtnPrevious = new JButton();
        
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        jLblNumPage = new JLabel();
        //endregion
        
        //region : layouts
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        Group group1 = layout.createSequentialGroup();
        Group group2 = layout.createSequentialGroup();
        Group group3 = layout.createSequentialGroup();
        
        Group vGroupTitle = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup1labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup2Labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	    Group vGroup3Labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

        Group totalLines = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vBtnPrevAndNext = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        //endregion
	    
        //region : header
        jBtnBack.setFont(new Font("bubble & soap", 0, 24));
        jBtnBack.setIcon(new ImageIcon("res/Buttons/Back.png"));
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
				jBtnBack.setIcon(new ImageIcon("res/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("res/Buttons/Back_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        jLblTitle.setFont(new Font("bubble & soap", 0, 36));
        jLblTitle.setText(title);
        
        
        
        vGroupTitle.addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE));
        vGroupTitle.addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGap(50, 50, 50)
                    .addComponent(jLblTitle));
        //endregion
        
        //region : elements preparation
        switch(nbLvlsToDisplay){
        case 15:
        	jButton15.setIcon(new javax.swing.ImageIcon(levels.get(14).adresseImage));
            jButton15.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton15.setBorderPainted(false);
            jButton15.setContentAreaFilled(false);
            jButton15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Window.resize(new Dimension(1024, 768));
					Window.affect(new Game());
				}
			});
            jLabel15.setText(levels.get(14).titreNiveau);
            
            vGroup3.addComponent(jButton15, 64, 64, 64);
        	vGroup3Labels.addComponent(jLabel15);
        case 14:
            jButton14.setIcon(new javax.swing.ImageIcon(levels.get(13).adresseImage));
            jButton14.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton14.setBorderPainted(false);
            jButton14.setContentAreaFilled(false);
            jLabel14.setText(levels.get(13).titreNiveau);
            
            vGroup3.addComponent(jButton14, 64, 64, 64);
    	    vGroup3Labels.addComponent(jLabel14);
        case 13:
        	jButton13.setIcon(new javax.swing.ImageIcon(levels.get(12).adresseImage));
            jButton13.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton13.setBorderPainted(false);
            jButton13.setContentAreaFilled(false);
            jLabel13.setText(levels.get(12).titreNiveau);
            
            vGroup3.addComponent(jButton13, 64, 64, 64);
        	vGroup3Labels.addComponent(jLabel13);
        case 12:
        	jButton12.setIcon(new javax.swing.ImageIcon(levels.get(11).adresseImage));
            jButton12.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton12.setBorderPainted(false);
            jButton12.setContentAreaFilled(false);
            jLabel12.setText(levels.get(11).titreNiveau);
            
            vGroup3.addComponent(jButton12, 64, 64, 64);
        	vGroup3Labels.addComponent(jLabel12);
        case 11:
        	jButton11.setIcon(new javax.swing.ImageIcon(levels.get(10).adresseImage));
            jButton11.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton11.setBorderPainted(false);
            jButton11.setContentAreaFilled(false);
            jLabel11.setText(levels.get(10).titreNiveau);
            
            vGroup3.addComponent(jButton11, 64, 64, 64);
        	vGroup3Labels.addComponent(jLabel11);
        case 10:
        	jButton10.setIcon(new javax.swing.ImageIcon(levels.get(9).adresseImage));
            jButton10.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton10.setBorderPainted(false);
            jButton10.setContentAreaFilled(false);
            jLabel10.setText(levels.get(9).titreNiveau);
            
            vGroup2Labels.addComponent(jLabel10);
        	vGroup2.addComponent(jButton10, 64, 64, 64);
        case 9:
        	jButton9.setIcon(new javax.swing.ImageIcon(levels.get(8).adresseImage));
            jButton9.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton9.setBorderPainted(false);
            jButton9.setContentAreaFilled(false);
            jLabel9.setText(levels.get(8).titreNiveau);
            
            vGroup2.addComponent(jButton9, 64, 64, 64);
        	vGroup2Labels.addComponent(jLabel9);
        case 8:
        	jButton8.setIcon(new javax.swing.ImageIcon(levels.get(7).adresseImage));
            jButton8.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton8.setBorderPainted(false);
            jButton8.setContentAreaFilled(false);
            jLabel8.setText(levels.get(7).titreNiveau);
            
            vGroup2.addComponent(jButton8, 64, 64, 64);
        	vGroup2Labels.addComponent(jLabel8);
        case 7:
        	jButton7.setIcon(new javax.swing.ImageIcon(levels.get(6).adresseImage));
            jButton7.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton7.setBorderPainted(false);
            jButton7.setContentAreaFilled(false);
            jLabel7.setText(levels.get(6).titreNiveau);
            
            vGroup2.addComponent(jButton7, 64, 64, 64);
        	vGroup2Labels.addComponent(jLabel7);
        case 6:
        	jButton6.setIcon(new javax.swing.ImageIcon(levels.get(5).adresseImage));
            jButton6.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton6.setBorderPainted(false);
            jButton6.setContentAreaFilled(false);
            jLabel6.setText(levels.get(5).titreNiveau);
            
            vGroup2.addComponent(jButton6, 64, 64, 64);
        	vGroup2Labels.addComponent(jLabel6);
        case 5:
        	jButton5.setIcon(new javax.swing.ImageIcon(levels.get(4).adresseImage));
            jButton5.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton5.setBorderPainted(false);
            jButton5.setContentAreaFilled(false);
            jLabel5.setText(levels.get(4).titreNiveau);
            
            vGroup1.addComponent(jButton5, 64, 64, 64);
        	vGroup1labels.addComponent(jLabel5);
        case 4:
        	jButton4.setIcon(new javax.swing.ImageIcon(levels.get(3).adresseImage));
            jButton4.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton4.setBorderPainted(false);
            jButton4.setContentAreaFilled(false);
            jLabel4.setText(levels.get(3).titreNiveau);
            
            vGroup1.addComponent(jButton4, 64, 64, 64);
        	vGroup1labels.addComponent(jLabel4);
        case 3:
        	jButton3.setIcon(new javax.swing.ImageIcon(levels.get(2).adresseImage));
            jButton3.setBorder(new javax.swing.border.SoftBevelBorder(0));
            jButton3.setBorderPainted(false);
            jButton3.setContentAreaFilled(false);
            jLabel3.setText(levels.get(2).titreNiveau);
            
            vGroup1.addComponent(jButton3, 64, 64, 64);
        	vGroup1labels.addComponent(jLabel3);
        case 2:
        	jButton2.setIcon(new javax.swing.ImageIcon(levels.get(1).adresseImage));
	        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(0));
	        jButton2.setBorderPainted(false);
	        jButton2.setContentAreaFilled(false);
	        jLabel2.setText(levels.get(1).titreNiveau);
	        
	        vGroup1.addComponent(jButton2, 64, 64, 64);
        	vGroup1labels.addComponent(jLabel2);
        case 1:
        	jButton1.setIcon(new javax.swing.ImageIcon(levels.get(0).adresseImage));
	        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(0));
	        jButton1.setBorderPainted(false);
	        jButton1.setContentAreaFilled(false);
	        jLabel1.setText(levels.get(0).titreNiveau);
	        
	        vGroup1.addComponent(jButton1, 64, 64, 64);
        	vGroup1labels.addComponent(jLabel1);
        }
        //endregion
        
        //region : lines preparation
        int marginBetweenElements = 124;
        
        if(nbLvlsToDisplay > 10){
        	if(nbLvlsToDisplay > 10){
        		int largeur = marginBetweenElements - (jLabel11.getFontMetrics(jLabel11.getFont()).stringWidth(jLabel11.getText()));
				group1.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        .addComponent(jButton11, 64, 64, 64)
			        .addGroup(layout.createParallelGroup()
			            .addGap(0, 0, 0)
			            .addComponent(jLabel11)))
			    .addGap(largeur,largeur,largeur);
        	}
        	if(nbLvlsToDisplay > 11){
        		int largeur = marginBetweenElements - (jLabel12.getFontMetrics(jLabel12.getFont()).stringWidth(jLabel12.getText()));
        		group1.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
			        .addGroup(layout.createSequentialGroup()
			            .addGap(0, 0, 0)
			            .addComponent(jLabel12)))
			    .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 12){
        		int largeur = marginBetweenElements - (jLabel13.getFontMetrics(jLabel13.getFont()).stringWidth(jLabel13.getText()));
        		group1.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			        .addComponent(jButton13, 64, 64, 64)
			        .addGroup(layout.createSequentialGroup()
			            .addGap(0, 0, 0)
			            .addComponent(jLabel13)))
			    .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 13){
        		int largeur = marginBetweenElements - (jLabel14.getFontMetrics(jLabel14.getFont()).stringWidth(jLabel14.getText()));
			    group1.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
			        .addGroup(layout.createSequentialGroup()
			            .addGap(0, 0, 0)
			            .addComponent(jLabel14)))
			    .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 14){
        		int largeur = marginBetweenElements - (jLabel15.getFontMetrics(jLabel15.getFont()).stringWidth(jLabel15.getText()));
			    group1.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
			        .addGroup(layout.createSequentialGroup()
			            .addGap(0, 0, 0)
			            .addComponent(jLabel15)))
			    .addGap(largeur, largeur, largeur);
        	}
        }
        
        if(nbLvlsToDisplay > 5){
        	if(nbLvlsToDisplay > 5){
        		int largeur = marginBetweenElements - (jLabel6.getFontMetrics(jLabel6.getFont()).stringWidth(jLabel6.getText()));
        		group2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel6)))
		                .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 6){
        		int largeur = marginBetweenElements - (jLabel7.getFontMetrics(jLabel7.getFont()).stringWidth(jLabel7.getText()));
		        group2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel7)))
		                .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 7){
        		int largeur = marginBetweenElements - (jLabel8.getFontMetrics(jLabel8.getFont()).stringWidth(jLabel8.getText()));
		        group2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel8)))
		                .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 8){
        		int largeur = marginBetweenElements - (jLabel9.getFontMetrics(jLabel9.getFont()).stringWidth(jLabel9.getText()));
		        group2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel9)))
		                .addGap(largeur, largeur, largeur);
        	}
        	if(nbLvlsToDisplay > 9){
        		int largeur = marginBetweenElements - (jLabel10.getFontMetrics(jLabel10.getFont()).stringWidth(jLabel10.getText()));
		        group2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel10)))
		                .addGap(largeur, largeur, largeur);
        	}
        }
	    
    	if(nbLvlsToDisplay > 0){
    		if(nbLvlsToDisplay > 0){
    			int largeur = marginBetweenElements - (jLabel1.getFontMetrics(jLabel1.getFont()).stringWidth(jLabel1.getText()));
		        group3.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel1)))
		        .addGap(largeur, largeur, largeur);
    		}
    		if(nbLvlsToDisplay > 1){
    			int largeur = marginBetweenElements - (jLabel2.getFontMetrics(jLabel2.getFont()).stringWidth(jLabel2.getText()));
		        group3.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel2)))
		        .addGap(largeur, largeur, largeur);
    		}
    		if(nbLvlsToDisplay > 2){
    			int largeur = marginBetweenElements - (jLabel3.getFontMetrics(jLabel3.getFont()).stringWidth(jLabel3.getText()));
		        group3.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel3)))
		        .addGap(largeur, largeur, largeur);
    		}
    		if(nbLvlsToDisplay > 3){
    			int largeur = marginBetweenElements - (jLabel4.getFontMetrics(jLabel4.getFont()).stringWidth(jLabel4.getText()));
		        group3.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel4)))
		        .addGap(largeur, largeur, largeur);
    		}
    		if(nbLvlsToDisplay > 4){
    			int largeur = marginBetweenElements - (jLabel5.getFontMetrics(jLabel5.getFont()).stringWidth(jLabel5.getText()));
		        group3.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(0, 0, 0)
		                .addComponent(jLabel5)))
		        .addGap(largeur, largeur, largeur);
    		}
    	}
        //endregion
    	
        //region : footer initializations
        
        jBtnNext.setIcon(new ImageIcon("res/Buttons/Next.png"));
        jBtnNext.setBorder(new SoftBevelBorder(0));
        jBtnNext.setBorderPainted(false);
        jBtnNext.setContentAreaFilled(false);
        jBtnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jBtnNextActionPerformed(evt);
            }
        });
        jBtnNext.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnNext.setIcon(new ImageIcon("res/Buttons/Next.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnNext.setIcon(new ImageIcon("res/Buttons/Next_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        jBtnPrevious.setIcon(new ImageIcon("res/Buttons/Prev.png"));
        jBtnPrevious.setBorder(new SoftBevelBorder(0));
        jBtnPrevious.setBorderPainted(false);
        jBtnPrevious.setContentAreaFilled(false);
        jBtnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jBtnPreviousActionPerformed(evt);
            }
        });
        jBtnPrevious.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnPrevious.setIcon(new ImageIcon("res/Buttons/Prev.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnPrevious.setIcon(new ImageIcon("res/Buttons/Prev_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        jLblNumPage.setFont(new java.awt.Font("bubble & soap", 0, 24));
        jLblNumPage.setText((currentPage+1)+"/"+(nbPages+1));
        //endregion
        
        //region : footer
        
        Group hBtnPrevAndNext = layout.createSequentialGroup().addContainerGap();
        if(currentPage != 0)hBtnPrevAndNext.addComponent(jBtnPrevious, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE);
        
        if(currentPage != nbPages)
        	((SequentialGroup) hBtnPrevAndNext).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        else
        	hBtnPrevAndNext.addGap(265);
        
        if(nbPages>0)hBtnPrevAndNext.addComponent(jLblNumPage, 305,305,305);	
        
        if(currentPage != nbPages){
        	hBtnPrevAndNext.addComponent(jBtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE);
        }

        //endregion
        
        //region : horizontal fill
        if(nbLvlsToDisplay > 10)
        	totalLines.addGroup(group1); // 3ème ligne
        if(nbLvlsToDisplay > 5)
        	totalLines.addGroup(group2); // 2ème ligne
        if(nbLvlsToDisplay > 0)
        	totalLines.addGroup(group3); // 1ère ligne
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(totalLines)
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLblTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
                    .addGroup(hBtnPrevAndNext))
                .addContainerGap())
        );
        //endregion

        //region : vertical fill
        if(currentPage != nbPages)vBtnPrevAndNext.addComponent(jBtnNext, 50, 50, 50);
        if(nbPages>0)vBtnPrevAndNext.addComponent(jLblNumPage);
        if(currentPage != 0)vBtnPrevAndNext.addComponent(jBtnPrevious, 50, 50, 50);
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(vGroupTitle)
                .addGap(50, 50, 50)
                .addGroup(vGroup1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vGroup1labels)
                .addGap(18, 18, 18)
                .addGroup(vGroup2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vGroup2Labels)
                .addGap(18, 18, 18)
                .addGroup(vGroup3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vGroup3Labels)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(vBtnPrevAndNext)
                .addContainerGap())
        );
        //endregion
    
    }

	private void jBtnBackActionPerformed(ActionEvent evt) {
    	Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
    }   
	
	protected void jBtnPreviousActionPerformed(ActionEvent evt) {
		numPage--;
				
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(levels[i]);
		}
		this.removeAll();
		initComponents(title, numPage, nbPages, levelsToDisplay);
	}

	protected void jBtnNextActionPerformed(ActionEvent evt) {
		numPage++;
		
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(levels[i]);
		}
		this.removeAll();
		initComponents(title, numPage, nbPages, levelsToDisplay);
	}
}
