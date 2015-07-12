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

	private final float MAX_SPEED = 4;
	private final float MAX_SPEED_X = 4;
	private float wind_force = 1.0f;
	private int timeElapsed = 0;
	private int life;
	private int invincibleTime;
	private int windTime = 0;
	
	static BufferedImage bubble;
	
	private Handler handler;
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		SpriteSheet spriteBubble = new SpriteSheet(loader.loadImage("/resources/bubble.png"), 64);
		bubble = spriteBubble.grabImage(0, 0);
	}
	
	public Player(float x, float y, Handler handler,ObjectId id) {
		super(x, y, id);
		this.invincibleTime = 0;
		this.handler = handler;
		this.life = 3;
	}
	
	/**
	 * set the gravity of the player to zero
	 */
	public void unsetGravity(){
		gravity = 0;
	}
	/**
	 * set the gravity of the player to its regular value
	 */
	public void resetGravity(){
		gravity = 0.03f;
	}
	
	/**
	 * determine how the player will act on each frame
	 * @param ArrayList of GameObject
	 */
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
		
		if(invincibleTime > 0){
			invincibleTime--;
		}
		
		collision(objects);
	}
	
	/**
	 * method to determine what to do according to the type of other game objects
	 * @param ArrayList of GameObject
	 */
	private void collision(ArrayList<GameObject> object){
		
		for(int i = 0; i<handler.objects.size(); i++){
			GameObject tempObject = handler.objects.get(i);
			
			if(Math.abs(tempObject.getX() - this.getX()) > 128) continue;
			if(Math.abs(tempObject.getY() - this.getY()) > 128)	continue;
			if(tempObject.getId() == ObjectId.Player) continue;
			if(tempObject.getBounds() == null) continue;
			
			falling = true;
			
			for (int j = 0; j < tempObject.getBounds().size(); j++) {
				if(tempObject.getBounds().get(j).getId() == ObjectId.Block){
					actionBlock(tempObject, j);
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.DangerousBlock){
					actionDangerousBlockOrEnemy(tempObject, j);
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Enemy){
					if(!isHighlander())
						actionDangerousBlockOrEnemy(tempObject, j);
					}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Boss){
					if(!isHighlander())
						actionBoss(tempObject, j);
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Collectable){
					actionCollectable(tempObject, j);
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Arrival){
					actionArrival(tempObject, j);
				}
				else if(tempObject.getBounds().get(j).getId() == ObjectId.Blower){
					actionBlower(tempObject, j);
				}
			}
		}
		
		preventFromExitPlayground();
	}
	
	/**
	 * provides the rendering of the player to Canvas' Graphics
	 * @param Graphics g
	 */
	public void render(Graphics g) {

		if(!(invincibleTime > 0 && invincibleTime % 10 >= 5))
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

	/**
	 * returns a rectangle representing the down side of the player
	 * @return Rectangle
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x+12), (int) ((int)y+height/2), (int)width-24, (int)height/2 - 4);
	}
	/**
	 * returns a rectangle representing the upper side of the player
	 * @return Rectangle
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+12), (int)y + 4, (int)width-24, (int)height/2 - 4);
	}
	/**
	 * returns a rectangle representing the right side of the player
	 * @return Rectangle
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-12), (int)y+8, (int)8, (int)height-16);
	}
	/**
	 * returns a rectangle representing the left side of the player
	 * @return Rectangle
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x +4, (int)y+8, (int)8, (int)height-16);
	}
	/**
	 * returns a list of the collision boxes concerning this GameObject
	 * @return ArrayList of collisionBox
	 */
	public ArrayList<CollisionBox> getBounds() {
		return null;
	}

	/**
	 * add or remove the specified amount of life
	 * @param variation the variation of life (+ or -)
	 */
	public void setLife(int variation){
		this.life += variation;
	}
	/**
	 * returns the actual player's amount-of-lives 
	 * @return int
	 */
	public int getLife(){
		return this.life;
	}
	
	/**
	 * gets the coordinates of the player as a string representation
	 * @return the coordinates of the player
	 */
	public String toString(){
		return "player : {"+(int)this.x+","+(int)this.y+"}";
	}
	
	/**
	 * do whatever is needed when the player gets hurt
	 */
	public void getHurt(){
		this.invincibleTime += 180;
		this.life--;
		AudioPlayer.playSfx("splaf");
	}
	
	/**
	 * tell if the player is temporarily invincible
	 * @return true or false
	 */
	public boolean isHighlander(){
		return invincibleTime > 0;
	}
	
	/**
	 * prevent the player from going outside the playground
	 */
	private void preventFromExitPlayground() {
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
	
	/**
	 * determine the actions to do when touching an arrival block
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionArrival(GameObject tempObject, int j) {
		if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds()) 
				|| getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())
				|| getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())
				|| getBoundsLeft().intersects(tempObject.getBounds().get(j))){
			if(!Game.isFinished()){
				velX = 0;
				Game.setFinished();
				Game.bgMusic.stop();
				Game.bgMusic = new AudioPlayer("/resources/musics/victory.mp3", false);
				Game.bgMusic.play();
			}
		}
	}
	/**
	 * determine the actions to do when touching a blower block
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionBlower(GameObject tempObject, int j) {
		if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds()) 
				|| getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())
				|| getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())
				|| getBoundsLeft().intersects(tempObject.getBounds().get(j))){
			
			if(windTime <= 0){
				if(((Blower)tempObject).getDirection() == Direction.down)
					Game.player.setVelY(Math.abs(Game.player.getVelY()) + wind_force);
				if(((Blower)tempObject).getDirection() == Direction.up) 
					Game.player.setVelY(-Math.abs(Game.player.getVelY()) - wind_force);
				if(((Blower)tempObject).getDirection() == Direction.left) 
					Game.player.setVelX(-Math.abs(Game.player.getVelX()) - wind_force);
				if(((Blower)tempObject).getDirection() == Direction.right) 
					Game.player.setVelX(Math.abs(Game.player.getVelX()) + wind_force);
				
				windTime = 30;
			}
			windTime--;
		}
		else
			windTime = 0;
	}
	/**
	 * determine the actions to do when touching a collectable
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionCollectable(GameObject tempObject, int j) {
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
					else if(cid == CollectableId.valuable){
						AudioPlayer.playSfx("honey");
						Game.score.setNbItems(Game.score.getNbItems()+5);
					}
					handler.removeObject(tempObject);
				}
	}
	/**
	 * determine the actions to do when touching a boss
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionBoss(GameObject tempObject, int j) {
		// TOP
		if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
			y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
			velY = 1.5f;
			velX = this.getVelX()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// BOTTOM
		if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
			y = tempObject.getBounds().get(j).y - 64;
			velY = -2f;
			velX = this.getVelX()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// RIGHT
		if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
			x = tempObject.getBounds().get(j).x - 64;
			velX = -1.5f;
			velY = this.getVelY()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// LEFT
		if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
			x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).getBounds().width;
			velX = 1.5f;
			velY = this.getVelY()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
	}
	/**
	 * determine the actions to do when touching a dangerous block or an enemy
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionDangerousBlockOrEnemy(GameObject tempObject, int j) {
		// TOP
		if(getBoundsTop().intersects(tempObject.getBounds().get(j).getBounds())){
			y = tempObject.getBounds().get(j).y + tempObject.getBounds().get(j).height;
			velY = 1.5f;
			velX = this.getVelX()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// BOTTOM
		if(getBoundsBottom().intersects(tempObject.getBounds().get(j).getBounds())){
			y = tempObject.getBounds().get(j).y - 64;
			velY = -2f;
			velX = this.getVelX()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// RIGHT
		if(getBoundsRight().intersects(tempObject.getBounds().get(j).getBounds())){	
			x = tempObject.getBounds().get(j).x - 64;
			velX = -1.5f;
			velY = this.getVelY()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
		// LEFT
		if(getBoundsLeft().intersects(tempObject.getBounds().get(j))){ 	
			x = tempObject.getBounds().get(j).x + tempObject.getBounds().get(j).getBounds().width;
			velX = 1.5f;
			velY = this.getVelY()/4;
			if(!Game.isFinished()){
				if(!this.isHighlander()) this.getHurt();
			}
		}
	}
	/**
	 * determine the actions to do when touching a wall
	 * @param tempObject the object the player has to deal with
	 * @param j the number of the collisionBox concerned
	 */
	private void actionBlock(GameObject tempObject, int j) {
		////////////////////////TOP \\\\\\\\\\\\\\\\\\\\\\\\
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
	
}
