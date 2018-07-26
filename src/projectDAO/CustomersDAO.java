package projectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projectDBConn.ProjectDBConn;
import projectVO.CustomersVO;
import projectVO.ProductsVO;

public class CustomersDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public CustomersDAO() throws ClassNotFoundException, SQLException {
		con = new ProjectDBConn().getConnection();
	}

	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (con != null)
			con.close();
	}

	public boolean insertCustomers(int c_id, String c_name, String address, String phoneNum, int sum, String b_favorite)
			throws ClassNotFoundException {
		String sql = "insert into customers values (?,?,?,?,?,?,?)";
		try {
			CustomersDAO cdao = new CustomersDAO();
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_id);
			ps.setString(2, c_name);
			ps.setString(3, address);
			ps.setString(4, phoneNum);
			ps.setInt(5, sum);
			ps.setString(6, setGrade(sum));
			ps.setString(7, b_favorite);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert Error!");
			return false;
		}
		return true;
	}

	public boolean updateCustomers(String c_name, String address, String newPhone, int sum, String b_favorite,
			String phoneNum) {
		String sql = "update Customers set c_name = ?, address = ?, phoneNum = ?, sum = ?, grade = ?, b_favorite = ? where phoneNum = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c_name);
			ps.setString(2, address);
			ps.setString(3, newPhone);
			ps.setInt(4, sum);
			ps.setString(5, setGrade(sum));
			ps.setString(6, b_favorite);
			ps.setString(7, phoneNum);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Error!");
			return false;
		}
		return true;
	}

	public boolean deleteCustomers(String phoneNum) {
		String sql = "delete from customers where phoneNum = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phoneNum);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Delete Error!");
			return false;

		}
		return true;

	}

	public ArrayList<CustomersVO> msgCustomers(String b_favorite) {
		ArrayList<CustomersVO> arr = new ArrayList<>();
		String sql = "select * from customers where b_favorite = ? order by c_name";
		CustomersVO customers;

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, b_favorite);
			rs = ps.executeQuery();

			while (rs.next()) {
				int c_id = rs.getInt("C_Id");
				String cname = rs.getString("C_Name");
				String address = rs.getString("Address");
				String pnumber = rs.getString("PhoneNum");
				int sum = rs.getInt("sum");
				String grade = rs.getString("Grade");
				String bfavorite = rs.getString("B_Favorite");
				arr.add(new CustomersVO(c_id, cname, address, pnumber, sum, grade, bfavorite));
			}

		} catch (SQLException e) {
			System.out.println("Unidentifiable Customers");
			return null;
		}

		return arr;

	}

	public ArrayList<CustomersVO> getAllCustomers() {
		ArrayList<CustomersVO> cArr = new ArrayList<>();
		String sql = "select * from customers order by c_id";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int c_id = rs.getInt("C_Id");
				String c_name = rs.getString("C_Name");
				String address = rs.getString("Address");
				String phoneNum = rs.getString("PhoneNum");
				int sum = rs.getInt("sum");
				String grade = rs.getString("Grade");
				String b_favorite = rs.getString("B_Favorite");
				cArr.add(new CustomersVO(c_id, c_name, address, phoneNum, sum, grade, b_favorite));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return cArr;
	}

	public CustomersVO searchCustomers(String phoneNum) {
		String sql = "select * from customers where phoneNum = ? order by c_id";
		CustomersVO customers;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phoneNum);
			rs = ps.executeQuery();

			int c_id = rs.getInt("C_Id");
			String cname = rs.getString("C_Name");
			String address = rs.getString("Address");
			String pnumber = rs.getString("PhoneNum");
			int sum = rs.getInt("sum");
			String grade = rs.getString("Grade");
			String bfavorite = rs.getString("B_Favorite");
			customers = new CustomersVO(c_id, cname, address, pnumber, sum, grade, bfavorite);
		} catch (SQLException e) {
			System.out.println("Search Error!");
			return null;
		}
		return customers;
	}

	public boolean resetId(int c_id) {
		String sql = "update customers set c_id = (c_id-1) where c_id > ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, c_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String setGrade(int sum) {
		String grade;
		if (sum <= 100000 && sum >= 0)
			return grade = "¿œπ›";
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
