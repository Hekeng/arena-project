package arena.core.scene;

import java.util.Scanner;

import arena.logic.BattleController;
import arena.logic.RoundResult;

import arena.ui.Menu;

import arena.config.FightMenuConfig;

import arena.helpers.ClearConsole;

public class BattleScene {
	
	public static boolean battleScena(BattleController battle, Scanner scan){
		while (battle.getAttacker().getIsAlive() && battle.getDefender().getIsAlive()) {
			
			int atkChoice = SkillChoiseScene.skillChoose(battle.getAttacker(), scan);
			int defChoice = SkillChoiseScene.skillChoose(battle.getDefender(), scan);
			
			RoundResult result = battle.executeRound(atkChoice, defChoice);
			
			String[] fightResultMessage = FightMenuConfig.getAfterRoundStatus(battle.getAttacker(), battle.getDefender(), result
					//battle.getAttacker(), result.attackMessage, battle.getDefender(), result.defMessage, result.damage
			);
			
			Menu.menuChooseSkill(fightResultMessage);
			
			if (!battle.getDefender().getIsAlive()) {
				System.out.println("VIN! " + battle.getAttacker().getName() + " crushed the enemy!");
				break;
			}
			if (!battle.getAttacker().getIsAlive()) {
				System.out.println("VIN! " + battle.getDefender().getName() + " crushed the enemy!");
				break;
			}
			
			battle.changeRolls();
			
			 Pauses.waitForContinue(scan);
			 ClearConsole.clearConsole();
		}
	return true;
	}
}
