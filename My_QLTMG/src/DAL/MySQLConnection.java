package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {
    private static MySQLConnection instance;
    private Connection connection;
    
    // THÔNG TIN KẾT NỐI - ĐIỀU CHỈNH THEO CỦA BẠN
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_qltmg"; // Thay tên database
    private static final String USER_NAME = "root";
    private static final String PASSWORD = ""; // Để trống nếu không có password
    
    // Constructor private để đảm bảo Singleton
    private MySQLConnection() {}
    
    // Phương thức tạo kết nối
    Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // BƯỚC 1: Đăng ký Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // BƯỚC 2: Tạo kết nối
                connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Không tìm thấy MySQL Driver!", e);
            }
        }
        return connection;
    }
    
    // Phương thức getInstance theo Singleton pattern
    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }
    
    /**
     * PHƯƠNG THỨC KIỂM TRA KẾT NỐI
     */
    public static boolean testConnection() {
        System.out.println("=== KIEM TRA KET NOI MYSQL ===");
        System.out.println("Database URL: " + DB_URL);
        System.out.println("Username: " + USER_NAME);
        System.out.println("Password: " + (PASSWORD.isEmpty() ? "(empty)" : "***"));
        
        Connection conn = null;
        try {
            // Tạo kết nối mới để test
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("☑ Kết nối thành công!");
                System.out.println("☑ Test ket noi: THANH CONG");
                
                // Test thực thi câu lệnh
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                
                if (rs.next()) {
                    System.out.println("☑ Test query: THANH CONG");
                }
                
                // Đóng tài nguyên
                rs.close();
                stmt.close();
                return true;
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy MySQL Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Kết nối thất bại!");
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            // Đóng kết nối
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("☑ Đã đóng kết nối");
                } catch (SQLException e) {
                    System.out.println("❌ Lỗi khi đóng: " + e.getMessage());
                }
            }
        }
        
        return false;
    }
    
    /**
     * PHƯƠNG THỨC THỰC THI QUERY (dùng cho chương trình chính)
     */
    
    // Thực thi SELECT trả về ResultSet
    public ResultSet executeQuery(String query, Object... parameters) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
        }
        
        return stmt.executeQuery();
    }
    
    // Thực thi SELECT trả về List
    public List<Object[]> executeQueryList(String query, Object... parameters) throws SQLException {
        List<Object[]> resultList = new ArrayList<>();
        
        try (ResultSet rs = executeQuery(query, parameters)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                resultList.add(row);
            }
        }
        
        return resultList;
    }
    
    // Thực thi INSERT, UPDATE, DELETE
    public int executeUpdate(String query, Object... parameters) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
        }
        
        return stmt.executeUpdate();
    }
    
    // Thực thi query trả về một giá trị
    public Object executeScalar(String query, Object... parameters) throws SQLException {
        try (ResultSet rs = executeQuery(query, parameters)) {
            if (rs.next()) {
                return rs.getObject(1);
            }
        }
        return null;
    }
    
    /**
     * ĐÓNG KẾT NỐI
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("☑ Đã đóng kết nối");
            } catch (SQLException e) {
                System.out.println("❌ Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
    
    /**
     * MAIN METHOD - CHỈ DÙNG ĐỂ TEST KẾT NỐI
     */
    public static void main(String[] args) {
        // Chỉ gọi phương thức test kết nối
        testConnection();
    }
}