package leveleditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import leveleditor.EditorListener.BackListener;
import leveleditor.EditorListener.DescriptionListener;
import leveleditor.EditorListener.SaveListener;
import leveleditor.EditorListener.TimeMaxListener;
import addon.JarLevel;
import addon.JarTheme;
import addon.Level;
import core.SpriteSheet;


public class EditorGUI extends JPanel {
	private static final long serialVersionUID = -4428661135236351743L;
	public  static final int MINIMUM_TIME = 60;
	public  static final int NB_TILES_MATRIX = 60;
	public  static final String PROMPT_TIMEMAX = "Number of seconds";
	public  static final String PROMPT_DESCRIPTION = "Input your level description here";
	
	private final int MARGIN_TILES = 1;
	private final int NB_COLS_LEGEND = 3;
	private final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
	
	public  static JPanel current;
    public  static Tile tileCurrent, departure, arrival;
    public  static Image[] backImages, frontImages;
    public  static Map<Point, Integer[]> spritesComp;
    public  static int[][] intersComp;
    private static JPanel gridMatrix;
		
	private JButton btnSave, btnBack, btnEmpty;
    private JLabel lblCurrent, lblDescription, lblLevel, lblTheme, lblTimeMax;
	private JPanel header, labels, fields, description, legend;	
    private JPanel gridSprites, gridInteractions;
    private JScrollPane scrollMatrix, scrollSprites, scrollInteractions;
    private JSeparator sep1, sep2;
    private JTabbedPane tabPane;
    private JTextArea areaDescription;
    private JTextField txtLevel, txtTheme, txtTimeMax;
	private JarLevel jarLevelUsed;
	private JarTheme jarThemeUsed;
	private Font windsPolice24 = null;
	private String[] intersTips;
	
	
	//region Constructors 
	/**
	 * Construct a new Editor GUI panel for the specified JarLevel and JarTheme.
	 * @param jl	JarLevel to manipulate
	 * @param jt	JarTheme to use
	 */
	/*OK*/public EditorGUI(JarLevel jl, JarTheme jt) {
		jarLevelUsed = jl;
        jarThemeUsed = jt;
        spritesComp = jt.getSpritesCompatibility();
        intersComp  = jt.getInteractionsCompatibility();
        intersTips  = jt.getInteractionTips();
        backImages  = new SpriteSheet( jt.getSprites64(), Tile.SIZE).getSprites();
        frontImages = new SpriteSheet( jt.getInteractions(), Tile.SIZE).getSprites();
        
        initComponents();
        initComponentsConfig();
        initStructure();
        
        initMatrix();
        initSprites();
        initInteractions();
    }
	//endregion
	
	
    //region GUI Initialisation 
	/**
	 * Initializes the GUI's components.
	 */
    /*OK*/private void initComponents(){
    	try {
    		windsPolice24 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,24F);
		} catch (FontFormatException | IOException e) {
			windsPolice24 = new Font ("Serif", Font.BOLD, 24);
		}
    	
    	initHeaderComponents();
    	initMatrixComponents();
    	initLegendComponents();        
    }
    /**
     * Initializes the pseudo-menu's components.
     */
    /*OK*/private void initHeaderComponents(){
        header = new JPanel();
        btnSave = new JButton();
        sep1 = new JSeparator();
        labels = new JPanel();
        lblLevel = new JLabel();
        lblTheme = new JLabel();
        lblTimeMax = new JLabel();
        fields = new JPanel();
        txtLevel = new JTextField();
        txtTheme = new JTextField();
        txtTimeMax = new JTextField();
        description = new JPanel();
        lblDescription = new JLabel();
        areaDescription = new JTextArea();
        sep2 = new JSeparator();
        btnBack = new JButton();
    }
    /**
     * Initializes the matrix's components.
     */
    /*OK*/private void initMatrixComponents(){
    	gridMatrix = new JPanel();
        scrollMatrix = new JScrollPane();
    }
    /**
     * Initializes the legend's components.
     */
    /*OK*/private void initLegendComponents(){
    	legend = new JPanel();
        current = new JPanel();
        lblCurrent = new JLabel();
        tileCurrent = Tile.createEmptyCurrent();
        tabPane = new JTabbedPane();
        gridSprites = new JPanel();
        scrollSprites = new JScrollPane();
        gridInteractions = new JPanel();
        scrollInteractions = new JScrollPane();
        btnEmpty = new JButton();
    }
    
    
    /**
     * Configures the GUI's components.
     */
    /*OK*/private void initComponentsConfig(){
    	initHeaderConfig();
    	initMatrixConfig();
        initLegendConfig();
    }
    /**
     * Configures the pseudo-menu's components.
     */
    /*OK*/private void initHeaderConfig(){
    	btnSave.setText("Save");
    	btnSave.setCursor(CURSOR_HAND);
    	btnSave.setFont(windsPolice24);
        btnSave.addActionListener(new SaveListener(this));

        sep1.setOrientation(SwingConstants.VERTICAL);

        lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLevel.setText("Level name :");

        lblTheme.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTheme.setText("Theme used :");

        lblTimeMax.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTimeMax.setText("Time max :");
        
        txtLevel.setEditable(false);
        txtLevel.setFocusable(false);
        txtLevel.setHorizontalAlignment(JTextField.CENTER);
        txtLevel.setText(jarLevelUsed.getLevel().getName());

        txtTheme.setEditable(false);
        txtTheme.setFocusable(false);
        txtTheme.setHorizontalAlignment(JTextField.CENTER);
        txtTheme.setText(jarThemeUsed.getName());
        
        txtTimeMax.setToolTipText("minimum allowed :  "+ MINIMUM_TIME +"seconds\nmaximum allowed : 999 seconds");
        txtTimeMax.setCursor(CURSOR_HAND);
        txtTimeMax.setHorizontalAlignment(JTextField.CENTER);
        TimeMaxListener tml = new TimeMaxListener();
        txtTimeMax.addKeyListener(tml);
        txtTimeMax.addFocusListener(tml);
        int time = jarLevelUsed.getLevel().getTimeMax();
    	txtTimeMax.setText(time == 0 ? PROMPT_TIMEMAX : String.valueOf(time));
    	txtTimeMax.setForeground(time != 0 ? Color.BLACK : Color.GRAY);
        
        lblDescription.setText("Level description :");
        
        String desc = jarLevelUsed.getLevel().getDescription();
        areaDescription.setText(desc == null ? PROMPT_DESCRIPTION : desc);
        areaDescription.setToolTipText("maximum allowed : 255 characters");
        areaDescription.setForeground(Color.GRAY);
        areaDescription.setLineWrap(true);
        areaDescription.setWrapStyleWord(true);
        areaDescription.setCursor(CURSOR_HAND);
        DescriptionListener dl = new DescriptionListener();
        areaDescription.addKeyListener(dl);
        areaDescription.addFocusListener(dl);
        areaDescription.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        areaDescription.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        
        sep2.setOrientation(SwingConstants.VERTICAL);
        
        btnBack.setText("Back");
        btnBack.setCursor(CURSOR_HAND);
        btnBack.setFont(windsPolice24);
        btnBack.addActionListener(new BackListener());
    }
    /**
     * Configures the matrix's components.
     */
    /*OK*/private void initMatrixConfig(){
    	gridMatrix.setLayout(new GridLayout(NB_TILES_MATRIX, NB_TILES_MATRIX, MARGIN_TILES, MARGIN_TILES));
    	gridMatrix.setCursor(CURSOR_HAND);
    	scrollMatrix.setViewportView(gridMatrix);
    }
    /**
     * Configures the legend's components.
     */
    /*OK*/private void initLegendConfig(){
    	GridLayout layLegend = new GridLayout(0, NB_COLS_LEGEND, MARGIN_TILES, MARGIN_TILES);
    	
    	current.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
    	current.setOpaque(true);

        lblCurrent.setText("Current selected tile");
        lblCurrent.setAlignmentY(0.0F);

        tileCurrent.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        
        tabPane.setTabPlacement(JTabbedPane.BOTTOM);
        tabPane.setMaximumSize(new Dimension(216, 100));
        tabPane.setMinimumSize(new Dimension(216, 100));
        tabPane.setPreferredSize(new Dimension(216, 100));
        tabPane.setCursor(CURSOR_HAND);

        gridSprites.setLayout(layLegend);
        scrollSprites.setViewportView(gridSprites);
        tabPane.addTab("Sprites", scrollSprites);

        gridInteractions.setLayout(layLegend);
        scrollInteractions.setViewportView(gridInteractions);
        scrollInteractions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabPane.addTab("Interactions", scrollInteractions);

        btnEmpty.setText("Select empty tile");
        btnEmpty.setAlignmentY(0.0F);
        btnEmpty.setMaximumSize(new Dimension(111, 30));
        btnEmpty.setMinimumSize(new Dimension(111, 30));
        btnEmpty.setPreferredSize(new Dimension(111, 30));
        btnEmpty.setCursor(CURSOR_HAND);
        btnEmpty.addMouseListener(new EditorListener.EmptyListener());
    }
    
    
    /**
     * Builds the GUI's structure.
     */
    /*OK*/private void initStructure() {
    	GroupLayout grpLabels = new GroupLayout(labels);
        labels.setLayout(grpLabels);
        grpLabels.setHorizontalGroup(
            grpLabels.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblLevel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTheme, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(lblTimeMax, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        grpLabels.setVerticalGroup(
            grpLabels.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpLabels.createSequentialGroup()
                .addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTheme, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimeMax, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
        );
        
        GroupLayout grpFields = new GroupLayout(fields);
        fields.setLayout(grpFields);
        grpFields.setHorizontalGroup(
            grpFields.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(txtTheme)
            .addComponent(txtTimeMax, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
            .addComponent(txtLevel)
        );
        grpFields.setVerticalGroup(
            grpFields.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpFields.createSequentialGroup()
                .addComponent(txtLevel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTheme, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimeMax, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
        );
        
        GroupLayout grpDescription = new GroupLayout(description);
        description.setLayout(grpDescription);
        grpDescription.setHorizontalGroup(
            grpDescription.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpDescription.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpDescription.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(areaDescription)
                    .addGroup(grpDescription.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        grpDescription.setVerticalGroup(
            grpDescription.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpDescription.createSequentialGroup()
                .addComponent(lblDescription)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaDescription, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        
        GroupLayout grpHeader = new GroupLayout(header);
        header.setLayout(grpHeader);
        grpHeader.setHorizontalGroup(
            grpHeader.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpHeader.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fields, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        grpHeader.setVerticalGroup(
            grpHeader.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, grpHeader.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(grpHeader.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(sep1)
                    .addComponent(sep2)
                    .addComponent(description, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fields, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labels, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, grpHeader.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(grpHeader.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        
        GroupLayout grpCurrent = new GroupLayout(current);
        current.setLayout(grpCurrent);
        grpCurrent.setHorizontalGroup(
            grpCurrent.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, grpCurrent.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurrent)
                .addGap(18, 18, 18)
                .addComponent(tileCurrent, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        grpCurrent.setVerticalGroup(
            grpCurrent.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpCurrent.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpCurrent.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(tileCurrent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrent, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        GroupLayout grpLegend = new GroupLayout(legend);
        legend.setLayout(grpLegend);
        grpLegend.setHorizontalGroup(
            grpLegend.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpLegend.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(grpLegend.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEmpty, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(current, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
        );
        grpLegend.setVerticalGroup(
            grpLegend.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpLegend.createSequentialGroup()
                .addComponent(current, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        GroupLayout grpRoot = new GroupLayout(this);
        this.setLayout(grpRoot);
        grpRoot.setHorizontalGroup(
            grpRoot.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(grpRoot.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMatrix, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        grpRoot.setVerticalGroup(
            grpRoot.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(grpRoot.createSequentialGroup()
                .addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpRoot.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMatrix, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addComponent(legend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }
    //endregion
    
    
    //region Methods 
    /**
     * Initializes the matrix's tiles with their corresponding sprite
     * and interaction from the current JarLevel. 
     */
    /*OK*/private void initMatrix(){
    	Level lvl = jarLevelUsed.getLevel();
    	int[][] matrix = lvl.getMatrix();
    	int[][] inters = lvl.getInteractions();
    	
    	for(int i=0; i<NB_TILES_MATRIX; i++){
    		for(int j=0; j<NB_TILES_MATRIX; j++){
    			int position = i*NB_TILES_MATRIX + j;
    			int indexBack = matrix[i][j];
    			int indexFront = inters[i][j];
    			gridMatrix.add(indexBack == 0 && indexFront == 0 ?
					Tile.createEmptyMatrix(position) :
					Tile.createMatrix(backImages[indexBack],
						frontImages[indexFront], indexBack,
						indexFront, position));
    		}
    	}
    	
    	if(lvl.getStartPosition() != null && lvl.getEndPosition() != null){
    		Point start = lvl.getStartPosition(),
    			  end	= lvl.getEndPosition();
    		int startPosition = ((int) start.getX())*NB_TILES_MATRIX + ((int) start.getY()),
    			endPosition   = ((int) end.getX())  *NB_TILES_MATRIX + ((int) end.getY());
    		
    		departure = (Tile) gridMatrix.getComponent(startPosition);
    		arrival   = (Tile) gridMatrix.getComponent(endPosition);
    	}
    }
    /**
     * Initializes the legend's sprite tiles from the current JarTheme. 
     */
    /*OK*/private void initSprites(){
		for (int i=1; i<backImages.length; i++)
			gridSprites.add( Tile.createSprite(backImages[i],i) );
    }
    /**
     * Initializes the legend's interaction tiles from the current JarTheme. 
     */    
    /*OK*/private void initInteractions(){
    	for (int i=1; i<frontImages.length; i++)
			gridInteractions.add( Tile.createInteraction(frontImages[i], i, intersTips[i-1]) );
    }
    
    
    /**
     * Get an array of tiles which represents the neighbors of this tile in the matrix's list
     * where 0 is the top, 1 is the right, 2 is the bottom and 3 is the left.
     * @param position the tile's index in the matrix's list
     * @return Tile[4] 
     */
    /*OK*/static Tile[] getNeighbors(int position){
    	int nbTilesPerRow = NB_TILES_MATRIX,
    		nbMaxTiles = NB_TILES_MATRIX*NB_TILES_MATRIX;
    	
    	Tile[] ts = new Tile[4];	// top, right, bottom, left
    	int[] index = new int[]{ position - nbTilesPerRow, position + 1,
    							 position + nbTilesPerRow, position - 1 };
    	boolean[] test = new boolean[]{ index[0] < 0, index[1] % nbTilesPerRow == 0, index[2] >= nbMaxTiles,
    						index[3] % nbTilesPerRow == nbTilesPerRow-1 || index[3] % nbTilesPerRow == -1};
    	
    	for (int i=0; i<4 ;i++)
    		ts[i] = test[i] ? null : (Tile) gridMatrix.getComponent(index[i]);

    	return ts;
    }
    /**
     * Saves the current JarLevel. 
     */
    /*OK*/public JarLevel saveJarLevel(){
    	String timeMaxValue = txtTimeMax.getText();
    	String descriptionValue = areaDescription.getText();
    	
    	if(timeMaxValue.equals(PROMPT_TIMEMAX) || descriptionValue.equals(PROMPT_DESCRIPTION)){
    		JOptionPane.showMessageDialog(this, "Mandatory fields missing", "Warning", JOptionPane.WARNING_MESSAGE);
    		return null;
    	}
    	if(departure == null || arrival == null){
    		JOptionPane.showMessageDialog(this, "The departure and arrival\nof this level must be set.",
    				"Warning", JOptionPane.WARNING_MESSAGE);
    		return null;
    	}
    	
    	int timeMax = Integer.valueOf( timeMaxValue ).intValue();
    	Level lvl = jarLevelUsed.getLevel();
    	lvl.updateDate();
    	lvl.setDescription( descriptionValue );
    	lvl.setTimeMax( timeMax );
    	lvl.setStartPosition( getStartPosition() );
    	lvl.setEndPosition( getEndPosition() );
    	lvl.setMatrix( extractMatrix() );
    	lvl.setInteractions( extractInteractions() );

    	return jarLevelUsed.save() ? jarLevelUsed : null;
    }
    /**
     * Get the departure point of this level.
     * @return Point
     */
    /*OK*/private Point getStartPosition(){
    	Point p = null;
    	if(departure != null){
    		int x = departure.getPosition() % NB_TILES_MATRIX,
    			y = (int) (departure.getPosition() / NB_TILES_MATRIX);
    		p = new Point(x,y);
    	}
    	return p;
    }
    /**
     * Get the arrival point of this level.
     * @return Point
     */
    /*OK*/private Point getEndPosition(){
    	Point p = null;
    	if(arrival != null){
    		int x = arrival.getPosition() % NB_TILES_MATRIX,
    			y = (int) (arrival.getPosition() / NB_TILES_MATRIX);
    		p = new Point(x,y);
    	}
    	return p;
    }
    /**
     * Extracts the sprites' indexes of tiles positioned in matrix.
     * @return int[][]
     */
    /*OK*/private int[][] extractMatrix(){
    	Component[] components = gridMatrix.getComponents();
  	   
    	int[][] matrix = new int[NB_TILES_MATRIX][NB_TILES_MATRIX];
    	for(int i=0; i<NB_TILES_MATRIX; i++){
    		for(int j=0; j<NB_TILES_MATRIX; j++){
    			Tile tile = (Tile) components[ i*NB_TILES_MATRIX +j ];
    			matrix[i][j] = tile.getBackIndex();
    		}
    	}
    	return matrix;
    }
    /**
     * Extracts the interactions' indexes of tiles positioned in matrix.
     * @return int[][]
     */
    /*OK*/private int[][] extractInteractions(){
    	Component[] components = gridMatrix.getComponents();
   	   
    	int[][] matrix = new int[NB_TILES_MATRIX][NB_TILES_MATRIX];
    	for(int i=0; i<NB_TILES_MATRIX; i++){
    		for(int j=0; j<NB_TILES_MATRIX; j++){
    			Tile tile = (Tile) components[ i*NB_TILES_MATRIX +j ];
    			matrix[i][j] = tile.getFrontIndex();
    		}
    	}
    	return matrix;
    }
    //endregion

    
    //region Getters & Setters 
    /**
     * Get the current tile which represents the level's departure.
     * @return Tile
     */
    static Tile getDeparture(){
    	return departure;
    }
    /**
     * Get the current tile which represents the level's arrival.
     * @return Tile
     */
    static Tile getArrival(){
    	return arrival;
    }
    /**
     * Set the specified tile to become the current departure tile.
     * @param newDeparture
     * @return Tile
     */
    /*OK*/static void setDeparture(Tile newDeparture){
    	if(arrival != null && arrival.equals(newDeparture))
    		arrival = null;
    	
    	if(departure == null)
    		departure = newDeparture;
    	
    	else if( !departure.equals(newDeparture) ){
    		int position = departure.getPosition();
    		Tile exDep = (Tile) gridMatrix.getComponent(position);
    		exDep.removeInteraction();
    		departure = newDeparture;
    	}
    }
    /**
     * Set the specified tile to become the current arrival tile.
     * @param newArrival 
     * @return Tile
     */
    /*OK*/static void setArrival(Tile newArrival){
    	if(departure != null && departure.equals(newArrival))
    		departure = null;
    	
    	if(arrival == null)
    		arrival = newArrival;
    	
    	else if( !arrival.equals(newArrival) ){
    		int position = arrival.getPosition();
    		Tile exArr = (Tile) gridMatrix.getComponent(position);
    		exArr.removeInteraction();
    		arrival = newArrival;
    	}
    }
    //endregion

}
