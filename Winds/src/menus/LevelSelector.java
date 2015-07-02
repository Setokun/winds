package menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.SoftBevelBorder;

import addon.AddonManager;
import addon.JarLevel;
import addon.level.Type;
import database.LevelData;
import display.Window;

public class LevelSelector extends JPanel{
	private static final long serialVersionUID = 9194645999792575062L;
	
    //region Variables declaration
	private Font windsPolice24 = null, windsPolice36 = null;
    private JButton jBtnNext, jBtnPrevious, jBtnBack;
    private LevelButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8;
    private LevelButton jButton9, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15;
    
    private JLabel jLblTitle, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private JLabel jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLblNumPage;
    
    private int compteur, nbElements, nbPages, numPage = 0;
    
    private JarLevel[] jarLevels = new JarLevel[nbElements];
    private ArrayList<JarLevel> levelsToDisplay = new ArrayList<JarLevel>();
    
    String title;
    
    //endregion
    
	public LevelSelector(Type levelType) {
        
		if(levelType == Type.basic)		 this.title = "Basic levels";
		if(levelType == Type.custom)	 this.title = "Custom Levels";
		if(levelType == Type.my)		 this.title = "My levels";
		if(levelType == Type.toModerate) this.title = "Levels to moderate";
		
		jarLevels = AddonManager.getJarLevelsByType(levelType);
		for (int i = 0; i < jarLevels.length; i++) {
			jarLevels[i].getLevel().getName();
		}
		nbElements = jarLevels.length;
		
		nbPages = nbElements / 15;
    	compteur = nbElements % 15;
    	if(compteur == 0 && nbElements != 0) compteur = 15;
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			if(LevelData.getStatus(jarLevels[i].getLevel().getIdDB()) == null || !LevelData.getStatus(jarLevels[i].getLevel().getIdDB()).equals("desactivated"))
				levelsToDisplay.add(jarLevels[i]);
		}
		this.removeAll();
		
		initComponents(title, numPage, nbPages, levelsToDisplay);
    }

    private void initComponents(String title, int currentPage, int nbPages, ArrayList<JarLevel> levels) {

    	initializeFont();
    	
    	int nbLvlsToDisplay = 0;
    	if(levelsToDisplay != null) nbLvlsToDisplay = levelsToDisplay.size();
    	
        initButtonsAndLabels();
        
        //region : layouts
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        Group group1 = layout.createSequentialGroup();
        Group group2 = layout.createSequentialGroup();
        Group group3 = layout.createSequentialGroup();
        
        Group vGroupTitle = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup1Labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup2Labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vGroup3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	    Group vGroup3Labels = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

        Group totalLines = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        Group vBtnPrevAndNext = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        //endregion
	    
        //region : header
        initBackButton();
        
        jLblTitle.setFont(windsPolice36);
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
        case 15: jButton15.affect(levels.get(14), vGroup3, vGroup3Labels, jLabel15);
        case 14: jButton14.affect(levels.get(13), vGroup3, vGroup3Labels, jLabel14);
        case 13: jButton13.affect(levels.get(12), vGroup3, vGroup3Labels, jLabel13);
        case 12: jButton12.affect(levels.get(11), vGroup3, vGroup3Labels, jLabel12);
        case 11: jButton11.affect(levels.get(10), vGroup3, vGroup3Labels, jLabel11);
        case 10: jButton10.affect(levels.get(9), vGroup2, vGroup2Labels, jLabel10);
        case 9: jButton9.affect(levels.get(8), vGroup2, vGroup2Labels, jLabel9);
        case 8: jButton8.affect(levels.get(7), vGroup2, vGroup2Labels, jLabel8);
        case 7: jButton7.affect(levels.get(6), vGroup2, vGroup2Labels, jLabel7);
        case 6: jButton6.affect(levels.get(5), vGroup2, vGroup2Labels, jLabel6);
        case 5: jButton5.affect(levels.get(4), vGroup1, vGroup1Labels, jLabel5);
        case 4: jButton4.affect(levels.get(3), vGroup1, vGroup1Labels, jLabel4);
        case 3: jButton3.affect(levels.get(2), vGroup1, vGroup1Labels, jLabel3);
        case 2: jButton2.affect(levels.get(1), vGroup1, vGroup1Labels, jLabel2);
        case 1: jButton1.affect(levels.get(0), vGroup1, vGroup1Labels, jLabel1);
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
        
        initNextButton();
        
        initPreviousButton();
        
        jLblNumPage.setFont(windsPolice24);
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
                .addGroup(vGroup1Labels)
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
    
    }

	private void initPreviousButton() {
		jBtnPrevious.setIcon(new ImageIcon("resources/Buttons/Prev.png"));
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
				jBtnPrevious.setIcon(new ImageIcon("resources/Buttons/Prev.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnPrevious.setIcon(new ImageIcon("resources/Buttons/Prev_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initNextButton() {
		jBtnNext.setIcon(new ImageIcon("resources/Buttons/Next.png"));
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
				jBtnNext.setIcon(new ImageIcon("resources/Buttons/Next.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnNext.setIcon(new ImageIcon("resources/Buttons/Next_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initBackButton() {
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

	private void initButtonsAndLabels() {
		jBtnBack = new JButton();
        jLblTitle = new JLabel();
        
        jButton1 = new LevelButton();
        jButton2 = new LevelButton();
        jButton3 = new LevelButton();
        jButton4 = new LevelButton();
        jButton5 = new LevelButton();
        jButton6 = new LevelButton();
        jButton7 = new LevelButton();
        jButton8 = new LevelButton();
        jButton9 = new LevelButton();
        jButton10 = new LevelButton();
        jButton11 = new LevelButton();
        jButton12 = new LevelButton();
        jButton13 = new LevelButton();
        jButton14 = new LevelButton();
        jButton15 = new LevelButton();
        
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
		Window.affect(new LevelCategorySelector());
    }   
	
	protected void jBtnPreviousActionPerformed(ActionEvent evt) {
		numPage--;
				
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(jarLevels[i]);
		}
		this.removeAll();
		initComponents(title, numPage, nbPages, levelsToDisplay);
	}

	protected void jBtnNextActionPerformed(ActionEvent evt) {
		numPage++;
		
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(jarLevels[i]);
		}
		this.removeAll();
		initComponents(title, numPage, nbPages, levelsToDisplay);
	}
}
