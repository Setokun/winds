package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import addon.BufferedImageLoader;
import display.Window;

public class Enemy extends GameObject{

	//private Animation animation;
	
	private static BufferedImage[] sprites = new BufferedImage[2];
	private ArrayList<CollisionBox> collisions;
	private int width = 0, widthPath;
	private boolean facingRight;
	private Direction direction;
	
	static {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites[0] = new SpriteSheet(loader.loadImage("/enemies/boss_bee.png"), 96).grabImage(0, 0);
		sprites[1] = new SpriteSheet(loader.loadImage("/enemies/boss_bee_reverse.png"), 96).grabImage(0, 0);
	}
	
	public Enemy(float x, float y,ObjectId id, int widthPath, Direction direction) {
		super(x, y, id);
		this.widthPath = widthPath;
		this.direction = direction;

		this.collisions = new ArrayList<CollisionBox>();

		this.collisions.add( new CollisionBox((int)x+4, (int)y+20, 28, 20, ObjectId.Enemy) );
		//this.collisions.add( new CollisionBox((int)x+40, (int)y, 16, 48, ObjectId.Boss) );
		
		
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		
		if(direction == Direction.left || direction == Direction.right){
			if( this.widthPath > 0){
				if(width == this.widthPath){facingRight = false;}
				if(width == 0){facingRight = true;}
				
				if(facingRight){
					this.x+=(direction == Direction.right)?1:-1;
					for (int i = 0; i < collisions.size(); i++) {
						getBounds().get(i).x++;
					}
					width++;
				}
				else{
					this.x-=(direction == Direction.right)?1:-1;
					for (int i = 0; i < collisions.size(); i++) {
						getBounds().get(i).x--;
					}
					width--;
				}
			}
		}
		
		if(direction == Direction.up || direction == Direction.down){
			if( this.widthPath > 0){
				if(width == this.widthPath){facingRight = false;}
				if(width == 0){facingRight = true;}
				
				if(facingRight){
					this.y+=(direction == Direction.right)?1:-1;
					for (int i = 0; i < collisions.size(); i++) {
						getBounds().get(i).y++;
					}
					width++;
				}
				else{
					this.y-=(direction == Direction.right)?1:-1;
					for (int i = 0; i < collisions.size(); i++) {
						getBounds().get(i).y--;
					}
					width--;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(facingRight)
			g.drawImage(sprites[0], (int)x, (int)y, 48, 48, null);
		else
			g.drawImage(sprites[1], (int)x, (int)y, 48, 48, null);
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				g.setColor(Color.red);

				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.Enemy)
						g2d.setColor(Color.YELLOW);
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
