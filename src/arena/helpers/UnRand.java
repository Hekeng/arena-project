package arena.helpers;

import java.util.Random;

public class UnRand {

	private static final Random RANDOM = new Random();
	
	public static int randomNumber(int min, int max) {
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		return RANDOM.nextInt(max - min + 1) + min;
	}
}