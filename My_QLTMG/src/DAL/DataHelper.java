package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    private static DataHelper instance;
    private Connection connection;
    
    // Thông tin kết nối MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_qltmg";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    
    private DataHelper() {
        try {
            // Đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối MySQL thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối MySQL!");
            e.printStackTrace();
        }
    }
    
    // Singleton pattern
    public static DataHelper getInstance() {
        if (instance == null) {
            synchronized (DataHelper.class) {
                if (instance == null) {
                    instance = new DataHelper();
                }
            }
        }
        return instance;
    }
    
    // Lấy chuỗi kết nối
    public String getConnectionString() {
        return DB_URL + "?user=" + USER_NAME + "&password=" + PASSWORD;
    }
    
    // Thực thi câu lệnh SELECT và trả về ResultSet
    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
    
    // Thực thi câu lệnh SELECT với tham số
    public ResultSet executeQuery(String query, Object[] parameters) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
        }
        
        return pstmt.executeQuery();
    }
    
    // Thực thi câu lệnh SELECT và trả về List<Object[]>
    public List<Object[]> executeQueryToList(String query, Object[] parameters) throws SQLException {
        List<Object[]> resultList = new ArrayList<>();
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
            
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                resultList.add(row);
            }
        }
        
        return resultList;
    }
    
    // Thực thi câu lệnh không trả về kết quả (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query, Object[] parameters) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
            
            return pstmt.executeUpdate();
        }
    }
    
    // Thực thi câu lệnh và trả về một giá trị (ExecuteScalar)
    public Object executeScalar(String query, Object[] parameters) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        }
    }
    
    // Lấy connection để sử dụng trực tiếp
    public Connection getConnection() {
        return connection;
    }
    
    // Đóng kết nối
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Đã đóng kết nối MySQL!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng kết nối!");
            e.printStackTrace();
        }
    }
    
    // Kiểm tra kết nối
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
}