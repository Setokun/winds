package core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

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
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract ArrayList<Rectangle> getBounds2();
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		if(this.isFacingRight()/* && this.velX < MAX_VEL_X*/)
			this.velX += velX;
		if(!this.isFacingRight()/* && this.velX > MIN_VEL_X*/)
			this.velX += velX;
	}
	public void setVelY(float velY){
		if(this.isFacingDown())
			if(Math.abs(this.velY) < MAX_VEL_Y)
				this.velY += velY;
			else
				this.velY = MAX_VEL_Y;
		if(!this.isFacingDown())
			if(Math.abs(this.velY) < MAX_VEL_Y)
				this.velY += velY;
			else
				this.velY = MAX_VEL_Y;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isActing() {
		return acting;
	}
	public void setActing(boolean acting) {
		this.acting = acting;
	}
	
	public ObjectId getId(){
		return id;
	}
	
	public boolean isFacingRight() {
		return facingRight;
	}
	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
	public boolean isFacingDown() {
		return facingDown;
	}
	public void setFacingDown(boolean facingDown) {
		this.facingDown = facingDown;
	}
	
}
