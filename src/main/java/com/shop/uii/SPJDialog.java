/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.shop.uii;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import com.shop.dao.CategoryDAO;
import com.shop.dao.impl.BillDetailDAOImpl;
import com.shop.dao.impl.CategoryDAOImpl;
import com.shop.dao.impl.SPDAOImpl;
import com.shop.entity.Bill;
import com.shop.entity.BillDetail;
import com.shop.entity.Category;
import com.shop.entity.SP;
import com.shop.util.XDialog;
import javax.swing.ImageIcon;
import com.shop.dao.SPDAO;

import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author Dung Si Ban Tron
 */
public class SPJDialog extends JFrame {

    /**
     * Creates new form AddDrinkToBillJDialog
     */
    public SPJDialog(Frame parent) {
        super("");
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/images/Shop_logo.png")).getImage());
    }

    @Setter
    Bill bill;

    List<Category> categories = List.of();
    List<SP> drinks = List.of();

    
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillCategories();
        this.fillDrinks();
    }

  
    public void fillCategories() {
        CategoryDAO categoryDao = new CategoryDAOImpl();
        categories = categoryDao.findAll();
        DefaultTableModel model = (DefaultTableModel) tblCategories.getModel();
        model.setRowCount(0);
        categories.forEach(d -> model.addRow(new Object[]{d.getName()}));
        tblCategories.setRowSelectionInterval(0, 0);
    }

 
    public void fillDrinks() {
        Category category = categories.get(tblCategories.getSelectedRow());

        SPDAO drinkDao = new SPDAOImpl();
        drinks = drinkDao.findByCategoryId(category.getId());

        DefaultTableModel model = (DefaultTableModel) tblDrinks.getModel();
        model.setRowCount(0);
        drinks.forEach(d -> {
            Object[] row = {
                d.getId(),
                d.getName(),
                String.format("$%.1f", d.getUnitPrice()),
                String.format("%.0f%%", d.getDiscount() * 100)
            };
            model.addRow(row);
        });
    }


    public void addDrinkToBill() {
        SP drink = drinks.get(tblDrinks.getSelectedRow());

        String input = XDialog.prompt("Số lượng?");
        if (input != null && input.trim().length() > 0) {
            try {
                int quantity = Integer.parseInt(input.trim());
                if (quantity <= 0) {
                    XDialog.alert("Vui lòng nhập số lượng lớn hơn 0");
                    return;
                }

                BillDetail detail = new BillDetail();
                detail.setBillId(bill.getId());
                detail.setDrinkId(drink.getId());
                detail.setUnitPrice(drink.getUnitPrice());
                detail.setDiscount(drink.getDiscount());
                detail.setQuantity(quantity);

                new BillDetailDAOImpl().create(detail);
            } catch (NumberFormatException e) {
                XDialog.alert("Số lượng phải là số nguyên hợp lệ");
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDrinks = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategories = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chọn sản phẩm");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel3.setLayout(new java.awt.BorderLayout(5, 0));

        tblDrinks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên sản phẩm", "Đơn giá", "Giảm giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDrinks.setRowHeight(25);
        tblDrinks.setRowMargin(2);
        tblDrinks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDrinks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDrinks.setShowGrid(true);
        tblDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDrinksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDrinks);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 194));

        tblCategories.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblCategories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategories.setRowHeight(27);
        tblCategories.setRowMargin(2);
        tblCategories.setSelectionBackground(new java.awt.Color(255, 255, 0));
        tblCategories.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCategories);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.LINE_START);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDrinksMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.addDrinkToBill();
        }
    }//GEN-LAST:event_tblDrinksMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void tblCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriesMouseClicked
        // TODO add your handling code here:
        this.fillDrinks();
    }//GEN-LAST:event_tblCategoriesMouseClicked

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
            java.util.logging.Logger.getLogger(SPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        SPJDialog frame = new SPJDialog(new javax.swing.JFrame()); // ✅ constructor mới
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hoặc DISPOSE_ON_CLOSE
        frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCategories;
    private javax.swing.JTable tblDrinks;
    // End of variables declaration//GEN-END:variables

}
