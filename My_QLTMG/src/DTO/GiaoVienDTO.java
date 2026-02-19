package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiaoVienDTO {

    private String idGiaoVien;
    private String hoTen;
    private int gioiTinh; // 0 = Nữ, 1 = Nam
    private String ngaySinh;
    private String diaChi;
    private String cccd;
    private String sdt;

    public GiaoVienDTO() {}

    public GiaoVienDTO(ResultSet rs) throws SQLException {
        this.idGiaoVien = rs.getString("IDGIAOVIEN");
        this.hoTen = rs.getString("HOTENGIAOVIEN");
        this.gioiTinh = rs.getInt("GIOITINH");
        this.ngaySinh = rs.getString("NGAYSINH");
        this.diaChi = rs.getString("DIACHI");
        this.cccd = rs.getString("CCCD");
        this.sdt = rs.getString("SDT");
    }

    // ===== GETTER / SETTER =====
    public String getIdGiaoVien() { return idGiaoVien; }
    public void setIdGiaoVien(String idGiaoVien) { this.idGiaoVien = idGiaoVien; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public int getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(int gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    @Override
    public String toString() {
        return this.getHoTen();
    }

    
}

