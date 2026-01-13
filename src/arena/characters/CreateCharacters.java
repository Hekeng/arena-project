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
				newCharacter = new Mage(charName, FightClassesConfig.BASE_MAGE_HP, FightClassesConfig.BASE_MAGE_RES);
				list.add(newCharacter);
				break;

			case 2:
				System.out.println("You created Warrior!" );
				newCharacter = new Warrior(charName, FightClassesConfig.BASE_WARRIOR_HP, FightClassesConfig.BASE_WARRIOR_RES);
				list.add(newCharacter);
				break;

			case 3:
				System.out.println("You created Assassin!" );
				newCharacter = new Assassin(charName, FightClassesConfig.BASE_ASSASSIN_HP, FightClassesConfig.BASE_ASSASSIN_RES);
				list.add(newCharacter);
				break;

			default:
				// Код для всех остальных случаев
				//System.out.println("Level is unknown");
				break; //здесь необязателен, так как это последний блок
		}

	}

	public static Character createPreviewHero (int classChoice){
		Character tempGhost;
		if (classChoice == FightClassesConfig.CLASS_ID_MAGE) {
			tempGhost = new Mage("Preview", FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_MAGE_RES);
		} else if (classChoice == FightClassesConfig.CLASS_ID_WARRIOR){
			tempGhost = new Warrior("Preview", FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_WARRIOR_RES);
		} else {
			tempGhost = new Assassin("Preview", FightClassesConfig.BASE_CHARACTERS_HP, FightClassesConfig.BASE_ASSASSIN_RES);
		}
		return tempGhost;
	}
}
