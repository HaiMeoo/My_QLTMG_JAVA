package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class DiemDanhDTO {

    private String idDiemDanh;
    private String idHocSinh;
    private String hoTenHocSinh;
    private String idLop;
    private String tenLop;
    private Date ngayDiemDanh;
    private String trangThai;
    private String lyDo;
    private String idGiaoVien;
    private String hoTenGiaoVien;

    // ===== Constructor rỗng =====
    public DiemDanhDTO() {
    }

    // ===== Constructor từ ResultSet =====
    public DiemDanhDTO(ResultSet rs) throws SQLException {
        this.idDiemDanh     = rs.getString("IDDIEMDANH");
        this.idHocSinh      = rs.getString("IDHOCSINH");
        this.hoTenHocSinh   = rs.getString("HOTENHOCSINH");
        this.idLop          = rs.getString("IDLOP");
        this.tenLop         = rs.getString("TENLOP");
        this.ngayDiemDanh   = rs.getDate("NGAYDIEMDANH");
        this.trangThai      = rs.getString("TRANGTHAI");
        this.lyDo           = rs.getString("LYDO");
        this.idGiaoVien     = rs.getString("IDGIAOVIEN");
        this.hoTenGiaoVien  = rs.getString("HOTENGIAOVIEN");
    }

    // ===== Constructor đầy đủ =====
    public DiemDanhDTO(String idDiemDanh, String idHocSinh, String idLop,
                       Date ngayDiemDanh, String trangThai,
                       String lyDo, String idGiaoVien) {

        this.idDiemDanh   = idDiemDanh;
        this.idHocSinh    = idHocSinh;
        this.idLop        = idLop;
        this.ngayDiemDanh = ngayDiemDanh;
        this.trangThai    = trangThai;
        this.lyDo         = lyDo;
        this.idGiaoVien   = idGiaoVien;
    }

    // ===== Getter & Setter =====
    public String getIdDiemDanh() {
        return idDiemDanh;
    }

    public void setIdDiemDanh(String idDiemDanh) {
        this.idDiemDanh = idDiemDanh;
    }

    public String getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(String idHocSinh) {
        this.idHocSinh = idHocSinh;
    }

    public String getHoTenHocSinh() {
        return hoTenHocSinh;
    }

    public void setHoTenHocSinh(String hoTenHocSinh) {
        this.hoTenHocSinh = hoTenHocSinh;
    }

    public String getIdLop() {
        return idLop;
    }

    public void setIdLop(String idLop) {
        this.idLop = idLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Date getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(Date ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getIdGiaoVien() {
        return idGiaoVien;
    }

    public void setIdGiaoVien(String idGiaoVien) {
        this.idGiaoVien = idGiaoVien;
    }

    public String getHoTenGiaoVien() {
        return hoTenGiaoVien;
    }

    public void setHoTenGiaoVien(String hoTenGiaoVien) {
        this.hoTenGiaoVien = hoTenGiaoVien;
    }
}
