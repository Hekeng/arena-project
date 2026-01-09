package arena.helpers;

import java.util.Scanner;

public class UnInputInt {
	public static int ZhalenInput(Scanner inputScanner) {
		int analizAingabe;
		while (!inputScanner.hasNextInt()) {
			System.out.println("Fehler: Eingabe bitte eine richtige ganze Zahl ein:");
			inputScanner.nextLine(); // Вместо .next() используй .nextLine(), чтобы съесть ВЕСЬ неправильный ввод целиком с энтером
		}
		analizAingabe = inputScanner.nextInt();
		inputScanner.nextLine(); // Чистим буфер после успешного ввода числа
		return analizAingabe;
	}
}
