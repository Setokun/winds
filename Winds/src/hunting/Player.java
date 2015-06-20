package hunting;

import java.awt.Point;

public class Player {
	private Point coordo;
	
	public Player(){
		coordo = new Point(0,0);
	}
	public Point getCoordo(){
		return coordo;
	}

}