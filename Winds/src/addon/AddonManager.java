package addon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class AddonManager {
	private static ArrayList<JarTheme> themesList;
	private static ArrayList<JarLevel> levelsList;
	
	private static JarTheme loadedTheme;
	private static JarLevel loadedLevel;

	static {
		themesList = new ArrayList<JarTheme>();
		levelsList = new ArrayList<JarLevel>();
		collectThemes();
		collectLevels();
	}
	

	/*OK*/private static void collectThemes(){
		File folder = new File("bin/resources/themes");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
		    if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarTheme theme = themeValidate(items[i]);
		    	if(theme != null){ themesList.add(theme); }
		    }
		}
	}
	/*to finish - music*/private static JarTheme themeValidate(File jarFile){
		JarTheme currentTheme = new JarTheme();
		try {
			JarFile jar = new JarFile(jarFile);
			Enumeration<JarEntry> jarEntries = jar.entries();
			while(jarEntries.hasMoreElements()){
				String entry = jarEntries.nextElement().getName();
				
				if( entry.endsWith(".class") ){
					String className = entry.replace(".class", "").replace('/', '.');
					URL[] urls = { new URL("jar:" + jarFile.toURI().toURL() + "!/") };
				    URLClassLoader ucl = new URLClassLoader(urls);
				    try {
						Class<?> themeClass = Class.forName(className, true, ucl);
						currentTheme.setMainClass(themeClass);
					} catch (ClassNotFoundException e) { e.printStackTrace(); }
				    
				}else {
					URL entryURL = new URL("jar:" + jarFile.toURI().toURL() + "!/"+ entry);
					URL fileURL = entryURL.openConnection().getURL();
					
					if( entry.endsWith(".mp3") ){
						currentTheme.setMusic("music");
					}else {
						BufferedImage icon = ImageIO.read(fileURL);
						
						if( entry.endsWith("logo.png") ){
							currentTheme.setLogo(icon);
						}else
						if( entry.endsWith("background.jpg") ){
							currentTheme.setBackground(icon);
						}else
						if( entry.endsWith("interactions.png") ){
							currentTheme.setInteractions(icon);
						}else
						if( entry.endsWith("spritesheet/128.png") ){
							currentTheme.setSprites128(icon);
						}else
						if( entry.endsWith("spritesheet/64.png") ){
							currentTheme.setSprites64(icon);
						}else
						if( entry.endsWith("spritesheet/32.png") ){
							currentTheme.setSprites32(icon);
						}
					}
				}
			}
			jar.close();
		} catch (IOException e) {
			System.out.println("Unable to open the JAR file named \""+ jarFile.getName() +"\"");
		}
		return currentTheme.isValid() ? currentTheme : null;
	}
	/*OK*/public static void addTheme(File jarTheme){
		JarTheme current = themeValidate(jarTheme);
		if(current != null && !themesList.contains(current)){ themesList.add(current); }
	}
	/*OK*/public static JarTheme getThemeByID(int idTheme){
		for (JarTheme theme : themesList) {
			if(theme.getIdDB() == idTheme){ return theme; }
		}
		return null;
	}
	
	/*OK*/private static void collectLevels(){
		File folder = new File("bin/resources/levels");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
			if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarLevel level = levelValidate(items[i]);
			    if(level != null){ levelsList.add(level); }
			}
		}
	}
	/*OK*/private static JarLevel levelValidate(File jarFile){
		JarLevel currentLevel = new JarLevel();
		try {
			JarFile jar = new JarFile(jarFile);
			Enumeration<JarEntry> jarEntries = jar.entries();
			while(jarEntries.hasMoreElements()){
				String entry = jarEntries.nextElement().getName();
				
				if( entry.endsWith(".class") ){
					String className = entry.replace(".class", "").replace('/', '.');
					URL[] urls = { new URL("jar:" + jarFile.toURI().toURL() + "!/") };
				    URLClassLoader ucl = new URLClassLoader(urls);
				    try {
						Class<?> levelClass = Class.forName(className, true, ucl);
						currentLevel.setMainClass(levelClass);
					} catch (ClassNotFoundException e) { e.printStackTrace(); }
				}
			}
			jar.close();
		} catch (IOException e) {
			System.out.println("Unable to open the JAR file named \""+ jarFile.getName() +"\"");
		}
		return currentLevel.isValid() ? currentLevel : null;
	}
	/*OK*/public static void addLevel(File jarLevel){
		JarLevel current = levelValidate(jarLevel);
		if(current != null && !levelsList.contains(current)){ levelsList.add(current); }
	}
	/*OK*/public static JarLevel[] getLevelsByType(String wLevelType){
		ArrayList<JarLevel> levelsList = new ArrayList<JarLevel>();
		for (JarLevel lvl : levelsList) {
			if (lvl.getType() == wLevelType) { levelsList.add(lvl); }
		}
		
		JarLevel[] jarLevels = new JarLevel[ levelsList.size() ];
		return levelsList.toArray(jarLevels);
	}
	
	
	//region Setters 
	public static void loadTheme(int index){
		loadedTheme = themesList.get(index);
	}
	public static void loadTheme(JarTheme theme){
		loadedTheme = theme;
	}
	public static void loadLevel(int index){
		loadedLevel = levelsList.get(index);
	}
	public static void loadLevel(JarLevel level){
		loadedLevel = level;
	}
	//endregion
	//region Getters 
	public static JarTheme[] getThemes(){
		JarTheme[] jarThemes = new JarTheme[ themesList.size() ];
		return themesList.toArray(jarThemes);
	}
	public static JarLevel[] getLevels(){
		JarLevel[] jarLevels = new JarLevel[ levelsList.size() ];
		return levelsList.toArray(jarLevels);
	}
	public static JarTheme getLoadedTheme() {
		return loadedTheme;
	}
	public static JarLevel getLoadedLevel() {
		return loadedLevel;
	}
	//endregion
	
}
