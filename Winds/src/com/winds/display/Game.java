package com.winds.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.winds.audio.AudioPlayer;
import com.winds.menus.MainMenu;

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
	private BufferedImage bg = null, pauseImage;
	
	private boolean running = false;
	private Thread thread;
	private String bgMusicFilename;
	AudioPlayer bgMusic;
	private boolean paused;
	
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game(){
		
		start();
	}
	
	private void init(){
		this.setPreferredSize(new Dimension(800,600));
		paused = false;
		BufferedImageLoader loader = new BufferedImageLoader();
		bg = loader.loadImage("/background/pirate3.jpg");
		pauseImage = loader.loadImage("/background/pause.jpg");
		
		/////////////// sound initialization
		bgMusicFilename = "res/Winds_Ice_Cavern.mp3";
	    bgMusic = new AudioPlayer(bgMusicFilename, true);
	    bgMusic.play();
	    ///////////////
        
	    this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_Q){
					thread.stop();
					bgMusic.close();
					Window.resize(new Dimension(800, 550));
					Window.affect(new MainMenu());
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(paused)
						paused = false;
					else
						paused = true;
					System.out.println(paused);
				}
			}
		});
	    
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
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////////
		if(paused)
			g.drawImage(pauseImage, 0, 0, this);
		else
			g.drawImage(bg, 0, 0, this);
		
		image.getGraphics().fillRect(50, 100, 40, 30);
		//g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setFont(new Font("bubble & soap", 0, 36));
		g.setColor(Color.WHITE);
		g.drawString("Winds", 150, 150);
		
		/////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
}
