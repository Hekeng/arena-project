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
		// 1. Получаем конверты от тех, кто сейчас в ролях
		CombatIntent intentAtk = this.attacker.executeAction(atkChoice);
		CombatIntent intentDef = this.defender.executeAction(defChoice);
		
		// 2. Магия урона
		int rawAttackerDmg = intentAtk.damageValue;
		int damageToDef = (int) (rawAttackerDmg * intentDef.defenseMod);
		int rawDefenderDmg = intentDef.damageValue;
		int damageToAtk = (int) (rawDefenderDmg * intentAtk.defenseMod);
		
		boolean defResponded;
		// 3. Применяем последствия
		this.defender.takeDamage(damageToDef);
		
		if (defender.getIsAlive()) {
			this.attacker.changeMyResource(intentAtk.selfResourceChange);
			defResponded = true;
			this.attacker.takeDamage(damageToAtk);
			this.defender.changeMyResource(intentDef.selfResourceChange);
		} else {
			this.attacker.changeMyResource(intentAtk.selfResourceChange);
			defResponded = false;
		}
		
		
		return new RoundResult(intentAtk.message, rawAttackerDmg, damageToDef, intentDef.message, rawDefenderDmg, damageToAtk, defResponded);
	}

		public void changeRolls() {
			
			Character temp = getAttacker();
			setAttacker(getDefender());
			setDefender(temp);
		}
		
}