package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.BufferedImageLoader;
import audio.AudioPlayer;
import display.Game;
import display.Handler;
import display.Window;

public class Player extends GameObject{

	private float width = 64, height = 64;
	
	public static float gravity = 0.03f;

	private final float MAX_SPEED = 3;
	private final float MAX_SPEED_X = 4;
	private int timeElapsed = 0;
	private int life;
	
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
		this.life = 3;
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
			
			
			//if(Math.abs(tempObject.getY() - this.getY()) > 512 && Math.abs(tempObject.getY() - this.getY()) > 256) i+=4;
			if(Math.abs(tempObject.getX() - this.getX()) > 128) continue;
			if(Math.abs(tempObject.getY() - this.getY()) > 128)	continue;
			
			falling = true;
			
			if(tempObject.getId() == ObjectId.Player)
				continue;
			
			if(tempObject.getBounds() == null)
				continue;
			
			for (int j = 0; j < tempObject.getBounds().size(); j++) {
				if(tempObject.getBounds().get(j).getId() == ObjectId.Block){
					
					//////////////////////// TOP \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height -4;
						velY = -(this.getVelY()/4);
					}
					
					//////////////////////// BOTTOM \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).getBounds().y - 60;
						
						if(Math.abs(this.getVelY()) < 0.4f){ velY = 0; unsetGravity(); }
						else{ velY = -(this.getVelY()/1.5f); }
						
						if(Math.abs(this.getVelX()) < 0.3f){ velX = 0; }
						else{ velX = this.getVelX()/1.2f; }
						
						falling = false;
					}
					else{
						falling = true;
						if(timeElapsed % 60 == 0){ resetGravity(); }
					}
					
					//////////////////////// RIGHT \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
						x = tempObject.getBounds().get(j).x - 60;
						velX = -(this.getVelX()/2);
					}
					
					//////////////////////// LEFT \\\\\\\\\\\\\\\\\\\\\\\\
					if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
						x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).width -4;
						velX = -(this.getVelX()/2);
					}
				}
				
				else if(tempObject.getBounds().get(j).getId() == ObjectId.DangerousBlock 
						|| tempObject.getBounds().get(j).getId() == ObjectId.Enemy){
					
						// TOP
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
						velY = 1.5f;
						velX = this.getVelX()/4;
						if(!Game.isFinished()){
							this.life--;
							AudioPlayer.playSfx("splaf");
						}
					}
					// BOTTOM
					if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y - 64;
						velY = -2f;
						velX = this.getVelX()/4;
						if(!Game.isFinished()){
							this.life--;
							AudioPlayer.playSfx("splaf");
						}
					}
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
						x = tempObject.getBounds().get(j).x - 64;
						velX = -1.5f;
						velY = this.getVelY()/4;
						if(!Game.isFinished()){
							this.life--;
							AudioPlayer.playSfx("splaf");
						}
					}
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
						x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).getBounds().width;
						velX = 1.5f;
						velY = this.getVelY()/4;
						if(!Game.isFinished()){
							this.life--;
							AudioPlayer.playSfx("splaf");
						}
					}
					
					
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Boss){
					// TOP
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
						velY = 1.5f;
						velX = this.getVelX()/4;
						this.life--;
						AudioPlayer.playSfx("splaf");
					}
					// BOTTOM
					if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
						y = tempObject.getBounds().get(j).y - 64;
						velY = -2f;
						velX = this.getVelX()/4;
						this.life--;
						AudioPlayer.playSfx("splaf");
					}
					// RIGHT
					if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
						x = tempObject.getBounds().get(j).x - 64;
						velX = -1.5f;
						velY = this.getVelY()/4;
						this.life--;
						AudioPlayer.playSfx("splaf");
					}
					// LEFT
					if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
						x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).getBounds().width;
						velX = 1.5f;
						velY = this.getVelY()/4;
						this.life--;
						AudioPlayer.playSfx("splaf");
					}
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Collectable){

					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds()) 
					|| getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())
					|| getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())
					|| getBoundsLeft().intersects(tempObject.getBounds().get(j))){
						CollectableId cid = ((Collectable) tempObject).getCollectableId();
						
						if(cid == CollectableId.coin){
							AudioPlayer.playSfx("piece");
							Game.score.setNbItems(Game.score.getNbItems()+1);
						}
						else if(cid == CollectableId.life){
							AudioPlayer.playSfx("1up");
							Game.score.setNbItems(Game.score.getNbItems()+10);
						    this.life++;
						    if(this.life>5)
						    	this.life = 5;
						}
						else if(cid == CollectableId.honey){
							AudioPlayer.playSfx("honey");
							Game.score.setNbItems(Game.score.getNbItems()+2);
						}
						handler.removeObject(tempObject);
					}
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Arrival){
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds()) 
					|| getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())
					|| getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())
					|| getBoundsLeft().intersects(tempObject.getBounds().get(j))){
						if(!Game.isFinished()){
							velX = velX / 10;
							Game.setFinished();
							Game.bgMusic.stop();
							Game.bgMusic = new AudioPlayer("resources/musics/victory.mp3", false);
							Game.bgMusic.play();
						}
						
					}
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Blower){
					if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds()) 
							|| getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())
							|| getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())
							|| getBoundsLeft().intersects(tempObject.getBounds().get(j))){
						
						if(((Blower)tempObject).getDirection() == Direction.down) Game.player.setVelY(Math.abs(Game.player.getVelY()) +0.2f);
						if(((Blower)tempObject).getDirection() == Direction.up) Game.player.setVelY(-Math.abs(Game.player.getVelY()) -0.2f);
						if(((Blower)tempObject).getDirection() == Direction.left) Game.player.setVelX(-Math.abs(Game.player.getVelX()) -0.2f);
						if(((Blower)tempObject).getDirection() == Direction.right) Game.player.setVelX(Math.abs(Game.player.getVelX()) +0.2f);
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
		return new Rectangle((int) ((int)x+12), (int) ((int)y+height/2), (int)width-24, (int)height/2 - 4);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+12), (int)y + 4, (int)width-24, (int)height/2 - 4);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-12), (int)y+8, (int)8, (int)height-16);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x +4, (int)y+8, (int)8, (int)height-16);
	}


	@Override
	public ArrayList<CollisionBox> getBounds() {
		return null;
	}

	public void setLife(int variation){
		this.life += variation;
	}
	public int getLife(){
		return this.life;
	}
	
	public String toString(){
		return "player : {"+(int)this.x+","+(int)this.y+"}";
	}
	
}
