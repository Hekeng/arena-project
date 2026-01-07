package arena.config;
import java.util.ArrayList;
import java.util.List;

import arena.characters.Character;
import arena.logic.BattleController;
import arena.logic.RoundResult;

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
	
	public static String[] getAfterRoundStatus(Character atk, Character def, RoundResult result) {
		// Используем List как тип и ArrayList как реализацию
		List<String> lines = new ArrayList<>();
		
		lines.add("================= ROUND RESULT =================");
		
		// Секция Атакующего
		lines.add(" " + atk.getName() + ": " + result.attackMessage);
		int blockedAtk = result.rawAttackerDmg - result.dmgToDef;
		lines.add("  DEALT: " + result.dmgToDef + " DMG" + (blockedAtk > 0 ? " (Blocked: " + blockedAtk + ")" : ""));
		
		lines.add("------------------------------------------------");
		
		// Секция Защищающегося (с проверкой на выживание)
		if (result.defResponded) {
			lines.add(" " + def.getName() + ": " + result.defMessage);
			int blockedDef = result.rawDefenderDmg - result.dmgToAtk;
			lines.add("  RETALIATED: " + result.dmgToAtk + " DMG" + (blockedDef > 0 ? " (Blocked: " + blockedDef + ")" : ""));
		} else {
			lines.add(" " + def.getName() + " was defeated and could not act!");
		}
		
		lines.add("==================== STATUS ====================");
		// Для этого метода нужно добавить getResourceStatus() в класс Character
		lines.add(" " + atk.getName() + " HP: " + atk.getHealth());
		lines.add(" " + def.getName() + " HP: " + def.getHealth());
		lines.add("================================================");
		
		// ВАЖНО: Конвертируем List обратно в массив String[]
		return lines.toArray(new String[0]);
	}

//	public static String[] getAfterRoundStatus(Character attacker, Character defender, RoundResult result
//			//Character atk,  String attackMsg, Character def,  String defMsg, int dmg
//	) {
//		return new String[] {
//				"================ROUND RESULT:===============",
//				" " + atk.getName() + ": " + attackMsg + " | DMG: " + dmg,
//				" " + def.getName() + ": " + defMsg + " | HP: " + def.getHealth()
//		};
//	}




}

