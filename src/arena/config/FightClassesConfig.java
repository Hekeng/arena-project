package arena.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arena.characters.Character;

public class FightClassesConfig {
	private FightClassesConfig(){}

	public static final int BASE_CHARACTERS_HP = 100;

	//MAGE:
	public static final int CLASS_ID_MAGE = 1;
	public static final int BASE_MAGE_RES = 40;
	public static final int BASE_MIN_MAGE_DMG = 10;
	public static final int BASE_MAX_MAGE_DMG = 20;
	public static final int BASE_MAGE_SPELL_COST = 10;
	public static final int MIN_MAGE_SKILL_COST = 5;

	//WARRIOR:
	public static final int CLASS_ID_WARRIOR = 2;
	public static final int BASE_WARRIOR_RES = 0;
	public static final int BASE_MIN_WARRIOR_DMG = 10;
	public static final int BASE_MAX_WARRIOR_DMG = 20;
	public static final int BASE_WARRIOR_SKILL_COST = 15;
	public static final int MIN_WARRIOR_SKILL_COST = 5;


	public static String[] buildHeroCard(Character fighter, int mode) {
		List<String> allLines = new ArrayList<>();

		switch (mode) {
			case 1: // Выбор героя: История + Скиллы
				allLines.add("============== CLASS PREVIEW ===============");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getHistoryInfo()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getClassDescription()));
				break;

			case 2: // Перед боем: Личное + Скиллы + История
				allLines.add("======= Fighters meet in the arena =========");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getPersonalInfo()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getClassDescription()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getHistoryInfo()));
				break;

			case 3: // Только статы
				allLines.add("=============== ARENA EVENT ================");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getPersonalInfo()));
				break;
		}

		return allLines.toArray(new String[0]);
	}
	
}
