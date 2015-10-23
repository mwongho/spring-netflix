package za.co.mwongho.microservice.product.model;

public class Product {
    private long id;
    private long orderId;
    private String description;

    public Product() {

    }

	public Product(long id, long orderId, String description) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
