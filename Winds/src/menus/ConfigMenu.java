package menus;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import account.Profile;
import database.DBClass;
import display.Window;

public class ConfigMenu extends JPanel{
	private static final long serialVersionUID = -1171508012901068360L;
	
	private Font windsPolice30 = null, windsPolice36 = null;
	private JButton jBtnBack, btnSave;
	private JSlider sliderResolution;
	private JPanel north, jNorthWest, jNorthEast, jNorthCenter, middle, middleCenter, south, southEast;
	private JLabel title, lblResolution, lblDisplayResolution;
	
	/**
	 * constructor of the ConfigMenu GUI
	 */
	public ConfigMenu() {
		
		this.setLayout(new BorderLayout());
		
		initializeFont();
		
		initBackButton();
		initSaveButton();

		initResolution();
		
		createNorth();
		createMiddle();
		createSouth();
		
	}

	/**
	 * create the north section of this GUI
	 */
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
		flNorthCenter.setVgap(25);
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
	/**
	 * create the middle section of this GUI
	 */
	private void createMiddle() {
		
		middleCenter = new JPanel();
		FlowLayout flMiddle = new FlowLayout();
		flMiddle.setHgap(250);
		flMiddle.setVgap(65);
		middleCenter.setLayout(flMiddle);
		
		middleCenter.add(lblResolution);
		middleCenter.add(sliderResolution);
		middleCenter.add(lblDisplayResolution);
		middleCenter.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		middle = new JPanel();
		middle.setLayout(new BorderLayout(300,400));
		middle.add(middleCenter, BorderLayout.CENTER);
		
		this.add(middle);
		
	}
	/**
	 * create the south section of this GUI
	 */
	private void createSouth() {
		
		southEast = new JPanel();
		FlowLayout flSouth = new FlowLayout();
		flSouth.setHgap(10);
		flSouth.setVgap(10);
		southEast.setLayout(flSouth);
		southEast.add(btnSave);
		
		south = new JPanel();
		south.setLayout(new BorderLayout());
		south.add(southEast, BorderLayout.EAST);
		this.add(south, BorderLayout.SOUTH);
		
	}
	
	/**
	 * initialize GUI title
	 */
	private void initTitle() {
		title = new JLabel("Configuration");
		title.setFont(windsPolice36);
	}
	/**
	 * initialize resolution slider and text
	 */
	private void initResolution() {
		sliderResolution = new JSlider();
		sliderResolution.setValue(Profile.current.getResolution());
		sliderResolution.setMaximum(2);
		sliderResolution.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				switch(sliderResolution.getValue()){
				case 0:
					lblDisplayResolution.setText("640x480");
					break;
				case 1:
					lblDisplayResolution.setText("800x600");
					break;
				case 2:
					lblDisplayResolution.setText("1024x768");
					break;
				}
			}
		});
		lblResolution = new JLabel("Resolution");
		lblResolution.setFont(windsPolice30);
		lblDisplayResolution = new JLabel();
		switch(sliderResolution.getValue()){
		case 0:
			lblDisplayResolution.setText("640x480");
			break;
		case 1:
			lblDisplayResolution.setText("800x600");
			break;
		case 2:
			lblDisplayResolution.setText("1024x768");
			break;
		}
		lblDisplayResolution.setFont(windsPolice30);
	}
	/**
	 * initialize "Save" button
	 */
	private void initSaveButton() {
		btnSave = new JButton();
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Save.png")));
		btnSave.setBorder(new SoftBevelBorder(0));
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });
		btnSave.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				btnSave.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Save.png")));
			}
			public void mouseEntered(MouseEvent e) {
				btnSave.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Save_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}
	/**
	 * initialize "Back" button
	 */
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
	/**
	 * initialize custom font
	 */
	private void initializeFont() {
		try {
    		windsPolice30 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,30F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice30 = new Font ("Serif", Font.BOLD, 30);
			windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
	}
	
	/**
	 * determines what has to be done when clicking on the "Save" button
	 * @param evt
	 */
	protected void jBtnSaveActionPerformed(ActionEvent evt) {
		try {
			Profile.current = Profile.current.updateConfiguration(sliderResolution.getValue());
			JOptionPane.showMessageDialog(null, "Configuration updated !");
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Critical database issue, please restart the game.");
			DBClass.createStructures();
			DBClass.createStartData();
			System.exit(1);
		} finally {
			DBClass.disconnect();
		}
		
	}
	/**
	 * determines what has to be done when clicking on the "Back" button
	 * @param evt
	 */
	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.affect(new MainMenu(),Window.DIM_STANDARD);
	}
}
