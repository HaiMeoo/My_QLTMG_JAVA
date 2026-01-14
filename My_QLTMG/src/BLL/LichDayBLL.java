package BLL;

import DAL.LichDayDAL;
import DTO.LichDayDTO;
import java.util.List;

public class LichDayBLL {

    private LichDayDAL lichDayDAL;

    public LichDayBLL() {
        lichDayDAL = new LichDayDAL();
    }

    // ===== Lấy danh sách =====
    public List<LichDayDTO> getLichDayList() {
        List<LichDayDTO> list = lichDayDAL.loadLichDayList();
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách lịch dạy trống.");
        }
        return list;
    }

    // ===== Thêm =====
    public void themLichDay(String idLich, String ngayDay, String gioBatDau,
                            String gioKetThuc, String noiDung,
                            String idGiaoVien, String idLop) {

        if (isNullOrEmpty(idLich) || isNullOrEmpty(ngayDay) || isNullOrEmpty(gioBatDau)
            || isNullOrEmpty(gioKetThuc) || isNullOrEmpty(noiDung)
            || isNullOrEmpty(idGiaoVien) || isNullOrEmpty(idLop)) {
            throw new IllegalArgumentException("Thông tin lịch dạy không được để trống.");
        }

        LichDayDTO ld = new LichDayDTO(idLich, ngayDay, gioBatDau, gioKetThuc,
                                        noiDung, idGiaoVien, idLop);
        lichDayDAL.themLichDay(ld);
    }

    // ===== Xóa =====
    public void xoaLichDay(String idLich) {
        if (isNullOrEmpty(idLich)) {
            throw new IllegalArgumentException("Mã lịch không được để trống.");
        }
        lichDayDAL.xoaLichDay(idLich);
    }

    // ===== Sửa =====
    public void suaLichDay(String idLich, String ngayDay, String gioBatDau,
                           String gioKetThuc, String noiDung,
                           String idGiaoVien, String idLop) {
        if (isNullOrEmpty(idLich) || isNullOrEmpty(ngayDay) || isNullOrEmpty(gioBatDau)
            || isNullOrEmpty(gioKetThuc) || isNullOrEmpty(noiDung)
            || isNullOrEmpty(idGiaoVien) || isNullOrEmpty(idLop)) {
            throw new IllegalArgumentException("Thông tin lịch dạy không được để trống.");
        }

        LichDayDTO ld = new LichDayDTO(idLich, ngayDay, gioBatDau, gioKetThuc,
                                        noiDung, idGiaoVien, idLop);
        lichDayDAL.suaLichDay(ld);
    }

    // ===== Tìm kiếm =====
    public List<LichDayDTO> timKiemLichDay(String timKiem) {
        if (isNullOrEmpty(timKiem)) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống.");
        }
        return lichDayDAL.getLichDayByTenID(timKiem);
    }

    // ===== Hàm tiện ích =====
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
