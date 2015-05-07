package menus;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import display.Window;

public class Scores extends JPanel {
	private static final long serialVersionUID = -7611789181663762384L;
	
	private JTable tableScores;
	private JLabel title;
	private JTable tableTrophies;
	private JButton jBtnBack, btnUploadMyScores;
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Scores() {
		this.setPreferredSize(new Dimension(800,550));
		
		JScrollPane scrollPane = new JScrollPane();
		
		title = new JLabel("Scores");
		title.setFont(new Font("bubble & soap", 0, 50));
		
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
		btnUploadMyScores.setIcon(new javax.swing.ImageIcon("res/Buttons/UploadMyScores.png"));
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
				btnUploadMyScores.setIcon(new ImageIcon("res/Buttons/UploadMyScores.png"));
			}
			public void mouseEntered(MouseEvent e) {
				btnUploadMyScores.setIcon(new ImageIcon("res/Buttons/UploadMyScores_hover.png"));
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
		tableTrophies.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableTrophies.getTableHeader().setForeground(Color.WHITE);
		tableTrophies.setRowHeight(30);
		tableTrophies.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableTrophies.setBackground(Color.WHITE);
		tableTrophies.setFont(new Font("bubble & soap", Font.PLAIN, 18));
		tableTrophies.setModel(new DefaultTableModel(
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
		tableScores.getTableHeader().setFont(new Font("bubble & soap", 0, 20));
		tableScores.getTableHeader().setForeground(Color.WHITE);
		tableScores.setRowHeight(30);
		tableScores.setDefaultRenderer(Object.class, new CenterTableCellRenderer());
		tableScores.setBackground(Color.WHITE);
		tableScores.setFont(new Font("bubble & soap", Font.PLAIN, 20));
		tableScores.setModel(new DefaultTableModel(
			new Object[][] {
				{"Level 1", "10530", "137", "1:27", "327/10560"},
				{"Level 2", "10530", "137", "1:27", "327/10560"},
				{"Level 3", "10530", "137", "1:27", "327/10560"},
				{"Level 4", "10530", "137", "1:27", "327/10560"},
				{"Level 5", "10530", "137", "1:27", "327/10560"},
				{"Level 6", "10530", "137", "1:27", "327/10560"},
				{"Level 7", "10530", "137", "1:27", "327/10560"},
				{"Level 8", "10530", "137", "1:27", "327/10560"},
				{"Level 9", "10530", "137", "1:27", "327/10560"},
				{"Level 10", "10530", "137", "1:27", "327/10560"},
				{"Level 11", "10530", "137", "1:27", "327/10560"},
				{"Level 12", "10530", "137", "1:27", "327/10560"},
				{"Level 13", "10530", "137", "1:27", "327/10560"},
				{"Level 14", "10530", "137", "1:27", "327/10560"},
				{"Level 15", "10530", "137", "1:27", "327/10560"},
				{"Level 16", "10530", "137", "1:27", "327/10560"},
				{"Level 17", "10530", "137", "1:27", "327/10560"},
				{"Level 18", "10530", "137", "1:27", "327/10560"},
				{"Level 19", "10530", "137", "1:27", "327/10560"},
				{"Level 20", "10530", "137", "1:27", "327/10560"},
				{"Level 21", "10530", "137", "1:27", "327/10560"},
				{"Level 22", "10530", "137", "1:27", "327/10560"},
				{"Level 23", "10530", "137", "1:27", "327/10560"},
				{"Level 24", "10530", "137", "1:27", "327/10560"},
				{"Level 25", "10530", "137", "1:27", "327/10560"},
				{"Level 26", "10530", "137", "1:27", "327/10560"},
				{"Level 27", "10530", "137", "1:27", "327/10560"},
				{"Level 28", "10530", "137", "1:27", "327/10560"},
				{"Level 29", "10530", "137", "1:27", "327/10560"},
				{"Level 30", "10530", "137", "1:27", "327/10560"},
			},
			new String[] {
				"LEVEL NAME", "SCORE", "CLICKS", "TIME", "POSITION"
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
		tableScores.getColumnModel().getColumn(0).setResizable(false);
		tableScores.getColumnModel().getColumn(1).setResizable(false);
		tableScores.getColumnModel().getColumn(2).setResizable(false);
		tableScores.getColumnModel().getColumn(3).setResizable(false);
		tableScores.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(tableScores);
		
	}
	
	protected void btnUploadMyScoresActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
		
	}

	public class CenterTableCellRenderer extends DefaultTableCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 8632281077361047347L;

		public CenterTableCellRenderer() {
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	    }
	}
}
