package projectVO;

import java.sql.Date;

public class SalesVO {
	private Date s_date;
	private int p_id;
	private String p_name;
	private String b_name;
	private String p_category;
	private int daySales;
	
	public SalesVO(){}

	public SalesVO(Date s_date, int p_id, String p_name, String b_name, String p_category, int daySales) {
		super();
		this.s_date = s_date;
		this.p_id = p_id;
		this.p_name = p_name;
		this.b_name = b_name;
		this.p_category = p_category;
		this.daySales = daySales;
	}

	public Date getS_date() {
		return s_date;
	}
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public int getDaySales() {
		return daySales;
	}
	public void setDaySales(int daySales) {
		this.daySales = daySales;
	}
}
