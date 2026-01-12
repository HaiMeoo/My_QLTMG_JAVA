package Test;

import DAL.DataHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== KIỂM TRA KẾT NỐI MYSQL ===");
        
        // Lấy instance của DataHelper
        DataHelper dataHelper = DataHelper.getInstance();
        
        // Kiểm tra kết nối
        if (dataHelper.isConnected()) {
            System.out.println("✓ Đã kết nối thành công đến MySQL!");
            System.out.println("Chuỗi kết nối: " + dataHelper.getConnectionString());
            
            // Thử truy vấn đơn giản
            try {
                // Kiểm tra version MySQL
                String versionQuery = "SELECT VERSION()";
                Object version = dataHelper.executeScalar(versionQuery, null);
                System.out.println("✓ Phiên bản MySQL: " + version);
                
                // Kiểm tra database hiện tại
                String dbQuery = "SELECT DATABASE()";
                Object dbName = dataHelper.executeScalar(dbQuery, null);
                System.out.println("✓ Database đang sử dụng: " + dbName);
                
                // Kiểm tra các bảng trong database
                System.out.println("\n✓ Danh sách các bảng trong database:");
                String tablesQuery = "SHOW TABLES";
                ResultSet tables = dataHelper.executeQuery(tablesQuery);
                
                int count = 0;
                while (tables.next()) {
                    count++;
                    System.out.println("  " + count + ". " + tables.getString(1));
                }
                
                if (count == 0) {
                    System.out.println("  Database chưa có bảng nào!");
                }
                
            } catch (SQLException e) {
                System.err.println("✗ Lỗi khi thực thi truy vấn:");
                e.printStackTrace();
            }
        } else {
            System.err.println("✗ Không thể kết nối đến MySQL!");
        }
        
        // Đóng kết nối
        dataHelper.closeConnection();
        System.out.println("\n=== KẾT THÚC KIỂM TRA ===");
    }
}