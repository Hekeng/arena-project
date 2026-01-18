package arena.characters;

import arena.config.FightClassesConfig;

public class CreateCharacters {
	
	public static Character CreateCharacter(Integer userChose, String charName) {
		if (userChose == null) return null;
		
		Character newCharacter = instantiateHero(userChose, charName);
		
		if (newCharacter != null) {
			System.out.println("You created " + newCharacter.getClass().getSimpleName() + "!");
		}
		return newCharacter;
	}
	
	public static Character createPreviewHero(Integer classChoice) {
		return instantiateHero(classChoice, "Preview");
	}
	
	private static Character instantiateHero(Integer classChoice, String name) {
		if (classChoice == null) return null;
		
		return switch (classChoice) {
			case 1 -> new Mage(name, FightClassesConfig.BASE_MAGE_HP, FightClassesConfig.BASE_MAGE_RES);
			case 2 -> new Warrior(name, FightClassesConfig.BASE_WARRIOR_HP, FightClassesConfig.BASE_WARRIOR_RES);
			case 3 -> new Assassin(name, FightClassesConfig.BASE_ASSASSIN_HP, FightClassesConfig.BASE_ASSASSIN_RES);
			default -> {
				System.out.println("Something went wrong with class ID: " + classChoice);
				yield null;
			}
		};
	}
	
}
