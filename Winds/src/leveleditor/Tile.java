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
	private int type, position, index;
	private Image backImage, frontImage;
	private ImageIcon mixed;
	
	static {
		ClassLoader loader = Tile.class.getClassLoader();
		ImageIcon empty = new ImageIcon( loader.getResource("leveleditor/empty_64.png") );
		emptyMatrix = new Tile(MATRIX, empty.getImage(), null, 0, -1, null);
		emptyLegend = new Tile(LEGEND, empty.getImage(), null, 0, -1, null);
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
	/*OK*/static Tile createMatrix(Image back, Image front, int index, int position){
		return new Tile(MATRIX, back, front, index, position, null);
	}
	/*OK*/static Tile createSprite(Image back, int index){
		return new Tile(LEGEND, back, null, index, -1, null);
	}
	/*OK*/static Tile createInteraction(Image front, int index, String tips){
		return new Tile(LEGEND, null, front, index, -1, null);
	}		
	//endregion
	
	private Tile(int type, Image back, Image front, int index, int position, String tips){
		super();
		
		this.tips = tips;
		this.type = type;
		this.position = position;
		this.index = index;
		this.backImage = back;
		this.frontImage = front;
		this.mixed = new ImageIcon( mixImages() );
		
		setMinimumSize(DIM);
		setPreferredSize(DIM);
		setMaximumSize(DIM);
		setIcon(mixed);
		setToolTipText(this.tips);
		
		if(type == LEGEND){ this.addMouseListener(new TileLegendListener()); }
		if(type == MATRIX){ this.addMouseListener(new TileMatrixListener()); }
	}
	private BufferedImage mixImages(){
		BufferedImage mix = createTransparentBackImage();
		Graphics g = mix.getGraphics();
		
		if(backImage != null)   g.drawImage(backImage, 0, 0, SIZE, SIZE, this);
		if(frontImage != null)  g.drawImage(frontImage, 0, 0, SIZE, SIZE, this);
		
		g.dispose();
		return mix;
	}
	private BufferedImage createTransparentBackImage(){
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
			c.backImage = this.backImage;
			c.index = this.index;
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString(){
		return "Tile : {type: "+ (type == 1 ? "legend" : "matrix")
					+", position: "+ position
					+", index: "+ index
					+", icon: "+ backImage.toString()
					+", parent: " + super.toString() +"}";
	}
	public void updateFrom(Tile source){
		this.index = source.index;
		if(source.backImage  != null)  this.backImage  = source.backImage;
		if(source.frontImage != null)  this.frontImage = source.frontImage;
		this.mixed = new ImageIcon( mixImages() );
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
	public int getIndex(){
		return index;
	}
	public ImageIcon getBackImage() {
		return new ImageIcon(backImage);
	}
	public ImageIcon getFrontImage(){
		return new ImageIcon(frontImage);
	}
	//endregion


}
