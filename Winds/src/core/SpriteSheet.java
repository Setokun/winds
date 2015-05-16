package core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Spritesheet {

	private BufferedImage image;
	private int width, height, spriteSize;
	
	public Spritesheet(ImageIcon icon, int spriteSize){
		BufferedImage bi = new BufferedImage(
			    icon.getIconWidth(),
			    icon.getIconHeight(),
			    BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();

		this.image = bi;
		this.spriteSize = spriteSize;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	
	public Image[] getSprites(){
		ArrayList<Image> spritesList = new ArrayList<Image>();
		spritesList.add(null);
		
		for(int i=0; i<height; i+=spriteSize){
			for(int j=0; j<width; j+=spriteSize){
				spritesList.add( grabImage(j,i) );
			}
		}
		
		Image[] images = new Image[ spritesList.size() ];
		return spritesList.toArray(images);
	}
	/*a mettre en private a terme*/
	public Image grabImage(int col, int row){
		return image.getSubimage(col, row, spriteSize, spriteSize);
	}
	
}
