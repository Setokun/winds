package hunting;


public class Theme {
	private Player p;
	private Level l;
	private Boss b;
	
	public Theme(){
		p = new Player();
		l = new Level();
		b = new Boss(this, l);
	}
	public void run(){
		b.intercept(p);
	}
	
	public int[] getTileWeights(){
		return new int[]{1,30,30,30};
	}
	
}
