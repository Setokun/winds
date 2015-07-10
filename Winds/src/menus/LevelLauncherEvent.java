package menus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import display.Game;
import display.Window;

public class LevelLauncherEvent implements MouseListener{

	private JarLevel jarLvl;
	
	public LevelLauncherEvent(JarLevel jarLvl) {
		this.jarLvl = jarLvl;
	}
	
	public void mouseClicked(MouseEvent e) {
		Window.resize(Profile.current.getScreenDimensions());
		AddonManager.loadJarLevel(jarLvl);
		Window.affect(new Game());
	}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
