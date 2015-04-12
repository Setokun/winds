package com.winds.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.winds.display.Window;

public class Login extends JPanel{
	private static final long serialVersionUID = 7107280239017984047L;
	
    //region : Variables declaration
    private JButton jBtnLogOn, jBtnQuit;
    private JLabel jLblAuthentification, jLblLogin, jLblPwd;
    private JPasswordField jPwdPassword;
    private JTextField jTxtLogin;
    //endregion
	
	
	public Login() {
        initComponents();
    }
                        
    private void initComponents() {

        jLblAuthentification = new JLabel();
        jLblLogin = new JLabel();
        jTxtLogin = new JTextField();
        jLblPwd = new JLabel();
        jPwdPassword = new JPasswordField();
        jBtnQuit = new JButton();
        jBtnLogOn = new JButton();

        jLblAuthentification.setFont(new Font("bubble & soap", 0, 36));
        jLblAuthentification.setText("authentification");

        jTxtLogin.setFont(new Font("Tahoma", 0, 14));

        jLblLogin.setFont(new Font("bubble & soap", 0, 24));
        jLblLogin.setText("Login");

        jLblPwd.setFont(new Font("bubble & soap", 0, 24));
        jLblPwd.setText("password");

        jBtnQuit.setFont(new Font("bubble & soap", 0, 18));
        jBtnQuit.setIcon(new ImageIcon("res/Buttons/Quit.png"));
        jBtnQuit.setBorderPainted(false);
        jBtnQuit.setContentAreaFilled(false);
        jBtnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnQuitActionPerformed(evt);
            }
        });
        jBtnQuit.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnQuit.setIcon(new ImageIcon("res/Buttons/Quit.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnQuit.setIcon(new ImageIcon("res/Buttons/Quit_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        jBtnLogOn.setFont(new Font("bubble & soap", 0, 18));
        jBtnLogOn.setIcon(new ImageIcon("res/Buttons/Logon.png"));
        jBtnLogOn.setBorderPainted(false);
        jBtnLogOn.setContentAreaFilled(false);
        jBtnLogOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBtnLogOnActionPerformed(evt);
            }
        });
        jBtnLogOn.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				jBtnLogOn.setIcon(new ImageIcon("res/Buttons/Logon.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnLogOn.setIcon(new ImageIcon("res/Buttons/Logon_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addComponent(jBtnQuit, 140, 140, 140)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLogOn, 139, 139, 139)
                .addGap(178, 178, 178))
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblAuthentification)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblLogin)
                            .addComponent(jLblPwd))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtLogin)
                            .addComponent(jPwdPassword))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLblAuthentification)
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtLogin, 30, 30, 30)
                    .addComponent(jLblLogin))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPwdPassword, 30, 30, 30)
                    .addComponent(jLblPwd, 32, 32, 32))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnQuit, 49, 49, 49)
                    .addComponent(jBtnLogOn, 49, 49, 49))
                .addContainerGap(170, Short.MAX_VALUE))
        );
    }

    private void jBtnQuitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(1);
    }                                        

    private void jBtnLogOnActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	Window.resize(new Dimension(640, 500));
		Window.affect(new MainMenu());
    }                                         

}
