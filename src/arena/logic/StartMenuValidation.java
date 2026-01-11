package arena.logic;

import arena.characters.Character;
import arena.config.MenuConfig;
import arena.helpers.UnInputInt;
import arena.helpers.UnSlow;
import arena.ui.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class StartMenuValidation {
	public static int mainMenuValid(Scanner Scan, String[] menuArray) {
		
		int meinMenuUserAnswer ;
		
		do {
			
			meinMenuUserAnswer = UnInputInt.numericInput(Scan, menuArray);
			//System.out.println("Yours input was: " + meinMenuUserAnswer);//test
			
			int maxChoice = quantityMenuItemsValid(meinMenuUserAnswer, menuArray);
			
			if (maxChoice == 0) {
				UnSlow.slowFunc(MenuConfig.OUTPUT_MENU_DELAY);

				System.out.println("Yours input: " + meinMenuUserAnswer + " is wrong please input only 0, 1, 2. ");//поправить аутпут
				Menu.menuStart();
				//System.out.println("Input please you choice :");//test
				continue;
			}
			
			return meinMenuUserAnswer;
			
		} while (true);
		
	}
	
	public static int chooseClassMenuValid (Scanner Scan){
		int createCharacterMenuUserAnswer;
		
		do{
			createCharacterMenuUserAnswer = UnInputInt.numericInput(Scan);
			
			if (!quantityMenuItemsValid(createCharacterMenuUserAnswer, MenuConfig.CHOOSE_CLASS_MENU_PUNKTS_QUANTUTY)) {
				UnSlow.slowFunc(MenuConfig.OUTPUT_MENU_DELAY);
				System.out.println("Yours input: " + createCharacterMenuUserAnswer + " is wrong please input only 0, 1, 2. ");
				System.out.println("Input please you choice: ");//test
				continue;
			}
			
			return createCharacterMenuUserAnswer;
		}while (true);
	}
	
	
	public static int quantityMenuItemsValid(int answer, String[] menuArray) {
		
		int maxChoice = menuArray.length - MenuConfig.MENU_HEADER_SIZE;
		
		if (answer < 0 || answer >= maxChoice) {
			
			return maxChoice=0;
		}
		return maxChoice;
	}
	
	public static boolean quantityFightersValid (ArrayList<Character> list, int minFighters){
		if (list.size() >= minFighters) {
			return false;
		}
		return true;
	}
	
//	public static String errorMenuMessage (int maxChoice, String[] menuArray){
//		String message;
//
//		message = String.format("Yours input: " + %d + " is wrong please input only 0, 1, 2. ")
//
//		return message;
//	}
	

}
