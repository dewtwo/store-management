package projectGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projectDAO.BrandDAO;
import projectDAO.CustomersDAO;
import projectVO.BrandVO;
import projectVO.CustomersVO;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class MsgGUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private CustomersDAO cdao;
	private JComboBox comboBox;
	private BrandDAO bdao;
	private JTable table;

	public MsgGUI() {
		setTitle("고객 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] cColumn = { "NAME", "PHONE", "SUM", "GRADE" };
		tableModel = new DefaultTableModel(cColumn, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 38, 524, 288);
		contentPane.add(scrollPane);

		JScrollPane scrollPane1 = new JScrollPane((Component) null);

		JLabel label_4 = new JLabel("\uAD00\uC2EC \uBE0C\uB79C\uB4DC :");
		label_4.setBounds(12, 13, 84, 15);
		contentPane.add(label_4);

		comboBox = new JComboBox();
		comboBox.setBounds(105, 10, 130, 21);
		contentPane.add(comboBox);
		comboBox.addItem("선택");
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == comboBox) {
					tableModel.setNumRows(0);

					String b_favorite = (String) comboBox.getSelectedItem();
					try {
						cdao = new CustomersDAO();
						ArrayList<CustomersVO> arr = cdao.msgCustomers(b_favorite);
						Iterator it = arr.iterator();
						while (it.hasNext()) {
							Vector<String> vector = new Vector<>();
							CustomersVO msg = (CustomersVO) it.next();
							vector.add(msg.getC_name());
							vector.add(msg.getPhoneNum());
							vector.add(String.valueOf(msg.getSum()));
							vector.add(msg.getGrade());
							tableModel.addRow(vector);

						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.setBounds(12, 38, 524, 288);
		contentPane.add(table);
		try

		{
			bdao = new BrandDAO();
			for (int i = 0; i < bdao.getAllBrand().size(); i++) {
				comboBox.addItem(bdao.getAllBrand().get(i).getB_name());
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		setVisible(true);
	}

}
