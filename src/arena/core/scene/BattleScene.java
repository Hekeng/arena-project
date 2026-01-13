package arena.core.scene;

import java.util.Scanner;

import arena.logic.BattleController;
import arena.logic.RoundResult;

import arena.ui.Menu;

import arena.config.FightMenuConfig;

import arena.helpers.ClearConsole;
import arena.helpers.UnRand;

import arena.dialogs.BattleNarration;

public class BattleScene {
	
	public static boolean battleScena(BattleController battle, Scanner scan){
		boolean eventCooldown = false;
		while (battle.getAttacker().getIsAlive() && battle.getDefender().getIsAlive()) {

			//антураж боя
			if (!eventCooldown && UnRand.randomNumber(1, 5) == 1) {

				ClearConsole.clearConsole();
				String [] event = BattleNarration.getRandomArenaEvent(battle.getAttacker(),battle.getDefender());
				Menu.printStandardFrame(event);
				Pauses.waitForContinue(scan);
				ClearConsole.clearConsole();
				eventCooldown = true;
			} else {
				eventCooldown = false; // Даем шанс на событие в следующем раунде
			}

			int atkChoice = SkillChoiceScene.skillChoose(battle.getAttacker(), scan);
			int defChoice = SkillChoiceScene.skillChoose(battle.getDefender(), scan);
			
			RoundResult result = battle.executeRound(atkChoice, defChoice);
			
			String[] fightResultMessage = FightMenuConfig.getAfterRoundStatus(battle.getAttacker(), battle.getDefender(), result
					//battle.getAttacker(), result.attackMessage, battle.getDefender(), result.defMessage, result.damage
			);
			
			Menu.printStandardFrame(fightResultMessage);

			if (!battle.getDefender().getIsAlive() || !battle.getAttacker().getIsAlive()) {
				Pauses.waitForContinue(scan);
				return true;
			}
			
			battle.changeRolls();
			
			 Pauses.waitForContinue(scan);
			 ClearConsole.clearConsole();
		}
//		Character winner = battle.getAttacker().getIsAlive() ? battle.getAttacker() : battle.getDefender();
//		Character loser = battle.getAttacker().getIsAlive() ? battle.getDefender() : battle.getAttacker();

	return true;
	}
}
