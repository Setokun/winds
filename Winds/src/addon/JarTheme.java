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

import annotation.wFiles;
import annotation.wTheme;
import display.Handler;


/**
 * Class which represents a theme archive.
 */
public class JarTheme {
	private ThemeBase theme;
	private String JarFilePath;
	private Class<?> mainClass;
	private String music;
	private BufferedImage logo, background, interactions,
						  sprites64, sprites128, spritesCollectable; 
	
	
	//region Constructors 
	/**
	 * Instantiates a new JarTheme from the specified theme file.
	 * @param jarFile File which represents a theme
	 */
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
				
				music		 		= packageName +"/"+ mainClass.getDeclaredAnnotation(wFiles.class).music();
				logo		 		= getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).logo());
				background	 		= getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).background());
				interactions 		= getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).interactions());
				sprites64	 		= getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).sprites64());
				sprites128	 		= getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).sprites128());
				spritesCollectable  = getBufferedImage(jf, packageName, mainClass.getDeclaredAnnotation(wFiles.class).spritesCollectable());
				
			}
			jf.close();
		} catch (IllegalAccessException | InstantiationException |
				 ClassNotFoundException | IOException e) {
			jarFile.delete();
			return;
		}
		    
	}
	//endregion
	
	//region Public methods 
	/**
	 * Checks if the current JarTheme is valid.
	 * @return boolean
	 */
	public boolean isValid(){
		wTheme aTheme = mainClass.getDeclaredAnnotation(wTheme.class);		
		return aTheme != null && mainClass != null &&  music != null
			&& logo != null && background != null && interactions != null
			&& sprites64 != null && sprites128 != null;
	}
	/**
	 * Checks if the specified object is equals to the current JarTheme.
	 * @param obj The object to compare with
	 * @return boolean
	 */
	public boolean equals(Object obj){
		if( !(obj instanceof JarTheme) ){ return false; }
		
		JarTheme j = (JarTheme) obj;
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
	/**
	 * Get the string representation of this object.
	 * @return String
	 */
	public String toString(){
		return "JarTheme [mainClass: \""+ mainClass.getName()
			+"\", "+ wThemeToString() +", "+ wFilesToString() +"]";
	}
	/**
	 * Loads the theme's interactions into the handler.
	 * @param handler
	 */
	public void loadInteractions(Handler handler){
		int[][] elements = AddonManager.getLoadedJarLevel().getLevel().getInteractions();
		for(int i = 0; i < 60; i++)
			for(int j = 0; j < 60; j++){
				int id = elements[i][j];
				if (id == 0)  continue;
				theme.loadInteractions(j*128, i*128, id, handler);
			}
	}
	/**
	 * Loads the theme's objects on foreground.
	 * @param handler
	 */
	public void loadFront(Handler handler){
		theme.loadFront(handler);
	}
	//endregion
	
	//region Private methods 
	/**
	 * Returns the path of the first class found into the specified archive.
	 * Usually, this class is the theme's root class
	 * @param jar The archive where find the path
	 * @return String
	 */
	private String getClassFilePath(JarFile jar){
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
	/**
	 * Returns the image of the specified file contained into the specified archive.
	 * @param jar The archive which contains the file
	 * @param packageName The file's package
	 * @param path The wanted file's path
	 * @return BufferedImage
	 * @throws IOException
	 */
	private BufferedImage getBufferedImage(JarFile jar, String packageName, String path) throws IOException {
		InputStream is = jar.getInputStream( jar.getEntry(
			packageName == null ? path : packageName +"/"+ path) );
		BufferedImage img = ImageIO.read(is);
		is.close();
		return img;
	}
	//endregion
	
	//region Annotation getters 
	/**
	 * Get the string representation of the wTheme annotation.
	 * @return String
	 */
	public String wThemeToString(){
		return "wTheme [idDB: \""+ getIdDB()
			  +"\", creator: \""+ getCreator()
			  +"\", date: \""+ getDate()
			  +"\", name: \""+ getName()
			  +"\", description: \""+ getDescription() +"\"]";
	}
	/**
	 * Get the ID into the database.
	 * @return int 
	 */
	public int getIdDB(){
		return mainClass.getDeclaredAnnotation(wTheme.class).idDB();
	}
	/**
	 * Get the pseudonym of the creator who has made the theme.
	 * @return String
	 */
	public String getCreator(){
		return mainClass.getDeclaredAnnotation(wTheme.class).creator();
	}
	/**
	 * Get the creation date of this theme.
	 * @return String
	 */
	public String getDate(){
		return mainClass.getDeclaredAnnotation(wTheme.class).date();
	}
	/**
	 * Get the name of this theme.
	 * @return String
	 */
	public String getName(){
		return mainClass.getDeclaredAnnotation(wTheme.class).name();
	}
	/**
	 * Get the description of this theme.
	 * @return String
	 */
	public String getDescription(){
		return mainClass.getDeclaredAnnotation(wTheme.class).description();
	}
	
	/**
	 * Get the string representation of the wFiles annotation.
	 * @return String
	 */
	public String wFilesToString(){
		return "wFiles [music: \""+ getMusicPath()
				+"\", logo: \""+ getLogoPath()
				+"\", background: \""+ getBackgroundPath()
				+"\", interactions: \""+ getInteractionsPath()
				+"\", sprites64: \""+ getSprites64Path()
				+"\", sprites128: \""+ getSprites128Path()
				+"\", spritesCollectable: \""+ getSpritesCollectablePath()
				+"\"]";
	}
	/**
	 * Get the path of the theme's music file.
	 * @return String
	 */
	public String getMusicPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).music();
	}
	/**
	 * Get the path of the theme's logo file.
	 * @return String
	 */
	public String getLogoPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).logo();
	}
	/**
	 * Get the path of the theme's background image file.
	 * @return String
	 */
	public String getBackgroundPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).background();
	}
	/**
	 * Get the path of the theme's interactions file.
	 * @return String
	 */
	public String getInteractionsPath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).interactions();
	}
	/**
	 * Get the path of the theme's 64x64 sprites file.
	 * @return String
	 */
	public String getSprites64Path(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).sprites64();
	}
	/**
	 * Get the path of the theme's 128x128 sprites file.
	 * @return String
	 */
	public String getSprites128Path(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).sprites128();
	}
	/**
	 * Get the path of the theme's collectable sprites file.
	 * @return String
	 */
	public String getSpritesCollectablePath(){
		return mainClass.getPackage().getName() +"/"
			 + mainClass.getDeclaredAnnotation(wFiles.class).spritesCollectable();
	}
	//endregion
	
	//region Class getters 
	/**
	 * Get the theme's root class.
	 * @return Class
	 */
	public Class<?> getMainClass(){
		return mainClass;
	}
	/**
	 * Get the theme's path.
	 * @return Class
	 */
	public String getJarFilePath(){
		return JarFilePath;
	}
	/**
	 * Get the theme's sprites compatibility.
	 * @return Map<Point, Integer[]>
	 */
	public Map<Point, Integer[]> getSpritesCompatibility(){
		return theme.spriteCompatibility;
	}
	/**
	 * Get the theme's interactions compatibility.
	 * @return int[][]
	 */
	public int[][] getInteractionsCompatibility(){
		return theme.interactionsCompatibility;
	}
	/**
	 * Get the theme's collisions list.
	 * @return int[][][]
	 */
	public int[][][] getCollisions(){
		return theme.collisionsList;
	}
	/**
	 * Get the theme's interaction tip texts.
	 * @return String[]
	 */
	public String[] getInteractionTips(){
		return theme.interactionTips;
	}
	//endregion
	
	//region Member getters 
	/**
	 * Get the theme's music string.
	 * @return String
	 */
	public String getMusic() {
		return music;
	}
	/**
	 * Get the theme's logo image.
	 * @return BufferedImage
	 */
	public BufferedImage getLogo(){
		return logo;
	}
	/**
	 * Get the theme's background image.
	 * @return BufferedImage
	 */
	public BufferedImage getBackground() {
		return background;
	}
	/**
	 * Get the theme's interactions image.
	 * @return BufferedImage
	 */
	public BufferedImage getInteractions() {
		return interactions;
	}
	/**
	 * Get the theme's 64x64 sprites image.
	 * @return BufferedImage
	 */
	public BufferedImage getSprites64() {
		return sprites64;
	}
	/**
	 * Get the theme's 128x128 sprites image.
	 * @return BufferedImage
	 */
	public BufferedImage getSprites128() {
		return sprites128;
	}
	/**
	 * Get the theme's collectable sprites image.
	 * @return BufferedImage
	 */
	public BufferedImage getSpritesCollectable() {
		return spritesCollectable;
	}
	//endregion
	
	//region Class setters 
	/**
	 * Set the path of this archive file.
	 * @param jarFilePath The path of the archive file
	 */
	public void setJarFilePath(String jarFilePath){
		this.JarFilePath = jarFilePath;
	}
	//endregion
	
}
