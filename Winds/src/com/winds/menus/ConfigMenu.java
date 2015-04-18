package com.winds.menus;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigMenu extends JPanel{
	private static final long serialVersionUID = -1171508012901068360L;
	
	public ConfigMenu() {
		this.setPreferredSize(new Dimension(800, 550));
		
		JLabel title = new JLabel("Configuration");
		title.setFont(new Font("bubble & soap", 0, 36));
		
		int titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
		JButton btnBack = new JButton("Back");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(titleMargin)
					.addComponent(title)
					.addGap(192)
					.addComponent(btnBack)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack)
						.addComponent(title))
					.addContainerGap(502, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
