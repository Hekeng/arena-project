package arena.logic;

import arena.characters.Character;
import arena.core.system.LoadManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextMessageProvider {
	public static String[] buildHallOfFameMenu (){
		List<String> allLines = new ArrayList<>();
		allLines.add("========= Choose Your character ! ==========");
		allLines.add("============== HALL OF FAME ================");
		allLines.addAll(Arrays.asList(LoadManager.getSavedHeroesListDetailed()));
		allLines.add("============ 0.Back to previous menu =======");
		return allLines.toArray(new String[0]);
	}
	public static String[] buildHeroCard(Character fighter, int mode) {
		List<String> allLines = new ArrayList<>();
		
		switch (mode) {
			case 1:
				allLines.add("============== CLASS PREVIEW ===============");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getHistoryInfo()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getClassDescription()));
				break;
			
			case 2:
				allLines.add("======= Fighters meet in the arena =========");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getPersonalInfo()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getClassDescription()));
				allLines.add("============================================");
				allLines.addAll(Arrays.asList(fighter.getHistoryInfo()));
				break;
			
			case 3:
				allLines.add("=============== ARENA EVENT ================");
				allLines.add("=============  YOUR CHAMPION ===============");
				allLines.addAll(Arrays.asList(fighter.getPersonalInfo()));
				break;
		}
		
		return allLines.toArray(new String[0]);
	}
	
	public static String[] getAfterRoundStatus(Character atk, Character def, RoundResult result) {
		List<String> lines = new ArrayList<>();

		int totalDmgToAtk = result.dmgToAtk + result.atkPoisonDmg;
		int totalDmgToDef = result.dmgToDef + result.defPoisonDmg;
		
		String atkHpSign = (totalDmgToAtk > 0) ? "-" : "";
		String atkResSign = (result.atkResourceChange > 0) ? "+" : "";
		
		String defHpSign = (totalDmgToDef > 0) ? "-" : "";
		String defResSign = (result.defResourceChange > 0) ? "+" : "";
		
		lines.add("=============== ROUND RESULT ===============");

		lines.add(" " + atk.getName() + ": " + result.attackMessage + "\n"
				+ " " + def.getName() + ": " + result.defMessage
		);
		if (result.defTargetResChange < 0 || result.atkTargetResChange < 0) {
			
			lines.add("================= RESOURCES ================");
			if (result.defTargetResChange < 0) {
				lines.add(" " + atk.getName() + " burned " + result.defTargetResChange + " from " + def.getName());
			}
			if (result.atkTargetResChange < 0) {
				lines.add(" " + def.getName() + " burned " + result.atkTargetResChange + " from " + atk.getName());
			}
		}

		int blockedAtk = result.rawAttackerDmg - result.dmgToDef;
		lines.add(atk.getName() + " DEALT: " + result.rawAttackerDmg + (blockedAtk > 0 ? " [Blocked: " + blockedAtk + "]" : "") + " -> Final: " + result.dmgToDef + " DMG");

		if (result.defResponded) {
			int blockedDef = result.rawDefenderDmg - result.dmgToAtk;
			lines.add(def.getName() + " RETALIATED: " + result.rawDefenderDmg + (blockedDef > 0 ? " [Blocked: " + blockedDef + "]" : "") + " -> Final: " + result.dmgToAtk + " DMG");
		} else {
			lines.add(" " + def.getName() + " was defeated and could not act!");
		}

		if (result.atkPoisonDmg > 0 || result.defPoisonDmg > 0) {
			
			lines.add("================== POISON ==================");
			if (atk.getPoisonValue() > 0) {
				lines.add(" " + atk.getName() + " poison tick: -" + result.atkPoisonDmg + " HP (Current stack: " + atk.getPoisonValue() + ")");
			}
			if (def.getPoisonValue() > 0) {
				lines.add(" " + def.getName() + " poison tick: -" + result.defPoisonDmg + " HP (Current stack: " + def.getPoisonValue() + ")");
			}
		}

		lines.add("================== STATUS ==================");
		
		lines.add(" " + atk.getName() + " HP: " + atk.getHealth() + " (" + atkHpSign + totalDmgToAtk + ") | "
				+ atk.getResourceStatus() + " (" + atkResSign + result.atkResourceChange + ")");
		
		lines.add(" " + def.getName() + " HP: " + def.getHealth() + " (" + defHpSign + totalDmgToDef + ") | "
				+ def.getResourceStatus() + " (" + defResSign + result.defResourceChange + ")");
		
		return lines.toArray(new String[0]);
	}
	
}
