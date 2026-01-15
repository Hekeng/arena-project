package arena;

import java.util.Scanner;
import java.util.ArrayList;

import arena.characters.Character;

import arena.core.GameLoop;


public class Game {
	public static void main(String[] args) {
		System.out.println("TEST RUN V0.3");
		Scanner inputScanner = new Scanner(System.in);
		ArrayList<Character> characterList = new ArrayList<>();
		GameLoop.start(inputScanner, characterList);
		inputScanner.close();

	}
}