package menus;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import account.Profile;
import display.Window;

public class Login extends JPanel{
	private static final long serialVersionUID = 7107280239017984047L;
	
	private Font windsPolice24 = null, windsPolice36 = null;
	private JButton jBtnLogOn, jBtnQuit;
    private JLabel title, jLblLogin, jLblPwd;
    private JPasswordField jPwdPassword;
    private JTextField jTxtLogin;
    private GroupLayout layout;
    private int titleMargin;
	
	
	public Login() {
		
		initializeFont();
    	
    	initQuitButton();
    	
    	initLogOnButton();
    	
    	initFields();
    	
        title = new JLabel("authentification");
        title.setFont(windsPolice36);
        titleMargin = 400 - (title.getFontMetrics(title.getFont()).stringWidth(title.getText())/2);
        
        layout = new GroupLayout(this);
        
        initHGroup();
        initVGroup();
        
        this.setLayout(layout);
    }

    private void initFields() {
    	jLblLogin = new JLabel("Login");
        jLblLogin.setFont(windsPolice24);
        jTxtLogin = new JTextField();
        jTxtLogin.setFont(new Font("Tahoma", 0, 14));
        
        jLblPwd = new JLabel("password");
        jLblPwd.setFont(windsPolice24);
        jPwdPassword = new JPasswordField();
	}

	private void initVGroup() {
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
	}

	private void initHGroup() {
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
	}

	private void initLogOnButton() {
    	jBtnLogOn = new JButton(); 
        jBtnLogOn.setFont(windsPolice24);
        jBtnLogOn.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Logon.png")));
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
				jBtnLogOn.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Logon.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnLogOn.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Logon_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initQuitButton() {
    	jBtnQuit = new JButton();
    	jBtnQuit.setFont(windsPolice24);
        jBtnQuit.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Quit.png")));
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
				jBtnQuit.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Quit.png")));
			}
			public void mouseEntered(MouseEvent e) {
				jBtnQuit.setIcon(new ImageIcon(this.getClass().getResource("/resources/buttons/Quit_hover.png")));
			}
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initializeFont() {
    	try {
    		windsPolice24 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,24F);
    		windsPolice36 = Font.createFont(0, getClass().getResourceAsStream("/resources/font/bubble.ttf")).deriveFont(Font.PLAIN,36F);
		} catch (FontFormatException | IOException e) {
			windsPolice24 = new Font ("Serif", Font.BOLD, 24);
    		windsPolice36 = new Font ("Serif", Font.BOLD, 36);
		}
	}

	private void jBtnQuitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(1);
    }                                        

    private void jBtnLogOnActionPerformed(java.awt.event.ActionEvent evt) {
    	String email = "", password = "";
    	email = jTxtLogin.getText();
    	password = String.valueOf(jPwdPassword.getPassword());
    	
    	if(email.equals("") || password.equals(""))
    		JOptionPane.showMessageDialog(null, "Missing email or password !");
    	else {
    		Profile.current = Profile.connect(email, password);
    		
    		if(Profile.current == null){
    			int result = Profile.insertOrUpdateProfile(email, password);
    			if(result == 2){
    				JOptionPane.showMessageDialog(null, "Bad creditentials, please try again !");
    			}
    			else if(result == 1 || result == 3){
    				Profile.current = Profile.connect(email, password);
    	    		Window.affect(new MainMenu(),Window.DIM_STANDARD);
    			}
	    		
	    	}else{
	    		Window.affect(new MainMenu(),Window.DIM_STANDARD);
	    	}
    	}
    }                                         
}
