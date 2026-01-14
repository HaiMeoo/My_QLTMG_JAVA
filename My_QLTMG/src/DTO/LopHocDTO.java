package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LopHocDTO {

    private String IDLOP;
    private String TENLOP;
    private int SISO;
    private String IDGIAOVIEN;
    private String HOTENGIAOVIEN;

    // Constructor mặc định
    public LopHocDTO() {
    }

    // Constructor tương đương DataRow → ResultSet
    public LopHocDTO(ResultSet rs) throws SQLException {
        this.IDLOP = rs.getString("IDLOP");
        this.TENLOP = rs.getString("TENLOP");
        this.SISO = rs.getInt("SISO");
        this.IDGIAOVIEN = rs.getString("IDGIAOVIEN");
        this.HOTENGIAOVIEN = rs.getString("HOTENGIAOVIEN");
    }

    // Constructor đầy đủ tham số
    public LopHocDTO(String idLop, String tenLop, int siSo, String idGiaoVien, String hotenGiaovien) {
        this.IDLOP = idLop;
        this.TENLOP = tenLop;
        this.SISO = siSo;
        this.IDGIAOVIEN = idGiaoVien;
        this.HOTENGIAOVIEN = hotenGiaovien;
    }

    // Getter & Setter
    public String getIDLOP() {
        return IDLOP;
    }

    public void setIDLOP(String IDLOP) {
        this.IDLOP = IDLOP;
    }

    public String getTENLOP() {
        return TENLOP;
    }

    public void setTENLOP(String TENLOP) {
        this.TENLOP = TENLOP;
    }

    public int getSISO() {
        return SISO;
    }

    public void setSISO(int SISO) {
        this.SISO = SISO;
    }

    public String getIDGIAOVIEN() {
        return IDGIAOVIEN;
    }

    public void setIDGIAOVIEN(String IDGIAOVIEN) {
        this.IDGIAOVIEN = IDGIAOVIEN;
    }

    public String getHOTENGIAOVIEN() {
        return HOTENGIAOVIEN;
    }

    public void setHOTENGIAOVIEN(String HOTENGIAOVIEN) {
        this.HOTENGIAOVIEN = HOTENGIAOVIEN;
    }
}
