package thi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class dangnhap extends JFrame   {

	private JPanel contentPane;
	private JTextField tftaikhoan;
	private JPasswordField tfmatkhuau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dangnhap frame = new dangnhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public dangnhap () {
		setTitle("\u0110\u0103ng nh\u1EADp t\u00E0i kho\u1EA3n th\u01B0 vi\u1EC7n\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaikhoan = new JLabel("T\u00E0i Kho\u1EA3n");
		lblTaikhoan.setBounds(55, 71, 89, 20);
		contentPane.add(lblTaikhoan);
		
		tftaikhoan = new JTextField();
		tftaikhoan.setBounds(159, 68, 193, 26);
		contentPane.add(tftaikhoan);
		tftaikhoan.setColumns(10);
		
		JLabel lblmatkhau = new JLabel("M\u1EADt Kh\u1EA9u");
		lblmatkhau.setBounds(55, 156, 89, 20);
		contentPane.add(lblmatkhau);
		
		JButton btndangnhap = new JButton("\u0110\u0103ng Nh\u1EADp");
		btndangnhap.setToolTipText("click vào để đăng nhập");
		btndangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=Library1;integratedSecurity=TRUE");
					if (tftaikhoan.getText().equals("")||tfmatkhuau.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Nhap lai...");
					else
					{
					PreparedStatement st = conn.prepareStatement("Select * from accounts where email=? and pass=?");
					st.setString(1, tftaikhoan.getText());
					st.setString(2, tfmatkhuau.getText());
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");
						 new qltv("Quản lý thư viện").setVisible(true);
						 
						}
					else JOptionPane.showMessageDialog(null, "Dang nhap that bai");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btndangnhap.setBounds(98, 257, 115, 29);
		contentPane.add(btndangnhap);
		
		JButton btnthoat = new JButton("Tho\u00E1t");
		btnthoat.setToolTipText("click vào để thoát khỏi chương trình");
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnthoat.setBounds(283, 257, 115, 29);
		contentPane.add(btnthoat);
		
		tfmatkhuau = new JPasswordField();
		tfmatkhuau.setBounds(159, 156, 193, 26);
		contentPane.add(tfmatkhuau);
	}
}
