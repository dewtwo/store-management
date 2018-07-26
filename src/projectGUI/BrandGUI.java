package projectGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projectDAO.BrandDAO;
import projectDAO.ProductsDAO;
import projectVO.BrandVO;
import projectVO.ProductsVO;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class BrandGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tableModel;
	private BrandDAO bdao;
	private JTextField textField_4;

	public BrandGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Brand 관리");
		setBounds(100, 100, 590, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("\uBE0C\uB79C\uB4DC \uB4F1\uB85D");
		button.setBounds(452, 274, 111, 42);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button && !textField.getText().isEmpty()) {
					Vector<String> vector = new Vector<>();
					if (bdao.insertBrand(textField.getText(), textField_4.getText(), textField_1.getText())) {
						vector.add(textField.getText());
						vector.add(textField_4.getText());
						vector.add(textField_1.getText());
						tableModel.addRow(vector);
						textField.setText("");
						textField_1.setText("");
						textField_4.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "중복된 브랜드명입니다.\n다시 입력해주세요!");

					}

				}

			}

		});

		JButton button_1 = new JButton("\uC218\uC815");
		button_1.setBounds(452, 145, 111, 42);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button_1) {
					if(
					bdao.updateBrand(textField_4.getText(), textField_1.getText(),
							((String) (tableModel.getValueAt(table.getSelectedRow(), 0))))){

					table.setValueAt(textField_4.getText(), table.getSelectedRow(), 1);
					table.setValueAt(textField_1.getText(), table.getSelectedRow(), 2);

					textField.setText("");
					textField_1.setText("");
					textField_4.setText("");
				}}

			}

		});

		JButton button_2 = new JButton("\uC0AD\uC81C");
		button_2.setBounds(452, 210, 111, 42);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_2) {
					bdao.deleteBrand((String) (tableModel.getValueAt(table.getSelectedRow(), 0)));
					tableModel.removeRow(table.getSelectedRow());
					textField.setText("");
					textField_1.setText("");
					textField_4.setText("");

				}

			}
		});

		String[] bColumn = { "BRAND", "MANAGER", "TEL" };
		tableModel = new DefaultTableModel(bColumn, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 428, 244);
		contentPane.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textField.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 0)));
				textField_4.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 1)));
				textField_1.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 2)));

			}
		});

		JLabel lblNewLabel = new JLabel("\uBE0C\uB79C\uB4DC\uBA85 :");
		lblNewLabel.setBounds(12, 270, 61, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(76, 267, 364, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\uB2F4\uB2F9 \uB9E4\uB2C8\uC800 :");
		label.setBounds(12, 301, 82, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("\uC5F0\uB77D\uCC98 :");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(238, 301, 47, 15);
		contentPane.add(label_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(295, 298, 145, 21);
		contentPane.add(textField_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(96, 298, 122, 21);
		contentPane.add(textField_4);

		try {
			bdao = new BrandDAO();
			ArrayList<BrandVO> bArr = bdao.getAllBrand();
			Iterator it = bArr.iterator();
			while (it.hasNext()) {
				Vector<String> vector = new Vector<>();
				BrandVO brand = (BrandVO) it.next();
				vector.add(String.valueOf(brand.getB_name()));
				vector.add(brand.getB_maneger());
				vector.add(brand.getB_number());
				tableModel.addRow(vector);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		setVisible(true);

	}
}
