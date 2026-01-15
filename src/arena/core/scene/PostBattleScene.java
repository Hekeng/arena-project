package arena.core.scene;

import java.util.ArrayList;
import java.util.Scanner;

import arena.characters.Character;

import arena.config.FightClassesConfig;

import arena.ui.Menu;

import arena.helpers.ClearConsole;

import arena.core.system.SaveManager;

import arena.dialogs.BattleNarration;

import arena.logic.AfterBattleHeal;


public class PostBattleScene {

	public static void postBattle(ArrayList<Character> list, Scanner scan) {
		// 1. Находим кто жив, кто мертв
		Character winner = null;
		Character loser = null;

		for (Character c : list) {
			if (c.getIsAlive()) winner = c;
			else loser = c;
		}

		// 2. Сцена смерти (Панихида)
		ClearConsole.clearConsole();
		Menu.printStandardFrame(BattleNarration.DEATH_TRIBUTE);
		Pauses.waitForContinue(scan);

		// 3. Сцена триумфа
		ClearConsole.clearConsole();
		winner.addWin();
		Menu.printStandardFrame(BattleNarration.VICTORY_SHOUTS);

		// карточка победителя
		String[] winnerCard = FightClassesConfig.buildHeroCard(winner, 3);
		Menu.printStandardFrame(winnerCard);

		Pauses.waitForContinue(scan);

		// 4. Лечение
		ClearConsole.clearConsole();

		Narration.Narration(BattleNarration.RECOVERY_LOG);
		System.out.println("\n====== " + winner.getName() + " restored " + AfterBattleHeal.healthHeal(winner) + " HP. ======");

		SaveManager.saveCharacter(winner);
		Pauses.waitForContinue(scan);

		AfterBattleHeal.healthHeal(loser);
		list.remove(loser);
		list.remove(winner);
		ClearConsole.clearConsole();
	}
}