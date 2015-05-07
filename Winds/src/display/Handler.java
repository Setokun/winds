package display;

import java.awt.Graphics;
import java.util.LinkedList;

import core.GameObject;

public class Handler {
public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i=0; i<objects.size(); i++){
			tempObject = objects.get(i);
			
			tempObject.tick(objects);
		}
	}
	
	public void render(Graphics g){
		for(int i=0; i<objects.size(); i++){
			tempObject = objects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	public void removeObject(GameObject object){
		this.objects.remove(object);
	}
	
	public void createLevel(){
		
	}
}
