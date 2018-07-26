package projectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projectDBConn.ProjectDBConn;
import projectVO.ProductsVO;

public class ProductsDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ProductsDAO() throws ClassNotFoundException, SQLException {
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

	public boolean insertProduct(int p_id, String b_name, String p_name, String p_category, int price, String location,
			int amount) {
		String sql = "insert into products values (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setString(2, p_name);
			ps.setString(3, b_name);
			ps.setString(4, p_category);
			ps.setInt(5, price);
			ps.setString(6, location);
			ps.setInt(7, amount);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateProduct(String newName, String b_name, String p_category, int price, String location, int amount, String p_name) {
		String sql = "update products set p_name=?, b_name=?, p_category=?, price=?, location=?, amount=? where p_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setString(2, b_name);
			ps.setString(3, p_category);
			ps.setInt(4, price);
			ps.setString(5, location);
			ps.setInt(6, amount);
			ps.setString(7, p_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addAmount(int amount, String p_name) {
		String sql = "update products set amount= (amount+?) where p_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, p_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean subAmount(int amount, String p_name) {
		String sql = "update products set amount= (amount-?) where p_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, p_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteProduct(String p_name) {
		String sql = "delete from products where p_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<ProductsVO> getAllProduct() {
		ArrayList<ProductsVO> pArr = new  ArrayList<>();
		String sql = "select * from products order by b_name";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int p_id = rs.getInt("P_Id");
				String p_name = rs.getString("P_Name");
				String b_name = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int price = rs.getInt("Price");
				String location = rs.getString("Location");
				int amount = rs.getInt("Amount");
				pArr.add(new ProductsVO(p_id, p_name, b_name, p_category, price, location, amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pArr;
	}

	public ArrayList<ProductsVO> searchProducts(String p_name) {
		String sql = "select * from products where p_name like '%'||?||'%'";
		ArrayList<ProductsVO> pArr = new  ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int p_id = rs.getInt("P_Id");
				String pname = rs.getString("P_Name");
				String b_name = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int price = rs.getInt("Price");
				String location = rs.getString("Location");
				int amount = rs.getInt("Amount");
				pArr.add(new ProductsVO(p_id, pname, b_name, p_category, price, location, amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pArr;
	}
	
	public ProductsVO searchProduct(String p_name){
		String sql = "select * from products where p_name =?";
		ProductsVO product;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			rs = ps.executeQuery();
			rs.next();
			int p_id = rs.getInt("P_Id");
			String pname = rs.getString("P_Name");
			String b_name = rs.getString("B_Name");
			String p_category = rs.getString("P_Category");
			int price = rs.getInt("Price");
			String location = rs.getString("Location");
			int amount = rs.getInt("Amount");
			product = new ProductsVO(p_id, pname, b_name, p_category, price, location, amount);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return product;
	}
	
	public ArrayList<ProductsVO> getBrandProducts(String b_name) {
		String sql = "select * from products where b_name =?";
		ArrayList<ProductsVO> pArr = new  ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int p_id = rs.getInt("P_Id");
				String p_name = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int price = rs.getInt("Price");
				String location = rs.getString("Location");
				int amount = rs.getInt("Amount");
				pArr.add(new ProductsVO(p_id, p_name, bname, p_category, price, location, amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pArr;
	}

	public ArrayList<ProductsVO> inbound() {
		ArrayList<ProductsVO> arr = new ArrayList<>();
		String sql = "select * from products where amount = 0";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int p_id = rs.getInt("P_Id");
				String p_name = rs.getString("P_Name");
				String b_name = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int price = rs.getInt("Price");
				String location = rs.getString("Location");
				int amount = rs.getInt("Amount");
				arr.add(new ProductsVO(p_id, p_name, b_name, p_category, price, location, amount));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public boolean resetId(int p_id){
		String sql = "update products set p_id = (p_id-1) where p_id > ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int searchId(String p_name){
		String sql = "select p_id from products where p_name=?";
		int p_id=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			rs = ps.executeQuery();
			rs.next();
			p_id = rs.getInt("P_Id");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} 
		return p_id;
	}
}
