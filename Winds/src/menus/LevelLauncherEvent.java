package menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import display.Game;
import display.Window;
import addons.Level;

public class LevelLauncherEvent implements ActionListener{

	private Level lvl;
	
	public LevelLauncherEvent(Level lvl) {
		this.lvl = lvl;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Window.resize(new Dimension(800, 600));
		Window.affect(new Game(lvl));
	}

}
