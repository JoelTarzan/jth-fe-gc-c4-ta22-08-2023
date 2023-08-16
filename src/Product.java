public class Product {

	// Propiedades
	private String name;
	private float price;
	private int quantity;
	private float ivaType;

	// Constructor
	public Product(String name, float price, int quantity, float ivaType) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.ivaType = ivaType;
	}

	// Métodos
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getIvaType() {
		return ivaType;
	}

	public void setIvaType(float ivaType) {
		this.ivaType = ivaType;
	}
	
	public float getTotalPrice() {
		return price * quantity;
	}
	
	public float getTotalPriceWithIva() {
		return getTotalPrice() + (getTotalPrice() * ivaType);
	}
}