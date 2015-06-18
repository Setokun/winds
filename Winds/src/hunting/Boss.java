package hunting;

import java.awt.Point;

public class Boss {
	private Level lvl;
	private Point coordo;		
	
	public Boss(Level level){
		coordo = new Point(1,1);
		lvl = level;
	}
	public void intercept(Player target){
		long startTime = System.currentTimeMillis();
		Hunt h = new Hunt(lvl.getMatrix(), coordo, target.getCoordo());
		Point next = h.getNextMove();
		coordo = next;
		
		//System.out.println("bulle: "+ target.getCoordo().toString());
		//System.out.println("boss : "+ coordo.toString());
		//System.out.println("next : "+ next.toString());
		System.out.println("time : "+ (System.currentTimeMillis() - startTime));
		System.out.println("-----------------------");
		
	}
	public Point getCoordo(){
		return coordo;
	}
	
	
}