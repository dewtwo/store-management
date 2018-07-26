package projectVO;

import java.sql.Date;

public class InboundVO {
	private Date i_date;
	private String p_name;
	private String b_name;
	private int amount_in;
	private int cost;
	
	public InboundVO(){}

	public InboundVO(Date i_date, String p_name, String b_name, int amount_in, int cost) {
		super();
		this.i_date = i_date;
		this.p_name = p_name;
		this.b_name = b_name;
		this.amount_in = amount_in;
		this.cost = cost;
	}

	public Date getI_date() {
		return i_date;
	}
	public void setI_date(Date i_date) {
		this.i_date = i_date;
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
	public int getAmount_in() {
		return amount_in;
	}
	public void setAmount_in(int amount_in) {
		this.amount_in = amount_in;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
