package projectGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projectDAO.BrandDAO;
import projectDAO.CustomersDAO;
import projectVO.CustomersVO;

public class CustomersGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private DefaultTableModel tableModel;
	private CustomersDAO cdao;
	private JComboBox comboBox_1;
	private BrandDAO bdao;

	public CustomersGUI() {
		setTitle("고객 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 754, 368);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] cColumn = { "ID", "NAME", "ADD", "PHONE", "SUM", "GRADE", "FAV" };
		tableModel = new DefaultTableModel(cColumn, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 594, 244);
		contentPane.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 1)));
				textField_1.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 2)));
				textField_2.setText((String) (tableModel.getValueAt(table.getSelectedRow(), 3)));
				textField_3.setText((String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 4))));
				comboBox_1.setSelectedItem((Object) (tableModel.getValueAt(table.getSelectedRow(), 6)));
			}
		});

		JButton btnNewButton = new JButton("\uD68C\uC6D0 \uB4F1\uB85D");
		btnNewButton.setBounds(618, 10, 108, 40);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton && !textField.getText().isEmpty()) {
					Vector<String> vector = new Vector<>();
					String c_name = textField.getText();
					String address = textField_1.getText();
					String phoneNum = textField_2.getText();
					int sum = Integer.parseInt(textField_3.getText());
					String b_favorite = (String) comboBox_1.getSelectedItem();

					try {
						cdao = new CustomersDAO();
						int c_id = cdao.getAllCustomers().size() + 1;
						if (!c_name.trim().isEmpty() && !phoneNum.trim().isEmpty()) {
							if (cdao.insertCustomers(c_id, c_name, address, phoneNum, sum, b_favorite)) {
								vector.add(String.valueOf(c_id));
								vector.add(c_name);
								vector.add(address);
								vector.add(phoneNum);
								vector.add(String.valueOf(sum));
								if (sum <= 100000 && sum >= 0)
									vector.add("일반");
								else if (sum <= 300000)
									vector.add("Bronze");
								else if (sum <= 500000)
									vector.add("Silver");
								else if (sum <= 1000000)
									vector.add("Gold");
								else
									vector.add("VIP");
								vector.add((String) comboBox_1.getSelectedItem());
								tableModel.addRow(vector);
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								comboBox_1.setSelectedIndex(0);
							} else
								JOptionPane.showMessageDialog(null, "이미 등록된 번호입니다.\n다시 입력해주세요!");
						} else
							JOptionPane.showMessageDialog(null, "이름과 전화번호는 반드시 입력해주세요!");

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton button_1 = new JButton("\uC218\uC815");
		button_1.setBounds(618, 60, 108, 40);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_1 && !textField.getText().isEmpty()) {
					String c_name = textField.getText();
					String address = textField_1.getText();
					String newPhone = textField_2.getText();
					int sum = Integer.parseInt(textField_3.getText());
					String grade = setGrade(sum);
					String b_favorite = (String) (comboBox_1.getSelectedItem());
					String phoneNum = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 3));

					if (!c_name.trim().isEmpty() && !newPhone.trim().isEmpty()) {
						if (cdao.updateCustomers(c_name, address, newPhone, sum, b_favorite, phoneNum)) {

							table.setValueAt(c_name, table.getSelectedRow(), 1);
							table.setValueAt(address, table.getSelectedRow(), 2);
							table.setValueAt(newPhone, table.getSelectedRow(), 3);
							table.setValueAt(sum, table.getSelectedRow(), 4);
							table.setValueAt(grade, table.getSelectedRow(), 5);
							table.setValueAt(b_favorite, table.getSelectedRow(), 6);

							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							comboBox_1.setSelectedIndex(0);
						} else
							JOptionPane.showMessageDialog(null, "업데이트 실패!\n다시 입력해주세요!");
					} else
						JOptionPane.showMessageDialog(null, "이름과 전화번호는 반드시 입력해주세요!");
				}
			}
		});

		JButton button_2 = new JButton("\uC54C\uB9BC \uBA54\uC138\uC9C0");
		button_2.setBounds(618, 160, 108, 40);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MsgGUI msgGui = new MsgGUI();
			}
		});

		JScrollPane scrollPane1 = new JScrollPane((Component) null);
		scrollPane.setBounds(12, 10, 594, 244);
		contentPane.add(scrollPane);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(83, 267, 146, 21);
		contentPane.add(textField);

		JLabel label = new JLabel("\uD68C\uC6D0\uBA85 :");
		label.setBounds(12, 270, 47, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		label_2.setBounds(12, 299, 61, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\uC8FC\uC18C :");
		label_3.setBounds(250, 299, 78, 15);
		contentPane.add(label_3);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(294, 296, 268, 21);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(83, 296, 146, 21);
		contentPane.add(textField_2);

		JLabel label_4 = new JLabel("\uAD00\uC2EC \uBE0C\uB79C\uB4DC :");
		label_4.setBounds(589, 270, 108, 15);
		contentPane.add(label_4);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(589, 293, 128, 21);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("선택");
		try {
			bdao = new BrandDAO();
			for (int i = 0; i < bdao.getAllBrand().size(); i++) {
				comboBox_1.addItem(bdao.getAllBrand().get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		JLabel label_5 = new JLabel("\uAD6C\uB9E4 \uAE08\uC561 :");
		label_5.setBounds(250, 270, 72, 15);
		contentPane.add(label_5);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(322, 267, 240, 21);
		contentPane.add(textField_3);

		JButton button = new JButton("\uD68C\uC6D0 \uC0AD\uC81C");
		button.setBounds(618, 110, 108, 40);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "고객 관리", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (result == 0) {
						String id = (String) table.getValueAt(table.getSelectedRow(), 0);
						String phoneNum = (String) table.getValueAt(table.getSelectedRow(), 3);
						cdao.deleteCustomers(phoneNum);
						int c_id = Integer.parseInt(id);
						cdao.resetId(c_id);
						tableModel.setNumRows(0);
						setTable();
					}
				}
			}
		});
		
		JButton button_3 = new JButton("\uD68C\uC6D0 \uAC80\uC0C9");
		button_3.setBounds(618, 212, 108, 40);
		contentPane.add(button_3);
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_3) {
					String result = JOptionPane.showInputDialog(null, "검색할 회원의 전화번호 마지막 4자리를 입력해주세요!");
					int k = 0;
					for (int i = 0; i < tableModel.getRowCount(); i++) {
						String compare = (String) tableModel.getValueAt(i, 3);
						if (compare.substring(compare.lastIndexOf("-") + 1).equals(result)) {
							table.changeSelection(i, 3, true, false);
							k++;
						}
					}
					
					if (JOptionPane.CLOSED_OPTION == -1 || JOptionPane.CANCEL_OPTION == 2) {
						return;
					}

					if (result.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "찾을 번호를 입력해주세요!");
						actionPerformed(e);
					} else if (k == 0) {
						JOptionPane.showMessageDialog(null, "찾는 회원 정보가 없습니다!");
					}
				}
			}
		});

		setVisible(true);
		setTable();
	}

	public boolean setTable() {
		try {
			cdao = new CustomersDAO();
			ArrayList<CustomersVO> cArr = cdao.getAllCustomers();
			Iterator it = cArr.iterator();
			while (it.hasNext()) {
				Vector<String> vector = new Vector<>();
				CustomersVO customers = (CustomersVO) it.next();
				vector.add(String.valueOf(customers.getC_id()));
				vector.add(customers.getC_name());
				vector.add(customers.getAddress());
				vector.add(customers.getPhoneNum());
				vector.add(String.valueOf(customers.getSum()));
				vector.add(customers.getGrade());
				vector.add(customers.getB_favorite());
				tableModel.addRow(vector);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				comboBox_1.setSelectedIndex(0);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String setGrade(int sum) {
		String grade;
		if (sum <= 100000 && sum >= 0)
			return grade = "일반";
		else if (sum <= 300000)
			return grade = "Bronze";
		else if (sum <= 500000)
			return grade = "Silver";
		else if (sum <= 1000000)
			return grade = "Gold";
		else
			return grade = "VIP";
	}
}
