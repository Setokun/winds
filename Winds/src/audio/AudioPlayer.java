package audio;

import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class AudioPlayer {
	
    private String filename;
    private Player player; 
    //private FloatControl fc; // reste à gérer le volume
    private boolean loop, savedLoop;
    private FileInputStream fis;
    
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
				pauseLocation = fis.available();
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
        				fis = new FileInputStream(filename);
        				player = new Player(fis);
        				totalLength = fis.available();
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
        				fis = new FileInputStream(filename);
        				fis.skip(totalLength - pauseLocation);
        				player = new Player(fis);
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
    
}


