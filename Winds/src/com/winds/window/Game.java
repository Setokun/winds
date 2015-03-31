package com.winds.window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.winds.audio.AudioPlayer;
import com.winds.menus.Menu;
import com.winds.menus.Menu.typeMenu;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 2987645570832878854L;
	
	// permet d'avoir la taille de l'écran de l'hôte
	/*static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	static int height = (int)dimension.getHeight();
	static int width  = (int)dimension.getWidth();*/
	
	public static int WIDTH = 400;
	public static int HEIGHT = WIDTH / 4 * 3;
	//public static int WIDTH = width/2;
	//public static int HEIGHT = height/2;
	public static final int SCALE = 2;
	public final String TITLE = "Winds";
	
	

	private boolean running = false;
	private Thread thread;
	public WindowType winType;
	private Menu menu;
	private String bgMusicFilename;
	
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	
	
	private void init(){
		
		winType = WindowType.MENU;

		menu = new Menu();
		
		/////////////// sound initialization
		bgMusicFilename = "res/Winds_Ice_Cavern.mp3";
	    AudioPlayer bgMusic = new AudioPlayer(bgMusicFilename, true);
	    bgMusic.play();
	    ///////////////
        
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		Thread thread = new Thread(this);
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
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		init();
		
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
				System.out.println(updates + " updates, fps : " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick(){
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			// set the number of images prepared before rendering, including the current one
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////////

		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		if(winType == WindowType.MENU){
			menu.type = typeMenu.MAIN;
		    menu.render(g);
		}
		
		
		/////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Window(400, 300, "Neon Platformer", new Game());
	}
}
