package arena.core.scene;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;

import arena.config.FightClassesConfig;

import arena.ui.Menu;

import arena.helpers.UnSlowPrinter;
import arena.helpers.ClearConsole;
import arena.helpers.UnRand;

import arena.core.scene.Pauses;
import arena.core.scene.Narration;
import arena.core.scene.FirstDiceThrowScene;

import arena.logic.FightersDiceThrow;
import arena.logic.BattleController;

import arena.core.scene.SkillChoiseScene;
import arena.core.scene.BattleScene;
import arena.core.scene.PostBattleScene;


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
		Menu.menuChooseSkill(BattleNarration.DEATH_TRIBUTE);
		Pauses.waitForContinue(scan);

		// 3. Сцена триумфа
		ClearConsole.clearConsole();
		winner.addWin(); // Прибавляем победу сразу
		Menu.menuChooseSkill(BattleNarration.VICTORY_SHOUTS);

		// Показываем карточку победителя (наш режим 3 - только статы и имя)
		String[] winnerCard = FightClassesConfig.buildHeroCard(winner, 3);
		Menu.menuChooseSkill(winnerCard);

		Pauses.waitForContinue(scan);

		// 4. Лечение
		ClearConsole.clearConsole();
		int healAmount = (int)((100 - winner.getHealth()) * 0.2); // 20% от нехватки до 100
		winner.setHealth(winner.getHealth() + healAmount);


		Narration.Narration(BattleNarration.RECOVERY_LOG);
		System.out.println("\n======" + winner.getName() + " restored " + healAmount + " HP.======");
		Pauses.waitForContinue(scan);

		// 5. Сохранение (Тут будет вызов метода записи в файл)
		// SaveSystem.saveHero(winner);

		// 6. Очистка списка (Удаляем мертвого, чтобы он не вернулся в меню)
		list.remove(loser);
		ClearConsole.clearConsole();
	}
}