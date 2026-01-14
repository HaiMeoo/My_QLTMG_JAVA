package DAL;

import DTO.ChitietNGHDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChitietNGHDAL {

    private DataHelper dataHelper;

    public ChitietNGHDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ========== Load danh sách chi tiết người giám hộ ==========
    public List<ChitietNGHDTO> loadChitietNGHList() {
        List<ChitietNGHDTO> list = new ArrayList<>();

        String query = "SELECT ct.IDHOCSINH, hs.HOTENHOCSINH, "
                     + "ct.IDPHUHUYNH, ph.TENPHUHUYNH, "
                     + "ct.NGUOIGIAMHO "
                     + "FROM CHITIETNGUOIGIAMHO ct "
                     + "JOIN HOCSINH hs ON ct.IDHOCSINH = hs.IDHOCSINH "
                     + "JOIN PHUHUYNH ph ON ct.IDPHUHUYNH = ph.IDPHUHUYNH";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                list.add(new ChitietNGHDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load chi tiết NGH: " + e.getMessage());
        }

        return list;
    }

    // ========== Thêm ==========
    public void themChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        String query = "INSERT INTO CHITIETNGUOIGIAMHO (IDHOCSINH, IDPHUHUYNH, NGUOIGIAMHO) "
                     + "VALUES (?, ?, ?)";

        Object[] params = { idHocSinh, idPhuHuynh, nguoiGiamHo };

        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi thêm chi tiết NGH: " + e.getMessage());
        }
    }

    // ========== Sửa ==========
    public void suaChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        String query = "UPDATE CHITIETNGUOIGIAMHO SET NGUOIGIAMHO = ? "
                     + "WHERE IDHOCSINH = ? AND IDPHUHUYNH = ?";

        Object[] params = { nguoiGiamHo, idHocSinh, idPhuHuynh };

        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi sửa chi tiết NGH: " + e.getMessage());
        }
    }

    // ========== Xóa ==========
    public void xoaChitietNGH(String idHocSinh, String idPhuHuynh) {
        String query = "DELETE FROM CHITIETNGUOIGIAMHO "
                     + "WHERE IDHOCSINH = ? AND IDPHUHUYNH = ?";

        Object[] params = { idHocSinh, idPhuHuynh };

        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi xóa chi tiết NGH: " + e.getMessage());
        }
    }

    // ========== Tìm kiếm ==========
    public List<ChitietNGHDTO> getNguoiGiamHoByTenID(String timKiem) {
        List<ChitietNGHDTO> list = new ArrayList<>();

        String query = "SELECT ct.IDHOCSINH, hs.HOTENHOCSINH, "
                     + "ct.IDPHUHUYNH, ph.TENPHUHUYNH, "
                     + "ct.NGUOIGIAMHO "
                     + "FROM CHITIETNGUOIGIAMHO ct "
                     + "JOIN HOCSINH hs ON ct.IDHOCSINH = hs.IDHOCSINH "
                     + "JOIN PHUHUYNH ph ON ct.IDPHUHUYNH = ph.IDPHUHUYNH "
                     + "WHERE LOWER(hs.HOTENHOCSINH) LIKE ? "
                     + "OR LOWER(ph.TENPHUHUYNH) LIKE ? "
                     + "OR LOWER(ct.NGUOIGIAMHO) LIKE ?";

        String key = "%" + timKiem.toLowerCase() + "%";
        Object[] params = { key, key, key };

        try {
            ResultSet rs = dataHelper.executeQuery(query, params);
            while (rs.next()) {
                list.add(new ChitietNGHDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm chi tiết NGH: " + e.getMessage());
        }

        return list;
    }
}
