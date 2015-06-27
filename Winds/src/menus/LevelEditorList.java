package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import leveleditor.EditorGUI;
import addon.AddonManager;
import addon.JarLevel;
import addon.JarTheme;
import addon.Level;
import display.Window;

public class LevelEditorList extends JPanel {
	private static final long serialVersionUID = -7611789181663762384L;
	private Font windsPolice36, windsPolice18;
	private GroupLayout groupLayout;
	private JLabel title;
	private JButton btnBack, btnNewLevel;
	private JTable table;
	private JScrollPane scroll;
	private int titleMargin;
	
	public LevelEditorList() {
		initComponents();
		initComponentsConfig();
		initStructure();
		
		initTableConfig();
		initTableData();
		
		this.setLayout(groupLayout);
		this.setPreferredSize(Window.DIM_STANDARD);		
	}	

	private void initComponents(){
		title = new JLabel();
		btnNewLevel = new JButton();
		btnBack = new JButton();
		table = new JTable();
		scroll = new JScrollPane();
		groupLayout = new GroupLayout(this);
	}
	private void initComponentsConfig() {
    	try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
    	
    	title.setText("Level Editor List");
		title.setFont(windsPolice36);
		titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
		btnNewLevel.setIcon(new ImageIcon("resources/Buttons/NewLevel.png"));
		btnNewLevel.setBorder(new SoftBevelBorder(0));
		btnNewLevel.setBorderPainted(false);
		btnNewLevel.setContentAreaFilled(false);
		btnNewLevel.addActionListener( (evt)->{ btnNewLevelClicked(evt); } );
		btnNewLevel.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				btnNewLevel.setIcon(new ImageIcon("resources/Buttons/NewLevel.png"));
			}
			public void mouseEntered(MouseEvent e) {
				btnNewLevel.setIcon(new ImageIcon("resources/Buttons/NewLevel_hover.png"));
			}
		});
		
		btnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
		btnBack.setBorder(new SoftBevelBorder(0));
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBackClicked(evt);
            }
        });
		btnBack.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				btnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				btnBack.setIcon(new ImageIcon("resources/Buttons/Back_hover.png"));
			}
		});
		
		scroll.setViewportView(table);
	}
	private void initStructure() {
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewLevel)
					.addGap(560))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(titleMargin)
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(title)
						.addGap(15)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewLevel)
					.addGap(50)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(149, Short.MAX_VALUE))
		);
	}
	private void initTableConfig(){
		table.getTableHeader().setBackground(new Color(23,182,255));
		table.getTableHeader().setFont(windsPolice18);
		table.getTableHeader().setForeground(Color.WHITE);
		// penser à virer les bordures du header de la table
		table.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		table.setBackground(Color.WHITE);
		table.setFont(windsPolice18);
		
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
	}
	private void initTableData(){
		table.setModel(
			new DefaultTableModel(
				new Object[][] {
					{"Level 1", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 2", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 3", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 4", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 5", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 6", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 7", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 8", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 9", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 10", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 11", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 12", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 13", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 14", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 15", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 16", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 17", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 18", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 19", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 20", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
					{"Level 21", new ImageIcon("resources/Buttons/Btn_edit.png"), new ImageIcon("resources/Buttons/Btn_duplicate.png"), new ImageIcon("resources/Buttons/Btn_delete.png"), new ImageIcon("resources/Buttons/Btn_upload.png")},
				},
				new String[]{ "LEVEL NAME", "", "", "", "" }
			) {
				private static final long serialVersionUID = 218805739056532012L;
				@SuppressWarnings({ "unused", "rawtypes" })
				Class[] columnTypes = new Class[]{ String.class, ButtonColumn.class,
						ButtonColumn.class, ButtonColumn.class, ButtonColumn.class };
				boolean[] columnEditables = new boolean[]{
						  true, false, false, false, false };
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	
		int colWidth = 98;
		table.setRowHeight(29);
		table.getColumnModel().getColumn(0).setPreferredWidth(275);
		for(int i=1; i<=4; i++){
			table.getColumnModel().getColumn(i).setResizable(false);
			table.getColumnModel().getColumn(i).setMinWidth(colWidth);
			table.getColumnModel().getColumn(i).setMaxWidth(colWidth);
			table.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
		}
		
		new ButtonColumn(table, null, 1);
		new ButtonColumn(table, null, 2);
		new ButtonColumn(table, null, 3);
		new ButtonColumn(table, null, 4);
	}
	
	protected void btnNewLevelClicked(ActionEvent evt) {
		//LevelCreationDialog.show(true);
		String levelName = "aaa";//LevelCreationDialog.getNameChoosen();
		JarTheme themeUsed = AddonManager.getJarThemeByID(3);//LevelCreationDialog.getThemeChoosen();
		
		if(levelName == null || themeUsed == null){
			JOptionPane.showMessageDialog(
					Window.getFrame(), "Mandatory fields missing",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Level lvl = new Level(levelName, themeUsed.getIdDB());
		
		Window.resize(Window.DIM_EDITOR);
		Window.affect(new EditorGUI(new JarLevel(lvl), themeUsed));
	}
	protected void btnBackClicked(ActionEvent evt) {
		Window.resize(Window.DIM_STANDARD);
		Window.affect(new MainMenu());
	}
	
}
