package core;

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
		ArrayList<BufferedImage> spritesList = new ArrayList<BufferedImage>();
	
		for(int i=0; i<height; i+=spriteSize){
			for(int j=0; j<width; j+=spriteSize){
				spritesList.add( grabImage(j,i) );
			}
		}
		
		BufferedImage[] images = new BufferedImage[ spritesList.size() ];
		return spritesList.toArray(images);
	}
	/*a mettre en private a terme*/
	public BufferedImage grabImage(int col, int row){
		return image.getSubimage(col, row, spriteSize, spriteSize);
	}
	
}
