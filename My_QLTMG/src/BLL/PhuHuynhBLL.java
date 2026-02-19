package BLL;

import DAL.PhuHuynhDAL;
import DTO.PhuHuynhDTO;

import java.sql.ResultSet;
import java.util.List;
import java.sql.ResultSet;
import DTO.HocSinhDTO;

public class PhuHuynhBLL {

    private PhuHuynhDAL phuHuynhDAL;

    public PhuHuynhBLL() {
        phuHuynhDAL = new PhuHuynhDAL();
    }

    // ==============================
    // LẤY DANH SÁCH PHỤ HUYNH
    // ==============================
    public List<PhuHuynhDTO> getPhuHuynhList() {
        List<PhuHuynhDTO> list = phuHuynhDAL.getAllPhuHuynh();

        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách phụ huynh trống.");
        }
        return list;
    }
    public List<PhuHuynhDTO> getAll() {
        return phuHuynhDAL.getAllPhuHuynh();   
    }


    // ==============================
    // LẤY DANH SÁCH HỌC SINH (COMBOBOX)
    // ==============================
    public List<HocSinhDTO> getDanhSachHocSinh() {
        return phuHuynhDAL.getDanhSachHocSinh();
    }

    // ==============================
    // THÊM PHỤ HUYNH
    // ==============================
    public boolean themPhuHuynh(PhuHuynhDTO ph) {

        if (ph.getIDPHUHUYNH().isEmpty()
                || ph.getTENPHUHUYNH().isEmpty()
                || ph.getSDT().isEmpty()
                || ph.getEMAIL().isEmpty()
                || ph.getDIACHI().isEmpty()
                || ph.getIDHOCSINH().isEmpty()) {

            throw new IllegalArgumentException("Thông tin phụ huynh không được để trống.");
        }

        return phuHuynhDAL.insertPhuHuynh(ph);
    }

    // ==============================
    // SỬA PHỤ HUYNH
    // ==============================
    public boolean suaPhuHuynh(PhuHuynhDTO ph) {

        if (ph.getIDPHUHUYNH().isEmpty()) {
            throw new IllegalArgumentException("Mã phụ huynh không hợp lệ.");
        }

        return phuHuynhDAL.updatePhuHuynh(ph);
    }

    // ==============================
    // XOÁ PHỤ HUYNH
    // ==============================
    public boolean xoaPhuHuynh(String idPhuHuynh) {

        if (idPhuHuynh == null || idPhuHuynh.isEmpty()) {
            throw new IllegalArgumentException("Mã phụ huynh không hợp lệ.");
        }

        return phuHuynhDAL.deletePhuHuynh(idPhuHuynh);
    }

    // ==============================
    // TÌM KIẾM PHỤ HUYNH
    // ==============================
    public List<PhuHuynhDTO> timKiemPhuHuynh(String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Nội dung tìm kiếm không được để trống.");
        }

        List<PhuHuynhDTO> list = phuHuynhDAL.searchPhuHuynh(keyword);

        for (PhuHuynhDTO ph : list) {
            System.out.println(
                    "Mã PH: " + ph.getIDPHUHUYNH()
                    + ", Tên: " + ph.getTENPHUHUYNH()
                    + ", SDT: " + ph.getSDT()
            );
        }

        return list;
    }
}

