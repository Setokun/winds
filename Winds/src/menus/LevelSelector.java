package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import addon.AddonManager;
import addon.JarLevel;
import addon.Level;
import addon.level.Type;
import display.Window;

public class LevelSelector extends JPanel{
	private static final long serialVersionUID = 3603484470467460825L;
	
	private Font windsPolice24 = null, windsPolice36 = null;
    private JButton jBtnNext, jBtnPrevious, jBtnBack;
    private LevelButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15;
    private LevelButton[] buttons = {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15};
    private JLabel jLblTitle, jLblNumPage;
    private int compteur, nbElements, nbPages, numPage = 0;    
    private JarLevel[] jarLevels = new JarLevel[nbElements];
    private ArrayList<JarLevel> levelsToDisplay = new ArrayList<JarLevel>();
    private Type type;
    Boolean hasPrevious = false, hasNext = true;
    JPanel southMiddle, jSouthWest, jSouthEast, south, north, jNorthWest, jNorthEast, middle, grid;
    String title;
    
    /**
     * constructor of the Level Selector GUI
     * @param levelType Type of levels to be displayed : basic, custom, my or tomoderate
     * @param numPage The page number to be displayer first
     */
	public LevelSelector(Type levelType, int numPage) {
		type = levelType;
		this.numPage = numPage;
		
		if(type == Type.BASIC)		 this.title = "Basic levels";
		if(type == Type.CUSTOM)	 this.title = "Custom Levels";
		if(type == Type.MY)		 this.title = "My levels";
		if(type == Type.TOMODERATE) this.title = "Levels to moderate";
		
		jarLevels = AddonManager.getJarLevelsByType(type);
		for (int i = 0; i < jarLevels.length; i++) {
			jarLevels[i].getLevel().getName();
		}
		nbElements = jarLevels.length;
		
		nbPages = nbElements / 15;
    	compteur = nbElements % 15;
    	if(compteur == 0 && nbElements != 0) compteur = 15;
		
    	int[] idThemesInstalled = AddonManager.getThemesInstalledIds();
    	
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			if(Level.getStatus(jarLevels[i].getLevel().getIdDB()) == null || !Level.getStatus(jarLevels[i].getLevel().getIdDB()).equals("desactivated")){
				for (int j = 0; j < idThemesInstalled.length; j++) {
					if(idThemesInstalled[j] == jarLevels[i].getLevel().getIdTheme()){
						levelsToDisplay.add(jarLevels[i]);
						break;
					}
				}				
			}
		}
		this.removeAll();
		
		initComponents(numPage, nbPages, levelsToDisplay);
	}
	
	/**
	 * initialize the components on start or on refreshing the GUI when clicking on next or previous
	 * @param currentPage The page number to display
	 * @param nbPages The total of pages
	 * @param levelsToDisplay The ArrayList of levels for this page number
	 */
	private void initComponents(int currentPage, int nbPages, ArrayList<JarLevel> levelsToDisplay) {
		this.setLayout(new BorderLayout());
		
		initializeFont();
		initTitle();
		initBackButton();
		initPreviousButton();
		initNextButton();
		initPageNumber();
		
		createNorth();
		createSouth();
		createMiddle(currentPage, levelsToDisplay);
		
		this.add(north, BorderLayout.NORTH);
		this.add(middle, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		this.validate();
	}

	/**
	 * create the north section of this GUI
	 */
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
		//north.add(southMiddle, BorderLayout.CENTER);
		north.add(jNorthEast, BorderLayout.EAST);
		
	}
	/**
	 * create the middle section of this GUI
	 * @param currentPage The page number to display
	 * @param levelsToDisplay The ArrayList of levels for this page number
	 */
	private void createMiddle(int currentPage, ArrayList<JarLevel> levelsToDisplay) {
		
		middle = new JPanel();
		
		FlowLayout flMiddle = new FlowLayout();
		middle.setLayout(flMiddle);
		
		if(levelsToDisplay.size() == 0){
			JPanel nothingToDisplay = new JPanel();
			FlowLayout flNothingToDisplay = new FlowLayout();
			flNothingToDisplay.setVgap(160);
			nothingToDisplay.setLayout(flNothingToDisplay);
			nothingToDisplay.add(new JLabel("No levels available for this category, please go to the \"Shop\" menu to download Themes and Levels !"));
			
			middle.add(nothingToDisplay);
		}
		else{
			grid = new JPanel();
			GridLayout gridLayout = new GridLayout(0,5);
			gridLayout.setHgap(50);
			gridLayout.setVgap(30);
			grid.setLayout(gridLayout);
			
			for (int i = 0; i < levelsToDisplay.size(); i++) {
				buttons[i] = new LevelButton(levelsToDisplay.get(i), type, currentPage);
				grid.add(buttons[i].getButton());
			}
			
			middle.add(grid);
		}

	}
	/**
	 * create the south section of this GUI
	 */
	private void createSouth() {
		southMiddle = new JPanel();
		FlowLayout flSouthMiddle = new FlowLayout();
		flSouthMiddle.setVgap(25);
		southMiddle.setLayout(flSouthMiddle);
		
		jLblNumPage.setFont(windsPolice24);
        jLblNumPage.setText((numPage+1)+"/"+(nbPages+1));
		southMiddle.add(jLblNumPage);
        
		
		//////
		JButton jBtnPreviousBlank = new JButton();
		jBtnPreviousBlank.setPreferredSize(new Dimension(100,50));
		jBtnPreviousBlank.setBorder(new SoftBevelBorder(0));
		jBtnPreviousBlank.setBorderPainted(false);
		jBtnPreviousBlank.setContentAreaFilled(false);
		
		jSouthWest = new JPanel();
		FlowLayout flSouthWest = new FlowLayout();
		flSouthWest.setHgap(10);
		flSouthWest.setVgap(5);
		jSouthWest.setLayout(flSouthWest);
		jSouthWest.add((hasPrevious)?jBtnPrevious:jBtnPreviousBlank);
		//////
		
		JButton jBtnNextBlank = new JButton();
		jBtnNextBlank.setPreferredSize(new Dimension(100,50));
		jBtnNextBlank.setBorder(new SoftBevelBorder(0));
		jBtnNextBlank.setBorderPainted(false);
		jBtnNextBlank.setContentAreaFilled(false);
		
		jSouthEast = new JPanel();
		FlowLayout flSouthEast = new FlowLayout();
		flSouthEast.setHgap(10);
		flSouthEast.setVgap(5);
		jSouthEast.setLayout(flSouthEast);
		jSouthEast.add((hasNext)?jBtnNext:jBtnNextBlank);

		south = new JPanel();
		BorderLayout southLayout = new BorderLayout();
		south.setLayout(southLayout);
		
		south.add(jSouthWest, BorderLayout.WEST);
		south.add(southMiddle, BorderLayout.CENTER);
		south.add(jSouthEast, BorderLayout.EAST);
	}

	/**
	 * initialize Previous button
	 */
	private void initPreviousButton() {
		jBtnPrevious = new JButton();
		jBtnPrevious.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Prev.png")));
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
				jBtnPrevious.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Prev.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnPrevious.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Prev_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	/**
	 * initialize Next button
	 */
	private void initNextButton() {
		jBtnNext = new JButton();
		jBtnNext.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Next.png")));
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
				jBtnNext.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Next.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnNext.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Next_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	/**
	 * initialize Back button
	 */
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
	/**
	 * initialize title of this GUI
	 */
	private void initTitle() {	
        jLblTitle = new JLabel();
        jLblTitle.setFont(windsPolice36);
        jLblTitle.setText(title);        
	}
	/**
	 * initialize page numbers
	 */
	private void initPageNumber() {
		jLblNumPage = new JLabel();
		hasPrevious = (numPage != 0);
		hasNext = (numPage != nbPages);
	}
	/**
	 * initialize custom font
	 */
	private void initializeFont() {
		try {
    		windsPolice24 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,24F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice24 = new Font ("Serif", Font.BOLD, 24);
    		windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
	}
	/**
	 * determines what action has to be done when clicking on the "Back" button
	 * @param evt
	 */
	private void jBtnBackActionPerformed(ActionEvent evt) {
		Window.affect(new LevelCategorySelector(),Window.DIM_STANDARD);
    }
	/**
	 * determines what action has to be done when clicking on the "Previous" button
	 * @param evt
	 */
	protected void jBtnPreviousActionPerformed(ActionEvent evt) {
		numPage--;
				
		levelsToDisplay.removeAll(levelsToDisplay);
		
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(jarLevels[i]);
		}
		this.removeAll();
		initComponents(numPage, nbPages, levelsToDisplay);
	}
	/**
	 * determines what action has to be done when clicking on the "Next" button
	 * @param evt
	 */
	protected void jBtnNextActionPerformed(ActionEvent evt) {
		numPage++;
		
		levelsToDisplay.removeAll(levelsToDisplay);
		for(int i= (numPage * 15); i<((numPage == nbPages)?(numPage * 15) + compteur:((numPage+1) * 15)); i++){
			levelsToDisplay.add(jarLevels[i]);
		}
		this.removeAll();
		initComponents(numPage, nbPages, levelsToDisplay);
	}
	
	
	
}
