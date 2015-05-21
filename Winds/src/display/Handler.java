package display;

import java.awt.Graphics;
import java.util.ArrayList;

import audio.AudioPlayer;
import core.Collectable;
import core.GameObject;

public class Handler {
	
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i=0; i<objects.size(); i++){
			tempObject = objects.get(i);
			
			tempObject.tick(objects);
		}
	}
	
	public void render(Graphics g){
		
		int wIndex = ((int) objects.get(3600).getX())/128 - 4;
		int hIndex = ((int) objects.get(3600).getY())/128 - 3;
		
		if(wIndex < 0) wIndex = 0;
		if(hIndex < 0) hIndex = 0;
		
		if(wIndex > 51) wIndex = 51;
		if(hIndex > 53) hIndex = 53;
		
		for (int i = wIndex; i < wIndex + 9; i++) {
			for (int j = hIndex; j < hIndex + 7; j++) {

				tempObject = objects.get(j*60 + i);
				
				tempObject.render(g);
			}
		}
		
		//objects.get(3600).render(g);
		for (int i = 3600; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
		
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object){
		CollectableId cid = ((Collectable) object).getCollectableId();
		
		if(cid == CollectableId.coin){
			String sfxName = "resources/sounds/piece.mp3";
		    AudioPlayer sfx = new AudioPlayer(sfxName, false);
		    sfx.play();
		}
		else if(cid == CollectableId.life){
			String sfxName = "resources/sounds/1up.mp3";
		    AudioPlayer sfx = new AudioPlayer(sfxName, false);
		    sfx.play();
		}
	    
	    this.objects.remove(object);
		
	}
	
}
