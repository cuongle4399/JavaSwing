package connect;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class connectDatabase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static final String User = "sa";
	private static final String Password = "123";
	private static final String Url = "jdbc:sqlserver://CUONG-LE:1433;databaseName=quanlytuyensinh;trustServerCertificate=true";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					connectDatabase frame = new connectDatabase();
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
	public connectDatabase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Object [] nameColumn = {"Mã sinh viên", "Họ Tên", "Ngày sinh", "Giới tính", "Ngày sinh", "Mã Trường PT", "Mã nghành"};
		DefaultTableModel defaultTable = new DefaultTableModel(null,nameColumn);
		table = new JTable(defaultTable);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(10,10,669,225);
		contentPane.add(scrollTable);
		
		JButton btnClear = new JButton("Xóa toàn bộ dữ liệu trong JTable");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable.setRowCount(0);
			}
		});
		btnClear.setBounds(132, 245, 436, 34);
		contentPane.add(btnClear);
		
		JButton btnPushData = new JButton("Đổ dư liệu từ Database vào JTable");
		btnPushData.setBounds(132, 289, 436, 31);
		contentPane.add(btnPushData);
		
		JButton btnCheckConnet = new JButton("Connect");
		btnCheckConnet.setBounds(10, 252, 85, 21);
		btnCheckConnet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection connection = DriverManager.getConnection(Url,User,Password);
					JOptionPane.showMessageDialog(null, "Connected sucessfullly database");
					String chuoi = "SELECT * FROM SINHVIEN";
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(chuoi);
					while(result.next()) {
						defaultTable.addRow(new Object[]{
	                            result.getString(1),
	                            result.getString(2),
	                            result.getString(3),
	                            result.getString(4),
	                            result.getString(5),
	                            result.getString(6),
	                            result.getString(7)
	                        });
					}
					result.close();
                    statement.close();
                    connection.close();
				}
				catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Can't connect to database" );
					System.out.print(a.getMessage());
				}
			}
		});
				
		contentPane.add(btnCheckConnet);
	}
}

