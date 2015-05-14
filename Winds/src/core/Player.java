package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import display.Animation;
import display.Game;
import display.Handler;
import display.Window;

public class Player extends GameObject{

	private float width = 64, height = 64;
	
	public static float gravity = 0.03f;

	private final float MAX_SPEED = 3;
	private final float MAX_SPEED_X = 4;
	private int timeElapsed = 0;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk;
	
	public Player(float x, float y, Handler handler,ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		playerWalk = new Animation(4, tex.bulle[0]);
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
		
		playerWalk.runAnimation();
	}
	
	private void collision(ArrayList<GameObject> object){
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject tempObject = handler.objects.get(i);
			
			
			if(Math.abs(tempObject.getY() - this.getY()) > 512 && Math.abs(tempObject.getY() - this.getY()) > 256)
				i+=4;
			
			
			if(Math.abs(tempObject.getX() - this.getX()) > 256)
				continue;
			
			if(Math.abs(tempObject.getY() - this.getY()) > 256)
				continue;
			
			falling = true;
			
			if(tempObject.getId() == ObjectId.Player)
				continue;
			
			for (int j = 0; j < tempObject.getBounds3().size(); j++) {
				if(tempObject.getBounds3().get(j).getId() == ObjectId.Block){
					
					// TOP
					if(getBoundsTop().intersects(tempObject.getBounds3().get(j).getBounds())){
						
						System.out.println("Contact avec un bloc standard");
						
						y = tempObject.getBounds3().get(j).y + tempObject.getBounds3().get(j).height;
						velY = -(this.getVelY()/4);
					}
					
					// BOTTOM
					if(getBounds().intersects(tempObject.getBounds3().get(j).getBounds())){
						
						System.out.println("Contact avec un bloc standard");
						
						y = tempObject.getBounds3().get(j).getBounds().y - 64;
						
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
					
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds3().get(j).getBounds())){	
						
						System.out.println("Contact avec un bloc standard");
						
						x = tempObject.getBounds3().get(j).x - 64;
						velX = -(this.getVelX()/2);
					}
					
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds3().get(j))){ 	
						
						System.out.println("Contact avec un bloc standard");
						
						x = tempObject.getBounds3().get(j).x + tempObject.getBounds3().get(j).width;
						velX = -(this.getVelX()/2);
					}
				}
				else if(tempObject.getBounds3().get(j).getId() == ObjectId.DangerousBlock){
					
					// TOP
					if(getBoundsTop().intersects(tempObject.getBounds3().get(j).getBounds())){
						
						System.out.println("Contact avec un bloc dangereux");
						
						y = tempObject.getBounds3().get(j).y + tempObject.getBounds3().get(j).height;
						velY = -(this.getVelY()/4);
					}
					
					// BOTTOM
					if(getBounds().intersects(tempObject.getBounds3().get(j).getBounds())){
						
						System.out.println("Contact avec un bloc dangereux");
						
						y = tempObject.getBounds3().get(j).y - 64;
						
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
					
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds3().get(j).getBounds())){	
						
						System.out.println("Contact avec un bloc dangereux");						
						
						x = tempObject.getBounds3().get(j).x - 64;
						velX = -(this.getVelX()/2);
					}
					
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds3().get(j))){ 	
						
						System.out.println("Contact avec un bloc dangereux");
						
						x = tempObject.getBounds3().get(j).x + tempObject.getBounds3().get(j).getBounds().width;
						velX = -(this.getVelX()/2);
					}
				}
			}
			
			/*if(tempObject.getId() == ObjectId.Block){
				
				for (int j = 0; j < tempObject.getBounds2().size(); j++) {
					// TOP
					if(getBoundsTop().intersects(tempObject.getBounds2().get(j))){
						y = tempObject.getBounds2().get(j).y + tempObject.getBounds2().get(j).height;
						velY = -(this.getVelY()/4);
					}
					
					// BOTTOM
					if(getBounds().intersects(tempObject.getBounds2().get(j)) && tempObject.getId() == ObjectId.Block){
						
						y = tempObject.getBounds2().get(j).y - 64;
						
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
					
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds2().get(j))){	
						x = tempObject.getBounds2().get(j).x - 64;
						velX = -(this.getVelX()/2);
					}
					
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds2().get(j))){ 	
						x = tempObject.getBounds2().get(j).x + tempObject.getBounds2().get(j).width;
						velX = -(this.getVelX()/2);
					}
				}
			}*/
		}
			/*else if(tempObject.getId() == ObjectId.InoffensiveBlock){
				
				// TOP
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
					velY = 1;
				}
				
				// BOTTOM
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY()- height ;
					if(Math.abs(this.getVelY()) < 1){
						velX = 0;
						Player.gravity = 0;
					}
					else
						velY = -(this.getVelY()/1.5f);
					velX = this.getVelX()/1.5f;
					falling = false;
					//System.out.println("touche un bloc inoffensif");
				}
				else
					falling = true;

				// RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds())){	
					x = tempObject.getX() - width;
					velX = -1;
				}
				
				// LEFT
				if(getBoundsLeft().intersects(tempObject.getBounds())){ 	
					x = tempObject.getX() + 32;
					velX = 1;
				}
			}
		}*/
		if(this.getX() <= 0){
			this.setX(this.getX() + 2);
			velX = -(this.getVelX()/2);
		}
		if(this.getY() <= 0){
			this.setY(this.getY() + 2);
			velY = -(this.getVelY()/2);
		}
		
		if(this.getX() + 64 >= 60*128){
			this.setX(this.getX() - 2);
			velX = -(this.getVelX()/2);
		}
		if(this.getY() + 64 >= 60*128){
			this.setY(this.getY() - 2);
			velY = -(this.getVelY()/2);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		if(velX != 0)
			playerWalk.drawAnimation(g, (int)x, (int)y);
		else
			g.drawImage(tex.bulle[0], (int)x, (int)y, null);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		if(Window.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.red);
			g2d.draw(getBounds());
			g2d.draw(getBoundsRight());
			g2d.draw(getBoundsLeft());
			g2d.draw(getBoundsTop());
		}
	}

	public Rectangle getBounds() {
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
	public ArrayList<Rectangle> getBounds2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CollisionBox> getBounds3() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
