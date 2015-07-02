package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import server.ServerConnection;
import database.Score;
import display.Window;

public class Scores extends JPanel {
	private static final long serialVersionUID = -7611789181663762384L;
	
	private JTable tableScores;
	private JLabel title;
	private JTable tableTrophies;
	private JButton jBtnBack, btnUploadMyScores;
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Scores() {
		
		Font windsPolice48 = null, windsPolice18 = null;;
    	try {
    		windsPolice18 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,18F);
    		windsPolice48 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,48F);
		} catch (FontFormatException | IOException e) {
			windsPolice18 = new Font ("Serif", Font.BOLD, 18);
    		windsPolice48 = new Font ("Serif", Font.BOLD, 48);
		}
		
		this.setPreferredSize(new Dimension(800,550));
		
		Object[][] results = null, resultsTrophies = null;
		results = Score.getFormattedScores();
		resultsTrophies = Score.getFormattedTrophies();
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		title = new JLabel("Scores");
		title.setFont(windsPolice48);
		
		int titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
		
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
		
		JScrollPane scrollPaneTrophies = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(titleMargin)
							.addComponent(title)
							.addPreferredGap(ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
							.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnUploadMyScores))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(155)
							.addComponent(scrollPaneTrophies, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(title)
							.addGap(12)
							.addComponent(btnUploadMyScores))
						.addComponent(jBtnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(scrollPaneTrophies, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		
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
				"Trophies", "OK"
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
		tableTrophies.getColumnModel().getColumn(0).setPreferredWidth(275);
		tableTrophies.getColumnModel().getColumn(1).setResizable(false);
		tableTrophies.getColumnModel().getColumn(1).setPreferredWidth(50);
		scrollPaneTrophies.setViewportView(tableTrophies);
		this.setLayout(groupLayout);
		
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
		scrollPane.setViewportView(tableScores);
		
	}
	
	protected void btnUploadMyScoresActionPerformed(ActionEvent evt) {
		ServerConnection sc = new ServerConnection();
		if(sc.uploadScores(Score.getLocalScores())){
			JOptionPane.showMessageDialog(null, "Scores uploaded");
		}
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
		
	}

	public class CenterTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 8632281077361047347L;

		public CenterTableCellRenderer() {
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	    }
	}
	
	
	
}
