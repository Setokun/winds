package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.BufferedImageLoader;
import display.Handler;
import display.Window;

public class Player extends GameObject{

	private float width = 64, height = 64;
	
	public static float gravity = 0.03f;

	private final float MAX_SPEED = 3;
	private final float MAX_SPEED_X = 4;
	private int timeElapsed = 0;
	
	static BufferedImage bubble;
	
	private Handler handler;
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		SpriteSheet spriteBubble = new SpriteSheet(loader.loadImage("/bulle2.png"), 64);
		bubble = spriteBubble.grabImage(0, 0);
	}
	
	public Player(float x, float y, Handler handler,ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public void unsetGravity(){
		gravity = 0;
	}
	
	public void resetGravity(){
		gravity = 0.03f;
	}
	
	@Override
	public void tick(ArrayList<GameObject> objects) {
		x += velX; 
		y += velY + .005f;
		timeElapsed++;
		
		if(falling && !isActing()){
			velY += gravity;
			
			
		}
		
		if(velY > MAX_SPEED)
			velY = MAX_SPEED;
		
		if(velX > MAX_SPEED_X)
			velX = MAX_SPEED_X;
		
		collision(objects);
	}
	
	private void collision(ArrayList<GameObject> object){
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject tempObject = handler.objects.get(i);
			
			
			if(Math.abs(tempObject.getY() - this.getY()) > 512 && Math.abs(tempObject.getY() - this.getY()) > 256) i+=4;
			if(Math.abs(tempObject.getX() - this.getX()) > 256) continue;
			if(Math.abs(tempObject.getY() - this.getY()) > 256)	continue;
			
			falling = true;
			
			if(tempObject.getId() == ObjectId.Player)
				continue;
			
			for (int j = 0; j < tempObject.getBounds().size(); j++) {
				if(tempObject.getBounds().get(j).getId() == ObjectId.Block){
					
					//////////////////////// TOP \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
						velY = -(this.getVelY()/4);
					}
					
					//////////////////////// BOTTOM \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).getBounds().y - 64;
						
						if(Math.abs(this.getVelY()) < 0.4f){
							velY = 0;
							unsetGravity();
						}
						else{
							velY = -(this.getVelY()/1.5f);
						}
						
						if(Math.abs(this.getVelX()) < 0.3f){
							velX = 0;
						}
						else{
						velX = this.getVelX()/1.2f;
						}
						
						falling = false;
					}
					else{
						falling = true;
						if(timeElapsed % 60 == 0){
							resetGravity();
						}
					}
					
					//////////////////////// RIGHT \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
						x = tempObject.getBounds().get(j).x - 64;
						velX = -(this.getVelX()/2);
					}
					
					//////////////////////// LEFT \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
						x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).width;
						velX = -(this.getVelX()/2);
					}
				}
				
				else if(tempObject.getBounds().get(j).getId() == ObjectId.DangerousBlock){
					
					// TOP
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
						velY = -(this.getVelY()*1.5f);
					}					
					// BOTTOM
					if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y - 64;
						velY = -(this.getVelY()*1.5f);
					}					
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
						x = tempObject.getBounds().get(j).x - 64;
						velX = -(this.getVelX()*1.5f);
					}					
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
						x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).getBounds().width;
						velX = -(this.getVelX()*1.5f);
					}
				}
			}
		}
		
		// 4 instructions to prevent the bubble from going outside the playground
		if(this.getX() <= 0){
			this.setX(this.getX() + 2);
			velX = -(this.getVelX()/2);
		}
		if(this.getY() <= 0){
			this.setY(this.getY() + 2);
			velY = -(this.getVelY()/2);
		}
		
		if(this.getX() + 72 >= 60*128){
			this.setX(this.getX() - 2);
			velX = -(this.getVelX()/2);
		}
		if(this.getY() + 92 >= 60*128){
			this.setY(this.getY() - 2);
			velY = -(this.getVelY()/2);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawImage(bubble, (int)x, (int)y, null);
		
		if(Window.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.red);
			g2d.draw(getBoundsBottom());
			g2d.draw(getBoundsRight());
			g2d.draw(getBoundsLeft());
			g2d.draw(getBoundsTop());
		}
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x+8), (int) ((int)y+height/2), (int)width-16, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+8), (int)y, (int)width-16, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-8), (int)y+5, (int)8, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)8, (int)height-10);
	}


	@Override
	public ArrayList<CollisionBox> getBounds() {
		return null;
	}
	

}
