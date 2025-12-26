package arena.logic;

import arena.helpers.UnRand;

public class Dice {
	// Просто обертка для удобства
	public static int diceThrow() {
		return UnRand.randomNumber(1, 6);
	}
}
