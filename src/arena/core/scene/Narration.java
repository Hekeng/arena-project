package arena.core.scene;

import java.util.Scanner;

import arena.dialogs.BattleNarration;
import arena.helpers.UnSlowPrinter;
import arena.config.MenuConfig;

public class Narration {
	public static void NarrationIntro (String [] arr) {
		for (String printedStr:arr) {
			UnSlowPrinter.oneLetterPrint(printedStr, MenuConfig.OUTPUT_MENU_DELAY);
		}
	}



}
