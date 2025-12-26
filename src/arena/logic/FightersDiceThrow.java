package arena.logic;

import java.util.ArrayList;
import java.util.Random;

import arena.characters.Character;

import arena.logic.FightersDiceThrow;
import arena.logic.BattleController;

import arena.logic.Dice;

import arena.helpers.UnRand;

public class FightersDiceThrow {
	//Random random = new Random();
	public static int[] twoFightersDiceThrow(ArrayList<Character> list
			//, Random rand
	){

		int[] throwsResult = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {

			int throwCube = Dice.diceThrow();

			throwsResult[i] = throwCube;
		}
	return throwsResult;
	}
	
	public static boolean setRolls (ArrayList<Character> list, BattleController battle, int [] arr){
		Character attacker, defender;
		if (arr[0] == arr[1]) {
			return false;
		} else if (arr[0] > arr[1]) {
			attacker = list.get(0);
			defender = list.get(1);
		} else {
			attacker = list.get(1);
			defender = list.get(0);
		}
		
		battle.setRolls(attacker, defender);
		return true;
		
	}
	
	public static void sayRolls(BattleController battle, int [] arr){
		if (arr[0] == arr[1]) {
			System.out.println("something incredible happened: the fighters rolled the same numbers! Roll again!!");
		} else {
			System.out.println("Fighter " + battle.getAttacker().getName() + " will attack first (" + Math.max(arr[0], arr[1]) + ")");
			System.out.println("Fighter " + battle.getDefender().getName() + " will defend (" + Math.min(arr[0], arr[1]) + ")");
		}
	}
	
	
	
//	public static boolean compareThrows(ArrayList<Character> list, BattleController battle, int [] arr){
//
//		Character fighter1 = list.get(0);
//		Character fighter2 = list.get(1);
//
//		if (arr[0] == arr[1]) {
//			System.out.println("something incredible happened: the fighters rolled the same numbers!");
//			return false;
//		}
//
//		Character attacker, defender;
//		if (arr[0] > arr[1]) {
//			attacker = fighter1;
//			defender = fighter2;
//		} else {
//			attacker = fighter2;
//			defender = fighter1;
//		}
//
//		System.out.println("Fighter " + attacker.getName() + " will attack first (" + Math.max(arr[0], arr[1]) + ")");
//		System.out.println("Fighter " + defender.getName() + " will defend (" + Math.min(arr[0], arr[1]) + ")");
//
//		battle.setRolls(attacker, defender);
//
//		return true;
//	}
	
}
