package themes.brambles;

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
import core.Animation;
import core.Arrival;
import core.Blower;
import core.Collectable;
import core.CollectableId;
import core.CollisionBox;
import core.Direction;
import core.GameObject;
import core.ObjectId;
import core.SpriteSheet;
import display.Game;
import display.Handler;
import display.Window;


@wTheme(idDB = 3, name = "Brambles", creator = "admin", date = "2015-05-17", description = "Brambles Theme")
@wFiles(background = "background.jpg", music = "brambles.mp3", logo = "logo.png",
		interactions = "sprites/interactions.png", sprites64 = "sprites/64.png",
		sprites128 = "sprites/128.png", spritesCollectable = "sprites/collectable.png")

public class Theme extends ThemeBase {
	protected Map<Point, Integer[]> initSpriteCompatibility(){
		Map<Point, Integer[]> compatibility = new HashMap<Point, Integer[]>();
		
		compatibility.put(new Point(1,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(1,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(1,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(1,3), new Integer[]{2,4,5,11,12,13,14,17,18,20,21});

		compatibility.put(new Point(2,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(2,1), new Integer[]{1,3,6,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(2,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(2,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(3,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(3,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(3,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(3,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});
		
		compatibility.put(new Point(4,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(4,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(4,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(4,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(5,0), new Integer[]{3,11,16,18,21});
		compatibility.put(new Point(5,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(5,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(5,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(6,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(6,1), new Integer[]{2,4,8,9,10,20,21});
		compatibility.put(new Point(6,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(6,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(7,0), new Integer[]{3,11,16,18,21});
		compatibility.put(new Point(7,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(7,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(7,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(8,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(8,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(8,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(8,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(9,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(9,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(9,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(9,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(10,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(10,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(10,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(10,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(11,0), new Integer[]{3,11,16,21});
		compatibility.put(new Point(11,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(11,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(11,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(12,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(12,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(12,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(12,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(13,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(13,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(13,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(13,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(14,0), new Integer[]{3,11,16,18,21});
		compatibility.put(new Point(14,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(14,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(14,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(15,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,20});
		compatibility.put(new Point(15,1), new Integer[]{16});
		compatibility.put(new Point(15,2), new Integer[]{1,2,3,4,8,9,10,16,17,18,19,21});
		compatibility.put(new Point(15,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(16,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(16,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(16,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(16,3), new Integer[]{15});

		compatibility.put(new Point(17,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(17,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(17,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(17,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(18,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(18,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(18,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(18,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});
	
		compatibility.put(new Point(19,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(19,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(19,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(19,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(20,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(20,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19,21});
		compatibility.put(new Point(20,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(20,3), new Integer[]{1,3,6,7,8,9,10,19});
	
		compatibility.put(new Point(21,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(21,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(21,2), new Integer[]{5,11,14});
		compatibility.put(new Point(21,3), new Integer[]{1,3,6,7,8,9,10,19});
		
		return compatibility;
	}
	protected int[][][] initCollisionsList(){
		return new int[][][] {
			{{}},
			{{74,48,16,32, 1},{90,40,38,24, 1},{90,64,38,24,1}},
			{{0,48,24,24, 1}},
			{{80,64,16,64, 1},{96,48,32,32, 1},{96,80,24,48, 1}},
			{{0,48,48,32, 1},{8,80,40,48, 1},{48,56,8,72, 1}},
			{{0,32,112,48, 1},{112,32,8,32, 1},{76,0,48,32, 1}},
			{{16,32,16,32, 1},{32,32,96,48, 1},{16,0,40,32, 1}},
			{{80,0,48,32, 1},{80,32,8,40, 1},{88,32,40,44, 1}},
			{{0,40,40,40, 1},{40,40,68,36, 1},{108,44,20,36, 1}},
			{{0,40,128,16, 1},{0,56,128,24, 1},{40,80,48,16, 0}},
			{{0,40,40,40, 1},{40,40,68,36, 1},{108,44,20,36, 1}},
			{{80,0,40,56, 1},{76,56,40,72, 1}},
			{{16,0,40,56, 1},{12,56,40,72, 1}},
			{{16,0,40,56, 1},{12,56,40,40, 1},{12,96,32,24, 1}},
			{{80,0,40,56, 1},{76,56,40,40, 1},{76,96,32,24, 1}},
			{{88,48,20,24, 1},{108,48,20,32, 1},{108,40,20,8, 0}},
			{{0,40,104,8, 0},{0,48,112,32, 1},{112,56,8,24, 1},{72,80,44,48, 1}},
			{{8,44,16,84, 1},{24,36,20,92, 1},{44,56,8,72, 1}},
			{{72,44,16,84, 1},{88,36,20,92, 1},{108,56,8,72, 1}},
			{{12,64,12,64, 1},{24,48,104,24, 1},{24,72,32,56, 1}},
			{{16,0,48,48, 1},{0,48,56,24, 1},{0,72,48,8, 1}},
			{{40,36,56,8, 1},{ 0,44,112,36, 1},{72,80,40,48, 1},{112,48,8,80, 1}}
		};
	}
	protected int[][] initInteractionsCompatibility(){
		return new int[][]{};
	}
	protected String[] initInteractionTips(){
		return new String[]{
			"Departure", "Arrival", "4 coins", "16 coins",
			"1 life", "1 flower", "2 flowers", "1 dragonfly",
			"3 dragonflies", "3 dragonflies", "3 dragonflies", "3 dragonflies", 
			"right blower", "down blower", "left blower", "up blower"
		};
	}
	public void loadInteractions(int x, int y, int id, Handler handler) {
		Random rand = new Random();
		switch(id){
		
		case 2:
			handler.addObject(new Arrival(x+8, y-24, ObjectId.ARRIVAL));
			break;
		case 3:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+16, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			break;
		case 4:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+16, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+48, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+64, y+16, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+48, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+64, y+32, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+16, y+48, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+48, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+16, y+64, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+32, y+64, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+48, y+48, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+64, y+48, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+48, y+64, CollectableId.COIN, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+64, y+64, CollectableId.COIN, ObjectId.COLLECTABLE));
			break;
		case 5:
			handler.addObject(new Collectable(x+64, y+64, CollectableId.LIFE, ObjectId.COLLECTABLE));
			break;
		case 6:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.VALUABLE, ObjectId.COLLECTABLE));
			break;
		case 7:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.VALUABLE, ObjectId.COLLECTABLE));
			handler.addObject(new Collectable(x+64, y+64, CollectableId.VALUABLE, ObjectId.COLLECTABLE));
			break;
		case 8:
			handler.addObject(new Enemy(x   , y   , ObjectId.ENEMY, rand.nextInt(100)+50, Direction.RIGHT));
			break;
		case 9:
			handler.addObject(new Enemy(x   , y   , ObjectId.ENEMY, rand.nextInt(100)+50, Direction.RIGHT));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.RIGHT));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.RIGHT));
			break;
		case 10:
			handler.addObject(new Enemy(x   , y   , ObjectId.ENEMY, rand.nextInt(100)+50, Direction.DOWN));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.DOWN));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.DOWN));
			break;
		case 11:
			handler.addObject(new Enemy(x   , y   , ObjectId.ENEMY, rand.nextInt(100)+50, Direction.LEFT));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.LEFT));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.LEFT));
			break;
		case 12:
			handler.addObject(new Enemy(x   , y   , ObjectId.ENEMY, rand.nextInt(100)+50, Direction.UP));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.UP));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.ENEMY, rand.nextInt(100)+50, Direction.UP));
			break;
		case 13:
			handler.addObject(new Blower(x   , y   , ObjectId.BLOWER, Direction.RIGHT));
			break;
		case 14:
			handler.addObject(new Blower(x   , y   , ObjectId.BLOWER, Direction.DOWN));
			break;
		case 15:
			handler.addObject(new Blower(x   , y   , ObjectId.BLOWER, Direction.LEFT));
			break;
		case 16:
			handler.addObject(new Blower(x   , y   , ObjectId.BLOWER, Direction.UP));
			break;
		case 17:
			handler.addObject(new Boss(x   , y   , ObjectId.ENEMY));
			break;
		}
	}
	public void loadFront(Handler handler){
		Random rand = new Random();
		
		for (int i = 0; i < 30; i++) {
			handler.addObject(new Leaf(rand.nextInt(2000)-2000,rand.nextInt(600), ObjectId.INTERACTION));
		}
	}
	
	private static class Enemy extends GameObject{
	
		private static BufferedImage[] spritesRight = new BufferedImage[4], spritesRightRaw;
		private static BufferedImage[] spritesLeft = new BufferedImage[4], spritesLeftRaw;
		private Animation animationRight, animationLeft;
		private ArrayList<CollisionBox> collisions;
		private int width = 0, widthPath;
		private boolean facingRight;
		private Direction direction;
		
		static {
			try {
				spritesRightRaw = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/dragonfly.png")), 64).getSprites();
				spritesLeftRaw = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/dragonfly_reverse.png")), 64).getSprites();
			} catch (IOException e) { e.printStackTrace(); }
		}
	
		public Enemy(float x, float y,ObjectId id, int widthPath, Direction direction) {
			super(x, y, id);
	
			this.widthPath = widthPath;
			this.direction = direction;
		
			this.collisions = new ArrayList<CollisionBox>();
		
			this.collisions.add( new CollisionBox((int)x+8, (int)y+25, 44, 20, ObjectId.ENEMY) );
			
			for (int i = 1; i < spritesRightRaw.length; i++) { spritesRight[i-1] = spritesRightRaw[i]; }
			for (int i = 1; i < spritesLeftRaw.length; i++)  { spritesLeft[i-1]  = spritesLeftRaw[i];  }
			
			animationRight = new Animation(1, spritesRight);
			animationLeft = new Animation(1, spritesLeft);

		}
	
		public void tick(ArrayList<GameObject> object) {
			animationLeft.runAnimation();
			animationRight.runAnimation();
			
			if(direction == Direction.LEFT || direction == Direction.RIGHT){
				if( this.widthPath > 0){
					if(width == this.widthPath){facingRight = false;}
					if(width == 0){facingRight = true;}
					
					if(facingRight){
						this.x+=(direction == Direction.RIGHT)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).x+=(direction == Direction.RIGHT)?1:-1;
						}
						width++;
					}
					else{
						this.x-=(direction == Direction.RIGHT)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).x-=(direction == Direction.RIGHT)?1:-1;
						}
						width--;
					}
				}
			}
			
			if(direction == Direction.UP || direction == Direction.DOWN){
				if( this.widthPath > 0){
					if(width == this.widthPath){facingRight = false;}
					if(width == 0){facingRight = true;}
					
					if(facingRight){
						this.y+=(direction == Direction.DOWN)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).y+=(direction == Direction.DOWN)?1:-1;
						}
						width++;
					}
					else{
						this.y-=(direction == Direction.DOWN)?1:-1;
						for (int i = 0; i < collisions.size(); i++) {
							getBounds().get(i).y-=(direction == Direction.DOWN)?1:-1;
						}
						width--;
					}
				}
			}
		}
	
		public void render(Graphics g) {
			if((facingRight && direction == Direction.LEFT) || (!facingRight && direction != Direction.LEFT) )
					animationRight.drawAnimation(g, (int)x, (int)y, 64, 64);
			else
				animationLeft.drawAnimation(g, (int)x, (int)y, 64, 64);
			
			if(Window.debug){
				if(this.getBounds() != null){
					Graphics2D g2d = (Graphics2D) g;
					g.setColor(Color.red);
	
					for (int i = 0; i < getBounds().size(); i++) {
						if(getBounds().get(i).getId() == ObjectId.ENEMY)
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
		private static BufferedImage[] sprites = new BufferedImage[4], spritesRaw;
		private static BufferedImage[] spritesLeft = new BufferedImage[4], spritesRawLeft;
		private ArrayList<CollisionBox> collisions;
		
		static {
			try {
				spritesRaw = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/dragonfly.png")), 64).getSprites();
				spritesRawLeft = new SpriteSheet(ImageIO.read(Boss.class.getResource("enemies/dragonfly_reverse.png")), 64).getSprites();
			} catch (IOException e) { e.printStackTrace(); }
		}
		
		public Boss(float x, float y,ObjectId id) {
			super(x, y, id);
			
			this.count = 0;
			this.collisions = new ArrayList<CollisionBox>();

			this.collisions.add( new CollisionBox((int)x+16, (int)y+50, 88, 40, ObjectId.BOSS) );
			
			for (int i = 1; i < spritesRaw.length; i++) {
				sprites[i-1] = spritesRaw[i];
			}
			for (int i = 1; i < spritesRawLeft.length; i++) {
				spritesLeft[i-1] = spritesRawLeft[i];
			}
			
			animation = new Animation(1, sprites);
			animationLeft = new Animation(1, spritesLeft);
		}

		public void tick(ArrayList<GameObject> objects) {
			animation.runAnimation();
			animationLeft.runAnimation();
			
			if(!Game.isFinished()){
				count++;
				GameObject player = objects.get(3600);
				if(count >= 2){
					
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
				animation.drawAnimation(g, (int)x, (int)y, 128, 128);
			else
				animationLeft.drawAnimation(g, (int)x, (int)y, 128, 128);
			
			if(Window.debug){
				if(this.getBounds() != null){
					Graphics2D g2d = (Graphics2D) g;
					g.setColor(Color.red);

					for (int i = 0; i < getBounds().size(); i++) {
						if(getBounds().get(i).getId() == ObjectId.BOSS)
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
	
	private static class Leaf extends GameObject{
		
		BufferedImage sprites;
		private float x1, y1;
		
		public Leaf(float x, float y, ObjectId id) {
			super(x, y, id);
			x1 = x - 400;
			y1= y - 400;
			try {
				sprites = new SpriteSheet(ImageIO.read(Leaf.class.getResource("sprites/leaf.png")), 78).grabImage(0, 0);
			} catch (IOException e) { e.printStackTrace(); }
		}

		public void tick(ArrayList<GameObject> object) {
			x1+=5;
			if(x1 > 1024){
				x1 = -1024;
			}
			x= Game.player.getX()+32 + x1;
			y1+=1;
			if(y1 > 320){
				y1 = -320;
			}
			y= Game.player.getY()+32 + y1;
		}

		public void render(Graphics g) {
			g.drawImage(sprites, (int)x, (int)y, 25, 25, null);
		}

		public ArrayList<CollisionBox> getBounds() {
			return null;
		}

	}
	
}
