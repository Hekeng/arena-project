package arena.core.scene;

import arena.characters.Character;
import arena.logic.BattleController;
import arena.logic.FightersDiceThrow;
import arena.ui.Menu;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstDiceThrowScene {
	
	public static void throwScene(ArrayList<Character> list, BattleController battle, Scanner scan) {
		
		boolean isSuccess = false;
		
		while (!isSuccess) {

			isSuccess = FightersDiceThrow.tryToSetRolls(list, battle);

			String[] report = FightersDiceThrow.prepareToSayRolls(battle);

			Menu.printStandardFrame(report);

			Pauses.waitForContinue(scan);
		}
	}
}