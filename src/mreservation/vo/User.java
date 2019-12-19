package mreservation.vo;

public class User {
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_phone;
	private String user_email;
	private int user_point;
	private int user_activation = 1;
	
	public User() {
		
	}
	
	
	
	public User(int user_id, String user_name, String user_password, String user_phone, String user_email,
			int user_point, int user_activation) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_point = user_point;
		this.user_activation = user_activation;
	}



	public User(String user_name, String user_password, String user_phone, String user_email) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_phone = user_phone;
		this.user_email = user_email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_point() {
		return user_point;
	}

	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}



	public int getUser_activation() {
		return user_activation;
	}



	public void setUser_activation(int user_activation) {
		this.user_activation = user_activation;
	}



	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_phone=" + user_phone + ", user_email=" + user_email + ", user_point=" + user_point
				+ ", user_activation=" + user_activation + "]";
	}

	
}
