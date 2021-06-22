public class CustomerBean {
	private int id;
	private String username;
	private String password;
	private String address;
	private String phone;
	private String name;
	public boolean valid;

	public CustomerBean() {
		// PASS
	}

	public CustomerBean(int id, String username, String password, String address, String phone, boolean valid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.valid = valid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
