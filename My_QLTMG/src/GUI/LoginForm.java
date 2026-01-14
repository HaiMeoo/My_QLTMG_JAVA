package GUI;

import BLL.TaiKhoanBLL;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox cbShowPassword;

    private TaiKhoanBLL taiKhoanBLL;

    public LoginForm() {
        taiKhoanBLL = new TaiKhoanBLL();

        setTitle("Đăng nhập hệ thống");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 182, 193));

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("QUẢN LÝ TRƯỜNG MẪU GIÁO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBounds(40, 30, 340, 30);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTitle);

        // ===== PANEL FORM =====
        JPanel panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBackground(Color.WHITE);
        panelForm.setBounds(40, 90, 340, 300);
        panelForm.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        getContentPane().add(panelForm);

        // ===== LOGIN LABEL =====
        JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLogin.setBounds(0, 20, 340, 30);
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelForm.add(lblLogin);

        // ===== USERNAME =====
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setBounds(30, 70, 100, 20);
        panelForm.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBounds(30, 95, 280, 35);
        panelForm.add(txtUsername);

        // ===== PASSWORD =====
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setBounds(30, 140, 100, 20);
        panelForm.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBounds(30, 165, 280, 35);
        panelForm.add(txtPassword);

        // ===== SHOW PASSWORD =====
        cbShowPassword = new JCheckBox("Hiện mật khẩu");
        cbShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbShowPassword.setBackground(Color.WHITE);
        cbShowPassword.setBounds(30, 205, 150, 20);
        cbShowPassword.addActionListener(e -> {
            if (cbShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }
        });
        panelForm.add(cbShowPassword);

        // ===== BUTTON LOGIN =====
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(192, 192, 192));
        btnLogin.setFocusPainted(false);
        btnLogin.setBounds(30, 235, 130, 40);

        // 👉 XỬ LÝ ĐĂNG NHẬP
        btnLogin.addActionListener(e -> xuLyDangNhap());

        panelForm.add(btnLogin);

        // ===== BUTTON EXIT =====
        JButton btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExit.setBackground(new Color(192, 192, 192));
        btnExit.setFocusPainted(false);
        btnExit.setBounds(180, 235, 130, 40);
        btnExit.addActionListener(e -> System.exit(0));
        panelForm.add(btnExit);
    }

    // ================= XỬ LÝ ĐĂNG NHẬP =================
    private void xuLyDangNhap() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        try {
            boolean isLoggedIn = taiKhoanBLL.dangNhap(username, password);

            if (isLoggedIn) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");

                // 👉 MỞ MAIN FORM
                MainForm main = new MainForm();
                main.setVisible(true);

                this.dispose(); // đóng login
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sai tài khoản hoặc mật khẩu!",
                        "Lỗi đăng nhập",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
