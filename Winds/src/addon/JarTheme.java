package addon;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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
	public Map<Point, Integer[]> getSpritesCompatibility(){
		// test
		Map<Point, Integer[]> compatibility = new HashMap<Point, Integer[]>();
		
		compatibility.put(new Point(1,0), new Integer[]{1,2,5,6,7,8,9,10,20});
		compatibility.put(new Point(1,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(1,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(1,3), new Integer[]{2,4,5,11,12,13,14,15,16,17,18,20,21});

		compatibility.put(new Point(2,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(2,1), new Integer[]{1,3,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(2,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(2,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(3,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(3,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(3,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(3,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});
		
		compatibility.put(new Point(4,0), new Integer[]{1,2,5,6,7,8,9,10,15,20});
		compatibility.put(new Point(4,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(4,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(4,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(5,0), new Integer[]{3,11,16,18,21});
		compatibility.put(new Point(5,1), new Integer[]{1,3,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(5,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(5,3), new Integer[]{1,3,6,7,8,9,10,19});

		compatibility.put(new Point(6,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(6,1), new Integer[]{2,4,8,9,10,20,21});
		compatibility.put(new Point(6,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(6,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(7,0), new Integer[]{3,11,16,18,21});
		compatibility.put(new Point(7,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(7,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(7,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(8,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(8,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(8,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(8,3), new Integer[]{1,3,6,7,8,9,10,20,21});

		compatibility.put(new Point(9,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(9,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(9,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(9,3), new Integer[]{1,3,6,7,8,9,10,20,21});

		compatibility.put(new Point(10,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(10,1), new Integer[]{2,4,5,8,9,10,20,21});
		compatibility.put(new Point(10,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(10,3), new Integer[]{1,3,6,7,8,9,10,20,21});

		compatibility.put(new Point(11,0), new Integer[]{3,11,16,21});
		compatibility.put(new Point(11,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(11,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(11,3), new Integer[]{2,4,5,11,12,13,14,17,18,20,21});

		compatibility.put(new Point(12,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(12,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(12,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(12,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(13,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(13,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(13,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(13,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(14,0), new Integer[]{3,11,16,21});
		compatibility.put(new Point(14,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(14,2), new Integer[]{1,2,3,4,8,9,10,15,16,17,18,19,21});
		compatibility.put(new Point(14,3), new Integer[]{2,4,5,11,12,13,14,17,18,20,21});

		compatibility.put(new Point(15,0), new Integer[]{1,2,5,6,7,8,9,10,20});
		compatibility.put(new Point(15,1), new Integer[]{16});
		compatibility.put(new Point(15,2), new Integer[]{1,2,3,4,8,9,10,17,18,19,21});
		compatibility.put(new Point(15,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(16,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,20});
		compatibility.put(new Point(16,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(16,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(16,3), new Integer[]{15});

		compatibility.put(new Point(17,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(17,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(17,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(17,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(18,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(18,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(18,2), new Integer[]{5,7,11,14});
		compatibility.put(new Point(18,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});
	
		compatibility.put(new Point(19,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15});
		compatibility.put(new Point(19,1), new Integer[]{2,4,5,8,9,10,21});
		compatibility.put(new Point(19,2), new Integer[]{6,12,13,20});
		compatibility.put(new Point(19,3), new Integer[]{2,4,5,11,12,13,14,16,17,18,20,21});

		compatibility.put(new Point(20,0), new Integer[]{4,12,17,19});
		compatibility.put(new Point(20,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18});
		compatibility.put(new Point(20,2), new Integer[]{1,2,3,4,8,9,10,15,16,21});
		compatibility.put(new Point(20,3), new Integer[]{1,3,6,7,8,9,10,19});
	
		compatibility.put(new Point(21,0), new Integer[]{1,2,5,6,7,8,9,10,13,14,15,20});
		compatibility.put(new Point(21,1), new Integer[]{1,3,6,7,11,12,13,14,15,17,18,19});
		compatibility.put(new Point(21,2), new Integer[]{5,11,14});
		compatibility.put(new Point(21,3), new Integer[]{1,3,6,7,8,9,10,19});
		
		return compatibility;
	}
	public int[][] getInteractionsCompatibility(){
		return new int[][]{
			
		};
	}
	public String[] getInteractionTips(){
		return new String[]{
			"3 mobs",
			"3 mobs",
			"3 mobs",
			"3 mobs",
			"4 coins",
			"1 mob",
			"Arrival",
			"1 flower",
			"1 life",
			"16 coins"
		};
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
