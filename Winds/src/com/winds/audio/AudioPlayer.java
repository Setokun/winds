package com.winds.audio;

import java.io.FileInputStream;
//import javax.sound.sampled.FloatControl;
import javazoom.jl.player.Player;


public class AudioPlayer {
    private String filename;
    private Player player; 
    //private FloatControl fc; // reste à gérer le volume
    private boolean loop;
    
    
    // constructor that takes the name of an MP3 file
    public AudioPlayer(String filename, boolean loop) {
        this.filename = filename;
        this.loop = true;
    }

    public void close() {
    	if (player != null){
	        loop = false;
	        player.close();
    	}
    }
    
    // play the MP3 file to the sound card
    public void play() {
        // run in new thread to play in background
        new Thread() {
			public void run() {
                try { 
                	FileInputStream fis;
        			
        			do{ 
        				fis = new FileInputStream(filename);
        				player = new Player(fis);
        				player.play(); }while(loop); 
                }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
        
    }
    
}
