package myApp;
import javax.swing.*;
import java.text.DecimalFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class phuongTrinhBacHai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					phuongTrinhBacHai frame = new phuongTrinhBacHai();
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
	private JTextField txta;
	private JTextField txtb;
	private JTextField txtc;
	public phuongTrinhBacHai() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Giải phương trình bậc 2");
		setBounds(100, 100, 350, 300);
		setLocationRelativeTo(null);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnResult = new JButton ("Kết quả");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txta.getText().isEmpty() || txtb.getText().isEmpty() || txtc.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Đ*t mọe mày nhập cho đầy đủ vào thằng l*n");
				}
				else {
					DecimalFormat lamTron = new DecimalFormat("#.#####");
					float a = Float.parseFloat(txta.getText());
					float b = Float.parseFloat(txtb.getText());
					float c = Float.parseFloat(txtc.getText());
					double delta = Math.pow(b, 2)- 4*a*c;
					if(delta < 0) {
						btnResult.setText("Vô nghiệm rồi cu");
					}
					else if(delta == 0) {
						btnResult.setText("x1 = x2: "+ -b/2*a);
					}
					else {
						btnResult.setText("x1 = " + lamTron.format((-b + Math.sqrt(delta))/ (2*a))  + 
								" x2 = " + lamTron.format((-b-Math.sqrt(delta)) / (2*a))); 
					}
				}
			}
		});
		btnResult.setBounds(25, 120, 300, 50);
		contentPane.add(btnResult);
		
		txta = new JTextField();
		txta.setBounds(30,40,50,30);
		contentPane.add(txta);
		
		JLabel lblx2 = new JLabel("X^2");
		lblx2.setBounds(95, 40, 40, 30);
		contentPane.add(lblx2);
		
		txtb = new JTextField();
		txtb.setBounds(140,40, 50, 30);
		contentPane.add(txtb);
		
		JLabel lblx = new JLabel("+ X");
		lblx.setBounds(195, 40, 40 , 30);
		contentPane.add(lblx);
		
		txtc = new JTextField();
		txtc.setBounds(240, 40, 50, 30);
		contentPane.add(txtc);
		
		
	}
}
