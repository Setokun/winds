package hunting;

import java.awt.Point;
import java.awt.Rectangle;


public class Level {
	// basé sur l'algorithme du gif sur le drop
	private int[][] matrix;
	private Rectangle[] obstacles;
	
	public Level(){
		matrix = new int[8][14];
		
		obstacles = new Rectangle[]{
			new Rectangle(3,1,1,5),
			new Rectangle(6,5,3,1),
			new Rectangle(8,5,1,3),
			new Rectangle(10,0,1,3),
			new Rectangle(10,2,2,1)
		};
	}
	public int[][] getMatrix(){
		return matrix;
	}
	public Rectangle[] getObstacles(){
		return obstacles;
	}
}