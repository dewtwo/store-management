package projectVO;

public class CustomersVO {
	private int c_id;
	private String c_name;
	private String address;
	private String phoneNum;
	private int sum;
	private String grade;
	private String b_favorite;

	public CustomersVO() {
	}

	public CustomersVO(int c_id, String c_name, String address, String phoneNum, int sum, String grade,
			String b_favorite) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.sum = sum;
		this.grade = grade;
		this.b_favorite = b_favorite;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getB_favorite() {
		return b_favorite;
	}

	public void setB_favorite(String b_favorite) {
		this.b_favorite = b_favorite;
	}

}
