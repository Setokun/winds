package addon;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;

import annotation.wTheme;

public class JarTheme {
	private Class<?> mainClass;
	private String music;
	private BufferedImage logo, background, interactions,
						  sprites32, sprites64, sprites128; 
	
	//region Public methods
	/*to finish*/public boolean isValid(){
		wTheme aTheme = mainClass.getDeclaredAnnotation(wTheme.class);		
		return aTheme != null && mainClass != null //&&  music != null
			&& logo != null && background != null //&& interactions != null
			&& sprites32 != null && sprites64 != null && sprites128 != null;
	}
	public boolean equals(Object o){
		if( !(o instanceof JarTheme) ){ return false; }
		
		JarTheme j = (JarTheme) o;
		return mainClass.equals(j.mainClass)
			
			&& getIdDB() == j.getIdDB() && getCreator().equals(j.getCreator())
			&& getDate().equals(j.getDate()) && getName().equals(j.getName())
			&& getDescription().equals(j.getDescription())
			
			&& music.equals(j.music)
			&& new ImageIcon(logo).getDescription().equals(new ImageIcon(j.logo).getDescription())
			&& new ImageIcon(background).getDescription().equals(new ImageIcon(j.background).getDescription())
			&& new ImageIcon(interactions).getDescription().equals(new ImageIcon(j.interactions).getDescription())
			&& new ImageIcon(sprites32).getDescription().equals(new ImageIcon(j.sprites32).getDescription())
			&& new ImageIcon(sprites64).getDescription().equals(new ImageIcon(j.sprites64).getDescription())
			&& new ImageIcon(sprites128).getDescription().equals(new ImageIcon(j.sprites128).getDescription());
	}
	public String toString(){
		return "JarTheme {mainClass: \""+ mainClass.getName()
						+"\", wTheme: \""+ wThemeToString()
						//+"\", music: \""+ music
						+"\", logo: \""+ (logo==null ? "null" : logo.getClass().getName())
						+"\", background: \""+ new ImageIcon(background).getDescription()
						//+"\", interactions: \""+ new ImageIcon(interactions).getDescription()
						+"\", spritesheet32: \""+ new ImageIcon(sprites32).getDescription()
						+"\", spritesheet64: \""+ new ImageIcon(sprites64).getDescription()
						+"\", spritesheet128: \""+ new ImageIcon(sprites128).getDescription() +"\"}";
	}
	//endregion
	
	//region Annotation getters - OK 
	public String wThemeToString(){
		return "wTheme {idDB: \""+ getIdDB()
			  +"\", creator: \""+ getCreator()
			  +"\", date: \""+ getDate()
			  +"\", name: \""+ getName()
			  +"\", description: \""+ getDescription() +"\"}";
	}
	public int getIdDB(){
		return mainClass.getDeclaredAnnotation(wTheme.class).idDB();
	}
	public String getCreator(){
		return mainClass.getDeclaredAnnotation(wTheme.class).creator();
	}
	public String getDate(){
		return mainClass.getDeclaredAnnotation(wTheme.class).date();
	}
	public String getName(){
		return mainClass.getDeclaredAnnotation(wTheme.class).name();
	}
	public String getDescription(){
		return mainClass.getDeclaredAnnotation(wTheme.class).description();
	}
	//endregion
	//region Class getters - OK 
	public Class<?> getMainClass(){
		return mainClass;
	}
	public int[][][] getCollisions(){
		Field[] fields = mainClass.getDeclaredFields();
		
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
	//region Member getters - OK 
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
	//endregion
	
	//region Class setters - OK 
	public void setMainClass(Class<?> mainClass) {
		this.mainClass = mainClass;
	}
	//endregion
	//region Memeber setters - OK 
	public void setMusic(String music) {
		this.music = music;
	}
	public void setLogo(BufferedImage logo) {
		this.logo = logo;
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
