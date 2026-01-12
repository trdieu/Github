/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ui.admin;

import java.awt.Color;
import java.awt.Component;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import manager.control.admin.AccountControl;
import manager.entity.admin.Account;
import manager.ui.LoginUI;

/**
 *
 * @author Dell
 */
public class AddAccountPanel extends javax.swing.JPanel {
    private class UserNameCellEditorListener implements CellEditorListener {

        private TableModel model;
        private int row;

        public UserNameCellEditorListener(TableModel model, int row) {
            this.model = model;
            this.row = row;
        }

        @Override
        public void editingStopped(ChangeEvent e) {
            if (model.getValueAt(row, 2) == null) {
                String userName = (String) model.getValueAt(row, 1);
                model.setValueAt(userName, row, 2);
                model.setValueAt("Sinh viên", row, 3);
            }
            int lastRow = model.getRowCount() - 1;
            if (row == lastRow) {
                if (model.getValueAt(lastRow, 1) != null) {
                    ((DefaultTableModel) model).addRow(new Object[]{lastRow + 2, null, null, null});
                    addAccountTable.getCellEditor(lastRow + 1, 1).addCellEditorListener(new UserNameCellEditorListener(model, lastRow + 1));
                }

            }
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class PasswordCellRenderer extends DefaultTableCellRenderer {
        private static final String ASTERISKS = "*****";

        @Override
        public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
            int length = 0;
            if (arg1 instanceof String) {
                length = ((String) arg1).length();
            } else if (arg1 instanceof char[]) {
                length = ((char[]) arg1).length;
            }
            setText(asterisks(length));
            return this;
        }

        private String asterisks(int length) {
            if (length > ASTERISKS.length()) {
                StringBuilder sb = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    sb.append('*');
                }
                return sb.toString();
            } else {
                return ASTERISKS.substring(0, length);
            }
        }
    }

    private class TypeComboBoxRenderer extends JComboBox implements TableCellRenderer {

        public TypeComboBoxRenderer(String[] items) {
            super(items);
            setSelectedIndex(0);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(new java.awt.Color(255,255,255));
                super.setBackground(new java.awt.Color(0,120,215));
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setSelectedItem(value);
            return this;
        }
    }

    private AccountControl accountControl;
    /**
     * Creates new form AddAccountPanel
     */
    public AddAccountPanel() {
        accountControl = new AccountControl();
        initComponents();
        initAccountTable();
    }

    public void initAccountTable() {
        JTableHeader header = addAccountTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(72, 3, 111));
        header.setForeground(Color.white);
        header.setFont(new java.awt.Font("SansSerif", 1, 14));
        addAccountScroll.getViewport().setBackground(Color.WHITE);
        Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        addAccountScroll.setViewportBorder(border);
        addAccountScroll.setBorder(border);

        TableModel model = addAccountTable.getModel();
        addAccountTable.getCellEditor(0, 1).addCellEditorListener(new UserNameCellEditorListener(model, 0));
        String items[] = {"Sinh viên", "Giáo vụ", "Quản trị"};
        JComboBox typeComboBox = new JComboBox(items);
        typeComboBox.setSelectedIndex(0);
        TableColumn typeColumn = addAccountTable.getColumnModel().getColumn(3);
        typeColumn.setCellEditor(new DefaultCellEditor(typeComboBox));
        typeColumn.setCellRenderer(new TypeComboBoxRenderer(items));
        
        TableColumn passWordColumn = addAccountTable.getColumnModel().getColumn(2);
        passWordColumn.setCellRenderer(new PasswordCellRenderer());
    }

    public void reset() {
        statusLabel.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addAccountScroll = new javax.swing.JScrollPane();
        addAccountTable = new javax.swing.JTable();
        addButton = new manager.ui.ButtonLabel(new java.awt.Color(85,55,118), null);
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        fastCreateLabel = new javax.swing.JLabel();
        fromLabel = new javax.swing.JLabel();
        fromTextField = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        toTextField = new javax.swing.JTextField();
        quickInsertButton = new manager.ui.ButtonLabel(new java.awt.Color(85,55,118), null);

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1020, 565));

        addAccountTable.setAutoCreateRowSorter(true);
        addAccountTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        addAccountTable.setForeground(new java.awt.Color(54, 33, 89));
        addAccountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, ""}
            },
            new String [] {
                "Số thứ tự", "Tên tài khoản", "Mật khẩu mặc định", "Loại tài khoản"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        addAccountTable.setGridColor(new java.awt.Color(204, 204, 255));
        addAccountScroll.setViewportView(addAccountTable);

        addButton.setBackground(new java.awt.Color(64, 43, 100));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addButton.setText("Thêm tài khoản");
        addButton.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        addButton.setOpaque(true);
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addButtonMousePressed(evt);
            }
        });

        titlePanel.setBackground(new java.awt.Color(110, 89, 222));
        titlePanel.setPreferredSize(new java.awt.Dimension(187, 60));
        titlePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 13));

        titleLabel.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Thêm tài khoản");
        titlePanel.add(titleLabel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        statusLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(54, 33, 89));

        fastCreateLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fastCreateLabel.setForeground(new java.awt.Color(54, 33, 89));
        fastCreateLabel.setText("Tạo nhanh tài khoản sinh viên:");

        fromLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fromLabel.setForeground(new java.awt.Color(54, 33, 89));
        fromLabel.setText("Từ");

        fromTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fromTextField.setForeground(new java.awt.Color(54, 33, 89));

        toLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        toLabel.setForeground(new java.awt.Color(54, 33, 89));
        toLabel.setText("Đến");

        toTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        toTextField.setForeground(new java.awt.Color(54, 33, 89));
        toTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toTextFieldActionPerformed(evt);
            }
        });

        quickInsertButton.setBackground(new java.awt.Color(64, 43, 100));
        quickInsertButton.setForeground(new java.awt.Color(255, 255, 255));
        quickInsertButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quickInsertButton.setText("Tạo nhanh");
        quickInsertButton.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        quickInsertButton.setOpaque(true);
        quickInsertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quickInsertButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fastCreateLabel)
                .addGap(18, 18, 18)
                .addComponent(fromLabel)
                .addGap(18, 18, 18)
                .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(toLabel)
                .addGap(18, 18, 18)
                .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(quickInsertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(412, 412, 412))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fastCreateLabel)
                    .addComponent(fromLabel)
                    .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel)
                    .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quickInsertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAccountScroll)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(addAccountScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMousePressed
        Account acc = new Account();
        DefaultTableModel dm = (DefaultTableModel) addAccountTable.getModel();
        int rowCount = dm.getRowCount();
        for(int i = rowCount-1; i >= 0; i--) {
            String userName = (String)dm.getValueAt(i, 1);
            String passWord = (String)dm.getValueAt(i, 2);
                    
            String typeAccount = (String)dm.getValueAt(i, 3);
            if(typeAccount == null) {
                typeAccount = new String("");
            }
            if(userName == null && passWord == null && typeAccount.equals("")) {
                
            } else if(userName == null || passWord == null || typeAccount.equals("")) {
                statusLabel.setForeground(Color.red);
                statusLabel.setText("Một số hàng không thể thêm vào cơ sở dữ liệu.");
            } else {
                acc.setUserName(userName);
                acc.setPassWord(LoginUI.getHash(LoginUI.getHash(passWord, "MD5"), "SHA-256"));
                switch(typeAccount){
                    case "Sinh viên":
                        acc.setAccountType("s");
                        break;
                    case "Giáo vụ":
                        acc.setAccountType("g");
                        break;
                    case "Quản trị":
                        acc.setAccountType("a");
                        break;
                }
                int errorCode = accountControl.insertAccount(acc);
                if(errorCode == 0) {
                    dm.removeRow(i);
                    statusLabel.setForeground(Color.green);
                    statusLabel.setText("Thêm thành viên thành công.");
                } else if(errorCode == 2627) {
                    statusLabel.setForeground(Color.red);
                    statusLabel.setText("Tên tài khoản " + acc.getUserName() + " đã tồn tại.");
                    break;
                }            
            }
        }  
        dm.setValueAt(dm.getRowCount(), dm.getRowCount()-1, 0);
    }//GEN-LAST:event_addButtonMousePressed

    private void toTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toTextFieldActionPerformed

    private void quickInsertButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quickInsertButtonMousePressed
        String from = fromTextField.getText();
        String to = toTextField.getText();
        if(!from.equals("") && !to.equals("")) {
            int errorCode = accountControl.insertGroupAccount(from, to);
            if(errorCode != 0) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Tạo nhanh tài khoản sinh viên thất bại.");
            } else {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Tạo nhanh tài khoản sinh viên thành công.");
            }
        }
    }//GEN-LAST:event_quickInsertButtonMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane addAccountScroll;
    private javax.swing.JTable addAccountTable;
    private manager.ui.ButtonLabel addButton;
    private javax.swing.JLabel fastCreateLabel;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JTextField fromTextField;
    private javax.swing.JPanel jPanel1;
    private manager.ui.ButtonLabel quickInsertButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTextField toTextField;
    // End of variables declaration//GEN-END:variables
}
