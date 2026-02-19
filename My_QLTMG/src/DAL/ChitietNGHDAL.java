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

    // ========== Load danh sách ==========
    public List<ChitietNGHDTO> loadChitietNGHList() {
        List<ChitietNGHDTO> list = new ArrayList<ChitietNGHDTO>();

        String sql = "SELECT ct.IDHOCSINH, hs.HOTENHOCSINH, "
                   + "ct.IDPHUHUYNH, ph.TENPHUHUYNH, "
                   + "ct.NGUOIGIAMHO "
                   + "FROM CHITIETNGUOIGIAMHO ct "
                   + "JOIN HOCSINH hs ON ct.IDHOCSINH = hs.IDHOCSINH "
                   + "JOIN PHUHUYNH ph ON ct.IDPHUHUYNH = ph.IDPHUHUYNH";

        try {
            ResultSet rs = dataHelper.executeQuery(sql);
            while (rs.next()) {
                ChitietNGHDTO dto = new ChitietNGHDTO();
                dto.setIDHOCSINH(rs.getString("IDHOCSINH"));
                dto.setHOTENHOCSINH(rs.getString("HOTENHOCSINH"));
                dto.setIDPHUHUYNH(rs.getString("IDPHUHUYNH"));
                dto.setTENPHUHUYNH(rs.getString("TENPHUHUYNH"));
                dto.setNGUOIGIAMHO(rs.getString("NGUOIGIAMHO"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ========== Thêm ==========
    public void themChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        System.out.println("ID HS: " + idHocSinh);
        System.out.println("ID PH: " + idPhuHuynh);

        String sql = "INSERT INTO CHITIETNGUOIGIAMHO (IDHOCSINH, IDPHUHUYNH, NGUOIGIAMHO) VALUES (?, ?, ?)";
        Object[] params = { idHocSinh, idPhuHuynh, nguoiGiamHo };

        try {
            dataHelper.executeUpdate(sql, params);
            System.out.println("Thêm thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== Sửa ==========
    public void suaChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        String sql = "UPDATE CHITIETNGUOIGIAMHO SET NGUOIGIAMHO=? "
                   + "WHERE IDHOCSINH=? AND IDPHUHUYNH=?";
        Object[] params = { nguoiGiamHo, idHocSinh, idPhuHuynh };
        try {
            dataHelper.executeUpdate(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== Xóa ==========
    public void xoaChitietNGH(String idHocSinh, String idPhuHuynh) {
        String sql = "DELETE FROM CHITIETNGUOIGIAMHO WHERE IDHOCSINH=? AND IDPHUHUYNH=?";
        Object[] params = { idHocSinh, idPhuHuynh };
        try {
            dataHelper.executeUpdate(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ========== Tìm kiếm ==========
    public List<ChitietNGHDTO> getNguoiGiamHoByTenID(String timKiem) {

        List<ChitietNGHDTO> list = new ArrayList<ChitietNGHDTO>();

        String sql = "SELECT ct.IDHOCSINH, hs.HOTENHOCSINH, "
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
            ResultSet rs = dataHelper.executeQuery(sql, params);
            while (rs.next()) {

                ChitietNGHDTO dto = new ChitietNGHDTO();
                dto.setIDHOCSINH(rs.getString("IDHOCSINH"));
                dto.setHOTENHOCSINH(rs.getString("HOTENHOCSINH"));
                dto.setIDPHUHUYNH(rs.getString("IDPHUHUYNH"));
                dto.setTENPHUHUYNH(rs.getString("TENPHUHUYNH"));
                dto.setNGUOIGIAMHO(rs.getString("NGUOIGIAMHO"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
