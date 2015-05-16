package core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {

	private BufferedImage image;
	private int width, height, spriteSize;
	
	public SpriteSheet(BufferedImage image, int spriteSize){
		

		this.image = image;
		this.spriteSize = spriteSize;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	
	public BufferedImage[] getSprites(){
		ArrayList<Image> spritesList = new ArrayList<Image>();
		spritesList.add(null);
		
		for(int i=0; i<height; i+=spriteSize){
			for(int j=0; j<width; j+=spriteSize){
				spritesList.add( grabImage(j,i) );
			}
		}
		
		BufferedImage[] images = new BufferedImage[ spritesList.size() ];
		return spritesList.toArray(images);
	}
	/*a mettre en private a terme*/
	public Image grabImage(int col, int row){
		return image.getSubimage(col, row, spriteSize, spriteSize);
	}
	
}
