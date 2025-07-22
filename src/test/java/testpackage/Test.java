/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.cafe.entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.cafe.util.XJdbc;
import com.cafe.util.XQuery;

/**
 *
 * @author VAN TRONG
 */
public class Test {

    public static void main(String[] args) {
        try {
            // Ensure database connection
            var conn = XJdbc.openConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
                return;
            }

            // 1. Thêm mới
            System.out.println("1. Thêm mới hai danh mục:");
            String insertSql = "INSERT INTO Categories (Id, Name) VALUES (?, ?)";
            XJdbc.executeUpdate(insertSql, "C03", "Loại 3");// thay doi thong tin tai day
            XJdbc.executeUpdate(insertSql, "C04", "Loại 4");// thay doi thong tin tai day
            System.out.println("Đã thêm C03 và C04 thành công!");

            // 2. Truy vấn nhiều bản ghi với ResultSet
            System.out.println("\n2. Truy vấn nhiều bản ghi với ResultSet (Name LIKE '%Loại%'):");
            String querySql = "SELECT * FROM Categories WHERE Name LIKE ?";
            ResultSet rs = XJdbc.executeQuery(querySql, "%Loại%");
            while (rs.next()) {
                String id = rs.getString("Id");
                String name = rs.getString("Name");
                System.out.printf("Id: %s, Name: %s%n", id, name);
            }
            rs.close();

            // 3. Truy xuất nhiều bản ghi và chuyển đổi sang List<Category>
            System.out.println("\n3. Truy xuất nhiều bản ghi và chuyển đổi sang List<Category> (Name LIKE '%Loại%'):");
            String beanListSql = "SELECT * FROM Categories WHERE Name LIKE ?";
            List<Category> beans = XQuery.getBeanList(Category.class, beanListSql, "%Loại%");
            for (Category category : beans) {
                System.out.printf("Id: %s, Name: %s%n", category.getId(), category.getName());
            }

            // 4. Truy xuất một bản ghi và chuyển đổi sang Category
            System.out.println("\n4. Truy xuất một bản ghi và chuyển đổi sang Category (Id = 'C04'):");// thay doi thong tin tai day
            String singleBeanSql = "SELECT * FROM Categories WHERE Id = ?";
            Category bean = XQuery.getSingleBean(Category.class, singleBeanSql, "C04");// thay doi thong tin tai day
            if (bean != null) {
                System.out.printf("Id: %s, Name: %s%n", bean.getId(), bean.getName());
            } else {
                System.out.println("Không tìm thấy danh mục với Id = 'C04'");// thay doi thong tin tai day
            }

            // 5. Truy vấn 1 giá trị
            System.out.println("\n5. Truy vấn Id lớn nhất (Name LIKE '%Loại%'):");
            String valueSql = "SELECT MAX(Id) FROM Categories WHERE Name LIKE ?";
            String maxId = XJdbc.getValue(valueSql, "%Loại%");
            System.out.println("Id lớn nhất: " + (maxId != null ? maxId : "Không có dữ liệu"));
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        } finally {
            XJdbc.closeConnection();
        }
    }
}
