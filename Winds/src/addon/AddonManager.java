package addon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.ImageIcon;


public class AddonManager {
	private static ArrayList<JarTheme> themes = new ArrayList<JarTheme>();
	private static ArrayList<JarLevel> levels = new ArrayList<JarLevel>();
	
	private static JarTheme loadedTheme;
	private static JarLevel loadedLevel;


	/*OK*/public static void getThemes(){
		File folder = new File("bin/resources/themes");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
		    if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarTheme theme = themeValidate(items[i]);
		    	if(theme != null){ themes.add(theme); }
		    }
		}
	}
	/*to finish - music*/public static JarTheme themeValidate(File jarFile){
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
					}else
					if( entry.endsWith("logo.png") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setLogo(icon);
					}else
					if( entry.endsWith("background.jpg") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setBackground(icon);
					}else
					if( entry.endsWith("interactions.png") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setInteractions(icon);
					}else
					if( entry.endsWith("spritesheet/128.png") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setSprites128(icon);
					}else
					if( entry.endsWith("spritesheet/64.png") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setSprites64(icon);
					}else
					if( entry.endsWith("spritesheet/32.png") ){
						ImageIcon icon = new ImageIcon(fileURL);
						currentTheme.setSprites32(icon);
					}
				}
			}
			jar.close();
		} catch (IOException e) {
			System.out.println("Unable to open the JAR file named \""+ jarFile.getName() +"\"");
		}
		return currentTheme.isValid() ? currentTheme : null;
	}

	public static void getLevels(){
		File folder = new File("bin/resources/levels");
		File[] items = folder.listFiles();
		
		for (int i = 0; i < items.length; i++){
		   if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
			   JarLevel level = levelValidate(items[i]);
			   if(level != null){ levels.add(level); }
		   }
		}
	}
	/*OK*/public static JarLevel levelValidate(File jarFile){
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
	
}
