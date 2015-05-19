package addon;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;

import annotation.wCard;
import annotation.wTheme;

public class JarTheme extends JarItem {
	private String music;
	private BufferedImage logo, background, interactions, sprites32, sprites64, sprites128; 
	
	
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
			&& new ImageIcon(logo).getDescription().equals(new ImageIcon(j.logo).getDescription())
			&& new ImageIcon(background).getDescription().equals(new ImageIcon(j.background).getDescription())
			//&& new ImageIcon(interactions).getDescription().equals(new ImageIcon(j.interactions).getDescription())
			&& new ImageIcon(sprites32).getDescription().equals(new ImageIcon(j.sprites32).getDescription())
			&& new ImageIcon(sprites64).getDescription().equals(new ImageIcon(j.sprites64).getDescription())
			&& new ImageIcon(sprites128).getDescription().equals(new ImageIcon(j.sprites128).getDescription());
	}
	
	/*OK*/public String toString(){
		return "JarTheme {mainClass: \""+ mainClass.getName()
						+"\", music: \""+ music
						+"\", logo: \""+ new ImageIcon(logo).getDescription()
						+"\", background: \""+ new ImageIcon(background).getDescription()
						//+"\", interactions: \""+ new ImageIcon(interactions).getDescription()
						+"\", spritesheet32: \""+ new ImageIcon(sprites32).getDescription()
						+"\", spritesheet64: \""+ new ImageIcon(sprites64).getDescription()
						+"\", spritesheet128: \""+ new ImageIcon(sprites128).getDescription() +"\"}";
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
	public BufferedImage getLogo(){
		return logo;
	}
	public BufferedImage getBackground() {
		return background;
	}
	public BufferedImage getInteractions() {
		return interactions;
	}
	public BufferedImage getSprites32() {
		return sprites32;
	}
	public BufferedImage getSprites64() {
		return sprites64;
	}
	public BufferedImage getSprites128() {
		return sprites128;
	}
	public int[][][] getCollisions(){
		
		Field[] fields = mainClass.getDeclaredFields();
		
		System.out.println(mainClass.getName());
		System.out.println(fields.length);
		
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
		try {
			Field f = mainClass.getDeclaredField("collisionsList");
			f.setAccessible(true);
			return (int[][][]) f.get(null);
		} catch (NoSuchFieldException e)	 { e.printStackTrace();
		} catch (SecurityException e)		 { e.printStackTrace();
		} catch (IllegalArgumentException e) { e.printStackTrace();
		} catch (IllegalAccessException e)	 { e.printStackTrace(); }
		return null;
	}
	//endregion
	//region Setters 
	public void setMusic(String music) {
		this.music = music;
	}
	public void setLogo(BufferedImage icon) {
		this.logo = icon;
	}
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	public void setInteractions(BufferedImage interactions) {
		this.interactions = interactions;
	}
	public void setSprites32(BufferedImage sprites32) {
		this.sprites32 = sprites32;
	}
	public void setSprites64(BufferedImage sprites64) {
		this.sprites64 = sprites64;
	}
	public void setSprites128(BufferedImage sprites128) {
		this.sprites128 = sprites128;
	}
	//endregion
}
