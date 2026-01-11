package arena.ui;

import java.util.ArrayList;
import java.util.Scanner;

import arena.config.FightMenuConfig;
import arena.config.MenuConfig;

import arena.core.scene.Pauses;
import arena.helpers.UnSlow;
import arena.helpers.UnSlowPrinter;

import arena.config.FightClassesConfig;

import arena.characters.Character;



public class Menu {

	public static void menuStart() {
		printMenu(MenuConfig.MAIN_MENU, MenuConfig.MENU_GAP);
	}
	
	public static void menuChooseClass(){
		printMenu(MenuConfig.MENU_CHOOSE_CLASS, MenuConfig.MENU_GAP);
	}
	
//	public static void printTurnResult (){
//		printMenu(FightMenuConfig.getAfterRoundStatus(),
//				MenuConfig.MENU_GAP);};
	
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
	
	public static void showFighters (ArrayList<Character> list, Scanner scan){
		
		for (int i = 0; i < list.size(); i++) {
			Character fighter  =  list.get(i);
			//FightClassesConfig.buildHeroCard(fighter, 2);
			Menu.printStandardFrame(FightClassesConfig.buildHeroCard(fighter, 2));
			//UnSlowPrinter.oneLetterPrint(fighter.toString(), 10);
			Pauses.waitForContinue(scan);
			//ClearConsole.clearConsole();
		}
		
	}
	public static void printStandardFrame(String[] skillsMenu) {
		// Получаем массив строк ПРЯМО из персонажа
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
