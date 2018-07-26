package projectGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import projectDAO.BrandDAO;
import projectDAO.InboundDAO;
import projectDAO.ProductsDAO;
import projectDAO.ProductsDAO;
import projectDAO.SalesDAO;
import projectVO.ProductsVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JComboBox;

public class AddGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultTableModel model;
	private JButton button;
	private ProductsDAO pdao;
	private BrandDAO bdao;
	private SalesDAO sdao;
	private JComboBox comboBox;
	private JLabel label_2;
	private JComboBox comboBox_1;
	private JTextField textField;
	private InboundDAO indao;

	public AddGUI() {
		setTitle("ÀÔ°í °ü¸®");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 355);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		lblNewLabel.setBounds(12, 70, 57, 15);
		contentPane.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(81, 117, 131, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		textField_1.setText(df.format(today));

		JLabel label = new JLabel("\uC785\uACE0 \uC77C\uC790 :");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label.setBounds(12, 120, 69, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\uC785\uACE0 \uC218\uB7C9 :");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label_1.setBounds(12, 172, 69, 15);
		contentPane.add(label_1);

		textField_2 = new JTextField();
		textField_2.setBounds(83, 169, 129, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		button = new JButton("\uB4F1\uB85D");
		button.setBounds(148, 270, 64, 23);
		contentPane.add(button);

		comboBox = new JComboBox();
		comboBox.setBounds(68, 67, 144, 21);
		contentPane.add(comboBox);
		comboBox.addItem("¼±ÅÃ");
		try {
			pdao = new ProductsDAO();
			for (int i = 0; i < pdao.getAllProduct().size(); i++) {
				comboBox.addItem(pdao.getAllProduct().get(i).getP_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		label_2 = new JLabel("\uBE0C\uB79C\uB4DC\uBA85 :");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label_2.setBounds(12, 25, 69, 15);
		contentPane.add(label_2);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(81, 22, 131, 21);
		contentPane.add(comboBox_1);
		comboBox_1.addActionListener(this);
		comboBox_1.addItem("¼±ÅÃ");
		try {
			bdao = new BrandDAO();
			pdao = new ProductsDAO();
			for (int i = 0; i < bdao.getAllBrand().size(); i++) {
				comboBox_1.addItem(bdao.getAllBrand().get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		JLabel label_3 = new JLabel("\uB2E8\uAC00 :");
		label_3.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label_3.setBounds(12, 223, 67, 15);
		contentPane.add(label_3);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(56, 220, 156, 21);
		contentPane.add(textField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==comboBox_1){
			String b_name = (String) comboBox_1.getSelectedItem();
			comboBox.removeAllItems();
			comboBox.addItem("¼±ÅÃ");
			ArrayList<ProductsVO> pArr = pdao.getBrandProducts(b_name);
			for(int i=0;i<pArr.size();i++){
				comboBox.addItem(pArr.get(i).getP_name());
			}
		}
	}

	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JComboBox getComboBox_1() {
		return comboBox_1;
	}
	public JTextField getTextField() {
		return textField;
	}
}
