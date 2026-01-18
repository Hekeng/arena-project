package arena.config;

public class FightClassesConfig {
	private FightClassesConfig(){}

	public static final int BASE_CHARACTERS_HP = 100;

	//MAGE:
	public static final int BASE_MAGE_HP = 80;
	public static final int CLASS_ID_MAGE = 1;
	public static final int BASE_MAGE_RES = 40;
	public static final int BASE_MIN_MAGE_DMG = 22;
	public static final int BASE_MAX_MAGE_DMG = 35;
	public static final int BASE_MAGE_SPELL_COST = 10;

	//WARRIOR:
	public static final int BASE_WARRIOR_HP = 100;
	public static final int CLASS_ID_WARRIOR = 2;
	public static final int BASE_WARRIOR_RES = 0;
	public static final int BASE_MIN_WARRIOR_DMG = 12;
	public static final int BASE_MAX_WARRIOR_DMG = 22;
	public static final int BASE_WARRIOR_SKILL_COST = 15;
	public static final int MIN_WARRIOR_SKILL_COST = 5;

	//ASSASSIN:
	public static final int BASE_ASSASSIN_HP = 90;
	public static final int CLASS_ID_ASSASSIN = 3;
	public static final int BASE_ASSASSIN_RES = 15;
	public static final int BASE_MIN_ASSASSIN_DMG = 8;
	public static final int BASE_MAX_ASSASSIN_DMG = 18;
	public static final int BASE_ASSASSIN_SKILL_COST = 15;
	public static final int MIN_ASSASSIN_SKILL_COST = 5;
	
	
}
