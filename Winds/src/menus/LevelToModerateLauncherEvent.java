package menus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import addon.level.LevelType;
import display.Game;
import display.Window;

public class LevelToModerateLauncherEvent implements MouseListener{

	private JarLevel jarLvl;
	private int numPage;
	
	/**
	 * constructor that specifies the targeted jar level and the page it is displayed
	 * @param jarLvl The targeted jar level
	 * @param numPage The page number where the event has been called 
	 */
	public LevelToModerateLauncherEvent(JarLevel jarLvl, int numPage) {
		this.jarLvl = jarLvl;
		this.numPage = numPage;
	}
	
	/**
	 * The actions done when clicking
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			if(JOptionPane.showConfirmDialog(null, "Do you want to delete this level ?") == JOptionPane.YES_OPTION){
				AddonManager.removeJarLevelById(jarLvl.getLevel().getIdDB());
				Window.affect(new LevelSelector(LevelType.tomoderate, numPage), Window.DIM_STANDARD);
			}
		}
		else{
			AddonManager.loadJarLevel(jarLvl);
			Window.affect(new Game(jarLvl.getLevel().getType(), numPage),Profile.current.getScreenDimensions());
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
