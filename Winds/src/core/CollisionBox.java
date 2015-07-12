package core;

import java.awt.Rectangle;

public class CollisionBox extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	private ObjectId id;
	
	public CollisionBox(int x, int y, int width, int height, ObjectId objectId){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = objectId;
	}
	
	/**
	 * returns the Object ID
	 * @return ObjectId
	 */
	public ObjectId getId() {return id;}

	/**
	 * returns the x position of this CollisionBox
	 * @return double
	 */
	public double getX(){return this.x;}
	/**
	 * returns the y position of this CollisionBox
	 * @return double
	 */
	public double getY(){return this.y;}
	/**
	 * returns the width of this CollisionBox
	 * @return double
	 */
	public double getWidth(){return this.width;}
	/**
	 * returns the height of this CollisionBox
	 * @return double
	 */
	public double getHeight(){return this.height;}
	
	/**
	 * sets the x position of this CollisionBox
	 * @param x int
	 */
	public void setX(int x){this.x = x;}
	/**
	 * sets the y position of this CollisionBox
	 * @param y int
	 */
	public void setY(int y){this.y = y;}
	/**
	 * sets the width of this CollisionBox
	 * @param width int
	 */
	public void setWidth(int width){this.width = width;}
	/**
	 * sets the height of this CollisionBox
	 * @param height int
	 */
	public void setHeight(int height){this.height = height;}
	
	/**
	 * returns the string representation of this CollisionBox
	 * @return String
	 */
	public String toString(){
		return super.toString() + " " + this.id; 
	}
	
	/**
	 * returns a Rectangle representing this CollisionBox
	 * @return Rectangle
	 */
	public Rectangle getBounds(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
}
