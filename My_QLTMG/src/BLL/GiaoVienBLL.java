package BLL;

import DAL.GiaoVienDAL;
import DTO.GiaoVienDTO;
import java.util.List;

public class GiaoVienBLL {

    private GiaoVienDAL giaoVienDAL;

    public GiaoVienBLL() {
        giaoVienDAL = new GiaoVienDAL();
    }

    // Lấy danh sách từ DB
    public List<GiaoVienDTO> getAll() {
        return giaoVienDAL.loadGiaoVienList();
    }

    // Thêm
    public void them(GiaoVienDTO gv) {
        if (gv.getIdGiaoVien().isEmpty() || gv.getHoTen().isEmpty()) {
            throw new IllegalArgumentException("Không được để trống dữ liệu");
        }

        giaoVienDAL.themGiaoVien(
                gv.getIdGiaoVien(),
                gv.getHoTen(),
                gv.getGioiTinh(),
                gv.getNgaySinh(),
                gv.getDiaChi(),
                gv.getCccd(),
                gv.getSdt()
        );
    }

    // Sửa
    public void sua(GiaoVienDTO gv) {
        giaoVienDAL.suaGiaoVien(
                gv.getIdGiaoVien(),
                gv.getHoTen(),
                gv.getGioiTinh(),
                gv.getNgaySinh(),
                gv.getDiaChi(),
                gv.getCccd(),
                gv.getSdt()
        );
    }

    // Xóa
    public void xoa(String idGiaoVien) {
        giaoVienDAL.xoaGiaoVien(idGiaoVien);
    }

    // Tìm
    public List<GiaoVienDTO> tim(String key) {
        return giaoVienDAL.getGiaoVienByTenID(key);
    }
}
