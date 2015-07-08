package hunting;

import java.awt.Point;

public class Boss {
	private Theme thm;
	private Level lvl;
	private Point coordo;		
	
	public Boss(Theme theme, Level level){
		thm = theme;
		lvl = level;
		coordo = new Point(0,0);
	}
	public void intercept(Player target){
		long startTime = System.currentTimeMillis();
		Hunt h = new Hunt(thm, lvl);
		Point next = h.getNextMove(coordo, target.getCoordo());
		
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