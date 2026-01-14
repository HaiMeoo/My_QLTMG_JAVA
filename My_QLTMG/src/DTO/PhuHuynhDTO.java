package DTO;

public class PhuHuynhDTO {

    private String IDPHUHUYNH;
    private String TENPHUHUYNH;
    private String SDT;
    private String EMAIL;
    private String DIACHI;
    private String HOTENHOCSINH;
    private String IDHOCSINH;

    // ==============================
    // Constructor rỗng
    // ==============================
    public PhuHuynhDTO() {
    }

    // ==============================
    // Constructor đầy đủ (phù hợp INSERT / UPDATE)
    // ==============================
    public PhuHuynhDTO(String idPhuHuynh, String tenPhuHuynh,
                       String sdt, String email, String diaChi,
                       String idHocSinh, String hoTenHocSinh) {

        this.IDPHUHUYNH = idPhuHuynh;
        this.TENPHUHUYNH = tenPhuHuynh;
        this.SDT = sdt;
        this.EMAIL = email;
        this.DIACHI = diaChi;
        this.IDHOCSINH = idHocSinh;
        this.HOTENHOCSINH = hoTenHocSinh;
    }

    // ==============================
    // Getter & Setter
    // ==============================
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getHOTENHOCSINH() {
        return HOTENHOCSINH;
    }

    public void setHOTENHOCSINH(String HOTENHOCSINH) {
        this.HOTENHOCSINH = HOTENHOCSINH;
    }

    public String getIDHOCSINH() {
        return IDHOCSINH;
    }

    public void setIDHOCSINH(String IDHOCSINH) {
        this.IDHOCSINH = IDHOCSINH;
    }
}
