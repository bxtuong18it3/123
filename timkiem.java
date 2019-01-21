package thi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class timkiem extends JFrame  implements ActionListener {

	private JPanel contentPane;
	private String header[] = {"Id", "Title", "Price"};
	private JTable tb;
	private JScrollPane tableResult;
	private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
	private JTextField tftimkiem;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					timkiem frame = new timkiem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public timkiem() {
		setTitle("T\u00ECm ki\u1EBFm S\u00E1ch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 456, 0);
		contentPane.add(label);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(15, 78, 52, 20);
		contentPane.add(lblTitle);
		
		tftimkiem = new JTextField();
		tftimkiem.setBounds(92, 75, 172, 26);
		contentPane.add(tftimkiem);
		tftimkiem.setColumns(10);
		
		
		JButton btnSeach = new JButton("Seach");
		btnSeach.setToolTipText("click vào để tìm kiếm");
		btnSeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement st = null;
				ResultSet rs = null;

				try { 
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn = DriverManager
							.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=Library1;integratedSecurity=TRUE");

				 String sql = "select * from Books ";

				
				 if (lblTitle.getText().length() > 0) {
				   sql = sql + " where title like '%" + tftimkiem.getText() + "%'";
				 }

				
				 st = conn.createStatement();

				 
				 rs = st.executeQuery(sql);
				 Vector data = null;

				 tblModel.setRowCount(0);

				 
				 if (rs.isBeforeFirst() == false) {
				  JOptionPane.showMessageDialog(rootPane, " Không tìm thấy Sách!");
				  return;
				 }

				 // Trong khi chưa hết dữ liệu
				 while (rs.next()) {
				   data = new Vector();
				   data.add(rs.getInt("id"));
				   data.add(rs.getString("title"));
				   data.add(rs.getString("price"));

				   // Thêm một dòng vào table model
				   tblModel.addRow(data);
				 }
				 tb = new JTable(tblModel);
				 tableResult = new JScrollPane(tb);

				 tableResult.setBounds(34, 139, 536, 238);
				 contentPane.add(tableResult);
				} catch (Exception e1) {
				  e1.printStackTrace();
				} finally {
				  try {
				    if (conn != null) {
				      conn.close();
				    }
				    if (st != null) {
				     st.close();
				    }
				    if (rs != null) {
				     rs.close();
				    }
				   } catch (Exception ex) {
				     ex.printStackTrace();
				   }
				}
			}
		});
		btnSeach.setBounds(291, 74, 115, 29);
		contentPane.add(btnSeach);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setToolTipText("click vào để thoát");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnEdit.setBounds(421, 74, 115, 29);
		contentPane.add(btnEdit);
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}




