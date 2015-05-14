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

	public ObjectId getId() {return id;}

	public double getX(){return this.x;}
	public double getY(){return this.y;}
	public double getWidth(){return this.width;}
	public double getHeight(){return this.height;}
	
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public void setWidth(int width){this.width = width;}
	public void setHeight(int height){this.height = height;}
	
}
