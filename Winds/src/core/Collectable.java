package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.AddonManager;
import addon.BufferedImageLoader;

public class Collectable extends GameObject{

	
	private CollectableId collectableId;
	private static BufferedImage[] sprites = new BufferedImage[3];
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites[0] = new SpriteSheet(loader.loadImage("/resources/collectables/coin.png"), 32).grabImage(0, 0);
		sprites[1] = new SpriteSheet(loader.loadImage("/resources/collectables/bubulle.png"), 25).grabImage(0, 0);
		sprites[2] = new SpriteSheet(AddonManager.getLoadedJarTheme().getSpritesCollectable(), 32).grabImage(0, 0);
	}
	
	public Collectable(float x, float y, CollectableId collectableId, ObjectId id) {
		super(x, y, id);
		this.collectableId = collectableId;
	}
	
	/**
	 * determine how the collectable will act on each frame
	 * @param ArrayList of GameObject
	 */
	public void tick(ArrayList<GameObject> object) {}
	/**
	 * determine how the collectable will be displayed on each frame
	 * @param g Canvas' Graphics
	 */
	public void render(Graphics g) {
		if(collectableId == CollectableId.coin)
			g.drawImage(sprites[0], (int)x, (int)y, 16, 16, null);
		if(collectableId == CollectableId.life)
			g.drawImage(sprites[1], (int)x, (int)y, 25, 25, null);
		if(collectableId == CollectableId.valuable)
			g.drawImage(sprites[2], (int)x, (int)y, 25, 25, null);
	}
	/**
	 * returns a list of the collision boxes concerning this GameObject
	 * @return ArrayList of collisionBox
	 */
	public ArrayList<CollisionBox> getBounds() {
		ArrayList<CollisionBox> cs = new ArrayList<CollisionBox>();
		if(collectableId == CollectableId.coin)
			cs.add( new CollisionBox((int)x, (int)y, 16, 16, ObjectId.Collectable) );
		if(collectableId == CollectableId.life)
			cs.add( new CollisionBox((int)x, (int)y, 25, 25, ObjectId.Collectable) );
		if(collectableId == CollectableId.valuable)
			cs.add( new CollisionBox((int)x, (int)y, 32, 32, ObjectId.Collectable) );
		return cs;
	}
	/**
	 * returns th ID of the collectable
	 * @return CollectableId
	 */
	public CollectableId getCollectableId(){
		return this.collectableId;
	}

}
