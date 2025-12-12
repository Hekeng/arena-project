package arena;
import java.util.Scanner;

public class StartMenu {
	static void main(String[] args) {
		
		Scanner inputScanner = new Scanner(System.in);
		
		//menuStart(inputScanner);
		//menuChooseClass(inputScanner);
		//menuEnterName(inputScanner);
		
		inputScanner.close(); // ВАЖНО ПЕРЕНЕСТИ!!!!
	}
	
	public static void menuNav(Scanner Scan){
		while (true){
			int userInput = 1;
			
			
			if (userInput == 0){
				break;
			}
		}
	}
	
	public static int menuStart(Scanner Scan) {
		System.out.println("=======Hello! Welkom to game Arena!=========");
		System.out.println("====================MENU====================");
		System.out.println("============================================");
		System.out.println("=============1.Create Character=============");
		System.out.println("=============2.Start Fight==================");
		System.out.println("=============0.Exit=========================");
		System.out.println("============================================");
		System.out.println("============================================");
		
		int answer = unInputInt.ZhalenInput(Scan);
		
		System.out.println("Yours input was: " + answer);
		return answer;
	}
	
	public static int menuChooseClass(Scanner Scan){
		System.out.println("=======Choose Your character class!=========");
		System.out.println("====================MENU====================");
		System.out.println("============================================");
		System.out.println("=============1.Create Mage==================");
		System.out.println("=============2.Create Warrior===============");
		System.out.println("=============9.Back to main menu============");
		System.out.println("============================================");
		
		int answer = unInputInt.ZhalenInput(Scan);
		
		System.out.println("Yours input was: " + answer);
		return answer;

	}
	
	public static String menuEnterName(Scanner Scan){
		System.out.println("============================================");
		System.out.println("=======Choose Your character Name!==========");
		System.out.println("============================================");
		
		String answer = unInputStr.StringInput(Scan);
		System.out.println("Yours input was: " + answer);
		return answer;
	}
	
	
}
