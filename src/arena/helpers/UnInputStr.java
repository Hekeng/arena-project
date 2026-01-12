package arena.helpers;

import java.util.Scanner;

import arena.ui.Menu;

import arena.dialogs.SystemMessages;

public class UnInputStr {
	public static String StringInput(Scanner inputScanner) {
		
		String analizAingabe = "";
		int ifValid = 0;
		
		do {
			int checkPrufung = 1;
			analizAingabe = inputScanner.nextLine();
			//uberprufung lear eingabe
			if (analizAingabe.trim().isEmpty()) {
				Menu.printStandardFrame(SystemMessages.ERR_STRING_INPUT);
				ifValid = 0;
				continue;
			}

			checkPrufung = 1;

			if (checkPrufung == 1) {
				ifValid = 1;
			}
			
			
		} while (ifValid == 0);
		
		return analizAingabe.trim();
	}
}
