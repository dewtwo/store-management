package projectGUI;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projectDAO.BrandDAO;
import projectDAO.InboundDAO;
import projectDAO.ProductsDAO;
import projectVO.BrandVO;
import projectVO.ProductsVO;

public class ProductsGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private ProductsDAO pdao;
	private BrandDAO bdao;
	private InboundDAO idao;
	InboundGUI inboundGUI;

	public ProductsGUI() {
		setTitle("상품 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		button = new JButton("\uC0C1\uD488 \uB4F1\uB85D");
		button.setBounds(452, 210, 99, 42);
		contentPane.add(button);
		button.addActionListener(this);

		button_1 = new JButton("\uC218\uC815");
		button_1.setBounds(452, 60, 99, 42);
		contentPane.add(button_1);
		button_1.addActionListener(this);

		button_2 = new JButton("\uC0AD\uC81C");
		button_2.setBounds(452, 110, 99, 42);
		contentPane.add(button_2);
		button_2.addActionListener(this);

		button_3 = new JButton("\uC7AC\uACE0 \uAD00\uB9AC");
		button_3.setBounds(452, 10, 99, 42);
		contentPane.add(button_3);
		button_3.addActionListener(this);

		String[] pColumn = { "NO", "BRAND", "PRODUCT", "CATEGORY", "PRICE", "LOCATION", "AMOUNT" };
		tableModel = new DefaultTableModel(pColumn, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 428, 244);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel.setBounds(12, 270, 47, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(62, 267, 177, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\uBE0C\uB79C\uB4DC :");
		label.setBounds(12, 301, 47, 15);
		contentPane.add(label);

		comboBox = new JComboBox<>();
		comboBox.setBounds(62, 298, 177, 21);
		try {
			bdao = new BrandDAO();
			comboBox.addItem("선택");
			ArrayList<BrandVO> bArr = bdao.getAllBrand();
			for (int i = 0; i < bArr.size(); i++) {
				comboBox.addItem(bArr.get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		contentPane.add(comboBox);

		JLabel label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC :");
		label_1.setBounds(251, 270, 61, 15);
		contentPane.add(label_1);

		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(312, 267, 88, 21);
		comboBox_1.addItem("선택");
		comboBox_1.addItem("SKIN CARE");
		comboBox_1.addItem("MAKEUP");
		comboBox_1.addItem("HAIR");
		comboBox_1.addItem("BODY");
		comboBox_1.addItem("PERFUME");
		contentPane.add(comboBox_1);

		JLabel label_2 = new JLabel("\uAC00\uACA9 :");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(261, 301, 47, 15);
		contentPane.add(label_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(312, 298, 88, 21);
		contentPane.add(textField_1);

		JLabel label_3 = new JLabel("\uC9C4\uC5F4 \uC704\uCE58 :");
		label_3.setBounds(423, 267, 61, 15);
		contentPane.add(label_3);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(490, 264, 61, 21);
		contentPane.add(textField_2);

		JLabel label_4 = new JLabel("\uC7AC\uACE0 \uC218\uB7C9 :");
		label_4.setBounds(423, 301, 61, 15);
		contentPane.add(label_4);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(490, 298, 61, 21);
		contentPane.add(textField_3);
		textField_3.setText("0");
		
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inboundGUI = new InboundGUI();
				inboundGUI.addGui.getButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == inboundGUI.addGui.getButton()) {
							if (inboundGUI.addGui.getComboBox_1().getSelectedIndex() != 0
									&& inboundGUI.addGui.getComboBox().getSelectedIndex() != 0
									&& !inboundGUI.addGui.getTextField_2().getText().trim().isEmpty()
									&& !inboundGUI.addGui.getTextField().getText().trim().isEmpty()) {

								String bname = (String) inboundGUI.addGui.getComboBox_1().getSelectedItem();
								String pname = (String) inboundGUI.addGui.getComboBox().getSelectedItem();
								String d = inboundGUI.addGui.getTextField_1().getText();

								String[] d1 = new String[3];
								d1 = d.split("-");
								java.sql.Date date = new java.sql.Date(Integer.parseInt(d1[0]) - 1900,
										Integer.parseInt(d1[1]) - 1, Integer.parseInt(d1[2]));

								int amount = Integer.parseInt(inboundGUI.addGui.getTextField_2().getText());
								int cost = Integer.parseInt(inboundGUI.addGui.getTextField().getText());

								try {
									idao = new InboundDAO();
									if (idao.insert(date, pname, bname, amount, cost)) {
										JOptionPane.showMessageDialog(null, "등록 완료!");

										inboundGUI.addGui.getComboBox_1().setSelectedIndex(0);
										inboundGUI.addGui.getComboBox().setSelectedIndex(0);

										Date today = new Date();
										SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										inboundGUI.addGui.getTextField_1().setText(df.format(today));
										inboundGUI.addGui.getTextField_2().setText(null);
										inboundGUI.addGui.getTextField().setText(null);
									} else {
										JOptionPane.showMessageDialog(null, "등록 실패!");
									}
								} catch (ClassNotFoundException | SQLException e1) {
									e1.printStackTrace();
								}
								pdao.addAmount(amount, pname);
								setTable();
							} else
								JOptionPane.showMessageDialog(null, "모든 데이터를 입력해주세요!");
						}
					}
				});
			}
		});

		button_4 = new JButton("\uCD08\uAE30\uD654");
		button_4.setBounds(452, 160, 99, 42);
		contentPane.add(button_4);
		button_4.addActionListener(this);

		try {
			pdao = new ProductsDAO();
			setTable();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				String brand = (String) table.getValueAt(table.getSelectedRow(), 1);
				comboBox.setSelectedItem(brand.toUpperCase());
				String category = (String) table.getValueAt(table.getSelectedRow(), 3);
				comboBox_1.setSelectedItem(category.toUpperCase());
				textField_1.setText((String) table.getValueAt(table.getSelectedRow(), 4));
				textField_2.setText((String) table.getValueAt(table.getSelectedRow(), 5));
				textField_3.setText((String) table.getValueAt(table.getSelectedRow(), 6));
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_1) {
			String p_name = (String) table.getValueAt(table.getSelectedRow(), 2);
			String newName = textField.getText();
			String b_name = (String) comboBox.getSelectedItem();
			String p_category = (String) comboBox_1.getSelectedItem();
			int price = Integer.parseInt(textField_1.getText());
			String location = textField_2.getText();
			int amount = Integer.parseInt(textField_3.getText());
			if (pdao.updateProduct(newName, b_name, p_category, price, location, amount, p_name)) {
				tableModel.setValueAt(b_name, table.getSelectedRow(), 1);
				tableModel.setValueAt(newName, table.getSelectedRow(), 2);
				tableModel.setValueAt(p_category, table.getSelectedRow(), 3);
				tableModel.setValueAt(String.valueOf(price), table.getSelectedRow(), 4);
				tableModel.setValueAt(location, table.getSelectedRow(), 5);
				tableModel.setValueAt(String.valueOf(amount), table.getSelectedRow(), 6);
				JOptionPane.showMessageDialog(null, "수정 완료");
			} else {
				JOptionPane.showMessageDialog(null, "수정 실패\n입력 정보를 확인해주세요.");
			}
		} else if (e.getSource() == button_2) {
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "삭제할 상품을 선택해주세요.");
			} else {
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "상품 관리", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(result==0){
					String p_name = textField.getText();
					int p_id = pdao.searchId(p_name);
					if (pdao.deleteProduct(p_name)) {
						if (p_id != -1 && pdao.resetId(p_id)) {
							tableModel.setNumRows(0);
							setTable();
							JOptionPane.showMessageDialog(null, "삭제 완료");
						} else {
							JOptionPane.showMessageDialog(null, "삭제 완료\n(ID수정 실패)");
						}
					} else {
						JOptionPane.showMessageDialog(null, "삭제 실패!");
					}
				}
			}
		} else if (e.getSource() == button) {
			if (textField.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "등록 실패!\n상품명을 입력해 주세요.");
			} else if (textField_1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "등록 실패!\n상품의 가격을 입력해 주세요.");
			} else {
				int p_id = pdao.getAllProduct().size() + 1;
				String p_name = textField.getText();
				String b_name = (String) comboBox.getSelectedItem();
				String p_category = (String) comboBox_1.getSelectedItem();
				int price = Integer.parseInt(textField_1.getText());
				String location = textField_2.getText();
				int amount = Integer.parseInt(textField_3.getText());
				if (pdao.insertProduct(p_id, b_name, p_name, p_category, price, location, amount)) {
					JOptionPane.showMessageDialog(null, "상품 등록 완료");
					setTable();
				} else {
					JOptionPane.showMessageDialog(null, "등록 실패!\n입력 정보를 다시 확인해 주세요.");
				}
			}
		} else if (e.getSource() == button_4) {
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			comboBox.setSelectedIndex(0);
			comboBox_1.setSelectedIndex(0);
		}
	}

	public boolean setTable() {
		tableModel.setNumRows(0);
		int count = 0;
		try {
			ArrayList<ProductsVO> pArr = pdao.getAllProduct();
			for (int i = 0; i < pArr.size(); i++) {
				Vector<String> pVector = new Vector<>();
				ProductsVO product = pArr.get(i);
				pVector.add(String.valueOf(++count));
				pVector.add(product.getB_name());
				pVector.add(product.getP_name());
				pVector.add(product.getP_category());
				pVector.add(String.valueOf(product.getPrice()));
				pVector.add(product.getLocation());
				pVector.add(String.valueOf(product.getAmount()));
				tableModel.addRow(pVector);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
