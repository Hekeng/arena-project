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

import arena.logic.TextMessageProvider;
import arena.logic.Validation;
import arena.logic.GameContext;
import arena.ui.Menu;


public class GameLoop {
	public static void start(Scanner scan, ArrayList<Character> list) {

		GameContext context = new GameContext();
		
		while (context.getNextState() != GameContext.GameState.EXIT) {
			switch (context.getNextState()) {
				case MAIN_MENU:
					startMenu(scan, context);
					break;
				case AI_CHOICE:
					chooseClassMenu(scan, context);
					break;
				case HUB:
					hubMenu(scan, context);
					break;
				case CHOICE_CLASS:
					chooseClassMenu(scan, context);
					break;
				case CLASS_PREVIEW:
					classPreviewMenu(scan, context);
					break;
				case ENTER_NAME:
					enterNameMenu(scan, context);
					if (Validation.characterNameValid(list, context.getPendingName())) {
						context.setNextState(GameContext.GameState.CREATE_CHARACTER);
					}
					break;

				case CREATE_CHARACTER:
					Character newCharacter = CreateCharacters.CreateCharacter(context.getSelectedClassId(), context.getPendingName());
					if (context.getIsAiGame()){
						newCharacter.setisAi(true);
					}
					list.add(newCharacter);
					context.setIsAiGame(false);
					context.setNextState(GameContext.GameState.HUB);
					context.setPendingName(null);
					context.setSelectedClassId(null);
					break;

				case SHOW_DUELIST:
					if (list.isEmpty()) {
						Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
						continue;
					} else {
						for (Character c : list) {
							ClearConsole.clearConsole();
							Menu.printStandardFrame(TextMessageProvider.buildHeroCard(c, 3));
							Pauses.waitForContinue(scan);
						}
					}
					context.setNextState(GameContext.GameState.HUB);
					break;

				case FIGHT:
					if (Validation.quantityFightersValid(list, FightMenuConfig.MIN_FIGHTERS_QUANTITY)) {
						FightLoop.startFight(list, scan);
						context.setNextState(GameContext.GameState.MAIN_MENU);
					} else {
						Menu.printStandardFrame(SystemMessages.ERR_QUANTITY_FIGHTERS);
						context.setNextState(GameContext.GameState.HUB);
						break;
					}
					break;
				case LOAD_MENU:
					loadMenu(scan, list, context);
					break;
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
					context.setNextState(GameContext.GameState.CHOICE_CLASS);
					context.setIsAiGame(true);
					break;
				case 2:
					context.setNextState(GameContext.GameState.HUB);
					break;
				case 0:
					context.setNextState(GameContext.GameState.EXIT);
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
					context.setNextState(GameContext.GameState.CHOICE_CLASS);
					break;
				case 2:
					context.setNextState(GameContext.GameState.LOAD_MENU);
					break;
				case 3:
					context.setNextState(GameContext.GameState.SHOW_DUELIST);
					break;
				case 9:
					context.setNextState(GameContext.GameState.FIGHT);
					break;
				case 0:
					context.setNextState(GameContext.GameState.MAIN_MENU);
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
					context.setNextState(GameContext.GameState.CLASS_PREVIEW);
					break;
				case 0:
					if (context.getIsAiGame()){
						context.setNextState(GameContext.GameState.MAIN_MENU);
						context.setSelectedClassId(null);
						context.setIsAiGame(false);
					} else {
						context.setNextState(GameContext.GameState.HUB);
						context.setSelectedClassId(null);
					}
					break;
				default:
					break;
			}
	}

	public static void classPreviewMenu(Scanner scan,GameContext context){
			Menu.printStandardFrame(TextMessageProvider.buildHeroCard(CreateCharacters.createPreviewHero(context.getSelectedClassId()), 1));
			Menu.printStandardFrame(MenuConfig.MENU_CLASS_PREVIEW);
			int menuChoice = UnInputInt.numericInput(scan, MenuConfig.MENU_CLASS_PREVIEW);
			switch (menuChoice) {
				case 1:
					if(!context.getIsAiGame()){
					context.setNextState(GameContext.GameState.ENTER_NAME);
					break;
					} else {
						aiChoose_class (scan, context);
						context.setNextState(GameContext.GameState.CREATE_CHARACTER);
					}
					break;
				case 0:
					if (context.getIsAiGame()){
						context.setNextState(GameContext.GameState.CHOICE_CLASS);
						context.setSelectedClassId(-1);
					} else {
					context.setNextState(GameContext.GameState.HUB);
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
			Menu.printStandardFrame(TextMessageProvider.buildHallOfFameMenu());
			int menuChoice = UnInputInt.numericInput(scan, TextMessageProvider.buildHallOfFameMenu());
			if (menuChoice == 0) {
				context.setNextState(GameContext.GameState.HUB);
				return;
			}
			Character newHero = LoadManager.loadCharacter(menuChoice);
			if (newHero != null) {
				if (Validation.isNameInParty(list, newHero.getName())) {
					Menu.printStandardFrame(SystemMessages.ERR_CHAR_EXIST);
					Pauses.waitForContinue(scan);
					context.setNextState(GameContext.GameState.HUB);
					return;
				} else {
					list.add(newHero);
					System.out.println("Hero " + newHero.getName() + " successfully joined the party!");
					Pauses.waitForContinue(scan);
					context.setNextState(GameContext.GameState.HUB);
					return;
				}
			} else {
				Menu.printStandardFrame(SystemMessages.ERR_EMPTY_PARTY);
				context.setNextState(GameContext.GameState.HUB);
				return;
			}

		} else {
			Menu.printStandardFrame(SystemMessages.ERR_PARTY_FULL);
			Pauses.waitForContinue(scan);
			context.setNextState(GameContext.GameState.HUB);
			return;
		}
	}
}