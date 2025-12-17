package arena.core;
import java.util.Scanner;
import java.util.ArrayList;


import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.helpers.UnSlowPrinter;
import arena.helpers.unSlow;
import arena.helpers.unInputStr;
import arena.helpers.unInputInt;
import arena.config.MenuConfig;
import arena.config.FightMenuConfig;
import arena.ui.Menu;
public class GameLoop {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		ArrayList<Character> characterList = new ArrayList<>();// ВАЖНО ПЕРЕНЕСТИ!!!!
		menuNav(inputScanner, characterList);
		//Menu.menuStart();
		//Menu.menuChooseClass();
		//Menu.menuEnterName();


		inputScanner.close();// ВАЖНО ПЕРЕНЕСТИ!!!!
	}

	public static void menuNav(Scanner scan, ArrayList<Character> list) {
		while (true) {
			Menu.menuStart();
			int mainMenuChoice = mainMenuValid(scan, list);

			// 0. Exit
			if (mainMenuChoice == MenuConfig.CLASS_ID_BACK) {
				break;
			}

			// 1. Создание персонажа (переходим во вложенную логику)
			if (mainMenuChoice == MenuConfig.CREATE_CHARACTER_ID) {
				Menu.menuChooseClass();
				int classChoice = chooseClassMenuValid(scan);

				// Если в подменю выбрали "Назад"
				if (classChoice == MenuConfig.CLASS_ID_BACK) {
					unSlow.slowFunc(MenuConfig.OUTPUT_MENU_DELEY);
					System.out.println("Return to main menu");
					continue;
				}

				// Если выбрали конкретный класс
				if (classChoice == MenuConfig.CLASS_ID_MAGE || classChoice == MenuConfig.CLASS_ID_WARRIOR) {
					Menu.menuEnterName();
					String characterName = unInputStr.StringInput(scan);
					characterCreation(classChoice, characterName, list);
				}
			}
		}
	}


	public static int mainMenuValid(Scanner Scan, ArrayList<Character> List){

		int meinMenuUserAnswer ;

		do {

			meinMenuUserAnswer = unInputInt.ZhalenInput(Scan);
			System.out.println("Yours input was: " + meinMenuUserAnswer);//test

			if (!quantityPunktsMenuValid(meinMenuUserAnswer, MenuConfig.MAIN_MENU_PUNKTS_QUANTUTY)) {
				unSlow.slowFunc(MenuConfig.OUTPUT_MENU_DELEY);
				System.out.println("Yours input: " + meinMenuUserAnswer + " is wrong please input only 0, 1, 2. ");
				Menu.menuStart();
				System.out.println("Input please you choice :");//test
				continue;
			}

			if (!quantityFightersValid(List,FightMenuConfig.MAX_FIGHTERS_QUANTITY)) {
				System.out.println("You already have two fighters! You kan only start to fight! (menu 2)");
				Menu.menuStart();
				System.out.println("Input please you choice ");
				continue;
			}

			return meinMenuUserAnswer;

		} while (true);

	}

	public static int chooseClassMenuValid (Scanner Scan){
		int createCharacterMenuUserAnswer;

		do{
			createCharacterMenuUserAnswer = unInputInt.ZhalenInput(Scan);

			if (!quantityPunktsMenuValid(createCharacterMenuUserAnswer, MenuConfig.CHOOSE_CLASS_MENU_PUNKTS_QUANTUTY)) {
				unSlow.slowFunc(MenuConfig.OUTPUT_MENU_DELEY);
				System.out.println("Yours input: " + createCharacterMenuUserAnswer + " is wrong please input only 0, 1, 2. ");
				System.out.println("Input please you choice: ");//test
				continue;
			}

			return createCharacterMenuUserAnswer;
		}while (true);
	}


	public static boolean quantityPunktsMenuValid (int answer, int quantityPunkts) {
		if (answer < 0 || answer >= quantityPunkts) {
			return false;
		}
		return true;
	}
	public static boolean quantityFightersValid (ArrayList<Character> List, int maxFighters){
		if (List.size() >= maxFighters) {
			return false;
		}
		return true;
	}

	public static void characterCreation (int answer, String charName, ArrayList<Character> List){
		if (answer == 1){
			System.out.println("You created Mage!" );
			Mage mage = new Mage(charName, 100, 40);
			List.add(mage);
		}

		if (answer == 2){
			System.out.println("You created Warrior!" );
			Warrior warrior = new Warrior(charName, 100, 20);
			List.add(warrior);
		}
	}

}
