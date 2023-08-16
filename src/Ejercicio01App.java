import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ejercicio01App {

	public static void main(String[] args) {
		
		// Preguntamos primero cuantos alumnos pertenecen al curso
		String numStudentsString = JOptionPane.showInputDialog("¿Cuantos alumnos pertenecen al curso?");
		int numStudents = Integer.parseInt(numStudentsString);
		
		// Preguntamos cuantas notas tiene cada alumno
		String numGradesStudentString = JOptionPane.showInputDialog("¿Cuantas notas tendrá cada alumno?");
		int numGradesStudent = Integer.parseInt(numGradesStudentString);
				
		// Llamamos a la función que preguntará los datos y creara una hashtable con la estructura deseada
		Hashtable<String, Double> students = generateStudentsData(numStudents, numGradesStudent);
		
		// Mostramos los resultados
		Enumeration<String> keys = students.keys();
		Enumeration<Double> elements = students.elements();
		
		JOptionPane.showMessageDialog(null, "Las notas medias son las siguientes.");
		while (keys.hasMoreElements()) {
			JOptionPane.showMessageDialog(null, keys.nextElement() + ": " + elements.nextElement());
		}
	}
	
	// Crea una Hashtable con el nombre del alumno y su nota media
	public static Hashtable<String, Double> generateStudentsData(int numStudents, int numGradesStudent) {
		Hashtable<String, Double> students = new Hashtable<String, Double>();
		
		// Preguntamos los datos de cada alumno
		for (int i = 0; i < numStudents; i++) {
			String name = JOptionPane.showInputDialog("Introduce el nombre del alumno " + (i + 1));
			
			// Preguntamos cada nota de cada alumno, y calculamos la media
			double grades[] = new double[numGradesStudent];
			
			for (int j = 0; j < numGradesStudent; j++) {
				String gradeString = JOptionPane.showInputDialog("Introduce la nota " + (j + 1) + " del alumno.");
				double grade = Double.parseDouble(gradeString);
				
				grades[j] = grade;
			}
			
			double averageGrade = calculateAverage(grades);
			
			// Añadimos este alumno con su nota media a la hashtable
			students.put(name, averageGrade);
		}
		
		return students;
	}
	
	// Devuelve la nota media de una lista de notas
	public static double calculateAverage(double[] grades) {
		double sum = 0;
		
		for (int i = 0; i < grades.length; i++) {
			sum += grades[i];
		}
		
		return sum / grades.length;
	}
}