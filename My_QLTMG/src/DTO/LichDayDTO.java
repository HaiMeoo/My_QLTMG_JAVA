package DTO;

public class LichDayDTO {

    private String IDLICH;
    private String NGAYDAY;
    private String GIOBATDAU;
    private String GIOKETTHUC;
    private String NOIDUNG;
    private String IDGIAOVIEN;
    private String IDLOP;

    // Thêm 2 field để hiển thị thông tin join
    private String HOTENGIAOVIEN;
    private String TENLOP;

    // Constructor rỗng
    public LichDayDTO() {}

    // Constructor đầy đủ
    public LichDayDTO(String idLich, String ngayDay, String gioBatDau,
                      String gioKetThuc, String noiDung,
                      String idGiaoVien, String idLop) {
        this.IDLICH = idLich;
        this.NGAYDAY = ngayDay;
        this.GIOBATDAU = gioBatDau;
        this.GIOKETTHUC = gioKetThuc;
        this.NOIDUNG = noiDung;
        this.IDGIAOVIEN = idGiaoVien;
        this.IDLOP = idLop;
    }

    // ===== Getter / Setter =====
    public String getIDLICH() { return IDLICH; }
    public void setIDLICH(String IDLICH) { this.IDLICH = IDLICH; }

    public String getNGAYDAY() { return NGAYDAY; }
    public void setNGAYDAY(String NGAYDAY) { this.NGAYDAY = NGAYDAY; }

    public String getGIOBATDAU() { return GIOBATDAU; }
    public void setGIOBATDAU(String GIOBATDAU) { this.GIOBATDAU = GIOBATDAU; }

    public String getGIOKETTHUC() { return GIOKETTHUC; }
    public void setGIOKETTHUC(String GIOKETTHUC) { this.GIOKETTHUC = GIOKETTHUC; }

    public String getNOIDUNG() { return NOIDUNG; }
    public void setNOIDUNG(String NOIDUNG) { this.NOIDUNG = NOIDUNG; }

    public String getIDGIAOVIEN() { return IDGIAOVIEN; }
    public void setIDGIAOVIEN(String IDGIAOVIEN) { this.IDGIAOVIEN = IDGIAOVIEN; }

    public String getIDLOP() { return IDLOP; }
    public void setIDLOP(String IDLOP) { this.IDLOP = IDLOP; }

    public String getHOTENGIAOVIEN() { return HOTENGIAOVIEN; }
    public void setHOTENGIAOVIEN(String HOTENGIAOVIEN) { this.HOTENGIAOVIEN = HOTENGIAOVIEN; }

    public String getTENLOP() { return TENLOP; }
    public void setTENLOP(String TENLOP) { this.TENLOP = TENLOP; }
}
