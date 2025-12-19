package arena.helpers;

import java.util.Random;

public class UnRand {
	public static int randomNumber(int min, int max, Random neuRandom) {
		int x;
		
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		
		x = neuRandom.nextInt(max - min + 1) + min;
		
		return x;
	}
}
