package DTO;

import java.util.Date;

public class BaoCaoSucKhoeDTO {

    private String IDBAOCAO;
    private Date NGAYKHAM;
    private String CANNANG;
    private String CHIEUCAO;
    private String TINHTRANG;
    private String IDHOCSINH;
    private String HOTENHOCSINH;
    private String IDLOP;
    private String TENLOP;
    private String IDGIAOVIEN;
    private String HOTENGIAOVIEN;

    // Constructor rỗng
    public BaoCaoSucKhoeDTO() {}

    // Constructor đầy đủ (dùng cho BLL)
    public BaoCaoSucKhoeDTO(String idBaoCao, Date ngayKham, String canNang, String chieuCao,
                            String tinhTrang, String idHocSinh, String idLop, String idGiaoVien) {
        this.IDBAOCAO = idBaoCao;
        this.NGAYKHAM = ngayKham;
        this.CANNANG = canNang;
        this.CHIEUCAO = chieuCao;
        this.TINHTRANG = tinhTrang;
        this.IDHOCSINH = idHocSinh;
        this.IDLOP = idLop;
        this.IDGIAOVIEN = idGiaoVien;
    }

    // ===== Getter / Setter =====
    public String getIDBAOCAO() { return IDBAOCAO; }
    public void setIDBAOCAO(String IDBAOCAO) { this.IDBAOCAO = IDBAOCAO; }

    public Date getNGAYKHAM() { return NGAYKHAM; }
    public void setNGAYKHAM(Date NGAYKHAM) { this.NGAYKHAM = NGAYKHAM; }

    public String getCANNANG() { return CANNANG; }
    public void setCANNANG(String CANNANG) { this.CANNANG = CANNANG; }

    public String getCHIEUCAO() { return CHIEUCAO; }
    public void setCHIEUCAO(String CHIEUCAO) { this.CHIEUCAO = CHIEUCAO; }

    public String getTINHTRANG() { return TINHTRANG; }
    public void setTINHTRANG(String TINHTRANG) { this.TINHTRANG = TINHTRANG; }

    public String getIDHOCSINH() { return IDHOCSINH; }
    public void setIDHOCSINH(String IDHOCSINH) { this.IDHOCSINH = IDHOCSINH; }

    public String getHOTENHOCSINH() { return HOTENHOCSINH; }
    public void setHOTENHOCSINH(String HOTENHOCSINH) { this.HOTENHOCSINH = HOTENHOCSINH; }

    public String getIDLOP() { return IDLOP; }
    public void setIDLOP(String IDLOP) { this.IDLOP = IDLOP; }

    public String getTENLOP() { return TENLOP; }
    public void setTENLOP(String TENLOP) { this.TENLOP = TENLOP; }

    public String getIDGIAOVIEN() { return IDGIAOVIEN; }
    public void setIDGIAOVIEN(String IDGIAOVIEN) { this.IDGIAOVIEN = IDGIAOVIEN; }

    public String getHOTENGIAOVIEN() { return HOTENGIAOVIEN; }
    public void setHOTENGIAOVIEN(String HOTENGIAOVIEN) { this.HOTENGIAOVIEN = HOTENGIAOVIEN; }
}
