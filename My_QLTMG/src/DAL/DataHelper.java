package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private static DataHelper instance;
    private Connection connection;

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/qltmg?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    private DataHelper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("✓ Kết nối MySQL thành công!");
            System.out.println("DB URL: " + DB_URL);

            // ===== KIỂM TRA DATABASE HIỆN TẠI =====
            Statement st = connection.createStatement();

            ResultSet rsDB = st.executeQuery("SELECT DATABASE()");
            if (rsDB.next()) {
                System.out.println("Đang dùng database: " + rsDB.getString(1));
            }

            // ===== KIỂM TRA SỐ LƯỢNG GIÁO VIÊN =====
            ResultSet rsCount = st.executeQuery("SELECT COUNT(*) FROM giaovien");
            if (rsCount.next()) {
                System.out.println("Count GV trong Java: " + rsCount.getInt(1));
            }

        } catch (Exception e) {
            System.err.println("✗ Lỗi kết nối MySQL!");
            e.printStackTrace();
        }
    }

    // ================= SINGLETON =================
    public static DataHelper getInstance() {
        if (instance == null) {
            instance = new DataHelper();
        }
        return instance;
    }

    // ================= CONNECTION =================
    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Đã đóng kết nối MySQL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getConnectionString() {
        return DB_URL;
    }

    // ================= QUERY =================
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public ResultSet executeQuery(String sql, Object[] params) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps.executeQuery();
    }

    public int executeUpdate(String sql, Object[] params) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps.executeUpdate();
    }

    public Object executeScalar(String sql, Object[] params) throws SQLException {
        ResultSet rs = executeQuery(sql, params);
        if (rs.next()) {
            return rs.getObject(1);
        }
        return null;
    }

    public List<Object[]> executeQueryToList(String sql, Object[] params) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = executeQuery(sql, params);
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();

        while (rs.next()) {
            Object[] row = new Object[colCount];
            for (int i = 0; i < colCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            list.add(row);
        }
        return list;
    }
}
