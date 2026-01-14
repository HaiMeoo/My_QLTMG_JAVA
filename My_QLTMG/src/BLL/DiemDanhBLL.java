package BLL;

import DAL.DiemDanhDAL;
import DTO.DiemDanhDTO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DiemDanhBLL {

    private DiemDanhDAL diemDanhDAL;

    public DiemDanhBLL() {
        diemDanhDAL = new DiemDanhDAL();
    }

    // ========== Load danh sách điểm danh ==========
    public List<DiemDanhDTO> loadDiemDanhList() {
        List<DiemDanhDTO> list = diemDanhDAL.loadDiemDanhList();
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách điểm danh trống.");
        }
        return list;
    }

    // ========== Thêm điểm danh ==========
    public void themDiemDanh(String idDiemDanh, String idHocSinh, String idLop,
                             String ngayDiemDanh, String trangThai,
                             String lyDo, String idGiaoVien) {

        if (isEmpty(idDiemDanh) || isEmpty(idHocSinh) || isEmpty(idLop)
                || isEmpty(trangThai) || isEmpty(idGiaoVien)) {
            throw new IllegalArgumentException("Thông tin điểm danh không được để trống.");
        }

        String ngayHopLe = chuanHoaNgay(ngayDiemDanh);

        diemDanhDAL.themDiemDanh(
            idDiemDanh,
            idHocSinh,
            idLop,
            ngayHopLe,
            trangThai,
            lyDo,
            idGiaoVien
        );
    }

    // ========== Sửa điểm danh ==========
    public void suaDiemDanh(String idDiemDanh, String idHocSinh, String idLop,
                            String ngayDiemDanh, String trangThai,
                            String lyDo, String idGiaoVien) {

        if (isEmpty(idDiemDanh) || isEmpty(idHocSinh) || isEmpty(idLop)
                || isEmpty(trangThai) || isEmpty(idGiaoVien)) {
            throw new IllegalArgumentException("Thông tin điểm danh không được để trống.");
        }

        String ngayHopLe = chuanHoaNgay(ngayDiemDanh);

        diemDanhDAL.suaDiemDanh(
            idDiemDanh,
            idHocSinh,
            idLop,
            ngayHopLe,
            trangThai,
            lyDo,
            idGiaoVien
        );
    }

    // ========== Xóa điểm danh ==========
    public void xoaDiemDanh(String idDiemDanh) {
        if (isEmpty(idDiemDanh)) {
            throw new IllegalArgumentException("Mã điểm danh không hợp lệ.");
        }
        diemDanhDAL.xoaDiemDanh(idDiemDanh);
    }

    // ========== Tìm kiếm điểm danh ==========
    public List<DiemDanhDTO> timKiemDiemDanh(String timKiem) {
        if (isEmpty(timKiem)) {
            throw new IllegalArgumentException("Nội dung tìm kiếm không được để trống.");
        }

        List<DiemDanhDTO> list = diemDanhDAL.getDiemDanhByTenID(timKiem);

        if (list.isEmpty()) {
            System.out.println("Không tìm thấy kết quả nào phù hợp.");
        }

        return list;
    }

    // ================== HÀM HỖ TRỢ ==================

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private String chuanHoaNgay(String ngay) {
        SimpleDateFormat[] formats = {
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("dd/MM/yyyy")
        };

        for (SimpleDateFormat f : formats) {
            try {
                Date d = f.parse(ngay);
                return new SimpleDateFormat("yyyy-MM-dd").format(d);
            } catch (ParseException ignored) {}
        }

        throw new IllegalArgumentException(
            "Ngày điểm danh không hợp lệ. Định dạng đúng: yyyy-MM-dd hoặc dd/MM/yyyy."
        );
    }
}
