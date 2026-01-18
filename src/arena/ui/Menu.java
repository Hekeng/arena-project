package arena.ui;

import java.util.ArrayList;
import java.util.Scanner;

import arena.config.MenuConfig;

import arena.core.scene.Pauses;
import arena.helpers.UnSlow;
import arena.helpers.UnSlowPrinter;

import arena.characters.Character;
import arena.logic.TextMessageProvider;

public class Menu {
	
	public static void showFighters (ArrayList<Character> list, Scanner scan){
		
		for (int i = 0; i < list.size(); i++) {
			Character fighter  =  list.get(i);
			
			Menu.printStandardFrame(TextMessageProvider.buildHeroCard(fighter, 2));
			
			Pauses.waitForContinue(scan);
		}
	}
	public static void printStandardFrame(String[] skillsMenu) {

		printMenu(skillsMenu, MenuConfig.MENU_GAP);
	}

	public static void printMenu (String [] arr, String str){
		UnSlowPrinter.oneLetterPrint(str, MenuConfig.MEIN_MENU_DELAY);
		for (int i = 0;  i < arr.length; i++) {
			UnSlow.slowFunc(MenuConfig.MEIN_MENU_DELAY);

			if (i == 0 || i == 1) {
				UnSlowPrinter.oneLetterPrint(arr[i], MenuConfig.MEIN_MENU_DELAY);
			} else if (i == 2) {
				UnSlowPrinter.oneLetterPrint(str, MenuConfig.MEIN_MENU_DELAY);
				UnSlowPrinter.oneLetterPrint(arr[i], MenuConfig.MEIN_MENU_DELAY);
			} else if (i > 2) {
				UnSlowPrinter.oneLetterPrint(arr[i], MenuConfig.MEIN_MENU_DELAY);
			}
		}
		UnSlowPrinter.oneLetterPrint(str, MenuConfig.MEIN_MENU_DELAY);
	}
	

}
