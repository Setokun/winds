package leveleditor;

import addon.AddonManager;
import addon.JarLevel;
import addon.JarTheme;
import core.SpriteSheet;


public class EditorController {
	static JarTheme themeUsed;
	static JarLevel levelUsed;
	
	static EditorGUI gui;
	static Tile[] tiles64;
	static Tile[] tiles32;
	
	
	public EditorController(){
		// pour les tests
		AddonManager.loadTheme(1);
		AddonManager.loadLevel(0);
		// --------------
		
		themeUsed = AddonManager.getLoadedTheme();
		SpriteSheet spritesheet64 = new SpriteSheet(themeUsed.getSprites64(), 64);
		
		levelUsed = AddonManager.getLoadedLevel();		
		int[][] matrix = levelUsed.getMatrix();
		
		gui = new EditorGUI();
		gui.setSprites(spritesheet64.getSprites());
		gui.setMatrix(matrix);
		gui.setVisible(true);
		
		//int[][] result = gui.extractMatrix();
		//System.out.println(result[0][1]);
	}
	
}
