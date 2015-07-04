package addon;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import display.Handler;
import annotation.wFiles;
import annotation.wTheme;


public class JarTheme {
	private ThemeBase theme;
	private Class<?> mainClass;
	private String music;
	private BufferedImage logo, background, interactions,
						  sprites64, sprites128, spritesBoss; 
	
	
	//region Constructors 
	public JarTheme(File jarFile){
		JarFile jf = null;
		try {
			jf = new JarFile(jarFile);
			String classFilePath = getClassFilePath(jf);
			
			if(classFilePath != null){
				URLClassLoader ucl = new URLClassLoader(new URL[]{
					new URL("jar:"+ jarFile.toURI().toURL() +"!/") });
				mainClass = Class.forName(classFilePath, true, ucl);
				theme = (ThemeBase) mainClass.newInstance();
				String packageName = mainClass.getPackage().toString().replace("package ", "").replace(".", "/");
				
				music		 = packageName +"/"+ mainClass.getDeclaredAnnotation(wFiles.class).music();
				logo		 = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).logo());
				background	 = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).background());
				interactions = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).interactions());
				sprites64	 = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).sprites64());
				sprites128	 = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).sprites128());
				spritesBoss  = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).spritesBoss());
				
			}
			jf.close();
		} catch (IllegalAccessException | InstantiationException |
				 ClassNotFoundException | IOException e) {
			System.out.println("Unable to open the JAR file named \""+ jarFile.getName() +"\"");
			e.printStackTrace();
			return;
		}
		    
	}
	//endregion
	
	//region Public methods
	public boolean isValid(){
		wTheme aTheme = mainClass.getDeclaredAnnotation(wTheme.class);		
		return aTheme != null && mainClass != null &&  music != null
			&& logo != null && background != null && interactions != null
			&& sprites64 != null && sprites128 != null;
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
			&& new ImageIcon(sprites64).getDescription().equals(new ImageIcon(j.sprites64).getDescription())
			&& new ImageIcon(sprites128).getDescription().equals(new ImageIcon(j.sprites128).getDescription());
	}
	public String toString(){
		return "JarTheme [mainClass: \""+ mainClass.getName()
						+"\", "+ wThemeToString()
						+", "+ wFilesToString() +"]";
	}
	public void loadInteractions(int x, int y, int id, Handler handler){
		theme.loadInteractions(x, y, id, handler);
	}
	//endregion
	
	//region Private methods 
	/*OK*/private String getClassFilePath(JarFile jar){
		String path = null;
		Enumeration<JarEntry> entries = jar.entries();
		while(entries.hasMoreElements()){
			String name = entries.nextElement().getName();
			if( name.endsWith(".class") && !name.contains("$")){
				path = name.replace("/", ".").replace(".class", "");
				break;
			}
		}
		return path;
	}
	/*OK*/private BufferedImage getBufferedImage(JarFile jar, String packageName, String path) throws IOException {
		InputStream is = jar.getInputStream( jar.getEntry(
			packageName == null ? path : packageName +"/"+ path) );
		BufferedImage img = ImageIO.read(is);
		is.close();
		return img;
	}
	//endregion
	
	//region Annotation getters - OK 
	public String wThemeToString(){
		return "wTheme [idDB: \""+ getIdDB()
			  +"\", creator: \""+ getCreator()
			  +"\", date: \""+ getDate()
			  +"\", name: \""+ getName()
			  +"\", description: \""+ getDescription() +"\"]";
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
	
	public String wFilesToString(){
		return "wFiles [music: \""+ getMusicPath()
				+"\", logo: \""+ getLogoPath()
				+"\", background: \""+ getBackgroundPath()
				+"\", interactions: \""+ getInteractionsPath()
				+"\", sprites64: \""+ getSprites64Path()
				+"\", sprites128: \""+ getSprites128Path()
				+"\", spritesBoss: \""+ getSpritesBossPath()
				+"\"]";
	}
	public String getMusicPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).music();
	}
	public String getLogoPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).logo();
	}
	public String getBackgroundPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).background();
	}
	public String getInteractionsPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).interactions();
	}
	public String getSprites64Path(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).sprites64();
	}
	public String getSprites128Path(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).sprites128();
	}
	public String getSpritesBossPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).spritesBoss();
	}
	//endregion
	
	//region Class getters - OK 
	public Class<?> getMainClass(){
		return mainClass;
	}
	public Map<Point, Integer[]> getSpritesCompatibility(){
		return theme.spriteCompatibility;
	}
	public int[][] getInteractionsCompatibility(){
		return theme.interactionsCompatibility;
	}
	public int[][][] getCollisions(){
		return theme.collisionsList;
	}
	public String[] getInteractionTips(){
		return theme.interactionTips;
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
	public BufferedImage getSprites64() {
		return sprites64;
	}
	public BufferedImage getSprites128() {
		return sprites128;
	}
	public BufferedImage getSpritesBoss() {
		return spritesBoss;
	}
	//endregion
	
}
