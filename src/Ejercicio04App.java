import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ejercicio04App {

	public static void main(String[] args) {

		// Creamos una lista donde iran los productos almacenados y otra para el carrito
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("Nestea", (float) 0.98, 12, (float) 0.21));
		products.add(new Product("Arroz", (float) 1.80, 33, (float) 0.21));
		products.add(new Product("Cereales", (float) 3.50, 40, (float) 0.21));
		products.add(new Product("Olivas", (float) 2.30, 15, (float) 0.21));
		products.add(new Product("Cereales", (float) 3.50, 24, (float) 0.21));
		ArrayList<Product> cart = new ArrayList<Product>();

		// Mostramos al usuario una interfaz para añadir productos o listarlos
		int selection = showOptions();

		// Dependiendo de la opción seleccionada haremos una cosa u otra
		while(selection != -1) {

			switch (selection) {
				case 0:
					// Controlamos que no se introduzca algún valor incorrectamente
					try {
						Product newProduct = createProduct();
						products.add(newProduct);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ha introducido un valor incorrecto, por favor inténtelo de nuevo");
					}
					selection = showOptions();
					break;

				case 1:
					listProducts(products);
					selection = showOptions();
					break;
					
				case 2:
					// Preguntamos por cada producto y vamos llenando el carro
					boolean continueBuying = true;
					
					while(continueBuying) {
						// Mostramos la lista de productos para que el cliente pueda seleccionar uno existente y añadirlo al carrito
						StringBuilder productString = new StringBuilder();

						for (Product product : products) {
							productString.append("Nombre: " + product.getName() + " - Precio: " + product.getPrice() + " € - Cantidad: " + product.getQuantity() + "\n");
						}
						String name = JOptionPane.showInputDialog(productString.toString() + "\n\nIntroduce el nombre del producto:");

						// Buscamos el producto asegurandonos que exista
						if (searchByName(products, name) == null) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el producto, por favor, inténtalo de nuevo.");
						} else {
							Product product = searchByName(products, name);
							int quantity = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad:"));

							product.setQuantity(product.getQuantity() - quantity);
							Product productToCart = new Product(product.getName(), product.getPrice(), quantity, product.getIvaType());
							cart.add(productToCart);
							
							// Preguntamos si quiere seguir comprando
							int response = JOptionPane.showConfirmDialog(null, "¿Quieres añadir otro producto?", "Continuar", JOptionPane.YES_NO_OPTION);
							if (response == JOptionPane.NO_OPTION) {
								continueBuying = false;
							}
						}
					}
					
					passByCashier(cart);
					selection = showOptions();
					break;
	
				default:
					break;
			}
		}

	}

	// Muestra al usuario una interfaz para añadir productos, listarlos o pasar por caja
	public static int showOptions() {
		int selection = JOptionPane.showOptionDialog(
				null, 
				"Añada, liste los productos o pase por caja", 
				"Selector de opciones", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null,
				new Object[] { "Añadir producto", "Listar productos", "Pasar por caja", "Cerrar" }, 
				"Añadir producto");

		return selection;
	}
	
	// Lista los productos almacenados
	public static void listProducts(ArrayList<Product> products) {	
		StringBuilder productString = new StringBuilder();
		
		for (Product product : products) {
			productString.append("Nombre: " + product.getName() + " - Precio: " + product.getPrice() + " € - Cantidad: " + product.getQuantity() + "\n");
		}

		JOptionPane.showMessageDialog(null, productString.toString());
	}
	
	// Crea un nuevo producto
	public static Product createProduct() {
		String name = JOptionPane.showInputDialog("Introduce el nombre del producto:");
		float price = Float.parseFloat(JOptionPane.showInputDialog("Introduce el precio por unidad:"));
		int quantity = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad:"));
		float ivaType = Float.parseFloat(JOptionPane.showInputDialog("Introduce el tipo de IVA (0.21 o 0.4): "));

		return new Product(name, price, quantity, ivaType);
	
	}
	
	// Busca un producto por el nombre dada una lista
	public static Product searchByName(ArrayList<Product> products, String name) {
		for (Product product : products) {

			if (product.getName().equals(name)) {
				return product;
			}
		}

		return null;
	}
	
	// Calcula el precio total, con iva, y maneja el pago del cliente
	public static void passByCashier(ArrayList<Product> cart) {
		// Una vez esta el carrito completo, calculamos el precio total bruto, el precio total con IVA y la cantidad de productos
		float totalPrice = 0;
		float totalPriceWithIva = 0;
		int totalQuantity = 0;

		for (Product product : cart) {
			totalPrice += product.getTotalPrice();
			totalPriceWithIva += product.getTotalPriceWithIva();
			totalQuantity += product.getQuantity();
		}

		// Mostramos al cliente los datos y le pedimos el pago, calculamos también el cambio a devolver
		String message = "Resumen de compra\n" +
				"Precio total bruto: " + totalPrice + " €\n" +
				"Precio total con IVA: " + totalPriceWithIva + " €\n" +
				"Cantidad de artículos comprados: " + totalQuantity;

		float customerPayment = Float.parseFloat(JOptionPane.showInputDialog(message + "\n\nIntroduce el pago:"));
		float change = customerPayment - totalPriceWithIva;

		// Mostramos los datos finales con toda la información
		String lastMessage = message + "\n\n" + 
				"Cantidad pagada: " + customerPayment + " €\n" +
				"Cambio a devolver: " + change + " €";

		JOptionPane.showMessageDialog(null, lastMessage);
	}
}