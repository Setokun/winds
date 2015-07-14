package menus;

import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import addon.AddonManager;
import addon.JarLevel;
import addon.level.Type;

public class LevelButton extends JPanel{
	private static final long serialVersionUID = -2721501040792454948L;
	
	private JButton button;
	private JLabel label;
	
	/**
	 * constructor to create a level button, with the information of where it comes from
	 * @param jarLvl The targeted jar level 
	 * @param type The level type : basic, custom, my or tomoderate
	 * @param numPage the number of the page where the button will be placed
	 */
	public LevelButton(JarLevel jarLvl, Type type, int numPage){
		button = new JButton();
		label = new JLabel();
        button.setBorder(new SoftBevelBorder(0));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        BufferedImage logo = AddonManager.getJarThemeByID(jarLvl.getLevel().getIdTheme()).getLogo();
		button.setIcon(new ImageIcon(logo));
		button.addMouseListener(
			(type == Type.TOMODERATE)?
				new LevelToModerateLauncherEvent(jarLvl, numPage)
				:new LevelLauncherEvent(jarLvl, numPage)
		);
		label.setText(jarLvl.getLevel().getName());
	}
	
	/**
	 * return a JPanel containing the logo and the name of the level
	 * @return JPanel
	 */
	public JPanel getButton(){
		JPanel j = new JPanel();
		j.setLayout(new BoxLayout(j, BoxLayout.PAGE_AXIS));
		j.add(this.button);
		j.add(this.label);
		return j;
	}

}
