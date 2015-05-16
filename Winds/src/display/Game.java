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
		

		BufferedImageLoader loader = new BufferedImageLoader();
		//bg = loader.loadImage("/background/pirate3.jpg");
		bg = loader.loadImage("/background/2.png");
		pauseImage = loader.loadImage("/background/menu_pause.png");
		brambles_sheet = loader.loadImage("/themes/brambles_21.png");
		
		brambles = new SpriteSheet(brambles_sheet, 128).getSprites();
		
		
		/////////////// sound initialization ///////////////
		//bgMusicFilename = "resources/Winds_Ice_Cavern.mp3";
		bgMusicFilename = "resources/Honey.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    //bgMusic.play();
	    ////////////////////////////////////////////////////
	    
	    loadLevelByMatrix(lvl.getMatrix());
	    
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
		
		
		//System.out.println("wdth - height : " + w + " " + h);
		
		for(int i = 0; i < 60; i++){
			for(int j = 0; j < 60; j++){
				
				ArrayList<CollisionBox> collisions1 = new ArrayList<CollisionBox>();
				collisions1.add(new CollisionBox(70,32,58,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 1) handler.addObject(new Block(j*128, i*128, 1, collisions1));
				
				ArrayList<CollisionBox> collisions2 = new ArrayList<CollisionBox>();
				collisions2.add(new CollisionBox(0,32,32,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 2) handler.addObject(new Block(j*128, i*128, 2, collisions2));
				
				ArrayList<CollisionBox> collisions3 = new ArrayList<CollisionBox>();
				collisions3.add(new CollisionBox(72,48,16,80, ObjectId.DangerousBlock));
				collisions3.add(new CollisionBox(88,40,40,88, ObjectId.DangerousBlock));
				if(elements[i][j] == 3) handler.addObject(new Block(j*128, i*128, 3, collisions3));
				
				ArrayList<CollisionBox> collisions4 = new ArrayList<CollisionBox>();
				collisions4.add(new CollisionBox(0,40,56,88, ObjectId.DangerousBlock));
				collisions4.add(new CollisionBox(56,56,8,72, ObjectId.DangerousBlock));
				if(elements[i][j] == 4) handler.addObject(new Block(j*128, i*128, 4, collisions4));
				
			  	ArrayList<CollisionBox> collisions5 = new ArrayList<CollisionBox>();
				collisions5.add(new CollisionBox(0,32,112,56, ObjectId.DangerousBlock));
				collisions5.add(new CollisionBox(112,32,16,48, ObjectId.DangerousBlock));
				collisions5.add(new CollisionBox(64,0,64,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 5) handler.addObject(new Block(j*128, i*128, 5, collisions5));
				
			  	ArrayList<CollisionBox> collisions6 = new ArrayList<CollisionBox>();
				collisions6.add(new CollisionBox(8,32,16,48, ObjectId.DangerousBlock));
				collisions6.add(new CollisionBox(24,32,104,56, ObjectId.DangerousBlock));
				collisions6.add(new CollisionBox(8,0,56,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 6) handler.addObject(new Block(j*128, i*128, 6, collisions6));
				
				ArrayList<CollisionBox> collisions7 = new ArrayList<CollisionBox>();
				collisions7.add(new CollisionBox(72,32,56,56, ObjectId.DangerousBlock));
				collisions7.add(new CollisionBox(72,0,56,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 7) handler.addObject(new Block(j*128, i*128, 7, collisions7));
				
				ArrayList<CollisionBox> collisions8 = new ArrayList<CollisionBox>();
				collisions8.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 8) handler.addObject(new Block(j*128, i*128, 8, collisions8));

				ArrayList<CollisionBox> collisions9 = new ArrayList<CollisionBox>();
				collisions9.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 9) handler.addObject(new Block(j*128, i*128, 9, collisions9));

				ArrayList<CollisionBox> collisions10 = new ArrayList<CollisionBox>();
				collisions10.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 10) handler.addObject(new Block(j*128, i*128, 10, collisions10));

				ArrayList<CollisionBox> collisions11 = new ArrayList<CollisionBox>();
				collisions11.add(new CollisionBox(72,0,56,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 11) handler.addObject(new Block(j*128, i*128, 11, collisions11));

				ArrayList<CollisionBox> collisions12 = new ArrayList<CollisionBox>();
				collisions12.add(new CollisionBox(0,0,56,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 12) handler.addObject(new Block(j*128, i*128, 12, collisions12));

				ArrayList<CollisionBox> collisions13 = new ArrayList<CollisionBox>();
				collisions13.add(new CollisionBox(0,0,60,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 13) handler.addObject(new Block(j*128, i*128, 13, collisions13));

				ArrayList<CollisionBox> collisions14 = new ArrayList<CollisionBox>();
				collisions14.add(new CollisionBox(68,0,60,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 14) handler.addObject(new Block(j*128, i*128, 14, collisions14));
				
				ArrayList<CollisionBox> collisions15 = new ArrayList<CollisionBox>();
				collisions15.add(new CollisionBox(80,48,28,32, ObjectId.DangerousBlock));
				collisions15.add(new CollisionBox(108,48,20,40, ObjectId.DangerousBlock));
				collisions15.add(new CollisionBox(108,40,20,8, ObjectId.Block));
				if(elements[i][j] == 15) handler.addObject(new Block(j*128, i*128, 15, collisions15));

				ArrayList<CollisionBox> collisions16 = new ArrayList<CollisionBox>();
				collisions16.add(new CollisionBox(0,40,110,8, ObjectId.Block));
				collisions16.add(new CollisionBox(0,48,120,40, ObjectId.DangerousBlock));
				collisions16.add(new CollisionBox(64,88,60,40, ObjectId.DangerousBlock));
				if(elements[i][j] == 16) handler.addObject(new Block(j*128, i*128, 16, collisions16));
				
				ArrayList<CollisionBox> collisions17 = new ArrayList<CollisionBox>();
				collisions17.add(new CollisionBox(0,40,16,88, ObjectId.DangerousBlock));
				collisions17.add(new CollisionBox(16,32,28,96, ObjectId.DangerousBlock));
				collisions17.add(new CollisionBox(44,56,16,72, ObjectId.DangerousBlock));
				if(elements[i][j] == 17) handler.addObject(new Block(j*128, i*128, 17, collisions17));

				ArrayList<CollisionBox> collisions18 = new ArrayList<CollisionBox>();
				collisions18.add(new CollisionBox(68,40,16,88, ObjectId.DangerousBlock));
				collisions18.add(new CollisionBox(84,32,28,96, ObjectId.DangerousBlock));
				collisions18.add(new CollisionBox(112,56,16,72, ObjectId.DangerousBlock));
				if(elements[i][j] == 18) handler.addObject(new Block(j*128, i*128, 18, collisions18));
				
				ArrayList<CollisionBox> collisions19 = new ArrayList<CollisionBox>();
				collisions19.add(new CollisionBox(0,64,16,64, ObjectId.DangerousBlock));
				collisions19.add(new CollisionBox(16,48,112,32, ObjectId.DangerousBlock));
				collisions19.add(new CollisionBox(16,80,48,48, ObjectId.DangerousBlock));
				if(elements[i][j] == 19) handler.addObject(new Block(j*128, i*128, 19, collisions19));

				ArrayList<CollisionBox> collisions20 = new ArrayList<CollisionBox>();
				collisions20.add(new CollisionBox(16,0,48,48, ObjectId.DangerousBlock));
				collisions20.add(new CollisionBox(0,48,64,24, ObjectId.DangerousBlock));
				collisions20.add(new CollisionBox(0,72,48,8, ObjectId.DangerousBlock));
				if(elements[i][j] == 20) handler.addObject(new Block(j*128, i*128, 20, collisions20));
				
				ArrayList<CollisionBox> collisions21 = new ArrayList<CollisionBox>();
				collisions21.add(new CollisionBox(32,32,80,16, ObjectId.DangerousBlock));
				collisions21.add(new CollisionBox(0,48,120,32, ObjectId.DangerousBlock));
				collisions21.add(new CollisionBox(72,80,48,48, ObjectId.DangerousBlock));
				if(elements[i][j] == 21) handler.addObject(new Block(j*128, i*128, 21, collisions21));
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
			//Game.bgMusic.resume();
			Game.pause = false;
		}
		
	}
	
	public static void reafficherMenu(){
		Game.running = false;
		Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
	}
}
