package projectGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import projectVO.InboundVO;
import projectVO.ProductsVO;

public class InboundGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JTable table;
	private DefaultTableModel tableModel; 
	private JButton button;
	private JButton button_1;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private BrandDAO bdao;
	private ProductsDAO pdao;
	private InboundDAO idao;
	AddGUI addGui;

	public InboundGUI() {
		setTitle("재고 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC785\uACE0\uC77C\uC790 :");
		label.setFont(new Font("굴림", Font.PLAIN, 12));
		label.setBounds(12, 12, 81, 26);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(76, 15, 118, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(219, 15, 118, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("~");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(194, 18, 26, 15);
		contentPane.add(lblNewLabel);
		
		label_1 = new JLabel("\uC0C1\uD488\uBA85 :");
		label_1.setFont(new Font("굴림", Font.PLAIN, 12));
		label_1.setBounds(226, 58, 61, 26);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\uBE0C\uB79C\uB4DC\uBA85 :");
		label_2.setFont(new Font("굴림", Font.PLAIN, 12));
		label_2.setBounds(12, 58, 69, 26);
		contentPane.add(label_2);
		
		comboBox = new JComboBox<>();
		comboBox.setMaximumRowCount(6);
		comboBox.setBounds(76, 61, 129, 21);
		contentPane.add(comboBox);
		comboBox.addItem("선택");
		try {
			bdao = new BrandDAO();
			ArrayList<BrandVO> bArr = bdao.getAllBrand();
			for(int i=0;i<bArr.size();i++){
				comboBox.addItem(bArr.get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		comboBox.addActionListener(this);
		
		button = new JButton("\uC870\uD68C");
		button.setFont(new Font("굴림", Font.BOLD, 13));
		button.setBounds(349, 12, 69, 37);
		contentPane.add(button);
		button.addActionListener(this);
		
		String[] iColumn = {"DATE","PRODUCT","BRAND","AMOUNT","COST"};
		tableModel = new DefaultTableModel(iColumn, 0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(new Color(220, 220, 220));
		scroll.setBounds(12, 94, 476, 227);
		contentPane.add(scroll);
		
		button_1 = new JButton("\uC785\uACE0");
		button_1.setFont(new Font("굴림", Font.BOLD, 12));
		button_1.setBounds(419, 12, 69, 37);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("\uC791\uC131 \uC608)  20170627");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(76, 37, 106, 15);
		contentPane.add(lblNewLabel_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setMaximumRowCount(6);
		comboBox_1.setBounds(277, 61, 211, 21);
		comboBox_1.addItem("선택");
		contentPane.add(comboBox_1);
		
		try {
			pdao = new ProductsDAO();
			idao = new InboundDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		addGui = new AddGUI();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==comboBox){
			String b_name = (String) comboBox.getSelectedItem();
			comboBox_1.removeAllItems();
			comboBox_1.addItem("선택");
			ArrayList<ProductsVO> pArr = pdao.getBrandProducts(b_name);
			for(int i=0;i<pArr.size();i++){
				comboBox_1.addItem(pArr.get(i).getP_name());
			}
		} else if (e.getSource()==button){
			String p_name = (String) comboBox_1.getSelectedItem();
			String b_name = (String) comboBox.getSelectedItem();
			String date1 = textField.getText();
			String date2 = textField_1.getText();
			if(p_name.equals("선택") && b_name.equals("선택") && !date1.isEmpty() && !date2.isEmpty()){
				ArrayList<InboundVO> iArr = idao.DateIn(date1, date2);
				setTable(iArr);
			} else if(p_name.equals("선택") && !b_name.equals("선택") && date1.isEmpty() && date2.isEmpty()){
				ArrayList<InboundVO> iArr = idao.brandIn(b_name);
				setTable(iArr);
			} else if(p_name.equals("선택") && !b_name.equals("선택") && !date1.isEmpty() && !date2.isEmpty()){
				ArrayList<InboundVO> iArr = idao.DateBrandIn(date1, date2, b_name);
				setTable(iArr);
			} else if(!p_name.equals("선택") && date1.isEmpty() && date2.isEmpty()){
				ArrayList<InboundVO> iArr = idao.ProductsIn(p_name);
				setTable(iArr);
			} else if (!p_name.equals("선택") && !date1.isEmpty() && !date2.isEmpty()){
				ArrayList<InboundVO> iArr = idao.DateProductsIn(date1, date2, p_name);
				setTable(iArr);
			}
		} else if (e.getSource()==button_1){
			addGui.setVisible(true);
		}
	}
	
	public void setTable(ArrayList<InboundVO> iArr){
		tableModel.setNumRows(0);
		for(int i=0;i<iArr.size();i++){
			InboundVO inbound = iArr.get(i);
			Vector<String> vec = new Vector<>();
			vec.add(String.valueOf(inbound.getI_date()));
			vec.add(inbound.getP_name());
			vec.add(inbound.getB_name());
			vec.add(String.valueOf(inbound.getAmount_in()));
			vec.add(String.valueOf(inbound.getCost()));
			tableModel.addRow(vec);
		}
	}
}
