package arena.core.scene;

import java.util.Scanner;

import arena.characters.Character;
import arena.logic.BattleController;
import arena.logic.RoundResult;
import arena.logic.Dice;

import arena.ui.Menu;

import arena.config.FightMenuConfig;

import arena.helpers.ClearConsole;
import arena.helpers.UnRand;

import arena.dialogs.BattleNarration;

import arena.core.scene.Narration;
public class BattleScene {
	
	public static boolean battleScena(BattleController battle, Scanner scan){
		boolean eventCooldown = false;
		while (battle.getAttacker().getIsAlive() && battle.getDefender().getIsAlive()) {

			//антураж боя
			if (!eventCooldown && UnRand.randomNumber(1, 5) == 1) {

				ClearConsole.clearConsole();
				String [] event = BattleNarration.getRandomArenaEvent(battle.getAttacker(),battle.getDefender());
				Menu.menuChooseSkill(event);
				Pauses.waitForContinue(scan);
				ClearConsole.clearConsole();
				eventCooldown = true;
			} else {
				eventCooldown = false; // Даем шанс на событие в следующем раунде
			}

			
			int atkChoice = SkillChoiseScene.skillChoose(battle.getAttacker(), scan);
			int defChoice = SkillChoiseScene.skillChoose(battle.getDefender(), scan);
			
			RoundResult result = battle.executeRound(atkChoice, defChoice);
			
			String[] fightResultMessage = FightMenuConfig.getAfterRoundStatus(battle.getAttacker(), battle.getDefender(), result
					//battle.getAttacker(), result.attackMessage, battle.getDefender(), result.defMessage, result.damage
			);
			
			Menu.menuChooseSkill(fightResultMessage);

			if (!battle.getDefender().getIsAlive() || !battle.getAttacker().getIsAlive()) {
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
