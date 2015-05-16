package addon;

import javax.swing.ImageIcon;

import annotation.wCard;
import annotation.wTheme;

public class JarTheme extends JarItem {
	private String music;
	private ImageIcon logo, background, interactions, sprites32, sprites64, sprites128; 
	
	
	/*to finish*/public boolean isValid(){
		wCard aCard = mainClass.getDeclaredAnnotation(wCard.class);
		wTheme aTheme = mainClass.getDeclaredAnnotation(wTheme.class);		
		
		return mainClass != null && aCard != null && aTheme != null && music != null
			&& logo != null && background != null //&& interactions != null
			&& sprites32 != null && sprites64 != null && sprites128 != null;
	}
	
	/*to finish*/public boolean equals(Object o){
		if( !(o instanceof JarTheme) ){ return false; }
		
		JarTheme j = (JarTheme) o;
		return super.equals(o) && getIdDB() == j.getIdDB() //&& music.equals(j.music)
			&& logo.getDescription().equals(j.logo.getDescription())
			&& background.getDescription().equals(j.background.getDescription())
			//&& interactions.getDescription().equals(j.interactions.getDescription())
			&& sprites32.getDescription().equals(j.sprites32.getDescription())
			&& sprites64.getDescription().equals(j.sprites64.getDescription())
			&& sprites128.getDescription().equals(j.sprites128.getDescription());
	}
	
	/*OK*/public String toString(){
		return "JarTheme {mainClass: \""+ mainClass.getName()
						+"\", music: \""+ music
						+"\", logo: \""+ logo.getDescription()
						+"\", background: \""+ background.getDescription()
						//+"\", interactions: \""+ interactions.getDescription()
						+"\", spritesheet32: \""+ sprites32.getDescription()
						+"\", spritesheet64: \""+ sprites64.getDescription()
						+"\", spritesheet128: \""+ sprites128.getDescription() +"\"}";
	}
	
	//region Annotation Getters 
	/*OK*/public String wThemeToString(){
		return "wTheme {idDB: \""+ getIdDB() +"\"}";
	}
	/*OK*/public int getIdDB(){
		return mainClass.getDeclaredAnnotation(wTheme.class).idDB();
	}
	//endregion
	
	//region Getters 
	public String getMusic() {
		return music;
	}
	public ImageIcon getLogo(){
		return logo;
	}
	public ImageIcon getBackground() {
		return background;
	}
	public ImageIcon getInteractions() {
		return interactions;
	}
	public ImageIcon getSprites32() {
		return sprites32;
	}
	public ImageIcon getSprites64() {
		return sprites64;
	}
	public ImageIcon getSprites128() {
		return sprites128;
	}
	//endregion
	//region Setters 
	public void setMusic(String music) {
		this.music = music;
	}
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}
	public void setBackground(ImageIcon background) {
		this.background = background;
	}
	public void setInteractions(ImageIcon interactions) {
		this.interactions = interactions;
	}
	public void setSprites32(ImageIcon sprites32) {
		this.sprites32 = sprites32;
	}
	public void setSprites64(ImageIcon sprites64) {
		this.sprites64 = sprites64;
	}
	public void setSprites128(ImageIcon sprites128) {
		this.sprites128 = sprites128;
	}
	//endregion
}
