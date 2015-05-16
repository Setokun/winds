package core;

import java.awt.image.BufferedImage;

import display.BufferedImageLoader;

public class Texture {

	SpriteSheet spriteBubble, brambles;
	
	private BufferedImage bulle_sheet = null;
	private BufferedImage brambles_sheet = null;
	
	public BufferedImage[] bubble = new BufferedImage[1];
	public BufferedImage[] lvl_brambles = new BufferedImage[21];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			bulle_sheet = loader.loadImage("/bulle2.png");
			brambles_sheet = loader.loadImage("/themes/brambles_21.png");
		}catch(Exception e){e.printStackTrace();}
		
		spriteBubble = new SpriteSheet(bulle_sheet);
		brambles = new SpriteSheet(brambles_sheet);
		
		getTextures();
		
	}

	private void getTextures() {
		
		bubble[0] = spriteBubble.grabImage(1, 1, 64, 64); // idle bubble

		for(int i=1; i<=3; i++){
			for(int j=1; j<=7; j++){
				int index = (i-1)*7 + j-1;
				lvl_brambles[index] = brambles.grabImage(j, i, 128, 128);
			}
		}
		
	}
	
	
}
