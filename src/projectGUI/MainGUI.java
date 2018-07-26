package projectGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectDAO.ProductsDAO;
import projectDAO.SalesDAO;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private SalesDAO sdao;
	private ProductsDAO pdao;
	ProductsGUI productgui;
	SalesGUI salesgui;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI() {
		setTitle("통합 관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("\uC0C1\uD488 \uAD00\uB9AC");
		btnNewButton.setBounds(246, 50, 136, 61);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					productgui.setVisible(true);
				}
			}
		});

		JButton button = new JButton("\uACE0\uAC1D \uAD00\uB9AC");
		button.setBounds(50, 147, 136, 61);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					CustomersGUI customersgui = new CustomersGUI();
				}
			}
		});

		JButton button_1 = new JButton("\uB9E4\uCD9C \uAD00\uB9AC");
		button_1.setBounds(246, 147, 136, 61);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_1) {
					salesgui.setVisible(true);
				}
			}
		});

		JButton button_2 = new JButton("\uBE0C\uB79C\uB4DC \uAD00\uB9AC");
		button_2.setBounds(50, 50, 136, 61);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_2) {
					BrandGUI brandgui = new BrandGUI();
				}
			}
		});
		
		productgui = new ProductsGUI();
		salesgui = new SalesGUI();
		setVisible(true);
		
		salesgui.getButton_2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sdao = new SalesDAO();
					String s_date = salesgui.getTextField_1().getText();
					int rowNum = salesgui.getTable().getSelectedRow();
					String id = (String) salesgui.getTable().getValueAt(rowNum, 0);
					int p_id = Integer.parseInt(id);
					String p_name = (String) salesgui.getTable().getValueAt(rowNum, 1);
					String b_name = (String) salesgui.getTable().getValueAt(rowNum, 2);
					String p_category = (String) salesgui.getTable().getValueAt(rowNum, 3);
					int daySales = Integer.parseInt(salesgui.getTextField_2().getText());
					if(sdao.insertData(s_date, p_id, p_name, b_name, p_category, daySales)){
						pdao = new ProductsDAO();
						pdao.subAmount(daySales, p_name);
						String amount = (String) salesgui.getTable().getValueAt(salesgui.getTable().getSelectedRow(), 5);
						salesgui.getTable().setValueAt(String.valueOf(Integer.parseInt(amount)-daySales), salesgui.getTable().getSelectedRow(), 5);
						salesgui.getTextField().setText("");
						salesgui.getTextField_2().setText("");
						productgui.setTable();
						JOptionPane.showMessageDialog(null, "일일 판매 수량이 입력되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "입력 실패!\n정보를 확인해주세요.");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
