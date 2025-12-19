package arena.core.scene;

import arena.helpers.UnSlowPrinter;
import arena.dialogs.SystemMessages;

import java.util.Scanner;

public class Pauses {
	public static void waitForContinue(Scanner scan) {
		System.out.println();
		UnSlowPrinter.oneLetterPrint(SystemMessages.PRESS_ENTER, 10);
		scan.nextLine();
	}
}
