package tw.com.hfu.entity;

public class Restaurant {
	private int id;
	private String name;
	private String category;// 餐廳類型
	private String address;
	private String picture;
	private String description;// 描述
	private String phone;
	

	public Restaurant() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", category=" + category + ", address=" + address
				+ ", picture=" + picture + ", description=" + description + ", phone=" + phone + "]";
	}
}