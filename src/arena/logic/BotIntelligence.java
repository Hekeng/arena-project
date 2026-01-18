package arena.logic;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.characters.Assassin;
import arena.helpers.UnRand;

public class BotIntelligence {

	public static int botIntelligence(Character ai, Character enemy) {

		if (ai.getHealth() < 25 && UnRand.randomNumber(1, 100) <= 80) {
			return 2;
		}
		if (ai instanceof Mage) {
			return mageLogic(ai, enemy);
		} else if (ai instanceof Warrior) {
			return warriorLogic(ai, enemy);
		} else if (ai instanceof Assassin) {
			return assassinLogic(ai, enemy);
		}

		return 1;
	}

	private static int mageLogic(Character ai, Character enemy) {
		if (ai.getResourceValue() < 10) return 3;
		return 1;
	}

	private static int warriorLogic(Character ai, Character enemy) {

		if (ai.getResourceValue() >= 30) return 3;
		if (UnRand.randomNumber(1, 5) == 1) return 2;
		return 1;
	}

	private static int assassinLogic(Character ai, Character enemy) {
		if (enemy.getResourceValue() > 30 && ai.getResourceValue() >= 20) {
			return 3;
		}
		if (enemy.getHealth() < 20) return 1;
		return UnRand.randomNumber(1, 2);
	}
}