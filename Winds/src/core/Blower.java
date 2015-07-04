package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.BufferedImageLoader;
import display.Animation;
import display.Window;

public class Blower extends GameObject{
	
	private BufferedImage[] sprites = new BufferedImage[10], spritesRaw;
	private ArrayList<CollisionBox> collisions;
	private Animation animation;
	private Direction direction;
	
	static {
		
	}
	
	public Blower(float x, float y,ObjectId id, Direction direction) {
		super(x, y, id);
		this.direction = direction;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage[] spritesRaw = null;
		if(direction == Direction.down) {
			spritesRaw = new SpriteSheet(loader.loadImage("/souffle_down.png"), 303).getSprites();
		}
		else if(direction == Direction.up) {
			spritesRaw = new SpriteSheet(loader.loadImage("/souffle_up.png"), 303).getSprites();
		}
		else if(direction == Direction.left) {
			spritesRaw = new SpriteSheet(loader.loadImage("/souffle_left.png"), 303).getSprites();
		}
		else if(direction == Direction.right) {
			spritesRaw = new SpriteSheet(loader.loadImage("/souffle_right.png"), 303).getSprites();
		}
		
		for (int i = 1; i < spritesRaw.length; i++) {
			sprites[i-1] = spritesRaw[i];
		}
		animation = new Animation(2, sprites);
		
		this.collisions = new ArrayList<CollisionBox>();

		if(direction == Direction.down || direction == Direction.up)
			this.collisions.add( new CollisionBox((int)x+64, (int)y+24, 56, 160, ObjectId.Blower) );
		if(direction == Direction.left || direction == Direction.right)
			this.collisions.add( new CollisionBox((int)x+24, (int)y+64, 160, 56, ObjectId.Blower) );
	}

	public void tick(ArrayList<GameObject> objects) {
		animation.runAnimation();
	}

	public void render(Graphics g) {
		animation.drawAnimation(g, (int)x, (int)y, 192, 192);
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				g.setColor(Color.red);

				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.Blower)
						g2d.setColor(Color.YELLOW);
					g2d.draw(getBounds().get(i).getBounds());
				}
			}
		}
	}

	public ArrayList<CollisionBox> getBounds() {
		return collisions;
	}
	
	public Direction getDirection(){
		return direction;
	}
}
