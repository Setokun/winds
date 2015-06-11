package leveleditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import leveleditor.EditorListener.TileLegendListener;
import leveleditor.EditorListener.TileMatrixListener;


/*
image de fond redimensionnée aléatoirement

compatibilité de sprites
int[][][] -> index de la tile / index du coté (top, left,...) concerné / tableau des index des tiles compatibles 
*/


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
	
	
	private int index;
	private ImageIcon icon;
	
	public Tile(int tileSize, int index, Image img){
		super();
		
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
	
	public void updateFrom(Tile source){
		this.index = source.index;
		this.icon = source.icon;
		setIcon(source.icon);
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(icon != null){
        	g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

	public int getIndex(){
		return index;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	
	public String toString(){
		return "Tile : {index: "+ index +", icon: "+ icon.toString() +", parent: " + super.toString() +"}";
	}

	static Tile getEmptyMatrix(){
		return emptyMatrix.clone();
	}
	static Tile getEmptyLegend(){
		return emptyLegend.clone();
	}

}
