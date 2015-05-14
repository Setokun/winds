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

	public ObjectId getId() {
		return id;
	}

	
	
}
