package core;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class GameObject {
	
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected final float MAX_VEL_X = 2, MIN_VEL_X = -MAX_VEL_X;
	protected final float MAX_VEL_Y = 5, MIN_VEL_Y = -MAX_VEL_Y;
	public boolean falling = true;
	protected boolean acting = false;
	public boolean facingRight = true, facingDown = true;
	

	public GameObject(float x, float y, ObjectId id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/**
	 * determines what to do on each frame
	 * @param object ArrayList of GameObject
	 */
	public abstract void tick(ArrayList<GameObject> object);
	/**
	 * determines what to display on each frame
	 * @param g Canvas' Graphics
	 */
	public abstract void render(Graphics g);
	/**
	 * returns an ArrayList of CollisionBoxes of the GameObject
	 * @return ArrayList of CollisionBox
	 */
	public abstract ArrayList<CollisionBox> getBounds();
	
	/**
	 * returns a float of the x position of the GameObject
	 * @return float
	 */
	public float getX(){
		return x;
	}
	/**
	 * returns a float of the y position of the GameObject
	 * @return float
	 */
	public float getY(){
		return y;
	}
	/**
	 * set the x position of the GameObject
	 * @param x float
	 */
	public void setX(float x){
		this.x = x;
	}
	/**
	 * set the y position of the GameObject
	 * @param y float
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * returns a float of the x velocity of the GameObject
	 * @return float
	 */
	public float getVelX(){
		return velX;
	}
	/**
	 * returns a float of the y velocity of the GameObject
	 * @return float
	 */
	public float getVelY(){
		return velY;
	}
	/**
	 * sets the x velocity
	 * @param velX the x velocity
	 */
	public void setVelX(float velX){
		this.velX += velX;
	}
	/**
	 * sets the y velocity
	 * @param velY the y velocity
	 */
	public void setVelY(float velY){
		if(Math.abs(this.velY) < MAX_VEL_Y)
			this.velY += velY;
		else
			this.velY = (this.isFacingDown())? MIN_VEL_Y : MAX_VEL_Y;
	}
	/**
	 * returns if the GameObject must be affected by the gravity or not
	 * @return boolean
	 */
	public boolean isFalling() {
		return falling;
	}
	/**
	 * sets if the GameObject must be affected by the gravity or not
	 * @param falling boolean
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	/**
	 * returns if the GameObject is actually acting or not
	 * @return boolean
	 */
	public boolean isActing() {
		return acting;
	}
	/**
	 * sets if the GameObject is actually acting or not
	 * @param acting boolean
	 */
	public void setActing(boolean acting) {
		this.acting = acting;
	}
	
	/**
	 * returns the ID of the object determined in the constructor
	 * @return ObjectId enumeration
	 */
	public ObjectId getId(){
		return id;
	}
	
	/**
	 * returns if the GameObject is actually facing right or not
	 * @return boolean
	 */
	public boolean isFacingRight() {
		return facingRight;
	}
	/**
	 * sets if the GameObject is actually facing right or not
	 * @param facingRight boolean
	 */
	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
	/**
	 * returns if the GameObject is actually facing down or not
	 * @return boolean
	 */
	public boolean isFacingDown() {
		return facingDown;
	}
	/**
	 * sets if the GameObject is actually facing down or not
	 * @param facingDown boolean
	 */
	public void setFacingDown(boolean facingDown) {
		this.facingDown = facingDown;
	}
	
}
