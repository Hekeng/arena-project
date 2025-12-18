package arena.ui;
import arena.config.FightMenuConfig;
import arena.config.MenuConfig;
import arena.helpers.UnSlowPrinter;
import arena.helpers.unSlow;
public class Menu {

	public static void menuStart() {
		printMenu(MenuConfig.MAIN_MENU, MenuConfig.MENU_GAP);
	}
	
	public static void menuChooseClass(){
		printMenu(MenuConfig.MENU_CHOOSE_CLASS, MenuConfig.MENU_GAP);
	}
	
	public static void menuEnterName(){
		UnSlowPrinter.oneLetterPrint(MenuConfig.MENU_GAP, MenuConfig.MEIN_MENU_DELAY);
		UnSlowPrinter.oneLetterPrint(MenuConfig.MENU_ENTER_NAME[0], MenuConfig.MEIN_MENU_DELAY);
		UnSlowPrinter.oneLetterPrint(MenuConfig.MENU_GAP, MenuConfig.MEIN_MENU_DELAY);
	}
	public static void menuChowFighters(){
		UnSlowPrinter.oneLetterPrint(MenuConfig.MENU_GAP, MenuConfig.MEIN_MENU_DELAY);
		UnSlowPrinter.oneLetterPrint(FightMenuConfig.MENU_SHOW_FIGHTERS[0], MenuConfig.MEIN_MENU_DELAY);
		UnSlowPrinter.oneLetterPrint(MenuConfig.MENU_GAP, MenuConfig.MEIN_MENU_DELAY);
	}

	public static void printMenu (String [] arr, String str){
		for (int i = 0;  i < arr.length; i++) {
			unSlow.slowFunc(MenuConfig.MEIN_MENU_DELAY);

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
