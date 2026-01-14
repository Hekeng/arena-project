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
	public void start(Scanner scan, ArrayList<Character> list) {
		String state = "MAIN_MENU";
		while (!state.equals("EXIT")) {
			switch (state) {
				case "MAIN_MENU":
					state = startMenu(scan, list);
					break;
				case "AI_CHOICE":
					state = aiChoose_class(scan, list);
					break;
				case "HUB":
					state = hubMenu(scan, list);
					break;
				case "CHOICE_CLASS":
					String choice = chooseClassMenu(scan);
					if (choice.equals("BACK")) {
						state = "MAIN_MENU";
					} else {
						pendingClassId = Integer.parseInt(choice);
						state = "CLASS_PREVIEW";
					}
					break;
				case "LOAD_MENU":
					state = showHub();
				case "CLASS_PREVIEW":
					state = showHub();

				case "FIGHT":
					runBattle();
					state = "POST_BATTLE";
					break;
				case "POST_BATTLE":
					state = "HUB";
					break;
			}
		}
	}

	public static String startMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_START_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_START_MENU);
			switch (menuChoice) {
				case 1:
					return "AI_CHOICE";
				case 2:
					return "HUB";
				case 0:
					return "EXIT";
					default:
					break;
			}
		}
	}
	public static String aiChoose_class (Scanner scan, ArrayList<Character> list){
		while (true) {
			ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_CHOOSE_CLASS);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CHOOSE_CLASS);
			Character newCharacter = null;
			switch (menuChoice) {
				case 1:
					newCharacter = CreateCharacters.CreateCharacter(menuChoice, "Bot_Mage");
					newCharacter.setisAi(true);
					list.add(newCharacter);
					return "HUB";
				case 2:
					newCharacter = CreateCharacters.CreateCharacter(menuChoice, "Bot_Warrior");
					newCharacter.setisAi(true);
					list.add(newCharacter);
					return "HUB";
				case 3:
					newCharacter = CreateCharacters.CreateCharacter(menuChoice, "Bot_Assassin");
					newCharacter.setisAi(true);
					list.add(newCharacter);
					return "HUB";
				case 0:
					return "MAIN_MENU";
				default:
					break;
			}
		}

	}




	public static String hubMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			Menu.printStandardFrame(MenuConfig.MENU_HUB_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_HUB_MENU);
			switch (menuChoice) {
				case 1:
					return "CHOICE_CLASS";
//					chooseClassMenu(scan, list);
//					break;
				case 2:
					return "LOAD_MENU";
//					loadMenu(scan, list);
//					break;
				case 3:
					if (list.isEmpty()) {
						Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
						continue;
					} else {
						for (Character c : list) {
							ClearConsole.clearConsole();
							Menu.printStandardFrame(FightClassesConfig.buildHeroCard(c, 3));
							Pauses.waitForContinue(scan);
						}
					}
					return "HUB";
				case 9:
					if (Validation.quantityFightersValid(list, FightMenuConfig.MIN_FIGHTERS_QUANTITY)) {
						FightLoop.startFight(list, scan);
						return "HUB";
					} else {
						Menu.printStandardFrame(SystemMessages.ERR_QUANTITY_FIGHTERS);
						break;
					}
				case 0:
					return "MAIN_MENU";
				default:
					break;
			}
		}
	}

	public static String chooseClassMenu(Scanner scan, ArrayList<Character> list){
		while (true) {
			//ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_CHOOSE_CLASS);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CHOOSE_CLASS);
			switch (menuChoice) {
				case 1:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_MAGE);
					if (!list.isEmpty()) {
						return "CLASS_PREVIEW";
					}
				case 2:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_WARRIOR);
					if (!list.isEmpty()) return "HUB";
				case 3:
					classPreviewMenu(scan, list, FightClassesConfig.CLASS_ID_ASSASSIN);
					if (!list.isEmpty()) return "HUB";
				case 0:
					return "HUB";
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
		Character newCharacter = CreateCharacters.CreateCharacter(classChoice, inputName);
		list.add(newCharacter);
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