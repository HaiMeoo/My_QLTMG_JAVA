package DAL;

import DTO.HocSinhDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HocSinhDAL {

	private DataHelper dataHelper;

    public HocSinhDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ==============================
    // LẤY DANH SÁCH HỌC SINH
    // ==============================
    public List<HocSinhDTO> getAllHocSinh() {
        List<HocSinhDTO> list = new ArrayList<>();

        String query =
                "SELECT H.IDHOCSINH, H.HOTENHOCSINH, H.GIOITINH, " +
                "H.NGAYSINH, H.DIACHI, L.IDLOP, L.TENLOP " +
                "FROM HOCSINH H " +
                "JOIN LOPHOC L ON H.IDLOP = L.IDLOP";

        try {
            ResultSet rs = dataHelper.executeQuery(query);

            while (rs.next()) {
                HocSinhDTO hs = new HocSinhDTO(
                        rs.getString("IDHOCSINH"),
                        rs.getString("HOTENHOCSINH"),
                        rs.getInt("GIOITINH"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("DIACHI"),
                        rs.getString("IDLOP"),
                        rs.getString("TENLOP")
                );
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ==============================
    // THÊM HỌC SINH
    // ==============================
    public boolean insertHocSinh(HocSinhDTO hs) {

        String query =
                "INSERT INTO HOCSINH (IDHOCSINH, HOTENHOCSINH, GIOITINH, NGAYSINH, DIACHI, IDLOP) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Object[] params = {
                hs.getIDHOCSINH(),
                hs.getHOTENHOCSINH(),
                hs.getGIOITINH(),
                hs.getNGAYSINH(),
                hs.getDIACHI(),
                hs.getIDLOP()
        };

        try {
            return dataHelper.executeUpdate(query, params) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // CẬP NHẬT HỌC SINH
    // ==============================
    public boolean updateHocSinh(HocSinhDTO hs) {

        String query =
                "UPDATE HOCSINH SET HOTENHOCSINH = ?, GIOITINH = ?, " +
                "NGAYSINH = ?, DIACHI = ?, IDLOP = ? " +
                "WHERE IDHOCSINH = ?";

        Object[] params = {
                hs.getHOTENHOCSINH(),
                hs.getGIOITINH(),
                hs.getNGAYSINH(),
                hs.getDIACHI(),
                hs.getIDLOP(),
                hs.getIDHOCSINH()
        };

        try {
            return dataHelper.executeUpdate(query, params) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // XOÁ HỌC SINH
    // ==============================
    public boolean deleteHocSinh(String idHocSinh) {

        String query = "DELETE FROM HOCSINH WHERE IDHOCSINH = ?";

        try {
            return dataHelper.executeUpdate(query, new Object[]{idHocSinh}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ==============================
    // LẤY DANH SÁCH LỚP (COMBOBOX)
    // ==============================
    public ResultSet getDanhSachLop() {
        String query = "SELECT IDLOP, TENLOP FROM LOPHOC";
        try {
            return dataHelper.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ==============================
    // TÌM HỌC SINH THEO TÊN
    // ==============================
    public List<HocSinhDTO> searchHocSinhByName(String keyword) {
        List<HocSinhDTO> list = new ArrayList<>();

        String query =
                "SELECT H.IDHOCSINH, H.HOTENHOCSINH, H.GIOITINH, " +
                "H.NGAYSINH, H.DIACHI, L.IDLOP, L.TENLOP " +
                "FROM HOCSINH H " +
                "JOIN LOPHOC L ON H.IDLOP = L.IDLOP " +
                "WHERE H.HOTENHOCSINH LIKE ?";

        try {
            ResultSet rs = dataHelper.executeQuery(
                    query,
                    new Object[]{"%" + keyword + "%"}
            );

            while (rs.next()) {
                HocSinhDTO hs = new HocSinhDTO(
                        rs.getString("IDHOCSINH"),
                        rs.getString("HOTENHOCSINH"),
                        rs.getInt("GIOITINH"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("DIACHI"),
                        rs.getString("IDLOP"),
                        rs.getString("TENLOP")
                );
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
