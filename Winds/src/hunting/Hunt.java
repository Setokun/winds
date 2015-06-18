package hunting;

import java.awt.Point;
import java.util.Comparator;
import java.util.LinkedList;

public class Hunt {
	int[][] matrix;
	Point origin, target;
	AlgoList items, analyzed;
	
	public Hunt(int[][] matrix, Point origin, Point target){
		this.matrix = matrix;
		this.origin = origin;
		this.target = target;
		
		items = new AlgoList();
		analyzed = new AlgoList();
		initItemsList();
	}
	private void initItemsList(){
		int len = matrix.length;
		for (int x=0; x<len; x++) {
			for (int y= 0; y<len; y++) {
				AlgoItem item = new AlgoItem(new Point(x,y));
				item.estimation = (int) Math.abs(target.getX() - x)
							 	+ (int) Math.abs(target.getY() - y);
				item.cost = 1;
				items.add(item);
			}
		}
		
		AlgoItem originItem = items.get(origin);
		originItem.weight = 0;
		items.set(origin, originItem);
		items.sortByWeight();
	}
	public Point getNextMove(){
		AlgoItem current = null;
		while ( (current = items.pollFirst()) != null) {
			analyzed.add(current);
			updateNeighboors(current);
			if(current.getCoordo().equals(target)){ break; }
			items.sortByWeight();
		}
		
		AlgoList trace = new AlgoList();
		AlgoItem last = analyzed.peekLast();
		while(last != null){
			trace.add(last);
			last = last.getPrevious();
		}
		trace.pollLast();
		
		return trace.peekLast().getCoordo();
	}
	private void updateNeighboors(AlgoItem current){
		Point cTop		= new Point((int)current.getCoordo().getX(), (int)current.getCoordo().getY()-1 );
		Point cRight	= new Point((int)current.getCoordo().getX()+1, (int)current.getCoordo().getY() );
		Point cBottom	= new Point((int)current.getCoordo().getX(), (int)current.getCoordo().getY()+1 );
		Point cLeft	= new Point((int)current.getCoordo().getX()-1, (int)current.getCoordo().getY() );
		
		updateOneNeighboor(current, cTop);
		updateOneNeighboor(current, cRight);
		updateOneNeighboor(current, cBottom);
		updateOneNeighboor(current, cLeft);
	}
	private void updateOneNeighboor(AlgoItem current, Point c){
		AlgoItem ai	= items.get(c);
		if(ai != null){
			ai.calculateWeight(current);
			items.set(c, ai);
		}
	}
	private static class AlgoItem {
		public int estimation, cost;
		public Integer weight;
		public Point c;
		public AlgoItem previous;
		
		public AlgoItem(Point c){
			this.c = c;
		}
		
		public void calculateWeight(AlgoItem current){
			int sum = estimation + cost;
			if(weight == null || sum < weight){
				weight = sum;
				previous = current;
			}
		}
		
		public Point getCoordo(){
			return c;
		}
		public AlgoItem getPrevious(){
			return previous;
		}
		public String toString(){
			String prev = (previous == null) ? null : previous.getCoordo().toString();					
			return "Item {x:"+ c.getX() +", y:"+ c.getY() +", estimation:"+ estimation
					+", cost:"+ cost +", weight:"+ weight +", previous:"+ prev +"}";
		}
	}
	private static class AlgoList extends LinkedList<AlgoItem>{
		private static final long serialVersionUID = -7947454604235897176L;
		
		public AlgoItem get(Point c){
			AlgoItem item = null;
			for (int i=0; i<size(); i++) {
				AlgoItem ai = get(i);
				if(ai.c.equals(c)){
					item = ai;
					break;
				}
			}
			return item;
		}
		public void set(Point c, AlgoItem item){
			for (int i=0; i<size(); i++) {
				AlgoItem ai = get(i);
				if(ai.c.equals(c)){
					set(i, item);
					break;
				}
			}
		}
		public void sortByWeight(){
			sort(new Comparator<AlgoItem>() {
				@Override
				public int compare(AlgoItem item1, AlgoItem item2) {
					if(item1.weight == null && item2.weight == null){ return 0; }
					if(item1.weight != null && item2.weight == null){ return -1; }
					if(item1.weight == null && item2.weight != null){ return 1; }
					return item1.weight.compareTo(item2.weight);
				}
			});
		}
	}
}