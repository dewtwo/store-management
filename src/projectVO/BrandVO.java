package projectVO;

public class BrandVO {
	private String b_name;
	private String b_address;
	private String b_maneger;
	private String b_number;

	public BrandVO() {
	}

	public BrandVO(String b_name, String b_maneger, String b_number) {
		super();
		this.b_name = b_name;
		this.b_maneger = b_maneger;
		this.b_number = b_number;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_maneger() {
		return b_maneger;
	}

	public void setB_maneger(String b_maneger) {
		this.b_maneger = b_maneger;
	}

	public String getB_number() {
		return b_number;
	}

	public void setB_number(String b_number) {
		this.b_number = b_number;
	}

}
