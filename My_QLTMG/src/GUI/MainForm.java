
package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import java.time.LocalDate;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicComboPopup;
import java.awt.Dimension;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import DTO.DiemDanhDTO;
import BLL.DiemDanhBLL;
import BLL.GiaoVienBLL;
import DTO.GiaoVienDTO;
import BLL.LopHocBLL;
import DTO.LopHocDTO;
import BLL.HocSinhBLL;
import DTO.HocSinhDTO;
import BLL.PhuHuynhBLL;
import DTO.PhuHuynhDTO;
import BLL.LichDayBLL;
import DTO.LichDayDTO;
import BLL.BaoCaoSucKhoeBLL;
import DTO.BaoCaoSucKhoeDTO;
import BLL.ChitietNGHBLL;
import DTO.ChitietNGHDTO;



public class MainForm extends JFrame {
	
	// KHAI BÁO CÁC LỚP BLL
	private TaiKhoanBLL taiKhoanBLL = new TaiKhoanBLL();
	private DefaultTableModel modelTaiKhoan;
	private LichDayBLL lichDayBLL = new LichDayBLL();
	private DefaultTableModel modelDD;
	private DiemDanhBLL diemDanhBLL = new DiemDanhBLL();
	private HocSinhBLL hocSinhBLL = new HocSinhBLL();
	private PhuHuynhBLL phuHuynhBLL;
	private JTable tableBCSK;  
	private JScrollPane spBCSK;
	private DefaultTableModel modelBCSK;
	private BaoCaoSucKhoeBLL bcskBLL = new BaoCaoSucKhoeBLL();
	private LopHocBLL lopHocBLL = new LopHocBLL();
	private DefaultTableModel modelLD;
	private JComboBox<HocSinhDTO> cbChonhsGH;
	private JComboBox<PhuHuynhDTO> cbChonphGHhs;
	private JComboBox<LopHocDTO> cbLopHocHocSinh;
	private JComboBox<HocSinhDTO> cbTenHocSinhPH;
	private JComboBox<GiaoVienDTO> cbChonGVDD;
	private JComboBox<HocSinhDTO> cbChonHSDD;
	private JComboBox<LopHocDTO> cbChonlophsDD;
	private JComboBox<GiaoVienDTO> cbChonGVLD;
	private JComboBox<LopHocDTO> cbChonLopLD;
	private JComboBox<LopHocDTO> cbChonlopSK;
	private JComboBox<GiaoVienDTO> cbChongvSK;
	private JComboBox<HocSinhDTO> cbChonHSSK;
	private GiaoVienBLL giaoVienBLL = new GiaoVienBLL();
	private DefaultTableModel modelGV;
	private JTabbedPane tabbedPane;
	private ChitietNGHBLL chitietNGHBLL = new ChitietNGHBLL();
	private JTable tableNGH;
	private DefaultTableModel modelNGH;
	private JScrollPane scrollingNGH;
	private JTable tableGV;
	private JTable tableLD;
	private JTable tableDD;
	private Runnable loadComboGV;
	private JComboBox<GiaoVienDTO> cbChonGVchunhiemlop;
	private Runnable loadTableLop;
	private Runnable loadComboLopHoc;
	
	JComboBox<HocSinhDTO> cbChonphHS = new JComboBox<>();
	private JComboBox<GiaoVienDTO> cbGVCN;
	private String[] colBCSK = {
		    "Mã báo cáo",
		    "Ngày khám",
		    "Cân nặng",
		    "Chiều cao",
		    "Tình trạng",
		    "Học sinh",
		    "Lớp",
		    "Giáo viên"
		};
	private void initComponents() {
	    setTitle("Main Form");
	    setSize(1200, 700);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout());

	    tabbedPane = new JTabbedPane();

	    // ================= TAB LỚP HỌC =================
	    JPanel panelLopHoc = new JPanel();
	    panelLopHoc.setLayout(null);
	    cbTenHocSinhPH = new JComboBox<>();

	    cbGVCN = new JComboBox<>();
	    cbGVCN.setBounds(100, 100, 250, 30);
	    panelLopHoc.add(cbGVCN);

	    tabbedPane.addTab("Lớp học", panelLopHoc);
	 // ================= TAB HỌC SINH =================
	    JPanel panelHocSinh = new JPanel();
	    panelHocSinh.setLayout(null);

	    cbLopHocHocSinh = new JComboBox<>();
	    cbLopHocHocSinh.setBounds(150, 80, 200, 25);
	    panelHocSinh.add(cbLopHocHocSinh);

	    tabbedPane.addTab("Học Sinh", panelHocSinh);

	 // ================= TAB ĐIỂM DANH =================
	    JPanel panelDiemDanh = new JPanel();
	    panelDiemDanh.setLayout(null);

	    
	    cbChonlophsDD = new JComboBox<>();
	    cbChonlophsDD.setBounds(150, 80, 200, 25);
	    panelDiemDanh.add(cbChonlophsDD);
	 
	    cbChonHSDD = new JComboBox<>();
	    cbChonHSDD.setBounds(150, 120, 200, 25);
	    panelDiemDanh.add(cbChonHSDD);
	    
	 
	    cbChonGVDD = new JComboBox<>();
	    cbChonGVDD.setBounds(150, 120, 200, 25);
	    panelDiemDanh.add(cbChonGVDD);


	    // add tab
	    tabbedPane.addTab("Điểm danh", panelDiemDanh);
	    
	    // ================= ADD TABPANE =================
	    getContentPane().add(tabbedPane, BorderLayout.CENTER);

	    // ================= EVENT CHỌN TAB =================
	    tabbedPane.addChangeListener(e -> {
	        if (tabbedPane.getSelectedIndex() != -1 &&
	            tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
	                     .equals("Lớp học")) {

	        	loadComboGVCN();
	        }
	    });
	    
	   
	}
	
	private void fixComboBoxWidth(JComboBox<?> comboBox) {
	    comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
	        @Override
	        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
	            Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);
	            if (comp instanceof BasicComboPopup) {
	                JScrollPane scrollPane =
	                        (JScrollPane) ((BasicComboPopup) comp).getComponent(0);

	                scrollPane.setPreferredSize(
	                        new Dimension(
	                                comboBox.getWidth(),
	                                scrollPane.getPreferredSize().height
	                        )
	                );
	            }
	        }

	        @Override public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
	        @Override public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
	    });
	}


	
	// KHÁC
    private JTextField txtHotenGV;
    private JTextField txtMaGV;
    private JTextField txtNgaysinhGV;
    private JTextField txtDiachiGV;
    private JTextField txtCmtGV;
    private JTextField txtSdtGV;
    private JTextField txtTimGV;
    private JComboBox<String> cbGioiTinhGV;
    private JPanel pLophoc;
    private JPanel pHocsinh;
    private JPanel pPhuhuynh;
    private JPanel pDanhsach;
    private JPanel pTaikhoan;
    private JTextField txtMalop;
    private JTextField txtTenlop;
    private JTextField textField_16;
    private JTextField txtTimLop;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_4;
    private JTextField txtTimHS;
    private JTextField txtMaPH;
    private JTextField txtHovatenPH;
    private JTextField txtSodtPH;
    private JTextField txtDiachiPH;
    private JTextField txtTimPH;
    private JTextField txtEmailPH;
    private JTextField txtMaTK;
    private JTextField txtTenTK;
    private JTextField txtMatkhauTK;
    private JTextField txtTimTK;
    private JTextField txtMaDD;
    private JTextField txtNgayDD;
    private JTextField txtTimDD;
    private JTextField txtTrangthaiDD;
    private JTextField txtLydoDD;
    private JTextField txtMaLD;
    private JTextField txtNoidungday;
    private JTextField txtGiobatdauLD;
    private JTextField txtGioketthucLD;
    private JTextField txtTimLD;
    private JTextField txtNgayday;
    private JTextField txtBaocaosuckhoe;
    private JTextField txtTinhtrang;
    private JTextField txtCannang;
    private JTextField txtChieucao;
    private JTextField txtNgaykham;
    private JTextField txtTimBCSK;
    private JTextField txtTimNGH;
    private JTextField txtNguoigiamho;
    private JTable tableListGV;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private JTable table_5;
    private JTable table_6;
    private JTable table_7;
    private JTextField txtGioitinhGV;

    public MainForm() {
        hocSinhBLL = new HocSinhBLL();
        giaoVienBLL = new GiaoVienBLL();
        phuHuynhBLL = new PhuHuynhBLL();
    	initComponents(); 
        initTableNguoiGiamHo();
        initTabReload();
        initBaoCaoSucKhoe();
        initTabDiemDanhEvent();
        initComboDiemDanhEvent();
        modelBCSK = new DefaultTableModel(
        	    new String[]{
        	        "Mã BC", "Ngày khám", "Cân nặng", "Chiều cao",
        	        "Tình trạng", "Học sinh", "Lớp", "Giáo viên"
        	    }, 0
        	);
        modelDD = new DefaultTableModel(
            new Object[]{
                "Mã điểm danh",
                "Học sinh",
                "Lớp",
                "Ngày điểm danh",
                "Trạng thái",
                "Lý do",
                "Giáo viên"
            }, 0
        );
        SwingUtilities.invokeLater(() -> {
            loadDanhSachDiemDanh();
            loadComboBoxLopDiemDanh();
            loadComboBoxGiaoVienDiemDanh();
            loadComboBoxLichDay();
            loadDanhSachLichDay();
        	loadComboBoxBCSK();
            loadDanhSachBaoCaoSucKhoe();
        	loadComboBoxNGH();
            loadDanhSachNguoiGiamHo();
        	loadComboGVCN(); 
        	loadComboHocSinhPhuHuynh();
        	loadComboLopHocHocSinh();
        	loadComboBoxLopLichDay();
        	loadComboBoxGiaoVienSK();
        });
    	 tabbedPane.addChangeListener(e -> {
    	        int index = tabbedPane.getSelectedIndex();
    	        String title = tabbedPane.getTitleAt(index);

    	        if (title.equals("Học Sinh")) {
    	            loadComboGVCN();
    	        }
    	    });
    	 tabbedPane.addChangeListener(e -> {
    		    if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
    		            .equals("Học Sinh")) {

    		    	loadComboLopHocHocSinh();
    		    }
    		});
    	
        fixComboBoxWidth(cbGVCN);
        
    	getContentPane().setBackground(new Color(255, 192, 203));
    	setBackground(new Color(255, 182, 193));
        getContentPane().setLayout(null);
        
        
        // ================= PANEL BUTTON =================
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(0, 0, 150, 661);
        panel_4.setBackground(new Color(250, 128, 114));
        getContentPane().add(panel_4);
        panel_4.setLayout(null);
        
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnAdmin.setBounds(10, 105, 130, 23);
        panel_4.add(btnAdmin);
        
        JButton btnDanhsach = new JButton("Danh Sách");
        btnDanhsach.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnDanhsach.setBounds(10, 204, 130, 23);
        panel_4.add(btnDanhsach);
        
        JButton btnGiaoVien = new JButton("Giáo Viên");
        btnGiaoVien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnGiaoVien.setBounds(10, 274, 130, 23);
        panel_4.add(btnGiaoVien);
        
        JButton btnLophoc = new JButton("Lớp Học");
        btnLophoc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLophoc.setBounds(10, 325, 130, 23);
        panel_4.add(btnLophoc);
        
        JButton btnHocsinh = new JButton("Học Sinh");
        btnHocsinh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnHocsinh.setBounds(10, 359, 130, 23);
        panel_4.add(btnHocsinh);
        
        JButton btnPhuhuynh = new JButton("Phụ Huynh");
        btnPhuhuynh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnPhuhuynh.setBounds(10, 390, 130, 23);
        panel_4.add(btnPhuhuynh);
        
        JButton btnDangxuat = new JButton("Đăng Xuất");
        btnDangxuat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnDangxuat.setBounds(10, 613, 130, 23);
        panel_4.add(btnDangxuat);

        // ================= TAB CHÍNH =================
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(112, 50, 862, 611);
        getContentPane().add(tabbedPane);

        
        
     // ================= TAB GIÁO VIÊN =================
        JPanel pGiaovien = new JPanel(null);
        pGiaovien.setBackground(Color.WHITE);
        tabbedPane.addTab("Giáo viên", pGiaovien);

        // ================== TABLE MODEL ==================
        String[] colGV = {
            "Mã giáo viên",
            "Họ và tên",
            "Giới tính",
            "Ngày sinh",
            "Địa chỉ",
            "CCCD/CMND",
            "Số điện thoại"
        };
        modelGV = new DefaultTableModel(colGV, 0);

        // ================== PANEL THÔNG TIN ==================
        JPanel panelInfo = new JPanel(null);
        panelInfo.setBounds(45, 57, 707, 140);
        panelInfo.setBorder(BorderFactory.createTitledBorder("Thông tin giáo viên"));
        pGiaovien.add(panelInfo);

        panelInfo.add(new JLabel("Họ và tên:")).setBounds(20, 30, 80, 15);
        txtHotenGV = new JTextField();
        txtHotenGV.setBounds(92, 27, 236, 20);
        panelInfo.add(txtHotenGV);

        panelInfo.add(new JLabel("Mã GV:")).setBounds(20, 60, 80, 15);
        txtMaGV = new JTextField();
        txtMaGV.setBounds(92, 56, 80, 20);
        panelInfo.add(txtMaGV);

        panelInfo.add(new JLabel("Ngày sinh:")).setBounds(20, 90, 80, 15);
        txtNgaysinhGV = new JTextField();
        txtNgaysinhGV.setBounds(92, 87, 236, 20);
        panelInfo.add(txtNgaysinhGV);

        panelInfo.add(new JLabel("Giới tính:")).setBounds(182, 60, 56, 15);
        cbGioiTinhGV = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGioiTinhGV.setBounds(248, 57, 80, 20);
        panelInfo.add(cbGioiTinhGV);

        panelInfo.add(new JLabel("Địa chỉ:")).setBounds(360, 30, 60, 15);
        txtDiachiGV = new JTextField();
        txtDiachiGV.setBounds(450, 27, 210, 20);
        panelInfo.add(txtDiachiGV);

        panelInfo.add(new JLabel("CCCD/CMND:")).setBounds(360, 60, 90, 15);
        txtCmtGV = new JTextField();
        txtCmtGV.setBounds(450, 57, 210, 20);
        panelInfo.add(txtCmtGV);

        panelInfo.add(new JLabel("SĐT:")).setBounds(360, 90, 90, 15);
        txtSdtGV = new JTextField();
        txtSdtGV.setBounds(450, 87, 210, 20);
        panelInfo.add(txtSdtGV);

        // ================== PANEL CHỨC NĂNG ==================
        JPanel panelBtn = new JPanel(null);
        panelBtn.setBounds(45, 207, 707, 40);
        pGiaovien.add(panelBtn);

        txtTimGV = new JTextField();
        txtTimGV.setBounds(10, 10, 120, 22);
        panelBtn.add(txtTimGV);

        JButton btnTimGV = new JButton("Tìm kiếm");
        btnTimGV.setBounds(140, 10, 90, 22);
        panelBtn.add(btnTimGV);

        JButton btnHienTatCaGV = new JButton("Hiển thị tất cả");
        btnHienTatCaGV.setBounds(240, 10, 120, 22);
        panelBtn.add(btnHienTatCaGV);

        JButton btnThemGV = new JButton("Thêm");
        btnThemGV.setBounds(370, 10, 70, 22);
        panelBtn.add(btnThemGV);

        JButton btnSuaGV = new JButton("Sửa");
        btnSuaGV.setBounds(450, 10, 70, 22);
        panelBtn.add(btnSuaGV);

        JButton btnXoaGV = new JButton("Xóa");
        btnXoaGV.setBounds(530, 10, 70, 22);
        panelBtn.add(btnXoaGV);

        JButton btnLamMoiGV = new JButton("Làm mới");
        btnLamMoiGV.setBounds(610, 10, 87, 22);
        panelBtn.add(btnLamMoiGV);

        // ================== PANEL TABLE ==================
        JPanel panelTable = new JPanel(null);
        panelTable.setBounds(45, 264, 707, 320);
        panelTable.setBorder(BorderFactory.createTitledBorder("Danh sách giáo viên"));
        pGiaovien.add(panelTable);

        tableGV = new JTable(modelGV);
        tableGV.setRowHeight(22);
        tableGV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollGV = new JScrollPane(tableGV);
        scrollGV.setBounds(10, 21, 677, 288);
        panelTable.add(scrollGV);

        // LOAD LẦN ĐẦU
        loadTableGV();

        // ================== SỰ KIỆN ==================
        tableGV.getSelectionModel().addListSelectionListener(e -> {
            int row = tableGV.getSelectedRow();
            if (row >= 0) {
                txtMaGV.setText(modelGV.getValueAt(row, 0).toString());
                txtHotenGV.setText(modelGV.getValueAt(row, 1).toString());
                cbGioiTinhGV.setSelectedItem(modelGV.getValueAt(row, 2).toString());
                txtNgaysinhGV.setText(modelGV.getValueAt(row, 3).toString());
                txtDiachiGV.setText(modelGV.getValueAt(row, 4).toString());
                txtCmtGV.setText(modelGV.getValueAt(row, 5).toString());
                txtSdtGV.setText(modelGV.getValueAt(row, 6).toString());
            }
        });

        btnThemGV.addActionListener(e -> {
            try {
                GiaoVienDTO gv = new GiaoVienDTO();
                gv.setIdGiaoVien(txtMaGV.getText());
                gv.setHoTen(txtHotenGV.getText());
                gv.setGioiTinh(cbGioiTinhGV.getSelectedIndex());
                gv.setNgaySinh(chuyenNgay(txtNgaysinhGV.getText()));
                gv.setDiaChi(txtDiachiGV.getText());
                gv.setCccd(txtCmtGV.getText());
                gv.setSdt(txtSdtGV.getText());

                giaoVienBLL.them(gv);
             // sau khi them giao vien thanh cong
                if (loadComboGV != null) {
                    loadComboGV.run();
                }


                loadTableGV();       
                loadComboGVCN();
                loadComboBoxLopDiemDanh();

                JOptionPane.showMessageDialog(null, "Thêm giáo viên thành công!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        
        btnSuaGV.addActionListener(e -> {

           
            if (txtMaGV.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn giáo viên cần sửa!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

         
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn cập nhật thông tin giáo viên này?",
                "Xác nhận sửa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try {
               
                GiaoVienDTO gv = new GiaoVienDTO();
                gv.setIdGiaoVien(txtMaGV.getText());
                gv.setHoTen(txtHotenGV.getText());
                gv.setGioiTinh(cbGioiTinhGV.getSelectedItem().equals("Nam") ? 1 : 0);
                gv.setNgaySinh(chuyenNgay(txtNgaysinhGV.getText()));
                gv.setDiaChi(txtDiachiGV.getText());
                gv.setCccd(txtCmtGV.getText());
                gv.setSdt(txtSdtGV.getText());

              
                giaoVienBLL.sua(gv);

               
                loadTableGV();

               
                JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật giáo viên thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật thất bại!\n" + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });


        btnXoaGV.addActionListener(e -> {
            int row = tableGV.getSelectedRow();

           
            if (row == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn giáo viên cần xóa!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

           
            String idGV = tableGV.getValueAt(row, 0).toString();

            
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xóa giáo viên này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

           
            if (confirm == JOptionPane.YES_OPTION) {
                boolean kq = giaoVienBLL.xoa(idGV);

                if (kq) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Xóa giáo viên thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE
                    );

              
                    loadTableGV();
                    loadComboGVCN();

                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Không thể xóa giáo viên!\nGiáo viên đang được sử dụng.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });


        btnTimGV.addActionListener(e -> {
            modelGV.setRowCount(0);
            for (GiaoVienDTO gv : giaoVienBLL.tim(txtTimGV.getText())) {
                modelGV.addRow(new Object[]{
                    gv.getIdGiaoVien(),
                    gv.getHoTen(),
                    gv.getGioiTinh() == 1 ? "Nam" : "Nữ",
                    gv.getNgaySinh(),
                    gv.getDiaChi(),
                    gv.getCccd(),
                    gv.getSdt()
                });
            }
        });

        btnHienTatCaGV.addActionListener(e -> loadTableGV());

        btnLamMoiGV.addActionListener(e -> {
            txtMaGV.setText("");
            txtHotenGV.setText("");
            txtNgaysinhGV.setText("");
            txtDiachiGV.setText("");
            txtCmtGV.setText("");
            txtSdtGV.setText("");
            txtTimGV.setText("");
            tableGV.clearSelection();
        });

     // ================= TAB LỚP HỌC =================
        pLophoc = new JPanel();
        pLophoc.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Lớp học", pLophoc);
        pLophoc.setLayout(null);

        // ===== BLL =====
        LopHocBLL lopHocBLL = new LopHocBLL();
        GiaoVienBLL giaoVienBLL_Lop = new GiaoVienBLL();

        // ================== PANEL THÔNG TIN ==================
        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin lớp học"));
        panel_3.setBounds(28, 27, 738, 140);
        pLophoc.add(panel_3);

        JLabel lblMaLop = new JLabel("Mã lớp:");
        lblMaLop.setBounds(20, 30, 80, 15);
        panel_3.add(lblMaLop);

        txtMalop = new JTextField();
        txtMalop.setBounds(110, 27, 217, 20);
        panel_3.add(txtMalop);

        JLabel lblTenLop = new JLabel("Tên lớp:");
        lblTenLop.setBounds(20, 60, 80, 15);
        panel_3.add(lblTenLop);

        txtTenlop = new JTextField();
        txtTenlop.setBounds(110, 57, 217, 20);
        panel_3.add(txtTenlop);

        JLabel lblSiSo = new JLabel("Sỉ số:");
        lblSiSo.setBounds(20, 90, 80, 15);
        panel_3.add(lblSiSo);

        textField_16 = new JTextField();
        textField_16.setBounds(110, 87, 217, 20);
        panel_3.add(textField_16);

        JLabel lblGVCN = new JLabel("Giáo viên chủ nhiệm:");
        lblGVCN.setBounds(356, 34, 150, 15);
        panel_3.add(lblGVCN);

        cbChonGVchunhiemlop = new JComboBox<>();
        cbChonGVchunhiemlop.setBounds(510, 30, 200, 22);
        panel_3.add(cbChonGVchunhiemlop);

        // ================== PANEL CHỨC NĂNG ==================
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBounds(28, 178, 738, 40);
        pLophoc.add(panel_1_1);

        txtTimLop = new JTextField();
        txtTimLop.setBounds(10, 10, 120, 22);
        panel_1_1.add(txtTimLop);

        JButton btnTimlop = new JButton("Tìm kiếm");
        btnTimlop.setBounds(140, 10, 90, 22);
        panel_1_1.add(btnTimlop);

        JButton btnHienthitatcalop = new JButton("Hiển thị tất cả");
        btnHienthitatcalop.setBounds(240, 10, 120, 22);
        panel_1_1.add(btnHienthitatcalop);

        JButton btnThemlop = new JButton("Thêm");
        btnThemlop.setBounds(370, 10, 70, 22);
        panel_1_1.add(btnThemlop);

        JButton btnSualop = new JButton("Sửa");
        btnSualop.setBounds(450, 10, 70, 22);
        panel_1_1.add(btnSualop);

        JButton btnXoalop = new JButton("Xóa");
        btnXoalop.setBounds(530, 10, 70, 22);
        panel_1_1.add(btnXoalop);

        JButton btnLammoilop = new JButton("Làm mới");
        btnLammoilop.setBounds(610, 10, 99, 22);
        panel_1_1.add(btnLammoilop);

        // ================== PANEL TABLE ==================
        JPanel panel_5_1 = new JPanel();
        panel_5_1.setLayout(null);
        panel_5_1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Danh sách lớp học"));
        panel_5_1.setBounds(28, 229, 738, 320);
        pLophoc.add(panel_5_1);

        String[] colLop = {"Mã lớp", "Tên lớp", "Sỉ số", "Giáo viên chủ nhiệm"};
        DefaultTableModel modelLop = new DefaultTableModel(colLop, 0);
        JTable tableLop = new JTable(modelLop);
        tableLop.setRowHeight(22);
        tableLop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLop = new JScrollPane(tableLop);
        scrollLop.setBounds(10, 21, 718, 289);
        panel_5_1.add(scrollLop);

        // ================== LOAD COMBO GV ==================
        loadComboGV = () -> {
            cbChonGVchunhiemlop.removeAllItems();
            for (GiaoVienDTO gv : giaoVienBLL_Lop.getAll()) {
                cbChonGVchunhiemlop.addItem(gv);
            }
        };


        // ================== LOAD TABLE ==================
        Runnable loadTableLop = () -> {
            modelLop.setRowCount(0);
            for (LopHocDTO lop : lopHocBLL.getAll()) {
                modelLop.addRow(new Object[]{
                        lop.getIDLOP(),
                        lop.getTENLOP(),
                        lop.getSISO(),
                        lop.getHOTENGIAOVIEN()
                });
            }
        };

        // LOAD BAN ĐẦU
        loadComboGV.run();
        loadTableLop.run();

        // ================== SỰ KIỆN ==================

        // CLICK TABLE
        tableLop.getSelectionModel().addListSelectionListener(e -> {
            int row = tableLop.getSelectedRow();
            if (row >= 0) {
                txtMalop.setText(modelLop.getValueAt(row, 0).toString());
                txtTenlop.setText(modelLop.getValueAt(row, 1).toString());
                textField_16.setText(modelLop.getValueAt(row, 2).toString());
            }
        });

        // THÊM
        btnThemlop.addActionListener(e -> {

          
            if (txtMalop.getText().trim().isEmpty()
                    || txtTenlop.getText().trim().isEmpty()
                    || textField_16.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng điền đầy đủ thông tin lớp học!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

        
            GiaoVienDTO gv = (GiaoVienDTO) cbChonGVchunhiemlop.getSelectedItem();
            if (gv == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn giáo viên chủ nhiệm!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

           
            int siSo;
            try {
                siSo = Integer.parseInt(textField_16.getText().trim());
                if (siSo <= 0) {
                    JOptionPane.showMessageDialog(null, "Sĩ số phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Sĩ số phải là số nguyên!");
                return;
            }

           
            try {
                lopHocBLL.themLopHoc(
                        txtMalop.getText().trim(),
                        txtTenlop.getText().trim(),
                        siSo,
                        gv.getIdGiaoVien()
                );

                loadTableLop.run();
                loadComboGVCN();      
                loadComboHocSinhPhuHuynh();
                loadComboLopHocHocSinh();
                loadComboBoxLopDiemDanh();
                
                JOptionPane.showMessageDialog(null, "Thêm lớp học thành công!");
                txtTimLop.setText("");     // reset tìm kiếm
                tableLop.clearSelection();
                loadTableLop.run();       // load full danh sách


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });


        // SỬA
        btnSualop.addActionListener(e -> {

            
            int row = tableLop.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn lớp học cần sửa!",
                        "Chưa chọn lớp",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

          
            if (txtTenlop.getText().trim().isEmpty()
                    || textField_16.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng điền đầy đủ thông tin để sửa!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            GiaoVienDTO gv = (GiaoVienDTO) cbChonGVchunhiemlop.getSelectedItem();
            if (gv == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn giáo viên chủ nhiệm!");
                return;
            }

            int siSo;
            try {
                siSo = Integer.parseInt(textField_16.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Sĩ số phải là số!");
                return;
            }

            // 👉 SỬA
            try {
                lopHocBLL.suaLopHoc(
                        txtMalop.getText(),
                        txtTenlop.getText(),
                        siSo,
                        gv.getIdGiaoVien()
                );

                loadTableLop.run();
                loadComboLopHocHocSinh();
                JOptionPane.showMessageDialog(null, "Cập nhật lớp học thành công!");
                txtTimLop.setText("");     // reset tìm kiếm
                tableLop.clearSelection();
                loadTableLop.run();       // load full danh sách


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });



        // XÓA
        btnXoalop.addActionListener(e -> {
            int row = tableLop.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn lớp cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa lớp học này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                String maLop = modelLop.getValueAt(row, 0).toString();
                lopHocBLL.xoaLopHoc(maLop);
                loadTableLop.run();
                loadComboGVCN();
                loadComboHocSinhPhuHuynh();
                loadComboLopHocHocSinh();

                JOptionPane.showMessageDialog(null, "🗑️ Xóa lớp học thành công!");
                txtTimLop.setText("");
                loadTableLop.run();

                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });


        // TÌM
        btnTimlop.addActionListener(e -> {
            modelLop.setRowCount(0);

            var ketQua = lopHocBLL.timKiemLopHoc(txtTimLop.getText());

            if (ketQua.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ Không tìm thấy lớp học phù hợp!");
                return;
            }

            for (LopHocDTO lop : ketQua) {
                modelLop.addRow(new Object[]{
                        lop.getIDLOP(),
                        lop.getTENLOP(),
                        lop.getSISO(),
                        lop.getHOTENGIAOVIEN()
                });
            }
        });


        // HIỂN THỊ TẤT CẢ
        btnHienthitatcalop.addActionListener(e -> loadTableLop.run());

        // LÀM MỚI
        btnLammoilop.addActionListener(e -> {
            txtMalop.setText("");
            txtTenlop.setText("");
            textField_16.setText("");
            txtTimLop.setText("");
            tableLop.clearSelection();
        });


        
     // ================= TAB HỌC SINH =================
        JPanel pHocsinh = new JPanel();
        pHocsinh.setBackground(Color.WHITE);
        tabbedPane.addTab("Học sinh", pHocsinh);
        pHocsinh.setLayout(null);

        // ===== BLL =====
        HocSinhBLL hocSinhBLL = new HocSinhBLL();
       

        // ================== PANEL THÔNG TIN ==================
        JPanel panelHS = new JPanel(null);
        panelHS.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin học sinh"));
        panelHS.setBounds(28, 27, 738, 140);
        pHocsinh.add(panelHS);

        panelHS.add(new JLabel("Mã HS:")).setBounds(20, 30, 80, 15);
        JTextField txtMaHS = new JTextField();
        txtMaHS.setBounds(110, 27, 150, 20);
        panelHS.add(txtMaHS);

        panelHS.add(new JLabel("Họ tên:")).setBounds(20, 60, 80, 15);
        JTextField txtTenHS = new JTextField();
        txtTenHS.setBounds(110, 57, 217, 20);
        panelHS.add(txtTenHS);

        panelHS.add(new JLabel("Ngày sinh:")).setBounds(20, 90, 80, 15);
        JTextField txtNgaySinhHS = new JTextField();
        txtNgaySinhHS.setBounds(110, 87, 150, 20);
        panelHS.add(txtNgaySinhHS);

        panelHS.add(new JLabel("Giới tính:")).setBounds(300, 30, 80, 15);
        JComboBox<String> cbGioiTinhHS = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGioiTinhHS.setBounds(380, 27, 80, 22);
        panelHS.add(cbGioiTinhHS);

        panelHS.add(new JLabel("Lớp:")).setBounds(300, 60, 80, 15);
        JComboBox<LopHocDTO> cbLopHS = new JComboBox<>();
        cbLopHS.setBounds(380, 57, 200, 22);
        panelHS.add(cbLopHS);

        panelHS.add(new JLabel("Địa chỉ:")).setBounds(300, 90, 80, 15);
        JTextField txtDiaChiHS = new JTextField();
        txtDiaChiHS.setBounds(380, 87, 300, 20);
        panelHS.add(txtDiaChiHS);

        // ================== PANEL CHỨC NĂNG ==================
        JPanel panelBtnHS = new JPanel(null);
        panelBtnHS.setBounds(28, 178, 738, 40);
        pHocsinh.add(panelBtnHS);

        JTextField txtTimHS = new JTextField();
        txtTimHS.setBounds(10, 10, 120, 22);
        panelBtnHS.add(txtTimHS);

        JButton btnTimHS = new JButton("Tìm kiếm");
        btnTimHS.setBounds(140, 10, 90, 22);
        panelBtnHS.add(btnTimHS);

        JButton btnHienTatCaHS = new JButton("Hiển thị tất cả");
        btnHienTatCaHS.setBounds(240, 10, 120, 22);
        panelBtnHS.add(btnHienTatCaHS);

        JButton btnThemHS = new JButton("Thêm");
        btnThemHS.setBounds(370, 10, 70, 22);
        panelBtnHS.add(btnThemHS);

        JButton btnSuaHS = new JButton("Sửa");
        btnSuaHS.setBounds(450, 10, 70, 22);
        panelBtnHS.add(btnSuaHS);

        JButton btnXoaHS = new JButton("Xóa");
        btnXoaHS.setBounds(530, 10, 70, 22);
        panelBtnHS.add(btnXoaHS);

        JButton btnLamMoiHS = new JButton("Làm mới");
        btnLamMoiHS.setBounds(610, 10, 99, 22);
        panelBtnHS.add(btnLamMoiHS);

        // ================== PANEL TABLE ==================
        JPanel panelTableHS = new JPanel(null);
        panelTableHS.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Danh sách học sinh"));
        panelTableHS.setBounds(28, 229, 738, 320);
        pHocsinh.add(panelTableHS);

        String[] colHS = {"Mã HS", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Lớp"};
        DefaultTableModel modelHS = new DefaultTableModel(colHS, 0);
        JTable tableHS = new JTable(modelHS);
        tableHS.setRowHeight(22);
        tableHS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollHS = new JScrollPane(tableHS);
        scrollHS.setBounds(10, 21, 718, 289);
        panelTableHS.add(scrollHS);

        // ================== LOAD COMBO LỚP ==================
        Runnable loadComboLopHS = () -> {
            cbLopHS.removeAllItems();
            for (LopHocDTO lop : lopHocBLL.getAll())
 {
                cbLopHS.addItem(lop);
            }
        };

        // ================== LOAD TABLE ==================
        Runnable loadTableHS = () -> {
            modelHS.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (HocSinhDTO hs : hocSinhBLL.getHocSinhList()) {
                modelHS.addRow(new Object[]{
                        hs.getIDHOCSINH(),
                        hs.getHOTENHOCSINH(),
                        hs.getGIOITINH() == 0 ? "Nam" : "Nữ",
                        sdf.format(hs.getNGAYSINH()),
                        hs.getDIACHI(),
                        hs.getTENLOP()
                });
            }
        };

        // LOAD BAN ĐẦU
        loadComboLopHS.run();
        loadTableHS.run();

        // ================== SỰ KIỆN ==================
        tableHS.getSelectionModel().addListSelectionListener(e -> {
            int row = tableHS.getSelectedRow();
            if (row >= 0) {
                txtMaHS.setText(modelHS.getValueAt(row, 0).toString());
                txtTenHS.setText(modelHS.getValueAt(row, 1).toString());
                cbGioiTinhHS.setSelectedItem(modelHS.getValueAt(row, 2).toString());
                txtNgaySinhHS.setText(modelHS.getValueAt(row, 3).toString());
                txtDiaChiHS.setText(modelHS.getValueAt(row, 4).toString());
            }
        });

        // ================== THÊM ==================
        btnThemHS.addActionListener(e -> {

            // ===== 1. KIỂM TRA RỖNG =====
            if (txtMaHS.getText().trim().isEmpty()
                    || txtTenHS.getText().trim().isEmpty()
                    || txtNgaySinhHS.getText().trim().isEmpty()
                    || txtDiaChiHS.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng điền đầy đủ thông tin học sinh!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // ===== 2. KIỂM TRA CHỌN LỚP =====
            if (cbLopHS.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn lớp!");
                return;
            }

            // ===== 3. KIỂM TRA NGÀY SINH =====
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(txtNgaySinhHS.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ngày sinh không hợp lệ (định dạng dd/MM/yyyy)",
                        "Sai ngày sinh",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            
            try {
                HocSinhDTO hs = new HocSinhDTO();
                hs.setIDHOCSINH(txtMaHS.getText());
                hs.setHOTENHOCSINH(txtTenHS.getText());
                hs.setDIACHI(txtDiaChiHS.getText());
                hs.setGIOITINH(cbGioiTinhHS.getSelectedIndex());

                LopHocDTO lop = (LopHocDTO) cbLopHS.getSelectedItem();
                hs.setIDLOP(lop.getIDLOP());

                Date ns = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinhHS.getText());
                hs.setNGAYSINH(ns);

                hocSinhBLL.themHocSinh(hs);
                loadTableHS.run();
                loadComboHocSinhPhuHuynh();
                loadComboBoxHocSinhDiemDanh(
                	    ((LopHocDTO) cbChonlophsDD.getSelectedItem()).getIDLOP()
                		);
                JOptionPane.showMessageDialog(null, "Thêm học sinh thành công!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        // ================== SỬA ==================
        btnSuaHS.addActionListener(e -> {

            // ===== 1. KIỂM TRA CHỌN DÒNG =====
            int row = tableHS.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn học sinh cần sửa!",
                        "Chưa chọn học sinh",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // ===== 2. KIỂM TRA RỖNG =====
            if (txtMaHS.getText().trim().isEmpty()
                    || txtTenHS.getText().trim().isEmpty()
                    || txtNgaySinhHS.getText().trim().isEmpty()
                    || txtDiaChiHS.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng điền đầy đủ thông tin học sinh!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // ===== 3. KIỂM TRA CHỌN LỚP =====
            if (cbLopHS.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn lớp!");
                return;
            }

            // ===== 4. KIỂM TRA NGÀY SINH =====
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(txtNgaySinhHS.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ngày sinh không hợp lệ (dd/MM/yyyy)",
                        "Sai ngày sinh",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // ===== 5. GIỮ NGUYÊN DTO =====
            try {
                HocSinhDTO hs = new HocSinhDTO();
                hs.setIDHOCSINH(txtMaHS.getText());
                hs.setHOTENHOCSINH(txtTenHS.getText());
                hs.setDIACHI(txtDiaChiHS.getText());
                hs.setGIOITINH(cbGioiTinhHS.getSelectedIndex());

                LopHocDTO lop = (LopHocDTO) cbLopHS.getSelectedItem();
                hs.setIDLOP(lop.getIDLOP());

                Date ns = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinhHS.getText());
                hs.setNGAYSINH(ns);

                hocSinhBLL.suaHocSinh(hs);
                loadTableHS.run();
                loadComboHocSinhPhuHuynh();

                JOptionPane.showMessageDialog(null, "Cập nhật học sinh thành công!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        // ================== XOÁ ==================
        btnXoaHS.addActionListener(e -> {

            int row = tableHS.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn học sinh cần xóa!",
                        "Chưa chọn học sinh",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String maHS = modelHS.getValueAt(row, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa học sinh này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    hocSinhBLL.xoaHocSinh(maHS);
                    loadTableHS.run();
                    loadComboHocSinhPhuHuynh();

                    JOptionPane.showMessageDialog(null, "Xóa học sinh thành công!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });


        // ================== TÌM ==================
        btnTimHS.addActionListener(e -> {
            try {
                modelHS.setRowCount(0);
                for (HocSinhDTO hs : hocSinhBLL.timHocSinhTheoTen(txtTimHS.getText())) {
                    modelHS.addRow(new Object[]{
                            hs.getIDHOCSINH(),
                            hs.getHOTENHOCSINH(),
                            hs.getGIOITINH() == 1 ? "Nam" : "Nữ",
                            new SimpleDateFormat("dd/MM/yyyy").format(hs.getNGAYSINH()),
                            hs.getDIACHI(),
                            hs.getTENLOP()
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        // ================== HIỂN THỊ TẤT CẢ ==================
        btnHienTatCaHS.addActionListener(e -> loadTableHS.run());

        // ================== LÀM MỚI ==================
        btnLamMoiHS.addActionListener(e -> {
            txtMaHS.setText("");
            txtTenHS.setText("");
            txtNgaySinhHS.setText("");
            txtDiaChiHS.setText("");
            txtTimHS.setText("");
            tableHS.clearSelection();
        });
        
        
        
        
     // ================= TAB PHỤ HUYNH =================
        JPanel pPhuhuynh = new JPanel();
        pPhuhuynh.setBackground(Color.WHITE);
        tabbedPane.addTab("Phụ huynh", pPhuhuynh);
        pPhuhuynh.setLayout(null);


        // ================== PANEL THÔNG TIN ==================
        JPanel panelPH_Info = new JPanel(null);
        panelPH_Info.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin phụ huynh"));
        panelPH_Info.setBounds(23, 23, 723, 170);
        pPhuhuynh.add(panelPH_Info);

        // --- Mã PH ---
        panelPH_Info.add(new JLabel("Mã phụ huynh:")).setBounds(20, 30, 100, 15);
        JTextField txtMaPH = new JTextField();
        txtMaPH.setBounds(130, 27, 180, 20);
        panelPH_Info.add(txtMaPH);

        // --- Họ tên ---
        panelPH_Info.add(new JLabel("Họ và tên:")).setBounds(20, 60, 100, 15);
        JTextField txtHovatenPH = new JTextField();
        txtHovatenPH.setBounds(130, 57, 180, 20);
        panelPH_Info.add(txtHovatenPH);

        // --- SĐT ---
        panelPH_Info.add(new JLabel("Số điện thoại:")).setBounds(20, 90, 100, 15);
        JTextField txtSodtPH = new JTextField();
        txtSodtPH.setBounds(130, 87, 180, 20);
        panelPH_Info.add(txtSodtPH);

        // --- Email ---
        panelPH_Info.add(new JLabel("Email:")).setBounds(20, 120, 100, 15);
        JTextField txtEmailPH = new JTextField();
        txtEmailPH.setBounds(130, 117, 180, 20);
        panelPH_Info.add(txtEmailPH);

        // --- Học sinh ---
        panelPH_Info.add(new JLabel("Tên học sinh:")).setBounds(360, 30, 100, 15);
        cbChonphHS = new JComboBox<>();
        cbChonphHS.setBounds(470, 27, 200, 22);
        panelPH_Info.add(cbChonphHS);

        // --- Địa chỉ ---
        panelPH_Info.add(new JLabel("Địa chỉ:")).setBounds(360, 60, 100, 15);
        JTextField txtDiachiPH = new JTextField();
        txtDiachiPH.setBounds(470, 57, 200, 80);
        panelPH_Info.add(txtDiachiPH);

        // ================== PANEL CHỨC NĂNG ==================
        JPanel panelPH_Btn = new JPanel(null);
        panelPH_Btn.setBounds(23, 206, 723, 40);
        pPhuhuynh.add(panelPH_Btn);

        JTextField txtTimPH = new JTextField();
        txtTimPH.setBounds(10, 10, 120, 22);
        panelPH_Btn.add(txtTimPH);

        JButton btnTimPH = new JButton("Tìm kiếm");
        btnTimPH.setBounds(140, 10, 90, 22);
        panelPH_Btn.add(btnTimPH);

        JButton btnHienthitatcaPH = new JButton("Hiển thị tất cả");
        btnHienthitatcaPH.setBounds(240, 10, 120, 22);
        panelPH_Btn.add(btnHienthitatcaPH);

        JButton btnThemPH = new JButton("Thêm");
        btnThemPH.setBounds(370, 10, 70, 22);
        panelPH_Btn.add(btnThemPH);

        JButton btnSuaPH = new JButton("Sửa");
        btnSuaPH.setBounds(450, 10, 70, 22);
        panelPH_Btn.add(btnSuaPH);

        JButton btnXoaPH = new JButton("Xóa");
        btnXoaPH.setBounds(530, 10, 70, 22);
        panelPH_Btn.add(btnXoaPH);

        JButton btnLammoiPH = new JButton("Làm mới");
        btnLammoiPH.setBounds(610, 10, 103, 22);
        panelPH_Btn.add(btnLammoiPH);

        // ================== PANEL TABLE ==================
        JPanel panelPH_Table = new JPanel(null);
        panelPH_Table.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Danh sách phụ huynh"));
        panelPH_Table.setBounds(23, 257, 723, 320);
        pPhuhuynh.add(panelPH_Table);

        String[] colPH = {"Mã PH", "Họ tên", "SĐT", "Email", "Địa chỉ", "Học sinh"};
        DefaultTableModel modelPH = new DefaultTableModel(colPH, 0);
        JTable tablePH = new JTable(modelPH);
        tablePH.setRowHeight(22);
        tablePH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPH = new JScrollPane(tablePH);
        scrollPH.setBounds(10, 22, 703, 288);
        panelPH_Table.add(scrollPH);


        // ================== LOAD TABLE ==================
        Runnable loadTablePH = () -> {
            modelPH.setRowCount(0);
            for (PhuHuynhDTO ph : phuHuynhBLL.getPhuHuynhList()) {
                modelPH.addRow(new Object[]{
                        ph.getIDPHUHUYNH(),
                        ph.getTENPHUHUYNH(),
                        ph.getSDT(),
                        ph.getEMAIL(),
                        ph.getDIACHI(),
                        ph.getHOTENHOCSINH()
                });
            }
        };

        loadTablePH.run();

        // ================== SỰ KIỆN ==================

        // CLICK TABLE
        tablePH.getSelectionModel().addListSelectionListener(e -> {
            int row = tablePH.getSelectedRow();
            if (row >= 0) {
                txtMaPH.setText(modelPH.getValueAt(row, 0).toString());
                txtHovatenPH.setText(modelPH.getValueAt(row, 1).toString());
                txtSodtPH.setText(modelPH.getValueAt(row, 2).toString());
                txtEmailPH.setText(modelPH.getValueAt(row, 3).toString());
                txtDiachiPH.setText(modelPH.getValueAt(row, 4).toString());
            }
        });

        // THÊM
        btnThemPH.addActionListener(e -> {
            try {
                PhuHuynhDTO ph = new PhuHuynhDTO();
                ph.setIDPHUHUYNH(txtMaPH.getText().trim());
                ph.setTENPHUHUYNH(txtHovatenPH.getText().trim());
                ph.setSDT(txtSodtPH.getText().trim());
                ph.setEMAIL(txtEmailPH.getText().trim());
                ph.setDIACHI(txtDiachiPH.getText().trim());

                HocSinhDTO hs = (HocSinhDTO) cbChonphHS.getSelectedItem();
                if (hs == null) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Chưa có học sinh để chọn",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                ph.setIDHOCSINH(hs.getIDHOCSINH());

                // ⭐ DÒNG CÓ THỂ THROW IllegalArgumentException
                phuHuynhBLL.themPhuHuynh(ph);

                JOptionPane.showMessageDialog(
                    this,
                    "Thêm phụ huynh thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
                );

                loadTablePH.run();
                

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Lỗi nhập liệu",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Có lỗi xảy ra khi thêm phụ huynh!",
                    "Lỗi hệ thống",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });


        // SỬA
        btnSuaPH.addActionListener(e -> {
            try {
                // 1️⃣ Kiểm tra có chọn dòng chưa
                if (txtMaPH.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn phụ huynh cần sửa!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                // 2️⃣ Xác nhận sửa
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn sửa thông tin phụ huynh không?",
                    "Xác nhận sửa",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                // 3️⃣ Lấy dữ liệu từ form
                PhuHuynhDTO ph = new PhuHuynhDTO();
                ph.setIDPHUHUYNH(txtMaPH.getText().trim());
                ph.setTENPHUHUYNH(txtHovatenPH.getText().trim());
                ph.setSDT(txtSodtPH.getText().trim());
                ph.setEMAIL(txtEmailPH.getText().trim());
                ph.setDIACHI(txtDiachiPH.getText().trim());

                HocSinhDTO hs = (HocSinhDTO) cbChonphHS.getSelectedItem();
                if (hs == null) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Chưa có học sinh để chọn",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                ph.setIDHOCSINH(hs.getIDHOCSINH());

                // 4️⃣ Gọi BLL (có thể throw IllegalArgumentException)
                phuHuynhBLL.suaPhuHuynh(ph);

                // 5️⃣ Thông báo thành công
                JOptionPane.showMessageDialog(
                    this,
                    "Sửa thông tin phụ huynh thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // 6️⃣ Reload bảng
                loadTablePH.run();

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Lỗi nhập liệu",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Có lỗi xảy ra khi sửa phụ huynh!",
                    "Lỗi hệ thống",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });



     // ================== XOÁ PHỤ HUYNH ==================
        btnXoaPH.addActionListener(e -> {

            int row = tablePH.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vui lòng chọn phụ huynh cần xóa!",
                        "Chưa chọn phụ huynh",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String maPH = modelPH.getValueAt(row, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa phụ huynh này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    phuHuynhBLL.xoaPhuHuynh(maPH);
                    loadTablePH.run();

                    JOptionPane.showMessageDialog(
                            null,
                            "Xóa phụ huynh thành công!",
                            "Thành công",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });


        // TÌM
        btnTimPH.addActionListener(e -> {
            try {
                String keyword = txtTimPH.getText().trim();

                if (keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập từ khóa tìm kiếm!",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                modelPH.setRowCount(0);

                for (PhuHuynhDTO ph : phuHuynhBLL.timKiemPhuHuynh(keyword)) {
                    modelPH.addRow(new Object[]{
                        ph.getIDPHUHUYNH(),
                        ph.getTENPHUHUYNH(),
                        ph.getSDT(),
                        ph.getEMAIL(),
                        ph.getDIACHI(),
                        ph.getHOTENHOCSINH()
                    });
                }

                // Không tìm thấy
                if (modelPH.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy phụ huynh phù hợp!",
                        "Kết quả tìm kiếm",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Lỗi nhập liệu",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Có lỗi xảy ra khi tìm phụ huynh!",
                    "Lỗi hệ thống",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });

        // HIỂN THỊ TẤT CẢ
        btnHienthitatcaPH.addActionListener(e -> loadTablePH.run());

        // LÀM MỚI
        btnLammoiPH.addActionListener(e -> {
            txtMaPH.setText("");
            txtHovatenPH.setText("");
            txtSodtPH.setText("");
            txtEmailPH.setText("");
            txtDiachiPH.setText("");
            txtTimPH.setText("");
            tablePH.clearSelection();
        });

        
        

     // ================= TAB TÀI KHOẢN =================

        pTaikhoan = new JPanel();
        pTaikhoan.setBackground(Color.WHITE);
        pTaikhoan.setLayout(null);
        tabbedPane.addTab("Tài khoản", pTaikhoan);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(null);
        panel_3_1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin tài khoản"));
        panel_3_1.setBounds(26, 33, 730, 140);
        pTaikhoan.add(panel_3_1);

        JLabel lblMaTK = new JLabel("Mã tài khoản:");
        lblMaTK.setBounds(152, 32, 90, 15);
        panel_3_1.add(lblMaTK);

        txtMaTK = new JTextField();
        txtMaTK.setBounds(242, 29, 217, 20);
        panel_3_1.add(txtMaTK);

        JLabel lblTenTK = new JLabel("Tên tài khoản:");
        lblTenTK.setBounds(152, 62, 90, 15);
        panel_3_1.add(lblTenTK);

        txtTenTK = new JTextField();
        txtTenTK.setBounds(242, 59, 217, 20);
        panel_3_1.add(txtTenTK);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setBounds(152, 92, 90, 15);
        panel_3_1.add(lblMatKhau);

        txtMatkhauTK = new JTextField();
        txtMatkhauTK.setBounds(242, 89, 217, 20);
        panel_3_1.add(txtMatkhauTK);

        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBounds(26, 184, 730, 40);
        pTaikhoan.add(panel_1_1_1);

        txtTimTK = new JTextField();
        txtTimTK.setBounds(10, 10, 120, 22);
        panel_1_1_1.add(txtTimTK);

        JButton btnTimTK = new JButton("Tìm kiếm");
        btnTimTK.setBounds(140, 10, 90, 22);
        panel_1_1_1.add(btnTimTK);

        JButton btnHienthitatcaTK = new JButton("Hiển thị tất cả");
        btnHienthitatcaTK.setBounds(240, 10, 120, 22);
        panel_1_1_1.add(btnHienthitatcaTK);

        JButton btnThemTK = new JButton("Thêm");
        btnThemTK.setBounds(370, 10, 70, 22);
        panel_1_1_1.add(btnThemTK);

        JButton btnSuaTK = new JButton("Sửa");
        btnSuaTK.setBounds(450, 10, 70, 22);
        panel_1_1_1.add(btnSuaTK);

        JButton btnXoaTK = new JButton("Xóa");
        btnXoaTK.setBounds(530, 10, 70, 22);
        panel_1_1_1.add(btnXoaTK);

        JButton btnLammoiTK = new JButton("Làm mới");
        btnLammoiTK.setBounds(610, 10, 90, 22);
        panel_1_1_1.add(btnLammoiTK);

        JPanel panel_5_4 = new JPanel();
        panel_5_4.setLayout(null);
        panel_5_4.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Danh sách tài khoản"));
        panel_5_4.setBounds(24, 239, 732, 320);
        pTaikhoan.add(panel_5_4);

        table_2 = new JTable();
        JScrollPane scroll = new JScrollPane(table_2);
        scroll.setBounds(10, 21, 710, 288);
        panel_5_4.add(scroll);

        // ===== LOAD DATA =====
        modelTaiKhoan = new DefaultTableModel(
                new String[]{"Mã tài khoản", "Tên tài khoản", "Mật khẩu"}, 0);
        table_2.setModel(modelTaiKhoan);

        for (TaiKhoanDTO tk : taiKhoanBLL.getTaiKhoanList()) {
            modelTaiKhoan.addRow(new Object[]{
                    tk.getIdTaiKhoan(),
                    tk.getTenTaiKhoan(),
                    tk.getMatKhau()
            });
        }

        // ===== EVENT =====
        table_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table_2.getSelectedRow();
                txtMaTK.setText(modelTaiKhoan.getValueAt(row, 0).toString());
                txtTenTK.setText(modelTaiKhoan.getValueAt(row, 1).toString());
                txtMatkhauTK.setText(modelTaiKhoan.getValueAt(row, 2).toString());
                txtMaTK.setEnabled(false);
            }
        });

        btnThemTK.addActionListener(e -> {
            try {
                taiKhoanBLL.themTaiKhoan(
                        txtMaTK.getText(),
                        txtTenTK.getText(),
                        txtMatkhauTK.getText()
                );

                modelTaiKhoan.addRow(new Object[]{
                        txtMaTK.getText(),
                        txtTenTK.getText(),
                        txtMatkhauTK.getText()
                });

                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });


        btnSuaTK.addActionListener(e -> {
            int row = table_2.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn tài khoản cần sửa",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                taiKhoanBLL.suaTaiKhoan(
                        txtMaTK.getText(),
                        txtTenTK.getText(),
                        txtMatkhauTK.getText()
                );

                modelTaiKhoan.setValueAt(txtTenTK.getText(), row, 1);
                modelTaiKhoan.setValueAt(txtMatkhauTK.getText(), row, 2);

                JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });


        btnXoaTK.addActionListener(e -> {
            int row = table_2.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn tài khoản cần xóa",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa tài khoản này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                taiKhoanBLL.xoaTaiKhoan(txtMaTK.getText());
                modelTaiKhoan.removeRow(row);

                JOptionPane.showMessageDialog(this, "Xóa thành công");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });


        btnTimTK.addActionListener(e -> {
            String key = txtTimTK.getText().trim().toLowerCase();
            modelTaiKhoan.setRowCount(0);

            for (TaiKhoanDTO tk : taiKhoanBLL.getTaiKhoanList()) {
                if (tk.getIdTaiKhoan().toLowerCase().contains(key)
                 || tk.getTenTaiKhoan().toLowerCase().contains(key)) {

                    modelTaiKhoan.addRow(new Object[]{
                            tk.getIdTaiKhoan(),
                            tk.getTenTaiKhoan(),
                            tk.getMatKhau()
                    });
                }
            }
        });

        btnHienthitatcaTK.addActionListener(e -> {
            modelTaiKhoan.setRowCount(0);
            for (TaiKhoanDTO tk : taiKhoanBLL.getTaiKhoanList()) {
                modelTaiKhoan.addRow(new Object[]{
                        tk.getIdTaiKhoan(),
                        tk.getTenTaiKhoan(),
                        tk.getMatKhau()
                });
            }
        });

        btnLammoiTK.addActionListener(e -> {
            txtMaTK.setText("");
            txtTenTK.setText("");
            txtMatkhauTK.setText("");
            txtMaTK.setEnabled(true);
            table_2.clearSelection();
        });

        
        // ================= TAB DANH SÁCH =================
        
        table_3 = new JTable();
        table_3.setModel(modelDD);
        table_3.setBounds(10, 21, 712, 288);
        panel_5_4.add(table_3);
        pDanhsach = new JPanel();
        pDanhsach.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Danh sách", pDanhsach);
        pDanhsach.setLayout(null);
        
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBounds(10, 11, 770, 584);
        pDanhsach.add(tabbedPane_1);
        
        // ================= TAB DANH SÁCH ĐIỂM DANH =================
        
        JPanel pDiemdanh = new JPanel();
        pDiemdanh.setBackground(new Color(255, 255, 255));
        tabbedPane_1.addTab("Điểm danh", null, pDiemdanh, null);
        pDiemdanh.setLayout(null);
     

        
        JPanel panel_2_1_1 = new JPanel();
        panel_2_1_1.setLayout(null);
        panel_2_1_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Thông tin danh sách điểm danh"));
        panel_2_1_1.setBounds(26, 11, 718, 150);
        pDiemdanh.add(panel_2_1_1);
        
        JLabel lblHoten_1_1_1 = new JLabel("Mã điểm danh:");
        lblHoten_1_1_1.setBounds(20, 30, 80, 15);
        panel_2_1_1.add(lblHoten_1_1_1);
        
        txtMaDD = new JTextField();
        txtMaDD.setBounds(110, 27, 217, 20);
        panel_2_1_1.add(txtMaDD);
        
        JLabel lblMaGV_1_1_1 = new JLabel("Tên học sinh:");
        lblMaGV_1_1_1.setBounds(20, 60, 80, 15);
        panel_2_1_1.add(lblMaGV_1_1_1);
        
        JLabel lblNgaySinh_1_1_2 = new JLabel("Tên lớp:");
        lblNgaySinh_1_1_2.setBounds(20, 90, 80, 15);
        panel_2_1_1.add(lblNgaySinh_1_1_2);
        
        JLabel lblDiachi_1_1_1 = new JLabel("Trạng thái:");
        lblDiachi_1_1_1.setBounds(353, 30, 75, 15);
        panel_2_1_1.add(lblDiachi_1_1_1);
        
        JLabel lblCCCD_1_1_1 = new JLabel("Lý do:");
        lblCCCD_1_1_1.setBounds(353, 60, 61, 15);
        panel_2_1_1.add(lblCCCD_1_1_1);
        
        JLabel lblNgaySinh_1_1_1_1 = new JLabel("Ngày điểm danh:");
        lblNgaySinh_1_1_1_1.setBounds(20, 119, 102, 15);
        panel_2_1_1.add(lblNgaySinh_1_1_1_1);
        
        
        txtNgayDD = new JTextField();
        txtNgayDD.setBounds(110, 116, 217, 20);
        panel_2_1_1.add(txtNgayDD);
        
        JLabel lblMaGV_1_1_1_1 = new JLabel("Tên giáo viên:");
        lblMaGV_1_1_1_1.setBounds(353, 90, 80, 15);
        panel_2_1_1.add(lblMaGV_1_1_1_1);
        
        txtTrangthaiDD = new JTextField();
        txtTrangthaiDD.setBounds(443, 27, 217, 20);
        panel_2_1_1.add(txtTrangthaiDD);
        
        txtLydoDD = new JTextField();
        txtLydoDD.setBounds(443, 57, 217, 20);
        panel_2_1_1.add(txtLydoDD);
        
        cbChonGVDD = new JComboBox<>();
        cbChonGVDD.setBounds(443, 86, 113, 22);
        panel_2_1_1.add(cbChonGVDD);
        
        cbChonHSDD = new JComboBox<>();
        cbChonHSDD.setBounds(110, 58, 113, 22);
        panel_2_1_1.add(cbChonHSDD);
        
        cbChonlophsDD = new JComboBox<>();
        cbChonlophsDD.setBounds(110, 86, 113, 22);
        panel_2_1_1.add(cbChonlophsDD);
        
        cbChonlophsDD.addActionListener(e -> {
            LopHocDTO lop = (LopHocDTO) cbChonlophsDD.getSelectedItem();
            if (lop != null) {

                loadComboBoxHocSinhDiemDanh(lop.getIDLOP());
                loadComboBoxGiaoVienTheoLop(lop.getIDLOP());
            }
        });

        
        JPanel panel_1_2_1_1 = new JPanel();
        panel_1_2_1_1.setLayout(null);
        panel_1_2_1_1.setBounds(36, 163, 718, 40);
        pDiemdanh.add(panel_1_2_1_1);
        
        txtTimDD = new JTextField();
        txtTimDD.setBounds(10, 10, 120, 22);
        panel_1_2_1_1.add(txtTimDD);
        
        JButton btnTimDD = new JButton("Tìm kiếm");
        btnTimDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnTimDD.setBounds(140, 10, 90, 22);
        panel_1_2_1_1.add(btnTimDD);
        
        
        JButton btnHienthitatcaDD = new JButton("Hiển thị tất cả");
        btnHienthitatcaDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnHienthitatcaDD.setBounds(240, 10, 120, 22);
        panel_1_2_1_1.add(btnHienthitatcaDD);
        
        btnHienthitatcaDD.addActionListener(e -> {
            loadDanhSachDiemDanh();
        });
        JButton btnThemDD = new JButton("Thêm");
        btnThemDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThemDD.setBounds(370, 10, 70, 22);
        panel_1_2_1_1.add(btnThemDD);
        

        
        JButton btnSuaDD = new JButton("Sửa");
        btnSuaDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSuaDD.setBounds(450, 10, 70, 22);
        panel_1_2_1_1.add(btnSuaDD);
        
        
        JButton btnXoaDD = new JButton("Xóa");
        btnXoaDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnXoaDD.setBounds(530, 10, 70, 22);
        panel_1_2_1_1.add(btnXoaDD);
        
        
        JButton btnLammoiDD = new JButton("Làm mới");
        btnLammoiDD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLammoiDD.setBounds(610, 10, 98, 22);
        panel_1_2_1_1.add(btnLammoiDD);
        
        
        JPanel panel_5_5 = new JPanel();
        panel_5_5.setLayout(null);
        panel_5_5.setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY),"Danh sách điểm danh"));
        panel_5_5.setBounds(37, 214, 707, 320);
        pDiemdanh.add(panel_5_5);
        
        String[] colDD = {
        	    "Mã điểm danh",
        	    "Học sinh",
        	    "Lớp",
        	    "Ngày điểm danh",
        	    "Trạng thái",
        	    "Lý do",
        	    "Giáo viên"
        	};

        	modelDD = new DefaultTableModel(colDD, 0);
        	tableDD = new JTable(modelDD);
        	tableDD.setModel(modelDD);
        	tableDD.setRowHeight(22);
        	tableDD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        JScrollPane scrollDD = new JScrollPane(tableDD);
        scrollDD.setBounds(10, 20, 685, 285);
        panel_5_5.add(scrollDD);
        
        btnTimDD.addActionListener(e -> {
            modelDD.setRowCount(0);
            for (DiemDanhDTO d : diemDanhBLL.timKiemDiemDanh(txtTimDD.getText())) {
                modelDD.addRow(new Object[]{
                    d.getIdDiemDanh(),
                    d.getHoTenHocSinh(),
                    d.getTenLop(),
                    d.getNgayDiemDanh(),
                    d.getTrangThai(),
                    d.getLyDo(),
                    d.getHoTenGiaoVien()
                });
            }
        });
        
        btnThemDD.addActionListener(e -> {
            try {
                HocSinhDTO hs = (HocSinhDTO) cbChonHSDD.getSelectedItem();
                LopHocDTO lop = (LopHocDTO) cbChonlophsDD.getSelectedItem();
                GiaoVienDTO gv = (GiaoVienDTO) cbChonGVDD.getSelectedItem();

                if (hs == null || lop == null || gv == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ học sinh, lớp và giáo viên!");
                    return;
                }

                diemDanhBLL.themDiemDanh(
                    txtMaDD.getText(),
                    hs.getIDHOCSINH(),
                    lop.getIDLOP(),
                    txtNgayDD.getText(),
                    txtTrangthaiDD.getText(),
                    txtLydoDD.getText(),
                    gv.getIdGiaoVien()
                );
                loadDanhSachDiemDanh();
                JOptionPane.showMessageDialog(this, "Thêm điểm danh thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        
        btnSuaDD.addActionListener(e -> {
            if (txtMaDD.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn điểm danh cần sửa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn sửa điểm danh này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                HocSinhDTO hs = (HocSinhDTO) cbChonHSDD.getSelectedItem();
                LopHocDTO lop = (LopHocDTO) cbChonlophsDD.getSelectedItem();
                GiaoVienDTO gv = (GiaoVienDTO) cbChonGVDD.getSelectedItem();

                if (hs == null || lop == null || gv == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ học sinh, lớp và giáo viên!");
                    return;
                }

                diemDanhBLL.suaDiemDanh(
                    txtMaDD.getText(),
                    hs.getIDHOCSINH(),
                    lop.getIDLOP(),
                    txtNgayDD.getText(),
                    txtTrangthaiDD.getText(),
                    txtLydoDD.getText(),
                    gv.getIdGiaoVien()
                );

                loadDanhSachDiemDanh();
                JOptionPane.showMessageDialog(this, "Cập nhật điểm danh thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        
        btnXoaDD.addActionListener(e -> {
            int row = tableDD.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
                return;
            }

            String idDD = modelDD.getValueAt(row, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xóa điểm danh này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    diemDanhBLL.xoaDiemDanh(idDD);
                    loadDanhSachDiemDanh();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });

        
        btnLammoiDD.addActionListener(e -> {
            txtMaDD.setText("");
            txtNgayDD.setText("");
            txtTrangthaiDD.setText("");
            txtLydoDD.setText("");
            txtTimDD.setText("");
            tableDD.clearSelection();
        });

        
        
        // ================= TAB DANH SÁCH LỊCH DẠY =================

        
        JPanel pLichday = new JPanel();
        pLichday.setBackground(new Color(255, 255, 255));
        tabbedPane_1.addTab("Lịch dạy", null, pLichday, null);
        pLichday.setLayout(null);
        
        JPanel panel_2_1_1_1 = new JPanel();
        panel_2_1_1_1.setLayout(null);
        panel_2_1_1_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Thông tin lịch dạy"));
        panel_2_1_1_1.setBounds(46, 11, 709, 150);
        pLichday.add(panel_2_1_1_1);
        
        JLabel lblHoten_1_1_1_1 = new JLabel("Mã lịch dạy:");
        lblHoten_1_1_1_1.setBounds(20, 30, 80, 15);
        panel_2_1_1_1.add(lblHoten_1_1_1_1);
        
        txtMaLD = new JTextField();
        txtMaLD.setBounds(110, 27, 217, 20);
        panel_2_1_1_1.add(txtMaLD);
        
        JLabel lblMaGV_1_1_1_2 = new JLabel("Ngày dạy:");
        lblMaGV_1_1_1_2.setBounds(20, 60, 80, 15);
        panel_2_1_1_1.add(lblMaGV_1_1_1_2);
        
        JLabel lblNgaySinh_1_1_2_1 = new JLabel("Nội dung dạy:");
        lblNgaySinh_1_1_2_1.setBounds(20, 90, 80, 15);
        panel_2_1_1_1.add(lblNgaySinh_1_1_2_1);
        
        JLabel lblDiachi_1_1_1_1 = new JLabel("Giờ bắt đầu:");
        lblDiachi_1_1_1_1.setBounds(353, 30, 75, 15);
        panel_2_1_1_1.add(lblDiachi_1_1_1_1);
        
        JLabel lblCCCD_1_1_1_1 = new JLabel("Giờ kết thúc:");
        lblCCCD_1_1_1_1.setBounds(353, 60, 61, 15);
        panel_2_1_1_1.add(lblCCCD_1_1_1_1);
        
        txtNoidungday = new JTextField();
        txtNoidungday.setBounds(110, 87, 217, 20);
        panel_2_1_1_1.add(txtNoidungday);
        
        JLabel lblMaGV_1_1_1_1_1 = new JLabel("Tên giáo viên:");
        lblMaGV_1_1_1_1_1.setBounds(353, 90, 80, 15);
        panel_2_1_1_1.add(lblMaGV_1_1_1_1_1);
        
        txtGiobatdauLD = new JTextField();
        txtGiobatdauLD.setBounds(443, 27, 217, 20);
        panel_2_1_1_1.add(txtGiobatdauLD);
        
        txtGioketthucLD = new JTextField();
        txtGioketthucLD.setBounds(443, 57, 217, 20);
        panel_2_1_1_1.add(txtGioketthucLD);
        
        cbChonGVLD = new JComboBox<>();
        cbChonGVLD.setBounds(443, 86, 113, 22);
        panel_2_1_1_1.add(cbChonGVLD);
        
        txtNgayday = new JTextField();
        txtNgayday.setBounds(110, 58, 217, 20);
        panel_2_1_1_1.add(txtNgayday);
        
        JLabel lblMaGV_1_1_1_1_1_1 = new JLabel("Tên lớp:");
        lblMaGV_1_1_1_1_1_1.setBounds(353, 116, 80, 15);
        panel_2_1_1_1.add(lblMaGV_1_1_1_1_1_1);
        
        cbChonLopLD = new JComboBox<>();
        cbChonLopLD.setBounds(443, 112, 113, 22);
        panel_2_1_1_1.add(cbChonLopLD);
        cbChonLopLD.addActionListener(e -> {

            LopHocDTO lop = (LopHocDTO) cbChonLopLD.getSelectedItem();

            if (lop != null) {
                loadGiaoVienTheoLopLichDay(lop.getIDLOP());
            }
        });
        
        JPanel panel_1_2_1_1_1 = new JPanel();
        panel_1_2_1_1_1.setLayout(null);
        panel_1_2_1_1_1.setBounds(46, 172, 709, 40);
        pLichday.add(panel_1_2_1_1_1);
        
        txtTimLD = new JTextField();
        txtTimLD.setBounds(10, 10, 120, 22);
        panel_1_2_1_1_1.add(txtTimLD);
        
        JButton btnTimLD = new JButton("Tìm kiếm");
        btnTimLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnTimLD.setBounds(140, 10, 90, 22);
        panel_1_2_1_1_1.add(btnTimLD);
        
        JButton btnHienthitatcaLD = new JButton("Hiển thị tất cả");
        btnHienthitatcaLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnHienthitatcaLD.setBounds(240, 10, 120, 22);
        panel_1_2_1_1_1.add(btnHienthitatcaLD);
        
        JButton btnThemLD = new JButton("Thêm");
        btnThemLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThemLD.setBounds(370, 10, 70, 22);
        panel_1_2_1_1_1.add(btnThemLD);
        
        JButton btnSuaLD = new JButton("Sửa");
        btnSuaLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSuaLD.setBounds(450, 10, 70, 22);
        panel_1_2_1_1_1.add(btnSuaLD);
        
        JButton btnXoaLD = new JButton("Xóa");
        btnXoaLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnXoaLD.setBounds(530, 10, 70, 22);
        panel_1_2_1_1_1.add(btnXoaLD);
        
        JButton btnLammoiLD = new JButton("Làm mới");
        btnLammoiLD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLammoiLD.setBounds(610, 10, 89, 22);
        panel_1_2_1_1_1.add(btnLammoiLD);
        
        JPanel panel_5_6 = new JPanel();
        panel_5_6.setLayout(null);
        panel_5_6.setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY),"Danh sách lịch dạy"));
        panel_5_6.setBounds(30, 223, 707, 320);
        pLichday.add(panel_5_6);
        String[] colLD = {
        	    "Mã lịch",
        	    "Ngày dạy",
        	    "Giờ bắt đầu",
        	    "Giờ kết thúc",
        	    "Nội dung",
        	    "Giáo viên",
        	    "Lớp"
        	};

        	modelLD = new DefaultTableModel(colLD, 0);
        	tableLD = new JTable(modelLD);
        	tableLD.setModel(modelLD);
        	tableLD.setRowHeight(22);
        	tableLD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        	JScrollPane scrollLD = new JScrollPane(tableLD);
        	scrollLD.setBounds(10, 20, 685, 285);
        	panel_5_6.add(scrollLD);
        	tableLD.addMouseListener(new MouseAdapter() {
        	    @Override
        	    public void mouseClicked(MouseEvent e) {
        	        int row = tableLD.getSelectedRow();
        	        if (row != -1) {
        	            txtMaLD.setText(modelLD.getValueAt(row, 0).toString());
        	            txtNgayday.setText(modelLD.getValueAt(row, 1).toString());
        	            txtGiobatdauLD.setText(modelLD.getValueAt(row, 2).toString());
        	            txtGioketthucLD.setText(modelLD.getValueAt(row, 3).toString());
        	            txtNoidungday.setText(modelLD.getValueAt(row, 4).toString());
        	        }
        	    }
        	});

        	
        	btnTimLD.addActionListener(e -> {
        	    modelLD.setRowCount(0);
        	    for (LichDayDTO ld : lichDayBLL.timKiemLichDay(txtTimLD.getText())) {
        	        modelLD.addRow(new Object[]{
        	            ld.getIDLICH(),
        	            ld.getNGAYDAY(),
        	            ld.getGIOBATDAU(),
        	            ld.getGIOKETTHUC(),
        	            ld.getNOIDUNG(),
        	            ld.getHOTENGIAOVIEN(),
        	            ld.getTENLOP()
        	        });
        	    }
        	});

        	btnHienthitatcaLD.addActionListener(e -> {
        	    loadDanhSachLichDay();
        	});

        	btnThemLD.addActionListener(e -> {

        	    // ===== 1. KIỂM TRA DỮ LIỆU NHẬP =====
        	    if (txtMaLD.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập mã lịch dạy!");
        	        txtMaLD.requestFocus();
        	        return;
        	    }

        	    if (txtNgayday.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập ngày dạy!");
        	        txtNgayday.requestFocus();
        	        return;
        	    }

        	    if (txtGiobatdauLD.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập giờ bắt đầu!");
        	        txtGiobatdauLD.requestFocus();
        	        return;
        	    }

        	    if (txtGioketthucLD.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập giờ kết thúc!");
        	        txtGioketthucLD.requestFocus();
        	        return;
        	    }

        	    if (txtNoidungday.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập nội dung dạy!");
        	        txtNoidungday.requestFocus();
        	        return;
        	    }

        	    // ===== 2. KIỂM TRA COMBOBOX =====
        	    GiaoVienDTO gv = (GiaoVienDTO) cbChonGVLD.getSelectedItem();
        	    LopHocDTO lop = (LopHocDTO) cbChonLopLD.getSelectedItem();

        	    if (gv == null) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn giáo viên!");
        	        cbChonGVLD.requestFocus();
        	        return;
        	    }

        	    if (lop == null) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp!");
        	        cbChonLopLD.requestFocus();
        	        return;
        	    }

        	 // ===== 3. KIỂM TRA GIỜ HỢP LỆ =====
        	    try {

        	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        	        LocalTime gioBatDau = LocalTime.parse(
        	                txtGiobatdauLD.getText().trim(), formatter);

        	        LocalTime gioKetThuc = LocalTime.parse(
        	                txtGioketthucLD.getText().trim(), formatter);

        	        if (!gioKetThuc.isAfter(gioBatDau)) {
        	            JOptionPane.showMessageDialog(
        	                    this,
        	                    "Giờ kết thúc phải sau giờ bắt đầu!",
        	                    "Dữ liệu không hợp lệ",
        	                    JOptionPane.WARNING_MESSAGE
        	            );
        	            txtGioketthucLD.requestFocus();
        	            return;
        	        }

        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(
        	                this,
        	                "Giờ phải đúng định dạng HH:mm (vd: 07:30 hoặc 7:30)",
        	                "Sai định dạng",
        	                JOptionPane.WARNING_MESSAGE
        	        );
        	        txtGiobatdauLD.requestFocus();
        	        return;
        	    }
        	 // ===== 3.5. KIỂM TRA & CHUYỂN ĐỔI NGÀY =====
        	    String ngaySQL = "";

        	    try {
        	        LocalDate localDate = LocalDate.parse(
        	                txtNgayday.getText().trim(),
        	                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        	        );

        	        ngaySQL = localDate.format(
        	                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        	        );

        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(
        	                this,
        	                "Ngày phải đúng định dạng dd/MM/yyyy (vd: 20/03/2026)",
        	                "Sai định dạng ngày",
        	                JOptionPane.WARNING_MESSAGE
        	        );
        	        txtNgayday.requestFocus();
        	        return;
        	    }

        	    // ===== 4. THÊM LỊCH DẠY =====
        	    try {
        	    	System.out.println("Ngày gửi xuống DB: " + ngaySQL);
        	        lichDayBLL.themLichDay(
        	            txtMaLD.getText().trim(),
        	            ngaySQL,
        	            txtGiobatdauLD.getText().trim(),
        	            txtGioketthucLD.getText().trim(),
        	            txtNoidungday.getText().trim(),
        	            gv.getIdGiaoVien(),
        	            lop.getIDLOP()
        	        );

        	        // ===== 5. LÀM MỚI FORM =====
        	        txtMaLD.setText("");
        	        txtNgayday.setText("");
        	        txtGiobatdauLD.setText("");
        	        txtGioketthucLD.setText("");
        	        txtNoidungday.setText("");
        	        tableLD.clearSelection();

        	        JOptionPane.showMessageDialog(
        	            this,
        	            "Thêm lịch dạy thành công!",
        	            "Thành công",
        	            JOptionPane.INFORMATION_MESSAGE
        	        );
        	        loadDanhSachLichDay();

        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(
        	            this,
        	            ex.getMessage(),
        	            "Lỗi",
        	            JOptionPane.ERROR_MESSAGE
        	        );
        	    }
        	});


        	btnSuaLD.addActionListener(e -> {
        	    if (txtMaLD.getText().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch cần sửa!");
        	        return;
        	    }

        	    try {
        	        GiaoVienDTO gv = (GiaoVienDTO) cbChonGVLD.getSelectedItem();
        	        LopHocDTO lop = (LopHocDTO) cbChonLopLD.getSelectedItem();

        	        lichDayBLL.suaLichDay(
        	            txtMaLD.getText(),
        	            txtNgayday.getText(),
        	            txtGiobatdauLD.getText(),
        	            txtGioketthucLD.getText(),
        	            txtNoidungday.getText(),
        	            gv.getIdGiaoVien(),
        	            lop.getIDLOP()
        	        );

        	        loadDanhSachLichDay();
        	        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(this, ex.getMessage());
        	    }
        	});

        	btnXoaLD.addActionListener(e -> {
        	    int row = tableLD.getSelectedRow();
        	    if (row == -1) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
        	        return;
        	    }

        	    String idLich = modelLD.getValueAt(row, 0).toString();

        	    if (JOptionPane.showConfirmDialog(
        	            this,
        	            "Bạn có chắc muốn xóa lịch dạy này?",
        	            "Xác nhận",
        	            JOptionPane.YES_NO_OPTION
        	    ) == JOptionPane.YES_OPTION) {
        	        lichDayBLL.xoaLichDay(idLich);
        	        loadDanhSachLichDay();
        	    }
        	});

        	btnLammoiLD.addActionListener(e -> {
        	    txtMaLD.setText("");
        	    txtNgayday.setText("");
        	    txtGiobatdauLD.setText("");
        	    txtGioketthucLD.setText("");
        	    txtNoidungday.setText("");
        	    txtTimLD.setText("");
        	    tableLD.clearSelection();
        	});


        
        // ================= TAB DANH SÁCH BÁO CÁO SỨC KHỎE =================
        
        table_5 = new JTable();
        table_5.setBounds(10, 21, 677, 288);
        panel_5_6.add(table_5);
        
        JPanel pSuckhoe = new JPanel();
        pSuckhoe.setBackground(new Color(255, 255, 255));
        tabbedPane_1.addTab("Sức khỏe", null, pSuckhoe, null);
        pSuckhoe.setLayout(null);
        
        JPanel panel_2_1_1_1_1 = new JPanel();
        panel_2_1_1_1_1.setLayout(null);
        panel_2_1_1_1_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Thông tin báo cáo sức khỏe"));
        panel_2_1_1_1_1.setBounds(39, 11, 716, 150);
        pSuckhoe.add(panel_2_1_1_1_1);
        
        JLabel lblHoten_1_1_1_1_1 = new JLabel("Mã báo cáo sức khỏe:");
        lblHoten_1_1_1_1_1.setBounds(20, 30, 113, 15);
        panel_2_1_1_1_1.add(lblHoten_1_1_1_1_1);
        
        txtBaocaosuckhoe = new JTextField();
        txtBaocaosuckhoe.setBounds(126, 27, 201, 20);
        panel_2_1_1_1_1.add(txtBaocaosuckhoe);
        
        JLabel lblMaGV_1_1_1_2_1 = new JLabel("Ngày khám:");
        lblMaGV_1_1_1_2_1.setBounds(20, 60, 80, 15);
        panel_2_1_1_1_1.add(lblMaGV_1_1_1_2_1);
        
        JLabel lblNgaySinh_1_1_2_1_1 = new JLabel("Tình trạng:");
        lblNgaySinh_1_1_2_1_1.setBounds(20, 90, 80, 15);
        panel_2_1_1_1_1.add(lblNgaySinh_1_1_2_1_1);
        
        JLabel lblDiachi_1_1_1_1_1 = new JLabel("Cân nặng:");
        lblDiachi_1_1_1_1_1.setBounds(20, 119, 75, 15);
        panel_2_1_1_1_1.add(lblDiachi_1_1_1_1_1);
        
        JLabel lblCCCD_1_1_1_1_1 = new JLabel("Chiều cao:");
        lblCCCD_1_1_1_1_1.setBounds(353, 28, 61, 15);
        panel_2_1_1_1_1.add(lblCCCD_1_1_1_1_1);
        
        txtTinhtrang = new JTextField();
        txtTinhtrang.setBounds(126, 87, 201, 20);
        panel_2_1_1_1_1.add(txtTinhtrang);
        
        JLabel lblMaGV_1_1_1_1_1_2 = new JLabel("Tên lớp:");
        lblMaGV_1_1_1_1_1_2.setBounds(353, 90, 80, 15);
        panel_2_1_1_1_1.add(lblMaGV_1_1_1_1_1_2);
        
        txtCannang = new JTextField();
        txtCannang.setBounds(126, 116, 201, 20);
        panel_2_1_1_1_1.add(txtCannang);
        
        txtChieucao = new JTextField();
        txtChieucao.setBounds(443, 25, 113, 20);
        panel_2_1_1_1_1.add(txtChieucao);
        
        cbChonlopSK = new JComboBox<>();
        cbChonlopSK.setBounds(443, 86, 113, 22);
        panel_2_1_1_1_1.add(cbChonlopSK);
        cbChonlopSK.addActionListener(e -> {

            LopHocDTO lop = (LopHocDTO) cbChonlopSK.getSelectedItem();

            if (lop != null) {
                loadHocSinhTheoLop(lop.getIDLOP());
            }
        });
        
        txtNgaykham = new JTextField();
        txtNgaykham.setBounds(126, 58, 201, 20);
        panel_2_1_1_1_1.add(txtNgaykham);
        
        JLabel lblMaGV_1_1_1_1_1_1_1 = new JLabel("Tên giáo viên:");
        lblMaGV_1_1_1_1_1_1_1.setBounds(353, 116, 80, 15);
        panel_2_1_1_1_1.add(lblMaGV_1_1_1_1_1_1_1);
        
        cbChongvSK = new JComboBox<>();
        cbChongvSK.setBounds(443, 112, 113, 22);
        panel_2_1_1_1_1.add(cbChongvSK);
        cbChongvSK.addActionListener(e -> {

            GiaoVienDTO gv = (GiaoVienDTO) cbChongvSK.getSelectedItem();

            if (gv != null) {
                loadLopTheoGiaoVien(gv.getIdGiaoVien());
            }
        });
        
        JLabel lblMaGV_1_1_1_1_1_2_1 = new JLabel("Tên học sinh:");
        lblMaGV_1_1_1_1_1_2_1.setBounds(353, 57, 80, 15);
        panel_2_1_1_1_1.add(lblMaGV_1_1_1_1_1_2_1);
        
        cbChonHSSK = new JComboBox<>();
        cbChonHSSK.setBounds(443, 53, 113, 22);
        panel_2_1_1_1_1.add(cbChonHSSK);
        
        JPanel panel_1_2_1_1_1_1 = new JPanel();
        panel_1_2_1_1_1_1.setLayout(null);
        panel_1_2_1_1_1_1.setBounds(39, 172, 716, 40);
        pSuckhoe.add(panel_1_2_1_1_1_1);
        
        txtTimBCSK = new JTextField();
        txtTimBCSK.setBounds(10, 10, 120, 22);
        panel_1_2_1_1_1_1.add(txtTimBCSK);
        
        JButton btnTimBCSK = new JButton("Tìm kiếm");
        btnTimBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnTimBCSK.setBounds(140, 10, 90, 22);
        panel_1_2_1_1_1_1.add(btnTimBCSK);
        
        JButton btnHienthitatcaBCSK = new JButton("Hiển thị tất cả");
        btnHienthitatcaBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnHienthitatcaBCSK.setBounds(240, 10, 120, 22);
        panel_1_2_1_1_1_1.add(btnHienthitatcaBCSK);
        
        JButton btnThemBCSK = new JButton("Thêm");
        btnThemBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThemBCSK.setBounds(370, 10, 70, 22);
        panel_1_2_1_1_1_1.add(btnThemBCSK);
        
        JButton btnSuaBCSK = new JButton("Sửa");
        btnSuaBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSuaBCSK.setBounds(450, 10, 70, 22);
        panel_1_2_1_1_1_1.add(btnSuaBCSK);
        
        JButton btnXoaBCSK = new JButton("Xóa");
        btnXoaBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnXoaBCSK.setBounds(530, 10, 70, 22);
        panel_1_2_1_1_1_1.add(btnXoaBCSK);
        
        JButton btnLammoiBCSK = new JButton("Làm mới");
        btnLammoiBCSK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLammoiBCSK.setBounds(610, 10, 96, 22);
        panel_1_2_1_1_1_1.add(btnLammoiBCSK);
        
        JPanel panelDanhSachBCSK = new JPanel();
        panelDanhSachBCSK.setLayout(null);
        panelDanhSachBCSK.setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY),"Danh sách báo cáo sức khỏe"));
        panelDanhSachBCSK.setBounds(39, 223, 707, 320);
        pSuckhoe.add(panelDanhSachBCSK);
        String[] colBCSK = {
        	    "Mã báo cáo", "Ngày khám", "Cân nặng", "Chiều cao",
        	    "Tình trạng", "Học sinh", "Lớp", "Giáo viên"
        	};

        	modelBCSK = new DefaultTableModel(colBCSK, 0);

        	tableBCSK = new JTable(modelBCSK);
        	tableBCSK.setRowHeight(22);
        	tableBCSK.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        	JScrollPane scrollBCSK = new JScrollPane(tableBCSK);
        	scrollBCSK.setBounds(10, 20, 680, 280);
        	panelDanhSachBCSK.add(scrollBCSK);




        	loadDanhSachBaoCaoSucKhoe();

        	tableBCSK.addMouseListener(new MouseAdapter() {
        	    @Override
        	    public void mouseClicked(MouseEvent e) {
        	        int row = tableBCSK.getSelectedRow();
        	        if (row != -1) {
        	            txtBaocaosuckhoe.setText(modelBCSK.getValueAt(row, 0).toString());
        	            txtNgaykham.setText(modelBCSK.getValueAt(row, 1).toString());
        	            txtCannang.setText(modelBCSK.getValueAt(row, 2).toString());
        	            txtChieucao.setText(modelBCSK.getValueAt(row, 3).toString());
        	            txtTinhtrang.setText(modelBCSK.getValueAt(row, 4).toString());
        	        }
        	    }
        	});

        	btnThemBCSK.addActionListener(e -> {
        	    if (txtBaocaosuckhoe.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập mã báo cáo!");
        	        txtBaocaosuckhoe.requestFocus();
        	        return;
        	    }
        	    if (txtNgaykham.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập ngày khám!");
        	        txtNgaykham.requestFocus();
        	        return;
        	    }
        	    if (txtCannang.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập cân nặng!");
        	        txtCannang.requestFocus();
        	        return;
        	    }
        	    if (txtChieucao.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập chiều cao!");
        	        txtChieucao.requestFocus();
        	        return;
        	    }
        	    if (txtTinhtrang.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Chưa nhập tình trạng!");
        	        txtTinhtrang.requestFocus();
        	        return;
        	    }

        	    HocSinhDTO hs = (HocSinhDTO) cbChonHSSK.getSelectedItem();
        	    LopHocDTO lop = (LopHocDTO) cbChonlopSK.getSelectedItem();
        	    GiaoVienDTO gv = (GiaoVienDTO) cbChongvSK.getSelectedItem();

        	    if (hs == null || lop == null || gv == null) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh, lớp và giáo viên!");
        	        return;
        	    }

        	    try {
        	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	    	sdf.setLenient(false); 

        	        Date ngayKham = sdf.parse(txtNgaykham.getText().trim());

        	        BaoCaoSucKhoeDTO bc = new BaoCaoSucKhoeDTO(
        	        	txtBaocaosuckhoe.getText().trim(),
        	            ngayKham,
        	            txtCannang.getText().trim(),
        	            txtChieucao.getText().trim(),
        	            txtTinhtrang.getText().trim(),
        	            hs.getIDHOCSINH(),
        	            lop.getIDLOP(),
        	            gv.getIdGiaoVien()

        	        );

        	        bcskBLL.themBaoCaoSucKhoe(bc);
        	        loadDanhSachBaoCaoSucKhoe();

        	        JOptionPane.showMessageDialog(this, "Thêm báo cáo sức khỏe thành công!");

        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        	    }
        	});
        	
        	btnSuaBCSK.addActionListener(e -> {

        	    int row = tableBCSK.getSelectedRow();
        	    if (row == -1) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn báo cáo cần sửa!");
        	        return;
        	    }

        	    if (txtBaocaosuckhoe.getText().trim().isEmpty()) {
        	        JOptionPane.showMessageDialog(this, "Mã báo cáo không được để trống!");
        	        return;
        	    }

        	    HocSinhDTO hs = (HocSinhDTO) cbChonHSSK.getSelectedItem();
        	    LopHocDTO lop = (LopHocDTO) cbChonlopSK.getSelectedItem();
        	    GiaoVienDTO gv = (GiaoVienDTO) cbChongvSK.getSelectedItem();

        	    if (hs == null || lop == null || gv == null) {
        	        JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh, lớp và giáo viên!");
        	        return;
        	    }

        	    try {
        	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	    	sdf.setLenient(false);
        	    	Date ngayKham = sdf.parse(txtNgaykham.getText().trim());


        	        BaoCaoSucKhoeDTO bc = new BaoCaoSucKhoeDTO(
        	        	txtBaocaosuckhoe.getText().trim(),
        	            ngayKham,
        	            txtCannang.getText().trim(),
        	            txtChieucao.getText().trim(),
        	            txtTinhtrang.getText().trim(),
        	            hs.getIDHOCSINH(),
        	            lop.getIDLOP(),
        	            gv.getIdGiaoVien()
        	        );

        	        bcskBLL.suaBaoCaoSucKhoe(bc);
        	        loadDanhSachBaoCaoSucKhoe();

        	        JOptionPane.showMessageDialog(this, "Cập nhật báo cáo sức khỏe thành công!");

        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        	    }
        	});

        	
        	btnXoaBCSK.addActionListener(e -> {
        	    int row = tableBCSK.getSelectedRow();
        	    if (row == -1) {
        	        JOptionPane.showMessageDialog(this, "Chưa chọn báo cáo!");
        	        return;
        	    }

        	    String id = modelBCSK.getValueAt(row, 0).toString();
        	    if (JOptionPane.showConfirmDialog(this,
        	            "Xóa báo cáo này?",
        	            "Xác nhận",
        	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

        	        bcskBLL.xoaBaoCaoSucKhoe(id);
        	        loadDanhSachBaoCaoSucKhoe();
        	    }
        	});

        	btnTimBCSK.addActionListener(e -> {
        	    modelBCSK.setRowCount(0);
        	    for (BaoCaoSucKhoeDTO bc : bcskBLL.timKiemBaoCaoSucKhoe(txtTimBCSK.getText().trim())) {
        	        modelBCSK.addRow(new Object[]{
        	            bc.getIDBAOCAO(),
        	            bc.getNGAYKHAM(),
        	            bc.getCANNANG(),
        	            bc.getCHIEUCAO(),
        	            bc.getTINHTRANG(),
        	            bc.getHOTENHOCSINH(),
        	            bc.getTENLOP(),
        	            bc.getHOTENGIAOVIEN()
        	        });
        	    }
        	});

        	btnLammoiBCSK.addActionListener(e -> {
        		txtBaocaosuckhoe.setText("");
        	    txtNgaykham.setText("");
        	    txtCannang.setText("");
        	    txtChieucao.setText("");
        	    txtTinhtrang.setText("");
        	    txtTimBCSK.setText("");

        	    table_6.clearSelection();
        	    loadDanhSachBaoCaoSucKhoe();
        	});


        
        // ================= TAB DANH SÁCH NGƯỜI GIÁM HỘ =================
        
        JPanel pChitietNGH = new JPanel();
        pChitietNGH.setBackground(new Color(255, 255, 255));
        tabbedPane_1.addTab("Người giám hộ", null, pChitietNGH, null);
        pChitietNGH.setLayout(null);
        
        JPanel panel_3_2 = new JPanel();
        panel_3_2.setLayout(null);
        panel_3_2.setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY),"Thông tin lớp học"));
        panel_3_2.setBounds(40, 11, 715, 140);
        pChitietNGH.add(panel_3_2);
        
        JLabel lblHoten_2_2 = new JLabel("Tên học sinh:");
        lblHoten_2_2.setBounds(209, 35, 80, 15);
        panel_3_2.add(lblHoten_2_2);
        
        JLabel lblMaGV_2_2 = new JLabel("Tên phụ huynh:");
        lblMaGV_2_2.setBounds(209, 65, 80, 15);
        panel_3_2.add(lblMaGV_2_2);
        
        JLabel lblNgiGimH = new JLabel("Người giám hộ:");
        lblNgiGimH.setBounds(209, 95, 80, 15);
        panel_3_2.add(lblNgiGimH);
        
        cbChonhsGH = new JComboBox<>();
        cbChonhsGH.setBounds(299, 31, 109, 22);
        panel_3_2.add(cbChonhsGH);
        
        cbChonphGHhs = new JComboBox<>();
        cbChonphGHhs.setBounds(299, 61, 109, 22);
        panel_3_2.add(cbChonphGHhs);
        
        txtNguoigiamho = new JTextField();
        txtNguoigiamho.setBounds(298, 92, 110, 20);
        panel_3_2.add(txtNguoigiamho);
        txtNguoigiamho.setColumns(10);
        
        JPanel panel_1_1_2 = new JPanel();
        panel_1_1_2.setLayout(null);
        panel_1_1_2.setBounds(40, 162, 715, 40);
        pChitietNGH.add(panel_1_1_2);
        
        txtTimNGH = new JTextField();
        txtTimNGH.setBounds(10, 10, 120, 22);
        panel_1_1_2.add(txtTimNGH);
        
        JButton btnTimNGH = new JButton("Tìm kiếm");
        btnTimNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnTimNGH.setBounds(140, 10, 90, 22);
        panel_1_1_2.add(btnTimNGH);
        
        JButton btnHienthitatcaNGH = new JButton("Hiển thị tất cả");
        btnHienthitatcaNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnHienthitatcaNGH.setBounds(240, 10, 120, 22);
        panel_1_1_2.add(btnHienthitatcaNGH);
        
        JButton btnThemNGH = new JButton("Thêm");
        btnThemNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThemNGH.setBounds(370, 10, 70, 22);
        panel_1_1_2.add(btnThemNGH);
        
        JButton btnSuaNGH = new JButton("Sửa");
        btnSuaNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSuaNGH.setBounds(450, 10, 70, 22);
        panel_1_1_2.add(btnSuaNGH);
        
        JButton btnXoaNGH = new JButton("Xóa");
        btnXoaNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnXoaNGH.setBounds(530, 10, 70, 22);
        panel_1_1_2.add(btnXoaNGH);
        
        JButton btnLammoiNGH = new JButton("Làm mới");
        btnLammoiNGH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLammoiNGH.setBounds(610, 10, 95, 22);
        panel_1_1_2.add(btnLammoiNGH);
        
        JPanel panel_5_8 = new JPanel();
        panel_5_8.setLayout(null);
        panel_5_8.setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY),"Danh sách người giám hộ"));
        panel_5_8.setBounds(48, 225, 707, 320);
        pChitietNGH.add(panel_5_8);
        String[] colNGH = {
        	    "ID HS",
        	    "Tên học sinh",
        	    "ID PH",
        	    "Tên phụ huynh",
        	    "Người giám hộ"
        	};

        	modelNGH = new DefaultTableModel(colNGH, 0);
        	tableNGH = new JTable(modelNGH);
        	tableNGH.setRowHeight(22);
        	tableNGH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        	JScrollPane scrollNGH = new JScrollPane(tableNGH);
        	scrollNGH.setBounds(10, 21, 677, 288);
        	panel_5_8.add(scrollNGH);
        	tableNGH.addMouseListener(new MouseAdapter() {
        	    @Override
        	    public void mouseClicked(MouseEvent e) {
        	        int row = tableNGH.getSelectedRow();
        	        if (row != -1) {
        	            txtNguoigiamho.setText(
        	                modelNGH.getValueAt(row, 4).toString()
        	            );
        	        }
        	    }
        	});

        
        table_7 = new JTable();
        table_7.setBounds(10, 21, 677, 288);
        panel_5_8.add(table_7);
        
        btnThemNGH.addActionListener(e -> {

            HocSinhDTO hs = (HocSinhDTO) cbChonhsGH.getSelectedItem();
            PhuHuynhDTO ph = (PhuHuynhDTO) cbChonphGHhs.getSelectedItem();

            if (hs == null || ph == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh và phụ huynh!");
                return;
            }

            if (txtNguoigiamho.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa nhập người giám hộ!");
                return;
            }

            try {
                chitietNGHBLL.themChitietNGH(
                    hs.getIDHOCSINH(),
                    ph.getIDPHUHUYNH(),
                    txtNguoigiamho.getText().trim()
                );

                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                loadDanhSachNguoiGiamHo();
            }
        });

        btnSuaNGH.addActionListener(e -> {
            int row = tableNGH.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
                return;
            }

            String idHS = modelNGH.getValueAt(row, 0).toString();
            String idPH = modelNGH.getValueAt(row, 2).toString();

            try {
                chitietNGHBLL.suaChitietNGH(
                    idHS,
                    idPH,
                    txtNguoigiamho.getText().trim()
                );

                loadDanhSachNguoiGiamHo();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnXoaNGH.addActionListener(e -> {
            int row = tableNGH.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
                return;
            }

            String idHS = modelNGH.getValueAt(row, 0).toString();
            String idPH = modelNGH.getValueAt(row, 2).toString();

            if (JOptionPane.showConfirmDialog(
                this,
                "Xóa người giám hộ này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            ) == JOptionPane.YES_OPTION) {

                chitietNGHBLL.xoaChitietNGH(idHS, idPH);
                loadDanhSachNguoiGiamHo();
            }
        });

        btnTimNGH.addActionListener(e -> {
            modelNGH.setRowCount(0);

            for (ChitietNGHDTO ngh :
                 chitietNGHBLL.timKiemChitietNGH(txtTimNGH.getText().trim())) {

                modelNGH.addRow(new Object[]{
                    ngh.getIDHOCSINH(),
                    ngh.getHOTENHOCSINH(),
                    ngh.getIDPHUHUYNH(),
                    ngh.getTENPHUHUYNH(),
                    ngh.getNGUOIGIAMHO()
                });
            }
        });

        btnLammoiNGH.addActionListener(e -> {
            txtNguoigiamho.setText("");
            txtTimNGH.setText("");
            tableNGH.clearSelection();
            loadDanhSachNguoiGiamHo();
        });

        
        // ================= LABEL =================
        
        JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ TRƯỜNG MẪU GIÁO");
        lblNewLabel.setBounds(373, 11, 378, 28);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(lblNewLabel);

        // ===== FRAME =====
        setTitle("Main Form");
        setSize(1010, 715);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
        
    }
    private String chuyenNgay(String ngayNhap) {
        try {
            java.text.SimpleDateFormat f1 = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.text.SimpleDateFormat f2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return f2.format(f1.parse(ngayNhap));
        } catch (Exception e) {
            return ngayNhap;
        }
    }
	private void loadComboGVCN() {
	    DefaultComboBoxModel<GiaoVienDTO> model = new DefaultComboBoxModel<>();

	    List<GiaoVienDTO> listGV = giaoVienBLL.getGiaoVienDangCoLop();

	    for (GiaoVienDTO gv : listGV) {
	        model.addElement(gv);
	    }

	    cbGVCN.setModel(model);
	}
    private void loadTableGV() {
        modelGV.setRowCount(0);
        for (GiaoVienDTO gv : giaoVienBLL.getAll()) {
            modelGV.addRow(new Object[]{
                gv.getIdGiaoVien(),
                gv.getHoTen(),
                gv.getGioiTinh() == 1 ? "Nam" : "Nữ",
                gv.getNgaySinh(),
                gv.getDiaChi(),
                gv.getCccd(),
                gv.getSdt()
            });
        }
    }
    private void loadComboLopHocHocSinh() {

        DefaultComboBoxModel<LopHocDTO> model =
                new DefaultComboBoxModel<>();

        for (LopHocDTO lop : lopHocBLL.getAll()) {
            model.addElement(lop);
        }

        cbLopHocHocSinh.setModel(model);
    }
    private void initTabReload() {
        tabbedPane.addChangeListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            if (index == -1) return;

            String title = tabbedPane.getTitleAt(index);

            if ("Học Sinh".equals(title)) {
            	loadComboLopHocHocSinh();
            }
        });
    }
    private void loadComboHocSinhPhuHuynh() {

        DefaultComboBoxModel<HocSinhDTO> model = new DefaultComboBoxModel<>();

        List<HocSinhDTO> list = phuHuynhBLL.getDanhSachHocSinh();

        for (HocSinhDTO hs : list) {
            model.addElement(hs);
        }

        cbChonphHS.setModel(model);
    }
    
    private void loadDanhSachDiemDanh() {
        modelDD.setRowCount(0);

        for (DiemDanhDTO d : diemDanhBLL.loadDiemDanhList()) {
            modelDD.addRow(new Object[]{
                d.getIdDiemDanh(),
                d.getHoTenHocSinh(),
                d.getTenLop(),
                d.getNgayDiemDanh(),
                d.getTrangThai(),
                d.getLyDo(),
                d.getHoTenGiaoVien()
            });
        }
    }
    private void loadComboBoxLopDiemDanh() {
        cbChonlophsDD.removeAllItems();

        for (LopHocDTO lop : lopHocBLL.getAll()) {
            cbChonlophsDD.addItem(lop);
        }
    }
    private void loadComboBoxHocSinhDiemDanh(String idLop) {
        cbChonHSDD.removeAllItems();

        for (HocSinhDTO hs : hocSinhBLL.getHocSinhTheoLop(idLop)) {
            cbChonHSDD.addItem(hs);
        }
    }
    private void loadComboBoxGiaoVienDiemDanh() {
        cbChonGVDD.removeAllItems();

        for (GiaoVienDTO gv : giaoVienBLL.getAll()) {
            cbChonGVDD.addItem(gv);
        }
    }
    private void initTabDiemDanhEvent() {
        tabbedPane.addChangeListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            if (index == -1) return;

            if ("Điểm danh".equals(tabbedPane.getTitleAt(index))) {
                loadComboBoxLopDiemDanh();
                loadComboBoxGiaoVienDiemDanh();
                loadDanhSachDiemDanh();
            }
        });
    }


    private void initComboDiemDanhEvent() {
    	cbChonlophsDD.addActionListener(e -> {
            LopHocDTO lop = (LopHocDTO) cbChonlophsDD.getSelectedItem();
            if (lop != null) {
                loadComboBoxHocSinhDiemDanh(lop.getIDLOP());
            }
        });
    }
    private void loadComboBoxGiaoVienTheoLop(String idLop) {

        cbChonGVDD.removeAllItems();

        GiaoVienDTO gv = giaoVienBLL.getGiaoVienTheoLop(idLop);

        if (gv != null) {
            cbChonGVDD.addItem(gv);
        }

        // QUAN TRỌNG: cho phép chọn lại
        cbChonGVDD.setEnabled(true);

        // Tự chọn item đầu tiên
        if (cbChonGVDD.getItemCount() > 0) {
            cbChonGVDD.setSelectedIndex(0);
        }
    }

    private void loadDanhSachLichDay() {
        if (modelLD == null) return; 

        modelLD.setRowCount(0);
        for (LichDayDTO ld : lichDayBLL.getLichDayList()) {
            modelLD.addRow(new Object[]{
                ld.getIDLICH(),
                ld.getNGAYDAY(),
                ld.getGIOBATDAU(),
                ld.getGIOKETTHUC(),
                ld.getNOIDUNG(),
                ld.getHOTENGIAOVIEN(),
                ld.getTENLOP()
            });
        }
    }
    private void loadComboBoxLichDay() {

        cbChonGVLD.removeAllItems();

        List<GiaoVienDTO> listGV = giaoVienBLL.getAll();
        System.out.println("List GV size: " + listGV.size());

        for (GiaoVienDTO gv : listGV) {
            cbChonGVLD.addItem(gv);
        }

        System.out.println("Item count GV sau khi load: " + cbChonGVLD.getItemCount());

        cbChonLopLD.removeAllItems();

        List<LopHocDTO> listLop = lopHocBLL.getAll();
        System.out.println("List Lop size: " + listLop.size());

        for (LopHocDTO lop : listLop) {
            cbChonLopLD.addItem(lop);
        }

        System.out.println("Item count Lop sau khi load: " + cbChonLopLD.getItemCount());
    }
    
    private void loadComboBoxLopLichDay() {

        cbChonLopLD.removeAllItems();

        for (LopHocDTO lop : lopHocBLL.getAll()) {
            cbChonLopLD.addItem(lop);
        }

        if (cbChonLopLD.getItemCount() > 0) {
            cbChonLopLD.setSelectedIndex(0);
        }
    }
    private void loadGiaoVienTheoLopLichDay(String idLop) {

        cbChonGVLD.removeAllItems();

        GiaoVienDTO gv = giaoVienBLL.getGiaoVienTheoLop(idLop);

        if (gv != null) {
            cbChonGVLD.addItem(gv);
        }
    }
    private void loadDanhSachBaoCaoSucKhoe() {
        modelBCSK.setRowCount(0); 

        BaoCaoSucKhoeBLL bll = new BaoCaoSucKhoeBLL();
        List<BaoCaoSucKhoeDTO> list = bll.getBaoCaoSucKhoeList();

        for (BaoCaoSucKhoeDTO bc : list) {
            modelBCSK.addRow(new Object[]{
                bc.getIDBAOCAO(),
                bc.getNGAYKHAM(),          
                bc.getCANNANG(),
                bc.getCHIEUCAO(),
                bc.getTINHTRANG(),
                bc.getHOTENHOCSINH(),
                bc.getTENLOP(),
                bc.getHOTENGIAOVIEN()
            });
        }
    }
    private void loadLopTheoGiaoVien(String idGV) {

        cbChonlopSK.removeAllItems();

        List<LopHocDTO> list = lopHocBLL.getLopTheoGiaoVien(idGV);

        for (LopHocDTO lop : list) {
            cbChonlopSK.addItem(lop);
        }

        if (cbChonlopSK.getItemCount() > 0) {
            cbChonlopSK.setSelectedIndex(0);
        }
    }
    private void loadHocSinhTheoLop(String idLop) {

        cbChonHSSK.removeAllItems();

        for (HocSinhDTO hs : hocSinhBLL.getHocSinhTheoLop(idLop)) {
            cbChonHSSK.addItem(hs);
        }
    }
    private void loadComboBoxGiaoVienSK() {

        cbChongvSK.removeAllItems();

        for (GiaoVienDTO gv : giaoVienBLL.getAll()) {
            cbChongvSK.addItem(gv);
        }

        if (cbChongvSK.getItemCount() > 0) {
            cbChongvSK.setSelectedIndex(0);
        }
    }
    private void initBaoCaoSucKhoe() {
    }
    private void loadComboBoxBCSK() {

        // ===== Load Lớp =====
        cbChonlopSK.removeAllItems();
        for (LopHocDTO lop : lopHocBLL.getAll()) {
            cbChonlopSK.addItem(lop);
        }

        // ===== Load Giáo Viên =====
        cbChongvSK.removeAllItems();
        for (GiaoVienDTO gv : giaoVienBLL.getAll()) {
            cbChongvSK.addItem(gv);
        }

        // ===== Load Học Sinh =====
        cbChonHSSK.removeAllItems();
        for (HocSinhDTO hs : hocSinhBLL.getAll()) {
            cbChonHSSK.addItem(hs);
        }
    }
    private void loadDanhSachNguoiGiamHo() {
        modelNGH.setRowCount(0);

        for (ChitietNGHDTO ct : chitietNGHBLL.getChitietNGHList()) {
            modelNGH.addRow(new Object[] {
                ct.getIDHOCSINH(),
                ct.getHOTENHOCSINH(),
                ct.getIDPHUHUYNH(),
                ct.getTENPHUHUYNH(),
                ct.getNGUOIGIAMHO()
            });
        }
    }
    private void initTableNguoiGiamHo() {

        modelNGH = new DefaultTableModel(
            new String[]{
                "Mã NGH",
                "Học sinh",
                "Phụ huynh",
                "Quan hệ"
            }, 0
        );

        tableNGH = new JTable(modelNGH);

        scrollingNGH = new JScrollPane(tableNGH);
        scrollingNGH.setBounds(10, 21, 677, 288);

    }


    private void loadComboBoxNGH() {

        DefaultComboBoxModel<HocSinhDTO> modelHS = new DefaultComboBoxModel<>();
        for (HocSinhDTO hs : hocSinhBLL.getAll()) {
            modelHS.addElement(hs);
        }
        cbChonhsGH.setModel(modelHS);


        DefaultComboBoxModel<PhuHuynhDTO> modelPH = new DefaultComboBoxModel<>();
        for (PhuHuynhDTO ph : phuHuynhBLL.getAll()) {
            modelPH.addElement(ph);
        }
        cbChonphGHhs.setModel(modelPH);
    }
}
