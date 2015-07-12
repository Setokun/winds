package display;

import java.awt.Graphics;
import java.util.ArrayList;

import core.GameObject;

public class Handler {
	
	private final int NB_SQUARES = 60;
	private final int DIM_SQUARE = 128;
	
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i=0; i<objects.size(); i++){
			tempObject = objects.get(i);
			
			tempObject.tick(objects);
		}
	}
	
	public void render(Graphics g){
		int coefX = (int) (Game.WIDTH / (DIM_SQUARE * 1.5f));
		int coefY = (int) (Game.HEIGHT / (DIM_SQUARE * 1.5f));
		
		int wIndex = ((int) objects.get(NB_SQUARES*NB_SQUARES).getX())/DIM_SQUARE - coefX;
		int coefXMax = 2*coefX + 1;
		int hIndex = ((int) objects.get(NB_SQUARES*NB_SQUARES).getY())/DIM_SQUARE - coefY; 
		int coefYMax = 2*coefY + 1;
		
		if(wIndex < 0) wIndex = 0;
		if(hIndex < 0) hIndex = 0;
		
		if(wIndex > (NB_SQUARES - coefXMax)) wIndex = NB_SQUARES - coefXMax;
		if(hIndex > (NB_SQUARES - coefYMax)) hIndex = NB_SQUARES - coefYMax;
		
		for (int i = wIndex; i < wIndex + coefXMax; i++) {
			for (int j = hIndex; j < hIndex + coefYMax; j++) {

				tempObject = objects.get(j*NB_SQUARES + i);
				
				tempObject.render(g);
			}
		}

		for (int i = NB_SQUARES*NB_SQUARES; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
		
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object){
	    this.objects.remove(object);
	}
	
}
