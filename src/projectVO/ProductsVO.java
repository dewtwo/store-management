package projectVO;

public class ProductsVO {
	private int p_id;
	private String p_name;
	private String b_name;
	private String p_category;
	private int price;
	private String location;
	private int amount;
	
	public ProductsVO(){}
	
	public ProductsVO(int p_id, String p_name, String b_name, String p_category, int price, String location, 
			int amount) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.b_name = b_name;
		this.p_category = p_category;
		this.price = price;
		this.location = location;
		this.amount = amount;
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
	public String getB_name(){
		return b_name;
	}
	public void setB_name(String b_name){
		this.b_name = b_name;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
