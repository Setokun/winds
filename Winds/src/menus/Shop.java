package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

import display.Window;

public class Shop  extends JPanel {
	private static final long serialVersionUID = -6019096849869583156L;
	
	// Variables declarations
	private JLabel title;
	private JButton jBtnBack;
	private JScrollPane scrollPaneNewThemes, scrollPaneNewLevels, scrollPaneCustomLevels, scrollPaneLevelsToModerate;
	private JTable tableNewThemes, tableNewLevels, tableCustomLevels, tableLevelsToModerate;
	private GroupLayout gl;
	private boolean isAdmin = true;
	// end declarations
	
	public Shop() {
		//setBackground(new Color(200,200,200));
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
			.addGap(50)
			.addComponent(scrollPaneNewThemes, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
			.addGap(18)
			.addComponent(scrollPaneNewLevels, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE));
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
		
		int colWidth = 120;
		
		//region New Themes table
		
		tableNewThemes = new JTable();
		tableNewThemes.getTableHeader().setBackground(new Color(23,182,255));
		tableNewThemes.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewThemes.getTableHeader().setForeground(Color.WHITE);
		tableNewThemes.setRowHeight(20);
		tableNewThemes.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewThemes.setBackground(Color.WHITE);
		tableNewThemes.setModel(new DefaultTableModel(
			new Object[][] {
				{"NewTheme_1", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewTheme_2", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewTheme_3", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewTheme_4", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewTheme_5", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewTheme_6", new ImageIcon("res/Buttons/Btn_install.png")},
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
		tableNewThemes.setRowHeight(30);
		tableNewThemes.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableNewThemes.getColumnModel().getColumn(0).setResizable(false);
		tableNewThemes.getColumnModel().getColumn(1).setResizable(false);
		tableNewThemes.getColumnModel().getColumn(1).setPreferredWidth(colWidth);
		tableNewThemes.getColumnModel().getColumn(1).setMinWidth(colWidth);
		tableNewThemes.getColumnModel().getColumn(1).setMaxWidth(colWidth);
		new ButtonColumn(tableNewThemes, null, 1);
		tableNewThemes.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableNewThemes.columnAtPoint(p);
				int row = tableNewThemes.rowAtPoint(p);
				if(col == 1){
					System.out.println("Installation de " + tableNewThemes.getValueAt(row, 0));
					((DefaultTableModel)tableNewThemes.getModel()).removeRow(row);
				}
		    }  
		} );
		scrollPaneNewThemes.setViewportView(tableNewThemes);
		
		//endregion New Themes table
		
		//region New Levels table
		
		tableNewLevels = new JTable();
		tableNewLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableNewLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableNewLevels.getTableHeader().setForeground(Color.WHITE);
		tableNewLevels.setRowHeight(20);
		tableNewLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewLevels.setBackground(Color.WHITE);
		tableNewLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"NewOfficialLevel_1", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_2", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_3", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_4", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_5", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_6", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_7", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_8", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_9", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_10", new ImageIcon("res/Buttons/Btn_install.png")},
				{"NewOfficialLevel_11", new ImageIcon("res/Buttons/Btn_install.png")},
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
		tableNewLevels.setRowHeight(30);
		tableNewLevels.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableNewLevels.getColumnModel().getColumn(0).setResizable(false);
		tableNewLevels.getColumnModel().getColumn(1).setResizable(false);
		tableNewLevels.getColumnModel().getColumn(1).setPreferredWidth(colWidth);
		tableNewLevels.getColumnModel().getColumn(1).setMinWidth(colWidth);
		tableNewLevels.getColumnModel().getColumn(1).setMaxWidth(colWidth);
		new ButtonColumn(tableNewLevels, null, 1);
		tableNewLevels.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableNewLevels.columnAtPoint(p);
				int row = tableNewLevels.rowAtPoint(p);
				if(col == 1){
					System.out.println("Installation de " + tableNewLevels.getValueAt(row, 0));
					((DefaultTableModel)tableNewLevels.getModel()).removeRow(row);
				}
		    }  
		} );
		scrollPaneNewLevels.setViewportView(tableNewLevels);
		
		//endregion New Levels table
		
		//region Custom Levels table
		
		tableCustomLevels = new JTable();
		tableCustomLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableCustomLevels.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableCustomLevels.getTableHeader().setForeground(Color.WHITE);
		tableCustomLevels.setRowHeight(20);
		tableCustomLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableCustomLevels.setBackground(Color.WHITE);
		tableCustomLevels.setModel(new DefaultTableModel(
			new Object[][] {
				{"Custom Level 1", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 2", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 3", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 4", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 5", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 6", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 7", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 8", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 9", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 10", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
				{"Custom Level 11", new ImageIcon("res/Buttons/Btn_install.png"), null, null, true},
			},
			new String[] {
				"Custom level name", "", "", "", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCustomLevels.setRowHeight(30);
		
		//region columnSizes
		tableCustomLevels.getColumnModel().getColumn(0).setResizable(false);
		
		tableCustomLevels.getColumnModel().getColumn(1).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(1).setPreferredWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(1).setMinWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(1).setMaxWidth(colWidth);
		
		tableCustomLevels.getColumnModel().getColumn(2).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(2).setPreferredWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(2).setMinWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(2).setMaxWidth(colWidth);
		
		tableCustomLevels.getColumnModel().getColumn(3).setResizable(false);
		tableCustomLevels.getColumnModel().getColumn(3).setPreferredWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(3).setMinWidth(colWidth);
		tableCustomLevels.getColumnModel().getColumn(3).setMaxWidth(colWidth);
		
		tableCustomLevels.getColumnModel().getColumn(4).setPreferredWidth(0);
		tableCustomLevels.getColumnModel().getColumn(4).setMinWidth(0);
		tableCustomLevels.getColumnModel().getColumn(4).setMaxWidth(0);
		//endregion columnSizes
		
		new ButtonColumn(tableCustomLevels, null, 1);
		new ButtonColumn(tableCustomLevels, null, 2);
		new ButtonColumn(tableCustomLevels, null, 3);
		tableCustomLevels.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableCustomLevels.columnAtPoint(p);
				int row = tableCustomLevels.rowAtPoint(p);
				if(col == 1){
					if(tableCustomLevels.getValueAt(row, col) != null){
						System.out.println("Installation de " + tableCustomLevels.getValueAt(row, 0));
						tableCustomLevels.setValueAt(null, row, col);
						tableCustomLevels.setValueAt(new ImageIcon("res/Buttons/Btn_activate.png"), row, 2);
						tableCustomLevels.setValueAt(new ImageIcon("res/Buttons/Btn_uninstall.png") , row, 3);
					}
				}
				if(col == 2){
					if((Boolean)tableCustomLevels.getValueAt(row, 4)){
						System.out.println("activation de " + tableCustomLevels.getValueAt(row, 0));
						tableCustomLevels.setValueAt(new ImageIcon("res/Buttons/Btn_desactivate.png") , row, col);
						tableCustomLevels.setValueAt(false, row, 4);
					}
					else{
						System.out.println("désactivation de " + tableCustomLevels.getValueAt(row, 0));
						tableCustomLevels.setValueAt(new ImageIcon("res/Buttons/Btn_activate.png") , row, col);
						tableCustomLevels.setValueAt(true, row, 4);
					}
				}
				if(col == 3){
					System.out.println("Désinstallation de " + tableCustomLevels.getValueAt(row, 0));
					tableCustomLevels.setValueAt(null, row, 2);
					tableCustomLevels.setValueAt(null, row, col);
					tableCustomLevels.setValueAt(new ImageIcon("res/Buttons/Btn_install.png") , row, 1);
				}
		    }  
		} );
		
		scrollPaneCustomLevels.setViewportView(tableCustomLevels);
		
		//endregion Custom Levels table
		
		//region Levels to moderate table
		
		tableLevelsToModerate = new JTable();
		tableLevelsToModerate.getTableHeader().setBackground(new Color(23,182,255));
		tableLevelsToModerate.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableLevelsToModerate.getTableHeader().setForeground(Color.WHITE);
		//tableLevelsToModerate.setRowHeight(20);
		tableLevelsToModerate.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableLevelsToModerate.setBackground(Color.WHITE);
		tableLevelsToModerate.setModel(new DefaultTableModel(
			new Object[][] {
				{"Level_to_moderate_1", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_2", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_3", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_4", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_5", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_6", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_7", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_8", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_9", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_10", new ImageIcon("res/Buttons/Btn_install.png")},
				{"Level_to_moderate_11", new ImageIcon("res/Buttons/Btn_install.png")},
			},
			new String[] {
				"Levels to moderate", ""
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
		tableLevelsToModerate.setRowHeight(30);
		tableLevelsToModerate.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableLevelsToModerate.getColumnModel().getColumn(0).setResizable(false);
		tableLevelsToModerate.getColumnModel().getColumn(1).setResizable(false);
		tableLevelsToModerate.getColumnModel().getColumn(1).setPreferredWidth(colWidth);
		tableLevelsToModerate.getColumnModel().getColumn(1).setMinWidth(colWidth);
		tableLevelsToModerate.getColumnModel().getColumn(1).setMaxWidth(colWidth);
		new ButtonColumn(tableLevelsToModerate, null, 1);
		tableLevelsToModerate.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableLevelsToModerate.columnAtPoint(p);
				int row = tableLevelsToModerate.rowAtPoint(p);
				if(col == 1){
					System.out.println("Installation de " + tableLevelsToModerate.getValueAt(row, 0));
					((DefaultTableModel)tableLevelsToModerate.getModel()).removeRow(row);
				}
		    }  
		} );
		scrollPaneLevelsToModerate.setViewportView(tableLevelsToModerate);
		
		//endregion Levels to moderate table
		
		setLayout(gl);
		
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
	}
	

}
