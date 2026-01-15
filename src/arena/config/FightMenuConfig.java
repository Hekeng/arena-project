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
		"============ 1.Attack (dmg) ================",
		"============ 2.Defend(reduce dmg) ==========",
		"============ 3.Special(+30mana) ============"
};

public static final String[] FIGHT_MENU_CHOOSE_WARRIOR_SKILL = {
		"====== Choose Your character skill! ========",
		"=================== MENU ===================",
		"============ 1.Attack (dmg) ================",
		"============ 2.Defend(reduce dmg) ==========",
		"============ 3.Special(-15rage) ============"
};

	public static final String[] FIGHT_MENU_CHOOSE_ASSASSIN_SKILL = {
			"====== Choose Your character skill! ========",
			"=================== MENU ===================",
			"============ 1.Attack (dmg) ================",
			"============ 2.Defend(reduce dmg) ==========",
			"============ 3.Special ====================="
	};
	
//	public static String[] getAfterRoundStatus(Character atk, Character def, RoundResult result) {
//		// Используем List как тип и ArrayList как реализацию
//		List<String> lines = new ArrayList<>();
////		lines.add("=================== MENU ===================");
//		lines.add("=============== ROUND RESULT ===============");
//
//		// Секция Атакующего
//		lines.add(" " + atk.getName() + ": " + result.attackMessage);
//		lines.add(" " + def.getName() + ": " + result.defMessage);
//
//
//		int blockedAtk = result.rawAttackerDmg - result.dmgToDef;
//		lines.add(atk.getName() + "  DEALT: " + result.rawAttackerDmg + " " + (blockedAtk > 0 ? " Blocked: " + blockedAtk + " " : " ") + " Result: " + result.dmgToDef + " DMG" );
//
//		// Секция Защищающегося (с проверкой на выживание)
//		if (result.defResponded) {
//
//			int blockedDef = result.rawDefenderDmg - result.dmgToAtk;
//			lines.add(def.getName() +"  RETALIATED: " + result.rawDefenderDmg  + (blockedDef > 0 ? " Blocked: " + blockedDef + " " : " ") + " Result: " + result.dmgToAtk + " DMG" );
//
//		} else {
//			lines.add(" " + def.getName() + " was defeated and could not act!");
//		}
//// --- НОВАЯ СЕКЦИЯ: ЯД ---
//		if (atk.getPoisonValue() > 0 || def.getPoisonValue() > 0) {
//			lines.add("------------------ POISON ------------------");
//			if (atk.getPoisonValue() > 0) {
//				lines.add(" " + atk.getName() + " is poisoned! (Current: " + atk.getPoisonValue() + " DMG/round)");
//			}
//			if (def.getPoisonValue() > 0) {
//				lines.add(" " + def.getName() + " is poisoned! (Current: " + def.getPoisonValue() + " DMG/round)");
//			}
//		}
//		lines.add("================== STATUS ==================");
//
//// Логика для Атакующего
//		String atkHpSign = (result.dmgToAtk > 0) ? "-" : ""; // Если есть урон, ставим минус, если нет - пусто
//		String atkResSign = (result.atkResourceChange > 0) ? "+" : ""; // Если ресурс вырос, ставим плюс
//
//// Логика для Защитника
//		String defHpSign = (result.dmgToDef > 0) ? "-" : "";
//		String defResSign = (result.defResourceChange > 0) ? "+" : "";
//
//		lines.add(" " + atk.getName() + " HP: " + atk.getHealth() + "/100 (" + atkHpSign + result.dmgToAtk + ") | "
//				+ atk.getResourceStatus() + "/100 (" + atkResSign + result.atkResourceChange + ")");
//
//		lines.add(" " + def.getName() + " HP: " + def.getHealth() + "/100 (" + defHpSign + result.dmgToDef + ") | "
//				+ def.getResourceStatus() + "/100 (" + defResSign + result.defResourceChange + ")");
//
//		// ВАЖНО: Конвертируем List обратно в массив String[]
//		return lines.toArray(new String[0]);
//	}
public static String[] getAfterRoundStatus(Character atk, Character def, RoundResult result) {
	List<String> lines = new ArrayList<>();

	// --- 1. Сначала считаем все знаки и суммы, чтобы использовать их ниже ---
	int totalDmgToAtk = result.dmgToAtk + result.atkPoisonDmg;
	int totalDmgToDef = result.dmgToDef + result.defPoisonDmg;

	String atkHpSign = (totalDmgToAtk > 0) ? "-" : "";
	String atkResSign = (result.atkResourceChange > 0) ? "+" : "";

	String defHpSign = (totalDmgToDef > 0) ? "-" : "";
	String defResSign = (result.defResourceChange > 0) ? "+" : "";

	// --- 2. Формируем визуальные блоки ---

	lines.add("=============== ROUND RESULT ===============");

	// Секция сообщений (твоя связка через \n)
	lines.add(" " + atk.getName() + ": " + result.attackMessage + "\n"
			+ " " + def.getName() + ": " + result.defMessage
	);

	// Секция сжигания ресурсов (если оно было)
	if (result.defTargetResChange < 0 || result.atkTargetResChange < 0) {

		lines.add("================= RESOURCES ================");
		if (result.defTargetResChange < 0) {
			lines.add(" " + atk.getName() + " burned " + result.defTargetResChange + " from " + def.getName());
		}
		if (result.atkTargetResChange < 0) {
			lines.add(" " + def.getName() + " burned " + result.atkTargetResChange + " from " + atk.getName());
		}
	}

	// Секция урона Атакующего
	int blockedAtk = result.rawAttackerDmg - result.dmgToDef;
	lines.add(atk.getName() + " DEALT: " + result.rawAttackerDmg + (blockedAtk > 0 ? " [Blocked: " + blockedAtk + "]" : "") + " -> Final: " + result.dmgToDef + " DMG");

	// Секция урона Защищающегося
	if (result.defResponded) {
		int blockedDef = result.rawDefenderDmg - result.dmgToAtk;
		lines.add(def.getName() + " RETALIATED: " + result.rawDefenderDmg + (blockedDef > 0 ? " [Blocked: " + blockedDef + "]" : "") + " -> Final: " + result.dmgToAtk + " DMG");
	} else {
		lines.add(" " + def.getName() + " was defeated and could not act!");
	}

	// Секция Яда
	if (result.atkPoisonDmg > 0 || result.defPoisonDmg > 0) {

		lines.add("================== POISON ==================");
		if (atk.getPoisonValue() > 0) {
			lines.add(" " + atk.getName() + " poison tick: -" + result.atkPoisonDmg + " HP (Current stack: " + atk.getPoisonValue() + ")");
		}
		if (def.getPoisonValue() > 0) {
			lines.add(" " + def.getName() + " poison tick: -" + result.defPoisonDmg + " HP (Current stack: " + def.getPoisonValue() + ")");
		}
	}

	// Финальный статус
	lines.add("================== STATUS ==================");

	lines.add(" " + atk.getName() + " HP: " + atk.getHealth() + " (" + atkHpSign + totalDmgToAtk + ") | "
			+ atk.getResourceStatus() + " (" + atkResSign + result.atkResourceChange + ")");

	lines.add(" " + def.getName() + " HP: " + def.getHealth() + " (" + defHpSign + totalDmgToDef + ") | "
			+ def.getResourceStatus() + " (" + defResSign + result.defResourceChange + ")");

	return lines.toArray(new String[0]);
}


}

