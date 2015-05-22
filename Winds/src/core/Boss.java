package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.BufferedImageLoader;
import display.Window;

public class Boss extends GameObject{

	//private Animation animation;
	
	private static BufferedImage sprite;
	private ArrayList<CollisionBox> collisions;
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite = new SpriteSheet(loader.loadImage("/enemies/boss_bee.png"), 96).grabImage(0, 0);
	}
	
	public Boss(float x, float y,ObjectId id) {
		super(x, y, id);

		this.collisions = new ArrayList<CollisionBox>();

		this.collisions.add( new CollisionBox((int)x+8, (int)y+40, 56, 40, ObjectId.Boss) );
		//this.collisions.add( new CollisionBox((int)x+40, (int)y, 16, 48, ObjectId.Boss) );
		
		
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		/*x+=2;
		y++;
		for (int i = 0; i < collisions.size(); i++) {
			getBounds().get(i).x+=2;
			getBounds().get(i).y++;
			
		}*/
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, 96, 96, null);
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				g.setColor(Color.red);

				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.Boss)
						g2d.setColor(Color.MAGENTA);
					g2d.draw(getBounds().get(i).getBounds());
				}
			}
		}
	}

	@Override
	public ArrayList<CollisionBox> getBounds() {
		return collisions;
	}
	
}
