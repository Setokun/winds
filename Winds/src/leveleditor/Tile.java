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
	
	static final int SIZE_LEGEND = 64;
	static final int SIZE_MATRIX = 32;
	
	private static Tile emptyMatrix, emptyLegend;
	
	static {
		ClassLoader loader = Tile.class.getClassLoader();
		ImageIcon icon32 = new ImageIcon( loader.getResource("leveleditor/empty_32.png") );
		ImageIcon icon64 = new ImageIcon( loader.getResource("leveleditor/empty_64.png") );
		emptyMatrix = new Tile(SIZE_MATRIX, 0, icon32.getImage());
		emptyLegend = new Tile(SIZE_LEGEND, 0, icon64.getImage());
	}
	
	
	private int position, index;
	private ImageIcon icon;
	
	public Tile(int tileSize, int index, Image img){
		this(tileSize, -1, index, img);
	}
	public Tile(int tileSize, int position, int index, Image img){
		super();
		
		this.position = position;
		this.index = index;
		this.icon = img == null ? null : new ImageIcon(img);
		
		Dimension tileDim = new Dimension(tileSize, tileSize);
		setMinimumSize(tileDim);
		setPreferredSize(tileDim);
		setMaximumSize(tileDim);
		setIcon(icon);
		
		if(tileSize == SIZE_LEGEND){ this.addMouseListener(new TileLegendListener()); }
		if(tileSize == SIZE_MATRIX){ this.addMouseListener(new TileMatrixListener()); }
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
		return "Tile : {position: "+ position
					+", index: "+ index
					+", icon: "+ icon.toString()
					+", parent: " + super.toString() +"}";
	}
	
	public void updateFrom(int index, Image img){
		this.index = index;
		this.icon = new ImageIcon(img);
		setIcon(icon);
	}
	public void updateFrom(Tile source){
		updateFrom(source.index, source.icon.getImage());		
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(icon != null){
        	g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

	//region Getters
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
		return emptyLegend.clone();
	}
	//endregion
}
