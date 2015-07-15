package leveleditor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import leveleditor.EditorListener.TileLegendListener;
import leveleditor.EditorListener.TileMatrixListener;

/**
 * Class which is the graphic representation of JarLevel et JarTheme elements
 */
public class Tile extends JLabel implements Cloneable {
	public  static final int SIZE=64, DEFAULT=0;
	private static final int MATRIX=0, CURRENT=1, LEGEND=2;
	private static final long serialVersionUID = -6388677825551701561L;
	private static final Dimension DIM = new Dimension(SIZE,SIZE);
	private static Tile emptyMatrix, emptyCurrent;	
	
	private String tips;
	private int type, position, backIndex, frontIndex;
	private Image backImage, frontImage;
	private ImageIcon mixed;
	
	static {
		ClassLoader loader = Tile.class.getClassLoader();
		ImageIcon empty = new ImageIcon( loader.getResource("resources/empty_tile.png") );
		emptyMatrix  = new Tile(MATRIX,  empty.getImage(), createTransparentImage(), DEFAULT, DEFAULT, DEFAULT, null);
		emptyCurrent = new Tile(CURRENT, empty.getImage(), createTransparentImage(), DEFAULT, DEFAULT, DEFAULT, null);
	}
	
	//region Constructors 
	/**
	 * Create a tile with an empty background and no interaction for the current tile.
	 * @return Tile
	 */
	static Tile createEmptyCurrent(){
		return emptyCurrent.clone();
	}
	/**
	 * Create a matrix tile with an empty background, no interaction and the specified position.
	 * @param position index of the tile in the matrix
	 * @return Tile
	 */
	static Tile createEmptyMatrix(int position){
		Tile t = emptyMatrix.clone();
		t.position = position;
		return t;
	}
	/**
	 * Create a matrix tile with the specified attributes.
	 * @param backImage		Image of the sprite to display in the background of the tile
	 * @param frontImage	Image of the interaction to display in the foreground of the tile
	 * @param backIndex		Index of the sprite
	 * @param frontIndex	Index of the interaction
	 * @param position		Index of the tile in the matrix
	 * @return Tile
	 */
	static Tile createMatrix(Image backImage, Image frontImage, int backIndex, int frontIndex, int position){
		return new Tile(MATRIX, backImage, frontImage, backIndex, frontIndex, position, null);
	}
	/**
	 * Create a legend sprite tile with the specified attributes.
	 * @param backImage		Image of the sprite to display in the background of the tile
	 * @param backIndex		Index of the sprite
	 * @return Tile
	 */
	static Tile createSprite(Image backImage, int backIndex){
		return new Tile(LEGEND, backImage, null, backIndex, DEFAULT, DEFAULT, null);
	}
	/**
	 * Create a legend interaction tile with the specified attributes.
	 * @param frontImage	Image of the interaction to display in the foreground of the tile
	 * @param frontIndex	Index of the interaction
	 * @param tips			Tooltip text displayed when the mouse is over the tile
	 * @return Tile
	 */
	static Tile createInteraction(Image frontImage, int frontIndex, String tips){
		return new Tile(LEGEND, null, frontImage, DEFAULT, frontIndex, DEFAULT, tips);
	}		
	
	/**
	 * Create a tile with the specified attributes.
	 * @param type			Type of the tile to create
	 * @param backImage		Image of the sprite to display in the background of the tile
	 * @param frontImage	Image of the interaction to display in the foreground of the tile
	 * @param backIndex		Index of the sprite
	 * @param frontIndex	Index of the interaction
	 * @param position		Index of the tile in the matrix
	 * @param tips			Tooltip text displayed when the mouse is over the tile
	 * @return Tile
	 */
	private Tile(int type, Image backImage, Image frontImage, int backIndex, int frontIndex, int position, String tips){
		super();
		
		this.tips = tips;
		this.type = type;
		this.position = position;
		this.backIndex = backIndex;
		this.frontIndex = frontIndex;
		
		this.backImage = backImage;
		this.frontImage = frontImage;
		this.mixed = mixImages();
		
		setMinimumSize(DIM);
		setPreferredSize(DIM);
		setMaximumSize(DIM);
		setIcon(mixed);
		setToolTipText(this.tips);
		
		if(type == LEGEND){ this.addMouseListener(new TileLegendListener()); }
		if(type == MATRIX){ this.addMouseListener(new TileMatrixListener()); }
	}
	//endregion
	
	//region Public methods 
	/**
	 * Checks if the specified object is equals to this tile.
	 */	
	public boolean equals(Object obj){
		if( !(obj instanceof Tile) )  return false;
		
		Tile t = (Tile) obj;
		return tips == t.tips && type == t.type && position == t.position
			&& backIndex == t.backIndex && frontIndex == t.frontIndex;
	}
	/**
	 * Clones the tile.
	 * @return Tile
	 */
	public Tile clone(){
		try {  return (Tile) super.clone();
		} catch (CloneNotSupportedException e) {}
		return null;
	}
	/**
	 * Serializes this tile to a string.
	 * @return String
	 */
	public String toString(){
		return "Tile [tips: "+ tips
					+", type: "+ ((type == 0) ? "matrix" : (type == 1) ? "current" : "legend")
					+", position: "+ position
					+", backIndex: "+ backIndex
					+", frontIndex: "+ frontIndex
					+", backImage: "+ (backImage == null ? "null" : backImage)
					+", frontImage: " + (frontImage == null ? "null" : frontImage)
					+", mixed: "+ mixed.toString()
					+", parent: " + super.toString() +"]";
	}
	/**
	 * Paints the tile.
	 */
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(mixed != null)
        	g.drawImage(mixed.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
	
	/**
	 * Checks if the tile has a "departure" interaction.
	 * @return boolean
	 */
	public boolean isDeparture(){
		return frontIndex == 1;
	}
	/**
	 * Checks if the tile has a "arrival" interaction.
	 * @return boolean
	 */
	public boolean isArrival(){
		return frontIndex == 2;
	}
	/**
	 * Checks if the tile has no interaction.
	 * @return boolean
	 */
	public boolean isEmptyInteraction(){
		return frontIndex == DEFAULT;
	}
	/**
	 * Remove the interaction of the tile.
	 */
	public void removeInteraction(){
		frontIndex = emptyCurrent.frontIndex;
		frontImage = emptyCurrent.frontImage;
		if(backIndex == DEFAULT){
			backIndex  = emptyCurrent.backIndex;
			backImage  = emptyCurrent.backImage;
		}
		
		mixed = this.mixImages();
		setIcon(mixed);
	}
	/**
	 * Updates this tile with the attributes of the specified tile.
	 * @param source Tile where the attributes will be picked up
	 */
	public void updateFrom(Tile source){
		if(this.type == CURRENT && source.type == CURRENT)	updateFromEmpty();
		if(this.type == CURRENT && source.type == LEGEND)	updateCurrentFromLegend(source);
		if(this.type == MATRIX  && source.type == CURRENT)	updateMatrixFromCurrent(source);
		
		mixed = this.mixImages();
		setIcon(mixed);
	}
	//endregion
	
	//region Private methods 
	/**
	 * Get an image which represents the superposition of the interaction and sprite images.
	 * @return ImageIcon
	 */
	private ImageIcon mixImages(){
		BufferedImage mix = createBlankImage();
		Graphics g = mix.getGraphics();

		if(backImage != null)   g.drawImage(backImage, 0, 0, SIZE, SIZE, this);
		if(frontImage != null)  g.drawImage(frontImage, 0, 0, SIZE, SIZE, this);
		
		g.dispose();
		return new ImageIcon(mix);
	}
	/**
	 * Create a blank background image.
	 * Used for a tile which will contain only an interaction.
	 * @return BufferedImage
	 */
	private BufferedImage createBlankImage(){
		BufferedImage bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setColor(new Color(238, 238, 238));
		g.fillRect(0, 0, SIZE, SIZE);
		g.dispose();
		return bi;
	}
	/**
	 * Create a transparent foreground image.
	 * Used for a tile which will contain only a sprite.
	 * @return BufferedImage
	 */
	private static BufferedImage createTransparentImage(){
		BufferedImage bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, SIZE, SIZE);
		g2d.dispose();
		return bi;
	}
	/**
	 * Updates the tile with the attributes of a empty tile.
	 */
	private void updateFromEmpty(){
		backIndex  = emptyCurrent.backIndex;
		backImage  = emptyCurrent.backImage;
		frontIndex = emptyCurrent.frontIndex;
		frontImage = emptyCurrent.frontImage;
	}
	/**
	 * Updates the current tile in GUI with the attributes of the specified legend tile.
	 * @param source Tile where the attributes will be picked up
	 */
	private void updateCurrentFromLegend(Tile source){
		backIndex  = source.backIndex;
		backImage  = source.backImage;
		frontIndex = source.frontIndex;
		frontImage = source.frontImage;
	}
	/**
	 * Updates the matrix tile with the attributes of the current tile in GUI.
	 * @param source Tile where the attributes will be picked up
	 */
	private void updateMatrixFromCurrent(Tile source){
		if(source.backIndex == DEFAULT && source.frontIndex == DEFAULT){
			updateFromEmpty();
			return;
		}
		
		if(source.backIndex != DEFAULT){
			backIndex = source.backIndex;
			backImage = source.backImage;
		}
		if(source.frontIndex != DEFAULT) {
			frontIndex = source.frontIndex;
			frontImage = source.frontImage;
			if(this.backImage == emptyCurrent.backImage){
				backIndex = emptyCurrent.backIndex;
				backImage = createBlankImage();
			}
		}
	}
	//endregion
	
	//region Getters 
	/**
	 * Get the type of the tile.
	 * @return int
	 */
	public int getType(){
		return type;
	}
	/**
	 * Get the position of the tile in the matrix.
	 * @return int
	 */
	public int getPosition(){
		return position;
	}
	/**
	 * Get the sprite's index of the tile.
	 * @return int
	 */
	public int getBackIndex(){
		return backIndex;
	}
	/**
	 * Get the interaction's index of the tile.
	 * @return int
	 */
	public int getFrontIndex(){
		return frontIndex;
	}
	/**
	 * Get the sprite's image of the tile.
	 * @return int
	 */
	public Image getBackImage() {
		return backImage;
	}
	/**
	 * Get the interaction's image of the tile.
	 * @return int
	 */
	public Image getFrontImage(){
		return frontImage;
	}
	//endregion

}
