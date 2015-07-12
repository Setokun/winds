package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int frames;
	
	private int index;
	private int count;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	/**
	 * constructor that determines speed of the animation and the sprites to be animated
	 * @param speed
	 * @param args
	 */
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i=0; i<args.length; i++){
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	/**
	 * make the index of the sprite to display to evolve
	 */
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}
	/**
	 * reach the next frame
	 */
	private void nextFrame() {
		for(int i=0; i<frames; i++){
			if(count == i)
				currentImg = images[i];
		}
		count++;
		
		if(count > frames){
			count = 0;
		}
	}
	/**
	 * draws the current sprite
	 * @param g Canvas' Graphics where to draw the current sprite
	 * @param x coordinate where to start drawing
	 * @param y coordinate where to start drawing
	 */
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(currentImg, x, y, null);
	}
	/**
	 * draws the current sprite with a scale
	 * @param g Canvas' Graphics where to draw the current sprite
	 * @param x coordinate where to start drawing
	 * @param y coordinate where to start drawing
	 * @param scaleX the x scale to affect
	 * @param scaleY the y scale to affect
	 */
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
	
}
