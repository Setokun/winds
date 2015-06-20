package hunting;

import java.awt.Dimension;


public class Level {
	private final Dimension matrixDim = new Dimension(60,60);
	private int[][] matrix, walls;
	
	public Level(){
		matrix = new int[60][60];
		walls = new int[0][0];	// x1, y1, x2, y2
	}
	public int[][] getMatrix(){
		return matrix;
	}
	public Dimension getMatrixDimension(){
		return matrixDim;
	}
	public int[][] getWalls(){
		return walls;
	}
}