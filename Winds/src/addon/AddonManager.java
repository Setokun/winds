package addon;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import addon.level.LevelType;


/**
 * Class used to manage theme and level archives.
 */
public class AddonManager {
	public static String currentPath;
	private static ArrayList<JarTheme> jarThemesList;
	private static ArrayList<JarLevel> jarLevelsList;
	private static JarTheme loadedJarTheme;
	private static JarLevel loadedJarLevel;
	private static String themesPath, levelsPath;

	
	//region Theme methods 
	/**
	 * Collects the valid theme archives.
	 */
	private static void collectJarThemes(){
		File folder = new File(themesPath);
		if( !folder.exists() ) folder.mkdir();
		
		File[] items = folder.listFiles();
		if(items != null)
		for (int i = 0; i < items.length; i++)
		    if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarTheme theme = validateJarTheme(items[i]);
		    	if(theme != null){ 
		    		theme.setJarFilePath(items[i].getAbsolutePath().replace("\\", "/"));
		    		jarThemesList.add(theme); 
		    	}
		    }
	}
	/**
	 * Checks the validity of the theme archive.
	 * @param jarFile File which represents the theme archive
	 * @return	JarTheme or null
	 */
	private static JarTheme validateJarTheme(File jarFile){
		JarTheme current = new JarTheme(jarFile);
		return current.isValid() ? current : null;
	}
	/**
	 * Add the specified theme archive to the themes list if valid.
	 * @param jarFile File which represents the theme archive
	 */
	public static void addJarTheme(File jarFile){
		JarTheme current = validateJarTheme(jarFile);
		if(current != null && !jarThemesList.contains(current)){
			current.setJarFilePath(jarFile.getAbsolutePath().replace("\\", "/"));
			jarThemesList.add(current);
		}
	}
	/**
	 * Get the theme which has the specified ID.
	 * @param idTheme the ID of the theme to find
	 * @return JarTheme or null
	 */
	public static JarTheme getJarThemeByID(int idTheme){
		for (JarTheme jt : jarThemesList)
			if(jt.getIdDB() == idTheme)
				return jt;
		return null;
	}
	//endregion
	
	//region Level methods 
	/**
	 * Collects the valid level archives.
	 */
	private static void collectJarLevels(){
		File folder = new File(levelsPath);
		if( !folder.exists() ) folder.mkdir();
		
		File[] items = folder.listFiles();
		if(items != null)
		for (int i = 0; i < items.length; i++)
			if (items[i].isFile() && items[i].getName().endsWith(".jar")) {
		    	JarLevel level = validateJarLevel(items[i]);
			    if(level != null)  jarLevelsList.add(level);
			}
	}
	/**
	 * Checks the validity of the level archive.
	 * @param jarFile File which represents the level archive
	 * @return	JarLevel or null
	 */
	private static JarLevel validateJarLevel(File jarFile){
		JarLevel current = new JarLevel(jarFile);		
		return current.isValid() ? current : null;
	}
	/**
	 * Add the specified level archive to the levels list if valid.
	 * @param jarFile File which represents the level archive
	 */
	public static void addJarLevel(File jarFile){
		JarLevel current = validateJarLevel(jarFile);
		if(current != null && !jarLevelsList.contains(current))
			jarLevelsList.add(current);
	}
	/**
	 * Removes the specified level archive from the levels list<br>
	 * and returns the success statement.
	 * @param jar JarLevel which must be removed
	 * @return boolean
	 */
	public static boolean removeJarLevel(JarLevel jar){
		return jarLevelsList.remove(jar);
	}
	/**
	 * Removes the level archive which has the specified ID from the levels list<br>
	 * and returns the success statement.
	 * @param idLevel the ID of the level archive to remove
	 * @return boolean
	 */
	public static boolean removeJarLevelById(int idLevel){
		for (int i = 0; i < jarLevelsList.size(); i++)
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
		return false;
	}
	/**
	 * Get the level archives which have the specified type.
	 * @param levelType the type of the level archives to find
	 * @return JarLevel[]
	 */
	public static JarLevel[] getJarLevelsByType(LevelType levelType){
		ArrayList<JarLevel> list = new ArrayList<JarLevel>();
		for (JarLevel lvl : jarLevelsList)
			if (lvl.getLevel().getType().equals(levelType))
				list.add(lvl);

		JarLevel[] jarLevels = new JarLevel[ list.size() ];
		return list.toArray(jarLevels);
	}
	//endregion
	
	//region Getters and Setters 
	/**
	 * Get the full path of the themes folder.
	 * @return String
	 */
	public static String getThemesPath(){
		return themesPath;
	}
	/**
	 * Get the full path of the levels folder.
	 * @return String
	 */
	public static String getLevelsPath(){
		return levelsPath;
	}

	/**
	 * Get all theme archives.
	 * @return JarTheme[]
	 */
	public static JarTheme[] getJarThemes(){
		JarTheme[] jarThemes = new JarTheme[ jarThemesList.size() ];
		return jarThemesList.toArray(jarThemes);
	}
	/**
	 * Get all level archives.
	 * @return JarTheme[]
	 */
	public static JarLevel[] getJarLevels(){
		JarLevel[] jarLevels = new JarLevel[ jarLevelsList.size() ];
		return jarLevelsList.toArray(jarLevels);
	}
	
	/**
	 * Set the theme which has the specified ID as current loaded theme.
	 * @param idTheme the theme's ID
	 */
	public static void loadJarTheme(int idTheme){
		for (int i = 0; i < jarThemesList.size(); i++) {
			if(jarThemesList.get(i).getIdDB() == idTheme){
				loadedJarTheme = jarThemesList.get(i);
				break;
			}
		}
	}
	/**
	 * Set the specified theme as current loaded theme.
	 * @param jar the theme archive to load
	 */
	public static void loadJarTheme(JarTheme jar){
		loadedJarTheme = jar;
	}
	/**
	 * Set the level which has the specified ID as current loaded level.
	 * @param idLevel
	 */
	public static void loadJarLevel(int idLevel){
		for (int i = 0; i < jarLevelsList.size(); i++) {
			if(jarLevelsList.get(i).getLevel().getIdDB() == idLevel)
				loadedJarLevel = jarLevelsList.get(i);
		}
	}
	/**
	 * Set the specified level as current loaded level.
	 * @param jar the level archive to load
	 */
	public static void loadJarLevel(JarLevel jar){
		loadedJarLevel = jar;
	}
	
	/**
	 * Get the current loaded theme archive.
	 * @return JarTheme
	 */
	public static JarTheme getLoadedJarTheme() {
		return loadedJarTheme;
	}
	/**
	 * Get the current loaded level archive.
	 * @return JarTheme
	 */
	public static JarLevel getLoadedJarLevel() {
		return loadedJarLevel;
	}

	/**
	 * Get the IDs of the installed themes.
	 * @return int[]
	 */
	public static int[] getThemesInstalledIds(){
		int nbThemes = jarThemesList.size();
		int[] ids = new int[nbThemes];
		for (int i = 0; i < nbThemes; i++) {
			ids[i] = jarThemesList.get(i).getIdDB();
		}
		return ids;
	}
	/**
	 * Get the IDs of the installed levels which have the specified type.
	 * @param type the type of the level archives to find
	 * @return int[]
	 */
	public static int[] getLevelsInstalledIds(LevelType type){
		JarLevel[] levels = getJarLevelsByType(type);
		int nbLevels = levels.length;
		int[] ids = new int[nbLevels];
		for (int i = 0; i < nbLevels; i++)
			ids[i] = levels[i].getLevel().getIdDB();
		return ids;

	}
	//endregion
	
	/**
	 * Initializes the lists of valid available themes and levels.<br>
	 * This method must be called only by the splash screen.
	 */
	public static void init(){
		// paths initialization
		try {
			currentPath = URLDecoder.decode(JarLevel.class.getProtectionDomain()
							.getCodeSource().getLocation().getFile()
							.replace("Winds.jar", "").substring(1), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "UTF-8 encoding isn't supported, please check your JAVA version");
		}
		themesPath = currentPath +"resources/themes/";
		levelsPath = currentPath +"resources/levels/";
		
		File folder = new File(currentPath +"resources/");
		if( !folder.exists() ) folder.mkdir();
		
		// lists initialization
		jarThemesList = new ArrayList<JarTheme>();
		jarLevelsList = new ArrayList<JarLevel>();
		collectJarThemes();
		collectJarLevels();
	}
	
}
