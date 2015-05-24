package core;

import java.util.Random;

import display.Handler;

public class InteractionBlock{
	
	Handler handler;
	
	public InteractionBlock(Handler handler){
		this.handler = handler;
	}
	
	public void loadInteraction(int x, int y, int id){
		Random rand = new Random();
		switch(id){
		
		case 1:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.honey, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, x+64, CollectableId.life, ObjectId.Collectable));
			break;
		case 2:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50));
			handler.addObject(new Enemy(x+96, y+96, ObjectId.Enemy, rand.nextInt(100)+50));
			break;
		case 3:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+16, y+32, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+32, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+48, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+48, y+32, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+32, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+16, y+48, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+48, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+16, y+64, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+64, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+48, y+48, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+48, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+48, y+64, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+64, CollectableId.coin, ObjectId.Collectable));
			break;
		case 4:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50));
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		case 10:
			
			break;	
		}
		
	}
	
}
