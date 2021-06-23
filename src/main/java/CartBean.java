public class CartBean {
	private int id;
	private int custId;
	private int prodId;

	private CustomerBean customer = new CustomerBean();
	private ProductBean product = new ProductBean();

	public CartBean() {
		// PASS
	}

	public CartBean(int id, int custId, int prodId, CustomerBean customer, ProductBean product) {
		this.id = id;
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
