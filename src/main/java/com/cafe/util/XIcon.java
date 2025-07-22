package com.cafe.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class XIcon {

    public static ImageIcon getIcon(String path) {
        if(!path.contains("/") && !path.contains("\\")){ // resource name
            return XIcon.getIcon("/poly/cafe/icons/" + path);
        }
        if(path.startsWith("/")){ // resource path
            return new ImageIcon(XIcon.class.getResource(path));
        }
        return new ImageIcon(path);
    }
    
    //Đọc icon theo kích thước
    //path đường dẫn file hoặc tài nguyên
    //width chiều rộng
    //height chiều cao
    public static ImageIcon getIcon(String path, int width, int height) {
        Image image = getIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    
    //Thay đổi icon của JLabel
    //label JLabel cần thay đổi
    //path đường dẫn file hoặc tài nguyên
    public static void setIcon(JLabel label, String path) {
        label.setIcon(XIcon.getIcon(path, label.getWidth(), label.getHeight()));
    }
 
    //Thay đổi icon của JLabel
    //label JLabel cần thay đổi
    //file file icon
    public static void setIcon(JLabel label, File file) {
        XIcon.setIcon(label, file.getAbsolutePath());
    }

    //Sao chép file vào thư mục với tên file mới là duy nhất
    //fromFile file cần sao chép
    //folder thư mục đích
    //File đã sao chép
    public static File copyTo(File fromFile, String folder) {
        String fileExt = fromFile.getName().substring(fromFile.getName().lastIndexOf("."));
        File toFile = new File(folder, XStr.getKey() + fileExt);
        toFile.getParentFile().mkdirs();
        try {
            Files.copy(fromFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return toFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static File copyTo(File fromFile) {
        return copyTo(fromFile, "files");
    }
}
