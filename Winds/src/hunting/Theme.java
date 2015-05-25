package hunting;

import java.util.Comparator;
import java.util.LinkedList;


public class Theme {
	private Player p;
	private Level l;
	private Boss b;
	
	public Theme(){
		p = new Player();
		l = new Level();
		b = new Boss(l);
	}
	public void run(){
		int counter = 19;
		long startTime = System.currentTimeMillis();
		while((counter--) > 0) {
			if(counter%2 == 0){
				p.moveTo(p.getCoordo().getX()+1, p.getCoordo().getY());
			}else{
				p.moveTo(p.getCoordo().getX(), p.getCoordo().getY()+1);
			}
			
			b.intercept(p);
			if(b.getCoordo().equals(p.getCoordo())){
				System.out.println("catched");
				break;
			}
		}
		System.out.println("main time : "+ (System.currentTimeMillis() - startTime));
	}
	

	public static class Coordinate {
		private int x, y;
		
		public Coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public boolean equals(Object o){
			if( !(o instanceof Coordinate) ){ return false; }
			Coordinate c = (Coordinate) o;
			return x == c.x && y == c.y;
		}
		public String toString(){
			return "Coordinate: {x:"+ x +", y:"+ y +"}";
		}
	}
	public static class Boss {
		private Level lvl;
		private Coordinate c;		
		
		public Boss(Level level){
			lvl = level;
			
			int pos = lvl.matrix.length -1;
			c = new Coordinate(pos,pos);
		}
		public void intercept(Player target){
			long startTime = System.currentTimeMillis();
			Algo a = new Algo(lvl.getMatrix(), c, target.getCoordo());
			Coordinate next = a.getNextMove();
			c = next;
			
			System.out.println("bulle: "+ target.getCoordo().toString());
			System.out.println("boss : "+ c.toString());
			System.out.println("next : "+ next.toString());
			System.out.println("time : "+ (System.currentTimeMillis() - startTime));
			System.out.println("-----------------------");
			
		}
		public Coordinate getCoordo(){
			return c;
		}
		
		private static class Algo {
			int[][] matrix;
			Coordinate origin, target;
			AlgoList items, analyzed;
			
			public Algo(int[][] matrix, Coordinate origin, Coordinate target){
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
						AlgoItem item = new AlgoItem(new Coordinate(x,y));
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
			public Coordinate getNextMove(){
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
				Coordinate cTop		= new Coordinate(current.getCoordo().getX(), current.getCoordo().getY()-1 );
				Coordinate cRight	= new Coordinate(current.getCoordo().getX()+1, current.getCoordo().getY() );
				Coordinate cBottom	= new Coordinate(current.getCoordo().getX(), current.getCoordo().getY()+1 );
				Coordinate cLeft	= new Coordinate(current.getCoordo().getX()-1, current.getCoordo().getY() );
				
				updateOneNeighboor(current, cTop);
				updateOneNeighboor(current, cRight);
				updateOneNeighboor(current, cBottom);
				updateOneNeighboor(current, cLeft);
			}
			private void updateOneNeighboor(AlgoItem current, Coordinate c){
				AlgoItem ai	= items.get(c);
				if(ai != null){
					ai.calculateWeight(current);
					items.set(c, ai);
				}
			}
			private static class AlgoItem {
				public int estimation, cost;
				public Integer weight;
				public Coordinate c;
				public AlgoItem previous;
				
				public AlgoItem(Coordinate c){
					this.c = c;
				}
				
				public void calculateWeight(AlgoItem current){
					int sum = estimation + cost;
					if(weight == null || sum < weight){
						weight = sum;
						previous = current;
					}
				}
				
				public Coordinate getCoordo(){
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
				public boolean contains(Coordinate c){
					boolean found = false;
					for (int i=0; i<this.size(); i++) {
						AlgoItem ai = get(i);
						if(ai.c.equals(c)){
							found = true;
							break;
						}
					}
					return found;
				}
				public AlgoItem get(Coordinate c){
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
				public void set(Coordinate c, AlgoItem item){
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
	}
	public static class Player {
		private Coordinate c;
		
		public Player(){
			c = new Coordinate(0,0);
		}
		public Coordinate getCoordo(){
			return c;
		}
		public void moveTo(int x, int y){
			c = new Coordinate(x, y);
		}
	}
	public static class Level {
		int[][] matrix;
		
		public Level(){
			matrix = new int[20][20];
		}
		public int[][] getMatrix(){
			return matrix;
		}
	}
	
}
