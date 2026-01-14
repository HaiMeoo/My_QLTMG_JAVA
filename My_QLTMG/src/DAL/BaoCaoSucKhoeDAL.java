package DAL;

import DTO.BaoCaoSucKhoeDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaoCaoSucKhoeDAL {

    private DataHelper dataHelper;

    public BaoCaoSucKhoeDAL() {
        dataHelper = DataHelper.getInstance();
    }

    // ===== Load danh sách báo cáo sức khỏe =====
    public List<BaoCaoSucKhoeDTO> loadBaoCaoSucKhoeList() {
        List<BaoCaoSucKhoeDTO> list = new ArrayList<>();
        String query = "SELECT bc.IDBAOCAO, bc.NGAYKHAM, bc.CANNANG, bc.CHIEUCAO, bc.TINHTRANG, " +
                       "bc.IDHOCSINH, hs.HOTENHOCSINH, bc.IDLOP, lh.TENLOP, bc.IDGIAOVIEN, gv.HOTENGIAOVIEN " +
                       "FROM BAOCAOSUCKHOE bc " +
                       "JOIN HOCSINH hs ON bc.IDHOCSINH = hs.IDHOCSINH " +
                       "JOIN LOPHOC lh ON bc.IDLOP = lh.IDLOP " +
                       "JOIN GIAOVIEN gv ON bc.IDGIAOVIEN = gv.IDGIAOVIEN";

        try {
            ResultSet rs = dataHelper.executeQuery(query);
            while (rs.next()) {
                BaoCaoSucKhoeDTO bc = new BaoCaoSucKhoeDTO();
                bc.setIDBAOCAO(rs.getString("IDBAOCAO"));
                bc.setNGAYKHAM(rs.getDate("NGAYKHAM"));
                bc.setCANNANG(rs.getString("CANNANG"));
                bc.setCHIEUCAO(rs.getString("CHIEUCAO"));
                bc.setTINHTRANG(rs.getString("TINHTRANG"));
                bc.setIDHOCSINH(rs.getString("IDHOCSINH"));
                bc.setHOTENHOCSINH(rs.getString("HOTENHOCSINH"));
                bc.setIDLOP(rs.getString("IDLOP"));
                bc.setTENLOP(rs.getString("TENLOP"));
                bc.setIDGIAOVIEN(rs.getString("IDGIAOVIEN"));
                bc.setHOTENGIAOVIEN(rs.getString("HOTENGIAOVIEN"));
                list.add(bc);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi load báo cáo sức khỏe: " + e.getMessage());
        }

        return list;
    }

    // ===== Thêm báo cáo sức khỏe =====
    public void themBaoCaoSucKhoe(BaoCaoSucKhoeDTO bc) {
        String query = "INSERT INTO BAOCAOSUCKHOE " +
                       "(IDBAOCAO, NGAYKHAM, CANNANG, CHIEUCAO, TINHTRANG, IDHOCSINH, IDLOP, IDGIAOVIEN) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
            bc.getIDBAOCAO(),
            new java.sql.Date(bc.getNGAYKHAM().getTime()),
            bc.getCANNANG(),
            bc.getCHIEUCAO(),
            bc.getTINHTRANG(),
            bc.getIDHOCSINH(),
            bc.getIDLOP(),
            bc.getIDGIAOVIEN()
        };

        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi thêm báo cáo sức khỏe: " + e.getMessage());
        }
    }

    // ===== Sửa báo cáo sức khỏe =====
    public void suaBaoCaoSucKhoe(BaoCaoSucKhoeDTO bc) {
        String query = "UPDATE BAOCAOSUCKHOE SET " +
                       "NGAYKHAM = ?, CANNANG = ?, CHIEUCAO = ?, TINHTRANG = ?, " +
                       "IDHOCSINH = ?, IDLOP = ?, IDGIAOVIEN = ? " +
                       "WHERE IDBAOCAO = ?";
        Object[] params = {
            new java.sql.Date(bc.getNGAYKHAM().getTime()),
            bc.getCANNANG(),
            bc.getCHIEUCAO(),
            bc.getTINHTRANG(),
            bc.getIDHOCSINH(),
            bc.getIDLOP(),
            bc.getIDGIAOVIEN(),
            bc.getIDBAOCAO()
        };

        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi sửa báo cáo sức khỏe: " + e.getMessage());
        }
    }

    // ===== Xóa báo cáo sức khỏe =====
    public void xoaBaoCaoSucKhoe(String idBaoCao) {
        String query = "DELETE FROM BAOCAOSUCKHOE WHERE IDBAOCAO = ?";
        Object[] params = { idBaoCao };
        try {
            dataHelper.executeUpdate(query, params);
        } catch (SQLException e) {
            System.out.println("Lỗi xóa báo cáo sức khỏe: " + e.getMessage());
        }
    }

    // ===== Tìm kiếm báo cáo theo từ khóa =====
    public List<BaoCaoSucKhoeDTO> timKiemBaoCaoSucKhoe(String keyword) {
        List<BaoCaoSucKhoeDTO> list = new ArrayList<>();
        String query = "SELECT bc.IDBAOCAO, bc.NGAYKHAM, bc.CANNANG, bc.CHIEUCAO, bc.TINHTRANG, " +
                       "bc.IDHOCSINH, hs.HOTENHOCSINH, bc.IDLOP, lh.TENLOP, bc.IDGIAOVIEN, gv.HOTENGIAOVIEN " +
                       "FROM BAOCAOSUCKHOE bc " +
                       "JOIN HOCSINH hs ON bc.IDHOCSINH = hs.IDHOCSINH " +
                       "JOIN LOPHOC lh ON bc.IDLOP = lh.IDLOP " +
                       "JOIN GIAOVIEN gv ON bc.IDGIAOVIEN = gv.IDGIAOVIEN " +
                       "WHERE LOWER(bc.IDBAOCAO) LIKE ? " +
                       "OR LOWER(bc.CANNANG) LIKE ? " +
                       "OR LOWER(bc.CHIEUCAO) LIKE ? " +
                       "OR LOWER(bc.TINHTRANG) LIKE ? " +
                       "OR LOWER(hs.HOTENHOCSINH) LIKE ? " +
                       "OR LOWER(lh.TENLOP) LIKE ? " +
                       "OR LOWER(gv.HOTENGIAOVIEN) LIKE ?";

        String kw = "%" + keyword.toLowerCase() + "%";
        Object[] params = { kw, kw, kw, kw, kw, kw, kw };

        try {
            ResultSet rs = dataHelper.executeQuery(query, params);
            while (rs.next()) {
                BaoCaoSucKhoeDTO bc = new BaoCaoSucKhoeDTO();
                bc.setIDBAOCAO(rs.getString("IDBAOCAO"));
                bc.setNGAYKHAM(rs.getDate("NGAYKHAM"));
                bc.setCANNANG(rs.getString("CANNANG"));
                bc.setCHIEUCAO(rs.getString("CHIEUCAO"));
                bc.setTINHTRANG(rs.getString("TINHTRANG"));
                bc.setIDHOCSINH(rs.getString("IDHOCSINH"));
                bc.setHOTENHOCSINH(rs.getString("HOTENHOCSINH"));
                bc.setIDLOP(rs.getString("IDLOP"));
                bc.setTENLOP(rs.getString("TENLOP"));
                bc.setIDGIAOVIEN(rs.getString("IDGIAOVIEN"));
                bc.setHOTENGIAOVIEN(rs.getString("HOTENGIAOVIEN"));
                list.add(bc);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm báo cáo sức khỏe: " + e.getMessage());
        }

        return list;
    }
}
