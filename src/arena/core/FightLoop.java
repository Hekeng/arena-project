package arena.core;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.dialogs.BattleNarration;
import arena.ui.Menu;
import arena.helpers.UnSlowPrinter;
import arena.helpers.clearConsole;



public class FightLoop {
	public static void main(String[] args) {
		ArrayList<Character> characterList = new ArrayList<>();
		Random random = new Random();
		Mage mage = new Mage("MageHexen", 100, 40);
		Warrior warrior = new Warrior("WarriorHexen", 100, 20);
		characterList.add(mage);
		characterList.add(warrior);

		startFight(characterList);

	}
	public static boolean startFight(ArrayList<Character> list){
		while (true){
			Menu.menuChowFighters();
			showFighters(list);
			return true;
		}
	}

	public static void showFighters (ArrayList<Character> list){
		
		for (int i = 0; i < list.size(); i++) {
			Character fighter  =  list.get(i);
			UnSlowPrinter.oneLetterPrint(fighter.toString(), 10);
		}
		
	}
	
	public static void diceThrow (String phrase){
	
	}

}
