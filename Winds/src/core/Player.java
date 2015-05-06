package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import display.Animation;
import display.Game;
import display.Handler;

public class Player extends GameObject{

	private float width = 64, height = 64;
	
	public static float gravity = 0.03f;
	
	public void resetGravity(){
		gravity = 0;
	}
	
	
	private final float MAX_SPEED = 4;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk;
	
	public Player(float x, float y, Handler handler,ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		playerWalk = new Animation(4, tex.bulle[0]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX; 
		y += velY + .005f;
		
		if(falling && !isActing()){
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		collision(object);
		
		playerWalk.runAnimation();
	}
	
	private void collision(LinkedList<GameObject> object){
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject tempObject = handler.objects.get(i);
			
			if(tempObject.getId() == ObjectId.Block){
				
				// TOP
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
					velY = -(this.getVelY()/4);
				}
				
				// BOTTOM
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.getId() == ObjectId.Block){
					y = tempObject.getY()- height;
					if(Math.abs(this.getVelY()) < 0.4f){
						velY = 0;
						Player.gravity = 0;
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
					//System.out.println("touche un bloc normal");
				}
				else
					falling = true;

				// RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds())){	
					x = tempObject.getX() - width;
					velX = -(this.getVelX()/2);
				}
				
				// LEFT
				if(getBoundsLeft().intersects(tempObject.getBounds())){ 	
					x = tempObject.getX() + 32;
					velX = -(this.getVelX()/2);
				}
				
			}
			else if(tempObject.getId() == ObjectId.InoffensiveBlock){
				
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
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		if(velX != 0)
			playerWalk.drawAnimation(g, (int)x, (int)y);
		else
			g.drawImage(tex.bulle[0], (int)x, (int)y, null);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		/*Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());*/
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+height/2), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	

}
