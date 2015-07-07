package themes.hive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import addon.BufferedImageLoader;
import addon.ThemeBase;
import annotation.wFiles;
import annotation.wTheme;
import core.Arrival;
import core.Collectable;
import core.CollectableId;
import core.CollisionBox;
import core.Direction;
import core.GameObject;
import core.ObjectId;
import core.SpriteSheet;
import display.Handler;
import display.Window;


@wTheme(idDB = 2, name = "Hive", creator = "admin", date = "2015-07-07", description = "Hive Theme")
@wFiles(background = "background.jpg", music = "Honey.mp3", logo = "logo.png",
		interactions = "sprites/interactions.png", sprites64 = "sprites/64.png",
		sprites128 = "sprites/128.png", spritesBoss = "sprites/boss.png")

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
		compatibility.put(new Point(11,3), new Integer[]{2,4,5,11,12,13,14,17,18,20,21});

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
		compatibility.put(new Point(14,3), new Integer[]{2,4,5,11,12,13,14,17,18,20,21});

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
		return new int[][][] {{{}},
				{{0,0,128,128, 0}},
				{{0,0,128,8, 0},{120,0,8,128, 0},{0,120,128,8, 0},{0,0,8,128, 0}},
				{{0,0,28,128, 0},{0,0,128,24, 0},{28,24,16,32, 0}},
				{{0,0,32,128, 1}},
				{{0,0,32,128, 1}},
				{{0,0,32,128, 1}},
				{{0,0,28,128, 0}},
				{{0,0,28,108, 0},{0,108,128,20, 0}},
				{{0,108,128,20, 0}},
				{{0,108,128,20, 0},{0,104,128,4, 1}},
				{{0,108,128,20, 0},{0,104,128,4, 1}},
				{{0,0,128,24, 0}},
				{{0,0,32, 24, 0}},
				{{84,0,44,128, 0}},
				{{96,0,32,24, 0}},
				{{0,108,24,20, 0}},
				{{0,0,128,128, 0}},
				{{0,0,128,128, 0}},
				{{0,0,16,24, 0},{16,0,12,16, 0}},
				{{84,108,44,20, 0}},
				{{0,0,128,24, 0},{84,24,44,104, 0}},
				{{0,108,128,20, 0},{84,0,44,128, 0},{72,84,12,24, 0}},
				{{80,0,48,128, 1}},
				{{80,0,48,128, 1}},
				{{80,0,48,128, 1}},
				{{80,0,48,128, 1}}
			};
	}
	protected int[][] initInteractionsCompatibility(){
		return new int[][]{};
	}
	protected String[] initInteractionTips(){
		return new String[]{
			"Departure", "Arrival", "4 coins", "16 coins",
			"1 life", "1 flower", "2 flowers", "1 mob",
			"3 mobs", "3 mobs", "3 mobs", "3 mobs"
		};
	}

	public void run() {
		methodTest();
	}
	private void methodTest(){
		System.out.println("running unknown method");
	}
	public void loadInteractions(int x, int y, int id, Handler handler) {

		Random rand = new Random();
		switch(id){
		
		case 1:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.honey, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, x+64, CollectableId.life, ObjectId.Collectable));
			break;
		case 2:
			handler.addObject(new Arrival(x+8, y-24, ObjectId.Arrival));
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
			handler.addObject(new Collectable(x+64, x+64, CollectableId.life, ObjectId.Collectable));
			break;
		case 6:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			handler.addObject(new Collectable(x+16, y+16, CollectableId.honey, ObjectId.Collectable));
			break;
		case 7:
			handler.addObject(new Collectable(x+16, y+16, CollectableId.honey, ObjectId.Collectable));
			handler.addObject(new Collectable(x+64, y+64, CollectableId.honey, ObjectId.Collectable));
			break;
		case 8:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			break;
		case 9:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50, Direction.right));
			break;
		case 10:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.down));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50, Direction.down));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50, Direction.down));
			break;
		case 11:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.left));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50, Direction.left));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50, Direction.left));
			break;
		case 12:
			handler.addObject(new Enemy(x   , y   , ObjectId.Enemy, rand.nextInt(100)+50, Direction.up));
			handler.addObject(new Enemy(x+32, y+32, ObjectId.Enemy, rand.nextInt(100)+50, Direction.up));
			handler.addObject(new Enemy(x+64, y+64, ObjectId.Enemy, rand.nextInt(100)+50, Direction.up));
			break;
		case 13:
			handler.addObject(new Boss(x   , y   , ObjectId.Enemy));
			break;
		}
	}
	public void loadFront(Handler handler) {
		// TODO Auto-generated method stub
		
	}

	private static class Enemy extends GameObject{
	
		//private Animation animation;
	
	private BufferedImage[] sprites = new BufferedImage[2];
	private ArrayList<CollisionBox> collisions;
	private int width = 0, widthPath;
	private boolean facingRight;
	private Direction direction;
	
	
	
	public Enemy(float x, float y,ObjectId id, int widthPath, Direction direction) {
		super(x, y, id);
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites[0] = new SpriteSheet(loader.loadImage("/enemies/boss_bee.png"), 96).grabImage(0, 0);
		sprites[1] = new SpriteSheet(loader.loadImage("/enemies/boss_bee_reverse.png"), 96).grabImage(0, 0);
		this.widthPath = widthPath;
		this.direction = direction;
	
		this.collisions = new ArrayList<CollisionBox>();
	
		this.collisions.add( new CollisionBox((int)x+4, (int)y+20, 28, 20, ObjectId.Enemy) );
		//this.collisions.add( new CollisionBox((int)x+40, (int)y, 16, 48, ObjectId.Boss) );
			
			
		}
	
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
	
		public ArrayList<CollisionBox> getBounds() {
			return collisions;
		}
	}

	private static class Boss extends GameObject{
		int count;
		
		private static BufferedImage sprite;
		private ArrayList<CollisionBox> collisions;
		
		static {
			BufferedImageLoader loader = new BufferedImageLoader();
			sprite = new SpriteSheet(loader.loadImage("/enemies/boss_bee.png"), 96).grabImage(0, 0);
		}
		
		public Boss(float x, float y,ObjectId id) {
			super(x, y, id);
			
			this.count = 0;
			this.collisions = new ArrayList<CollisionBox>();

			this.collisions.add( new CollisionBox((int)x+8, (int)y+40, 56, 40, ObjectId.Boss) );
			//this.collisions.add( new CollisionBox((int)x+40, (int)y, 16, 48, ObjectId.Boss) );
			
			
		}

		public void tick(ArrayList<GameObject> objects) {
			count++;
			GameObject player = objects.get(3600);
			if(count >= 3){
				
				this.x+=((player.getX()-this.x)>0)?1:-1;
				this.y+=((player.getY()-this.y)>0)?1:-1;
				this.collisions.get(0).x+=((player.getX()-this.x)>0)?1:-1;
				this.collisions.get(0).y+=((player.getY()-this.y)>0)?1:-1;
				count = 0;
			}
			
		}

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

		public ArrayList<CollisionBox> getBounds() {
			return collisions;
		}
	}
	
}
