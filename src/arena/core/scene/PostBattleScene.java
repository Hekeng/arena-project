package arena.core.scene;

import java.util.ArrayList;
import java.util.Scanner;

import arena.characters.Character;

import arena.logic.TextMessageProvider;
import arena.ui.Menu;

import arena.helpers.ClearConsole;

import arena.core.system.SaveManager;

import arena.dialogs.BattleNarration;

import arena.logic.AfterBattleHeal;


public class PostBattleScene {

	public static void postBattle(ArrayList<Character> list, Scanner scan) {
		Character winner = null;
		Character loser = null;

		for (Character c : list) {
			if (c.getIsAlive()) winner = c;
			else loser = c;
		}

		ClearConsole.clearConsole();
		Menu.printStandardFrame(BattleNarration.DEATH_TRIBUTE);
		Pauses.waitForContinue(scan);

		ClearConsole.clearConsole();
		winner.addWin();
		Menu.printStandardFrame(BattleNarration.VICTORY_SHOUTS);

		String[] winnerCard = TextMessageProvider.buildHeroCard(winner, 3);
		Menu.printStandardFrame(winnerCard);

		Pauses.waitForContinue(scan);

		ClearConsole.clearConsole();
		
		Menu.printStandardFrame(BattleNarration.RECOVERY_LOG);
		System.out.println("\n====== " + winner.getName() + " restored " + AfterBattleHeal.healthHeal(winner) + " HP. ======");

		SaveManager.saveCharacter(winner);
		Pauses.waitForContinue(scan);

		AfterBattleHeal.healthHeal(loser);
		list.remove(loser);
		list.remove(winner);
		ClearConsole.clearConsole();
	}
}