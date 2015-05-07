package menus;

import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import javax.swing.JLabel;

import addons.Level;

public class LevelButton extends JButton{
	private static final long serialVersionUID = -2721501040792454948L;
	
	public LevelButton(){
		
        setBorder(new javax.swing.border.SoftBevelBorder(0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        
	}
	
	public void affect(Level lvl, Group buttonsGroup, Group labelsGroup, JLabel label){
		setIcon(new javax.swing.ImageIcon(lvl.adresseImage));
		addActionListener(new LevelLauncherEvent(lvl));
		label.setText(lvl.titreNiveau);
		buttonsGroup.addComponent(this, 64, 64, 64);
		labelsGroup.addComponent(label);
	}

}
