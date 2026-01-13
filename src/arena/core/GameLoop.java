package arena.core;
import java.util.Scanner;
import java.util.ArrayList;

import arena.characters.Character;

import arena.characters.CreateCharacters;
import arena.config.FightClassesConfig;
import arena.config.FightMenuConfig;
import arena.config.MenuConfig;
import arena.core.scene.Pauses;
import arena.core.system.LoadManager;
import arena.dialogs.SystemMessages;
import arena.helpers.ClearConsole;
import arena.helpers.UnInputInt;
import arena.helpers.UnInputStr;
import arena.logic.Validation;
import arena.ui.Menu;


public class GameLoop {

	public static void startMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_START_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_START_MENU);
			switch (menuChoice) {
				case 1:
					boolean iiFlag = true;
					ClearConsole.clearConsole();
					//после появления ии переделать правильно
					hubMenu(scan, list);
					break;
				case 2:
					iiFlag = false;
					hubMenu(scan, list);
					break;
				case 0:
					return;
				default:
					break;
			}
		}
	}

	public static void hubMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			//ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_HUB_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_HUB_MENU);
			switch (menuChoice) {
				case 1:
					chooseClassMenu(scan, list);
					break;
				case 2:
					loadMenu(scan, list);
					break;
				case 3:
					if (list.isEmpty()) {
						Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
						//Pauses.waitForContinue(scan);
						continue;
					} else {
						for (Character c : list) {
							ClearConsole.clearConsole();
							Menu.printStandardFrame(FightClassesConfig.buildHeroCard(c, 3));
							Pauses.waitForContinue(scan);
						}
					}
					break;
				case 9:
					if (Validation.quantityFightersValid(list, FightMenuConfig.MIN_FIGHTERS_QUANTITY)) {
						FightLoop.startFight(list, scan);
						return;
					} else {
						Menu.printStandardFrame(SystemMessages.ERR_QUANTITY_FIGHTERS);
						break;
					}
				case 0:
					return;
				default:
					break;
			}
		}
	}

	public static void chooseClassMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			//ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_CHOOSE_CLASS);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CHOOSE_CLASS);
			switch (menuChoice) {
				case 1:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_MAGE);
					if (!list.isEmpty()) return;
					break;
				case 2:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_WARRIOR);
					if (!list.isEmpty()) return;
					break;
				case 3:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_ASSASSIN);
					if (!list.isEmpty()) return;
					break;
				case 0:
					return;
				default:
					break;
			}
		}
	}

	public static void classPreviewMenu(Scanner scan, ArrayList<Character> list, int classChoice){
		//ClearConsole.clearConsole();

		Menu.printStandardFrame(FightClassesConfig.buildHeroCard(CreateCharacters.createPreviewHero(classChoice), 1));
		Menu.printStandardFrame(MenuConfig.MENU_CLASS_PREVIEW);
		int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CLASS_PREVIEW);


		switch (menuChoice) {
			case 1:
				enterNameMenu(scan, list, classChoice);
				return;

			case 0:
				return;
			default:
				break;
		}

	}
	public static void enterNameMenu(Scanner scan, ArrayList<Character> list, int classChoice) {
		String inputName = "";
		//ClearConsole.clearConsole();
		Menu.printStandardFrame(MenuConfig.MENU_ENTER_NAME);
		while (true) {
			inputName = UnInputStr.StringInput(scan);
			if (Validation.characterNameValid(list, inputName)) {
				break;
			}
			Pauses.waitForContinue(scan);
			//ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_ENTER_NAME);
		}
		CreateCharacters.CreateCharacter(classChoice, inputName, list);
		return;
	}

	public static void loadMenu (Scanner scan, ArrayList<Character> list) {
		//ClearConsole.clearConsole();
		if (list.size() < FightMenuConfig.MIN_FIGHTERS_QUANTITY) {
			Menu.printStandardFrame(MenuConfig.buildHallOfFameMenu());
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.buildHallOfFameMenu());
			if (menuChoice == 0) {
				return;
			}
			Character newHero = LoadManager.loadCharacter(menuChoice);
			if (newHero != null) {
				if (Validation.isNameInParty(list, newHero.getName())) {
					Menu.printStandardFrame(SystemMessages.ERR_CHAR_EXIST);
					Pauses.waitForContinue(scan);
					return;
				} else {
					list.add(newHero);
					System.out.println("Hero " + newHero.getName() + " successfully joined the party!");
					Pauses.waitForContinue(scan);
					return;
				}

			} else {
				Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
				return;
			}

		} else {
			// Если в отряде уже максимум бойцов, выводим сообщение и уходим
			Menu.printStandardFrame(SystemMessages.ERR_PARTY_FULL);
			Pauses.waitForContinue(scan);
			return;
		}
	}
}