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
import core.InoffensiveBlock;
import core.ObjectId;
import core.Player;
import core.Texture;
import core.TransparentBlock;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 2987645570832878854L;
	
	public static int WIDTH = (int) Window.profile.getScreenDimensions().getWidth(), HEIGHT = 600;
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
		//level = loader.loadImage("/levels/level3.png"); // loading the level
		//level = loader.loadImage("/levels/bramble_map1.png"); // loading the level
		
		
		handler = new Handler();
		
		cam = new Camera(0, 0);
		
		/////////////// sound initialization ///////////////
		bgMusicFilename = "resources/Winds_Ice_Cavern.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    //bgMusic.play();
	    ////////////////////////////////////////////////////
	    
	    //LoadImageLevel(level);
	    //loadImageLevel128(level);
	    
	    /*int [][] elements = new int[60][60];
	    
	    for (int i = 0; i < 10; i++) {
			elements[0][i] = 8;
		}*/
	    
	    int [][] elements = {
	    		{3,8,9,10,8,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{11,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{11,15,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{7,8,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	    		};
	    
	    loadImageLevelByMatrix2(elements);
	    
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
			
			for(int i = 0; i<5000; i+=bg.getWidth())
				for(int j = 0; j<5000; j+=bg.getHeight())
					g.drawImage(bg, (int)(i+cam.getX()/4), (int)(j+cam.getY()/4), this);
			
			g2d.translate(cam.getX(), cam.getY());
			
			handler.render(g);
			
			g2d.translate( -cam.getX(), -cam.getY());
			
			
			// affichage du temps écoulé
			g.setColor(Color.white);
			g.drawString(seconds/60 + ":" + seconds%60, 32, 32);
			
			g.setFont(new Font("bubble & soap", 0, 24));
			g.drawRect(150, 10, 200, 25);
			g.drawString(lvl.titreNiveau, 150, 30);
			
		}
		
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
	
	private void loadImageLevel128(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		//System.out.println("wdth - height : " + w + " " + h);
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				ArrayList<int[]> bounds = new ArrayList<int[]>();
				int[] a1 = {0,0,16,32};
				bounds.add(a1);
				int[] a2 = {0,0,8,32};
				bounds.add(a2);
				
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_01, ObjectId.Block));
				if(red == 245 && green == 245 && blue == 245) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_02, ObjectId.Block));
				if(red == 235 && green == 235 && blue == 235) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_03, ObjectId.Block));
				if(red == 225 && green == 225 && blue == 225) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_04, ObjectId.Block));
				if(red == 215 && green == 215 && blue == 215) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_05, ObjectId.Block));
				if(red == 205 && green == 205 && blue == 205) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_06, ObjectId.Block));
				if(red == 195 && green == 195 && blue == 195) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_07, ObjectId.Block));
				if(red == 185 && green == 185 && blue == 185) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_08, ObjectId.Block, bounds));
				if(red == 175 && green == 175 && blue == 175) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_09, ObjectId.Block));
				if(red == 165 && green == 165 && blue == 165) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_10, ObjectId.Block));
				if(red == 155 && green == 155 && blue == 155) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_11, ObjectId.Block));
				if(red == 145 && green == 145 && blue == 145) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_12, ObjectId.Block));
				if(red == 135 && green == 135 && blue == 135) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_13, ObjectId.Block));
				if(red == 125 && green == 125 && blue == 125) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_14, ObjectId.Block));
				if(red == 115 && green == 115 && blue == 115) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_15, ObjectId.Block));
				if(red == 105 && green == 105 && blue == 105) handler.addObject(new Block(xx*128, yy*128, Block.blockType.BRAMBLES_16, ObjectId.Block));
			}
		}
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx*128, yy*128, handler, ObjectId.Player));
			}
		}
		
	}
	
	
	private void loadImageLevelByMatrix(int[][] elements){
		
		
		//System.out.println("wdth - height : " + w + " " + h);
		
		for(int i = 0; i < 60; i++){
			for(int j = 0; j < 60; j++){
				
				/*ArrayList<int[]> bounds = new ArrayList<int[]>();
				int[] a1 = {0,24,128,64};
				bounds.add(a1);*/
				/*int[] a2 = {0,0,8,32};
				bounds.add(a2);*/
				
				ArrayList<int[]> bounds1 = new ArrayList<int[]>();int[] a1_1 = {70,32,58,56};bounds1.add(a1_1);
				if(elements[i][j] == 1) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_01, ObjectId.Block, bounds1));
				
				ArrayList<int[]> bounds2 = new ArrayList<int[]>();int[] a2_1 = {0,32,32,56};bounds2.add(a2_1);
				if(elements[i][j] == 2) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_02, ObjectId.Block, bounds2));
				
				ArrayList<int[]> bounds3 = new ArrayList<int[]>();int[] a3_1 = {64,40,64,88};bounds3.add(a3_1);
				if(elements[i][j] == 3) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_03, ObjectId.Block, bounds3));
				
				ArrayList<int[]> bounds4 = new ArrayList<int[]>();int[] a4_1 = {0,40,64,88};bounds4.add(a4_1);
				if(elements[i][j] == 4) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_04, ObjectId.Block, bounds4));
				
				ArrayList<int[]> bounds5 = new ArrayList<int[]>();int[] a5_1 = {0,32,128,56};bounds5.add(a5_1);
																  int[] a5_2 = {64,0,64,32};bounds5.add(a5_2);
				if(elements[i][j] == 5) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_05, ObjectId.Block, bounds5));
				
				ArrayList<int[]> bounds6 = new ArrayList<int[]>();int[] a6_1 = {8,32,120,56};bounds6.add(a6_1);
																  int[] a6_2 = {8,0,56,32};bounds6.add(a6_2);
				if(elements[i][j] == 6) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_06, ObjectId.Block, bounds6));
				
				ArrayList<int[]> bounds7 = new ArrayList<int[]>();int[] a7_1 = {72,32,56,56};bounds7.add(a7_1);
				  												  int[] a7_2 = {72,0,56,32};bounds7.add(a7_2);
				if(elements[i][j] == 7) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_07, ObjectId.Block, bounds7));
				
				ArrayList<int[]> bounds8 = new ArrayList<int[]>();int[] a8_1 = {0,32,128,56};bounds8.add(a8_1);
				if(elements[i][j] == 8) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_08, ObjectId.Block, bounds8));
				
				ArrayList<int[]> bounds9 = new ArrayList<int[]>();int[] a9_1 = {0,32,128,56};bounds9.add(a9_1);
				if(elements[i][j] == 9) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_09, ObjectId.Block, bounds9));
				
				ArrayList<int[]> bounds10 = new ArrayList<int[]>();int[] a10_1 = {0,32,128,56};bounds10.add(a10_1);
				if(elements[i][j] == 10) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_10, ObjectId.Block, bounds10));
				
				ArrayList<int[]> bounds11 = new ArrayList<int[]>();int[] a11_1 = {72,0,56,128};bounds11.add(a11_1);
				if(elements[i][j] == 11) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_11, ObjectId.Block, bounds11));
				
				ArrayList<int[]> bounds12 = new ArrayList<int[]>();int[] a12_1 = {0,0,56,128};bounds12.add(a12_1);
				if(elements[i][j] == 12) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_12, ObjectId.Block, bounds12));
				
				ArrayList<int[]> bounds13 = new ArrayList<int[]>();int[] a13_1 = {0,0,60,128};bounds13.add(a13_1);
				if(elements[i][j] == 13) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_13, ObjectId.Block, bounds13));
				
				ArrayList<int[]> bounds14 = new ArrayList<int[]>();int[] a14_1 = {68,0,60,128};bounds14.add(a14_1);
				if(elements[i][j] == 14) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_14, ObjectId.Block, bounds14));
				
				ArrayList<int[]> bounds15 = new ArrayList<int[]>();int[] a15_1 = {80,40,48,48};bounds15.add(a15_1);
				if(elements[i][j] == 15) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_15, ObjectId.Block, bounds15));
				
				ArrayList<int[]> bounds16 = new ArrayList<int[]>();int[] a16_1 = {0,40,120,48};bounds16.add(a16_1);
																   int[] a16_2 = {64,88,60,40};bounds16.add(a16_2);
				if(elements[i][j] == 16) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_16, ObjectId.Block, bounds16));
			}
		}
		
		System.out.println(handler.objects.size());
		
		handler.addObject(new Player(2*128, 1*128, handler, ObjectId.Player));
		
	}

	private void loadImageLevelByMatrix2(int[][] elements){
		
		
		//System.out.println("wdth - height : " + w + " " + h);
		
		for(int i = 0; i < 60; i++){
			for(int j = 0; j < 60; j++){
				
				ArrayList<CollisionBox> collisions1 = new ArrayList<CollisionBox>();
				collisions1.add(new CollisionBox(70,32,58,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 1) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_01, collisions1));
				
				ArrayList<CollisionBox> collisions2 = new ArrayList<CollisionBox>();
				collisions2.add(new CollisionBox(0,32,32,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 2) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_02, collisions2));
				
				ArrayList<CollisionBox> collisions3 = new ArrayList<CollisionBox>();
				collisions3.add(new CollisionBox(72,48,16,80, ObjectId.DangerousBlock));
				collisions3.add(new CollisionBox(88,40,40,88, ObjectId.DangerousBlock));
				if(elements[i][j] == 3) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_03, collisions3));
				
				ArrayList<CollisionBox> collisions4 = new ArrayList<CollisionBox>();
				collisions4.add(new CollisionBox(0,40,64,88, ObjectId.DangerousBlock));
				if(elements[i][j] == 4) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_04, collisions4));
				
			  	ArrayList<CollisionBox> collisions5 = new ArrayList<CollisionBox>();
				collisions5.add(new CollisionBox(0,32,112,56, ObjectId.DangerousBlock));
				collisions5.add(new CollisionBox(112,32,16,48, ObjectId.DangerousBlock));
				collisions5.add(new CollisionBox(64,0,64,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 5) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_05, collisions5));
				
			  	ArrayList<CollisionBox> collisions6 = new ArrayList<CollisionBox>();
				collisions6.add(new CollisionBox(8,32,120,56, ObjectId.DangerousBlock));
				collisions6.add(new CollisionBox(8,0,56,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 6) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_06, collisions6));
				
				ArrayList<CollisionBox> collisions7 = new ArrayList<CollisionBox>();
				collisions7.add(new CollisionBox(72,32,56,56, ObjectId.DangerousBlock));
				collisions7.add(new CollisionBox(72,0,56,32, ObjectId.DangerousBlock));
				if(elements[i][j] == 7) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_07, collisions7));
				
				ArrayList<CollisionBox> collisions8 = new ArrayList<CollisionBox>();
				collisions8.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 8) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_08, collisions8));

				ArrayList<CollisionBox> collisions9 = new ArrayList<CollisionBox>();
				collisions9.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 9) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_09, collisions9));

				ArrayList<CollisionBox> collisions10 = new ArrayList<CollisionBox>();
				collisions10.add(new CollisionBox(0,32,128,56, ObjectId.DangerousBlock));
				if(elements[i][j] == 10) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_10, collisions10));

				ArrayList<CollisionBox> collisions11 = new ArrayList<CollisionBox>();
				collisions11.add(new CollisionBox(72,0,56,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 11) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_11, collisions11));

				ArrayList<CollisionBox> collisions12 = new ArrayList<CollisionBox>();
				collisions12.add(new CollisionBox(0,0,56,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 12) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_12, collisions12));

				ArrayList<CollisionBox> collisions13 = new ArrayList<CollisionBox>();
				collisions13.add(new CollisionBox(0,0,60,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 13) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_13, collisions13));

				ArrayList<CollisionBox> collisions14 = new ArrayList<CollisionBox>();
				collisions14.add(new CollisionBox(68,0,60,128, ObjectId.DangerousBlock));
				if(elements[i][j] == 14) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_14, collisions14));
				
				ArrayList<CollisionBox> collisions15 = new ArrayList<CollisionBox>();
				collisions15.add(new CollisionBox(80,48,28,32, ObjectId.DangerousBlock));
				collisions15.add(new CollisionBox(108,48,20,40, ObjectId.DangerousBlock));
				collisions15.add(new CollisionBox(108,40,20,8, ObjectId.Block));
				if(elements[i][j] == 15) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_15, collisions15));

				ArrayList<CollisionBox> collisions16 = new ArrayList<CollisionBox>();
				collisions16.add(new CollisionBox(0,40,110,8, ObjectId.Block));
				collisions16.add(new CollisionBox(0,48,120,40, ObjectId.DangerousBlock));
				collisions16.add(new CollisionBox(64,88,60,40, ObjectId.DangerousBlock));
				if(elements[i][j] == 16) handler.addObject(new Block(j*128, i*128, Block.blockType.BRAMBLES_16, collisions16));
			}
		}
		
		handler.addObject(new Player(2*128, 1*128, handler, ObjectId.Player));
		
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
