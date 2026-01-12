package arena.logic;

import arena.characters.Character;
import arena.config.MenuConfig;
import arena.core.system.SaveManager;
import arena.dialogs.SystemMessages;
import arena.helpers.UnInputInt;
import arena.helpers.UnInputStr;
import arena.helpers.UnSlow;
import arena.ui.Menu;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

public class Validation {
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
	
	public static int chooseClassMenuValid (Scanner Scan, String[] menuArray){
		int createCharacterMenuUserAnswer;
		
		do{
			createCharacterMenuUserAnswer = UnInputInt.numericInput(Scan, menuArray);
			
			if (Validation.quantityMenuItemsValid(createCharacterMenuUserAnswer,  menuArray) == 0) {
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

	public static String characterNameValid (ArrayList<Character> list, Scanner scan) {
		while (true) {
			String analyzedInputName = UnInputStr.StringInput(scan);
			boolean flag = false;

			if (analyzedInputName.isEmpty()) {
				Menu.printStandardFrame(SystemMessages.ERR_EMPTY_NAME);
				continue;
			}
			// 1. Проверяем в текущей сессии (в списке list)
			for (Character analyzedCharacter : list) {
				if (analyzedCharacter.getName().equalsIgnoreCase(analyzedInputName)) {
					Menu.printStandardFrame(SystemMessages.ERR_NAME_EXIST);
					flag = true;
					break;
				}
			}

			// Если нашли в списке — идем на новый круг ввода
			if (flag) continue;

			// 2. Проверяем на диске (в папке saves)
			if (isNameTaken(analyzedInputName)) {
				Menu.printStandardFrame(SystemMessages.ERR_NAME_EXIST);
				continue; // Имя занято на диске — на новый круг
			}

			// Если прошли обе проверки — только тогда возвращаем имя
			return analyzedInputName;
		}
	}
	public static boolean isNameTaken(String inputName) {
		// 1. Формируем путь к потенциальному файлу (только имя + расширение)
		String filePath = SaveManager.SAVE_PATH + inputName + ".dat";
		File file = new File(filePath);
		return file.exists();
	}
	
//	public static String errorMenuMessage (int maxChoice, String[] menuArray){
//		String message;
//
//		message = String.format("Yours input: " + %d + " is wrong please input only 0, 1, 2. ")
//
//		return message;
//	}
	

}
