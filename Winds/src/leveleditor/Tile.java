package leveleditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import leveleditor.EditorListener.TileLegendListener;
import leveleditor.EditorListener.TileMatrixListener;


public class Tile extends JLabel implements Cloneable {
	private static final long serialVersionUID = 1L;
	
	static final int LEGEND=0, MATRIX=1;
	static final int SIZE = 64;
	static private final Dimension DIM = new Dimension(SIZE,SIZE);
	static private Tile emptyMatrix, emptyLegend;
	
	static {
		ClassLoader loader = Tile.class.getClassLoader();
		ImageIcon icon64 = new ImageIcon( loader.getResource("leveleditor/empty_64.png") );
		emptyMatrix = new Tile(MATRIX, 0, icon64.getImage());
		emptyLegend = new Tile(LEGEND, 0, icon64.getImage());
	}
	
	
	private int type, position, index;
	private ImageIcon icon;
	
	public Tile(int tileType, int index, Image img){
		this(tileType, -1, index, img);
	}
	public Tile(int tileType, int position, int index, Image img){
		super();
		
		this.type = tileType;
		this.position = position;
		this.index = index;
		this.icon = img == null ? null : new ImageIcon(img);
		
		setMinimumSize(DIM);
		setPreferredSize(DIM);
		setMaximumSize(DIM);
		setIcon(icon);
		
		if(tileType == LEGEND){ this.addMouseListener(new TileLegendListener()); }
		if(tileType == MATRIX){ this.addMouseListener(new TileMatrixListener()); }
	}
	
	public Tile clone(){
		try {
			Tile c = (Tile) super.clone();
			c.icon = this.icon;
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
					+", icon: "+ icon.toString()
					+", parent: " + super.toString() +"}";
	}
	
	public void updateFrom(Tile source){
		this.index = source.index;
		this.icon  = source.icon;
		setIcon(icon);
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(icon != null){
        	g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

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
	public ImageIcon getIcon() {
		return icon;
	}
	//endregion
	
	//region Empty tiles
	static Tile getEmptyMatrix(){
		Tile t = emptyMatrix.clone();
		t.position = -1;
		return t;
	}
	static Tile getEmptyMatrix(int position){
		Tile t = emptyMatrix.clone();
		t.position = position;
		return t;
	}
	static Tile getEmptyLegend(){
		Tile t = emptyLegend.clone();
		t.position = -1;
		return t;
	}
	static Tile getEmptyLegend(int position){
		Tile t = emptyLegend.clone();
		t.position = position;
		return t;
	}
	//endregion
}
