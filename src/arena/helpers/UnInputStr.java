package arena.helpers;

import java.util.Scanner;

import arena.ui.Menu;

import arena.dialogs.SystemMessages;

public class UnInputStr {
	public static String StringInput(Scanner inputScanner) {
		
		String analiseinput = null;
		int ifValid = 0;
		
		do {
			int checkProof = 1;
			analiseinput = inputScanner.nextLine();

			if (analiseinput.trim().isEmpty()) {
				Menu.printStandardFrame(SystemMessages.ERR_STRING_INPUT);
				ifValid = 0;
				continue;
			}

			checkProof = 1;

			if (checkProof == 1) {
				ifValid = 1;
			}
			
			
		} while (ifValid == 0);
		
		return analiseinput.trim();
	}
}
