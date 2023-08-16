import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ejercicio03App {

	public static void main(String[] args) {

		// Creamos la base de datos con 10 artículos
		Hashtable<String, Float> products = new Hashtable<String, Float>();
		products.put("Nestea", (float) 0.98);
		products.put("Arroz", (float) 1.80);
		products.put("Cartón de leche", (float) 1.20);
		products.put("Olivas", (float) 2.30);
		products.put("Cereales", (float) 3.50);
		products.put("Barra de pan", (float) 2.50);
		products.put("Botella de aceite", (float) 7.50);
		products.put("Manzana", (float) 1.50);
		products.put("Macarrones", (float) 2.50);
		products.put("Hamburguesa de pollo", (float) 2.30);

		// Mostramos al usuario una interfaz para añadir productos o listarlos
		int selection = showOptions();

		// Dependiendo de la opción seleccionada haremos una cosa u otra
		while(selection != -1) {

			switch (selection) {
				case 0:
					String product[] = createProduct();
					products.put(product[0], Float.parseFloat(product[1]));
					selection = showOptions();
					break;
	
				case 1:
					listProducts(products);
					selection = showOptions();
					break;
					
				case 2:
					String name = JOptionPane.showInputDialog("Introduce el nombre del producto:");
					
					if (products.get(name) == null) {
						JOptionPane.showMessageDialog(null, "No se ha encontrado el producto, por favor, inténtalo de nuevo.");
					} else {
						JOptionPane.showMessageDialog(null, "Nombre: " + name + " - Precio: " + products.get(name));
					}
					
					selection = showOptions();
					break;
	
				default:
					break;
			}
		}
	}

	// Muestra al usuario una interfaz para añadir productos o listarlos
	public static int showOptions() {
		int selection = JOptionPane.showOptionDialog(
				null, 
				"Añada o liste los productos", 
				"Selector de opciones", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null,
				new Object[] { "Añadir producto", "Listar productos", "Información de un producto", "Cerrar" }, 
				"Añadir producto");

		return selection;
	}

	// Pregunta los datos al usuario para añadir un producto nuevo y lo devuelve
	public static String[] createProduct() {
		String name = JOptionPane.showInputDialog("Introduce el nombre del producto:");
		String quantity = JOptionPane.showInputDialog("Introduce la cantidad:");

		String product[] = new String[2];
		product[0] = name;
		product[1] = quantity;

		return product;
	}

	// Lista los productos
	public static void listProducts(Hashtable<String, Float> products) {
		Enumeration<String> keys = products.keys();
		Enumeration<Float> elements = products.elements();
		StringBuilder productString = new StringBuilder();

		while (keys.hasMoreElements()) {
			productString.append(keys.nextElement() + " => " + elements.nextElement() + " €\n");
		}

		JOptionPane.showMessageDialog(null, productString.toString());
	}	
}