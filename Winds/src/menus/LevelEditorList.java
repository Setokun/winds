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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import display.Window;

public class LevelEditorList extends JPanel {
	private static final long serialVersionUID = -7611789181663762384L;
	private JLabel title;
	private JTable table;
	private JButton jBtnBack, jBtnNewLevel;
	
	@SuppressWarnings("rawtypes")
	public LevelEditorList() {
		this.setPreferredSize(new Dimension(800,550));
		
		title = new JLabel("Level Editor List");
		title.setFont(new Font("bubble & soap", 0, 36));
		
		int titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
		jBtnNewLevel = new JButton();
		jBtnNewLevel.setIcon(new javax.swing.ImageIcon("res/Buttons/NewLevel.png"));
		jBtnNewLevel.setBorder(new javax.swing.border.SoftBevelBorder(0));
		jBtnNewLevel.setBorderPainted(false);
		jBtnNewLevel.setContentAreaFilled(false);
		jBtnNewLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jBtnNewLevelActionPerformed(evt);
            }
        });
		jBtnNewLevel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnNewLevel.setIcon(new ImageIcon("res/Buttons/NewLevel.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnNewLevel.setIcon(new ImageIcon("res/Buttons/NewLevel_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
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
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jBtnNewLevel)
					.addGap(560))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(titleMargin)
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
					.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(title)
						.addGap(15)
						.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jBtnNewLevel)
					.addGap(50)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.getTableHeader().setBackground(new Color(23,182,255));
		table.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		table.getTableHeader().setForeground(Color.WHITE);
		
		// penser à virer les bordures du header
		
		table.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		table.setBackground(Color.WHITE);
		table.setFont(new Font("bubble & soap", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
					{"Level 1", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 2", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 3", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 4", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 5", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 6", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 7", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 8", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 9", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 10", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 11", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 12", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 13", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 14", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 15", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 16", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 17", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 18", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 19", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 20", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					{"Level 21", new ImageIcon("res/Buttons/Btn_edit.png"), new ImageIcon("res/Buttons/Btn_duplicate.png"), new ImageIcon("res/Buttons/Btn_delete.png"), new ImageIcon("res/Buttons/Btn_upload.png")},
					
			},
			new String[] {
					"LEVEL NAME", "", "", "", ""
				}
			) {
				private static final long serialVersionUID = 218805739056532012L;
				@SuppressWarnings("unused")
				Class[] columnTypes = new Class[] {
					String.class, ButtonColumn.class, ButtonColumn.class, ButtonColumn.class, ButtonColumn.class
				};
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		int colWidth = 98;
		
		table.setRowHeight(29);
		table.getColumnModel().getColumn(0).setPreferredWidth(275);
		
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(colWidth);
		table.getColumnModel().getColumn(1).setMinWidth(colWidth);
		table.getColumnModel().getColumn(1).setMaxWidth(colWidth);
		
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(colWidth);
		table.getColumnModel().getColumn(2).setMinWidth(colWidth);
		table.getColumnModel().getColumn(2).setMaxWidth(colWidth);
		
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(colWidth);
		table.getColumnModel().getColumn(3).setMinWidth(colWidth);
		table.getColumnModel().getColumn(3).setMaxWidth(colWidth);
		
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(colWidth);
		table.getColumnModel().getColumn(4).setMinWidth(colWidth);
		table.getColumnModel().getColumn(4).setMaxWidth(colWidth);
		
		new ButtonColumn(table, null, 1);
		new ButtonColumn(table, null, 2);
		new ButtonColumn(table, null, 3);
		new ButtonColumn(table, null, 4);
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = table.columnAtPoint(p);
				int row = table.rowAtPoint(p);
				if(col == 1){
					System.out.println("Edition de " + table.getValueAt(row, 0));
				}
				if(col == 2){
					System.out.println("Duplication de " + table.getValueAt(row, 0));
				}
				if(col == 3){
					System.out.println("Suppression de " + table.getValueAt(row, 0));
				}
				if(col == 4){
					if(!table.getValueAt(row, 4).equals("Uploaded")){
						System.out.println("Upload de " + table.getValueAt(row, 0));
						table.setValueAt("Uploaded", row, 4);
					}
					else{
						System.out.println("niveau déjà uploadé");
					}
					
				}
		    }  
		} );

		scrollPane_1.setViewportView(table);
		this.setLayout(groupLayout);
		
	}
	
	protected void jBtnNewLevelActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("Ouverture de la fenêtre de création en mode 'nouveau niveau'");
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
		
	}

	protected void ActionOnEdit(ActionListener actionListener) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
		
	}
	
}
