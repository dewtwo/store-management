package projectGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import projectDAO.SalesDAO;
import projectVO.BrandVO;
import projectVO.ProductsVO;
import projectVO.SalesVO;

public class SalesGUI_statistics extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTable table;
	private DefaultTableModel tableModel; 
	private JButton button;
	private SalesDAO sdao;
	private ProductsDAO pdao;
	private InboundDAO idao;
	private BrandDAO bdao;
	private JButton button_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox<String> comboBox;
	private JTextField textField_8;
	private JTextField textField_9;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;

	public SalesGUI_statistics() {
		setTitle("매출 통계");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 765, 538);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uB9E4\uCD9C\uC77C\uC790 :");
		label.setFont(new Font("굴림", Font.PLAIN, 12));
		label.setBounds(15, 46, 81, 26);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(79, 49, 99, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(211, 49, 99, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("~");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(179, 52, 33, 15);
		contentPane.add(lblNewLabel);
		
		label_1 = new JLabel("\uC0C1\uD488\uBA85 :");
		label_1.setFont(new Font("굴림", Font.PLAIN, 12));
		label_1.setBounds(245, 12, 65, 26);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\uBE0C\uB79C\uB4DC\uBA85 :");
		label_2.setFont(new Font("굴림", Font.PLAIN, 12));
		label_2.setBounds(15, 12, 69, 26);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\uCE74\uD14C\uACE0\uB9AC :");
		label_3.setFont(new Font("굴림", Font.PLAIN, 12));
		label_3.setBounds(331, 46, 69, 26);
		contentPane.add(label_3);
		
		comboBox = new JComboBox<>();
		comboBox.setMaximumRowCount(6);
		comboBox.setBounds(400, 49, 157, 21);
		comboBox.addItem("선택");
		comboBox.addItem("SKIN CARE");
		comboBox.addItem("MAKEUP");
		comboBox.addItem("HAIR");
		comboBox.addItem("BODY");
		comboBox.addItem("PERFUME");
		contentPane.add(comboBox);
		
		button = new JButton("\uC870\uD68C");
		button.setFont(new Font("굴림", Font.BOLD, 13));
		button.setBounds(580, 25, 69, 42);
		contentPane.add(button);
		button.addActionListener(this);
		
		String[] sColumn = {"DATE","PRODUCT","BRAND","CATEGORY","COST","PRICE","AMOUNT","SALES"};
		tableModel = new DefaultTableModel(sColumn, 0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(new Color(220, 220, 220));
		scroll.setBounds(12, 93, 725, 375);
		contentPane.add(scroll);
		
		button_1 = new JButton("\uCD08\uAE30\uD654");
		button_1.setFont(new Font("굴림", Font.BOLD, 12));
		button_1.setBounds(652, 25, 81, 42);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setBackground(Color.WHITE);
		textField_4.setFont(new Font("굴림", Font.BOLD, 12));
		textField_4.setBounds(647, 469, 90, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setFont(new Font("굴림", Font.BOLD, 12));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("\uCD1D \uB9E4\uCD9C \uC774\uC775");
		textField_5.setColumns(10);
		textField_5.setBounds(551, 469, 93, 21);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("\uCD1D \uB9E4\uCD9C");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("굴림", Font.BOLD, 12));
		textField_6.setColumns(10);
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(384, 469, 69, 21);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_7.setFont(new Font("굴림", Font.BOLD, 12));
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setBounds(455, 469, 90, 21);
		contentPane.add(textField_7);
		
		JLabel lblNewLabel_1 = new JLabel("\uC791\uC131 \uC608)  20170627");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(79, 71, 106, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_8.setFont(new Font("굴림", Font.BOLD, 12));
		textField_8.setColumns(10);
		textField_8.setBackground(Color.WHITE);
		textField_8.setBounds(287, 469, 90, 21);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("\uCD1D \uC218\uB7C9");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("굴림", Font.BOLD, 12));
		textField_9.setColumns(10);
		textField_9.setBackground(Color.WHITE);
		textField_9.setBounds(215, 469, 69, 21);
		contentPane.add(textField_9);
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(79, 15, 133, 21);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("선택");
		try {
			bdao = new BrandDAO();
			ArrayList<BrandVO> bArr = bdao.getAllBrand();
			for(int i=0;i<bArr.size();i++){
				comboBox_1.addItem(bArr.get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		comboBox_1.addActionListener(this);
		
		comboBox_2 = new JComboBox<>();
		comboBox_2.setBounds(301, 15, 256, 21);
		contentPane.add(comboBox_2);
		comboBox_2.addItem("선택");
		
		try {
			sdao = new SalesDAO();
			pdao = new ProductsDAO();
			idao = new InboundDAO();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			tableModel.setNumRows(0);
			String b_name = (String) comboBox_1.getSelectedItem();
			String p_name = (String) comboBox_2.getSelectedItem();
			String category = (String) comboBox.getSelectedItem();
			String date1 = textField.getText().trim();
			String date2 = textField_1.getText().trim();
			if(date1.isEmpty() || date2.isEmpty()){
				JOptionPane.showMessageDialog(null, "매출 일자를 입력해주세요.");
			} else if(b_name.equals("선택") && category.equals("선택")){
				ArrayList<SalesVO> sArr = sdao.salesData_all(date1, date2);
				if(sArr.size()==0)	JOptionPane.showMessageDialog(null, "조회 결과가 없습니다.");
				setTable(sArr);
			} else if(!p_name.equals("선택") && !b_name.equals("선택") && category.equals("선택")){
				ArrayList<SalesVO> pArr = sdao.salesData_product(p_name, date1, date2);
				setTable(pArr);
			} else if(p_name.equals("선택") && !b_name.equals("선택") && category.equals("선택")){
				ArrayList<SalesVO> bArr = sdao.salesData_brand(b_name, date1, date2);
				setTable(bArr);
			} else if(!category.equals("선택")){
				ArrayList<SalesVO> cArr = sdao.salesData_category(category, date1, date2);
				setTable(cArr);
			}
		} else if(e.getSource() == button_1){
			textField.setText("");
			textField_1.setText("");
			textField_4.setText("");
			textField_7.setText("");
			textField_8.setText("");
			comboBox_1.setSelectedIndex(0);
			comboBox_2.setSelectedIndex(0);
			comboBox.setSelectedIndex(0);
			tableModel.setNumRows(0);
		} else if(e.getSource()==comboBox_1){
			String b_name = (String) comboBox_1.getSelectedItem();
			comboBox_2.removeAllItems();
			comboBox_2.addItem("선택");
			ArrayList<ProductsVO> pArr = pdao.getBrandProducts(b_name);
			for(int i=0;i<pArr.size();i++){
				comboBox_2.addItem(pArr.get(i).getP_name());
			}
		}
	}
	
	public void setTable(ArrayList<SalesVO> arr){
		int aTotal =0;
		int sTotal =0;
		int sProfit =0;
		for(int i=0;i<arr.size();i++){
			SalesVO salesData = arr.get(i);
			ProductsVO product = pdao.searchProduct(salesData.getP_name());
			Vector<String> vec = new Vector<>();
			Date date = salesData.getS_date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			vec.add(df.format(date));
			vec.add(salesData.getP_name());
			vec.add(salesData.getB_name());
			vec.add(salesData.getP_category());
			vec.add(String.valueOf(idao.searchCost(salesData.getP_name())));
			vec.add(String.valueOf(product.getPrice()));
			vec.add(String.valueOf(salesData.getDaySales()));
			int total = product.getPrice()*salesData.getDaySales();
			vec.add(String.valueOf(total));
			tableModel.addRow(vec);
		}
		for(int i=0;i<tableModel.getRowCount();i++){
			int data = Integer.parseInt((String)tableModel.getValueAt(i, 6));
			aTotal += data;
		}
		textField_8.setText(String.valueOf(aTotal));
		for(int i=0;i<tableModel.getRowCount();i++){
			int data = Integer.parseInt((String)tableModel.getValueAt(i, 7));
			sTotal += data;
		}
		textField_7.setText(String.valueOf(sTotal));
		for(int i=0;i<tableModel.getRowCount();i++){
			int cost = Integer.parseInt((String)tableModel.getValueAt(i, 4));
			int amount = Integer.parseInt((String)tableModel.getValueAt(i, 6));
			int sales = Integer.parseInt((String)tableModel.getValueAt(i, 7));
			sProfit += (sales-(cost*amount));
		}
		textField_4.setText(String.valueOf(sProfit));
	}
}
