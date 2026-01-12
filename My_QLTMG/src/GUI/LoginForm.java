package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import BLL.TaiKhoanBLL;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JCheckBox cbShowPassword;
    private JButton btnLogin;
    private JButton btnExit;
    
    private TaiKhoanBLL taiKhoanBLL;
    
    public LoginForm() {
        initComponents();
        setupUI();
        centerForm();
        taiKhoanBLL = new TaiKhoanBLL();
    }
    
    private void initComponents() {
        // Thiết lập frame
        setTitle("Đăng Nhập Hệ Thống");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Tạo main panel với BorderLayout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180)); // SteelBlue
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        lblTitle = new JLabel("QUẢN LÝ TRUNG TÂM MẦM NON");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Panel form đăng nhập
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        lblUsername = new JLabel("Tài khoản:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblUsername, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(lblPassword, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(txtPassword, gbc);
        
        // Show password checkbox
        gbc.gridx = 1;
        gbc.gridy = 2;
        cbShowPassword = new JCheckBox("Hiển thị mật khẩu");
        cbShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        cbShowPassword.setBackground(Color.WHITE);
        formPanel.add(cbShowPassword, gbc);
        
        // Panel nút bấm
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnLogin = new JButton("Đăng Nhập");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Arial", Font.BOLD, 14));
        btnExit.setBackground(new Color(220, 20, 60)); // Crimson
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnExit);
        
        // Thêm các panel vào main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm main panel vào frame
        add(mainPanel);
        pack();
        
        // Thêm sự kiện
        addEventListeners();
    }
    
    private void setupUI() {
        // Tạo phím tắt Enter để đăng nhập
        InputMap inputMap = btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnLogin.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "login");
        actionMap.put("login", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });
        
        // Focus vào field username khi mở form
        SwingUtilities.invokeLater(() -> txtUsername.requestFocus());
    }
    
    private void centerForm() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
            (screenSize.width - frameSize.width) / 2,
            (screenSize.height - frameSize.height) / 2
        );
    }
    
    private void addEventListeners() {
        // Sự kiện nút Đăng Nhập
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        // Sự kiện nút Thoát
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        
        // Sự kiện checkbox hiển thị mật khẩu
        cbShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('•');
                }
            }
        });
        
        // Sự kiện đóng cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
        
        // Sự kiện key listener cho password field (Enter để đăng nhập)
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }
    
    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        // Kiểm tra dữ liệu nhập
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập đầy đủ tài khoản và mật khẩu!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Thực hiện đăng nhập
            boolean isLoggedIn = taiKhoanBLL.dangNhap(username, password);
            
            if (isLoggedIn) {
                JOptionPane.showMessageDialog(this,
                    "Đăng nhập thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Mở form chính
                Main mainForm = new Main();
                mainForm.setVisible(true);
                
                // Đóng form đăng nhập
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(this,
                    "Sai tài khoản hoặc mật khẩu!",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
                
                // Xóa password và focus lại
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Lỗi dữ liệu",
                JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Lỗi hệ thống: " + ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void exitApplication() {
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
        // Set Look and Feel để có giao diện đẹp hơn
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Chạy ứng dụng
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });
    }
}