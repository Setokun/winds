package menus;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import accounts.Profile;
import display.Window;

public class Login extends JPanel{
	private static final long serialVersionUID = 7107280239017984047L;
	
    //region : Variables declaration
    private JButton jBtnLogOn, jBtnQuit;
    private JLabel title, jLblLogin, jLblPwd;
    private JPasswordField jPwdPassword;
    private JTextField jTxtLogin;
    //endregion
	
	
	public Login() {
		this.setPreferredSize(new Dimension(800,550));
        initComponents();
    }
                        
    private void initComponents() {

        title = new JLabel();
        jLblLogin = new JLabel();
        jTxtLogin = new JTextField();
        jLblPwd = new JLabel();
        jPwdPassword = new JPasswordField();
        jBtnQuit = new JButton();
        jBtnLogOn = new JButton();

        title.setFont(new Font("bubble & soap", 0, 36));
        title.setText("authentification");

        int titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
        
        jTxtLogin.setFont(new Font("Tahoma", 0, 14));

        jLblLogin.setFont(new Font("bubble & soap", 0, 24));
        jLblLogin.setText("Login");

        jLblPwd.setFont(new Font("bubble & soap", 0, 24));
        jLblPwd.setText("password");

        jBtnQuit.setFont(new Font("bubble & soap", 0, 18));
        jBtnQuit.setIcon(new ImageIcon("resources/Buttons/Quit.png"));
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
				jBtnQuit.setIcon(new ImageIcon("resources/Buttons/Quit.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnQuit.setIcon(new ImageIcon("resources/Buttons/Quit_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        jBtnLogOn.setFont(new Font("bubble & soap", 0, 18));
        jBtnLogOn.setIcon(new ImageIcon("resources/Buttons/Logon.png"));
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
				jBtnLogOn.setIcon(new ImageIcon("resources/Buttons/Logon.png"));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnLogOn.setIcon(new ImageIcon("resources/Buttons/Logon_hover.png"));
			}
			public void mouseClicked(MouseEvent e) {}
		});
        
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(10, Short.MAX_VALUE)
        			.addComponent(jBtnQuit, 140, 140, 140)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jBtnLogOn, 139, 139, 139)
        			.addGap(250))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(149)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLblLogin)
        				.addComponent(jLblPwd))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jTxtLogin, 251, 251, 251)
        				.addComponent(jPwdPassword, 251, 251, 251))
        			.addContainerGap(255, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(titleMargin, Short.MAX_VALUE)
        			.addComponent(title)
        			.addGap(214))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(76)
        			.addComponent(title)
        			.addGap(77)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jTxtLogin, 30, 30, 30)
        				.addComponent(jLblLogin))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jPwdPassword, 30, 30, 30)
        				.addComponent(jLblPwd, 32, 32, 32))
        			.addGap(20)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jBtnQuit, 49, 49, 49)
        				.addComponent(jBtnLogOn, 49, 49, 49))
        			.addContainerGap(220, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }

    private void jBtnQuitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(1);
    }                                        

    private void jBtnLogOnActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	
    	if(jTxtLogin.getText().equals("") || String.valueOf(jPwdPassword.getPassword()).equals(""))
    		JOptionPane.showMessageDialog(null, "Missing email or password !");
    	else {
    		Window.profile = Profile.connect(jTxtLogin.getText(), String.valueOf(jPwdPassword.getPassword()));
    		
    		if(Window.profile == null){
	    		JOptionPane.showConfirmDialog(null, "Bad creditentials, please try again !");
	    	}else{
	    		Window.resize(new Dimension(800, 550));
	    		Window.affect(new MainMenu());
	    	}
    	}
    	
    	
    }                                         

}
