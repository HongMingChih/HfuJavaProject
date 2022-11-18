package tw.com.hfu.entity;

/*
 * 網頁管理者
 */
public class MemberAdmin {
	private int ma_id;
	private String ma_name;
	private String ma_email;
	private String ma_password;
	private String ma_picture;

	public MemberAdmin(String ma_picture) {
	
		this.ma_picture = ma_picture;
	}

	public MemberAdmin() {

	}

	public String getMa_picture() {
		return ma_picture;
	}

	public void setMa_picture(String ma_picture) {
		this.ma_picture = ma_picture;
	}


	public int getMa_id() {
		return ma_id;
	}

	public void setMa_id(int ma_id) {
		this.ma_id = ma_id;
	}

	public String getMa_name() {
		return ma_name;
	}

	public void setMa_name(String ma_name) {
		this.ma_name = ma_name;
	}

	public String getMa_email() {
		return ma_email;
	}

	public void setMa_email(String ma_email) {
		this.ma_email = ma_email;
	}

	public String getMa_password() {
		return ma_password;
	}

	public void setMa_password(String ma_password) {
		this.ma_password = ma_password;
	}

	@Override
	public String toString() {
		return "MemberAdmin [ma_id=" + ma_id + ", ma_name=" + ma_name + ", ma_email=" + ma_email + ", ma_password="
				+ ma_password + ", ma_picture=" + ma_picture + "]";
	}



}
