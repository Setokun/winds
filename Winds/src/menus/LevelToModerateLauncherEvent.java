package menus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import addon.level.Type;
import display.Game;
import display.Window;

public class LevelToModerateLauncherEvent implements MouseListener{

	private JarLevel jarLvl;
	
	public LevelToModerateLauncherEvent(JarLevel jarLvl) {
		this.jarLvl = jarLvl;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			if(JOptionPane.showConfirmDialog(null, "Do you want to delete this level ?") == JOptionPane.YES_OPTION){
				AddonManager.removeJarLevelById(jarLvl.getLevel().getIdDB());
				Window.affect(new LevelSelector(Type.tomoderate));
			}
		}
		else{
			Window.resize(Profile.current.getScreenDimensions());
			AddonManager.loadJarLevel(jarLvl);
			Window.affect(new Game());
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
