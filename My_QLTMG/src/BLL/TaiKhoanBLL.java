package BLL;

import java.util.List;
import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;

public class TaiKhoanBLL {
    private TaiKhoanDAL taiKhoanDAL;
    
    public TaiKhoanBLL() {
        taiKhoanDAL = new TaiKhoanDAL();
    }
    
    /**
     * Lấy danh sách tất cả tài khoản
     */
    public List<TaiKhoanDTO> getTaiKhoanList() {
        List<TaiKhoanDTO> taiKhoanList = taiKhoanDAL.getAllTaiKhoan();
        
        if (taiKhoanList == null || taiKhoanList.isEmpty()) {
            System.out.println("Danh sách tài khoản trống.");
        }
        
        return taiKhoanList;
    }
    
    /**
     * Đăng nhập
     */
    public boolean dangNhap(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Tài khoản và mật khẩu không được để trống.");
        }
        
        return taiKhoanDAL.login(username, password);
    }
    
    /**
     * Thêm tài khoản mới
     */
    public boolean themTaiKhoan(String idTaiKhoan, String tenTaiKhoan, String matKhau) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty() || 
            tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty() || 
            matKhau == null || matKhau.trim().isEmpty()) {
            throw new IllegalArgumentException("Thông tin tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.addTaiKhoan(idTaiKhoan, tenTaiKhoan, matKhau);
    }
    
    /**
     * Thêm tài khoản mới từ DTO
     */
    public boolean themTaiKhoan(TaiKhoanDTO taiKhoan) {
        if (taiKhoan == null) {
            throw new IllegalArgumentException("Đối tượng tài khoản không được null.");
        }
        
        return taiKhoanDAL.addTaiKhoan(taiKhoan);
    }
    
    /**
     * Xóa tài khoản
     */
    public boolean xoaTaiKhoan(String idTaiKhoan) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.deleteTaiKhoan(idTaiKhoan);
    }
    
    /**
     * Sửa tài khoản
     */
    public boolean suaTaiKhoan(String idTaiKhoan, String tenTaiKhoan, String matKhau) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty() || 
            tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty() || 
            matKhau == null || matKhau.trim().isEmpty()) {
            throw new IllegalArgumentException("Thông tin tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.updateTaiKhoan(idTaiKhoan, tenTaiKhoan, matKhau);
    }
    
    /**
     * Sửa tài khoản từ DTO
     */
    public boolean suaTaiKhoan(TaiKhoanDTO taiKhoan) {
        if (taiKhoan == null) {
            throw new IllegalArgumentException("Đối tượng tài khoản không được null.");
        }
        
        return taiKhoanDAL.updateTaiKhoan(taiKhoan);
    }
    
    /**
     * Lấy tài khoản theo ID
     */
    public TaiKhoanDTO getTaiKhoanById(String idTaiKhoan) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.getTaiKhoanById(idTaiKhoan);
    }
    
    /**
     * Kiểm tra tên tài khoản đã tồn tại chưa
     */
    public boolean isTenTaiKhoanExists(String tenTaiKhoan) {
        if (tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.isTenTaiKhoanExists(tenTaiKhoan);
    }
    
    /**
     * Kiểm tra ID tài khoản đã tồn tại chưa
     */
    public boolean isIdTaiKhoanExists(String idTaiKhoan) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài khoản không được để trống.");
        }
        
        return taiKhoanDAL.isIdTaiKhoanExists(idTaiKhoan);
    }
    
    /**
     * Đổi mật khẩu
     */
    public boolean doiMatKhau(String idTaiKhoan, String matKhauMoi) {
        if (idTaiKhoan == null || idTaiKhoan.trim().isEmpty() || 
            matKhauMoi == null || matKhauMoi.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài khoản và mật khẩu mới không được để trống.");
        }
        
        return taiKhoanDAL.changePassword(idTaiKhoan, matKhauMoi);
    }
    
    /**
     * Tìm kiếm tài khoản theo tên
     */
    public List<TaiKhoanDTO> timKiemTaiKhoan(String tuKhoa) {
        if (tuKhoa == null) {
            tuKhoa = "";
        }
        
        return taiKhoanDAL.searchTaiKhoanByTen(tuKhoa.trim());
    }
    
    /**
     * Lấy tổng số tài khoản
     */
    public int getTongSoTaiKhoan() {
        return taiKhoanDAL.getTotalTaiKhoan();
    }
    
    /**
     * Kiểm tra kết nối database
     */
    public boolean kiemTraKetNoi() {
        return taiKhoanDAL.testConnection();
    }
    
    /**
     * Đóng kết nối
     */
    public void dongKetNoi() {
        taiKhoanDAL.close();
    }
}