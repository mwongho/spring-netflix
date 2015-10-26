package za.co.mwongho.microservice.customer.model;

public class Customer {
    private long id;
    private long orderId;
    private String name;

    public Customer() {

    }

	public Customer(long id, long orderId, String name) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
