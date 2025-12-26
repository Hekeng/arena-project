package arena.config;

public class FightMenuConfig {
	
	private FightMenuConfig(){}  // запрещаем создание объекта

	public static final int MIN_FIGHTERS_QUANTITY = 2;
	
	public static final String[] MENU_SHOW_FIGHTERS = {
			"=======Fighters meet in the arena==========="
	};

public static final String[] FIGHT_MENU_CHOOSE_MAGE_SKILL = {
		"=======Choose Your character skill!=========",
		"====================MENU====================",
		"=============1.Atack (dmg)==================",
		"=============2.Defend(reduce dmg)===========",
		"=============3.Special(+30mana)============="
};

public static final String[] FIGHT_MENU_CHOOSE_WARRIOR_SKILL = {
		"=======Choose Your character skill!=========",
		"====================MENU====================",
		"=============1.Atack (dmg)==================",
		"=============2.Defend(reduce dmg)===========",
		"=============3.Special(-15rage)============="
};

}