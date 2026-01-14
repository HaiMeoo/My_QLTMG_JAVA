package DAL;

import DTO.GiaoVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaoVienDAL {

    private DataHelper dataHelper;

    public GiaoVienDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ========== Load danh sách giáo viên ==========
    public List<GiaoVienDTO> loadGiaoVienList() {
        List<GiaoVienDTO> giaoVienList = new ArrayList<>();

        String query = "SELECT IDGIAOVIEN, HOTENGIAOVIEN, GIOITINH, NGAYSINH, DIACHI, CCCD, SDT FROM GIAOVIEN";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                GiaoVienDTO gv = new GiaoVienDTO(rs);
                giaoVienList.add(gv);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load giáo viên: " + e.getMessage());
        }

        return giaoVienList;
    }

    // ========== Thêm giáo viên ==========
    public void themGiaoVien(String idGiaoVien, String tenGiaoVien, int gioiTinh,
                             String ngaySinh, String diaChi, String cccd, String sdt) {

        String query = "INSERT INTO GIAOVIEN (IDGIAOVIEN, HOTENGIAOVIEN, GIOITINH, NGAYSINH, DIACHI, CCCD, SDT) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[] params = {
            idGiaoVien,
            tenGiaoVien,
            gioiTinh,
            ngaySinh,
            diaChi,
            cccd,
            sdt
        };

        try {
            int rowsAffected = dataHelper.executeUpdate(query, params);
            if (rowsAffected == 0) {
                System.out.println("Không có dữ liệu nào được thêm vào.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm giáo viên: " + e.getMessage());
        }
    }

    // ========== Xóa giáo viên ==========
    public void xoaGiaoVien(String idGiaoVien) {

        String query = "DELETE FROM GIAOVIEN WHERE IDGIAOVIEN = ?";

        try {
            int rowsAffected = dataHelper.executeUpdate(query, new Object[]{idGiaoVien});
            if (rowsAffected == 0) {
                System.out.println("Không tìm thấy giáo viên cần xóa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa giáo viên: " + e.getMessage());
        }
    }

    // ========== Sửa giáo viên ==========
    public void suaGiaoVien(String idGiaoVien, String hoTenGiaoVien, int gioiTinh,
                            String ngaySinh, String diaChi, String cccd, String sdt) {

        String query = "UPDATE GIAOVIEN SET HOTENGIAOVIEN = ?, GIOITINH = ?, NGAYSINH = ?, "
                     + "DIACHI = ?, CCCD = ?, SDT = ? WHERE IDGIAOVIEN = ?";

        Object[] params = {
            hoTenGiaoVien,
            gioiTinh,
            ngaySinh,
            diaChi,
            cccd,
            sdt,
            idGiaoVien
        };

        try {
            int rowsAffected = dataHelper.executeUpdate(query, params);
            if (rowsAffected == 0) {
                System.out.println("Không tìm thấy giáo viên để sửa.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi sửa giáo viên: " + e.getMessage());
        }
    }

    // ========== Tìm giáo viên theo ID hoặc tên ==========
    public List<GiaoVienDTO> getGiaoVienByTenID(String timKiem) {
        List<GiaoVienDTO> list = new ArrayList<>();

        String query = "SELECT IDGIAOVIEN, HOTENGIAOVIEN, GIOITINH, NGAYSINH, DIACHI, CCCD, SDT "
                     + "FROM GIAOVIEN "
                     + "WHERE LOWER(IDGIAOVIEN) LIKE ? OR LOWER(HOTENGIAOVIEN) LIKE ?";

        String keyword = "%" + timKiem.toLowerCase() + "%";

        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{keyword, keyword});
            while (rs.next()) {
                list.add(new GiaoVienDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm giáo viên: " + e.getMessage());
        }

        return list;
    }
}
