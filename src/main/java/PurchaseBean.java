public class PurchaseBean {
	private int id;
	private String date;
	private String shipping;
	private int quantity;
	private int custId;
	private int prodId;

	private CustomerBean customer = new CustomerBean();
	private ProductBean product = new ProductBean();

	public PurchaseBean() {
		// PASS
	}

	public PurchaseBean(int id, String date, String shipping, int quantity, int custId, int prodId,
			CustomerBean customer, ProductBean product) {
		this.id = id;
		this.date = date;
		this.shipping = shipping;
		this.quantity = quantity;
		this.custId = custId;
		this.prodId = prodId;
		this.customer = customer;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

}
