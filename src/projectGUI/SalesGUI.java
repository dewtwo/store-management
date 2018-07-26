package projectGUI;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projectDAO.ProductsDAO;
import projectDAO.SalesDAO;
import projectVO.ProductsVO;

public class SalesGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel model;
	private JButton button;
	private JButton button_2;
	private ProductsDAO pdao;
	private JButton button_1;
	

	public SalesGUI() {
		setTitle("¸ÅÃâ °ü¸®");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		lblNewLabel.setBounds(12, 14, 57, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(81, 11, 265, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.setBounds(358, 10, 64, 23);
		contentPane.add(button);
		button.addActionListener(this);
		
		textField_1 = new JTextField();
		textField_1.setBounds(81, 39, 91, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		textField_1.setText(df.format(today));
		
		JLabel label = new JLabel("\uB9E4\uCD9C\uC77C\uC790 :");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label.setBounds(12, 42, 69, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\uD310\uB9E4 \uC218\uB7C9 :");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label_1.setBounds(184, 42, 69, 15);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(255, 39, 91, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		String[] sColumn = {"ID","PRODUCT","BRAND","CATEGORY","PRICE","AMOUNT"};
		model = new DefaultTableModel(sColumn,0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(220, 220, 220));
		scrollPane.setBounds(12, 70, 410, 182);
		contentPane.add(scrollPane);
		
		button_2 = new JButton("\uC785\uB825");
		button_2.setBounds(358, 38, 64, 23);
		contentPane.add(button_2);
		
		button_1 = new JButton("\uB9E4\uCD9C \uD1B5\uACC4");
		button_1.setBounds(270, 259, 152, 23);
		contentPane.add(button_1);
		button_1.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				textField.setText((String) table.getValueAt(table.getSelectedRow(), 1));
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button){
			model.setNumRows(0);
			try {
				pdao = new ProductsDAO();
				String p_name = textField.getText();
				ArrayList<ProductsVO> pArr = new ArrayList<>();
				pArr = pdao.searchProducts(p_name);
				Iterator it = pArr.iterator();
				while(it.hasNext()){
					Vector<String> pVector = new Vector<>();
					ProductsVO product = (ProductsVO) it.next();
					pVector.add(String.valueOf(product.getP_id()));
					pVector.add(product.getP_name());
					pVector.add(product.getB_name());
					pVector.add(product.getP_category());
					pVector.add(String.valueOf(product.getPrice()));
					pVector.add(String.valueOf(product.getAmount()));
					model.addRow(pVector);
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource()==button_1){
			SalesGUI_statistics st = new SalesGUI_statistics();
		}
	}

	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JTable getTable() {
		return table;
	}
	public JButton getButton_2() {
		return button_2;
	}
}
