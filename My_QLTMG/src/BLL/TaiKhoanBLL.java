package BLL;

import java.util.List;
import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;

public class TaiKhoanBLL {

    private TaiKhoanDAL taiKhoanDAL;

    public TaiKhoanBLL() {
        taiKhoanDAL = new TaiKhoanDAL();
    }

    /* =========================
       LẤY DANH SÁCH
    ========================= */
    public List<TaiKhoanDTO> getTaiKhoanList() {
        return taiKhoanDAL.getAllTaiKhoan();
    }

    /* =========================
       ĐĂNG NHẬP
    ========================= */
    public boolean dangNhap(String username, String password) {

        if (username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Tên tài khoản không được để trống");

        if (password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("Mật khẩu không được để trống");

        return taiKhoanDAL.login(username.trim(), password.trim());
    }

    /* =========================
       THÊM TÀI KHOẢN
    ========================= */
    public void themTaiKhoan(String id, String ten, String matKhau) {

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("Mã tài khoản không được để trống");

        if (ten == null || ten.trim().isEmpty())
            throw new IllegalArgumentException("Tên tài khoản không được để trống");

        if (matKhau == null || matKhau.trim().isEmpty())
            throw new IllegalArgumentException("Mật khẩu không được để trống");

        if (taiKhoanDAL.isIdTaiKhoanExists(id))
            throw new IllegalArgumentException("Mã tài khoản đã tồn tại");

        if (taiKhoanDAL.isTenTaiKhoanExists(ten))
            throw new IllegalArgumentException("Tên tài khoản đã tồn tại");

        taiKhoanDAL.addTaiKhoan(id.trim(), ten.trim(), matKhau.trim());
    }

    public void themTaiKhoan(TaiKhoanDTO tk) {

        if (tk == null)
            throw new IllegalArgumentException("Dữ liệu tài khoản không hợp lệ");

        themTaiKhoan(
                tk.getIdTaiKhoan(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau()
        );
    }

    /* =========================
       SỬA TÀI KHOẢN
    ========================= */
    public void suaTaiKhoan(String id, String ten, String matKhau) {

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("Chưa chọn tài khoản cần sửa");

        if (ten == null || ten.trim().isEmpty())
            throw new IllegalArgumentException("Tên tài khoản không được để trống");

        if (matKhau == null || matKhau.trim().isEmpty())
            throw new IllegalArgumentException("Mật khẩu không được để trống");

        taiKhoanDAL.updateTaiKhoan(id.trim(), ten.trim(), matKhau.trim());
    }

    public void suaTaiKhoan(TaiKhoanDTO tk) {

        if (tk == null)
            throw new IllegalArgumentException("Dữ liệu tài khoản không hợp lệ");

        suaTaiKhoan(
                tk.getIdTaiKhoan(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau()
        );
    }

    /* =========================
       XÓA TÀI KHOẢN
    ========================= */
    public void xoaTaiKhoan(String id) {

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("Chưa chọn tài khoản cần xóa");

        taiKhoanDAL.deleteTaiKhoan(id.trim());
    }

    /* =========================
       LẤY THEO ID
    ========================= */
    public TaiKhoanDTO getTaiKhoanById(String id) {

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("Mã tài khoản không hợp lệ");

        return taiKhoanDAL.getTaiKhoanById(id.trim());
    }

    /* =========================
       TÌM KIẾM (MÃ + TÊN)
    ========================= */
    public List<TaiKhoanDTO> timKiemTaiKhoan(String keyword) {

        if (keyword == null || keyword.trim().isEmpty())
            throw new IllegalArgumentException("Vui lòng nhập mã hoặc tên tài khoản");

        return taiKhoanDAL.timKiemTheoMaHoacTen(keyword.trim());
    }

    /* =========================
       ĐỔI MẬT KHẨU
    ========================= */
    public void doiMatKhau(String id, String matKhauMoi) {

        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("Mã tài khoản không hợp lệ");

        if (matKhauMoi == null || matKhauMoi.trim().isEmpty())
            throw new IllegalArgumentException("Mật khẩu mới không được để trống");

        taiKhoanDAL.changePassword(id.trim(), matKhauMoi.trim());
    }

    /* =========================
       THỐNG KÊ – HỆ THỐNG
    ========================= */
    public int getTongSoTaiKhoan() {
        return taiKhoanDAL.getTotalTaiKhoan();
    }

    public boolean kiemTraKetNoi() {
        return taiKhoanDAL.testConnection();
    }

    public void dongKetNoi() {
        taiKhoanDAL.close();
    }
}
