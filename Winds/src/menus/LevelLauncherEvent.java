package menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import display.Game;
import display.Window;

public class LevelLauncherEvent extends MouseAdapter{

	private JarLevel jarLvl;
	private int numPage;
	
	/**
	 * constructor that specifies the targeted jar level and the page it is displayed
	 * @param jarLvl The targeted jar level
	 * @param numPage The page number where the event has been called 
	 */
	public LevelLauncherEvent(JarLevel jarLvl, int numPage) {
		this.jarLvl = jarLvl;
		this.numPage = numPage;
	}
	
	/**
	 * The actions done when clicking
	 */
	public void mouseClicked(MouseEvent e) {
		AddonManager.loadJarLevel(jarLvl);
		Window.affect(new Game(jarLvl.getLevel().getType(), numPage), Profile.current.getScreenDimensions());
	}

}
