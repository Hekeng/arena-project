package arena.core.scene;

import java.util.ArrayList;
import java.util.Scanner;

import arena.characters.Character;

import arena.config.FightClassesConfig;

import arena.ui.Menu;

import arena.helpers.ClearConsole;

import arena.core.system.SaveManager;


import arena.dialogs.BattleNarration;


public class PostBattleScene {

	public static void postBattle(ArrayList<Character> list, Scanner scan) {
		// 1. Находим кто жив, кто мертв
		Character winner = null;
		Character loser = null;

		for (Character c : list) {
			if (c.getIsAlive()) winner = c;
			else loser = c;
		}
		//Pauses.waitForContinue(scan);
		// 2. Сцена смерти (Панихида)
		ClearConsole.clearConsole();
		Menu.printStandardFrame(BattleNarration.DEATH_TRIBUTE);
		Pauses.waitForContinue(scan);

		// 3. Сцена триумфа
		ClearConsole.clearConsole();
		winner.addWin(); // Прибавляем победу сразу
		Menu.printStandardFrame(BattleNarration.VICTORY_SHOUTS);

		// Показываем карточку победителя (наш режим 3 - только статы и имя)
		String[] winnerCard = FightClassesConfig.buildHeroCard(winner, 3);
		Menu.printStandardFrame(winnerCard);

		Pauses.waitForContinue(scan);

		// 4. Лечение
		ClearConsole.clearConsole();
		int healAmount = (int)((100 - winner.getHealth()) * 0.2); // 20% от нехватки до 100
		winner.setHealth(winner.getHealth() + healAmount);


		Narration.Narration(BattleNarration.RECOVERY_LOG);
		System.out.println("\n====== " + winner.getName() + " restored " + healAmount + " HP. ======");
		SaveManager.saveCharacter(winner);
		Pauses.waitForContinue(scan);

		// 5. Сохранение (Тут будет вызов метода записи в файл)
		

		// 6. Очистка списка (Удаляем мертвого, чтобы он не вернулся в меню)
		list.remove(loser);
		ClearConsole.clearConsole();
	}
}