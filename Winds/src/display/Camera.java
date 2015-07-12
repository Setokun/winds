package display;

import core.GameObject;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}

	private int levelWidth = Handler.NB_SQUARES*Handler.DIM_SQUARE;
	private int levelHeight = Handler.NB_SQUARES*Handler.DIM_SQUARE;
	
	/**
	 * update the Camera position in function of the player position
	 * @param player
	 */
	public void tick(GameObject player){
		if(player.getX() >= Game.WIDTH/2 && player.getX() <= levelWidth - Game.WIDTH/2)
			x =(int) (-player.getX() + Game.WIDTH/2);
		if(player.getY() >= Game.HEIGHT/2 && player.getY() <= levelHeight - Game.HEIGHT/2)
			y =(int) (-player.getY() + Game.HEIGHT/2);
	}
	
	/**
	 * returns x position of the Camera
	 * @return float
	 */
	public float getX() { return x; }
	/**
	 * returns y position of the Camera
	 * @return float
	 */
	public float getY() { return y; }
	/**
	 * sets x position of the Camera
	 * @param x
	 */
	public void setX(float x) { this.x = x; }
	/**
	 * sets y position of the Camera
	 * @param y
	 */
	public void setY(float y) { this.y = y; }
	
}
