import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Ejercicio02App {

	public static void main(String[] args) {

		// Creamos una lista donde iran los productos
		ArrayList<Product> cart = new ArrayList<Product>();

		// Preguntamos por cada producto y vamos llenando el carro
		boolean continueBuying = true;

		while (continueBuying) {

			// Controlamos que no se introduzca algún valor incorrectamente
			try {
				Product newProduct = createProduct();
				cart.add(newProduct);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ha introducido un valor incorrecto, por favor inténtelo de nuevo");
			}

			// Preguntamos si quiere seguir comprando
			int response = JOptionPane.showConfirmDialog(null, "¿Quieres añadir otro producto?", "Continuar", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.NO_OPTION) {
				continueBuying = false;
			}
		}

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
	
	// Crea un nuevo producto
	public static Product createProduct() {
		String name = JOptionPane.showInputDialog("Introduce el nombre del producto:");
		float price = Float.parseFloat(JOptionPane.showInputDialog("Introduce el precio por unidad:"));
		int quantity = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad:"));
		float ivaType = Float.parseFloat(JOptionPane.showInputDialog("Introduce el tipo de IVA (0.21 o 0.4): "));

		return new Product(name, price, quantity, ivaType);
	}

}