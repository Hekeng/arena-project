package arena.logic;

import arena.helpers.UnRand;

public class Dice {
	public static int diceThrow() {
		return UnRand.randomNumber(1, 6);
	}
}
