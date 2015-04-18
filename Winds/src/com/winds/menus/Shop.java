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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
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
	// end declarations
	
	public Shop() {
		setBackground(new Color(200,200,200));
		this.setPreferredSize(new Dimension(800,550));
		
		title = new JLabel("Shop");
		title.setFont(new Font("bubble & soap", 0, 50));
		
		
		jBtnBack = new JButton();
		jBtnBack.setIcon(new javax.swing.ImageIcon("res/Buttons/Back.png"));

		jBtnBack.setBorder(new javax.swing.border.SoftBevelBorder(0));
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
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl.createSequentialGroup()
							.addGap(titleMargin)
							.addComponent(title)
							.addPreferredGap(ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
							.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl.createSequentialGroup()
							.addGap(124)
							.addComponent(scrollPaneNewThemes, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPaneNewLevels, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl.createSequentialGroup()
							.addGap(225)
							.addComponent(scrollPaneLevelsToModerate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addComponent(scrollPaneCustomLevels, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(title)
						.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPaneNewLevels, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPaneNewThemes, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(scrollPaneCustomLevels, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneLevelsToModerate, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		
		
		
		
		tableNewLevels = new JTable();
		tableNewLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableNewLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewLevels.getTableHeader().setForeground(Color.WHITE);
		tableNewLevels.setRowHeight(20);
		tableNewLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewLevels.setBackground(Color.WHITE);
		tableNewLevels.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableNewLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", "OK"},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
			},
			new String[] {
				"New Levels", ""
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
		scrollPaneNewLevels.setViewportView(tableNewLevels);
		
		tableNewThemes = new JTable();
		tableNewThemes.getTableHeader().setBackground(new Color(23,182,255));
		tableNewThemes.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewThemes.getTableHeader().setForeground(Color.WHITE);
		tableNewThemes.setRowHeight(20);
		tableNewThemes.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewThemes.setBackground(Color.WHITE);
		tableNewThemes.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableNewThemes.setModel(new DefaultTableModel(
			new Object[][] {
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", "OK"},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
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
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneNewThemes.setViewportView(tableNewThemes);
		
		tableCustomLevels = new JTable();
		tableCustomLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableCustomLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableCustomLevels.getTableHeader().setForeground(Color.WHITE);
		tableCustomLevels.setRowHeight(20);
		tableCustomLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableCustomLevels.setBackground(Color.WHITE);
		tableCustomLevels.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableCustomLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
				{"Finish a level in less than 1min", ""},
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
		scrollPaneCustomLevels.setViewportView(tableCustomLevels);
		
		tableLevelsToModerate = new JTable();
		tableLevelsToModerate.getTableHeader().setBackground(new Color(23,182,255));
		tableLevelsToModerate.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableLevelsToModerate.getTableHeader().setForeground(Color.WHITE);
		tableLevelsToModerate.setRowHeight(20);
		tableLevelsToModerate.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableLevelsToModerate.setBackground(Color.WHITE);
		tableLevelsToModerate.setFont(new Font("bubble & soap", Font.PLAIN, 14));
		tableLevelsToModerate.setModel(new DefaultTableModel(
			new Object[][] {
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", "OK"},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
				{"Finish a level in less than 1min", null},
			},
			new String[] {
				"Levels to moderate", ""
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
		scrollPaneLevelsToModerate.setViewportView(tableLevelsToModerate);
		
		setLayout(gl);
		
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
	}

	public class CenterTableCellRenderer extends DefaultTableCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = -5657765201658268700L;

		public CenterTableCellRenderer() {
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	    }
	}
}
