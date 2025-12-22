package arena.logic;

import java.util.ArrayList;
import java.util.Random;

import arena.characters.Character;

import arena.logic.Dice;



import arena.helpers.UnRand;

public class FightersDiceThrow {
	//Random random = new Random();
	public static int[] twoFightersDiceThrow(ArrayList<Character> list, Random rand){

		int[] throwsResult = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {

			int throwCube = Dice.DiceThrow(rand);

			throwsResult[i] = throwCube;
		}
	return throwsResult;
	}

	public static boolean compareThrows(ArrayList<Character> list, ArrayList<Character> order, int [] arr){

		Character fighter1 = list.get(0);
		Character fighter2 = list.get(1);


		if (arr[0] == arr[1]) {
			System.out.println("something incredible happened: the fighters rolled the same numbers!");
			return false;
		}

		Character first, second;
		if (arr[0] > arr[1]) {
			first = fighter1;
			second = fighter2;
		} else {
			first = fighter2;
			second = fighter1;
		}

		System.out.println("Fighter " + first.getName() + " will attack first (" + Math.max(arr[0], arr[1]) + ")");
		System.out.println("Fighter " + second.getName() + " will defend (" + Math.min(arr[0], arr[1]) + ")");

		order.clear();
		order.add(first);
		order.add(second);

		return true;
	}




}
