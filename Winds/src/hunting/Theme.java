package hunting;


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
		b.intercept(p);
	}
	
}
