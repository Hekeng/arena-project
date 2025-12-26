package arena.characters;

import arena.config.FightClassesConfig;
import arena.config.FightMenuConfig;
import arena.helpers.UnRand;
import arena.logic.CombatIntent;

public class Warrior extends Character {
	private int rage; // Новое, уникальное поле

	// Конструктор Warrior
	public Warrior(String name, int health, int rage) {

		super(name, health);
		this.rage = rage;
		
	}
	
	@Override
	protected int resultDamage(int min, int max){
		int resultDamage = UnRand.randomNumber(min, max);
		return resultDamage;
	}
	
	@Override
	public void changeMyResource(int amount) {
		this.rage += amount;
		if (this.rage < 0) this.rage = 0;
		if (this.rage>100) this.rage = 100;

	}
	
	@Override
	public CombatIntent attack() {
		CombatIntent intent = new CombatIntent();
		intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_WARRIOR_DMG, FightClassesConfig.BASE_MAX_WARRIOR_DMG);
		intent.selfResourceChange += 5;
		intent.message = "tensed all muscles and hit";
		return intent;
			
		
	}
	
	@Override
	public CombatIntent defend() {
		CombatIntent intent = new CombatIntent();
		intent.defenseMod = 0.5; // пример
		intent.selfResourceChange +=10;
		intent.message = "took a defensive stance";
		return intent;
	}
	
	@Override
	public CombatIntent special() {
		CombatIntent intent = new CombatIntent();
		if (this.rage >= FightClassesConfig.BASE_WARRIOR_SKILL_COST) {
			intent.damageValue =  resultDamage(FightClassesConfig.BASE_MIN_WARRIOR_DMG, FightClassesConfig.BASE_MAX_WARRIOR_DMG) * 2;
			intent.message = "hit with the maximum force available to him";
			intent.selfResourceChange = FightClassesConfig.BASE_WARRIOR_SKILL_COST;
		} else {
			intent.selfResourceChange -= 10;
			intent.message = "overexerted so much that couldn't lift the sword.";
			
		}

		return intent;
	}
	@Override
	public String[] getMySkillMenu() {
		return FightMenuConfig.FIGHT_MENU_CHOOSE_WARRIOR_SKILL;
	}

}
