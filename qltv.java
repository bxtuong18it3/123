package thi;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import thi.timkiem;

public class qltv extends JFrame implements ActionListener, MouseListener {

	Connection conn;
	Statement stm;
	ResultSet rst;

	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	JButton btnsua, btnxoa, btnthem, btntim;
	JTextField tftim;
	int selectedrow = 0;

	public qltv(String s) {
		super(s);
		try {
			tftim = new JTextField("20");
			JPanel p = new JPanel();
			btnsua = new JButton("Chinh Sua");
			btnsua.setToolTipText("click vào để sửa thông tin sách");
			btnsua.addActionListener(this);
			btnxoa = new JButton("Xoa");
			btnxoa.setToolTipText("click vào để xóa sách");
			btnxoa.addActionListener(this);
			btnthem = new JButton("Them");
			btnthem.setToolTipText("click vào để thêm sách");
			btnthem.addActionListener(this);
			btntim = new JButton("Tim Kiem");
			btntim.setToolTipText("click vào để tìm sách");
			btntim.addActionListener(this);

			btntim.addActionListener(this);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=Library1;integratedSecurity=TRUE");
			stm = conn.createStatement();
			p.add(btnsua);
			p.add(btnxoa);
			p.add(btnthem);
			p.add(btntim);

			getContentPane().add(p, "South");
			reload();
			model = new DefaultTableModel(vData, vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			tableResult = new JScrollPane(tb);
			this.getContentPane().add(tableResult, "Center");
			this.setSize(1300, 700);
			this.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void reload() {
		try {
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery("select * from Books");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			for (int i = 1; i <= num_column; i++) {
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			while (rst.next()) {
				Vector row = new Vector(num_column);
				for (int i = 1; i <= num_column; i++)
					row.add(rst.getString(i));
				vData.add(row);
			}
			rst.close();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	public void xoa() {
		    
		try {
			Vector st = (Vector) vData.elementAt(selectedrow);
			stm.executeUpdate("Delete from Books where ID = '" + st.elementAt(0) + "'");
			vData.remove(selectedrow);
			model.fireTableDataChanged();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
			
		

	public void mouseClicked(MouseEvent e) {
		selectedrow = tb.getSelectedRow();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		new qltv("quan ly thu vien");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Tim Kiem")) {
			new timkiem().setVisible(true);
		
		}
		if (e.getActionCommand().equals("Xoa")) {
			xoa();
		}
		if(e.getActionCommand().equals("Them")) {
			new them("Them thong tin sach", " ", "0 ", " ", this);
		}
		if (e.getActionCommand().equals("Chinh Sua")) {
			Vector st = (Vector)vData.elementAt(selectedrow);
			new them("Chinh sua thong tin sach", (String) st.elementAt(0), (String) st.elementAt(1),(String) st.elementAt(2),this);
		}
	}
}




	

	


