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
import addon.level.Type;
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
	
	/*OK*/public LevelEditorList() {
		initComponents();
		initComponentsConfig();
		initStructure();
		
		initTableConfig();
		initTableData();
		
		this.setLayout(groupLayout);
		this.setPreferredSize(Window.DIM_STANDARD);		
	}	

	//region Initialisation 
	/*OK*/private void initComponents(){
		title = new JLabel();
		btnNewLevel = new JButton();
		btnBack = new JButton();
		table = new JTable();
		scroll = new JScrollPane();
		groupLayout = new GroupLayout(this);
	}
	/*OK*/private void initComponentsConfig() {
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
		btnNewLevel.addActionListener( (evt)->{ btnNewClicked(evt); } );
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
	/*OK*/private void initStructure() {
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
	/*OK*/private void initTableConfig(){
		table.getTableHeader().setBackground(new Color(23,182,255));
		table.getTableHeader().setFont(windsPolice18);
		table.getTableHeader().setForeground(Color.WHITE);
		// penser à virer les bordures du header de la table
		table.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		table.setBackground(Color.WHITE);
		table.setFont(windsPolice18);
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
				int col = table.columnAtPoint( e.getPoint() );
				int row = table.rowAtPoint( e.getPoint() );
				
				ActionEvent evt = new ActionEvent(new Point(row,0), ActionEvent.ACTION_PERFORMED, null);
				if(col == 2)	btnEditClicked(evt);
				if(col == 3)	btnDuplicateClicked(evt);
				if(col == 4)	btnDeleteClicked(evt);
				if(col == 5)	btnUploadClicked(evt);
		    }  
		} );
	}
	/*OK*/private void initTableData(){
		JarLevel[] jars = AddonManager.getJarLevelsByType(Type.my);
		
		DefaultTableModel model = new DefaultTableModel(){
			private static final long serialVersionUID = 218805739056532012L;
			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[]{
					JarLevel.class, String.class,
					ButtonColumn.class, ButtonColumn.class,
					ButtonColumn.class, ButtonColumn.class };
			boolean[] columnEditables = new boolean[]{
					  false, false, false, false, false, false };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		model.setColumnIdentifiers(new String[]{ "", "LEVEL NAME", "", "", "", "" });
		for(int i=0; i<jars.length; i++)
			model.addRow(new Object[]{
				jars[i],
				jars[i].getLevel().getName(),
				new ImageIcon("resources/Buttons/Btn_edit.png"),
				new ImageIcon("resources/Buttons/Btn_duplicate.png"),
				new ImageIcon("resources/Buttons/Btn_delete.png"),
				new ImageIcon("resources/Buttons/Btn_upload.png")
			});

		int colWidth = 98;
		table.setModel(model);
		table.setRowHeight(29);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(275);
		for(int i=2; i<=5; i++){
			table.getColumnModel().getColumn(i).setResizable(false);
			table.getColumnModel().getColumn(i).setMinWidth(colWidth);
			table.getColumnModel().getColumn(i).setMaxWidth(colWidth);
			table.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
		}
		
		new ButtonColumn(table, null, 2);
		new ButtonColumn(table, null, 3);
		new ButtonColumn(table, null, 4);
		new ButtonColumn(table, null, 5);
	}
	//endregion
	
	//region Methods 
	/*OK*/private JarLevel getJarLevelAtPoint(Point p){
		if(p == null)	return null;
		return (JarLevel) table.getValueAt((int) p.x, (int) p.y);
	}
	//endregion
	
	//region Button Events 
	/*OK*/private void btnNewClicked(ActionEvent evt) {
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
	/*OK*/private void btnBackClicked(ActionEvent evt) {
		Window.resize(Window.DIM_STANDARD);
		Window.affect(new MainMenu());
	}
	/*OK*/private void btnEditClicked(ActionEvent evt){
		JarLevel jarL = getJarLevelAtPoint((Point) evt.getSource());
		JarTheme jarT = AddonManager.getJarThemeByID(jarL.getLevel().getIdTheme());
		
		Window.resize(Window.DIM_EDITOR);
		Window.affect(new EditorGUI(jarL, jarT));
	}
	private void btnDuplicateClicked(ActionEvent evt){
		JarLevel jar = getJarLevelAtPoint((Point) evt.getSource());
		
		
		
	}
	private void btnDeleteClicked(ActionEvent evt){
		JarLevel jar = getJarLevelAtPoint((Point) evt.getSource());
		
	}
	private void btnUploadClicked(ActionEvent evt){
		JarLevel jar = getJarLevelAtPoint((Point) evt.getSource());
		
	}
	//endregion

}
