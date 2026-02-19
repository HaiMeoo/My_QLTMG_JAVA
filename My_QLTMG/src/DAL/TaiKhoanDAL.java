package DAL;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import DTO.TaiKhoanDTO;

public class TaiKhoanDAL {
    private Connection conn;
    
    public TaiKhoanDAL() {
        conn = DataHelper.getInstance().getConnection();
    }
    
    /**
     * Lấy danh sách tất cả tài khoản
     */
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        List<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        String sql = "SELECT IDTAIKHOAN, TENTAIKHOAN, MATKHAU FROM TAIKHOAN";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO(
                    rs.getString("IDTAIKHOAN"),
                    rs.getString("TENTAIKHOAN"),
                    rs.getString("MATKHAU")
                );
                taiKhoanList.add(taiKhoan);
            }
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        
        return taiKhoanList;
    }
    
    /**
     * Lấy tài khoản theo ID
     */
    public TaiKhoanDTO getTaiKhoanById(String idTaiKhoan) {
        String sql = "SELECT IDTAIKHOAN, TENTAIKHOAN, MATKHAU FROM TAIKHOAN WHERE IDTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idTaiKhoan);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                        rs.getString("IDTAIKHOAN"),
                        rs.getString("TENTAIKHOAN"),
                        rs.getString("MATKHAU")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy tài khoản theo ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Đăng nhập
     */
    public boolean login(String username, String password) {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE TENTAIKHOAN = ? AND MATKHAU = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng nhập: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Thêm tài khoản mới
     */
    public boolean addTaiKhoan(TaiKhoanDTO taiKhoan) {
        return addTaiKhoan(taiKhoan.getIdTaiKhoan(), taiKhoan.getTenTaiKhoan(), taiKhoan.getMatKhau());
    }
    
    public boolean addTaiKhoan(String idTaiKhoan, String tenTaiKhoan, String matKhau) {
        String sql = "INSERT INTO TAIKHOAN (IDTAIKHOAN, TENTAIKHOAN, MATKHAU) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idTaiKhoan);
            pstmt.setString(2, tenTaiKhoan);
            pstmt.setString(3, matKhau);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Cập nhật tài khoản
     */
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan) {
        return updateTaiKhoan(taiKhoan.getIdTaiKhoan(), taiKhoan.getTenTaiKhoan(), taiKhoan.getMatKhau());
    }
    
    public boolean updateTaiKhoan(String idTaiKhoan, String tenTaiKhoan, String matKhau) {
        String sql = "UPDATE TAIKHOAN SET TENTAIKHOAN = ?, MATKHAU = ? WHERE IDTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenTaiKhoan);
            pstmt.setString(2, matKhau);
            pstmt.setString(3, idTaiKhoan);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật tài khoản: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Xóa tài khoản
     */
    public boolean deleteTaiKhoan(String idTaiKhoan) {
        String sql = "DELETE FROM TAIKHOAN WHERE IDTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idTaiKhoan);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa tài khoản: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Kiểm tra tên tài khoản đã tồn tại chưa
     */
    public boolean isTenTaiKhoanExists(String tenTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE TENTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenTaiKhoan);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra tên tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Kiểm tra ID tài khoản đã tồn tại chưa
     */
    public boolean isIdTaiKhoanExists(String idTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE IDTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idTaiKhoan);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra ID tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Đổi mật khẩu
     */
    public boolean changePassword(String idTaiKhoan, String newPassword) {
        String sql = "UPDATE TAIKHOAN SET MATKHAU = ? WHERE IDTAIKHOAN = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, idTaiKhoan);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi đổi mật khẩu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Tìm kiếm tài khoản theo tên
     */
    public List<TaiKhoanDTO> searchTaiKhoanByTen(String keyword) {
        List<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        String sql = "SELECT IDTAIKHOAN, TENTAIKHOAN, MATKHAU FROM TAIKHOAN WHERE TENTAIKHOAN LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TaiKhoanDTO taiKhoan = new TaiKhoanDTO(
                        rs.getString("IDTAIKHOAN"),
                        rs.getString("TENTAIKHOAN"),
                        rs.getString("MATKHAU")
                    );
                    taiKhoanList.add(taiKhoan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        
        return taiKhoanList;
    }
    
    public List<TaiKhoanDTO> timKiemTheoMaHoacTen(String key) {
        List<TaiKhoanDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE IDTAIKHOAN LIKE ? OR TENTAIKHOAN LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TaiKhoanDTO(
                    rs.getString("IDTAIKHOAN"),
                    rs.getString("TENTAIKHOAN"),
                    rs.getString("MATKHAU")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    /**
     * Lấy tổng số tài khoản
     */
    public int getTotalTaiKhoan() {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm số tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * Kiểm tra kết nối
     */
    public boolean testConnection() {
        try {
            return conn != null && !conn.isClosed() && conn.isValid(5);
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra kết nối: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Đóng kết nối
     */
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
        }
    }
}