package BLL;

import DAL.ChitietNGHDAL;
import DTO.ChitietNGHDTO;
import java.util.List;

public class ChitietNGHBLL {

    private ChitietNGHDAL chitietNGHDAL;

    public ChitietNGHBLL() {
        chitietNGHDAL = new ChitietNGHDAL();
    }

    public List<ChitietNGHDTO> getChitietNGHList() {
        return chitietNGHDAL.loadChitietNGHList();
    }

    public void themChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        if (idHocSinh.isEmpty() || idPhuHuynh.isEmpty() || nguoiGiamHo.isEmpty()) {
            throw new IllegalArgumentException("Không được để trống thông tin NGH");
        }
        chitietNGHDAL.themChitietNGH(idHocSinh, idPhuHuynh, nguoiGiamHo);
    }

    public void suaChitietNGH(String idHocSinh, String idPhuHuynh, String nguoiGiamHo) {
        chitietNGHDAL.suaChitietNGH(idHocSinh, idPhuHuynh, nguoiGiamHo);
    }

    public void xoaChitietNGH(String idHocSinh, String idPhuHuynh) {
        chitietNGHDAL.xoaChitietNGH(idHocSinh, idPhuHuynh);
    }

    public List<ChitietNGHDTO> timKiemChitietNGH(String timKiem) {
        return chitietNGHDAL.getNguoiGiamHoByTenID(timKiem);
    }
}
