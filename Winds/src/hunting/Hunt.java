package hunting;

import java.awt.Point;

public class Hunt {
	Level lvl;
	Point origin, current, target;
	int[][] weight, estim;			// x, y
	boolean[][] closed;				// x, y
	Point[][] previous;				// x, y, [currentX,currentY]
	int counter=0, nbCols, nbRows;	// x, y
	final int INFINITE = (Integer.MAX_VALUE /2) -1;
	
	/*OK*/public Hunt(Level lvl, Point origin, Point target){
		this.lvl = lvl;
		this.origin  = origin;
		this.current = origin;
		this.target  = target;		
		
		initItems();
		do {
			getMinWeightPoint();
			updateNeighboors();
			closed[current.x][current.y] = true;
			counter++;
		} while( !current.equals(target) /*&& counter < (nbRows*nbCols)*/ );
	}
	/*OK*/private void initItems(){
		nbRows = (int) lvl.getMatrixDimension().getHeight();
		nbCols = (int) lvl.getMatrixDimension().getWidth();
		
		// weights 
		weight = new int[nbCols][nbRows];
		for (int y=0; y<nbRows; y++)
			for (int x=0; x<nbCols; x++)
				weight[x][y] = INFINITE;
		weight[origin.x][origin.y] = 0;
		
		// estimations
		estim = new int[nbCols][nbRows];
		for (int y=0; y<nbRows; y++)
			for (int x=0; x<nbCols; x++)
				estim[x][y] = Math.abs(target.x - x) + Math.abs(target.y - y);

		closed = new boolean[nbCols][nbRows];
		previous = new Point[nbCols][nbRows];
		
		int[][] walls = lvl.getWalls();
		for (int i=0; i< walls.length; i++) {
			int[] data = walls[i];
			int x1=data[0], y1=data[1];
			while (x1 <= data[2]){
				estim[x1][data[1]] = INFINITE;
				closed[x1++][data[1]] = true;
			}
			while (y1 <= data[3]){
				estim[data[0]][y1] = INFINITE;
				closed[data[0]][y1++] = true;
			}
		}
		
		//displayStep();
	}
	/*OK*/private void getMinWeightPoint(){
		int cost, minCost=INFINITE;
		for(int y=0; y<nbRows; y++){
			for(int x=0; x<nbCols; x++){
				if( !closed[x][y] ){
					cost = weight[x][y] + estim[x][y];
					if(cost < minCost){
						current = new Point(x,y);
						minCost = cost;
					}
				}
			}
		}
	}
	/*OK*/private void updateNeighboors(){
		int x=current.x, y=current.y, w=weight[x][y];
		
		updateNeighboor(x+1, y, w);
		updateNeighboor(x, y+1, w);
		updateNeighboor(x-1, y, w);
		updateNeighboor(x, y-1, w);
	}
	/*OK*/private void updateNeighboor(int x, int y, int w){
		if(existsNeighboor(x,y) && !closed[x][y]){
			if(w+1 < weight[x][y]){
				weight[x][y] = w+1;
				previous[x][y] = current;
			}
		}
	}
	/*OK*/private boolean existsNeighboor(int x, int y){
		return 0 <= x && x < nbCols
			&& 0 <= y && y < nbRows;
	}
	/*OK*/private Point nextPoint(){
		Point next=null, prev = previous[target.x][target.y];

		while (!prev.equals(origin)){
			next = prev;
			// write here a syso of prev to trace the route
			prev = previous[prev.x][prev.y];
		}
		return next;
	}
	/*OK*/public static Point getNextMove(Level lvl, Point origin, Point target) {
		return new Hunt(lvl, origin, target).nextPoint();
	}
	
}