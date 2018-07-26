package projectDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projectDBConn.ProjectDBConn;
import projectVO.SalesVO;

public class SalesDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public SalesDAO() throws ClassNotFoundException, SQLException {
		con = new ProjectDBConn().getConnection();
	}

	public void close() throws SQLException {
		if (rs != null)	rs.close();
		if (ps != null)	ps.close();
		if (con != null) con.close();
	}
	
	public boolean insertData(String s_date, int p_id, String p_name, String b_name, String p_category, int daySales){
		String sql = "insert into sales values (?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			String[] dArr = s_date.split("-");
			int year = Integer.parseInt(dArr[0])-1900;
			int month = Integer.parseInt(dArr[1])-1;
			int day = Integer.parseInt(dArr[2]);
			ps.setDate(1, new Date(year,month,day));
			ps.setInt(2, p_id);
			ps.setString(3, p_name);
			ps.setString(4, b_name);
			ps.setString(5, p_category);
			ps.setInt(6, daySales);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<SalesVO> salesData_all(String date1, String date2){
		ArrayList<SalesVO> aArr = new ArrayList<>();
		String sql = "select * from sales where s_date>=? and s_date<=? order by s_date";
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
			
			while(rs.next()){
				Date s_date = rs.getDate("S_Date");
				int p_id = rs.getInt("P_Id");
				String pname = rs.getString("P_Name");
				String b_name = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int daySales = rs.getInt("DaySales");
				aArr.add(new SalesVO(s_date, p_id, pname, b_name, p_category, daySales));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return aArr;
	}
	
	public ArrayList<SalesVO> salesData_product(String p_name, String date1, String date2){
		ArrayList<SalesVO> pArr = new ArrayList<>();
		String sql = "select * from sales where p_name like '%'||?||'%' and s_date>=? and s_date<=? order by s_date";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p_name);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(2, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(3, new Date(year2, month2, day2));
			rs = ps.executeQuery();
			
			while(rs.next()){
				Date s_date = rs.getDate("S_Date");
				int p_id = rs.getInt("P_Id");
				String pname = rs.getString("P_Name");
				String b_name = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int daySales = rs.getInt("DaySales");
				pArr.add(new SalesVO(s_date, p_id, pname, b_name, p_category, daySales));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pArr;
	}
	
	public ArrayList<SalesVO> salesData_brand(String b_name, String date1, String date2){
		ArrayList<SalesVO> bArr = new ArrayList<>();
		String sql = "select * from sales where b_name like '%'||?||'%' and s_date>=? and s_date<=? order by s_date";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b_name);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(2, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(3, new Date(year2, month2, day2));
			rs = ps.executeQuery();
			
			while(rs.next()){
				Date s_date = rs.getDate("S_Date");
				int p_id = rs.getInt("P_Id");
				String p_name = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int daySales = rs.getInt("DaySales");
				bArr.add(new SalesVO(s_date, p_id, p_name, bname, p_category, daySales));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return bArr;
	}
	
	public ArrayList<SalesVO> salesData_category(String category, String date1, String date2){
		ArrayList<SalesVO> cArr = new ArrayList<>();
		String sql = "select * from sales where p_category=? and s_date>=? and s_date<=? order by s_date";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
			int year1 = Integer.parseInt(date1.substring(0,4))-1900;
			int month1 = Integer.parseInt(date1.substring(4, 6))-1;
			int day1 = Integer.parseInt(date1.substring(6));
			ps.setDate(2, new Date(year1, month1, day1));
			int year2 = Integer.parseInt(date2.substring(0,4))-1900;
			int month2 = Integer.parseInt(date2.substring(4, 6))-1;
			int day2 = Integer.parseInt(date2.substring(6));
			ps.setDate(3, new Date(year2, month2, day2));
			rs = ps.executeQuery();
			
			while(rs.next()){
				Date s_date = rs.getDate("S_Date");
				int p_id = rs.getInt("P_Id");
				String p_name = rs.getString("P_Name");
				String bname = rs.getString("B_Name");
				String p_category = rs.getString("P_Category");
				int daySales = rs.getInt("DaySales");
				cArr.add(new SalesVO(s_date, p_id, p_name, bname, p_category, daySales));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return cArr;
	}
	
}
