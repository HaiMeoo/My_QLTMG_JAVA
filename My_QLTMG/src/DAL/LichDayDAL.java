package DAL;

import DTO.LichDayDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LichDayDAL {

    private DataHelper dataHelper;

    public LichDayDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ========== Load danh sách lịch dạy ==========
    public List<LichDayDTO> loadLichDayList() {
        List<LichDayDTO> list = new ArrayList<>();
        String query =
            "SELECT ld.IDLICH, ld.NGAYDAY, ld.GIOBATDAU, ld.GIOKETTHUC, " +
            "ld.NOIDUNG, ld.IDGIAOVIEN, gv.HOTENGIAOVIEN, ld.IDLOP, lh.TENLOP " +
            "FROM LICHDAYHOC ld " +
            "JOIN GIAOVIEN gv ON ld.IDGIAOVIEN = gv.IDGIAOVIEN " +
            "JOIN LOPHOC lh ON ld.IDLOP = lh.IDLOP";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                LichDayDTO ld = new LichDayDTO();
                ld.setIDLICH(rs.getString("IDLICH"));
                ld.setNGAYDAY(rs.getString("NGAYDAY"));
                ld.setGIOBATDAU(rs.getString("GIOBATDAU"));
                ld.setGIOKETTHUC(rs.getString("GIOKETTHUC"));
                ld.setNOIDUNG(rs.getString("NOIDUNG"));
                ld.setIDGIAOVIEN(rs.getString("IDGIAOVIEN"));
                ld.setHOTENGIAOVIEN(rs.getString("HOTENGIAOVIEN"));
                ld.setIDLOP(rs.getString("IDLOP"));
                ld.setTENLOP(rs.getString("TENLOP"));
                list.add(ld);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load lịch dạy: " + e.getMessage());
        }

        return list;
    }

    // ========== Thêm lịch dạy ==========
    public void themLichDay(LichDayDTO ld) {
        String query =
            "INSERT INTO LICHDAYHOC (IDLICH, NGAYDAY, GIOBATDAU, GIOKETTHUC, NOIDUNG, IDGIAOVIEN, IDLOP) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
            ld.getIDLICH(),
            ld.getNGAYDAY(),
            ld.getGIOBATDAU(),
            ld.getGIOKETTHUC(),
            ld.getNOIDUNG(),
            ld.getIDGIAOVIEN(),
            ld.getIDLOP()
        };
        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi thêm lịch dạy: " + e.getMessage());
        }
    }

    // ========== Sửa lịch dạy ==========
    public void suaLichDay(LichDayDTO ld) {
        String query =
            "UPDATE LICHDAYHOC SET NGAYDAY = ?, GIOBATDAU = ?, GIOKETTHUC = ?, " +
            "NOIDUNG = ?, IDGIAOVIEN = ?, IDLOP = ? WHERE IDLICH = ?";
        Object[] params = {
            ld.getNGAYDAY(),
            ld.getGIOBATDAU(),
            ld.getGIOKETTHUC(),
            ld.getNOIDUNG(),
            ld.getIDGIAOVIEN(),
            ld.getIDLOP(),
            ld.getIDLICH()
        };
        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi sửa lịch dạy: " + e.getMessage());
        }
    }

    // ========== Xóa lịch dạy ==========
    public void xoaLichDay(String idLich) {
        String query = "DELETE FROM LICHDAYHOC WHERE IDLICH = ?";
        try {
            dataHelper.executeUpdate(query, new Object[]{idLich});
        } catch (SQLException e) {
            System.out.println("Lỗi xóa lịch dạy: " + e.getMessage());
        }
    }

    // ========== Tìm kiếm ==========
    public List<LichDayDTO> getLichDayByTenID(String timKiem) {
        List<LichDayDTO> list = new ArrayList<>();
        String query =
            "SELECT ld.IDLICH, ld.NGAYDAY, ld.GIOBATDAU, ld.GIOKETTHUC, " +
            "ld.NOIDUNG, ld.IDGIAOVIEN, gv.HOTENGIAOVIEN, ld.IDLOP, lh.TENLOP " +
            "FROM LICHDAYHOC ld " +
            "JOIN GIAOVIEN gv ON ld.IDGIAOVIEN = gv.IDGIAOVIEN " +
            "JOIN LOPHOC lh ON ld.IDLOP = lh.IDLOP " +
            "WHERE LOWER(ld.IDLICH) LIKE ? OR LOWER(ld.NOIDUNG) LIKE ?";
        String keyword = "%" + timKiem.toLowerCase() + "%";
        try {
            ResultSet rs = dataHelper.executeQuery(query, new Object[]{keyword, keyword});
            while (rs.next()) {
                LichDayDTO ld = new LichDayDTO();
                ld.setIDLICH(rs.getString("IDLICH"));
                ld.setNGAYDAY(rs.getString("NGAYDAY"));
                ld.setGIOBATDAU(rs.getString("GIOBATDAU"));
                ld.setGIOKETTHUC(rs.getString("GIOKETTHUC"));
                ld.setNOIDUNG(rs.getString("NOIDUNG"));
                ld.setIDGIAOVIEN(rs.getString("IDGIAOVIEN"));
                ld.setHOTENGIAOVIEN(rs.getString("HOTENGIAOVIEN"));
                ld.setIDLOP(rs.getString("IDLOP"));
                ld.setTENLOP(rs.getString("TENLOP"));
                list.add(ld);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm lịch dạy: " + e.getMessage());
        }
        return list;
    }
}
