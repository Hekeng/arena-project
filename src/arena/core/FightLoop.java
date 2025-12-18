package arena.core;
import java.util.Scanner;
import java.util.ArrayList;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.ui.Menu;

import java.util.ArrayList;

public class FightLoop {
	public static void main(String[] args) {
		ArrayList<Character> characterList = new ArrayList<>();
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
		Character fighter1 = list.get(0);
		Character fighter2 = list.get(1);
		System.out.println("fighter1: " + fighter1);
		System.out.println("fighter2: " + fighter2);

	}

}
