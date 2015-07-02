package menus;

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
import javax.swing.JSlider;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import display.Window;

public class ConfigMenu extends JPanel{
	private static final long serialVersionUID = -1171508012901068360L;
	
	private Font windsPolice30 = null, windsPolice36 = null;
	private JButton jBtnBack, btnSave;
	private JSlider sliderMusic, sliderSounds, sliderResolution;
	private JLabel title, lblMusic, lblSounds, lblResolution, lblDisplayMusicvolume, lblDisplaySoundsvolume, lblDisplayResolution;
	private GroupLayout groupLayout;
	
	
	public ConfigMenu() {
		
		initializeFont();
		
		title = new JLabel("Configuration");
		title.setFont(windsPolice36);
		
		initBackButton();
		initSaveButton();
		
		initMusic();
		initSounds();
		initResolution();
		
		groupLayout = new GroupLayout(this);
		initHGroup();
		initVGroup();
		
		setLayout(groupLayout);
		
	}

	private void initVGroup() {
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(title)
								.addGap(115)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMusic)
									.addComponent(sliderMusic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDisplayMusicvolume))
								.addGap(46)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(sliderSounds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblSounds)
									.addComponent(lblDisplaySoundsvolume))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblResolution)
										.addComponent(sliderResolution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblDisplayResolution)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(jBtnBack)
								.addGap(355)
								.addComponent(btnSave)))
						.addGap(139))
			);
	}

	private void initHGroup() {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(150, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblSounds)
											.addComponent(lblMusic)
											.addComponent(lblResolution))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(sliderResolution, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(sliderSounds, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(sliderMusic, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblDisplayMusicvolume)
											.addComponent(lblDisplaySoundsvolume)
											.addComponent(lblDisplayResolution)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(240)
										.addComponent(title)
								.addGap(110)
								.addComponent(jBtnBack))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(240)
										.addComponent(title)
								.addGap(100)
								.addComponent(btnSave))))
								)
						.addContainerGap())
			);
	}

	private void initResolution() {
		sliderResolution = new JSlider();
		sliderResolution.setValue(Window.profile.getResolution());
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

	private void initSounds() {
		sliderSounds = new JSlider();
		sliderSounds.setValue(Window.profile.getSoundEffectsVolume()); //TODO � r�cup�rer de la BDD en fonction du profil connect�
		sliderSounds.setMaximum(10);
		sliderSounds.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lblDisplaySoundsvolume.setText(String.valueOf(sliderSounds.getValue()));
			}
		});
		lblSounds = new JLabel("Sounds volume");
		lblSounds.setFont(windsPolice30);
		lblDisplaySoundsvolume = new JLabel();
		lblDisplaySoundsvolume.setText(String.valueOf(sliderSounds.getValue()));
		lblDisplaySoundsvolume.setFont(new Font("bubble & soap", Font.PLAIN, 30));
	}

	private void initMusic() {
		sliderMusic = new JSlider();
		sliderMusic.setValue(Window.profile.getMusicVolume());
		sliderMusic.setMaximum(10);
		sliderMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO 
				lblDisplayMusicvolume.setText(String.valueOf(sliderMusic.getValue()));
			}
		});
		lblMusic = new JLabel("Music volume");
		lblMusic.setFont(windsPolice30);
		lblDisplayMusicvolume = new JLabel();
		lblDisplayMusicvolume.setText(String.valueOf(sliderMusic.getValue()));
		lblDisplayMusicvolume.setFont(new Font("bubble & soap", Font.PLAIN, 30));
	}

	private void initSaveButton() {
		btnSave = new JButton();
		btnSave.setIcon(new ImageIcon("resources/Buttons/Save.png"));
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
				btnSave.setIcon(new ImageIcon("resources/Buttons/Save.png"));
			}
			public void mouseEntered(MouseEvent e) {
				btnSave.setIcon(new ImageIcon("resources/Buttons/Save_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initBackButton() {
		jBtnBack = new JButton();
		jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
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
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnBack.setIcon(new ImageIcon("resources/Buttons/Back_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initializeFont() {
		try {
    		windsPolice30 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,30F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice30 = new Font ("Serif", Font.BOLD, 30);
			windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
	}
	
	protected void jBtnSaveActionPerformed(ActionEvent evt) {
		Window.profile = Window.profile.updateConfiguration(sliderMusic.getValue(), sliderSounds.getValue(), sliderResolution.getValue());
		JOptionPane.showMessageDialog(null, "Configuration updated !");
	}

	protected void jBtnBackActionPerformed(ActionEvent evt) {
		Window.resize(new Dimension(800, 550));
		Window.affect(new MainMenu());
	}
}
