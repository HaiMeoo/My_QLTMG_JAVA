package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;

public class Main extends JFrame {
    private JPanel contentPane;
    private JTable dataGridViewTK;
    private JTextField txtMataikhoan;
    private JTextField txtTentaikhoan;
    private JTextField txtMatkhau;
    private JButton btnThemtaikhoan;
    private JButton btnSuataikhoan;
    private JButton btnXoataikhoan;
    private JButton btnLammoitaikhoan;
    
    private TaiKhoanBLL taiKhoanBLL;
    
    public Main() {
        taiKhoanBLL = new TaiKhoanBLL();
        initComponents();
        loadTaiKhoan();
    }
    
    private void initComponents() {
        setTitle("HỆ THỐNG QUẢN LÝ TRUNG TÂM MẦM NON");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        
        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Hệ thống
        JMenu menuHeThong = new JMenu("Hệ thống");
        JMenuItem menuDangXuat = new JMenuItem("Đăng xuất");
        JMenuItem menuThoat = new JMenuItem("Thoát");
        
        menuDangXuat.addActionListener(e -> dangXuat());
        menuThoat.addActionListener(e -> thoatChuongTrinh());
        
        menuHeThong.add(menuDangXuat);
        menuHeThong.addSeparator();
        menuHeThong.add(menuThoat);
        
        // Menu Quản lý
        JMenu menuQuanLy = new JMenu("Quản lý");
        JMenuItem menuQLTaiKhoan = new JMenuItem("Quản lý Tài khoản");
        JMenuItem menuQLGiaoVien = new JMenuItem("Quản lý Giáo viên");
        JMenuItem menuQLHocSinh = new JMenuItem("Quản lý Học sinh");
        JMenuItem menuQLLopHoc = new JMenuItem("Quản lý Lớp học");
        
        menuQuanLy.add(menuQLTaiKhoan);
        menuQuanLy.add(menuQLGiaoVien);
        menuQuanLy.add(menuQLHocSinh);
        menuQuanLy.add(menuQLLopHoc);
        
        // Menu Trợ giúp
        JMenu menuTroGiup = new JMenu("Trợ giúp");
        JMenuItem menuGioiThieu = new JMenuItem("Giới thiệu");
        
        menuTroGiup.add(menuGioiThieu);
        
        menuBar.add(menuHeThong);
        menuBar.add(menuQuanLy);
        menuBar.add(menuTroGiup);
        
        setJMenuBar(menuBar);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Panel chính chứa form và table
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel form nhập liệu
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Mã tài khoản
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblMaTK = new JLabel("Mã tài khoản:");
        lblMaTK.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblMaTK, gbc);
        
        gbc.gridx = 1;
        txtMataikhoan = new JTextField();
        txtMataikhoan.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMataikhoan.setColumns(20);
        formPanel.add(txtMataikhoan, gbc);
        
        // Tên tài khoản
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblTenTK = new JLabel("Tên tài khoản:");
        lblTenTK.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblTenTK, gbc);
        
        gbc.gridx = 1;
        txtTentaikhoan = new JTextField();
        txtTentaikhoan.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTentaikhoan.setColumns(20);
        formPanel.add(txtTentaikhoan, gbc);
        
        // Mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblMatKhau, gbc);
        
        gbc.gridx = 1;
        txtMatkhau = new JTextField();
        txtMatkhau.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMatkhau.setColumns(20);
        formPanel.add(txtMatkhau, gbc);
        
        // Panel nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnThemtaikhoan = new JButton("Thêm");
        btnThemtaikhoan.setFont(new Font("Arial", Font.BOLD, 14));
        btnThemtaikhoan.setBackground(new Color(60, 179, 113));
        btnThemtaikhoan.setForeground(Color.WHITE);
        btnThemtaikhoan.setFocusPainted(false);
        btnThemtaikhoan.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        btnSuataikhoan = new JButton("Sửa");
        btnSuataikhoan.setFont(new Font("Arial", Font.BOLD, 14));
        btnSuataikhoan.setBackground(new Color(70, 130, 180));
        btnSuataikhoan.setForeground(Color.WHITE);
        btnSuataikhoan.setFocusPainted(false);
        btnSuataikhoan.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        btnXoataikhoan = new JButton("Xóa");
        btnXoataikhoan.setFont(new Font("Arial", Font.BOLD, 14));
        btnXoataikhoan.setBackground(new Color(220, 20, 60));
        btnXoataikhoan.setForeground(Color.WHITE);
        btnXoataikhoan.setFocusPainted(false);
        btnXoataikhoan.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        btnLammoitaikhoan = new JButton("Làm mới");
        btnLammoitaikhoan.setFont(new Font("Arial", Font.BOLD, 14));
        btnLammoitaikhoan.setBackground(new Color(105, 105, 105));
        btnLammoitaikhoan.setForeground(Color.WHITE);
        btnLammoitaikhoan.setFocusPainted(false);
        btnLammoitaikhoan.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        buttonPanel.add(btnThemtaikhoan);
        buttonPanel.add(btnSuataikhoan);
        buttonPanel.add(btnXoataikhoan);
        buttonPanel.add(btnLammoitaikhoan);
        
        // Thêm button panel vào form panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        // Panel table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));
        
        // Tạo table model
        String[] columnNames = {"Mã Tài Khoản", "Tên Tài Khoản", "Mật Khẩu"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp trên table
            }
        };
        
        dataGridViewTK = new JTable(tableModel);
        dataGridViewTK.setFont(new Font("Arial", Font.PLAIN, 12));
        dataGridViewTK.setRowHeight(25);
        dataGridViewTK.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        dataGridViewTK.getTableHeader().setBackground(new Color(70, 130, 180));
        dataGridViewTK.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(dataGridViewTK);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm các panel vào main panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(formPanel, BorderLayout.NORTH);
        leftPanel.add(new JPanel(), BorderLayout.CENTER); // Spacer
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Thêm tất cả vào contentPane
        contentPane.add(titlePanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        
        // Thêm sự kiện
        addEventListeners();
    }
    
    private void addEventListeners() {
        // Sự kiện nút Thêm
        btnThemtaikhoan.addActionListener(e -> themTaiKhoan());
        
        // Sự kiện nút Sửa
        btnSuataikhoan.addActionListener(e -> suaTaiKhoan());
        
        // Sự kiện nút Xóa
        btnXoataikhoan.addActionListener(e -> xoaTaiKhoan());
        
        // Sự kiện nút Làm mới
        btnLammoitaikhoan.addActionListener(e -> lamMoiTaiKhoan());
        
        // Sự kiện click trên table
        dataGridViewTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = dataGridViewTK.getSelectedRow();
                if (row >= 0) {
                    txtMataikhoan.setText(dataGridViewTK.getValueAt(row, 0).toString());
                    txtTentaikhoan.setText(dataGridViewTK.getValueAt(row, 1).toString());
                    txtMatkhau.setText(dataGridViewTK.getValueAt(row, 2).toString());
                    txtMataikhoan.setEnabled(false);
                    btnThemtaikhoan.setEnabled(false);
                }
            }
        });
    }
    
    private void loadTaiKhoan() {
        DefaultTableModel model = (DefaultTableModel) dataGridViewTK.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        
        try {
            List<TaiKhoanDTO> taiKhoanList = taiKhoanBLL.getTaiKhoanList();
            
            for (TaiKhoanDTO tk : taiKhoanList) {
                Object[] rowData = {
                    tk.getIdTaiKhoan(),
                    tk.getTenTaiKhoan(),
                    tk.getMatKhau()
                };
                model.addRow(rowData);
            }
            
            if (taiKhoanList.isEmpty()) {
                System.out.println("Danh sách tài khoản trống.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Lỗi khi tải danh sách tài khoản: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void themTaiKhoan() {
        String idTaiKhoan = txtMataikhoan.getText().trim();
        String tenTaiKhoan = txtTentaikhoan.getText().trim();
        String matKhau = txtMatkhau.getText().trim();
        
        if (idTaiKhoan.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập đầy đủ thông tin tài khoản!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            boolean result = taiKhoanBLL.themTaiKhoan(idTaiKhoan, tenTaiKhoan, matKhau);
            
            if (result) {
                loadTaiKhoan();
                JOptionPane.showMessageDialog(this,
                    "Thêm tài khoản thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                lamMoiTaiKhoan();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Thêm tài khoản thất bại!",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Lỗi: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void suaTaiKhoan() {
        String idTaiKhoan = txtMataikhoan.getText().trim();
        String tenTaiKhoan = txtTentaikhoan.getText().trim();
        String matKhau = txtMatkhau.getText().trim();
        
        if (idTaiKhoan.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn tài khoản cần sửa!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            boolean result = taiKhoanBLL.suaTaiKhoan(idTaiKhoan, tenTaiKhoan, matKhau);
            
            if (result) {
                loadTaiKhoan();
                JOptionPane.showMessageDialog(this,
                    "Sửa tài khoản thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                lamMoiTaiKhoan();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Sửa tài khoản thất bại!",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Lỗi: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void xoaTaiKhoan() {
        String idTaiKhoan = txtMataikhoan.getText().trim();
        String tenTaiKhoan = txtTentaikhoan.getText().trim();
        
        if (idTaiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn tài khoản cần xóa!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn chắc chắn muốn xóa tài khoản '" + tenTaiKhoan + "'?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean result = taiKhoanBLL.xoaTaiKhoan(idTaiKhoan);
                
                if (result) {
                    loadTaiKhoan();
                    JOptionPane.showMessageDialog(this,
                        "Xóa tài khoản thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                    lamMoiTaiKhoan();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Xóa tài khoản thất bại!",
                        "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Lỗi: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    private void lamMoiTaiKhoan() {
        txtMataikhoan.setText("");
        txtTentaikhoan.setText("");
        txtMatkhau.setText("");
        txtMataikhoan.setEnabled(true);
        btnThemtaikhoan.setEnabled(true);
        dataGridViewTK.clearSelection();
    }
    
    private void dangXuat() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn đăng xuất?",
            "Xác nhận đăng xuất",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            });
        }
    }
    
    private void thoatChuongTrinh() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn thoát ứng dụng?",
            "Xác nhận thoát",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}