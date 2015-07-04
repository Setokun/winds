package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import menus.LevelCategorySelector;
import addon.AddonManager;
import addon.BufferedImageLoader;
import audio.AudioPlayer;
import controls.KeyInput;
import controls.MouseInput;
import core.Block;
import core.CollisionBox;
import core.ObjectId;
import core.Player;
import core.SpriteSheet;
import database.Score;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 2987645570832878854L;
	
	public static int WIDTH = (int) Window.profile.getScreenDimensions().getWidth(), HEIGHT = (int) Window.profile.getScreenDimensions().getHeight();
	public static Camera cam;
	public static Score score;
	public final String TITLE = "Winds";
	private String bgMusicFilename;
	private BufferedImage bubulle, gameover, victory, bg = null, pauseImage = null;
	
	private static boolean pause, running, finished, defeat, scoreUploaded;
	
	private Thread thread;
	
	public static AudioPlayer bgMusic;
	private Handler handler;
	private static BufferedImage[] instance;
	//private InteractionBlock interactions;

	private Player player;
	
	private int seconds, delayVictory, delayGameOver, timeMax;
	
	public Game(){
	    AddonManager.loadJarTheme(AddonManager.getLoadedJarLevel().getLevel().getIdTheme());
	    start();
	}
	
	private void init(){
		
		timeMax = AddonManager.getLoadedJarLevel().getLevel().getTimeMax();
		seconds = 0; delayVictory = 53; delayGameOver = 20;
		pause = false;
		finished = false;
		defeat = false;
		scoreUploaded = false;
		
		score = new Score(AddonManager.getLoadedJarLevel().getLevel().getIdDB());
		handler = new Handler();
		cam = new Camera(0, 0);
		//interactions = new InteractionBlock(handler);
		

		BufferedImageLoader loader = new BufferedImageLoader();
		//bg = loader.loadImage("/background/pirate3.jpg");
		bg = AddonManager.getLoadedJarTheme().getBackground();
		pauseImage = loader.loadImage("/background/menu_pause.png");
		gameover = loader.loadImage("/background/gameover.png");
		victory = loader.loadImage("/background/victory.png");
		
		bubulle = new SpriteSheet(loader.loadImage("/bubulle.png"), 25).grabImage(0, 0);
		
	    instance = new SpriteSheet(AddonManager.getLoadedJarTheme().getSprites128(), 128).getSprites();
		
		
		/////////////// sound initialization ///////////////
		//bgMusicFilename = "resources/Winds_Ice_Cavern.mp3";
		bgMusicFilename = "resources/Honey.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    bgMusic.play();
	    ////////////////////////////////////////////////////
	    
	    loadLevelByMatrix(AddonManager.getLoadedJarLevel().getLevel().getMatrix());
	    Point startPoint = AddonManager.getLoadedJarLevel().getLevel().getStartPosition();
	    player = new Player(startPoint.x*128, startPoint.y*128, handler, ObjectId.Player);
		handler.addObject(player);
		
	    
		
		int[][] elements = AddonManager.getLoadedJarLevel().getLevel().getInteractions();
		
	    loadInteractions(elements);

	    
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

				endLevel();
				
				System.out.println(updates + " updates, fps : " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	private void tick(){
		if(!getPause() && !defeat){
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
		if(bs == null){this.createBufferStrategy(2);return;}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.setFont(new Font("bubble & soap", 0, 36));
		g.setColor(Color.WHITE);
		

		if(pause){
			g.setColor(Color.red);
			g.drawImage(pauseImage, 0, 0, this.getWidth(), this.getHeight(), this);
			if(Window.debug){
				g.drawRect(297,185,180,40);
				g.drawRect(217,265,330,40);
				g.drawRect(260,348,250,40);
			}
		}else{
			
			// drawing the background
			g.drawImage(bg, (int)(cam.getX()/4), (int)(cam.getY()/8), this);
			
			
			g2d.translate(cam.getX(), cam.getY());
			handler.render(g);
			g2d.translate( -cam.getX(), -cam.getY());
			
			
			// draw elapsed time
			if((timeMax - seconds) < 10)
				g.setColor(Color.red);
			else
				g.setColor(Color.white);
			g.drawString((timeMax - seconds)/60 + ":" + (timeMax - seconds)%60, 32, 32);
			
			// rendering the lifes count
			for (int i = 0; i < player.getLife(); i++) {g.drawImage(bubulle, 30 +i*30, 40, this);}
			
			if(player.getLife() == 0 || (timeMax - seconds) == 0){
				if(!defeat){
					defeat = true;
					bgMusic.stop();
					bgMusic = new AudioPlayer("resources/musics/gameover.mp3", false);
				    bgMusic.play();
				}
				
				g.drawImage(gameover, 0, 0, this);
			}
			
			if(finished) g.drawImage(victory, 0, 0, this);
			
		}

		g.dispose();
		bs.show();
	}
	
	private void loadLevelByMatrix(int[][] elements){
		
		
		int[][][] collisionsList = AddonManager.getLoadedJarTheme().getCollisions();
		
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
		
	}
	private void loadInteractions(int[][] elements){

		int number;
		
		for(int i = 0; i < 60; i++){
			for(int j = 0; j < 60; j++){
				
				number = elements[i][j];
				
				if (number == 0) {
					//handler.addObject(new Block(j*128, i*128, number, null));
					continue;
				}
				
				AddonManager.getLoadedJarTheme().loadInteractions(j*128, i*128, number, handler);
				
			}
		}
		
	}
	
	
	public static BufferedImage[] getInstance(){
		return instance;
	}
	
	private void endLevel() {
		if(!getPause() && player.getLife() > 0 && !finished && !defeat)
		{
			seconds++;
			score.setTime(seconds);
		}
		if(finished && !pause){
			if(!scoreUploaded){
				scoreUploaded = true;
				score.setScore(AddonManager.getLoadedJarLevel().getLevel().getIdDB());
			}
			
			delayVictory--;
		}
		if(defeat && !pause) delayGameOver--;
		
		if(delayVictory == 0 || delayGameOver == 0) goBackToMenu();
		
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
	
	public static void goBackToMenu(){
		Game.bgMusic.stop();
		Game.running = false;
		Window.resize(new Dimension(800, 550));
		Window.affect(new LevelCategorySelector());
	}
	
	public static boolean isFinished(){
		return finished;
	}
	public static void setFinished(){
		finished = true;
	}
	
}
