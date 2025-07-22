package com.cafe.util;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class XDialog {
    public static void alert(String message){
        XDialog.alert(message, "Thông báo!");
    }
      public static void alert(String message, String title) {
        // Tạo tùy chỉnh dialog có nút "Xác nhận"
        JOptionPane optionPane = new JOptionPane(
            message,
            JOptionPane.ERROR_MESSAGE,
            JOptionPane.DEFAULT_OPTION,
            null,
            new Object[]{"Xác nhận"}, // Đổi chữ nút tại đây
            "Xác nhận"
        );
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    
    public static boolean confirm(String message){
        return XDialog.confirm(message, "Xác nhận!");
    }
        public static boolean confirm(String message, String title) {
        Object[] options = {"Có", "Không"};
        int result = JOptionPane.showOptionDialog(
            null,
            message,
            title,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(String message){
        return XDialog.prompt(message, "Nhập vào!");
    }
    public static String prompt(String message, String title){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}