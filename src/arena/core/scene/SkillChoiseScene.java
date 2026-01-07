package arena.core.scene;



import arena.characters.Character;
import arena.helpers.ClearConsole;
import arena.helpers.unInputInt;
import arena.logic.BattleController;

import arena.ui.Menu;

import java.util.Scanner;


public class SkillChoiseScene {
	public static int skillChoose(Character character, Scanner inputScanner) {
		
		Menu.menuChooseSkill(character.getMySkillMenu());
		System.out.println("=====Fighter: " + character.getName() + " do yours choose===");
		System.out.println("============================================");
		int answer = unInputInt.ZhalenInput(inputScanner);
		ClearConsole.clearConsole();
		return answer;

	}
	
}
