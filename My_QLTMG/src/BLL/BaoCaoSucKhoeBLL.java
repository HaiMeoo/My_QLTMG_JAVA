package BLL;

import DAL.BaoCaoSucKhoeDAL;
import DTO.BaoCaoSucKhoeDTO;

import java.util.ArrayList;
import java.util.List;

public class BaoCaoSucKhoeBLL {

    private BaoCaoSucKhoeDAL baocaoDAL;

    public BaoCaoSucKhoeBLL() {
        baocaoDAL = new BaoCaoSucKhoeDAL();
    }

    // ===== Lấy danh sách báo cáo sức khỏe =====
    public List<BaoCaoSucKhoeDTO> getBaoCaoSucKhoeList() {
        List<BaoCaoSucKhoeDTO> list = baocaoDAL.loadBaoCaoSucKhoeList();
        return list != null ? list : new ArrayList<>();
    }

    // ===== Thêm báo cáo sức khỏe =====
    public void themBaoCaoSucKhoe(BaoCaoSucKhoeDTO bc) {
        if (bc == null
                || isNullOrEmpty(bc.getIDBAOCAO())
                || bc.getNGAYKHAM() == null
                || isNullOrEmpty(bc.getCANNANG())
                || isNullOrEmpty(bc.getCHIEUCAO())
                || isNullOrEmpty(bc.getTINHTRANG())
                || isNullOrEmpty(bc.getIDHOCSINH())
                || isNullOrEmpty(bc.getIDLOP())
                || isNullOrEmpty(bc.getIDGIAOVIEN())) {
            throw new IllegalArgumentException("Thông tin báo cáo sức khỏe không được để trống.");
        }
        baocaoDAL.themBaoCaoSucKhoe(bc);
    }

    // ===== Xóa báo cáo sức khỏe =====
    public void xoaBaoCaoSucKhoe(String idBaoCao) {
        if (isNullOrEmpty(idBaoCao)) {
            throw new IllegalArgumentException("Mã báo cáo không được để trống.");
        }
        baocaoDAL.xoaBaoCaoSucKhoe(idBaoCao);
    }

    // ===== Sửa báo cáo sức khỏe =====
    public void suaBaoCaoSucKhoe(BaoCaoSucKhoeDTO bc) {
        if (bc == null
                || isNullOrEmpty(bc.getIDBAOCAO())
                || bc.getNGAYKHAM() == null
                || isNullOrEmpty(bc.getCANNANG())
                || isNullOrEmpty(bc.getCHIEUCAO())
                || isNullOrEmpty(bc.getTINHTRANG())
                || isNullOrEmpty(bc.getIDHOCSINH())
                || isNullOrEmpty(bc.getIDLOP())
                || isNullOrEmpty(bc.getIDGIAOVIEN())) {
            throw new IllegalArgumentException("Thông tin báo cáo sức khỏe không được để trống.");
        }
        baocaoDAL.suaBaoCaoSucKhoe(bc);
    }

    // ===== Tìm kiếm báo cáo sức khỏe =====
    public List<BaoCaoSucKhoeDTO> timKiemBaoCaoSucKhoe(String keyword) {
        if (isNullOrEmpty(keyword)) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống.");
        }
        return baocaoDAL.timKiemBaoCaoSucKhoe(keyword);
    }

    // ===== Hàm hỗ trợ =====
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
