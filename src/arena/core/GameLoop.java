package arena.core;
import java.util.Scanner;
import java.util.ArrayList;

import arena.characters.CharacterNotAliveException;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.helpers.UnSlowPrinter;
import arena.helpers.unInputStr;
import arena.helpers.unInputInt;
import arena.ui.Menu;
public class GameLoop {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		ArrayList<Character> characterList = new ArrayList<>();// ВАЖНО ПЕРЕНЕСТИ!!!!
		MenuNav(inputScanner, characterList);
		//Menu.menuStart();
		//Menu.menuChooseClass();
		//Menu.menuEnterName();


		inputScanner.close();// ВАЖНО ПЕРЕНЕСТИ!!!!
	}

	public static void MenuNav (Scanner Scan, ArrayList<Character> List){
		int userAnswer;
		String userName;

		while(true){

			Menu.menuStart();
			userAnswer = unInputInt.ZhalenInput(Scan);
			System.out.println("Yours input was: " + userAnswer);

			if (userAnswer == 0) {
				break;
			}

			if (userAnswer == 1) {
				Menu.menuChooseClass();
				userAnswer = unInputInt.ZhalenInput(Scan);
				System.out.println("Yours input was: " + userAnswer);
			}

			if (userAnswer == 1) {
				System.out.println("You created Mage!" );
				Menu.menuEnterName();
				userName = unInputStr.StringInput(Scan);
				Mage mage = new Mage(userName, 100, 40);
				List.add(mage);
				continue;
				} else if (userAnswer == 2) {
					System.out.println("You created Warrior!" );
					Menu.menuEnterName();
					userName = unInputStr.StringInput(Scan);
					Warrior warrior = new Warrior(userName, 100, 20);
					List.add(warrior);
					continue;
					} else if (userAnswer == 9) {
						System.out.println("Return to main menu" );
						Menu.menuEnterName();
						continue;
						} else {
							System.out.println("Input is inkorrect return to main menu" );
							//Menu.menuEnterName();
							continue;
			}
		}
	}

//	public static String CharacterCreation (){
//
//	}

}
