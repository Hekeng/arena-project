package arena.core;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;

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


import arena.dialogs.BattleNarration;


public class FightLoop {
	public static void main(String[] args) {
		ArrayList<Character> characterList = new ArrayList<>();

		ArrayList<Character> fightOrder = new ArrayList<>();

		Random myRand = new Random();

		Scanner inputScanner = new Scanner(System.in);
		Mage mage = new Mage("MageHexen", 100, 40);
		Warrior warrior = new Warrior("WarriorHexen", 100, 0);
		characterList.add(mage);
		characterList.add(warrior);

		startFight(characterList, inputScanner, fightOrder

		);
		inputScanner.close();

	}
	public static boolean startFight(ArrayList<Character> list, Scanner scan, ArrayList<Character> order

	){
		while (true){

			Menu.menuChowFighters();
			System.out.println("\n");
			showFighters(list);

			Pauses.waitForContinue(scan);
			ClearConsole.clearConsole();
			Narration.Narration(BattleNarration.FIRST_SCENA_DIALOGE);

			Pauses.waitForContinue(scan);
			ClearConsole.clearConsole();
			
			BattleController battle = new BattleController();
			
			FirstDiceThrowScene.throwScene(list, battle
					//, rand
			);
			Pauses.waitForContinue(scan);
			ClearConsole.clearConsole();

			Character champion = BattleScene.battleScena(battle, scan);
			
			
			return true;
		}
	}

	public static void showFighters (ArrayList<Character> list){

		for (int i = 0; i < list.size(); i++) {
			Character fighter  =  list.get(i);
			UnSlowPrinter.oneLetterPrint(fighter.toString(), 10);
		}

	}
	

}
