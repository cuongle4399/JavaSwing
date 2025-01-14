package myApp;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class tienDien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tienDien frame = new tienDien();
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
	private JComboBox doiTuong;
	private JTextField chiSoCu;
	private JTextField chiSoMoi;
	private JTextField dinhMuc;
	private JTextField giaVuotDinhMuc;
	private JButton them;
	private JButton tinh;
	private JTable bang;
	private JTextField giaTrongDinhMuc;
	private int STT = 0;
	private Object [] duLieuLuu = {STT, null, null};
	public tienDien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 645);
		setTitle("Tính tiền điện");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		doiTuong = new JComboBox();
		doiTuong.setBounds(500, 399, 132, 30);
		doiTuong.addItem("Cá Nhân");
		doiTuong.addItem("Đơn vị hành chính");
		doiTuong.addItem("Đơn vị sản xuất");
		contentPane.add(doiTuong);
		
		JLabel lblChiSo = new JLabel("Nhập chỉ số cũ: ");
		lblChiSo.setBounds(10, 399, 125, 30);
		contentPane.add(lblChiSo);
		
		chiSoCu = new JTextField();
		chiSoCu.setBounds(128, 400, 89, 29);
		contentPane.add(chiSoCu);
		
		JLabel lblChiSoMoi = new JLabel("Nhập chỉ số mới: ");
		lblChiSoMoi.setBounds(10, 439, 125, 30);
		contentPane.add(lblChiSoMoi);
		
		chiSoMoi = new JTextField();
		chiSoMoi.setBounds(128, 439, 89, 29);
		contentPane.add(chiSoMoi);
		
		JLabel lblDinhMuc = new JLabel("Nhập định mức : ");
		lblDinhMuc.setBounds(10, 479, 125, 30);
		contentPane.add(lblDinhMuc);
		
		dinhMuc = new JTextField();
		dinhMuc.setBounds(128, 480, 89, 29);
		contentPane.add(dinhMuc);
		
		JLabel lblvuotDinhMuc = new JLabel("Giá khi vượt định mức : ");
		lblvuotDinhMuc.setBounds(227, 399, 162, 30);
		contentPane.add(lblvuotDinhMuc);
		
		giaVuotDinhMuc = new JTextField();
		giaVuotDinhMuc.setBounds(411, 400, 79, 29);
		contentPane.add(giaVuotDinhMuc);
		
		JLabel lblGiaTrongDinhMuc = new JLabel("Giá khi vượt định mức : ");
		lblGiaTrongDinhMuc.setBounds(227, 439, 162, 30);
		contentPane.add(lblGiaTrongDinhMuc);
		
		giaTrongDinhMuc = new JTextField();
		giaTrongDinhMuc.setBounds(411, 440, 79, 29);
		contentPane.add(giaTrongDinhMuc);
		
		tinh = new JButton("Tính tiền điện");
		tinh.setBounds(10,529,400,37);
		contentPane.add(tinh);
		tinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chiSoMoi.getText().isEmpty() || chiSoCu.getText().isEmpty() ||
						dinhMuc.getText().isEmpty() || giaVuotDinhMuc.getText().isEmpty() 
						|| giaTrongDinhMuc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ vào !!!!!!!!");
				}
				else if (Integer.parseInt(chiSoMoi.getText()) < Integer.parseInt(chiSoCu.getText())) {
					JOptionPane.showMessageDialog(null, "Có lộn không cha, điện nhà m sài không tăng mà chạy ngược lun à");
				}
				else {
					int dinhMuc1 = Integer.parseInt(dinhMuc.getText());
					int gia = Integer.parseInt(giaTrongDinhMuc.getText());
					int giaNgoaiDinhMuc =Integer.parseInt(giaVuotDinhMuc.getText());
					int soChiSo;
					int tienDien = 0;
					soChiSo = Integer.parseInt(chiSoMoi.getText()) 
							- Integer.parseInt(chiSoCu.getText());
					if(soChiSo <= dinhMuc1) {
						tienDien = soChiSo * gia;
					}
					else {
						tienDien = dinhMuc1 * gia + (soChiSo - dinhMuc1) * giaNgoaiDinhMuc;
					}
					duLieuLuu = new Object[] {null, doiTuong.getSelectedItem(),tienDien};
					JOptionPane.showMessageDialog(null, "Tiền điện phải trả là: " + tienDien);
				}
					
			}
		});
		Font font = new Font("Arial", Font.PLAIN, 18);
		Object [] nameColumn = {
				"STT",
				"Đối Tượng",
				"Tiền điện"
		};
		DefaultTableModel cauTruc = new DefaultTableModel(null, nameColumn);
		bang = new JTable(cauTruc);
		bang.setFont(font);
		bang.setRowHeight(28);
		JScrollPane bangCuon = new JScrollPane(bang);
		bangCuon.setBounds(10,10,622,310);
		contentPane.add(bangCuon);
		
		
		them = new JButton("Thêm");
		them.setBounds(420,529,89,37);
		them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (duLieuLuu[1] == null || duLieuLuu[2] == null) {
					JOptionPane.showMessageDialog(null,"Chưa có dữ liệu để thêm hoặc chưa tính tiền điện");
				}
				else {
					duLieuLuu[0] = STT++;
					cauTruc.addRow(duLieuLuu);
					duLieuLuu[1] = null;
					duLieuLuu[2] = null;
				}
				
			}
		});
		contentPane.add(them);
		
		JButton saveData = new JButton("Lưu Data");
		saveData.setBounds(519, 529, 89, 37);
		contentPane.add(saveData);
		
		JButton loadData = new JButton("Load Data file");
		loadData.setBounds(421, 489, 187, 30);
		loadData.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String data = "" ;
				try {
					FileReader loadFile = new FileReader("data.txt");
					BufferedReader rf = new BufferedReader (loadFile);
					String stringLine;
					while ((stringLine = rf.readLine()) != null) {
						data += stringLine;
					}
					loadFile.close();
					String [] dataPersion = data.split("\\*");
					for(int i = 0;i < dataPersion.length;i++) {
						String [] smallData = dataPersion[i].split("\\|");
						Object [] luuNe = {STT++,smallData[1],smallData[2]};
						cauTruc.addRow(luuNe);
					}
					JOptionPane.showMessageDialog(null, "Load thành công dữ liệu");
				}
				catch(Exception a){
					JOptionPane.showMessageDialog(null, "Lỗi tải data file");
				}
				
			}
		});
		contentPane.add(loadData);
		saveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bang.getRowCount() > 0) {
					try {
						FileWriter file = new FileWriter("data.txt");
						String datavip = "";
						for(int i = 0 ;i < bang.getRowCount() ;i++) {
							for(int j = 0 ;j < bang.getColumnCount();j++) {
								if (j == bang.getColumnCount() - 1) {
									datavip += bang.getValueAt(i, j).toString();
								}
								else {
									datavip += bang.getValueAt(i, j).toString() + "|";
								}
							}
							datavip += "*";						
						}
						file.write(datavip);
						file.close();
						JOptionPane.showMessageDialog(null, "Lưu dữ liệu vào file thành công");
					}
					catch (Exception a){
						JOptionPane.showMessageDialog(null, "Lỗi ghi file");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Bảng dữ liệu trống, không thể lưu dữ liệu");
				}
			}
		});
	}
}
