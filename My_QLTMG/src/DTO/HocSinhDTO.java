package DTO;

import java.util.Date;

public class HocSinhDTO {

    private String IDHOCSINH;
    private String HOTENHOCSINH;
    private int GIOITINH;
    private Date NGAYSINH;
    private String DIACHI;
    private String IDLOP;
    private String TENLOP;

    // ===== Constructor =====
    public HocSinhDTO() {
    }

    public HocSinhDTO(String IDHOCSINH, String HOTENHOCSINH, int GIOITINH,
                      Date NGAYSINH, String DIACHI, String IDLOP, String TENLOP) {
        this.IDHOCSINH = IDHOCSINH;
        this.HOTENHOCSINH = HOTENHOCSINH;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.IDLOP = IDLOP;
        this.TENLOP = TENLOP;
    }

    // ===== Getter & Setter =====
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

    public int getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(int GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

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

    // ===== Getter hiển thị giới tính =====
    public String getGIOITINH_TEXT() {
        if (GIOITINH == 1) return "Nữ";
        if (GIOITINH == 0) return "Nam";
        return "Khác";
    }
    @Override
    public String toString() {
        return HOTENHOCSINH;
    }
}
