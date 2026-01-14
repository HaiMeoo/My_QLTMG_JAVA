package BLL;

import DAL.LopHocDAL;
import DTO.LopHocDTO;

import java.util.List;

public class LopHocBLL {

    private LopHocDAL lopHocDAL;

    public LopHocBLL() {
        lopHocDAL = new LopHocDAL();
    }

    // ========== Lấy danh sách lớp học ==========
    public List<LopHocDTO> getLopHocList() {
        List<LopHocDTO> lopHocList = lopHocDAL.loadLopHocList();
        if (lopHocList == null || lopHocList.isEmpty()) {
            System.out.println("Danh sách lớp học trống.");
        }
        return lopHocList;
    }

    // ========== Thêm lớp học ==========
    public void themLopHoc(String idLop, String tenLop, int siSo, String idGiaoVien) {
        if (isNullOrEmpty(idLop) || isNullOrEmpty(tenLop) || isNullOrEmpty(idGiaoVien)) {
            throw new IllegalArgumentException("Thông tin lớp học không được để trống.");
        }

        lopHocDAL.themLopHoc(idLop, tenLop, siSo, idGiaoVien);
    }

    // ========== Lấy giáo viên chưa có lớp ==========
    public List<Object[]> getGiaoVienChuaCoLop() {
        return lopHocDAL.getGiaoVienChuaCoLop();
    }

    // ========== Xóa lớp học ==========
    public void xoaLopHoc(String idLop) {
        if (isNullOrEmpty(idLop)) {
            throw new IllegalArgumentException("Mã lớp học không được để trống.");
        }

        lopHocDAL.xoaLopHoc(idLop);
    }

    // ========== Sửa lớp học ==========
    public void suaLopHoc(String idLop, String tenLop, int siSo, String idGiaoVien) {
        if (isNullOrEmpty(idLop) || isNullOrEmpty(tenLop) || isNullOrEmpty(idGiaoVien)) {
            throw new IllegalArgumentException("Thông tin lớp học không được để trống.");
        }

        lopHocDAL.suaLopHoc(idLop, tenLop, siSo, idGiaoVien);
    }

    // ========== Tìm kiếm lớp học ==========
    public List<LopHocDTO> timKiemLopHoc(String timKiem) {
        if (isNullOrEmpty(timKiem)) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống.");
        }

        return lopHocDAL.getLopHocByTenID(timKiem);
    }

    // ========== Hàm hỗ trợ ==========
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
