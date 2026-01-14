package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChitietNGHDTO {

    private String IDHOCSINH;
    private String HOTENHOCSINH;
    private String IDPHUHUYNH;
    private String TENPHUHUYNH;
    private String NGUOIGIAMHO;

    // ==============================
    // CONSTRUCTOR RỖNG
    // ==============================
    public ChitietNGHDTO() {
    }

    // ==============================
    // CONSTRUCTOR TỪ RESULTSET
    // ==============================
    public ChitietNGHDTO(ResultSet rs) throws SQLException {
        this.IDHOCSINH = rs.getString("IDHOCSINH");
        this.HOTENHOCSINH = rs.getString("HOTENHOCSINH");
        this.IDPHUHUYNH = rs.getString("IDPHUHUYNH");
        this.TENPHUHUYNH = rs.getString("TENPHUHUYNH");
        this.NGUOIGIAMHO = rs.getString("NGUOIGIAMHO");
    }

    // ==============================
    // CONSTRUCTOR DÙNG KHI INSERT
    // ==============================
    public ChitietNGHDTO(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        this.IDHOCSINH = idHocSinh;
        this.IDPHUHUYNH = idPhuHuynh;
        this.NGUOIGIAMHO = nguoiGiamHo;
    }

    // ==============================
    // GETTER & SETTER
    // ==============================
    public String getIDHOCSINH() {
        return IDHOCSINH;
    }

    public void setIDHOCSINH(String IDHOCSINH) {
        this.IDHOCSINH = IDHOCSINH;
    }

    public String getHOTENHOCSINH() {
        return HOTENHOCSINH;
    }

    public void setHOTENHOCSINH(String HOTENHOCSINH) {
        this.HOTENHOCSINH = HOTENHOCSINH;
    }

    public String getIDPHUHUYNH() {
        return IDPHUHUYNH;
    }

    public void setIDPHUHUYNH(String IDPHUHUYNH) {
        this.IDPHUHUYNH = IDPHUHUYNH;
    }

    public String getTENPHUHUYNH() {
        return TENPHUHUYNH;
    }

    public void setTENPHUHUYNH(String TENPHUHUYNH) {
        this.TENPHUHUYNH = TENPHUHUYNH;
    }

    public String getNGUOIGIAMHO() {
        return NGUOIGIAMHO;
    }

    public void setNGUOIGIAMHO(String NGUOIGIAMHO) {
        this.NGUOIGIAMHO = NGUOIGIAMHO;
    }
}
