package arena.helpers;

import java.util.Scanner;
import arena.ui.Menu;
import arena.dialogs.SystemMessages;

public class UnInputInt {
	public static int numericInput(Scanner inputScanner, String[] currentMenu) {
		int analiseInput;
		String[] validOptions = menuSimplification(currentMenu);

		while (true) {
			if (!inputScanner.hasNextInt()) {
				Menu.printStandardFrame(SystemMessages.ERR_NOT_A_NUMBER);
				inputScanner.nextLine();
				continue;
			}

			analiseInput = inputScanner.nextInt();
			String inputStr = String.valueOf(analiseInput);
			boolean isFound = false;
			for (String s : validOptions) {
				if (s != null && s.equals(inputStr)) {
					isFound = true;
					break;
				}
			}
			if (isFound) {
				inputScanner.nextLine();
				return analiseInput;
			} else {
				Menu.printStandardFrame(SystemMessages.failInputMessage(validOptions, analiseInput));
				inputScanner.nextLine();
			}
		}
	}
	public static int numericInput (Scanner inputScanner) {
		int analiseInput;
		while (!inputScanner.hasNextInt()) {
			System.out.println("Error: Incorrect input. Please try again ():");
			inputScanner.nextLine();
		}
		analiseInput = inputScanner.nextInt();
		inputScanner.nextLine();
		return analiseInput;
	}
	public static String [] menuSimplification(String[] currentMenu){

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






}
