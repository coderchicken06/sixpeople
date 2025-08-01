package com.shop.util;

import com.shop.entity.SP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class XJdbc {

    private static Connection connection;

    // Mở kết nối nếu chưa mở hoặc đã đóng
    //@return Kết nối đã sẵn sàng
    public static Connection openConnection() {
        var driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        var dburl = "jdbc:sqlserver://Phamthanhdat;database=ShopGUCCHI;encrypt=true;trustServerCertificate=true;";

        var username = "sa";
        var password = "05082006";
        try {
            if (!XJdbc.isReady()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(dburl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //Đóng kết nối
    public static void closeConnection() {
        try {
            if (XJdbc.isReady()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Kiểm tra kết nối đã sẵn sàng hay chưa
    //@return true nếu kết nối đã được mở
    public static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Thao tác dữ liệu
    //@param sql câu lệnh SQL (INSERT, UPDATE, DELETE)
    //@param values các giá trị cung cấp cho các tham số trong SQL
    //@return số lượng bản ghi đã thực hiện
    //@throws RuntimeException không thực thi được câu lệnh SQL
    public static int executeUpdate(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Truy vấn dữ liệu
    //@param sql câu lệnh SQL (SELECT)
    //@param values các giá trị cung cấp cho các tham số trong SQL
    //@return tập kết quả truy vấn
    //@throws RuntimeException không thực thi được câu lệnh SQL
    public static ResultSet executeQuery(String sql, Object... values) {
        try {
            var stmt = XJdbc.getStmt(sql, values);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Truy vấn một giá trị
    //@param <T> kiểu dữ liệu kết quả
    //@param sql câu lệnh SQL (SELECT)
    //@param values các giá trị cung cấp cho các tham số trong SQL
    //@return giá trị truy vấn hoặc null
    //@throws RuntimeException không thực thi được câu lệnh SQL
    public static <T> T getValue(String sql, Object... values) {
        try {
            var resultSet = XJdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Tạo PreparedStatement từ câu lệnh SQL/PROC
    //@param sql câu lệnh SQL/PROC
    //@param values các giá trị cung cấp cho các tham số trong SQL/PROC
    //@return đối tượng đã tạo
    //@throws SQLException không tạo được PreparedStatement
    private static PreparedStatement getStmt(String sql, Object... values) throws SQLException {
        var conn = XJdbc.openConnection();
        var stmt = sql.trim().startsWith("{") ? conn.prepareCall(sql) : conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }

    public static void main(String[] args) {
            demo1();
            demo2();
            demo3();
        try {
            var conn = XJdbc.openConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công!");
                // Query all drinks
                String sql = "SELECT * FROM Drinks";
                List<SP> drinks = XQuery.getBeanList(SP.class, sql);
                System.out.println("Danh sách đồ uống:");
                for (SP drink : drinks) {
                    System.out.printf("ID: %s, Name: %s, Price: %.2f, Discount: %.2f, Image: %s, Available: %b, Category: %s%n",
                            drink.getId(), drink.getName(), drink.getUnitPrice(), drink.getDiscount(),
                            drink.getImage(), drink.isAvailable(), drink.getCategoryId());
                }
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        } finally {
            XJdbc.closeConnection();
        }
    }

    private static void demo1() {
        String sql = "SELECT * FROM Drinks WHERE UnitPrice BETWEEN ? AND ?";
        var rs = XJdbc.executeQuery(sql, 1.5, 5.0);
    }

    private static void demo2() {
        String sql = "SELECT max(UnitPrice) FROM Drinks WHERE UnitPrice > ?";
        var maxPrice = XJdbc.getValue(sql, 1.5);
    }

    private static void demo3() {
        String sql = "DELETE FROM Drinks WHERE UnitPrice < ?";
        var count = XJdbc.executeUpdate(sql, 0.0);
    }
}
