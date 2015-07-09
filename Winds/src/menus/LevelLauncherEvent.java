package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import display.Game;
import display.Window;

public class LevelLauncherEvent implements ActionListener{

	private JarLevel jarLvl;
	
	public LevelLauncherEvent(JarLevel jarLvl) {
		this.jarLvl = jarLvl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Window.resize(Profile.current.getScreenDimensions());
		AddonManager.loadJarLevel(jarLvl);
		Window.affect(new Game());
	}

}
