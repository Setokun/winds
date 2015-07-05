package addon.level;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private final int FIELD_WIDTH=125, FIELD_HEIGHT=25, MAX_CHAR_NAME=14;
	
	private static String[] themeNames;
	private String nameChoosen;
	private JarTheme themeChoosen;
	
	private boolean canceled;
	private JDialog dial;
	private JTextField txtName;
	private JComboBox<String> cbTheme;
	private JLabel lblName, lblTheme;
	private JButton btnValid, btnReset, btnCancel;
	
	
	//region Constructor 
	/**
	 * Instanciate a dialog box which is required when a JarLevel must be created
	 * with the specified jarThemeToUse.
	 * @param jarThemeToUse
	 */
	private LevelCreationDialog(JarTheme jarThemeToUse){
		canceled = false;
		themeChoosen = jarThemeToUse;
		initFields();
		initButtons();
		initDialog();
	}
	//endregion
	
	
	//region Statics 
	/**
	 * Shows the dialog box. If jarThemeToUse is set, the selection of the JarTheme to use is not displayed.
	 * @param jarThemeToUse
	 * @return LevelCreationDialog
	 */
	public static LevelCreationDialog show(JarTheme jarThemeToUse){
		themeNames = (jarThemeToUse == null) ? getThemeNames(AddonManager.getJarThemes()) : new String[0];
		return new LevelCreationDialog(jarThemeToUse);
	}
	/**
	 * Collects the themes' name.
	 * @param themes Array of JarThemes
	 * @return String[]
	 */
	private static String[] getThemeNames(JarTheme[] themes){
		String[] names = new String[ themes.length +1 ];
		names[0] = "Choose a theme";
		for (int i=0; i<themes.length; i++) {
			names[i+1] = themes[i].getName();
		}		
		return names;
	}
	//endregion
	
	
	//region Initialization 
	/**
	 * Initializes the fields of the dialog box.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initFields(){
		lblName = new JLabel("Level name :");
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(txtName.getText().length() == MAX_CHAR_NAME)
					e.consume();
			}
		});
		//20 chars max
		lblTheme = new JLabel("Theme to use : ");
        cbTheme = new JComboBox(themeNames);
        
        boolean isNullTheme = themeChoosen == null;
		lblTheme.setVisible(isNullTheme);
		cbTheme.setVisible(isNullTheme);
	}
	/**
	 * Initializes the buttons of the dialog box.
	 */
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
			if(cbTheme.isVisible()) cbTheme.setSelectedIndex(0);
			updateChoices();
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener((e) -> {
			canceled = true;
			dial.dispose();
		});
	}
	/**
	 * Initializes the dialog box.
	 */
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
	/**
	 * Initializes the listener of the dialog box.
	 * @return WindowApdater
	 */
	private WindowAdapter initWindowListener(){
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				updateChoices();
				if(nameChoosen != null && themeChoosen != null){
					dial.dispose();
					return;
				}
				
				StringBuilder message = new StringBuilder("You must type a name");
				if(themeChoosen == null)  message.append(" and choose a theme");
				JOptionPane.showMessageDialog(dial, message.append(".").toString(),
					"Mandatory Fields", JOptionPane.ERROR_MESSAGE, null);
			}
		};
	}
	/**
     * Builds the GUI's structure.
	 * @return GroupLayout
	 */
	private GroupLayout initStructure(){
		GroupLayout layout = new GroupLayout(dial.getContentPane());
		
		//region HorizontalGroup 
		layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup( layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84,84,84)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTheme)
                            .addComponent(lblName))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, FIELD_WIDTH, FIELD_WIDTH, Short.MAX_VALUE)
                            .addComponent(cbTheme, FIELD_WIDTH, FIELD_WIDTH, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69,69,69)
                        .addComponent(btnValid)
                        .addGap(30,30,30)
                        .addComponent(btnReset)
                        .addGap(30,30,30)
                        .addComponent(btnCancel)))
                .addContainerGap(69,Short.MAX_VALUE))
        );
        //endregion
        //region VerticalGroup 
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, FIELD_HEIGHT, FIELD_HEIGHT, FIELD_HEIGHT))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTheme)
                    .addComponent(cbTheme, FIELD_HEIGHT, FIELD_HEIGHT, FIELD_HEIGHT))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValid)
                    .addComponent(btnReset)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
        //endregion
        
        return layout;
	}
	//endregion
	
	
	//region Methods 
	/**
	 * Updates the choices.
	 */
	private void updateChoices(){
		String name = txtName.getText();
		nameChoosen = name.equals("") ? null : name;
		
		int indexTheme = cbTheme.getSelectedIndex();
		if(cbTheme.isVisible()) themeChoosen = (indexTheme == 0) ? null : AddonManager.getJarThemes()[indexTheme -1];
	}
	//endregion
	
	
	//region Getters 
	/**
	 * Get the input level name.
	 * @return String
	 */
	public String getNameChoosen() {
		return nameChoosen;
	}
	/**
	 * Get the selected JarTheme object.
	 * @return JarTheme
	 */
	public JarTheme getThemeChoosen() {
		return themeChoosen;
	}
	/**
	 * Checks if the dialog box has been canceled.
	 * @return boolean
	 */
	public boolean canceled(){
		return canceled;
	}
	//endregion
	
}
