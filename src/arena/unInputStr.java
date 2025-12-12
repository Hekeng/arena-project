package arena;

import java.util.Scanner;

public class unInputStr {
	public static String StringInput(Scanner inputScanner) {
		
		String analizAingabe = "";
		int ifValid = 0;
		
		do {
			int checkPrufung = 1;
			analizAingabe = inputScanner.nextLine();
			//uberprufung lear eingabe
			if (analizAingabe.trim().isEmpty()) {
				System.out.println("Eingabe solen hat booschtaben nischt lear, bitte noch einmall ein");
				ifValid = 0;
				continue;
			}
			
			//uberprufung chahlen
			
			for (int i = 0; i < analizAingabe.length(); i++) {
				char currentC = analizAingabe.charAt(i);
				if (java.lang.Character.isDigit(currentC)){
					
					System.out.println("Eingabe solen hat booschtaben nischt lear, bitte noch einmall ein");
					checkPrufung = 0;
					break;
				}
			}
			
			if (checkPrufung == 1) {
				ifValid = 1;
			}
			
			
		} while (ifValid == 0);
		
		return analizAingabe;
	}
}
