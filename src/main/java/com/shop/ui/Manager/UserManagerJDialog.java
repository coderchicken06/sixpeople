/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.shop.ui.Manager;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.shop.dao.UserDAO;
import com.shop.dao.impl.UserDAOImpl;
import com.shop.entity.User;
import com.shop.util.XDialog;
import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author DELL
 */
public class UserManagerJDialog extends JFrame {

    /**
     * Creates new form UserJDialog
     */
    public UserManagerJDialog(Frame parent) {
        super("");
        initComponents();
    }

    UserDAO dao = new UserDAOImpl();
    List<User> items = List.of();

    
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
    }

    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getUsername(),
                item.getPassword(),
                item.getFullname(),
                item.getPhoto(),
                item.isManager() ? "Quản lý" : "Nhân viên",
                item.isEnabled() ? "Hoạt động" : "Tạm dừng",
                false
            };
            model.addRow(rowData);
        });
    }

    
    public void edit() {
        User entity = items.get(jTable1.getSelectedRow());
        this.setForm(entity);
        this.setEditable(true);
        tabs.setSelectedIndex(1);
    }

    
    public void checkAll() {
        this.setCheckedAll(true);
    }

    
    public void uncheckAll() {
        this.setCheckedAll(false);
    }

    private void setCheckedAll(boolean checked) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(checked, i, 6);
        }
    }

    
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if ((Boolean) jTable1.getValueAt(i, 6)) {
                    dao.deleteById(items.get(i).getUsername());
                }
            }
            this.fillToTable();
        }
    }

    
    public void setForm(User entity) {
        txtUsername4.setText(entity.getUsername());
        txtPassword4.setText(entity.getPassword());
        txtConfirm4.setText(entity.getPassword());
        rdoEnabled4.setIndex(entity.isEnabled() ? 0 : 1);
        txtFullname4.setText(entity.getFullname());
        pnlPhoto4.setPreferredSize(new java.awt.Dimension(169, 169));
        pnlPhoto4.setIcon("/icons/" + entity.getPhoto());
        pnlPhoto4.revalidate();
        pnlPhoto4.repaint();
        rdoManager4.setIndex(entity.isManager() ? 0 : 1);
    }

    
    public User getForm() {
        User entity = new User();
        entity.setUsername(txtUsername4.getText());
        entity.setPassword(new String(txtPassword4.getPassword()));
        entity.setEnabled(rdoEnabled4.getIndex() == 0);
        entity.setFullname(txtFullname4.getText());
        entity.setPhoto(pnlPhoto4.getIcon());
        entity.setManager(rdoManager4.getIndex() == 0);
        return entity;
    }

    
    public void create() {
        User entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    
    public void update() {
        User entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    
    public void delete() {
        if (XDialog.confirm("Bạn thực sự muốn xóa?")) {
            String id = txtUsername4.getText();
            dao.deleteById(id);
            this.fillToTable();
            this.clear();
        }
    }

    
    public void clear() {
        this.setForm(new User());
        this.setEditable(false);
    }

    
    public void setEditable(boolean editable) {
        txtUsername4.setEnabled(!editable);
        btnCreate1.setEnabled(!editable);
        btnUpdate1.setEnabled(editable);
        btnDelete1.setEnabled(editable);

        int rowCount = jTable1.getRowCount();
        btnMoveFirst1.setEnabled(editable && rowCount > 0);
        btnMovePrevious1.setEnabled(editable && rowCount > 0);
        btnMoveNext1.setEnabled(editable && rowCount > 0);
        btnMoveLast1.setEnabled(editable && rowCount > 0);
    }

    
    public void moveFirst() {
        this.moveTo(0);
    }

    
    public void movePrevious() {
        this.moveTo(jTable1.getSelectedRow() - 1);
    }

    
    public void moveNext() {
        this.moveTo(jTable1.getSelectedRow() + 1);
    }

    
    public void moveLast() {
        this.moveTo(jTable1.getRowCount() - 1);
    }

    
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= jTable1.getRowCount()) {
            this.moveFirst();
        } else {
            jTable1.clearSelection();
            jTable1.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageJPanel1 = new com.cafe.ui.component.ImageJPanel();
        jPanel22 = new javax.swing.JPanel();
        btnCheckAll2 = new javax.swing.JButton();
        btnUncheckAll2 = new javax.swing.JButton();
        btnDeleteCheckedItems2 = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnCheckAll = new javax.swing.JButton();
        btnUncheckAll = new javax.swing.JButton();
        btnDeleteCheckedItems = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        btnCreate1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btnMoveFirst1 = new javax.swing.JButton();
        btnMovePrevious1 = new javax.swing.JButton();
        btnMoveNext1 = new javax.swing.JButton();
        btnMoveLast1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtUsername4 = new javax.swing.JTextField();
        txtFullname4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtPassword4 = new javax.swing.JPasswordField();
        txtConfirm4 = new javax.swing.JPasswordField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rdoManager4 = new com.cafe.ui.component.RadioJPanel();
        rdoEnabled4 = new com.cafe.ui.component.RadioJPanel();
        pnlPhoto4 = new com.cafe.ui.component.ImageJPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        btnCheckAll1 = new javax.swing.JButton();
        btnUncheckAll1 = new javax.swing.JButton();
        btnDeleteCheckedItems1 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        btnCheckAll3 = new javax.swing.JButton();
        btnUncheckAll3 = new javax.swing.JButton();
        btnDeleteCheckedItems3 = new javax.swing.JButton();

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2, 2));

        btnCheckAll2.setText("Chọn tất cả");
        btnCheckAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAll2ActionPerformed(evt);
            }
        });
        jPanel22.add(btnCheckAll2);

        btnUncheckAll2.setText("Bỏ chọn tất cả");
        btnUncheckAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUncheckAll2ActionPerformed(evt);
            }
        });
        jPanel22.add(btnUncheckAll2);

        btnDeleteCheckedItems2.setText("Xóa các mục chọn");
        btnDeleteCheckedItems2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCheckedItems2ActionPerformed(evt);
            }
        });
        jPanel22.add(btnDeleteCheckedItems2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý tài khoản");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2, 2));

        btnCheckAll.setText("Chọn tất cả");
        btnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAllActionPerformed(evt);
            }
        });
        jPanel3.add(btnCheckAll);

        btnUncheckAll.setText("Bỏ chọn tất cả");
        btnUncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUncheckAllActionPerformed(evt);
            }
        });
        jPanel3.add(btnUncheckAll);

        btnDeleteCheckedItems.setText("Xóa các mục chọn");
        btnDeleteCheckedItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCheckedItemsActionPerformed(evt);
            }
        });
        jPanel3.add(btnDeleteCheckedItems);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel9.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel16.setLayout(new java.awt.GridLayout(1, 0, 2, 2));

        btnCreate1.setText("Tạo mới");
        btnCreate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate1ActionPerformed(evt);
            }
        });
        jPanel16.add(btnCreate1);

        btnUpdate1.setText("Cập nhật");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });
        jPanel16.add(btnUpdate1);

        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        jPanel16.add(btnDelete1);

        btnClear1.setText("Nhập mới");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });
        jPanel16.add(btnClear1);

        jPanel9.add(jPanel16, java.awt.BorderLayout.LINE_START);

        jPanel17.setLayout(new java.awt.GridLayout(1, 0, 2, 2));

        btnMoveFirst1.setText("|<");
        btnMoveFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveFirst1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnMoveFirst1);

        btnMovePrevious1.setText("<<");
        btnMovePrevious1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovePrevious1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnMovePrevious1);

        btnMoveNext1.setText(">>");
        btnMoveNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveNext1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnMoveNext1);

        btnMoveLast1.setText(">|");
        btnMoveLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveLast1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnMoveLast1);

        jPanel9.add(jPanel17, java.awt.BorderLayout.LINE_END);
        jPanel9.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel19.setLayout(new java.awt.GridLayout(0, 2, 5, 5));

        jLabel25.setText("Tên đăng nhập");
        jLabel25.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel25);

        jLabel26.setText("Họ và tên");
        jLabel26.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel26);
        jPanel19.add(txtUsername4);
        jPanel19.add(txtFullname4);

        jLabel27.setText("Mật khẩu");
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel27);

        jLabel28.setText("Xác nhận mật khẩu");
        jLabel28.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel28);
        jPanel19.add(txtPassword4);
        jPanel19.add(txtConfirm4);

        jLabel29.setText("Vai trò");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel29);

        jLabel30.setText("Trạng thái");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel19.add(jLabel30);

        rdoManager4.setItems(new String[] {"Quản lý", "Nhân viên"});
        jPanel19.add(rdoManager4);

        rdoEnabled4.setItems(new String[] {"Hoạt động", "Tạm dừng"});
        jPanel19.add(rdoEnabled4);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPhoto4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPhoto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        tabs.addTab("DANH SÁCH", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("tab2", jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2, 2));

        btnCheckAll1.setText("Chọn tất cả");
        btnCheckAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAll1ActionPerformed(evt);
            }
        });
        jPanel21.add(btnCheckAll1);

        btnUncheckAll1.setText("Bỏ chọn tất cả");
        btnUncheckAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUncheckAll1ActionPerformed(evt);
            }
        });
        jPanel21.add(btnUncheckAll1);

        btnDeleteCheckedItems1.setText("Xóa các mục chọn");
        btnDeleteCheckedItems1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCheckedItems1ActionPerformed(evt);
            }
        });
        jPanel21.add(btnDeleteCheckedItems1);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2, 2));

        btnCheckAll3.setText("Chọn tất cả");
        btnCheckAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAll3ActionPerformed(evt);
            }
        });
        jPanel23.add(btnCheckAll3);

        btnUncheckAll3.setText("Bỏ chọn tất cả");
        btnUncheckAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUncheckAll3ActionPerformed(evt);
            }
        });
        jPanel23.add(btnUncheckAll3);

        btnDeleteCheckedItems3.setText("Xóa các mục chọn");
        btnDeleteCheckedItems3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCheckedItems3ActionPerformed(evt);
            }
        });
        jPanel23.add(btnDeleteCheckedItems3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(414, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(415, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(200, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(200, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void btnMoveLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveLast1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMoveLast1ActionPerformed

    private void btnMoveNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveNext1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMoveNext1ActionPerformed

    private void btnMovePrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovePrevious1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMovePrevious1ActionPerformed

    private void btnMoveFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveFirst1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMoveFirst1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreate1ActionPerformed

    private void btnDeleteCheckedItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCheckedItemsActionPerformed
        // TODO add your handling code here:
        this.deleteCheckedItems();
    }//GEN-LAST:event_btnDeleteCheckedItemsActionPerformed

    private void btnUncheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUncheckAllActionPerformed
        // TODO add your handling code here:
        this.uncheckAll();
    }//GEN-LAST:event_btnUncheckAllActionPerformed

    private void btnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAllActionPerformed
        // TODO add your handling code here:
        this.checkAll();
    }//GEN-LAST:event_btnCheckAllActionPerformed

    private void btnCheckAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAll1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckAll1ActionPerformed

    private void btnUncheckAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUncheckAll1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUncheckAll1ActionPerformed

    private void btnDeleteCheckedItems1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCheckedItems1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteCheckedItems1ActionPerformed

    private void btnCheckAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAll2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckAll2ActionPerformed

    private void btnUncheckAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUncheckAll2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUncheckAll2ActionPerformed

    private void btnDeleteCheckedItems2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCheckedItems2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteCheckedItems2ActionPerformed

    private void btnCheckAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAll3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckAll3ActionPerformed

    private void btnUncheckAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUncheckAll3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUncheckAll3ActionPerformed

    private void btnDeleteCheckedItems3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCheckedItems3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteCheckedItems3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        UserManagerJDialog frame = new UserManagerJDialog(new javax.swing.JFrame()); // ✅ constructor mới
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hoặc DISPOSE_ON_CLOSE
        frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckAll;
    private javax.swing.JButton btnCheckAll1;
    private javax.swing.JButton btnCheckAll2;
    private javax.swing.JButton btnCheckAll3;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnCreate1;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDeleteCheckedItems;
    private javax.swing.JButton btnDeleteCheckedItems1;
    private javax.swing.JButton btnDeleteCheckedItems2;
    private javax.swing.JButton btnDeleteCheckedItems3;
    private javax.swing.JButton btnMoveFirst1;
    private javax.swing.JButton btnMoveLast1;
    private javax.swing.JButton btnMoveNext1;
    private javax.swing.JButton btnMovePrevious1;
    private javax.swing.JButton btnUncheckAll;
    private javax.swing.JButton btnUncheckAll1;
    private javax.swing.JButton btnUncheckAll2;
    private javax.swing.JButton btnUncheckAll3;
    private javax.swing.JButton btnUpdate1;
    private com.cafe.ui.component.ImageJPanel imageJPanel1;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private com.cafe.ui.component.ImageJPanel pnlPhoto4;
    private com.cafe.ui.component.RadioJPanel rdoEnabled4;
    private com.cafe.ui.component.RadioJPanel rdoManager4;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JPasswordField txtConfirm4;
    private javax.swing.JTextField txtFullname4;
    private javax.swing.JPasswordField txtPassword4;
    private javax.swing.JTextField txtUsername4;
    // End of variables declaration//GEN-END:variables

}
