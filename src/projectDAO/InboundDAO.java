package projectDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projectDBConn.ProjectDBConn;
import projectVO.InboundVO;

public class InboundDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public InboundDAO() throws ClassNotFoundException, SQLException {
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

	public boolean insert(Date date, String p_name, String b_name, int amount_in, int cost) {
		String sql = "insert into inbound values (?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, date);
			ps.setString(2, p_name);
			ps.setString(3, b_name);
			ps.setInt(4, amount_in);
			ps.setInt(5, cost);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<InboundVO> DateProductsIn(String date1, String date2, String p_name) {
		ArrayList<InboundVO> arr = new ArrayList<>();
		String sql = "select * from inbound where i_date>=? and i_date<=? and p_name=? order by i_date";
		try {
			ps = con.prepareStatement(sql);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(1, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(2, new Date(year2, month2, day2));
			ps.setString(3, p_name);
			rs = ps.executeQuery();

			while (rs.next()) {
				Date date_in = rs.getDate("I_Date");
				String pname = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				int amount_in = rs.getInt("Amount_in");
				int cost = rs.getInt("Cost");
				arr.add(new InboundVO(date_in, pname, bname, amount_in, cost));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}

	public ArrayList<InboundVO> DateIn(String date1, String date2) {
		ArrayList<InboundVO> arr = new ArrayList<>();
		String sql = "select * from inbound where i_date>=? and i_date<=? order by i_date";
		try {
			ps = con.prepareStatement(sql);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(1, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(2, new Date(year2, month2, day2));
			rs = ps.executeQuery();

			while (rs.next()) {
				Date date_in = rs.getDate("I_Date");
				String pname = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				int amount_in = rs.getInt("Amount_in");
				int cost = rs.getInt("Cost");
				arr.add(new InboundVO(date_in, pname, bname, amount_in, cost));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public ArrayList<InboundVO> ProductsIn(String p_name) {
		ArrayList<InboundVO> arr = new ArrayList<>();
		String sql = "select * from inbound where p_name = ? order by i_date";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			rs = ps.executeQuery();

			while (rs.next()) {
				Date date_in = rs.getDate("I_Date");
				String pname = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				int amount_in = rs.getInt("Amount_in");
				int cost = rs.getInt("Cost");
				arr.add(new InboundVO(date_in, pname, bname, amount_in, cost));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public ArrayList<InboundVO> brandIn(String b_name) {
		ArrayList<InboundVO> arr = new ArrayList<>();
		String sql = "select * from inbound where b_name = ? order by i_date";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_name);
			rs = ps.executeQuery();

			while (rs.next()) {
				Date date_in = rs.getDate("I_Date");
				String pname = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				int amount_in = rs.getInt("Amount_in");
				int cost = rs.getInt("Cost");
				arr.add(new InboundVO(date_in, pname, bname, amount_in, cost));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public ArrayList<InboundVO> DateBrandIn(String date1, String date2, String b_name) {
		ArrayList<InboundVO> arr = new ArrayList<>();
		String sql = "select * from inbound where i_date>=? and i_date<=? and b_name=? order by i_date";
		try {
			ps = con.prepareStatement(sql);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(1, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(2, new Date(year2, month2, day2));
			ps.setString(3, b_name);
			rs = ps.executeQuery();

			while (rs.next()) {
				Date date_in = rs.getDate("I_Date");
				String pname = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				int amount_in = rs.getInt("Amount_in");
				int cost = rs.getInt("Cost");
				arr.add(new InboundVO(date_in, pname, bname, amount_in, cost));
			}
		} catch (SQLException e) {
			System.out.println("Read Error!");
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public int searchCost(String p_name){
		String sql = "select cost from inbound where p_name =?";
		int cost = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			rs = ps.executeQuery();
			rs.next();
			cost = rs.getInt("Cost");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return cost;
	}
}
