package arena.helpers;

import java.util.Scanner;

import arena.ui.Menu;

import arena.dialogs.SystemMessages;

public class UnInputInt {
	public static int numericInput(Scanner inputScanner, String[] currentMenu) {
		int analiseInput;
		
		String [] validOptions = menuSimplification(currentMenu);
		
		while (true) {
			if (!inputScanner.hasNextInt()) {
				// Ошибка 1: Это не число
				Menu.printStandardFrame(new String[] {"ERROR", "Incorrect input!", "Please enter a NUMBER"});
				inputScanner.nextLine();
				continue;
			}
			
			analiseInput = inputScanner.nextInt();
			
			if (analiseInput < 0 || analiseInput > currentMenu.length - 1) {
				// Ошибка 2: Число есть, но оно не из меню
				// Вызываем твой метод, который вернет String[]
				Menu.printStandardFrame(SystemMessages.failInputMessage(validOptions, analiseInput));
				inputScanner.nextLine();
				continue;
			}
			
			inputScanner.nextLine();
			return analiseInput;
		}
		
		
		
	}
	public static String [] menuSimplification(String[] currentMenu){
		
//		int analiseQuantity = currentMenu.length - 2;
		String[] validOptions = new String [currentMenu.length - 2];
		int count = 0;
		for (int i = 2; i < currentMenu.length; i++) {
			String simleString = currentMenu[i];
			int dotIndex = simleString.indexOf(".");
			if (dotIndex != -1){
			String sub = simleString.substring(0, dotIndex);
			sub = sub.replaceAll("[^0-9]", "");
			validOptions[count] = sub;
			count++;
			}
		}
		return validOptions;


	}
//	public class UnInputInt {
//		public static int ZhalenInput(Scanner inputScanner) {
//			int analizAingabe;
//			while (!inputScanner.hasNextInt()) {
//				System.out.println("Error: Incorrect input. Please try again ():");
//				inputScanner.nextLine(); // Вместо .next() используй .nextLine(), чтобы съесть ВЕСЬ неправильный ввод целиком с энтером
//			}
//			analizAingabe = inputScanner.nextInt();
//			inputScanner.nextLine(); // Чистим буфер после успешного ввода числа
//			return analizAingabe;
//		}




}
