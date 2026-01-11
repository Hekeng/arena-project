package arena.core.scene;



import arena.characters.Character;
import arena.helpers.ClearConsole;
import arena.helpers.UnInputInt;

import arena.ui.Menu;

import java.util.Scanner;


public class SkillChoiceScene {
	public static int skillChoose(Character character, Scanner inputScanner) {
		
		Menu.printStandardFrame(character.getMySkillMenu());
		System.out.println("=====Fighter: " + character.getName() + " do yours choose===");
		System.out.println("============================================");
		int answer = UnInputInt.numericInput(inputScanner);
		ClearConsole.clearConsole();
		return answer;

	}
	
}
