package DTO;

public class TaiKhoanDTO {
    private String idTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    
    // Constructor mặc định
    public TaiKhoanDTO() {
    }
    
    // Constructor với tham số
    public TaiKhoanDTO(String idTaiKhoan, String tenTaiKhoan, String matKhau) {
        this.idTaiKhoan = idTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }
    
    // Getter và Setter
    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }
    
    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }
    
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }
    
    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    // Phương thức toString() để hiển thị thông tin
    @Override
    public String toString() {
        return "TaiKhoanDTO [idTaiKhoan=" + idTaiKhoan + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + "]";
    }
}