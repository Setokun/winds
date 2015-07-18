package audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import addon.AddonManager;

/**
 * Class used to play musics.
 */
public class AudioPlayer {
	
    private String filename;
    private Player player; 
    private boolean loop, savedLoop, isTheme;
    private BufferedInputStream bis;
    
    private long pauseLocation;
    private long totalLength;
    
    /**
	 * Constructor for the music inside the Jar Theme
	 * @param loop boolean determinate if the music must be repeated
	 */
    public AudioPlayer(boolean loop) {
        this.isTheme = true;
    	this.savedLoop = loop;
        this.loop = loop;
    }
    /**
	 * Constructor for all music outside of the Jar Theme
	 * @param filename String
	 * @param loop boolean determinate if the music must be repeated
	 */
    public AudioPlayer(String filename, boolean loop) {
        this.isTheme = false;
    	this.filename = filename;
        this.savedLoop = loop;
        this.loop = loop;
    }
    /**
	 * method to stop the music from being played
	 */
    public void stop() {
    	if (player != null){
	        loop = false;
	        player.close();
    	}
    }
    /**
	 * method to destroy this object
	 */
    public void pause() {
    	if (player != null){
	        loop = false;
    		try {
    			pauseLocation = bis.available();
			} catch (IOException e) {}
	        player.close();
    	}
    }
    /**
	 * method to pause the music
	 */
    public void play() {
        // run in new thread to play in background
        new Thread() {
			public void run() {
				loop = savedLoop;
				try { 
                	
        			do{
        				if(isTheme){
        					String musicPath = "jar:file:/"+AddonManager.getLoadedJarTheme().getJarFilePath()+"!/"+AddonManager.getLoadedJarTheme().getMusic();
            				URL url = new URL(musicPath);
            				bis = new BufferedInputStream(url.openStream());
        				}else{
        					bis = new BufferedInputStream(getClass().getResourceAsStream(filename));
        				}
        				player = new Player(bis);
        				totalLength = bis.available();
        				player.play(); }while(loop); 
                } catch (Exception e) {}
            }
        }.start();
        
    }
    /**
	 * method to launch the music
	 */
    public void resume() {
        new Thread() {
			public void run() {
                loop = savedLoop;
				try { 
					if(isTheme){
    					String musicPath = "jar:file:/"+AddonManager.getLoadedJarTheme().getJarFilePath()+"!/"+AddonManager.getLoadedJarTheme().getMusic();
        				URL url = new URL(musicPath);
        				bis = new BufferedInputStream(url.openStream());
    				}else{
    					bis = new BufferedInputStream(getClass().getResourceAsStream(filename));
    				}
    				bis.skip(totalLength - pauseLocation);
    				player = new Player(bis);
    				player.play(); 
    				player.close();
    				if(loop){play();}
                }
                catch(JavaLayerException e){
                	play();
                }
				catch (Exception e) { 
                }
            }
        }.start();
        
    }
    /**
	 * static method to play sound effects
	 * @param path String the name of the sfx, with repository or ".mp3" extension
	 */
    public static void playSfx(String path){
		String sfxName = "/resources/sounds/"+ path +".mp3";
	    AudioPlayer sfx = new AudioPlayer(sfxName, false);
	    sfx.play();
	}
    
}


