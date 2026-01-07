package arena.config;
import java.util.ArrayList;
import java.util.List;

import arena.characters.Character;

import arena.logic.BattleController;
import arena.logic.RoundResult;

import arena.config.FightClassesConfig;

public class FightMenuConfig {
	
	private FightMenuConfig(){}  // запрещаем создание объекта

	public static final int MIN_FIGHTERS_QUANTITY = 2;
	
	public static final String[] MENU_SHOW_FIGHTERS = {
			"====== Fighters meet in the arena =========="
	};

public static final String[] FIGHT_MENU_CHOOSE_MAGE_SKILL = {
		"====== Choose Your character skill! ========",
		"=================== MENU ===================",
		"============ 1.Atack (dmg) =================",
		"============ 2.Defend(reduce dmg) ==========",
		"============ 3.Special(+30mana) ============"
};

public static final String[] FIGHT_MENU_CHOOSE_WARRIOR_SKILL = {
		"====== Choose Your character skill! ========",
		"=================== MENU ===================",
		"============ 1.Atack (dmg) =================",
		"============ 2.Defend(reduce dmg) ==========",
		"============ 3.Special(-15rage) ============"
};
	
	public static String[] getAfterRoundStatus(Character atk, Character def, RoundResult result) {
		// Используем List как тип и ArrayList как реализацию
		List<String> lines = new ArrayList<>();
//		lines.add("=================== MENU ===================");
		lines.add("=============== ROUND RESULT ===============");
		
		// Секция Атакующего
		lines.add(" " + atk.getName() + ": " + result.attackMessage +"\n"
				+ " " + def.getName() + ": " + result.defMessage
		);
		//lines.add(" " + def.getName() + ": " + result.defMessage);

		int blockedAtk = result.rawAttackerDmg - result.dmgToDef;
		lines.add(atk.getName() + "  DEALT: " + result.rawAttackerDmg + " " + (blockedAtk > 0 ? " Blocked: " + blockedAtk + " " : " ") + " Result: " + result.dmgToDef + " DMG" );

		// Секция Защищающегося (с проверкой на выживание)
		if (result.defResponded) {

			int blockedDef = result.rawDefenderDmg - result.dmgToAtk;
			lines.add(def.getName() +"  RETALIATED: " + result.rawDefenderDmg  + (blockedDef > 0 ? " Blocked: " + blockedDef + " " : " ") + " Result: " + result.dmgToAtk + " DMG" );

		} else {
			lines.add(" " + def.getName() + " was defeated and could not act!");
		}

		lines.add("================== STATUS ==================");

// Логика для Атакующего
		String atkHpSign = (result.dmgToAtk > 0) ? "-" : ""; // Если есть урон, ставим минус, если нет - пусто
		String atkResSign = (result.atkResourceChange > 0) ? "+" : ""; // Если ресурс вырос, ставим плюс

// Логика для Защитника
		String defHpSign = (result.dmgToDef > 0) ? "-" : "";
		String defResSign = (result.defResourceChange > 0) ? "+" : "";

		lines.add(" " + atk.getName() + " HP: " + atk.getHealth() + "/100 (" + atkHpSign + result.dmgToAtk + ") | "
				+ atk.getResourceStatus() + "/100 (" + atkResSign + result.atkResourceChange + ")");

		lines.add(" " + def.getName() + " HP: " + def.getHealth() + "/100 (" + defHpSign + result.dmgToDef + ") | "
				+ def.getResourceStatus() + "/100 (" + defResSign + result.defResourceChange + ")");

//		lines.add(" " + atk.getName() + " HP: " + atk.getHealth() + "/100 (" + result.dmgToAtk + ") | " + atk.getResourceStatus()
//				+ "/100 (" + result.atkResourceChange + ")"
//		);
//		lines.add(" " + def.getName() + " HP: " + def.getHealth() + "/100 (" + result.dmgToDef + ") | " + def.getResourceStatus()
//				+ "/100 (" + result.defResourceChange + ")"
//		);



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

