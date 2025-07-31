/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.uii;

import com.shop.ui.Manager.BillManagerJDialog;
import com.shop.ui.Manager.CardManagerJDialog;
import com.shop.ui.Manager.CategoryManagerJDialog;
import com.shop.ui.Manager.SPManagerJDialog;
import com.shop.ui.Manager.RevenueManagerJDialog;
import com.shop.ui.Manager.UserManagerJDialog;
import com.shop.util.XDialog;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Dung Si Ban Tron
 */
public interface PolyShopController {

    /**
     * Hiển thị cửa sổ chào Hiển thị cửa sổ đăng nhập Hiển thị thông tin user
     * đăng nhập Disable/Enable các thành phần tùy thuộc vào vai trò đăng nhập
     */
    void init();

    default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
        }
    }

    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showLoginJDialog(JFrame frame) {
        this.showJDialog(new WelcomeJDialog(frame, true));
    }

    default void showWelcomeJDialog(JFrame frame) {
        this.showJDialog(new LoginJDialog(frame, true));
    }

    default void showChangePasswordJDialog(JFrame frame) {
        this.showJDialog(new ChangePasswordJDialog(frame, true));
    }

    default void showSalesJDialog(JFrame frame) {
        new SalesJDialog(frame).setVisible(true);
    }

    default void showHistoryJDialog(JFrame frame) {
        new HistoryJDialog(frame).setVisible(true);
    }

    default void showDrinkManagerJDialog(JFrame frame) {
        new SPManagerJDialog(frame).setVisible(true);
    }

    default void showCategoryManagerJDialog(JFrame frame) {
       new CategoryManagerJDialog(frame).setVisible(true);
    }

    default void showCardManagerJDialog(JFrame frame) {
        new CardManagerJDialog(frame).setVisible(true);
    }

    default void showBillManagerJDialog(JFrame frame) {
        new BillManagerJDialog(frame).setVisible(true);
    }

    default void showUserManagerJDialog(JFrame frame) {
        new UserManagerJDialog(frame).setVisible(true);
    }

    default void showRevenueManagerJDialog(JFrame frame) {
        new RevenueManagerJDialog(frame).setVisible(true);
    }
}
