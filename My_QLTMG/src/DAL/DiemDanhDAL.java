package DAL;

import DTO.DiemDanhDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiemDanhDAL {

    private DataHelper dataHelper;

    public DiemDanhDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ========== Load danh sách điểm danh ==========
    public List<DiemDanhDTO> loadDiemDanhList() {
        List<DiemDanhDTO> list = new ArrayList<>();

        String query =
            "SELECT D.IDDIEMDANH, D.IDHOCSINH, H.HOTENHOCSINH, " +
            "D.IDLOP, L.TENLOP, D.NGAYDIEMDANH, D.TRANGTHAI, D.LYDO, " +
            "D.IDGIAOVIEN, G.HOTENGIAOVIEN " +
            "FROM DIEMDANH D " +
            "JOIN HOCSINH H ON D.IDHOCSINH = H.IDHOCSINH " +
            "JOIN LOPHOC L ON D.IDLOP = L.IDLOP " +
            "JOIN GIAOVIEN G ON D.IDGIAOVIEN = G.IDGIAOVIEN";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                list.add(new DiemDanhDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load điểm danh: " + e.getMessage());
        }

        return list;
    }

    // ========== Thêm điểm danh ==========
    public void themDiemDanh(String idDiemDanh, String idHocSinh, String idLop,
                             String ngayDiemDanh, String trangThai,
                             String lyDo, String idGiaoVien) {

        String query =
            "INSERT INTO DIEMDANH (IDDIEMDANH, IDHOCSINH, IDLOP, NGAYDIEMDANH, TRANGTHAI, LYDO, IDGIAOVIEN) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[] params = {
            idDiemDanh,
            idHocSinh,
            idLop,
            ngayDiemDanh,
            trangThai,
            lyDo,
            idGiaoVien
        };

        try {
            int rows = dataHelper.executeUpdate(query, params);
            if (rows == 0) {
                System.out.println("Không có dữ liệu điểm danh được thêm.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi thêm điểm danh: " + e.getMessage());
        }
    }

    // ========== Sửa điểm danh ==========
    public void suaDiemDanh(String idDiemDanh, String idHocSinh, String idLop,
                            String ngayDiemDanh, String trangThai,
                            String lyDo, String idGiaoVien) {

        String query =
            "UPDATE DIEMDANH SET IDHOCSINH = ?, IDLOP = ?, NGAYDIEMDANH = ?, " +
            "TRANGTHAI = ?, LYDO = ?, IDGIAOVIEN = ? " +
            "WHERE IDDIEMDANH = ?";

        Object[] params = {
            idHocSinh,
            idLop,
            ngayDiemDanh,
            trangThai,
            lyDo,
            idGiaoVien,
            idDiemDanh
        };

        try {
            int rows = dataHelper.executeUpdate(query, params);
            if (rows == 0) {
                System.out.println("Không tìm thấy điểm danh để sửa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi sửa điểm danh: " + e.getMessage());
        }
    }

    // ========== Xóa điểm danh ==========
    public void xoaDiemDanh(String idDiemDanh) {

        String query = "DELETE FROM DIEMDANH WHERE IDDIEMDANH = ?";

        try {
            int rows = dataHelper.executeUpdate(query, new Object[]{idDiemDanh});
            if (rows == 0) {
                System.out.println("Không tìm thấy điểm danh để xóa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi xóa điểm danh: " + e.getMessage());
        }
    }

    // ========== Tìm điểm danh theo ID / tên / trạng thái ==========
    public List<DiemDanhDTO> getDiemDanhByTenID(String timKiem) {
        List<DiemDanhDTO> list = new ArrayList<>();

        String query =
            "SELECT D.IDDIEMDANH, D.IDHOCSINH, H.HOTENHOCSINH, " +
            "D.IDLOP, L.TENLOP, D.NGAYDIEMDANH, D.TRANGTHAI, D.LYDO, " +
            "D.IDGIAOVIEN, G.HOTENGIAOVIEN " +
            "FROM DIEMDANH D " +
            "LEFT JOIN HOCSINH H ON D.IDHOCSINH = H.IDHOCSINH " +
            "LEFT JOIN LOPHOC L ON D.IDLOP = L.IDLOP " +
            "LEFT JOIN GIAOVIEN G ON D.IDGIAOVIEN = G.IDGIAOVIEN " +
            "WHERE LOWER(D.IDDIEMDANH) LIKE ? " +
            "OR LOWER(H.HOTENHOCSINH) LIKE ? " +
            "OR LOWER(D.TRANGTHAI) LIKE ?";

        String keyword = "%" + timKiem.toLowerCase() + "%";

        try {
            ResultSet rs = dataHelper.executeQuery(
                query,
                new Object[]{keyword, keyword, keyword}
            );
            while (rs.next()) {
                list.add(new DiemDanhDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm điểm danh: " + e.getMessage());
        }

        return list;
    }
}
