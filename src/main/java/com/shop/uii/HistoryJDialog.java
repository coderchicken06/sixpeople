/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.shop.uii;

import java.awt.Frame;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.shop.dao.BillDAO;
import com.shop.dao.impl.BillDAOImpl;
import com.shop.entity.Bill;
import com.shop.util.TimeRange;
import com.shop.util.XAuth;
import com.shop.util.XDate;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Dung Si Ban Tron
 */
public class HistoryJDialog extends JFrame {

    /**
     * Creates new form BillJDialog
     */
    public HistoryJDialog(Frame parent) {
        super("Lich su");
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/images/Shop_logo.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBills = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBegin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        cboTimeRanges = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử bán hàng của bạn");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Thẻ số", "Thời điểm tạo phiếu", "Thời điểm thanh toán", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBills.setRowHeight(25);
        tblBills.setRowMargin(2);
        tblBills.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBills.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBills.setShowGrid(true);
        tblBills.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBills);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Từ ngày: ");
        jPanel3.add(jLabel1);

        txtBegin.setColumns(8);
        jPanel3.add(txtBegin);

        jLabel2.setText("Đến ngày: ");
        jPanel3.add(jLabel2);

        txtEnd.setColumns(8);
        jPanel3.add(txtEnd);

        btnFilter.setText("Lọc");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        jPanel3.add(btnFilter);

        cboTimeRanges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Quý này", "Năm nay" }));
        cboTimeRanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimeRangesActionPerformed(evt);
            }
        });
        jPanel3.add(cboTimeRanges);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.showBillJDialog();
        }
    }//GEN-LAST:event_tblBillsMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void cboTimeRangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimeRangesActionPerformed
        // TODO add your handling code here:
        this.selectTimeRange();
    }//GEN-LAST:event_cboTimeRangesActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        this.fillBills();
    }//GEN-LAST:event_btnFilterActionPerformed

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
            java.util.logging.Logger.getLogger(HistoryJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
        BillJDialog frame = new BillJDialog(new javax.swing.JFrame()); // ✅ constructor mới
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hoặc DISPOSE_ON_CLOSE
        frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> cboTimeRanges;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBills;
    private javax.swing.JTextField txtBegin;
    private javax.swing.JTextField txtEnd;
    // End of variables declaration//GEN-END:variables

    BillDAO billDao = new BillDAOImpl();
    List<Bill> bills = List.of();

    
    public void open() {
        this.setLocationRelativeTo(null);
        this.selectTimeRange();
        this.fillBills();
    }

   
    public void fillBills() {
        Date begin = XDate.parse(txtBegin.getText());
        Date end = XDate.parse(txtEnd.getText());
        bills = billDao.findByUserAndTimeRange(XAuth.user.getUsername(), begin, end);

        DefaultTableModel model = (DefaultTableModel) tblBills.getModel();
        model.setRowCount(0);
        String[] statuses = {"Servicing", "Completed", "Canceled"};
        bills.forEach(b -> {
            String checkout = XDate.format(b.getCheckout(), "HH:mm:ss dd-MM-yyyy");
            Object[] row = {
                b.getId(),
                "Card #" + b.getCardId(),
                XDate.format(b.getCheckin(), "HH:mm:ss dd-MM-yyyy"),
                checkout,
                statuses[b.getStatus()]
            };
            model.addRow(row);
        });
    }

   
    public void selectTimeRange() {
        TimeRange range = TimeRange.today();
        switch (cboTimeRanges.getSelectedIndex()) {
            case 0 ->
                range = TimeRange.today();
            case 1 ->
                range = TimeRange.thisWeek();
            case 2 ->
                range = TimeRange.thisMonth();
            case 3 ->
                range = TimeRange.thisQuarter();
            case 4 ->
                range = TimeRange.thisYear();
        }
        txtBegin.setText(XDate.format(range.getBegin()));
        txtEnd.setText(XDate.format(range.getEnd()));
        this.fillBills();
    }

   
    public void showBillJDialog() {
        Bill bill = bills.get(tblBills.getSelectedRow());

        BillJDialog dialog = new BillJDialog((Frame) this.getOwner());
        dialog.setBill(bill);
        dialog.setVisible(true);

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                HistoryJDialog.this.fillBills();
            }
        });
    }
}
