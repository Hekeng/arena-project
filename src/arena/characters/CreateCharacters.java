package arena.characters;

import java.util.ArrayList;

import arena.characters.Character;

public class CreateCharacters {
	public static Character CreateCharacter(int userChose, String charName, ArrayList list ){
		
		Character newCharacter = null;;

		switch (userChose) {
			case 1:
				System.out.println("You created Mage!" );
				newCharacter = new Mage(charName, 100, 40);
				list.add(newCharacter);
				break; // !!! Важно: останавливает выполнение switch

			case 2:
			case 3:
				// Код, если level == 2 ИЛИ level == 3
				System.out.println("Level 2-3: Intermediate");
				break;

			default:
				// Код для всех остальных случаев
				System.out.println("Level is unknown");
				// break здесь необязателен, так как это последний блок
		}

		return newCharacter;
	}
}
