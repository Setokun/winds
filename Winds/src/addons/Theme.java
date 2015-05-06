package addons;

import java.awt.image.BufferedImage;

import audio.AudioPlayer;

public class Theme {
	private BufferedImage backgroundImage;
	private AudioPlayer music;
	
	public Theme(){
		
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public AudioPlayer getMusic() {
		return music;
	}

	public void setMusic(AudioPlayer music) {
		this.music = music;
	}
}
