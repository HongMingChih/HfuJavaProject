package tw.com.hfu.entity;

public class Member {
	
	private int id;
	private String name;
	private String account;
    private String password;
    private String phone;
    private String email;
    private String picture;

    public Member() {
    	
    };
	
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPicture() {
		return picture;
	}
    
    public void setPicture(String Picture) {
		this.picture = Picture;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + ", phone="
				+ phone + ", email=" + email + ", Picture=" + picture + "]";
	}
}
