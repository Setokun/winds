
package leveleditor;

public class EditorGUI extends javax.swing.JPanel {

	// Variables declaration - do not modify                     
    private javax.swing.JButton btnBack, btnEmpty, btnSave;
    private javax.swing.JLabel jLabel2, jLabel3, lblDescription, lblLevel, lblTheme, lblTimeMax;
	private javax.swing.JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel5, jPanel6;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane5, jScrollPane6;
    private javax.swing.JSeparator jSeparator1, jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane scrollMatrix;
    private javax.swing.JTable tableAVirerInteractions, tableAVirerSprites, tableAvirer1;
    private javax.swing.JTextField txtLevel, txtTheme, txtTimeMax;
    // End of variables declaration     


    public EditorGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lblLevel = new javax.swing.JLabel();
        lblTheme = new javax.swing.JLabel();
        lblTimeMax = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtLevel = new javax.swing.JTextField();
        txtTheme = new javax.swing.JTextField();
        txtTimeMax = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        btnBack = new javax.swing.JButton();
        scrollMatrix = new javax.swing.JScrollPane();
        tableAvirer1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableAVirerSprites = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableAVirerInteractions = new javax.swing.JTable();
        btnEmpty = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1024, 600));
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setPreferredSize(new java.awt.Dimension(1024, 600));

        btnSave.setFont(new java.awt.Font("bubble & soap", 0, 24));
        btnSave.setText("Save");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblLevel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLevel.setText("Level name :");

        lblTheme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTheme.setText("Theme used :");

        lblTimeMax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTimeMax.setText("Time max :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLevel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTheme, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(lblTimeMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimeMax, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtLevel.setEditable(false);
        txtLevel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLevel.setText("level name");

        txtTheme.setEditable(false);
        txtTheme.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTheme.setText("theme name");

        txtTimeMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTimeMax.setText("number of seconds");

        javax.swing.GroupLayout LayoutTextFields = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(LayoutTextFields);
        LayoutTextFields.setHorizontalGroup(
            LayoutTextFields.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTheme)
            .addComponent(txtTimeMax, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
            .addComponent(txtLevel)
        );
        LayoutTextFields.setVerticalGroup(
            LayoutTextFields.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutTextFields.createSequentialGroup()
                .addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimeMax, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDescription.setText("Level description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout LayoutScrollPane = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(LayoutScrollPane);
        LayoutScrollPane.setHorizontalGroup(
            LayoutScrollPane.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutScrollPane.createSequentialGroup()
                .addContainerGap()
                .addGroup(LayoutScrollPane.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(LayoutScrollPane.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        LayoutScrollPane.setVerticalGroup(
            LayoutScrollPane.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutScrollPane.createSequentialGroup()
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnBack.setFont(new java.awt.Font("bubble & soap", 0, 24)); // NOI18N
        btnBack.setText("Back");

        javax.swing.GroupLayout LayoutBandeau = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(LayoutBandeau);
        LayoutBandeau.setHorizontalGroup(
            LayoutBandeau.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutBandeau.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        LayoutBandeau.setVerticalGroup(
            LayoutBandeau.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayoutBandeau.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LayoutBandeau.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayoutBandeau.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(LayoutBandeau.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        tableAvirer1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollMatrix.setViewportView(tableAvirer1);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setText("Current selected tile");
        jLabel2.setAlignmentY(0.0F);

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout LayoutLabels = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(LayoutLabels);
        LayoutLabels.setHorizontalGroup(
            LayoutLabels.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayoutLabels.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LayoutLabels.setVerticalGroup(
            LayoutLabels.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutLabels.createSequentialGroup()
                .addContainerGap()
                .addGroup(LayoutLabels.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane2.setMaximumSize(new java.awt.Dimension(216, 32767));
        jTabbedPane2.setMinimumSize(new java.awt.Dimension(216, 67));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(216, 100));

        tableAVirerSprites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tableAVirerSprites);

        jTabbedPane2.addTab("Sprites", jScrollPane5);

        tableAVirerInteractions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tableAVirerInteractions);

        jTabbedPane2.addTab("Interactions", jScrollPane6);

        btnEmpty.setText("Select empty tile");
        btnEmpty.setAlignmentY(0.0F);
        btnEmpty.setMaximumSize(new java.awt.Dimension(111, 30));
        btnEmpty.setMinimumSize(new java.awt.Dimension(111, 30));
        btnEmpty.setPreferredSize(new java.awt.Dimension(111, 30));

        javax.swing.GroupLayout LayoutLegend = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(LayoutLegend);
        LayoutLegend.setHorizontalGroup(
            LayoutLegend.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutLegend.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(LayoutLegend.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEmpty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
        );
        LayoutLegend.setVerticalGroup(
            LayoutLegend.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutLegend.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }                       


                  
}
