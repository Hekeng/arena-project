package arena.core;
import java.util.Scanner;

import arena.helpers.UnSlowPrinter;
import arena.helpers.unInputStr;
import arena.helpers.unInputInt;
import arena.ui.Menu;
public class GameLoop {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		MenuNav(inputScanner);
		//Menu.menuStart();
		//Menu.menuChooseClass();
		//Menu.menuEnterName();


		inputScanner.close();// ВАЖНО ПЕРЕНЕСТИ!!!!
	}

	public static void MenuNav (Scanner Scan){
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
				userName = unInputStr.StringInput(Scan);
				continue;
			} else if (userAnswer == 2) {
				System.out.println("You created Warrior!" );
				Menu.menuEnterName();
				userName = unInputStr.StringInput(Scan);
				continue;
			} else if (userAnswer == 9) {
				System.out.println("Return to main menu" );
				Menu.menuEnterName();
				continue;
			} else {
				System.out.println("Input is inkorrect return to main menu" );
				continue;
			}
		}
	}

//	public static String CharacterCreation (){
//
//	}

}
