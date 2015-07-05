package hunting;

import java.awt.Dimension;


public class Level {
	private final Dimension matrixDim = new Dimension(60,60);
	private int[][] matrix;
	
	public Level(){
		matrix = new int[60][60];
		matrix[1][0] = 1;
		matrix[1][1] = 1;
		matrix[1][2] = 1;
		matrix[1][3] = 1;
		matrix[1][4] = 1;
		matrix[0][6] = 2;
		matrix[1][6] = 2;
		matrix[2][6] = 2;
		matrix[3][6] = 2;
		matrix[4][4] = 3;
		matrix[4][5] = 3;
		matrix[4][6] = 3;
	}
	public int[][] getMatrix(){
		return matrix;
	}
	public Dimension getMatrixDimension(){
		return matrixDim;
	}

}