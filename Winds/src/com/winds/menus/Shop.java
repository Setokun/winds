package com.winds.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.winds.display.Window;

public class Shop  extends JPanel {
	private static final long serialVersionUID = -6019096849869583156L;
	
	// Variables declarations
	private JLabel title;
	private JButton jBtnBack;
	private JScrollPane scrollPaneNewThemes, scrollPaneNewLevels, scrollPaneCustomLevels, scrollPaneLevelsToModerate;
	private JTable tableNewThemes, tableNewLevels, tableCustomLevels, tableLevelsToModerate;
	private GroupLayout gl;
	private boolean isAdmin = false;
	// end declarations
	
	public Shop() {
		setBackground(new Color(200,200,200));
		this.setPreferredSize(new Dimension(800,550));
		
		title = new JLabel("Shop");
		title.setFont(new Font("bubble & soap", 0, 50));
		
		
		jBtnBack = new JButton();
		jBtnBack.setIcon(new ImageIcon("res/Buttons/Back.png"));

		jBtnBack.setBorder(new SoftBevelBorder(0));
		jBtnBack.setBorderPainted(false);
		jBtnBack.setContentAreaFilled(false);
		jBtnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnBackActionPerformed(evt);
            }
        });
		jBtnBack.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("res/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("res/Buttons/Back_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
		
		
		int titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
		scrollPaneNewThemes = new JScrollPane();
		scrollPaneNewLevels = new JScrollPane();
		scrollPaneCustomLevels = new JScrollPane();
		scrollPaneLevelsToModerate = new JScrollPane();
		
		gl = new GroupLayout(this);
		
		ParallelGroup hGroup = gl.createParallelGroup(Alignment.LEADING);
		hGroup.addGroup(gl.createSequentialGroup()
			.addGap(titleMargin)
			.addComponent(title)
			.addPreferredGap(ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
			.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(gl.createSequentialGroup()
			.addGap(124)
			.addComponent(scrollPaneNewThemes, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
			.addGap(18)
			.addComponent(scrollPaneNewLevels, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE));
		if(isAdmin){
		hGroup.addGroup(gl.createSequentialGroup()
			.addGap(225)
			.addComponent(scrollPaneLevelsToModerate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE));
		}
		
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(hGroup)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addComponent(scrollPaneCustomLevels, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
		);
		
		SequentialGroup vGroup = gl.createSequentialGroup().addContainerGap();
		vGroup.addGroup(gl.createParallelGroup(Alignment.LEADING)
				.addComponent(title)
				.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		vGroup.addGroup(gl.createParallelGroup(Alignment.LEADING, false)
			.addComponent(scrollPaneNewLevels, 0, 0, Short.MAX_VALUE)
			.addComponent(scrollPaneNewThemes, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE));
		vGroup.addGap(18)
			.addComponent(scrollPaneCustomLevels, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE);
		if(isAdmin){
		vGroup.addGap(18)
			.addComponent(scrollPaneLevelsToModerate, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE);
		vGroup.addGap(23);
		}else{
			vGroup.addGap(75);
		}

		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(vGroup)
		);
		
		
		
		tableNewThemes = new JTable();
		tableNewThemes.getTableHeader().setBackground(new Color(23,182,255));
		tableNewThemes.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewThemes.getTableHeader().setForeground(Color.WHITE);
		tableNewThemes.setRowHeight(20);
		tableNewThemes.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewThemes.setBackground(Color.WHITE);
		//tableNewThemes.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableNewThemes.setModel(new DefaultTableModel(
			new Object[][] {
				{"NewTheme_1", "install"},
				{"NewTheme_2", "install"},
				{"NewTheme_3", "install"},
				{"NewTheme_4", "install"},
				{"NewTheme_5", "install"},
				{"NewTheme_6", "install"},
			},
			new String[] {
				"New Themes", ""
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableNewThemes.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableNewThemes.getColumnModel().getColumn(0).setResizable(false);
		tableNewThemes.getColumnModel().getColumn(1).setResizable(false);
		
		scrollPaneNewThemes.setViewportView(tableNewThemes);
		
		tableNewLevels = new JTable();
		tableNewLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableNewLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewLevels.getTableHeader().setForeground(Color.WHITE);
		tableNewLevels.setRowHeight(20);
		tableNewLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewLevels.setBackground(Color.WHITE);
		//tableNewLevels.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableNewLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"NewOfficialLevel_1", "install"},
				{"NewOfficialLevel_2", "install"},
				{"NewOfficialLevel_3", "install"},
				{"NewOfficialLevel_4", "install"},
				{"NewOfficialLevel_5", "install"},
				{"NewOfficialLevel_6", "install"},
				{"NewOfficialLevel_7", "install"},
				{"NewOfficialLevel_8", "install"},
				{"NewOfficialLevel_9", "install"},
				{"NewOfficialLevel_10", "install"},
				{"NewOfficialLevel_11", "install"},
			},
			new String[] {
				"New Levels", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableNewLevels.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableNewLevels.getColumnModel().getColumn(0).setResizable(false);
		tableNewLevels.getColumnModel().getColumn(1).setResizable(false);
		
		scrollPaneNewLevels.setViewportView(tableNewLevels);
		
		tableCustomLevels = new JTable();
		tableCustomLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableCustomLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableCustomLevels.getTableHeader().setForeground(Color.WHITE);
		tableCustomLevels.setRowHeight(20);
		tableCustomLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableCustomLevels.setBackground(Color.WHITE);
		//tableCustomLevels.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableCustomLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"Custom Level 1", "", "", ""},
				{"Custom Level 2", "", "", ""},
				{"Custom Level 3", "", "", ""},
				{"Custom Level 4", "", "", ""},
				{"Custom Level 5", "", "", ""},
				{"Custom Level 6", "", "", ""},
				{"Custom Level 7", "", "", ""},
				{"Custom Level 8", "", "", ""},
				{"Custom Level 9", "", "", ""},
				{"Custom Level 10", "", "", ""},
				{"Custom Level 11", "", "", ""},
			},
			new String[] {
				"Custom level name", "", "", ""
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCustomLevels.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableCustomLevels.getColumnModel().getColumn(0).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(1).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(2).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(3).setResizable(false);
		
		scrollPaneCustomLevels.setViewportView(tableCustomLevels);
		
		tableLevelsToModerate = new JTable();
		tableLevelsToModerate.getTableHeader().setBackground(new Color(23,182,255));
		tableLevelsToModerate.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableLevelsToModerate.getTableHeader().setForeground(Color.WHITE);
		tableLevelsToModerate.setRowHeight(20);
		tableLevelsToModerate.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableLevelsToModerate.setBackground(Color.WHITE);
		//tableLevelsToModerate.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableLevelsToModerate.setModel(new DefaultTableModel(
			new Object[][] {
				{"Level_to_moderate_1", null},
				{"Level_to_moderate_2", null},
				{"Level_to_moderate_3", null},
				{"Level_to_moderate_4", null},
				{"Level_to_moderate_5", null},
				{"Level_to_moderate_6", null},
				{"Level_to_moderate_7", null},
				{"Level_to_moderate_8", null},
				{"Level_to_moderate_9", null},
				{"Level_to_moderate_10", null},
				{"Level_to_moderate_11", null},
			},
			new String[] {
				"Levels to moderate", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableLevelsToModerate.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableLevelsToModerate.getColumnModel().getColumn(0).setResizable(false);
		tableLevelsToModerate.getColumnModel().getColumn(1).setResizable(false);
		
		scrollPaneLevelsToModerate.setViewportView(tableLevelsToModerate);
		
		setLayout(gl);
		
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
	}

}
