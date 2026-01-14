package BLL;

import DAL.HocSinhDAL;
import DTO.HocSinhDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HocSinhBLL {

    private HocSinhDAL hocSinhDAL;

    public HocSinhBLL() {
        hocSinhDAL = new HocSinhDAL();
    }

    // ==============================
    // LẤY DANH SÁCH HỌC SINH
    // ==============================
    public List<HocSinhDTO> getHocSinhList() {
        List<HocSinhDTO> list = hocSinhDAL.getAllHocSinh();

        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách học sinh trống.");
            return new ArrayList<>();
        }
        return list;
    }

    // ==============================
    // THÊM HỌC SINH
    // ==============================
    public boolean themHocSinh(HocSinhDTO hs) throws Exception {

        if (hs.getIDHOCSINH() == null || hs.getIDHOCSINH().isEmpty()
                || hs.getHOTENHOCSINH() == null || hs.getHOTENHOCSINH().isEmpty()
                || hs.getDIACHI() == null || hs.getDIACHI().isEmpty()
                || hs.getIDLOP() == null || hs.getIDLOP().isEmpty()) {
            throw new Exception("Thông tin học sinh không được để trống.");
        }

        if (hs.getGIOITINH() < 0 || hs.getGIOITINH() > 2) {
            throw new Exception("Giới tính không hợp lệ.");
        }

        return hocSinhDAL.insertHocSinh(hs);
    }

    // ==============================
    // SỬA HỌC SINH
    // ==============================
    public boolean suaHocSinh(HocSinhDTO hs) throws Exception {

        if (hs.getIDHOCSINH() == null || hs.getIDHOCSINH().isEmpty()) {
            throw new Exception("Mã học sinh không hợp lệ.");
        }

        if (hs.getGIOITINH() < 0 || hs.getGIOITINH() > 2) {
            throw new Exception("Giới tính không hợp lệ.");
        }

        return hocSinhDAL.updateHocSinh(hs);
    }

    // ==============================
    // XOÁ HỌC SINH
    // ==============================
    public boolean xoaHocSinh(String idHocSinh) throws Exception {

        if (idHocSinh == null || idHocSinh.isEmpty()) {
            throw new Exception("Mã học sinh không hợp lệ.");
        }

        return hocSinhDAL.deleteHocSinh(idHocSinh);
    }

    // ==============================
    // TÌM KIẾM HỌC SINH THEO TÊN
    // ==============================
    public List<HocSinhDTO> timHocSinhTheoTen(String keyword) throws Exception {

        if (keyword == null || keyword.isEmpty()) {
            throw new Exception("Nội dung tìm kiếm không được để trống.");
        }

        return hocSinhDAL.searchHocSinhByName(keyword);
    }

    // ==============================
    // KIỂM TRA ĐỊNH DẠNG NGÀY
    // ==============================
    public Date parseNgaySinh(String ngaySinh) throws Exception {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
        } catch (Exception e) {
            throw new Exception("Ngày sinh không hợp lệ (yyyy-MM-dd).");
        }
    }
}
