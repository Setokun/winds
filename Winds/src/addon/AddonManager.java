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

import addon.level.Type;


public class AddonManager {
	private static ArrayList<JarTheme> jarThemesList;
	private static ArrayList<JarLevel> jarLevelsList;
	
	private static JarTheme loadedJarTheme;
	private static JarLevel loadedJarLevel;

	static {
		jarThemesList = new ArrayList<JarTheme>();
		jarLevelsList = new ArrayList<JarLevel>();
		collectJarThemes();
		collectJarLevels();
	}
	

	/*OK*/private static void collectJarThemes(){
		File folder = new File("bin/resources/themes");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
		    if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarTheme theme = validateJarTheme(items[i]);
		    	if(theme != null){ jarThemesList.add(theme); }
		    }
		}
	}
	/*to finish - music*/private static JarTheme validateJarTheme(File jarFile){
		JarTheme current = new JarTheme();
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
						current.setMainClass(themeClass);
					} catch (ClassNotFoundException e) { e.printStackTrace(); }
				    
				}else {
					URL entryURL = new URL("jar:" + jarFile.toURI().toURL() + "!/"+ entry);
					URL fileURL = entryURL.openConnection().getURL();
					
					if( entry.endsWith(".mp3") ){
						current.setMusic("music");
					}else {
						BufferedImage icon = ImageIO.read(fileURL);
						
						if( entry.endsWith("logo.png") ){
							current.setLogo(icon);
						}else
						if( entry.endsWith("background.jpg") ){
							current.setBackground(icon);
						}else
						if( entry.endsWith("interactions.png") ){
							current.setInteractions(icon);
						}else
						if( entry.endsWith("spritesheet/128.png") ){
							current.setSprites128(icon);
						}else
						if( entry.endsWith("spritesheet/64.png") ){
							current.setSprites64(icon);
						}else
						if( entry.endsWith("spritesheet/32.png") ){
							current.setSprites32(icon);
						}
					}
				}
			}
			jar.close();
		} catch (IOException e) {
			System.out.println("Unable to open the JAR file named \""+ jarFile.getName() +"\"");
		}
		return current.isValid() ? current : null;
	}
	/*OK*/public static void addJarTheme(File jarFile){
		JarTheme current = validateJarTheme(jarFile);
		if(current != null && !jarThemesList.contains(current)){ jarThemesList.add(current); }
	}
	/*OK*/public static JarTheme getJarThemeByID(int idTheme){
		for (JarTheme jt : jarThemesList) {
			if(jt.getIdDB() == idTheme){ return jt; }
		}
		return null;
	}
	
	/*OK*/private static void collectJarLevels(){
		File folder = new File("bin/resources/levels");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
			if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarLevel level = validateJarLevel(items[i]);
			    if(level != null){ jarLevelsList.add(level); }
			}
		}
	}
	/*OK*/private static JarLevel validateJarLevel(File jarFile){
		JarLevel current = new JarLevel(jarFile);
		return current.isValid() ? current : null;
	}
	/*OK*/public static void addJarLevel(File jarFile){
		JarLevel current = validateJarLevel(jarFile);
		if(current != null && !jarLevelsList.contains(current)){ jarLevelsList.add(current); }
	}
	/*OK*/public static JarLevel[] getJarLevelsByType(Type levelType){
		ArrayList<JarLevel> list = new ArrayList<JarLevel>();
		for (JarLevel lvl : jarLevelsList) {
			if (lvl.getLevel().getType().equals(levelType)) { list.add(lvl); }
		}

		JarLevel[] jarLevels = new JarLevel[ list.size() ];
		return list.toArray(jarLevels);
	}
	
	
	//region Setters 
	public static void loadJarTheme(int index){
		for (int i = 0; i < jarThemesList.size(); i++) {
			if(jarThemesList.get(i).getIdDB() == index)
				loadedJarTheme = jarThemesList.get(i);
		}
		//loadedTheme = themesList.get(index);
	}
	public static void loadJarTheme(JarTheme jar){
		loadedJarTheme = jar;
	}
	public static void loadJarLevel(int index){
		for (int i = 0; i < jarLevelsList.size(); i++) {
			if(jarLevelsList.get(i).getLevel().getIdDB() == index)
				loadedJarLevel = jarLevelsList.get(i);
		}
		//loadedJarLevel = jarLevelsList.get(index);
	}
	public static void loadJarLevel(JarLevel jar){
		loadedJarLevel = jar;
	}
	//endregion
	
	//region Getters 
	public static JarTheme[] getJarThemes(){
		JarTheme[] jarThemes = new JarTheme[ jarThemesList.size() ];
		return jarThemesList.toArray(jarThemes);
	}
	public static JarLevel[] getJarLevels(){
		JarLevel[] jarLevels = new JarLevel[ jarLevelsList.size() ];
		return jarLevelsList.toArray(jarLevels);
	}
	public static JarTheme getLoadedJarTheme() {
		return loadedJarTheme;
	}
	public static JarLevel getLoadedJarLevel() {
		return loadedJarLevel;
	}
	public static int[] getThemesInstalledIds(){
		int nbThemes = jarThemesList.size();
		int[] ids = new int[nbThemes];
		for (int i = 0; i < nbThemes; i++) {
			ids[i] = jarThemesList.get(i).getIdDB();
		}
		return ids;
	}
	public static int[] getLevelsInstalledIds(Type type){
		JarLevel[] levels = getJarLevelsByType(type);
		int nbLevels = levels.length;
		int[] ids = new int[nbLevels];
		for (int i = 0; i < nbLevels; i++) {
			ids[i] = levels[i].getLevel().getIdDB();
		}
		return ids;
	}
	//endregion
	
}
