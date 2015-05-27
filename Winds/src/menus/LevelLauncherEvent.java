package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import addon.AddonManager;
import addon.JarLevel;
import display.Game;
import display.Window;

public class LevelLauncherEvent implements ActionListener{

	private JarLevel lvl;
	
	public LevelLauncherEvent(JarLevel lvl) {
		this.lvl = lvl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Window.resize(Window.profile.getScreenDimensions());
		AddonManager.loadLevel(lvl);
		Window.affect(new Game());
	}

}
