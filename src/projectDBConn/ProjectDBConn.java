package projectDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectDBConn {
	private Connection con;
	
	public ProjectDBConn() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	}
	
	public Connection getConnection(){
		return con;
	}
}
