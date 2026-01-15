package DAL;

import DTO.PhuHuynhDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DTO.HocSinhDTO;

public class PhuHuynhDAL {

    private DataHelper dataHelper;

    public PhuHuynhDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ==============================
    // LẤY DANH SÁCH PHỤ HUYNH
    // ==============================
    public List<PhuHuynhDTO> getAllPhuHuynh() {
        List<PhuHuynhDTO> list = new ArrayList<>();

        String query =
        	    "SELECT P.IDPHUHUYNH, P.TENPHUHUYNH, P.SDT, P.EMAIL, P.DIACHI, " +
        	    "H.IDHOCSINH, H.HOTEN AS HOTENHOCSINH " +
        	    "FROM PHUHUYNH P " +
        	    "JOIN HOCSINH H ON P.IDHOCSINH = H.IDHOCSINH";


        try {
            ResultSet rs = dataHelper.executeQuery(query);

            while (rs.next()) {
                PhuHuynhDTO ph = new PhuHuynhDTO(
                        rs.getString("IDPHUHUYNH"),
                        rs.getString("TENPHUHUYNH"),
                        rs.getString("SDT"),
                        rs.getString("EMAIL"),
                        rs.getString("DIACHI"),
                        rs.getString("IDHOCSINH"),
                        rs.getString("HOTENHOCSINH")
                );
                list.add(ph);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ==============================
    // LẤY DANH SÁCH HỌC SINH (COMBOBOX)
    // ==============================
    public List<HocSinhDTO> getDanhSachHocSinh() {
        List<HocSinhDTO> list = new ArrayList<>();

        String sql = "SELECT IDHOCSINH, HOTENHOCSINH FROM HOCSINH";

        try {
            ResultSet rs = dataHelper.executeQuery(sql);
            while (rs.next()) {
                HocSinhDTO hs = new HocSinhDTO();
                hs.setIDHOCSINH(rs.getString("IDHOCSINH"));
                hs.setHOTENHOCSINH(rs.getString("HOTENHOCSINH"));
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ==============================
    // THÊM PHỤ HUYNH
    // ==============================
    public boolean insertPhuHuynh(PhuHuynhDTO ph) {

        String query =
                "INSERT INTO PHUHUYNH " +
                "(IDPHUHUYNH, TENPHUHUYNH, SDT, EMAIL, DIACHI, IDHOCSINH) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Object[] params = {
                ph.getIDPHUHUYNH(),
                ph.getTENPHUHUYNH(),
                ph.getSDT(),
                ph.getEMAIL(),
                ph.getDIACHI(),
                ph.getIDHOCSINH()
        };

        try {
            return dataHelper.executeUpdate(query, params) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // CẬP NHẬT PHỤ HUYNH
    // ==============================
    public boolean updatePhuHuynh(PhuHuynhDTO ph) {

        String query =
                "UPDATE PHUHUYNH SET TENPHUHUYNH = ?, SDT = ?, EMAIL = ?, " +
                "DIACHI = ?, IDHOCSINH = ? WHERE IDPHUHUYNH = ?";

        Object[] params = {
                ph.getTENPHUHUYNH(),
                ph.getSDT(),
                ph.getEMAIL(),
                ph.getDIACHI(),
                ph.getIDHOCSINH(),
                ph.getIDPHUHUYNH()
        };

        try {
            return dataHelper.executeUpdate(query, params) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // XOÁ PHỤ HUYNH
    // ==============================
    public boolean deletePhuHuynh(String idPhuHuynh) {

        String query = "DELETE FROM PHUHUYNH WHERE IDPHUHUYNH = ?";

        try {
            return dataHelper.executeUpdate(query, new Object[]{idPhuHuynh}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // TÌM KIẾM PHỤ HUYNH
    // ==============================
    public List<PhuHuynhDTO> searchPhuHuynh(String keyword) {

        List<PhuHuynhDTO> list = new ArrayList<>();

        String query =
        	    "SELECT P.IDPHUHUYNH, P.TENPHUHUYNH, P.SDT, P.EMAIL, P.DIACHI, " +
        	    "H.IDHOCSINH, H.HOTEN AS HOTENHOCSINH " +
        	    "FROM PHUHUYNH P " +
        	    "LEFT JOIN HOCSINH H ON P.IDHOCSINH = H.IDHOCSINH " +
        	    "WHERE P.IDPHUHUYNH LIKE ? OR P.TENPHUHUYNH LIKE ?";


        try {
            ResultSet rs = dataHelper.executeQuery(
                    query,
                    new Object[]{keyword + "%", keyword + "%"}
            );

            while (rs.next()) {
                PhuHuynhDTO ph = new PhuHuynhDTO(
                        rs.getString("IDPHUHUYNH"),
                        rs.getString("TENPHUHUYNH"),
                        rs.getString("SDT"),
                        rs.getString("EMAIL"),
                        rs.getString("DIACHI"),
                        rs.getString("IDHOCSINH"),
                        rs.getString("HOTENHOCSINH")
                );
                list.add(ph);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
