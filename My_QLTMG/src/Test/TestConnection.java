package Test;

import DAL.DataHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("=== KIỂM TRA KẾT NỐI MYSQL ===");

        DataHelper dataHelper = DataHelper.getInstance();

        // 1️⃣ Test connection
        try (Connection conn = dataHelper.getConnection()) {

            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Kết nối MySQL thành công!");
            }

            // 2️⃣ Test VERSION
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println("✓ MySQL Version: " + rs.getString(1));
            }

            // 3️⃣ Test DATABASE
            rs = stmt.executeQuery("SELECT DATABASE()");
            if (rs.next()) {
                System.out.println("✓ Database đang dùng: " + rs.getString(1));
            }

            // 4️⃣ Test TABLES
            System.out.println("\n✓ Danh sách bảng:");
            rs = stmt.executeQuery("SHOW TABLES");
            int i = 1;
            while (rs.next()) {
                System.out.println("  " + (i++) + ". " + rs.getString(1));
            }

        } catch (SQLException e) {
            System.err.println("✗ Lỗi kết nối / truy vấn MySQL");
            e.printStackTrace();
        }

        System.out.println("\n=== KẾT THÚC KIỂM TRA ===");
    }
}
