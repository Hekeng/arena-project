package arena.logic;

import arena.characters.CreateCharacters;
import arena.config.FightClassesConfig;
import arena.core.FightLoop;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;

import arena.core.scene.Pauses;
import arena.dialogs.SystemMessages;

import arena.helpers.ClearConsole;
import arena.helpers.UnInputInt;
import arena.helpers.UnInputStr;

import arena.ui.Menu;

import arena.config.MenuConfig;
import arena.config.FightMenuConfig;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuNavigation {

	public static void navigation(Scanner scan){

	}

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
			ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_HUB_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_HUB_MENU);
			switch (menuChoice) {
				case 1:
					chooseClassMenu(scan, list);
					break;
				case 2:
					//LOADMENU
					break;
				case 3:
					if (list.isEmpty()) {
						Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
						continue;
						} else {
						for (Character c : list) {
							Menu.printStandardFrame(FightClassesConfig.buildHeroCard(c, 2));
						}
						Pauses.waitForContinue(scan);
					}
					break;
				case 9:
					if (Validation.quantityFightersValid(list, FightMenuConfig.MIN_FIGHTERS_QUANTITY)) {
						FightLoop.startFight(list, scan);
						break;
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
				ClearConsole.clearConsole();
				Menu.printStandardFrame(MenuConfig.MENU_CHOOSE_CLASS);
				int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CHOOSE_CLASS);
				switch (menuChoice) {
					case 1:
//						enterNameMenu(scan, list, FightClassesConfig.CLASS_ID_MAGE);
						classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_MAGE);
						break;
					case 2:
//						enterNameMenu(scan, list, FightClassesConfig.CLASS_ID_WARRIOR);
						classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_WARRIOR);
						break;
					case 0:
						return;
					default:
						break;
				}
			}
		}

	public static void classPreviewMenu(Scanner scan, ArrayList<Character> list, int classChoice){
		ClearConsole.clearConsole();

		Character tempGhost;
		if (classChoice == FightClassesConfig.CLASS_ID_MAGE) {
			tempGhost = new Mage("Preview", FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_MAGE_RES);
		} else {
			tempGhost = new Warrior("Preview", FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_WARRIOR_RES);
		}

		Menu.printStandardFrame(FightClassesConfig.buildHeroCard(tempGhost, 1));
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
	public static void enterNameMenu(Scanner scan, ArrayList<Character> list, int classChoice){
		ClearConsole.clearConsole();
		Menu.printStandardFrame(MenuConfig.MENU_ENTER_NAME);

			String charName = Validation.characterNameValid(list, scan);
			CreateCharacters.CreateCharacter(classChoice, charName, list);

	}


}
