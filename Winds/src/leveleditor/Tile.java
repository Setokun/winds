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
		ImageIcon empty = new ImageIcon( loader.getResource("leveleditor/empty_64.png") );
		emptyMatrix  = new Tile(MATRIX,  empty.getImage(), createTransparentImage(), DEFAULT, DEFAULT, DEFAULT, null);
		emptyCurrent = new Tile(CURRENT, empty.getImage(), createTransparentImage(), DEFAULT, DEFAULT, DEFAULT, null);
	}
	
	//region Constructors - OK 
	/*OK*/static Tile createEmptyCurrent(){
		return emptyCurrent.clone();
	}
	/*OK*/static Tile createEmptyMatrix(int position){
		Tile t = emptyMatrix.clone();
		t.position = position;
		return t;
	}
	/*OK*/static Tile createMatrix(Image backImage, Image frontImage, int backIndex, int frontIndex, int position){
		return new Tile(MATRIX, backImage, frontImage, backIndex, frontIndex, position, null);
	}
	/*OK*/static Tile createSprite(Image backImage, int backIndex){
		return new Tile(LEGEND, backImage, null, backIndex, DEFAULT, DEFAULT, null);
	}
	/*OK*/static Tile createInteraction(Image frontImage, int frontIndex, String tips){
		return new Tile(LEGEND, null, frontImage, DEFAULT, frontIndex, DEFAULT, tips);
	}		
	
	/*OK*/private Tile(int type, Image backImage, Image frontImage, int backIndex, int frontIndex, int position, String tips){
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
	/*OK*/public boolean equals(Object obj){
		if( !(obj instanceof Tile) )  return false;
		
		Tile t = (Tile) obj;
		return tips == t.tips && type == t.type && position == t.position
			&& backIndex == t.backIndex && frontIndex == t.frontIndex;
	}
	/*OK*/public Tile clone(){
		try {
			return (Tile) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*OK*/public String toString(){
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
	/*OK*/public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(mixed != null)
        	g.drawImage(mixed.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
	
	/*OK*/public boolean isDeparture(){
		return frontIndex == 1;
	}
	/*OK*/public boolean isArrival(){
		return frontIndex == 2;
	}
	/*OK*/public boolean isEmptyInteraction(){
		return frontIndex == DEFAULT;
	}
	/*OK*/public void removeInteraction(){
		frontIndex = emptyCurrent.frontIndex;
		frontImage = emptyCurrent.frontImage;
		if(backIndex == DEFAULT){
			backIndex  = emptyCurrent.backIndex;
			backImage  = emptyCurrent.backImage;
		}
		
		mixed = this.mixImages();
		setIcon(mixed);
	}
	/*OK*/public void updateFrom(Tile source){
		if(this.type == CURRENT && source.type == CURRENT)	updateFromEmpty();
		if(this.type == CURRENT && source.type == LEGEND)	updateCurrentFromLegend(source);
		if(this.type == MATRIX  && source.type == CURRENT)	updateMatrixFromCurrent(source);
		
		mixed = this.mixImages();
		setIcon(mixed);
	}
	//endregion
	
	//region Private methods 
	/*OK*/private ImageIcon mixImages(){
		BufferedImage mix = createBlankImage();
		Graphics g = mix.getGraphics();

		if(backImage != null)   g.drawImage(backImage, 0, 0, SIZE, SIZE, this);
		if(frontImage != null)  g.drawImage(frontImage, 0, 0, SIZE, SIZE, this);
		
		g.dispose();
		return new ImageIcon(mix);
	}
	/*OK*/private BufferedImage createBlankImage(){
		BufferedImage bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setColor(new Color(238, 238, 238));
		g.fillRect(0, 0, SIZE, SIZE);
		g.dispose();
		return bi;
	}
	/*OK*/private static BufferedImage createTransparentImage(){
		BufferedImage bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, SIZE, SIZE);
		g2d.dispose();
		return bi;
	}
	/*OK*/private void updateFromEmpty(){
		backIndex  = emptyCurrent.backIndex;
		backImage  = emptyCurrent.backImage;
		frontIndex = emptyCurrent.frontIndex;
		frontImage = emptyCurrent.frontImage;
	}
	/*OK*/private void updateCurrentFromLegend(Tile source){
		backIndex  = source.backIndex;
		backImage  = source.backImage;
		frontIndex = source.frontIndex;
		frontImage = source.frontImage;
	}
	/*OK*/private void updateMatrixFromCurrent(Tile source){
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
	public int getType(){
		return type;
	}
	public int getPosition(){
		return position;
	}
	public int getBackIndex(){
		return backIndex;
	}
	public int getFrontIndex(){
		return frontIndex;
	}
	public Image getBackImage() {
		return backImage;
	}
	public Image getFrontImage(){
		return frontImage;
	}
	//endregion

}
