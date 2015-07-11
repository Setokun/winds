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
	
	public LevelButton(JarLevel jarLvl, Type type, int numPage){
		button = new JButton();
		label = new JLabel();
        button.setBorder(new SoftBevelBorder(0));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        BufferedImage logo = AddonManager.getJarThemeByID(jarLvl.getLevel().getIdTheme()).getLogo();
		button.setIcon(new ImageIcon(logo));
		button.addMouseListener(
			(type == Type.tomoderate)?
				new LevelToModerateLauncherEvent(jarLvl, numPage)
				:new LevelLauncherEvent(jarLvl, numPage)
		);
		label.setText(jarLvl.getLevel().getName());
	}
	
	public JPanel getButton(){
		JPanel j = new JPanel();
		j.setLayout(new BoxLayout(j, BoxLayout.PAGE_AXIS));
		j.add(this.button);
		j.add(this.label);
		return j;
	}

}
