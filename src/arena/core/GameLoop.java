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
import arena.logic.GameContext;
import arena.ui.Menu;


public class GameLoop {
	public static void start(Scanner scan, ArrayList<Character> list) {

		GameContext context = new GameContext();
		while (!context.getNextState().equals("EXIT")) {
			switch (context.getNextState()) {
				case "MAIN_MENU":
					startMenu(scan, context);
					break;
				case "AI_CHOICE":
					chooseClassMenu(scan, context);
					//aiChoose_class(scan, list);
					break;
				case "HUB":
					hubMenu(scan, context);
					break;
				case "CHOICE_CLASS":
					chooseClassMenu(scan, context);
					break;
				case "CLASS_PREVIEW":
					classPreviewMenu(scan, context);
					break;
				case "ENTER_NAME":
					while (true) {
						enterNameMenu(scan, context);
						if (!Validation.characterNameValid(list, context.getPendingName())) {
							continue;
						}
						context.setNextState("CREATE_CHARACTER");
						break;
					}
					break;

				case "CREATE_CHARACTER":
					Character newCharacter = CreateCharacters.CreateCharacter(context.getSelectedClassId(), context.getPendingName());
					if (context.getIsAiGame()){
						newCharacter.setisAi(true);
					}
					list.add(newCharacter);
					context.setIsAiGame(false);
					context.setNextState("HUB");
					context.setPendingName("");
					context.setSelectedClassId(-1);
					break;

				case "SHOW_DUELIST":
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
					context.setNextState("HUB");
					break;

				case "FIGHT":
					if (Validation.quantityFightersValid(list, FightMenuConfig.MIN_FIGHTERS_QUANTITY)) {
						FightLoop.startFight(list, scan);
						context.setNextState("MAIN_MENU");
					} else {
						Menu.printStandardFrame(SystemMessages.ERR_QUANTITY_FIGHTERS);
						context.setNextState("HUB");
						break;
					}
					break;


				case "LOAD_MENU":
					loadMenu(scan, list, context);

				default:
					break;
			}
		}
	}

	public static void startMenu(Scanner scan, GameContext context){

			ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_START_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_START_MENU);
			switch (menuChoice) {
				case 1:
					context.setNextState("CHOICE_CLASS");
					context.setIsAiGame(true);
					break;
				case 2:
					context.setNextState("HUB");
					break;
				case 0:
					context.setNextState("EXIT");
					break;
				default:
					break;
			}

	}
	public static void aiChoose_class (Scanner scan, GameContext context){

			switch (context.getSelectedClassId()) {
				case 1:
					context.setPendingName("BOT_Mage");
					break;
				case 2:
					context.setPendingName("BOT_Warrior");
					break;
				case 3:
					context.setPendingName("BOT_Assassin");
					break;

				default:
					break;
			}

	}




	public static void hubMenu(Scanner scan, GameContext context){

			Menu.printStandardFrame(MenuConfig.MENU_HUB_MENU);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_HUB_MENU);
			switch (menuChoice) {
				case 1:
					context.setNextState("CHOICE_CLASS");
					break;
				case 2:
					context.setNextState("LOAD_MENU");
					break;
				case 3:
					context.setNextState("SHOW_DUELIST");
					break;
				case 9:
					context.setNextState("FIGHT");
					break;
				case 0:
					context.setNextState("MAIN_MENU");
					break;
				default:
					break;
			}

	}

	public static void chooseClassMenu(Scanner scan, GameContext context){
			//ClearConsole.clearConsole();
			Menu.printStandardFrame(MenuConfig.MENU_CHOOSE_CLASS);
			context.setSelectedClassId(UnInputInt.numericInput(scan, MenuConfig.MENU_CHOOSE_CLASS));
			switch (context.getSelectedClassId()) {
				case 1, 2 , 3:
					context.setNextState("CLASS_PREVIEW");
					break;
				case 0:
					if (context.getIsAiGame()){
						context.setNextState("MAIN_MENU");
						context.setSelectedClassId(-1);
						context.setIsAiGame(false);
					} else {
						context.setNextState("HUB");
						context.setSelectedClassId(-1);
					}

					break;
				default:
					break;
			}
	}

	public static void classPreviewMenu(Scanner scan,GameContext context){
			Menu.printStandardFrame(FightClassesConfig.buildHeroCard(CreateCharacters.createPreviewHero(context.getSelectedClassId()), 1));
			Menu.printStandardFrame(MenuConfig.MENU_CLASS_PREVIEW);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CLASS_PREVIEW);
			switch (menuChoice) {
				case 1:
					if(!context.getIsAiGame()){
					context.setNextState("ENTER_NAME");
					break;
					} else {
						aiChoose_class (scan, context);
						context.setNextState("CREATE_CHARACTER");
					}
					break;
				case 0:
					if (context.getIsAiGame()){
						context.setNextState("CHOICE_CLASS");
						context.setSelectedClassId(-1);
					} else {
					context.setNextState("HUB");
					context.setSelectedClassId(-1);}
					break;
				default:
					break;
			}
	}
	public static void enterNameMenu(Scanner scan, GameContext context) {

		Menu.printStandardFrame(MenuConfig.MENU_ENTER_NAME);
		context.setPendingName(UnInputStr.StringInput(scan));


	}

	public static void loadMenu (Scanner scan, ArrayList<Character> list, GameContext context) {

		if (list.size() < FightMenuConfig.MIN_FIGHTERS_QUANTITY) {
			Menu.printStandardFrame(MenuConfig.buildHallOfFameMenu());
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.buildHallOfFameMenu());
			if (menuChoice == 0) {
				context.setNextState("HUB");
				return;
			}
			Character newHero = LoadManager.loadCharacter(menuChoice);
			if (newHero != null) {
				if (Validation.isNameInParty(list, newHero.getName())) {
					Menu.printStandardFrame(SystemMessages.ERR_CHAR_EXIST);
					Pauses.waitForContinue(scan);
					context.setNextState("HUB");
					return;
				} else {
					list.add(newHero);
					System.out.println("Hero " + newHero.getName() + " successfully joined the party!");
					Pauses.waitForContinue(scan);
					context.setNextState("HUB");
					return;
				}

			} else {
				Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
				context.setNextState("HUB");
				return;
			}

		} else {
			// Если в отряде уже максимум бойцов, выводим сообщение и уходим
			Menu.printStandardFrame(SystemMessages.ERR_PARTY_FULL);
			Pauses.waitForContinue(scan);
			context.setNextState("HUB");
			return;
		}
	}
}