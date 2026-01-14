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
		//сжигаем ресурсы врага
		boolean defResponded;
		if (intentAtk.targetResourceChange != 0) {
			this.defender.changeMyResource(intentAtk.targetResourceChange);
		}

		if (intentDef.targetResourceChange != 0) {
			this.attacker.changeMyResource(intentDef.targetResourceChange);
		}
		// 3. Применяем последствия демедж
		this.defender.takeDamage(damageToDef);

		if (intentAtk.dotDamage > 0) {
			this.defender.addPoison(intentAtk.dotDamage);
		}
		if (intentDef.dotDamage > 0) {
			this.attacker.addPoison(intentDef.dotDamage);
		}
		
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
		if (this.defender.getIsAlive() && this.defender.getPoisonValue() > 0) {
			this.defender.takeDamage(this.defender.getPoisonValue());
		}
		if (this.attacker.getIsAlive() && this.attacker.getPoisonValue() > 0) {
			this.attacker.takeDamage(this.attacker.getPoisonValue());
		}
		int atkTargetResChange = intentAtk.targetResourceChange;
		int defTargetResChange = intentDef.targetResourceChange;

		int atkPoisonDmg = this.attacker.getPoisonValue();
		int defPoisonDmg = this.defender.getPoisonValue();
		
		return new RoundResult(intentAtk.message, rawAttackerDmg, damageToDef, atkResourceChange, atkPoisonDmg, atkTargetResChange, intentDef.message, rawDefenderDmg, damageToAtk, defResourceChange, defPoisonDmg, defTargetResChange, defResponded);
	}

		public void changeRolls() {
			
			Character temp = getAttacker();
			setAttacker(getDefender());
			setDefender(temp);
		}
		
}