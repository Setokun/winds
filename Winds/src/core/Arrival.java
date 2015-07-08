package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.BufferedImageLoader;
import display.Game;
import display.Window;

public class Arrival extends GameObject {

	private static BufferedImage[] sprites = new BufferedImage[2];
	private ArrayList<CollisionBox> collisions;
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites[0] = new SpriteSheet(loader.loadImage("/resources/arrival/pole_nok.png"), 64).grabImage(0, 0);
		sprites[1] = new SpriteSheet(loader.loadImage("/resources/arrival/pole_ok.png"), 64).grabImage(0, 0);
	}
	
	public Arrival(float x, float y, ObjectId id) {
		super(x, y, id);

		this.collisions = new ArrayList<CollisionBox>();
		this.collisions.add( new CollisionBox((int)x+44, (int)y+2, 7, 60, ObjectId.Arrival) );
	}

	@Override
	public void tick(ArrayList<GameObject> object) {}

	@Override
	public void render(Graphics g) {
		if(Game.isFinished())
			g.drawImage(sprites[1], (int)x, (int)y, 64, 64, null);
		else
			g.drawImage(sprites[0], (int)x, (int)y, 64, 64, null);
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				
				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.Arrival)
						g2d.setColor(Color.BLUE);
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
