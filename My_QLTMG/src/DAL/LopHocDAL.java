package DAL;

import DTO.LopHocDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LopHocDAL {

    private DataHelper dataHelper;

    public LopHocDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ========== Load danh sách lớp học ==========
    public List<LopHocDTO> loadLopHocList() {
        List<LopHocDTO> lopHocList = new ArrayList<>();

        String query = "SELECT L.IDLOP, L.TENLOP, L.SISO, G.IDGIAOVIEN, G.HOTENGIAOVIEN "
                     + "FROM LOPHOC L "
                     + "JOIN GIAOVIEN G ON L.IDGIAOVIEN = G.IDGIAOVIEN";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                LopHocDTO lopHoc = new LopHocDTO(rs);
                lopHocList.add(lopHoc);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load lớp học: " + e.getMessage());
        }

        return lopHocList;
    }

    // ========== Thêm lớp học ==========
    public void themLopHoc(String idLop, String tenLop, int siSo, String idGiaoVien) {

        String query = "INSERT INTO LOPHOC (IDLOP, TENLOP, SISO, IDGIAOVIEN) "
                     + "VALUES (?, ?, ?, ?)";

        Object[] params = {
            idLop,
            tenLop,
            siSo,
            idGiaoVien
        };

        try {
            int rowsAffected = dataHelper.executeUpdate(query, params);
            if (rowsAffected == 0) {
                System.out.println("Không có dữ liệu nào được thêm vào.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi thêm lớp học: " + e.getMessage());
        }
    }

    // ========== Lấy giáo viên chưa có lớp ==========
    public List<Object[]> getGiaoVienChuaCoLop() {

        String query = "SELECT IDGIAOVIEN, HOTENGIAOVIEN "
                     + "FROM GIAOVIEN "
                     + "WHERE IDGIAOVIEN NOT IN (SELECT IDGIAOVIEN FROM LOPHOC)";

        try {
            return dataHelper.executeQueryToList(query, null);
        } catch (SQLException e) {
            System.out.println("Lỗi lấy giáo viên chưa có lớp: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ========== Xóa lớp học ==========
    public void xoaLopHoc(String idLop) {

        String query = "DELETE FROM LOPHOC WHERE IDLOP = ?";

        try {
            int rowsAffected = dataHelper.executeUpdate(query, new Object[]{idLop});
            if (rowsAffected == 0) {
                System.out.println("Không có dữ liệu nào được xóa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi xóa lớp học: " + e.getMessage());
        }
    }

    // ========== Sửa lớp học ==========
    public void suaLopHoc(String idLop, String tenLop, int siSo, String idGiaoVien) {

        String query = "UPDATE LOPHOC SET TENLOP = ?, SISO = ?, IDGIAOVIEN = ? "
                     + "WHERE IDLOP = ?";

        Object[] params = {
            tenLop,
            siSo,
            idGiaoVien,
            idLop
        };

        try {
            int rowsAffected = dataHelper.executeUpdate(query, params);
            if (rowsAffected == 0) {
                System.out.println("Không có dữ liệu nào được cập nhật.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi sửa lớp học: " + e.getMessage());
        }
    }

    // ========== Tìm kiếm lớp học ==========
    public List<LopHocDTO> getLopHocByTenID(String timKiem) {
        List<LopHocDTO> list = new ArrayList<>();

        String query = "SELECT L.IDLOP, L.TENLOP, L.SISO, G.IDGIAOVIEN, G.HOTENGIAOVIEN "
                     + "FROM LOPHOC L "
                     + "JOIN GIAOVIEN G ON L.IDGIAOVIEN = G.IDGIAOVIEN "
                     + "WHERE L.IDLOP LIKE ? "
                     + "OR L.TENLOP LIKE ? "
                     + "OR CAST(L.SISO AS CHAR) LIKE ? "
                     + "OR G.HOTENGIAOVIEN LIKE ?";

        String keyword = "%" + timKiem + "%";

        try {
            ResultSet rs = dataHelper.executeQuery(
                    query,
                    new Object[]{keyword, keyword, keyword, keyword}
            );

            while (rs.next()) {
                list.add(new LopHocDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm lớp học: " + e.getMessage());
        }

        return list;
    }
 // ========== KIỂM TRA TRÙNG MÃ LỚP ==========
    public boolean kiemTraTrungMa(String maLop) {
        String query = "SELECT COUNT(*) FROM LOPHOC WHERE IDLOP = ?";

        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{maLop});
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra trùng mã lớp: " + e.getMessage());
        }
        return false;
    }
 // ========== KIỂM TRA TRÙNG TÊN LỚP (THÊM) ==========
    public boolean kiemTraTrungTen(String tenLop) {
        String query = "SELECT COUNT(*) FROM LOPHOC WHERE TENLOP = ?";

        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{tenLop});
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra trùng tên lớp: " + e.getMessage());
        }
        return false;
    }
 // ========== KIỂM TRA TRÙNG TÊN LỚP (SỬA) ==========
    public boolean kiemTraTrungTenKhiSua(String tenLop, String idLop) {
        String query = "SELECT COUNT(*) FROM LOPHOC WHERE TENLOP = ? AND IDLOP <> ?";

        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{tenLop, idLop});
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra trùng tên lớp khi sửa: " + e.getMessage());
        }
        return false;
    }
    public List<LopHocDTO> getLopTheoGiaoVien(String idGV) {

        List<LopHocDTO> list = new ArrayList<>();

        String query = "SELECT * FROM LOPHOC WHERE IDGIAOVIEN = ?";

        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{idGV});

            while (rs.next()) {
                list.add(new LopHocDTO(rs));
            }

        } catch (Exception e) {
            System.out.println("Lỗi load lớp theo giáo viên: " + e.getMessage());
        }

        return list;
    }

}
