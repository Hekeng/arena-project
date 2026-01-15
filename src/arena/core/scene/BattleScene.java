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
				eventCooldown = false; // шанс на событие в следующем раунде
			}

			int atkChoice = SkillChoiceScene.skillChoose(battle.getAttacker(), battle.getDefender(), scan);

			int defChoice = SkillChoiceScene.skillChoose(battle.getDefender(), battle.getAttacker(), scan);
			
			RoundResult result = battle.executeRound(atkChoice, defChoice);
			
			String[] fightResultMessage = FightMenuConfig.getAfterRoundStatus(battle.getAttacker(), battle.getDefender(), result
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


	return true;
	}
}
