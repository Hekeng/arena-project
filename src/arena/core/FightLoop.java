package arena.core;
import java.util.ArrayList;
import java.util.Scanner;

import arena.characters.Character;

import arena.ui.Menu;

import arena.helpers.ClearConsole;

import arena.core.scene.Pauses;
import arena.core.scene.FirstDiceThrowScene;

import arena.logic.BattleController;

import arena.core.scene.BattleScene;
import arena.core.scene.PostBattleScene;

import arena.dialogs.BattleNarration;

public class FightLoop {

	public static void startFight(ArrayList<Character> list, Scanner scan){

			Menu.showFighters(list, scan);

			ClearConsole.clearConsole();

			Menu.printStandardFrame(BattleNarration.FIRST_SCENA_DIALOGUE);
			Pauses.waitForContinue(scan);
			ClearConsole.clearConsole();
			
			BattleController battle = new BattleController();
			
			FirstDiceThrowScene.throwScene(list, battle, scan);
			
			ClearConsole.clearConsole();

			BattleScene.battleScena(battle, scan);
			
			PostBattleScene.postBattle(list, scan);
			

	}
}
