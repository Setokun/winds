package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.BufferedImageLoader;
import display.Window;

public class Blower extends GameObject{
	
	private BufferedImageLoader loader;
	private BufferedImage[] sprites = new BufferedImage[10], spritesRaw;
	private ArrayList<CollisionBox> collisions;
	private Animation animation;
	private Direction direction;
	
	public Blower(float x, float y,ObjectId id, Direction direction) {
		super(x, y, id);
		this.direction = direction;
		
		loader = new BufferedImageLoader();
		spritesRaw = null;
		if(direction == Direction.DOWN) {
			spritesRaw = new SpriteSheet(loader.loadImage("/resources/souffle_down.png"), 303).getSprites();
		}
		else if(direction == Direction.UP) {
			spritesRaw = new SpriteSheet(loader.loadImage("/resources/souffle_up.png"), 303).getSprites();
		}
		else if(direction == Direction.LEFT) {
			spritesRaw = new SpriteSheet(loader.loadImage("/resources/souffle_left.png"), 303).getSprites();
		}
		else if(direction == Direction.RIGHT) {
			spritesRaw = new SpriteSheet(loader.loadImage("/resources/souffle_right.png"), 303).getSprites();
		}
		
		for (int i = 1; i < spritesRaw.length; i++) {
			sprites[i-1] = spritesRaw[i];
		}
		animation = new Animation(2, sprites);
		
		this.collisions = new ArrayList<CollisionBox>();

		if(direction == Direction.DOWN || direction == Direction.UP)
			this.collisions.add( new CollisionBox((int)x+44, (int)y+12, 37, 105, ObjectId.BLOWER) );
		if(direction == Direction.LEFT || direction == Direction.RIGHT)
			this.collisions.add( new CollisionBox((int)x+12, (int)y+44, 105, 37, ObjectId.BLOWER) );
	}

	/**
	 * determine how the blower will act on each frame
	 * @param ArrayList of GameObject
	 */
	public void tick(ArrayList<GameObject> objects) {
		animation.runAnimation();
	}
	/**
	 * provides the rendering of the blower to Canvas' Graphics
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		animation.drawAnimation(g, (int)x, (int)y, 128, 128);
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				g.setColor(Color.red);

				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.BLOWER)
						g2d.setColor(Color.YELLOW);
					g2d.draw(getBounds().get(i).getBounds());
				}
			}
		}
	}
	/**
	 * returns a list of the collision boxes concerning this GameObject
	 * @return ArrayList of collisionBox
	 */
	public ArrayList<CollisionBox> getBounds() {
		return collisions;
	}
	/**
	 * returns the direction of the blower
	 * @return up, down, left or right
	 */
	public Direction getDirection(){
		return direction;
	}
}
