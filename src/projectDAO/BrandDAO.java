package projectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projectDBConn.ProjectDBConn;
import projectVO.BrandVO;
import projectVO.CustomersVO;
import projectVO.ProductsVO;

public class BrandDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BrandDAO() throws ClassNotFoundException, SQLException {
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

	public boolean insertBrand(String b_name, String b_manager, String b_number) {
		String sql = "insert into brand values (?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_name);
			ps.setString(2, b_manager);
			ps.setString(3, b_number);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Insert Error!");
			return false;
		}
		return true;
	}

	public boolean updateBrand(String b_manager, String b_number, String b_name) {
		String sql = "update brand set b_manager = ?, b_number = ? where b_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_manager);
			ps.setString(2, b_number);
			ps.setString(3, b_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Error!");
			return false;
		}
		return true;
	}

	public boolean deleteBrand(String b_name) {
		String sql = "delete from brand where b_name = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_name);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Delete Error!");
			return false;

		}
		return true;

	}

	public ArrayList<BrandVO> getAllBrand() {

		ArrayList<BrandVO> barr = new ArrayList<>();
		String sql = "select * from brand order by b_name";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String b_name = rs.getString("B_Name");
				String b_manager = rs.getString("B_Manager");
				String b_number = rs.getString("B_Number");
				barr.add(new BrandVO(b_name, b_manager, b_number));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return barr;
	}

}
