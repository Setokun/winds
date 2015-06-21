package leveleditor;

import java.awt.AlphaComposite;
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
	public  static final int SIZE=64;
	private static final int MATRIX=0, LEGEND=1;
	private static final long serialVersionUID = -6388677825551701561L;
	private static final Dimension DIM = new Dimension(SIZE,SIZE);
	private static Tile emptyMatrix, emptyLegend;	
	
	private String tips;
	private int type, position, backIndex, frontIndex;
	private Image backImage, frontImage;
	private ImageIcon mixed;
	
	static {
		ClassLoader loader = Tile.class.getClassLoader();
		ImageIcon empty = new ImageIcon( loader.getResource("leveleditor/empty_64.png") );
		emptyMatrix = new Tile(MATRIX, empty.getImage(), null, 0, -1, -1, null);
		emptyLegend = new Tile(LEGEND, empty.getImage(), null, 0, -1, -1, null);
	}
	
	//region Constructors - OK 
	/*OK*/static Tile createEmptyLegend(){
		return emptyLegend.clone();
	}
	/*OK*/static Tile createEmptyMatrix(){
		return emptyMatrix.clone();
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
		return new Tile(LEGEND, backImage, null, backIndex, -1, -1, null);
	}
	/*OK*/static Tile createInteraction(Image frontImage, int frontIndex, String tips){
		return new Tile(LEGEND, null, frontImage, -1, frontIndex, -1, tips);
	}		
	//endregion
	
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
	private ImageIcon mixImages(){
		BufferedImage mix = createTransparentImage();
		Graphics g = mix.getGraphics();
		
		if(backImage != null)   g.drawImage(backImage, 0, 0, SIZE, SIZE, this);
		if(frontImage != null)  g.drawImage(frontImage, 0, 0, SIZE, SIZE, this);
		
		g.dispose();
		return new ImageIcon(mix);
	}
	/*OK*/private static BufferedImage createTransparentImage(){
		BufferedImage bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, SIZE, SIZE);
		g2d.dispose();
		return bi;
	}
	
	//region Methods 
	public Tile clone(){
		try {
			Tile c = (Tile) super.clone();
			c.tips = this.tips;
			c.type = this.type;
			c.position = this.position;
			c.backIndex = this.backIndex;
			c.frontIndex = this.frontIndex;
			c.backImage = this.backImage;
			c.frontImage = this.frontImage;
			c.mixImages();
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString(){
		return "Tile : {tips: "+ tips
					+", type: "+ (type == 1 ? "legend" : "matrix")
					+", position: "+ position
					+", backIndex: "+ backIndex
					+", frontIndex: "+ frontIndex
					+", backImage: "+ (backImage == null ? "null" : backImage)
					+", frontImage: " + (frontImage == null ? "null" : frontImage)
					+", mixed: "+ mixed.toString()
					+", parent: " + super.toString() +"}";
	}
	public void updateFrom(Tile source){
		backIndex = source.backIndex;
		frontIndex = source.frontIndex;
		
		//region Update restrictions 
		if(source.backIndex == 0){
			frontImage = null;
			frontIndex = -1;
		}
		if(this.type == LEGEND){
			backImage  = source.backImage;
			frontImage = source.frontImage;
		}
		if(this.type == MATRIX){
			if(source.backImage  != null)  backImage  = source.backImage;
			if(source.frontImage != null)  frontImage = source.frontImage;
		}
		//endregion
		
		mixed = mixImages();
		setIcon(mixed);
	}
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(mixed != null){
        	g.drawImage(mixed.getImage(), 0, 0, getWidth(), getHeight(), this);
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
