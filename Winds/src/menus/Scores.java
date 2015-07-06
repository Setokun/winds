package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BoxLayout;
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
import database.Score;
import database.Trophy;
import display.Window;

public class Scores extends JPanel {
	private static final long serialVersionUID = -7611789181663762384L;
	
	private Font windsPolice48 = null, windsPolice18 = null;
	private JTable tableScores, tableTrophies;
	private JLabel title;
	private JButton jBtnBack, btnUploadMyScores;
	private Object[][] results = null, resultsTrophies = null;
	private JScrollPane scrollPaneScores, scrollPaneTrophies;
	private JPanel north, middle, south, jNorthWest, jNorthEast, jNorthCenter, northSouth;
	
	
	public Scores() {
				
    	initializeFont();
		this.setLayout(new BorderLayout());
		
		initBackButton();
		initUploadMyScoresButton();
		
		
		initTableScores();
		initTableTrophies();
		
		
		createNorth();
		createMiddle();
		createSouth();

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
		
		northSouth = new JPanel();
		northSouth.setLayout(new BoxLayout(northSouth, BoxLayout.LINE_AXIS));
		northSouth.add(btnUploadMyScores);
		
		north = new JPanel();
		BorderLayout northLayout = new BorderLayout();
		north.setLayout(northLayout);
		north.add(jNorthWest, BorderLayout.WEST);
		north.add(jNorthCenter, BorderLayout.CENTER);
		north.add(jNorthEast, BorderLayout.EAST);
		north.add(northSouth, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
	}
	private void createMiddle() {
		
		middle = new JPanel();
		FlowLayout flMiddle = new FlowLayout();
		flMiddle.setHgap(20);
		flMiddle.setVgap(20);
		middle.setLayout(flMiddle);
		
		middle.add(scrollPaneScores);
		this.add(middle, BorderLayout.CENTER);
		
	}
	private void createSouth() {
		
		south = new JPanel();
		FlowLayout flSouth = new FlowLayout();
		flSouth.setHgap(20);
		flSouth.setVgap(20);
		south.setLayout(flSouth);
		
		south.add(scrollPaneTrophies);
		this.add(south, BorderLayout.SOUTH);
		
		
	}
	

	private void initTitle() {
		title = new JLabel("Scores");
		title.setFont(windsPolice48);	
	}
	private void initializeFont() {
		try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice48 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,48F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice48 = new Font ("Serif", Font.BOLD, 48);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initTableScores() {
		scrollPaneScores = new JScrollPane();
		
		results = Score.getFormattedScores();
		
		tableScores = new JTable();
		tableScores.getTableHeader().setBackground(new Color(23,182,255));
		tableScores.getTableHeader().setFont(windsPolice18);
		tableScores.getTableHeader().setForeground(Color.WHITE);
		tableScores.setRowHeight(30);
		tableScores.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableScores.setBackground(Color.WHITE);
		tableScores.setFont(windsPolice18);
		tableScores.setModel(new DefaultTableModel(
			results,
			new String[] {
				"LEVEL NAME", "ITEMS", "CLICKS", "TIME", "SCORE"
			}
		) {
			private static final long serialVersionUID = 1093764222025627539L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class
			};
			
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		for (int i = 0; i < 5; i++) {
			tableScores.getColumnModel().getColumn(i).setResizable(false);
		}
		scrollPaneScores.setPreferredSize(new Dimension(760, 170));
		scrollPaneScores.setViewportView(tableScores);
	}
	private void initTableTrophies() {
		scrollPaneTrophies = new JScrollPane();
		
		resultsTrophies = this.getTrophies();
		
		tableTrophies = new JTable();
		tableTrophies.getTableHeader().setBackground(new Color(23,182,255));
		tableTrophies.getTableHeader().setFont(windsPolice18);
		tableTrophies.getTableHeader().setForeground(Color.WHITE);
		tableTrophies.setRowHeight(30);
		tableTrophies.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableTrophies.setBackground(Color.WHITE);
		tableTrophies.setFont(windsPolice18);
		tableTrophies.setModel(new DefaultTableModel(
			resultsTrophies,
			new String[] {
				"Trophies", ""
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
		tableTrophies.getColumnModel().getColumn(0).setPreferredWidth(500);
		tableTrophies.getColumnModel().getColumn(1).setResizable(false);
		tableTrophies.getColumnModel().getColumn(1).setPreferredWidth(25);
		new ButtonColumn(tableTrophies, null, 1);
		scrollPaneTrophies.setPreferredSize(new Dimension(700, 145));
		scrollPaneTrophies.setViewportView(tableTrophies);
	}	
	private void initUploadMyScoresButton() {
		btnUploadMyScores = new JButton();
		btnUploadMyScores.setIcon(new javax.swing.ImageIcon("resources/Buttons/UploadMyScores.png"));
		btnUploadMyScores.setBorder(new javax.swing.border.SoftBevelBorder(0));
		btnUploadMyScores.setBorderPainted(false);
		btnUploadMyScores.setContentAreaFilled(false);
		btnUploadMyScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	btnUploadMyScoresActionPerformed(evt);
            }
        });
		btnUploadMyScores.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				btnUploadMyScores.setIcon(new ImageIcon("resources/Buttons/UploadMyScores.png"));
			}
			public void mouseEntered(MouseEvent e) {
				btnUploadMyScores.setIcon(new ImageIcon("resources/Buttons/UploadMyScores_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	private void initBackButton() {
		jBtnBack = new JButton();
		jBtnBack.setIcon(new javax.swing.ImageIcon("resources/Buttons/Back.png"));

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
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	protected void btnUploadMyScoresActionPerformed(ActionEvent evt) {
		if(ServerConnection.uploadScores(Score.getLocalScores())){
			JOptionPane.showMessageDialog(null, "Scores uploaded");
		}
	}
	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
		
	}
	
	private Object[][] getTrophies(){
		Object[][] listTrophies = Trophy.getTrophies();
		Object[][] listTrophiesToDisplay = null;
		if(listTrophies != null){
			listTrophiesToDisplay = new Object[listTrophies.length][2];
			for(int i=0; i<listTrophies.length; i++){
				listTrophiesToDisplay[i][0] = listTrophies[i][0];
				listTrophiesToDisplay[i][1] = listTrophies[i][1].equals("ok")? new ImageIcon("resources/tick.png"):null;
			}
		}
		return listTrophiesToDisplay;
	}
	
}
