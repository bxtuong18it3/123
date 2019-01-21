package thi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class them extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfTitle;
	private JTextField tfPrice;
	private qltv mst;
	private JTextField tfid;
	Connection conn;
	Statement stm;
	ResultSet rst;
	int selectedrow = 0;
	public  them(String s, String ID, String Title, String Price, qltv st) {
		super(s);
		mst = st;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 501);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(55, 153, 69, 20);
		contentPane.add(lblTitle);
		
		tfTitle = new JTextField(Title);
		tfTitle.setBounds(165, 150, 258, 26);
		contentPane.add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(55, 211, 69, 20);
		contentPane.add(lblPrice);
		
		tfPrice = new JTextField(Price);
		tfPrice.setBounds(165, 208, 258, 26);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		 
		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("click vào để thêm Sách");
		btnSave.addActionListener(this);
		btnSave.setBounds(87, 286, 115, 29);
		contentPane.add(btnSave);
		
		JButton btnexit = new JButton("Exit");
		btnexit.setToolTipText("click vào để thoát");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnexit.setBounds(339, 286, 115, 29);
		contentPane.add(btnexit);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(63, 90, 61, 20);
		contentPane.add(lblId);
		
		tfid = new JTextField(ID);
		tfid.setBounds(166, 87, 257, 26);
		contentPane.add(tfid);
		tfid.setColumns(10);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Save"))
		{
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=Library1;integratedSecurity=TRUE");
				stm = conn.createStatement();
				String ID =  tfid.getText();
				String Title = tfTitle.getText();
				String sql = "";
				float Price =  Float.parseFloat(tfPrice.getText());
				if(this.getTitle().equals("Them thong tin sach"))
					sql = "insert into Books values('"+ID+"','"+Title+"',"+Price+")";
				else
					sql = "update Books set Title='"+Title+"',Price="+Price+" where ID='"+tfid.getText()+"'" ;
				mst.stm.executeUpdate(sql);
				mst.reload();
				mst.model.fireTableDataChanged();
				this.dispose();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
	}
	
}
