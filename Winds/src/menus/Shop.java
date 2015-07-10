package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import server.ServerConnection;
import account.Profile;
import addon.AddonManager;
import addon.level.Type;
import database.LevelData;
import database.LevelStatus;
import database.ThemeData;
import display.Window;

public class Shop  extends JPanel {
	private static final long serialVersionUID = -6019096849869583156L;
	
	// Variables declarations
	private JLabel title;
	private JButton jBtnBack;
	private JScrollPane scrollPaneNewThemes, scrollPaneNewLevels, scrollPaneCustomLevels, scrollPaneLevelsToModerate;
	private JTable tableNewThemes, tableNewLevels, tableCustomLevels, tableLevelsToModerate;
	private Object[][] listThemesToDisplay = null, listBasicLevelsToDisplay = null, listCustomLevelsToDisplay = null, listLevelsToModerateToDisplay = null;
	private JPanel north, middle, south, jNorthWest, jNorthEast, jNorthCenter, centerWest, centerEast, centerSouth;
	Font windsPolice48 = null, windsPolice18 = null;
	private int colWidth = 120;
	Dimension standardTableDimension;
	private boolean isModoAdmin;
	// end declarations
	
	public Shop() {

		this.isModoAdmin = Profile.current.getUserType().equals("administrator") 
				|| Profile.current.getUserType().equals("moderator");
		
		standardTableDimension = new Dimension(320,90);
		
		this.setLayout(new BorderLayout());
		
    	initializeFont();
		initBackButton();
		
		populateLists();

		createNorth();
		createMiddle();
		if(isModoAdmin)createSouth();
		
		initializeThemeList(listThemesToDisplay);
		initializeNewBasicLevelsList(listBasicLevelsToDisplay);
		initializeCustomLevelsList(listCustomLevelsToDisplay);
		if(isModoAdmin)initializeLevelsToModerateList(listLevelsToModerateToDisplay);
		
	}

	private void initTitle() {
		title = new JLabel("Shop");
		title.setFont(windsPolice48);
	}

	private void populateLists() {
		try {
			listThemesToDisplay = getThemesList();
			listBasicLevelsToDisplay = getLevelsList(Type.basic);
			listCustomLevelsToDisplay = getCustomLevelsList(Type.custom);
			listLevelsToModerateToDisplay = getLevelsList(Type.tomoderate);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Unable to reach distant winds server, please verify your internet connection and try again !");
		}
	}

	private void createNorth() {
		
		initTitle();
		
		jNorthWest = new JPanel();
		FlowLayout flNorthWest = new FlowLayout();
		flNorthWest.setHgap(10);
		flNorthWest.setVgap(10);
		jNorthWest.setLayout(flNorthWest);
		JButton b = new JButton();
		b.setPreferredSize(new Dimension(131,63));
		b.setBorder(new SoftBevelBorder(0));
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		jNorthWest.add(b);
		
		jNorthCenter = new JPanel();
		FlowLayout flNorthCenter = new FlowLayout();
		jNorthCenter.setLayout(flNorthCenter);
		jNorthCenter.add(title);
		
		jNorthEast = new JPanel();
		FlowLayout flNorthEast = new FlowLayout();
		flNorthEast.setHgap(10);
		flNorthEast.setVgap(10);
		jNorthEast.setLayout(flNorthEast);
		jNorthEast.add(jBtnBack);
		
		
		north = new JPanel();
		BorderLayout northLayout = new BorderLayout();
		north.setLayout(northLayout);
		
		north.add(jNorthWest, BorderLayout.WEST);
		north.add(jNorthCenter, BorderLayout.CENTER);
		north.add(jNorthEast, BorderLayout.EAST);
		this.add(north, BorderLayout.NORTH);
		
	}
	private void createMiddle() {
		
		scrollPaneNewThemes = new JScrollPane();
		scrollPaneNewLevels = new JScrollPane();
		scrollPaneCustomLevels = new JScrollPane();
		
		middle = new JPanel();
		middle.setLayout(new BorderLayout());
		
		centerWest = new JPanel();
		FlowLayout flCenterWest = new FlowLayout();
		flCenterWest.setHgap(50);
		centerWest.setLayout(flCenterWest);
		centerWest.add(scrollPaneNewThemes);
		
		centerEast = new JPanel();
		FlowLayout flCenterEast = new FlowLayout();
		flCenterEast.setHgap(50);
		centerEast.setLayout(flCenterEast);
		centerEast.add(scrollPaneNewLevels);
		
		centerSouth = new JPanel();
		FlowLayout flCenterSouth = new FlowLayout();
		flCenterSouth.setHgap(20);
		flCenterSouth.setVgap(10);
		centerSouth.setLayout(flCenterSouth);
		centerSouth.add(scrollPaneCustomLevels);
		
		middle.add(centerWest, BorderLayout.WEST);
		middle.add(centerEast, BorderLayout.EAST);
		middle.add(centerSouth, BorderLayout.SOUTH);
		
		this.add(middle, BorderLayout.CENTER);
		
		
	}
	private void createSouth() {
		
		scrollPaneLevelsToModerate = new JScrollPane();
		
		south = new JPanel();
		FlowLayout flSouth = new FlowLayout();
		flSouth.setVgap(10);
		south.setLayout(flSouth);
		
		south.add(scrollPaneLevelsToModerate);
		this.add(south, BorderLayout.SOUTH);
		
	}

	private void initBackButton() {
		jBtnBack = new JButton();
		jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
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
				jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Back_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initializeFont() {
		try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice48 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,48F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice48 = new Font ("Serif", Font.BOLD, 48);
		}
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(Window.DIM_STANDARD);
		Window.affect(new MainMenu());
	}
	
	private Object[][] getThemesList() throws IOException{
		Object[][] listThemes = ThemeData.getThemesList();
		Object[][] listThemesToDisplay = null;
		if(listThemes != null){
			listThemesToDisplay = new Object[listThemes.length][3];
			for(int i=0; i<listThemes.length; i++){
				listThemesToDisplay[i][0] = listThemes[i][0];
				listThemesToDisplay[i][1] = new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png"));
				listThemesToDisplay[i][2] = listThemes[i][1];
			}
		}
		return listThemesToDisplay;
	}
	private Object[][] getLevelsList(Type type) throws IOException{
		Object[][] listLevels = LevelData.getLevelsList(type);
		Object[][] listLevelsToDisplay = null;
		if(listLevels != null){
			listLevelsToDisplay = new Object[listLevels.length][3];
			for(int i=0; i<listLevels.length; i++){
				listLevelsToDisplay[i][0] = listLevels[i][0];
				listLevelsToDisplay[i][1] = new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png"));
				listLevelsToDisplay[i][2] = listLevels[i][1];
			}
		}
		return listLevelsToDisplay;
	}
	private Object[][] getCustomLevelsList(Type type) throws IOException{
		Object[][] listLevels = LevelData.getCustomLevelsList();
		Object[][] listLevelsToDisplay = null;
		int[] ids = AddonManager.getThemesInstalledIds();
		int nbElementsToDisplay = 0;
		if(ids.length > 0){
			for (int i = 0; i < listLevels.length; i++) {
				for (int j = 0; j < ids.length; j++) {
					if(ids[j] == (int)listLevels[i][3]){
						nbElementsToDisplay++;
					}
				}
			}
			
			listLevelsToDisplay = new Object[nbElementsToDisplay][6];
			for(int i=0; i<listLevels.length; i++){
				for (int j = 0; j < ids.length; j++) {
					if(ids[j] == (int)listLevels[i][3]){
						listLevelsToDisplay[i][0] = listLevels[i][0];
						listLevelsToDisplay[i][1] = (listLevels[i][2] == null || listLevels[i][2].equals("uninstalled"))?new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png")):null;
						listLevelsToDisplay[i][2] = (listLevels[i][2] != null && !listLevels[i][2].equals("uninstalled"))?(listLevels[i][2].equals("installed"))?new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_desactivate.png")):new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_activate.png")):null;
						listLevelsToDisplay[i][3] = (listLevels[i][2] != null && !listLevels[i][2].equals("uninstalled"))?new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_uninstall.png")):null;
						listLevelsToDisplay[i][4] = (listLevels[i][2] != null && listLevels[i][2].equals("desactivated"));
						listLevelsToDisplay[i][5] = listLevels[i][1];
					}
				}
			}
		}
		return listLevelsToDisplay;
	}
	
	private void initializeThemeList(Object[][] listThemesToDisplay){
		tableNewThemes = new JTable();
		tableNewThemes.getTableHeader().setBackground(new Color(23,182,255));
		tableNewThemes.getTableHeader().setFont(windsPolice18);
		tableNewThemes.getTableHeader().setForeground(Color.WHITE);
		tableNewThemes.setRowHeight(20);
		tableNewThemes.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewThemes.setBackground(Color.WHITE);
		tableNewThemes.setModel(new DefaultTableModel(
				listThemesToDisplay,
			new String[] {
				"New Themes", "", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
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
		tableNewThemes.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableNewThemes.getColumnModel().getColumn(2).setMinWidth(0);
		tableNewThemes.getColumnModel().getColumn(2).setMaxWidth(0);
		new ButtonColumn(tableNewThemes, null, 1);
		tableNewThemes.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableNewThemes.columnAtPoint(p);
				int row = tableNewThemes.rowAtPoint(p);
				
				String themeName = (String) tableNewThemes.getValueAt(row, 0);
				tableNewThemes.setValueAt(new ImageIcon("Download in progress..."), row, 0);
				tableNewThemes.update(tableNewThemes.getGraphics());
				
				if(col == 1){
					
					if(ServerConnection.downloadTheme((int)tableNewThemes.getValueAt(row, 2))){
						int idThemeInstalled = (int)tableNewThemes.getValueAt(row, 2);
						JOptionPane.showMessageDialog(null, "New theme "+ themeName +" installed !!");
						((DefaultTableModel)tableNewThemes.getModel()).removeRow(row);
						ArrayList<LevelData> rows;
						try {
							rows = ServerConnection.getBasicLevelsList();
							for (int i = 0; i < rows.size(); i++) {
								if(rows.get(i).getIdTheme() == idThemeInstalled){
									Object[] rowToInsert = {rows.get(i).getName(), new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png")), rows.get(i).getIdLevel()};
									((DefaultTableModel)tableNewLevels.getModel()).addRow(rowToInsert);
								}
							}
							rows = ServerConnection.getCustomLevelsList();
							for (int i = 0; i < rows.size(); i++) {
								if(rows.get(i).getIdTheme() == idThemeInstalled){
									Object[] rowToInsert = {rows.get(i).getName(), new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png")),null, null, true, rows.get(i).getIdLevel()};
									((DefaultTableModel)tableCustomLevels.getModel()).addRow(rowToInsert);
								}
							}
						} catch (IOException e1) {
							tableNewThemes.setValueAt(themeName, row, 0);
							JOptionPane.showMessageDialog(null, "Unable to load new levels for this new theme, please reload this menu...");
						}
						
					}
					else{
						tableNewThemes.setValueAt(themeName, row, 0);
					}
				}
		    }
		} );
		scrollPaneNewThemes.setPreferredSize(standardTableDimension);
		scrollPaneNewThemes.setViewportView(tableNewThemes);
	}
	private void initializeNewBasicLevelsList(Object[][] listBasicLevelsToDisplay){
		tableNewLevels = new JTable();
		tableNewLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableNewLevels.getTableHeader().setFont(windsPolice18);
		tableNewLevels.getTableHeader().setForeground(Color.WHITE);
		tableNewLevels.setRowHeight(20);
		tableNewLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableNewLevels.setBackground(Color.WHITE);
		tableNewLevels.setModel(new DefaultTableModel(
				listBasicLevelsToDisplay,
			new String[] {
				"New Levels", "" ,""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
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
		
		tableNewLevels.getColumnModel().getColumn(2).setResizable(false);
		tableNewLevels.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableNewLevels.getColumnModel().getColumn(2).setMinWidth(0);
		tableNewLevels.getColumnModel().getColumn(2).setMaxWidth(0);
		new ButtonColumn(tableNewLevels, null, 1);
		tableNewLevels.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableNewLevels.columnAtPoint(p);
				int row = tableNewLevels.rowAtPoint(p);
				if(col == 1){
					if(ServerConnection.downloadLevel((int)tableNewLevels.getValueAt(row, 2))){
						JOptionPane.showMessageDialog(null, "New level installed !!");
						((DefaultTableModel)tableNewLevels.getModel()).removeRow(row);
					}
					else
						JOptionPane.showMessageDialog(null, "Unable to install this basic level");
				}
		    }  
		} );
		scrollPaneNewLevels.setPreferredSize(standardTableDimension);
		scrollPaneNewLevels.setViewportView(tableNewLevels);
		
	}
	private void initializeCustomLevelsList(Object[][] listCustomLevelsToDisplay){
		tableCustomLevels = new JTable();
		tableCustomLevels.getTableHeader().setBackground(new Color(23,182,255));
		tableCustomLevels.getTableHeader().setFont(windsPolice18);
		tableCustomLevels.getTableHeader().setForeground(Color.WHITE);
		tableCustomLevels.setRowHeight(20);
		tableCustomLevels.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableCustomLevels.setBackground(Color.WHITE);
		tableCustomLevels.setModel(new DefaultTableModel(
				listCustomLevelsToDisplay,
			new String[] {
				"Custom level name", "", "", "", "", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCustomLevels.setRowHeight(30);
		
		//region columnSizes
		tableCustomLevels.getColumnModel().getColumn(0).setResizable(false);
		
		for (int i = 1; i <= 3; i++) {
			tableCustomLevels.getColumnModel().getColumn(i).setResizable(false);
			tableCustomLevels.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
			tableCustomLevels.getColumnModel().getColumn(i).setMinWidth(colWidth);
			tableCustomLevels.getColumnModel().getColumn(i).setMaxWidth(colWidth);
		}
		
		for (int i = 4; i <= 5; i++) {
			tableCustomLevels.getColumnModel().getColumn(i).setResizable(false);
			tableCustomLevels.getColumnModel().getColumn(i).setPreferredWidth(0);
			tableCustomLevels.getColumnModel().getColumn(i).setMinWidth(0);
			tableCustomLevels.getColumnModel().getColumn(i).setMaxWidth(0);
		}
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
						if(ServerConnection.downloadLevel((int)tableCustomLevels.getValueAt(row, 5))){
							JOptionPane.showMessageDialog(null, "New custom level installed !!");
							tableCustomLevels.setValueAt(null, row, col);
							tableCustomLevels.setValueAt(new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_desactivate.png")), row, 2);
							tableCustomLevels.setValueAt(new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_uninstall.png")) , row, 3);
						}
						else
							JOptionPane.showMessageDialog(null, "Unable to install this custom level");
					}
				}
				if(col == 2){
					if(tableCustomLevels.getValueAt(row, 1) == null){
						if((Boolean)tableCustomLevels.getValueAt(row, 4)){
							if(LevelData.setStatus((int)tableCustomLevels.getValueAt(row, 5), LevelStatus.installed)){
								tableCustomLevels.setValueAt(new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_desactivate.png")) , row, col);
								tableCustomLevels.setValueAt(false, row, 4);
							}
							
						}
						else{
							if(LevelData.setStatus((int)tableCustomLevels.getValueAt(row, 5), LevelStatus.desactivated)){
								tableCustomLevels.setValueAt(new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_activate.png")) , row, col);
								tableCustomLevels.setValueAt(true, row, 4);
							}
						}
					}
				}
				if(col == 3){
					if(tableCustomLevels.getValueAt(row, 1) == null){
						int idLevel = (int)tableCustomLevels.getValueAt(row, 5);
						if(LevelData.setStatus(idLevel, LevelStatus.uninstalled)){
							if(AddonManager.removeJarLevelById(idLevel)){
								tableCustomLevels.setValueAt(new ImageIcon(this.getClass().getResource("/resources/buttons/Btn_install.png")) , row, 1);
								tableCustomLevels.setValueAt(null, row, 2);
								tableCustomLevels.setValueAt(null, row, col);
							}
						}
					}
				}
		    }  
		} );
		scrollPaneCustomLevels.setPreferredSize(new Dimension(600,(isModoAdmin)?200:300));
		scrollPaneCustomLevels.setViewportView(tableCustomLevels);
	}
	private void initializeLevelsToModerateList(Object[][] listLevelsToModerateToDisplay){
		tableLevelsToModerate = new JTable();
		tableLevelsToModerate.getTableHeader().setBackground(new Color(23,182,255));
		tableLevelsToModerate.getTableHeader().setFont(windsPolice18);
		tableLevelsToModerate.getTableHeader().setForeground(Color.WHITE);
		tableLevelsToModerate.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableLevelsToModerate.setBackground(Color.WHITE);
		tableLevelsToModerate.setModel(new DefaultTableModel(
				listLevelsToModerateToDisplay,
			new String[] {
				"Levels to moderate", "", ""
			}
		) {
			private static final long serialVersionUID = 5700292645347825439L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
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
		tableLevelsToModerate.getColumnModel().getColumn(2).setResizable(false);
		tableLevelsToModerate.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableLevelsToModerate.getColumnModel().getColumn(2).setMinWidth(0);
		tableLevelsToModerate.getColumnModel().getColumn(2).setMaxWidth(0);
		new ButtonColumn(tableLevelsToModerate, null, 1);
		tableLevelsToModerate.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		        // get the coordinates of the mouse click
				Point p = e.getPoint();
			    // get the row index and col index that contains that coordinate
				int col = tableLevelsToModerate.columnAtPoint(p);
				int row = tableLevelsToModerate.rowAtPoint(p);
				if(col == 1){
					//System.out.println("Installation de " + tableLevelsToModerate.getValueAt(row, 0));
					if(ServerConnection.downloadLevel((int)tableLevelsToModerate.getValueAt(row, 2))){
						JOptionPane.showMessageDialog(null, "New level to moderate installed !!");
						((DefaultTableModel)tableLevelsToModerate.getModel()).removeRow(row);
					}
					else
						JOptionPane.showMessageDialog(null, "Unable to install this level to moderate");
					
				}
		    }  
		} );
		scrollPaneLevelsToModerate.setPreferredSize(standardTableDimension);
		scrollPaneLevelsToModerate.setViewportView(tableLevelsToModerate);
	}

}
