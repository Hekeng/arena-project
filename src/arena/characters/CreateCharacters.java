package arena.characters;

import java.util.ArrayList;

import arena.characters.Character;
import arena.config.FightClassesConfig;


public class CreateCharacters {
	public static void CreateCharacter(int userChose, String charName, ArrayList<Character> list){
		
		Character newCharacter = null;;

		switch (userChose) {
			case 1:
				System.out.println("You created Mage!" );
				newCharacter = new Mage(charName, FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_MAGE_RES);
				list.add(newCharacter);
				break;

			case 2:
				System.out.println("You created Warrior!" );
				newCharacter = new Warrior(charName, FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_WARRIOR_RES);
				list.add(newCharacter);
				break;

			case 3:
				// Код, если level == 2 ИЛИ level == 3
				//System.out.println("Level 2-3: Intermediate");
				break;

			default:
				// Код для всех остальных случаев
				//System.out.println("Level is unknown");
				break; //здесь необязателен, так как это последний блок
		}

	}
}
