package arena.logic;
import java.util.Random;



import arena.helpers.UnRand;
public class Dice {
	Random rand = new Random();
	public static int DiceThrow(Random rand){
		int throwCube;
		return throwCube = UnRand.randomNumber(1,6, rand);
	}


}
