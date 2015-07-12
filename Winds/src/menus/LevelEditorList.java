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
import java.util.HashMap;

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
import server.ServerConnection;
import addon.AddonManager;
import addon.JarLevel;
import addon.JarTheme;
import addon.Level;
import addon.level.LevelCreationDialog;
import addon.level.Type;

import com.google.gson.Gson;

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
	
	
	//region Constructor 
	public LevelEditorList() {
		initComponents();
		initComponentsConfig();
		initStructure();
		
		initTableConfig();
		initTableData();
		
		this.setLayout(groupLayout);
		this.setPreferredSize(Window.DIM_STANDARD);		
	}	
	//endregion
	
	//region Initialisation 
	/**
	 * initialize components
	 */
	private void initComponents(){
		title = new JLabel();
		btnNewLevel = new JButton();
		btnBack = new JButton();
		table = new JTable();
		scroll = new JScrollPane();
		groupLayout = new GroupLayout(this);
	}
	/**
	 * initialize title and buttons
	 */
	private void initComponentsConfig() {
    	try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
    	
    	title.setText("Level Editor List");
		title.setFont(windsPolice36);
		titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
		btnNewLevel.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/NewLevel.png")));
		btnNewLevel.setBorder(new SoftBevelBorder(0));
		btnNewLevel.setBorderPainted(false);
		btnNewLevel.setContentAreaFilled(false);
		btnNewLevel.addActionListener( (evt)->{ btnNewClicked(evt); } );
		btnNewLevel.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				btnNewLevel.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/NewLevel.png")));
			}
			public void mouseEntered(MouseEvent e) {
				btnNewLevel.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/NewLevel_hover.png")));
			}
		});
		
		btnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
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
				btnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
			}
			public void mouseEntered(MouseEvent e) {
				btnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back_hover.png")));
			}
		});
		
		scroll.setViewportView(table);
	}
	/**
	 * initialize GUI structure
	 */
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
	/**
	 * initialize levels list table
	 */
	private void initTableConfig(){
		table.getTableHeader().setBackground(new Color(23,182,255));
		table.getTableHeader().setFont(windsPolice18);
		table.getTableHeader().setForeground(Color.WHITE);
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
	/**
	 * initialize data for the levels list table
	 */
	private void initTableData(){
		JarLevel[] jars = AddonManager.getJarLevelsByType(Type.my);
		
		DefaultTableModel model = new DefaultTableModel(){
			private static final long serialVersionUID = 218805739056532012L;
			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[]{
					JarLevel.class, String.class,
					ButtonColumn.class, ButtonColumn.class,
					ButtonColumn.class, Object.class };
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
				new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_edit.png")),
				new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_duplicate.png")),
				new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_delete.png")),
				jars[i].getLevel().isUploaded() ? "Uploaded" : new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_upload.png"))
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
	/**
	 * returns the JarLevel hidden in a row of the levels list item
	 * @param p the position to identify what JarLevel is required
	 * @return JarLevel
	 */
	private JarLevel getJarLevelAtPoint(Point p){
		if(p == null)	return null;
		return (JarLevel) table.getValueAt((int) p.x, (int) p.y);
	}
	//endregion
	
	//region Button Events 
	/**
	 * determines what action has to be done when clicking on "New Level" button
	 * @param evt
	 */
	private void btnNewClicked(ActionEvent evt) {
		// requirements
		LevelCreationDialog lcd = LevelCreationDialog.show(null);
		if(lcd.canceled())  return;
		
		String levelName = lcd.getNameChoosen();
		JarTheme themeUsed = lcd.getThemeChoosen();
		if(levelName == null || themeUsed == null){
			JOptionPane.showMessageDialog(
					Window.getFrame(), "Mandatory fields missing",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// empty level creation
		Level lvl = new Level(levelName, themeUsed.getIdDB());
		
		// level editor opening
		Window.affect(new EditorGUI(new JarLevel(lvl), themeUsed),Window.DIM_EDITOR);
	}
	/**
	 * determines what action has to be done when clicking on "Back" button
	 * @param evt
	 */
	private void btnBackClicked(ActionEvent evt) {
		Window.affect(new MainMenu(),Window.DIM_STANDARD);
	}
	/**
	 * determines what action has to be done when clicking on "Edit" button
	 * @param evt
	 */
	private void btnEditClicked(ActionEvent evt){
		JarLevel jarL = getJarLevelAtPoint((Point) evt.getSource());
		JarTheme jarT = AddonManager.getJarThemeByID(jarL.getLevel().getIdTheme());
		
		Window.affect(new EditorGUI(jarL, jarT),Window.DIM_EDITOR);
	}
	/**
	 * determines what action has to be done when clicking on "Duplicate" button
	 * @param evt
	 */
	private void btnDuplicateClicked(ActionEvent evt){
		JarLevel jarL = getJarLevelAtPoint((Point) evt.getSource());
		JarTheme jarT = AddonManager.getJarThemeByID(jarL.getLevel().getIdTheme());
		
		// requirements
		LevelCreationDialog lcd = LevelCreationDialog.show(jarT);
		if(lcd.canceled())  return;
		
		String levelName = lcd.getNameChoosen();
		if(levelName == null){
			JOptionPane.showMessageDialog(
				Window.getFrame(), "Mandatory field missing",
				"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// level duplication
		JarLevel newJar = null;
		try {
			Level lvl = (Level) jarL.getLevel().clone();
			if(lvl == null) throw new Exception("Level cloning failed");
					
			lvl.setName(levelName);
			newJar = new JarLevel(lvl);
			newJar.save();
    	} catch (Exception e){
    		JOptionPane.showMessageDialog(Window.getFrame(),
				"Unable to save this level :\n"+ e.getMessage(),
				"Duplication failed", JOptionPane.WARNING_MESSAGE);
    		return;
    	}
		
		AddonManager.addJarLevel(newJar.getFile());
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.addRow(new Object[]{
			newJar,
			newJar.getLevel().getName(),
			new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_edit.png")),
			new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_duplicate.png")),
			new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_delete.png")),
			new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_upload.png"))
		});
		JOptionPane.showMessageDialog(Window.getFrame(),
				"Level duplicated.",
				"Duplicated", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * determines what action has to be done when clicking on "Delete" button
	 * @param evt
	 */
	private void btnDeleteClicked(ActionEvent evt){
		// requirements
		Point p = (Point) evt.getSource();
		int response = JOptionPane.showConfirmDialog(this, "Are you sure to delete this level ?",
					   "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.NO_OPTION)  return;
		
		// level deletion
		JarLevel jar = getJarLevelAtPoint(p);
		
		if( !AddonManager.removeJarLevel(jar) ){
			JOptionPane.showMessageDialog(this,
				"Unable to remove this level from the addon manager.",
				"Deletion failed", JOptionPane.WARNING_MESSAGE);
			return;
		}		
		
		if( !jar.getFile().delete() ){
			JOptionPane.showMessageDialog(this,
				"Unable to remove the file of this level.",
				"Deletion failed", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		((DefaultTableModel) table.getModel()).removeRow(p.x);
		JOptionPane.showMessageDialog(this, "Deletion succeeded.",
			"Deleted", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * determines what action has to be done when clicking on "Upload" button
	 * @param evt
	 */
	private void btnUploadClicked(ActionEvent evt){
		Point p = (Point) evt.getSource();
		if(table.getModel().getValueAt(p.x, 5).equals("Uploaded")) return;
		
		// JarLevel upload
		JarLevel jar = getJarLevelAtPoint(p);
		String jsonResponse = ServerConnection.uploadCustomLevel(jar).get(0);
		@SuppressWarnings("unchecked")
		HashMap<String, String> response = new Gson().fromJson(jsonResponse, HashMap.class);
		if(response.get("error") != null){
			JOptionPane.showMessageDialog(this, response.get("error"),
				"Uploading level failed", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			jar.getLevel().setUploaded(true);
			jar.save();
		} catch (Exception e) {}
		
		table.getModel().setValueAt("Uploaded", p.x, 5);
		JOptionPane.showMessageDialog(this,
			"This level has been uploaded.", "Uploaded",
			JOptionPane.INFORMATION_MESSAGE);
	}
	//endregion

}
