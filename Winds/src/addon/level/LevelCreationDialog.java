package addon.level;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import addon.AddonManager;
import addon.JarTheme;
import display.Window;

public class LevelCreationDialog {

	private final Dimension DIALOG_DIM = new Dimension(410,170);
	
	private static String nameChoosen;
	private static JarTheme themeChoosen;
	private static String[] themeNames;
	
	private JDialog dial;
	private JTextField txtName;
	private JComboBox<JarTheme> cbTheme;
	private JLabel lblName, lblTheme;
	private JButton btnValid, btnReset, btnCancel;
	

	public static void show(boolean needThemeChoice){
		nameChoosen = null;
		themeChoosen = null;
		themeNames = getThemeNames( AddonManager.getJarThemes() );
		new LevelCreationDialog(needThemeChoice);
	}
	
	private LevelCreationDialog(boolean needThemeChoice){
		initFields();
		initButtons();
		initDialog();
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	private void initFields(){
		lblName = new JLabel("Level name :");
		txtName = new JTextField();
		//20 chars max
		lblTheme = new JLabel("Theme to use : ");
        cbTheme = new JComboBox(themeNames);
	}
	private void initButtons(){
		btnValid  = new JButton("Valid");
		btnValid.addActionListener((e) -> {
			updateChoices();
			// ya surement des verifs a faire ici
			dial.dispose();
		});
		btnReset  = new JButton("Reset");
		btnReset.addActionListener((e) -> {
			txtName.setText(null);
			cbTheme.setSelectedIndex(0);
			updateChoices();
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener((e) -> {
			btnReset.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			dial.dispose();
		});
	}
	private void initDialog(){
		dial = new JDialog( Window.getFrame() );
		dial.setTitle("Level creation requirements");
		dial.setPreferredSize(DIALOG_DIM);
		dial.setMinimumSize(DIALOG_DIM);
		dial.setMaximumSize(DIALOG_DIM);
		dial.setResizable(false);
		dial.setLocationRelativeTo(null);
		dial.setModal(true);
		dial.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dial.addWindowListener( initWindowListener() );
		dial.setLayout( initStructure() );
		dial.setVisible(true);
	}
	
	private WindowAdapter initWindowListener(){
		WindowAdapter listener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				updateChoices();
				boolean emptyOrFilled = (nameChoosen == null && themeChoosen == null) ||
										(nameChoosen != null && themeChoosen != null);
				if(emptyOrFilled){
					dial.dispose();
				}else{
					JOptionPane.showMessageDialog(dial,
						"You must type a name and choose a theme.",
						"Mandatory Fields", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		};
		return listener;
	}
	private GroupLayout initStructure(){
		GroupLayout layout = new GroupLayout(dial.getContentPane());
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTheme)
                            .addComponent(lblName))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(cbTheme, 0, 125, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btnValid)
                        .addGap(30,30,30)
                        .addComponent(btnReset)
                        .addGap(30,30,30)
                        .addComponent(btnCancel)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTheme)
                    .addComponent(cbTheme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValid)
                    .addComponent(btnReset)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
        return layout;
	}
	private void updateChoices(){
		String name = txtName.getText();
		nameChoosen = name.equals("") ? null : name;
		
		int indexTheme = cbTheme.getSelectedIndex();
		themeChoosen = (indexTheme == 0) ? null : AddonManager.getJarThemes()[indexTheme -1];
	}
	
	private static String[] getThemeNames(JarTheme[] themes){
		String[] names = new String[ themes.length +1 ];
		names[0] = "Choose a theme";
		for (int i=0; i<themes.length; i++) {
			names[i+1] = themes[i].getName();
		}		
		return names;
	}
	public static String getNameChoosen() {
		return nameChoosen;
	}
	public static JarTheme getThemeChoosen() {
		return themeChoosen;
	}
	
}
