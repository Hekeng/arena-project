package arena.core.scene;

import arena.characters.Character;

import java.util.ArrayList;

import java.util.Random;

import arena.helpers.UnRand;

import arena.logic.FightersDiceThrow;

public class FirstDiceThrowScene {

	//fightersThrowsDice
	public static void throwScene(ArrayList<Character> list, ArrayList<Character> order, Random rand){
		while(true){
			int[] throwsResult = new int[list.size()];
			throwsResult = FightersDiceThrow.twoFightersDiceThrow(list, rand);

			if (!FightersDiceThrow.compareThrows(list, order, throwsResult)) {
				continue;
			}



			break;
		}
	}


}
