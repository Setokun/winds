package audio;

import java.io.BufferedInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class AudioPlayer {
	
    private String filename;
    private Player player; 
    //private FloatControl fc; // reste à gérer le volume
    private boolean loop, savedLoop;
    private BufferedInputStream bis;
    
    private long pauseLocation;
    private long totalLength;
    
    
    public AudioPlayer(String filename, boolean loop) {
        this.filename = filename;
        this.savedLoop = loop;
        this.loop = loop;
    }

    public void stop() {
    	if (player != null){
	        loop = false;
	        player.close();
    	}
    }
    
    public void pause() {
    	if (player != null){
	        loop = false;
    		try {
				pauseLocation = bis.available();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        player.close();
    	}
    }
    
    
    public void play() {
        // run in new thread to play in background
        new Thread() {
			public void run() {
				loop = savedLoop;
				try { 
                	
        			do{
        				bis = new BufferedInputStream(getClass().getResourceAsStream(filename));
        				player = new Player(bis);
        				totalLength = bis.available();
        				player.play(); }while(loop); 
                }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
        
    }
    
    public void resume() {
        new Thread() {
			public void run() {
                loop = savedLoop;
				try { 
					bis = new BufferedInputStream(getClass().getResourceAsStream(filename));
    				bis.skip(totalLength - pauseLocation);
    				player = new Player(bis);
    				player.play(); 
    				player.close();
    				if(loop){play();}
                }
                catch(JavaLayerException e){
                	System.out.println("exception levée !!");
                	play();
                }
				catch (Exception e) { 
                	System.out.println(e); 
                }
            }
        }.start();
        
    }
    
    public static void playSfx(String path){
		String sfxName = "/sounds/"+ path +".mp3";
	    AudioPlayer sfx = new AudioPlayer(sfxName, false);
	    sfx.play();
	}
    
}


