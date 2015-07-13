package themes.pirate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import addon.ThemeBase;
import annotation.wFiles;
import annotation.wTheme;
import core.Arrival;
import core.Blower;
import core.Collectable;
import core.CollectableId;
import core.CollisionBox;
import core.Direction;
import core.GameObject;
import core.ObjectId;
import core.SpriteSheet;
import display.Animation;
import display.Game;
import display.Handler;
import display.Window;


@wTheme(idDB = 1, name = "Pirate", creator = "admin", date = "2015-07-10", description = "Pirate Theme")
@wFiles(background = "background.jpg", music = "pirate.mp3", logo = "logo.png",
		interactions = "sprites/interactions.png", sprites64 = "sprites/64.png",
		sprites128 = "sprites/128.png", spritesCollectable = "sprites/collectable.png")

public class Theme extends ThemeBase {
	protected Map<Point, Integer[]> initSpriteCompatibility(){
		Map<Point, Integer[]> compatibility = new HashMap<Point, Integer[]>();
		
		compatibility.put(new Point(1,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(1,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(1,2), new Integer[]{11,13,14});
		compatibility.put(new Point(1,3), new Integer[]{2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(2,0), new Integer[]{2,3,4,5,6,10,15,17,19,20,21});
		compatibility.put(new Point(2,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(2,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(2,3), new Integer[]{1,2,3,5,6,7,9,10,14,15,15,17,18,19,20,21});

		compatibility.put(new Point(3,0), new Integer[]{2,3,4,6,15,17,21});
		compatibility.put(new Point(3,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(3,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(3,3), new Integer[]{2,3,9,10,14,17,18,19,20,21});

		compatibility.put(new Point(4,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(4,1), new Integer[]{5,7,8,9,18,19,24});
		compatibility.put(new Point(4,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(4,3), new Integer[]{1,2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(5,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(5,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(5,2), new Integer[]{2,18,19,21,24});
		compatibility.put(new Point(5,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(6,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(6,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(6,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(6,3), new Integer[]{1,2,3,5,6,7,9,10,15,16,17,18,19,20,21});

		compatibility.put(new Point(7,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(7,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(7,2), new Integer[]{9,17});
		compatibility.put(new Point(7,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(8,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(8,1), new Integer[]{5,7,8,9,18,19,24});
		compatibility.put(new Point(8,2), new Integer[]{9,17});
		compatibility.put(new Point(8,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(9,0), new Integer[]{7,8,14,18});
		compatibility.put(new Point(9,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(9,2), new Integer[]{9,17});
		compatibility.put(new Point(9,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(10,0), new Integer[]{2,3,4,6,10,15,17,21});
		compatibility.put(new Point(10,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(10,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(10,3), new Integer[]{2,3,9,10,14,17,18,19,20,21});

		compatibility.put(new Point(11,0), new Integer[]{2,3,4,6,10,15,16,17,21});
		compatibility.put(new Point(11,1), new Integer[]{5,7,8,9,13,14,18,19,20,23,24});
		compatibility.put(new Point(11,2), new Integer[]{1,4,5,6,7,8,15,16,22,23});
		compatibility.put(new Point(11,3), new Integer[]{2,3,9,10,14,17,18,19,20,21});

		compatibility.put(new Point(12,0), new Integer[]{2,3,4,6,10,15,16,17,21});
		compatibility.put(new Point(12,1), new Integer[]{5,7,8,9,13,14,18,19,20,23,24});
		compatibility.put(new Point(12,2), new Integer[]{1,4,5,6,7,8,15,16,22,23});
		compatibility.put(new Point(12,3), new Integer[]{2,3,9,10,14,17,18,19,20,21});

		compatibility.put(new Point(13,0), new Integer[]{2,3,4,6,10,15,16,17,21});
		compatibility.put(new Point(13,1), new Integer[]{5,7,8,9,13,14,18,19,20,23,24});
		compatibility.put(new Point(13,2), new Integer[]{1,4,5,6,7,8,16,22});
		compatibility.put(new Point(13,3), new Integer[]{2,3,9,10,11,12,13,14,17,18,19,20,21,24});

		compatibility.put(new Point(14,0), new Integer[]{1,2,3,4,6,10,15,16,17,21});
		compatibility.put(new Point(14,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(14,2), new Integer[]{9,17});
		compatibility.put(new Point(14,3), new Integer[]{2,3,9,10,11,12,13,14,17,18,19,20,21,24});

		compatibility.put(new Point(15,0), new Integer[]{2,3,4,6,10,11,12,15,16,17,21,22});
		compatibility.put(new Point(15,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(15,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(15,3), new Integer[]{2,3,9,10,14,17,18,19,20,21});

		compatibility.put(new Point(16,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(16,1), new Integer[]{2,4,6,16,17,21,22});
		compatibility.put(new Point(16,2), new Integer[]{11,13,14});
		compatibility.put(new Point(16,3), new Integer[]{2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(17,0), new Integer[]{7,8,14,18});
		compatibility.put(new Point(17,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(17,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(17,3), new Integer[]{2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(18,0), new Integer[]{2,3,4,5,6,10,15,17,19,20,21});
		compatibility.put(new Point(18,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(18,2), new Integer[]{9,17});
		compatibility.put(new Point(18,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(19,0), new Integer[]{2,3,4,5,6,10,15,17,19,20,21});
		compatibility.put(new Point(19,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(19,2), new Integer[]{2,18,19,21,24});
		compatibility.put(new Point(19,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});

		compatibility.put(new Point(20,0), new Integer[]{2,3,4,6,10,15,16,17,21});
		compatibility.put(new Point(20,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(20,2), new Integer[]{2,18,19,21,24});
		compatibility.put(new Point(20,3), new Integer[]{2,3,9,10,11,12,13,14,17,18,19,20,21,24});

		compatibility.put(new Point(21,0), new Integer[]{2,3,4,5,6,10,15,17,19,20,21});
		compatibility.put(new Point(21,1), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(21,2), new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		compatibility.put(new Point(21,3), new Integer[]{2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(22,0), new Integer[]{2,3,4,6,10,11,12,13,15,17,21,22,23,24});
		compatibility.put(new Point(22,1), new Integer[]{5,7,8,9,18,19,24});
		compatibility.put(new Point(22,2), new Integer[]{1,4,5,6,7,8,15,16,22,23});
		compatibility.put(new Point(22,3), new Integer[]{1,2,3,5,6,7,9,10,14,15,16,17,18,19,20,21});

		compatibility.put(new Point(23,0), new Integer[]{2,3,4,6,10,11,12,15,16,17,21,22});
		compatibility.put(new Point(23,1), new Integer[]{5,7,8,9,18,19,24});
		compatibility.put(new Point(23,2), new Integer[]{1,4,5,6,7,8,16,22});
		compatibility.put(new Point(23,3), new Integer[]{2,3,9,10,11,12,13,14,17,18,19,20,21,24});

		compatibility.put(new Point(24,0), new Integer[]{2,3,4,5,6,10,15,17,19,20,21});
		compatibility.put(new Point(24,1), new Integer[]{5,7,8,9,13,14,18,19,20,23,24});
		compatibility.put(new Point(24,2), new Integer[]{1,4,5,6,7,8,16,22});
		compatibility.put(new Point(24,3), new Integer[]{2,3,4,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24});
		
		return compatibility;
	}
	protected int[][][] initCollisionsList(){
		return new int[][][] {
			{{}},
			{{0,56,128,72, 0}},
			{{56,-4,76,36, 0},{24,24,108,40, 0},{0,56,132,76, 0}},
			{{-4,-4,40,40, 0},{56,-4,76,40, 0},{0,24,132,108, 0}},
			{{0,56,32,32, 0},{0,88,64,44, 0},{56,56,76,76, 0}},
			{{56,56,76,76, 0}},
			{{24,24,76,40, 0},{-4,56,136,76, 0}},
			{{88,56,44,76, 0}},
			{{88,56,44,76, 0}},
			{{88,-4,44,136, 0}},
			{{-4,-4,40,40, 0},{56,-4,76,40, 0},{-4,24,136,108, 0}},
			{{-4,-4,72,136, 0},{64,-4,68,72, 0}},
			{{-4,-4,72,136, 0},{88,-4,44,40, 0},{64,24,68,44, 0}},
			{{-4,-4,100,40, 0},{-4,32,40,36, 0},{88,-4,44,72, 0}},
			{{-4,24,72,44, 0},{24,-4,108,40, 0},{88,32,44,100, 0}},
			{{-4,-4,72,136, 0},{64,88,68,44, 0},{92,56,40,40, 0}},
			{{-4,56,136,76, 0}},
			{{88,-4,44,68, 0},{-4,56,136,76, 0}},
			{{56,-4,76,40, 0},{88,32,44,100, 0}},
			{{56,-4,72,136, 0}},
			{{-4,24,72,44, 0},{24,-4,108,40, 0},{56,32,76,100, 0}},
			{{56,-4,76,68, 0},{-4,56,136,76, 0}},
			{{0,56,32,32, 0},{0,88,68,44, 0}},
			{{-4,-4,72,72, 0}},
			{{56,-4,76,40, 0},{88,24,40,40, 0}}
		};
	}
	protected int[][] initInteractionsCompatibility(){
		return new int[][]{};
	}
	protected String[] initInteractionTips(){
		return new String[]{
			"Departure", "Arrival", "4 coins", "16 coins",
			"1 life", "1 chest", "2 chests", "1 mean bird",
			"1 mean bird", "1 mean bird", "1 mean bird", "3 mean birds", "right blower", "down blower", "left blower", "up blower"
		};
	}

	public void loadInteractions(int x, int y, int id, Handler handler) {
		Random rand = new Random();
		switch(id){
		
		case 2:
			handler.addObject(new Arrival(x+8, y+40, ObjectId.Arrival));
			break;
		case 3:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+16, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+16, y+32, CollectableId.coin, ObjectId.Collectable));
			handler.addObject(new Collectable(x+32, y+32, CollectableId.coin, ObjectId.Collectable));
			break;
		case 4:
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
		case 5:
			handler.addObject(new Collectable(x+64, y+64, CollectableId.life, ObjectId.Collectable));
			break;
		case 6:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.valuable, ObjectId.Collectable));
			break;
		case 7:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.valuable, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+64, CollectableId.valuable, ObjectId.Collectable));
			break;
		case 8:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			break;
		case 9:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.down));
			break;
		case 10:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.left));
			break;
		case 11:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.up));
			break;
		case 12:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			break;
		case 13:
			handler.addObject(new Blower(x   , y   , ObjectId.Blower, Direction.right));
			break;
		case 14:
			handler.addObject(new Blower(x   , y   , ObjectId.Blower, Direction.down));
			break;
		case 15:
			handler.addObject(new Blower(x   , y   , ObjectId.Blower, Direction.left));
			break;
		case 16:
			handler.addObject(new Blower(x   , y   , ObjectId.Blower, Direction.up));
			break;
		case 17:
			handler.addObject(new Boss(x   , y   , ObjectId.Enemy));
			break;
		}
	}
	public void loadFront(Handler handler) {
		// TODO Auto-generated method stub
		
	}
	
	private static class Enemy extends GameObject{
		
		private static BufferedImage[] spritesRight = new BufferedImage[8], spritesRightRaw;
		private static BufferedImage[] spritesLeft = new BufferedImage[8], spritesLeftRaw;
		private Animation animationRight, animationLeft;
		private ArrayList<CollisionBox> collisions;
		private int width = 0, widthPath;
		private boolean facingRight;
		private Direction direction;
		
		static {
			try {
				spritesRightRaw = new SpriteSheet(ImageIO.read(Enemy.class.getResource("enemies/mean_bird.png")), 64).getSprites();
				spritesLeftRaw = new SpriteSheet(ImageIO.read(Enemy.class.getResource("enemies/mean_bird_reverse.png")), 64).getSprites();
			} catch (IOException e) { e.printStackTrace(); }
		}
	
		public Enemy(float x, float y,ObjectId id, int widthPath, Direction direction) {
			super(x, y, id);
	
			this.widthPath = widthPath;
			this.direction = direction;
		
			this.collisions = new ArrayList<CollisionBox>();
		
			this.collisions.add( new CollisionBox((int)x+4, (int)y+20, 28, 20, ObjectId.Enemy) );
			
			for (int i = 1; i < spritesRightRaw.length; i++) { spritesRight[i-1] = spritesRightRaw[i]; }
			for (int i = 1; i < spritesLeftRaw.length; i++)  { spritesLeft[i-1]  = spritesLeftRaw[i];  }
			
			animationRight = new Animation(1, spritesRight);
			animationLeft = new Animation(1, spritesLeft);

		}
	
		public void tick(ArrayList<GameObject> object) {
			animationLeft.runAnimation();
			animationRight.runAnimation();
			
			if(direction == Direction.left || direction == Direction.right){
				if( this.widthPath > 0){
					if(width == this.widthPath){facingRight = false;}
					if(width == 0){facingRight = true;}
					
					if(facingRight){
						this.x+=(direction == Direction.right)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).x+=(direction == Direction.right)?1:-1;
						}
						width++;
					}
					else{
						this.x-=(direction == Direction.right)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).x-=(direction == Direction.right)?1:-1;
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
						this.y+=(direction == Direction.down)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).y+=(direction == Direction.down)?1:-1;
						}
						width++;
					}
					else{
						this.y-=(direction == Direction.down)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).y-=(direction == Direction.down)?1:-1;
						}
						width--;
					}
				}
			}
		}
	
		public void render(Graphics g) {
			if((facingRight && direction == Direction.left) || (!facingRight && direction != Direction.left) )
				animationRight.drawAnimation(g, (int)x, (int)y, 48, 48);
			else
				animationLeft.drawAnimation(g, (int)x, (int)y, 48, 48);
			
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
	
		public ArrayList<CollisionBox> getBounds() {
			return collisions;
		}
	}

	private static class Boss extends GameObject{
		int count;
		
		private Animation animation, animationLeft;
		private static BufferedImage[] sprites = new BufferedImage[6], spritesRaw;
		private static BufferedImage[] spritesLeft = new BufferedImage[6], spritesRawLeft;
		private ArrayList<CollisionBox> collisions;
		
		static {
			try {
				spritesRawLeft = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/pirate_boss_reverse.png")), 128).getSprites();
				spritesRaw = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/pirate_boss.png")), 128).getSprites();
			} catch (IOException e) { e.printStackTrace(); }
		}
		
		public Boss(float x, float y,ObjectId id) {
			super(x, y, id);
			
			this.count = 0;
			this.collisions = new ArrayList<CollisionBox>();

			this.collisions.add( new CollisionBox((int)x+8, (int)y+40, 56, 40, ObjectId.Boss) );
			
			for (int i = 1; i < spritesRaw.length; i++) {
				sprites[i-1] = spritesRaw[i];
			}
			for (int i = 1; i < spritesRawLeft.length; i++) {
				spritesLeft[i-1] = spritesRawLeft[i];
			}
			
			animation = new Animation(1, sprites);
			animationLeft =  new Animation(1, spritesLeft);
		}

		public void tick(ArrayList<GameObject> objects) {
			animation.runAnimation();
			animationLeft.runAnimation();
			if(!Game.isFinished()){
				count++;
				GameObject player = objects.get(3600);
				if(count >= 1){
					
					facingRight = ((player.getX()-this.x)<0);
					
					this.x+=((player.getX()-this.x)>0)?1:-1;
					this.y+=((player.getY()-this.y)>0)?1:-1;
					this.collisions.get(0).x+=((player.getX()-this.x)>0)?1:-1;
					this.collisions.get(0).y+=((player.getY()-this.y)>0)?1:-1;
					count = 0;
				}
			}
			
		}

		public void render(Graphics g) {
			if(facingRight)
				animation.drawAnimation(g, (int)x, (int)y, 96, 96);
			else
				animationLeft.drawAnimation(g, (int)x, (int)y, 96, 96);
			
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

		public ArrayList<CollisionBox> getBounds() {
			return collisions;
		}
	}
}
