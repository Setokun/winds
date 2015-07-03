package addon;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import addon.level.Type;


public class AddonManager {
	private static ArrayList<JarTheme> jarThemesList;
	private static ArrayList<JarLevel> jarLevelsList;
	
	private static JarTheme loadedJarTheme;
	private static JarLevel loadedJarLevel;
	
	private static String themesPath, levelsPath, commonPath;

	static {
		// paths initialisation
		String currentPath = JarLevel.class.getResource("").getPath().replace("%20", " ");
		themesPath = currentPath.replace("addon/", "resources/themes/");
		levelsPath = currentPath.replace("addon/", "resources/levels/");
		commonPath = currentPath.replace("addon/", "resources/common/");
		
		// lists initialisation
		jarThemesList = new ArrayList<JarTheme>();
		jarLevelsList = new ArrayList<JarLevel>();
		collectJarThemes();
		collectJarLevels();
	}
	
	//region Theme methods 
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
	/*OK*/private static JarTheme validateJarTheme(File jarFile){
		JarTheme current = new JarTheme(jarFile);
		return current.isValid() ? current : null;
	}
	/*OK*/public static void addJarTheme(File jarFile){
		JarTheme current = validateJarTheme(jarFile);
		if(current != null && !jarThemesList.contains(current))
			jarThemesList.add(current);
	}
	/*OK*/public static JarTheme getJarThemeByID(int idTheme){
		for (JarTheme jt : jarThemesList)
			if(jt.getIdDB() == idTheme)
				return jt;
		return null;
	}
	//endregion
	
	//region Level methods 
	/*OK*/private static void collectJarLevels(){
		File folder = new File("bin/resources/levels");
		File[] items = folder.listFiles();
				
		for (int i = 0; i < items.length; i++)
			if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarLevel level = validateJarLevel(items[i]);
			    if(level != null)  jarLevelsList.add(level);
			}
	}
	/*OK*/private static JarLevel validateJarLevel(File jarFile){
		JarLevel current = new JarLevel(jarFile);		
		return current.isValid() ? current : null;
	}
	/*OK*/public static void addJarLevel(File jarFile){
		JarLevel current = validateJarLevel(jarFile);
		if(current != null && !jarLevelsList.contains(current))
			jarLevelsList.add(current);
	}
	/*OK*/public static boolean removeJarLevel(JarLevel jar){
		return jarLevelsList.remove(jar);
	}
	/*OK*/public static boolean removeJarLevelById(int idLevel){
		for (int i = 0; i < jarLevelsList.size(); i++) {
			if(jarLevelsList.get(i).getLevel().getIdDB() == idLevel){
				JarLevel jar = jarLevelsList.get(i);
				jarLevelsList.remove(jar);
				if( !jar.getFile().delete() ){
					JOptionPane.showMessageDialog(null,
						"Unable to remove the file of this level.",
						"Deletion failed", JOptionPane.WARNING_MESSAGE);
					return false;
				}
				return true;
			}
		}
		return false;
	}
	/*OK*/public static JarLevel[] getJarLevelsByType(Type levelType){
		ArrayList<JarLevel> list = new ArrayList<JarLevel>();
		for (JarLevel lvl : jarLevelsList)
			if (lvl.getLevel().getType().equals(levelType))
				list.add(lvl);

		JarLevel[] jarLevels = new JarLevel[ list.size() ];
		return list.toArray(jarLevels);
	}
	//endregion
	
	//region Getters and Setters 
	public static String getThemesPath(){
		return themesPath;
	}
	public static String getLevelsPath(){
		return levelsPath;
	}

	public static String getCommonPath(){
		return commonPath;
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


	public static void loadJarTheme(int index){
		for (int i = 0; i < jarThemesList.size(); i++) {
			if(jarThemesList.get(i).getIdDB() == index)
				loadedJarTheme = jarThemesList.get(i);
		}
	}
	public static void loadJarTheme(JarTheme jar){
		loadedJarTheme = jar;
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
