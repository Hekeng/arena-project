package arena.logic;
import arena.characters.Character;


public class BattleController {
	
	private Character attacker;
	private Character defender;
	
	public void setRolls(Character first, Character second){
		this.attacker = first;
		this.defender = second;
	}
	
	public  void  setAttacker (Character attacker){
		this.attacker = attacker;
	}
	
	public void setDefender(Character defender){
		this.defender = defender;
	}
	
	public Character getAttacker(){ return attacker; }
	public Character getDefender(){ return defender; }
	
	public void executeRound(int atkChoice, int defChoice) {
		// 1. Получаем конверты от тех, кто сейчас в ролях
		CombatIntent intentAtk = this.attacker.executeAction(atkChoice);
		CombatIntent intentDef = this.defender.executeAction(defChoice);
		
		// 2. Магия урона
		int finalDamage = (int) (intentAtk.damageValue * intentDef.defenseMod);
		
		// 3. Применяем последствия
		this.defender.takeDamage(finalDamage);
		this.attacker.changeMyResource(intentAtk.selfResourceChange);
		this.defender.changeMyResource(intentDef.selfResourceChange);
		
		// 4. Логи
		System.out.println("\n--- РЕЗУЛЬТАТ ХОДА ---");
		System.out.println(attacker.getName() + ": " + intentAtk.message + " | УРОН: " + finalDamage);
		System.out.println(defender.getName() + ": " + intentDef.message);
		System.out.println("HP " + defender.getName() + ": " + defender.getHealth());
		System.out.println("----------------------\n");
	}
	
	public void changeRolls (){
		
		Character temp = getAttacker();
		setAttacker(getDefender());
		setDefender(temp);
		
		
	}
	
}