package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import menus.LevelCategorySelector;
import addons.Level;
import audio.AudioPlayer;
import controls.KeyInput;
import controls.MouseInput;
import core.Block;
import core.InoffensiveBlock;
import core.ObjectId;
import core.Player;
import core.Texture;
import core.TransparentBlock;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 2987645570832878854L;
	
	public static int WIDTH = 800, HEIGHT = 600;
	public static Camera cam;
	public final String TITLE = "Winds";
	private Level lvl;
	
	private BufferedImage bg = null, pauseImage = null, level = null;
	
	private static boolean pause = false, running = false;
	
	private Thread thread;
	private String bgMusicFilename;
	static AudioPlayer bgMusic;
	private Handler handler;
	static Texture tex;
	
	
	private int seconds;
	
	//private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game(Level lvl){
		this.lvl = lvl;
		System.out.println(lvl);
		start();
	}
	
	private void init(){
		
		seconds = 0;
		
		tex = new Texture();
		
		//this.setPreferredSize(new Dimension(800,600));
		pause = false;
		BufferedImageLoader loader = new BufferedImageLoader();
		bg = loader.loadImage("/background/pirate3.jpg");
		pauseImage = loader.loadImage("/background/menu_pause.png");
		level = loader.loadImage("/levels/level3.png"); // loading the level
		
		
		
		handler = new Handler();
		
		cam = new Camera(0, 0);
		
		/////////////// sound initialization ///////////////
		bgMusicFilename = "resources/Winds_Ice_Cavern.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    bgMusic.play();
	    ////////////////////////////////////////////////////
	    LoadImageLevel(level);
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
				System.out.println(updates + " updates, fps : " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		
		for(int i = 0; i < handler.objects.size(); i++){
			if(handler.objects.get(i).getId() == ObjectId.Player){
				cam.tick(handler.objects.get(i));
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
		if(pause)
			g.drawImage(pauseImage, 0, 0, this.getWidth(), this.getHeight(), this);
		else{
			/*g.drawImage(bg, 0, 0, this);
			// affichage du temps écoulé
			//g.setColor(Color.red);
			g.drawString(seconds/60 + ":" + seconds%60, 32, 32);*/
			////////////////////////
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			for(int i = 0; i<5000; i+=bg.getWidth())
				for(int j = 0; j<5000; j+=bg.getHeight())
					g.drawImage(bg, (int)(i+cam.getX()/4), (int)(j+cam.getY()/4), this);
			
			g2d.translate(cam.getX(), cam.getY());
			
			handler.render(g);
			
			g2d.translate( -cam.getX(), -cam.getY());
			
			
			// affichage du temps écoulé
			g.setColor(Color.white);
			g.drawString(seconds/60 + ":" + seconds%60, 32, 32);
			
		}
		
		//image.getGraphics().fillRect(50, 100, 40, 30);
		//g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

		//g.drawString(lvl.titreNiveau, 150, 50);
		
		/////////////////////////////////////////
		
		
		
		g.dispose();
		bs.show();
	}
	
	private void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		//System.out.println("wdth - height : " + w + " " + h);
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32, Block.blockType.SIMPLE_BLOCK, ObjectId.Block));
				if(red == 128 && green == 128 && blue == 128) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.UPPER_SIMPLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 100 && green == 100 && blue == 100) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.LEFT_SIMPLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 80 && green == 80 && blue == 80) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.LEFT_DOWN_SIMPLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 60 && green == 60 && blue == 60) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.LEFT_UP_CORNER_SIMPLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 40 && green == 50 && blue == 60) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.UPPER_LEFT_SINGLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 90 && green == 50 && blue == 10) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.UPPER_RIGHT_SINGLE_BLOCK, ObjectId.TransparentBlock));
				if(red == 122 && green == 190 && blue == 255) handler.addObject(new InoffensiveBlock(xx*32, yy*32, InoffensiveBlock.blockType.FLOOR_BLOCK, ObjectId.InoffensiveBlock));
				if(red == 190 && green == 122 && blue == 255) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.UPPER_FLOOR_BLOCK, ObjectId.TransparentBlock));
				
				if(red == 190 && green == 100 && blue == 220) handler.addObject(new Block(xx*32, yy*32, Block.blockType.PIRATE_CAISSE_1, ObjectId.Block));
				if(red == 190 && green == 100 && blue == 230) handler.addObject(new Block(xx*32, yy*32, Block.blockType.PIRATE_CAISSE_2, ObjectId.Block));
				if(red == 190 && green == 100 && blue == 240) handler.addObject(new Block(xx*32, yy*32, Block.blockType.PIRATE_CAISSE_3, ObjectId.Block));
				if(red == 190 && green == 100 && blue == 250) handler.addObject(new Block(xx*32, yy*32, Block.blockType.PIRATE_CAISSE_4, ObjectId.Block));

				
				if(red == 200 && green == 105 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_1, ObjectId.Block));
				if(red == 15 && green == 105 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_2, ObjectId.Block));
				if(red == 150 && green == 105 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_3, ObjectId.Block));
				if(red == 200 && green == 150 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_4, ObjectId.Block));
				if(red == 200 && green == 150 && blue == 255) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_5, ObjectId.Block));
				if(red == 255 && green == 105 && blue == 255) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_6, ObjectId.Block));
				if(red == 0 && green == 150 && blue == 255) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_7, ObjectId.Block));
				if(red == 255 && green == 255 && blue == 150) handler.addObject(new Block(xx*32, yy*32, Block.blockType.ICE_BLOCK_8, ObjectId.Block));
				
				
				// ronces
				if(red == 201 && green == 106 && blue == 101) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_1, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 102) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_2, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 103) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_3, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 104) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_4, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_5, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 106) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_6, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 107) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_7, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 108) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_8, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 109) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_9, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 110) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_10, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 111) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_11, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 112) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_12, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 113) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_13, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 114) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_14, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 115) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_15, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 116) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_16, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 117) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_17, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 118) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_18, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 119) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_19, ObjectId.Block));
				if(red == 201 && green == 106 && blue == 120) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_HAUT_20, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 101) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_1, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 102) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_2, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 103) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_3, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 104) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_4, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 105) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_5, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 106) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_6, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 107) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_7, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 108) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_8, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 109) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_9, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 110) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_10, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 111) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_11, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 112) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_12, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 113) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_13, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 114) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_14, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 115) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_15, ObjectId.Block));
				if(red == 0 && green == 106 && blue == 116) handler.addObject(new Block(xx*32, yy*32, Block.blockType.RONCES_COTE_16, ObjectId.Block));
				
				if(red == 101 && green == 106 && blue == 101) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.RONCES_HAUT_1, ObjectId.TransparentBlock));
				if(red == 101 && green == 106 && blue == 102) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.RONCES_HAUT_2, ObjectId.TransparentBlock));
				if(red == 101 && green == 106 && blue == 103) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.RONCES_HAUT_3, ObjectId.TransparentBlock));
				if(red == 101 && green == 106 && blue == 104) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.RONCES_HAUT_4, ObjectId.TransparentBlock));
				if(red == 101 && green == 106 && blue == 105) handler.addObject(new TransparentBlock(xx*32, yy*32, TransparentBlock.blockType.RONCES_HAUT_5, ObjectId.TransparentBlock));
				
				
				
			}
		}
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
			}
		}
		
	}
	
	public static Texture getInstance(){
		return tex;
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
