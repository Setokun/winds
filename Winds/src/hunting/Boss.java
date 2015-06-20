package hunting;

import java.awt.Point;

public class Boss {
	private Level lvl;
	private Point coordo;		
	
	public Boss(Level level){
		coordo = new Point(59,59);
		lvl = level;
	}
	public void intercept(Player target){
		long startTime = System.currentTimeMillis();
		Point next = Hunt.getNextMove(lvl, coordo, target.getCoordo());
		
		System.out.println("bulle: "+ target.getCoordo().toString());
		System.out.println("boss : "+ coordo.toString());
		System.out.println("next : "+ next.toString());
		System.out.println("-----------------------");
		System.out.println("time : "+ (System.currentTimeMillis() - startTime));		
		
	}
	public Point getCoordo(){
		return coordo;
	}
	
	
}