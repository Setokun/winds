package menus;

import java.awt.image.BufferedImage;

import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.SoftBevelBorder;

import addon.AddonManager;
import addon.JarLevel;

public class LevelButton extends JButton{
	private static final long serialVersionUID = -2721501040792454948L;
	
	public LevelButton(){
		
        setBorder(new SoftBevelBorder(0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        
	}
	
	public void affect(JarLevel jarLvl, Group buttonsGroup, Group labelsGroup, JLabel label){
		BufferedImage logo = AddonManager.getThemeByID(jarLvl.getLevel().getIdTheme()).getLogo();
		setIcon(new ImageIcon(logo));
		addActionListener(new LevelLauncherEvent(jarLvl));
		label.setText(jarLvl.getLevel().getName());
		buttonsGroup.addComponent(this, 64, 64, 64);
		labelsGroup.addComponent(label);
	}

}
