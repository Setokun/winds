package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import menus.LevelCategorySelector;
import addon.AddonManager;
import addons.Level;
import audio.AudioPlayer;
import controls.KeyInput;
import controls.MouseInput;
import core.Block;
import core.CollisionBox;
import core.ObjectId;
import core.Player;
import core.SpriteSheet;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 2987645570832878854L;
	
	public static int WIDTH = (int) Window.profile.getScreenDimensions().getWidth(), 
					  HEIGHT = (int) Window.profile.getScreenDimensions().getHeight();
	public static Camera cam;
	public final String TITLE = "Winds";
	private Level lvl;
	
	private BufferedImage bg = null, pauseImage = null;
	private BufferedImage brambles_sheet = null;
	
	private static boolean pause = false, running = false;
	
	private Thread thread;
	private String bgMusicFilename;
	static AudioPlayer bgMusic;
	private Handler handler;
	static BufferedImage[] brambles;
	
	private int seconds;
	
	public Game(Level lvl){
		this.lvl = lvl;
		System.out.println(lvl);
		start();
	}
	
	private void init(){
		
		seconds = 0;
		pause = false;
		
		
		
		handler = new Handler();
		cam = new Camera(0, 0);
		
		AddonManager.loadLevel(2);
	    AddonManager.loadTheme(1);

		BufferedImageLoader loader = new BufferedImageLoader();
		//bg = loader.loadImage("/background/pirate3.jpg");
		//bg = loader.loadImage("/background/2.jpg");
		//bg = loader.loadImage("/background/ruche.jpg");
		bg = AddonManager.getLoadedTheme().getBackground();
		pauseImage = loader.loadImage("/background/menu_pause.png");
		brambles_sheet = AddonManager.getLoadedTheme().getSprites128();//loader.loadImage("/themes/brambles_21.png");
		

	    brambles = new SpriteSheet(AddonManager.getLoadedTheme().getSprites128(), 128).getSprites();
		
		
		/////////////// sound initialization ///////////////
		//bgMusicFilename = "resources/Winds_Ice_Cavern.mp3";
		bgMusicFilename = "resources/Honey.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    bgMusic.play();
	    ////////////////////////////////////////////////////
	    
	    loadLevelByMatrix(AddonManager.getLoadedLevel().getMatrix());
	    
	    System.out.println(handler.objects.size());
	    
	    this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
	    
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				if(!pause)seconds++;
				System.out.println(updates + " updates, fps : " + frames/* + " Camera : " + cam.getX() + " - " + cam.getY()*/);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		if(!getPause()){
			handler.tick();
			
			for(int i = 0; i < handler.objects.size(); i++){
				if(handler.objects.get(i).getId() == ObjectId.Player){
					cam.tick(handler.objects.get(i));
				}
			}
		}
	}
	
	private void render(){
		
		this.requestFocus();
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			// set the number of images prepared before rendering, including the current one
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(new Font("bubble & soap", 0, 36));
		g.setColor(Color.WHITE);
		
		
		/////////////////////////////////////////
		if(pause){
			g.setColor(Color.red);
			g.drawImage(pauseImage, 0, 0, this.getWidth(), this.getHeight(), this);
			if(Window.debug){
				g.drawRect(297, 185, 180, 40);
				g.drawRect(217,265,330,40);
				g.drawRect(260,348,250,40);
			}
		}else{
			/*g.drawImage(bg, 0, 0, this);
			// affichage du temps écoulé
			//g.setColor(Color.red);
			g.drawString(seconds/60 + ":" + seconds%60, 32, 32);*/
			////////////////////////
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			// drawing the background
			g.drawImage(bg, (int)(cam.getX()/4), (int)(cam.getY()/8), this);
			
			g2d.translate(cam.getX(), cam.getY());
			
			handler.render(g);
			
			g2d.translate( -cam.getX(), -cam.getY());
			
			
			// affichage du temps écoulé
			g.setColor(Color.white);
			g.drawString(seconds/60 + ":" + seconds%60, 32, 32);
			
			/*g.setFont(new Font("bubble & soap", 0, 24));
			g.drawRect(150, 10, 200, 25);
			g.drawString(lvl.titreNiveau, 150, 30);*/
			
		}
		
		/////////////////////////////////////////
		
		
		
		g.dispose();
		bs.show();
	}
	
	private void loadLevelByMatrix(int[][] elements){
		
		
		int[][][] collisionsList = AddonManager.getLoadedTheme().getCollisions();
		
		int number;
		
		for(int i = 0; i < 60; i++){
			for(int j = 0; j < 60; j++){
				
				number = elements[i][j];
				
				if (number == 0) {
					handler.addObject(new Block(j*128, i*128, number, null));
					continue;
				}
				
				ArrayList<CollisionBox> collisions = new ArrayList<CollisionBox>();
				for (int k = 0; k < collisionsList[number].length; k++) {
					ObjectId id = ObjectId.Block;
					if(collisionsList[number][k][4] == 1) id = ObjectId.DangerousBlock;
					collisions.add(new CollisionBox(collisionsList[number][k][0],
													collisionsList[number][k][1],
													collisionsList[number][k][2],
													collisionsList[number][k][3],
													id));
				}
				
				handler.addObject(new Block(j*128, i*128, number, collisions));
				
			}
		}
		
		handler.addObject(new Player(2*128, 1*128, handler, ObjectId.Player));
		
	}
	
	
	public static BufferedImage[] getInstance(){
		return brambles;
	}
	
	public static boolean getPause(){
		return Game.pause;
	}
	
	public static void setPause(){
		if(!pause){
			Game.bgMusic.pause();
			Game.pause = true;
		}
		else{
			Game.bgMusic.resume();
			Game.pause = false;
		}
		
	}
	
	public static void reafficherMenu(){
		Game.running = false;
		Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
	}
}
