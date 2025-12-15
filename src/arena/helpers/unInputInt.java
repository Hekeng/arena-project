package arena.helpers;

import java.util.Scanner;

public class unInputInt {
	public static int ZhalenInput(Scanner inputScanner) {
		// --- 2. Input int ---
		int analizAingabe;
		
		while (!inputScanner.hasNextInt()) {
			System.out.println("Fehler: Eingabe bitte eine richtige ganze Zahl ein:");
			inputScanner.next();
		}
		analizAingabe = inputScanner.nextInt();
		
		inputScanner.nextLine();// очистка буфера
		return analizAingabe;
	}
}
