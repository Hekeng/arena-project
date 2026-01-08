package arena.logic;
import arena.characters.Character;

public class BattleController {
	
	private Character attacker;
	private Character defender;
	
	public void setRolls(Character first, Character second) {
		this.attacker = first;
		this.defender = second;
	}
	
	public void setAttacker(Character attacker) {
		this.attacker = attacker;
	}
	
	public void setDefender(Character defender) {
		this.defender = defender;
	}
	
	public Character getAttacker() {
		return attacker;
	}
	
	public Character getDefender() {
		return defender;
	}
	
	public RoundResult executeRound(int atkChoice, int defChoice) {
		// Получаем конверты от тех, кто сейчас в ролях
		CombatIntent intentAtk = this.attacker.executeAction(atkChoice);
		CombatIntent intentDef = this.defender.executeAction(defChoice);
		
		// урон
		int rawAttackerDmg = intentAtk.damageValue;
		int damageToDef = (int) (rawAttackerDmg * intentDef.defenseMod);
		int rawDefenderDmg = intentDef.damageValue;
		int damageToAtk = (int) (rawDefenderDmg * intentAtk.defenseMod);

		// ресурсы:

		int atkResourceChange = intentAtk.selfResourceChange;
		int defResourceChange = intentDef.selfResourceChange;
		
		boolean defResponded;
		// 3. Применяем последствия
		this.defender.takeDamage(damageToDef);
		
		if (defender.getIsAlive()) {
			this.attacker.changeMyResource(atkResourceChange);
			defResponded = true;
			this.attacker.takeDamage(damageToAtk);
			this.defender.changeMyResource(defResourceChange);
		} else {
			this.attacker.changeMyResource(atkResourceChange);
			defResponded = false;
			damageToAtk = 0;
			defResourceChange = 0;

		}
		
		
		return new RoundResult(intentAtk.message, rawAttackerDmg, damageToDef, atkResourceChange, intentDef.message, rawDefenderDmg, damageToAtk, defResourceChange, defResponded);
	}

		public void changeRolls() {
			
			Character temp = getAttacker();
			setAttacker(getDefender());
			setDefender(temp);
		}
		
}