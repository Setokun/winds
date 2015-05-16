package core;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

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
		
		spriteBubble = new SpriteSheet(bulle_sheet, 64);
		brambles = new SpriteSheet(brambles_sheet, 128);
		
		getTextures();
		
	}

	private void getTextures() {
		
		bubble[0] = (BufferedImage) spriteBubble.grabImage(0, 0);

		for(int i=0; i<3; i++){
			for(int j=0; j<7; j++){
				int index = (i)*7 + j;
				lvl_brambles[index] = (BufferedImage) brambles.grabImage(j, i);
			}
		}
		
	}
	
	
}
