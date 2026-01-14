package arena.core.scene;



import arena.characters.Character;
import arena.helpers.ClearConsole;
import arena.helpers.UnInputInt;

import arena.ui.Menu;

import arena.logic.BotIntelect;

import java.util.Scanner;


public class SkillChoiceScene {
	public static int skillChoose(Character character, Character character1, Scanner scan) {

		Menu.printStandardFrame(character.getMySkillMenu());
		System.out.println("==== Fighter: " + character.getName() + " do yours choose ===");//refactor
		System.out.println("============================================");//refactor
		if (!character.getisAi()) {
			int answer = UnInputInt.numericInput(scan);
			ClearConsole.clearConsole();
			return answer;
		} else {
			int answer = BotIntelect.botIntelect(character, character1);
			Pauses.waitForContinue(scan);
			ClearConsole.clearConsole();
			System.out.println("==================== "+ answer +" =====================");
			return answer;
		}
	}
	
}
