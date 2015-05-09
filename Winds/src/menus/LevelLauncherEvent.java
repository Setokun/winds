package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import addons.Level;
import display.Game;
import display.Window;

public class LevelLauncherEvent implements ActionListener{

	private Level lvl;
	
	public LevelLauncherEvent(Level lvl) {
		this.lvl = lvl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Window.resize(Window.profile.getScreenDimensions());
		Window.affect(new Game(lvl));
	}

}
