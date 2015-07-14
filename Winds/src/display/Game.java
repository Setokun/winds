package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import menus.LevelSelector;
import account.Profile;
import addon.AddonManager;
import addon.BufferedImageLoader;
import addon.level.Type;
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
	
	public static int WIDTH, HEIGHT, numPageFrom;
	public static Camera cam;
	public static Score score;
	public static AudioPlayer bgMusic;
	public static Player player;
	public static int delayAfterFinished;
	private static boolean pause, running, finished, defeat, scoreUploaded, finishedLoading;;
	private static BufferedImage[] instance;
	private static Type typeLvl;
	
	public final String TITLE = "Winds";
	private BufferedImage bubulle, gameoverImage, victory, bg = null, pauseImage = null;
	private Thread thread;
	private Handler handler;
	Font windsPolice36;
	private int seconds, delayVictory, delayGameOver, timeMax;
	
	/**
	 * constructor that takes in parameter data to know where to come back when exiting
	 * @param typeLevel
	 * @param numPage
	 */
	public Game(Type typeLevel, int numPage){
		typeLvl = typeLevel;
		numPageFrom = numPage;
		this.setBackground(Color.BLACK);
		finishedLoading = false;
		WIDTH = (int) Profile.current.getScreenDimensions().getWidth();
		HEIGHT = (int) Profile.current.getScreenDimensions().getHeight();
	    AddonManager.loadJarTheme(AddonManager.getLoadedJarLevel().getLevel().getIdTheme());
	    start();
	}
	/**
	 * Initialize variables and load every resources to launch the game
	 */
	private void init(){
		
		initializeFont();
		
		timeMax = AddonManager.getLoadedJarLevel().getLevel().getTimeMax();
		seconds = 0; delayVictory = 53; delayGameOver = 20; delayAfterFinished = 0;
		pause = false;
		finished = false;
		defeat = false;
		scoreUploaded = false;
		
		score = new Score(AddonManager.getLoadedJarLevel().getLevel().getIdDB());
		handler = new Handler();
		cam = new Camera(0, 0);
		
		initBackgroundsAndImages();
		
	    instance = new SpriteSheet(AddonManager.getLoadedJarTheme().getSprites128(), 128).getSprites();
		
		
		/////////////// sound initialization ///////////////
	    bgMusic = new AudioPlayer(true);
	    bgMusic.play();
	    ////////////////////////////////////////////////////
	    
	    loadLevelByMatrix(AddonManager.getLoadedJarLevel().getLevel().getMatrix());
	    
	    Point startPoint = AddonManager.getLoadedJarLevel().getLevel().getStartPosition();
	    player = new Player(startPoint.x*128, startPoint.y*128, handler, ObjectId.PLAYER);
		handler.addObject(player);
		
		AddonManager.getLoadedJarTheme().loadInteractions(handler);
	    AddonManager.getLoadedJarTheme().loadFront(handler);
	    
	    this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput(handler));
	    finishedLoading = true;
	}

	/**
	 * launch the Game thread
	 */
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * stop the Game thread
	 */
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
	
	/**
	 * Runnable method that run the Game
	 */
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
				if(Window.debug)updates++;
				delta--;
			}
			render();
			if(Window.debug)frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;

				endLevel();
				if(player.getLife() <= 0 && !defeat)
					defeat = true;
				
				if(Window.debug){
					System.out.println(updates + " updates, fps : " + frames);
					updates = 0;
					frames = 0;
				}
				
			}
		}
		stop();
	}
	/**
	 * calls the tick method of the handler, to calculate what has to be done on the next frame
	 */
	private void tick(){
		if(finishedLoading && !getPause() && !defeat){
			handler.tick();
			
			for(int i = 0; i < handler.objects.size(); i++){
				if(handler.objects.get(i).getId() == ObjectId.PLAYER){
					cam.tick(handler.objects.get(i));
				}
			}
		}
	}
	/**
	 * diplays all that has to be displayed : handler, menu screen, victory or gameover screens, etc...
	 */
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
			g.drawImage(pauseImage, 0, 0, this);
			if(Window.debug){
				g.drawRect(122,169,180,40);
				g.drawRect(47,235,330,40);
				g.drawRect(148,307,250,40);
			}
		}else{
			
			// drawing the background
			g.drawImage(bg, (int)(cam.getX()/4), (int)(cam.getY()/8), this);
			
			// drawing all sprites, including player and interactions, through the handler
			g2d.translate(cam.getX(), cam.getY());
			handler.render(g);
			g2d.translate( -cam.getX(), -cam.getY());
			
			
			// draw elapsed time
			if((timeMax - seconds) < 10)
				g.setColor(Color.red);
			else
				g.setColor(Color.white);
			g.drawString((timeMax - seconds)/60 + ":" + (((timeMax - seconds)%60 < 10)? "0"+(timeMax - seconds)%60:(timeMax - seconds)%60), 32, 32);
			
			// rendering the lifes count
			for (int i = 0; i < player.getLife(); i++) {g.drawImage(bubulle, 30 +i*30, 40, this);}
			
			//displaying the score
			g.setColor(Color.white);
			g.drawString(""+score.getScore(), WIDTH - 150, 32);
			
			
			if(player.getLife() <= 0 || (timeMax - seconds) == 0){
				if(!defeat){
					defeat = true;
					bgMusic.stop();
					Game.bgMusic = new AudioPlayer("/resources/musics/gameover.mp3", false);
					Game.bgMusic.play();
				}	
				g.drawImage(gameoverImage, 0, 0, WIDTH, HEIGHT, this);
			}
			
			// rendering victory screen ^^ 
			if(finished) g.drawImage(victory, 0, 0, WIDTH, HEIGHT, this);
		}

		g.dispose();
		bs.show();
	}
	
	/**
	 * loads level matrix from the current loaded level
	 * @param elements
	 */
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
					ObjectId id = ObjectId.BLOCK;
					if(collisionsList[number][k][4] == 1) id = ObjectId.DANGER;
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

	/**
	 * returns the current BufferedImage table of all the sprites of the current theme
	 * @return
	 */
	public static BufferedImage[] getInstance(){
		return instance;
	}
	/**
	 * initializes backgrounds and required images
	 */
	private void initBackgroundsAndImages() {
		BufferedImageLoader loader = new BufferedImageLoader();
		bg = AddonManager.getLoadedJarTheme().getBackground();
		pauseImage = loader.loadImage("/resources/background/menu_pause.png");
		gameoverImage = loader.loadImage("/resources/background/gameover.png");
		victory = loader.loadImage("/resources/background/victory.png");
		//life sprite
		bubulle = new SpriteSheet(loader.loadImage("/resources/collectables/bubulle.png"), 25).grabImage(0, 0);
	}
	/**
	 * determinates what has to be done concerning level's end
	 */
	private void endLevel() {
		
		if(!getPause() && player.getLife() > 0 && !finished && !defeat)
		{
			seconds++;
			score.setTime(seconds);
		}
		if(finished && !pause){
			if(!scoreUploaded){
				scoreUploaded = true;
				int id = AddonManager.getLoadedJarLevel().getLevel().getIdDB();
				if(id != 0)
					score.saveScore();
			}
			delayVictory--;
		}
		
		// decreasing the delay of GameOver before returning to Menu 
		if(defeat && !pause) delayGameOver--;
		
		// increasing delay to be able to click to pass through the end screens
		if(finished || defeat) delayAfterFinished++;
		
		// after delay and little music, we go back to Menu automatically
		if(delayVictory == 0 || delayGameOver == 0) goBackToMenu();
	}
	
	/**
	 * returns if the level is in pause status or not
	 * @return
	 */
	public static boolean getPause(){
		return Game.pause;
	}
	/**
	 * put the level in pause if it's not, or the opposite
	 */
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
	
	/**
	 * call back the menu that called the Game
	 */
	public static void goBackToMenu(){
		Game.bgMusic.stop();
		Game.running = false;
		Window.affect(new LevelSelector(typeLvl, numPageFrom), Window.DIM_STANDARD);
	}
	
	/**
	 * returns if the level was succeeded or failed
	 * @return boolean
	 */
	public static boolean isFinished(){
		return finished || defeat;
	}
	/**
	 * sets if the level was succeeded or failed
	 */
	public static void setFinished(){
		finished = true;
	}
	/**
	 * returns amount of seconds elapsed since the end of the level
	 * @return
	 */
	public static int getDelayAfterFinished(){
		return delayAfterFinished;
	}
	/**
	 * initialize the custom font
	 */
	private void initializeFont() {
		try {
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice36 = new Font ("Serif", Font.BOLD, 18);
		}
	}
	
}
