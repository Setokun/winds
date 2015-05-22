package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.BufferedImageLoader;

public class Collectable extends GameObject{

	
	private CollectableId collectableId;
	private static BufferedImage[] sprites = new BufferedImage[3];
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites[0] = new SpriteSheet(loader.loadImage("/piece_1.png"), 32).grabImage(0, 0);
		sprites[1] = new SpriteSheet(loader.loadImage("/bubulle.png"), 25).grabImage(0, 0);
		sprites[2] = new SpriteSheet(loader.loadImage("/collectables/honey.png"), 32).grabImage(0, 0);
	}
	
	public Collectable(float x, float y, CollectableId collectableId, ObjectId id) {
		super(x, y, id);
		this.collectableId = collectableId;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		if(collectableId == CollectableId.coin)
			g.drawImage(sprites[0], (int)x, (int)y, 16, 16, null);
		if(collectableId == CollectableId.life)
			g.drawImage(sprites[1], (int)x, (int)y, 25, 25, null);
		if(collectableId == CollectableId.honey)
			g.drawImage(sprites[2], (int)x, (int)y, 25, 25, null);
	}

	@Override
	public ArrayList<CollisionBox> getBounds() {
		ArrayList<CollisionBox> cs = new ArrayList<CollisionBox>();
		if(collectableId == CollectableId.coin)
			cs.add( new CollisionBox((int)x, (int)y, 16, 16, ObjectId.Collectable) );
		if(collectableId == CollectableId.life)
			cs.add( new CollisionBox((int)x, (int)y, 25, 25, ObjectId.Collectable) );
		if(collectableId == CollectableId.honey)
			cs.add( new CollisionBox((int)x, (int)y, 32, 32, ObjectId.Collectable) );
		return cs;
	}
	
	public CollectableId getCollectableId(){
		return this.collectableId;
	}

}
