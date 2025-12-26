package arena.core.scene;

import arena.characters.Character;

import java.util.ArrayList;

import java.util.Random;

import arena.helpers.UnRand;

import arena.logic.BattleController;
import arena.logic.FightersDiceThrow;

public class FirstDiceThrowScene {

	//fightersThrowsDice
	public static void throwScene(ArrayList<Character> list, BattleController battle
			//, Random rand
	){
		while(true){
			int[] throwsResult = new int[list.size()];
			throwsResult = FightersDiceThrow.twoFightersDiceThrow(list
					//, rand
			);
			
			if (!FightersDiceThrow.setRolls(list,  battle, throwsResult)) {
				continue;
			}
			FightersDiceThrow.sayRolls(battle, throwsResult);
			break;
		}
	}
	
}
