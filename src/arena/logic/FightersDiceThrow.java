package arena.logic;

import arena.characters.Character;
import java.util.ArrayList;
import java.util.List;

public class FightersDiceThrow {

	private static int roll1;
	private static int roll2;

	public static boolean tryToSetRolls(ArrayList<Character> list, BattleController battle) {
		roll1 = Dice.diceThrow();
		roll2 = Dice.diceThrow();

		if (roll1 == roll2) {
			return false;
		}

		Character attacker, defender;
		if (roll1 > roll2) {
			attacker = list.get(0);
			defender = list.get(1);
		} else {
			attacker = list.get(1);
			defender = list.get(0);
		}
		
		battle.setRolls(attacker, defender);
		return true;
	}

	public static String[] prepareToSayRolls(BattleController battle) {
		List<String> roles = new ArrayList<>();
		roles.add("=============== ARENA EVENT ================");
		
		if (roll1 == roll2) {
			roles.add("====== something incredible happened: ======");
			roles.add("The fighters rolled the same numbers: " + roll1 + "!");
			roles.add("=========== ROLL AGAIN, HEROES!! ===========");
		} else {
			roles.add("========== The dice have spoken! ===========");
			roles.add("Fighter " + battle.getAttacker().getName() + " will attack first (" + Math.max(roll1, roll2) + ")");
			roles.add("Fighter " + battle.getDefender().getName() + " will defend (" + Math.min(roll1, roll2) + ")");
			roles.add("============================================");
		}
		
		return roles.toArray(new String[0]);
	}
}