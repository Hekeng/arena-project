package arena.logic;

import arena.core.system.SaveManager;
import arena.logic.Validation;

import arena.characters.Character;

public class AfterBattleHeal {

	public static int healthHeal(Character character){
		character.setPoisonValue(0);
		character.setisAi(false);

		int healAmount = (int)((character.getMaxHealth() - character.getHealth()) * 0.5);
			character.setHealth(character.getHealth() + healAmount);


		if (Validation.isNameOnDisk(character.getName()) && !character.getIsAlive()){
			character.setHealth(character.getMaxHealth());
			character.setIsAlive(true);
			SaveManager.saveCharacter(character);
		}
		return healAmount;
	}
}
