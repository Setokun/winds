package hunting;

import java.awt.Point;

public class Hunt {
	private static final int max = 14, margin = 2;
	Theme thm;
	Level lvl;
	private Point origin, target;
	
	
	/*OK*/public Hunt(Theme thm, Level lvl){
		this.thm = thm;
		this.lvl = lvl;
	}
	
	/*OK*/public Point getNextMove(Point originPoint, Point targetPoint) {
		origin  = originPoint;
		target  = targetPoint;
		
		int distX = Math.abs(origin.x - target.x),
			distY = Math.abs(origin.y - target.y);
		boolean isInExplorationArea = distX - margin <= max && distY - margin <= max;
		System.out.println(distX +"  "+ distY +"  "+ isInExplorationArea);
		
		return isInExplorationArea ? new AlgorithmA().getNextPoint()
								   : getStandardNextPoint();
	}
	/*OK*/private Point getStandardNextPoint(){
		Point p = new Point();
		int x0 = origin.x, x1 = target.x,
			y0 = origin.y, y1 = target.y;
		
		if(Math.abs(x0 - x1) >= Math.abs(y0 - y1)){
			p.x = x0 + (x0 < x1 ? +1 : -1);
			p.y = y0;
		} else {
			p.x = x0;
			p.y = y0 + (y0 < y1 ? +1 : -1);
		}
		return p;
	}

	private class AlgorithmA {
		private int dimLvl;
		private Point current, zero;
		int[][] weight, estim;		// x, y
		boolean[][] closed;			// x, y
		Point[][] previous;			// x, y, [currentX,currentY]
		private final int INFINITE = (Integer.MAX_VALUE /2) -1;
		
		/*OK*/public Point getNextPoint(){System.out.println("algo A");
			current = origin;
			dimLvl = lvl.getMatrixDimension().width;
			getAreaSource();
			
			initItems();
			do {
				getMinWeightPoint();
				updateNeighboors();
				closed[current.x][current.y] = true;
			} while( !current.equals(target) );
			return nextPoint();
		}
		/*OK*/private void getAreaSource(){
			zero = new Point();
			zero.x = Math.min(origin.x, target.x) % 128;
			zero.y = Math.min(origin.y, target.y) % 128;
			
			int maxPosition = dimLvl -max -1;
			if(zero.x > maxPosition) zero.x = maxPosition;
			if(zero.y > maxPosition) zero.y = maxPosition;
		}
		
		/*OK*/private void initItems(){
			// weights 
			weight = new int[max][max];
			for (int y=0; y<max; y++)
				for (int x=0; x<max; x++)
					weight[x][y] = INFINITE;
			weight[origin.x % 128][origin.y % 128] = 0;
			
			// estimations
			estim = new int[max][max];
			for (int y=0; y<max; y++)
				for (int x=0; x<max; x++)
					estim[x][y] = Math.abs(target.x %128 - x) + Math.abs(target.y %128 - y);

			closed = new boolean[max][max];
			previous = new Point[max][max];
		}
		/*OK*/private void getMinWeightPoint(){
			int cost, minCost=INFINITE;
			for(int y=0; y<max; y++)
				for(int x=0; x<max; x++)
					if( !closed[x][y] ){
						int pond = thm.getTileWeights()[ lvl.getMatrix()[x][y] ];
						cost = weight[x][y] + estim[x][y] + pond;
						if(cost < minCost){
							current = new Point(x,y);
							minCost = cost;
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
			if(existsNeighboor(x,y) && !closed[x][y])
				if(w+1 < weight[x][y]){
					weight[x][y] = w+1;
					previous[x][y] = current;
				}
		}
		/*OK*/private boolean existsNeighboor(int x, int y){
			return 0 <= x && x < max
				&& 0 <= y && y < max;
		}
		/*OK*/private Point nextPoint(){
			Point next=null, prev = previous[target.x][target.y];
			while (!prev.equals(origin)){
				next = prev;
				// write here a syso of prev to trace the route
				System.out.println(prev);
				prev = previous[prev.x][prev.y];
			}
			return next;
		}
	}
	
}