package arena.characters;

import java.io.Serializable;

import arena.config.FightClassesConfig;
import arena.config.FightMenuConfig;
import arena.helpers.UnRand;
import arena.logic.CombatIntent;


public class Warrior extends Character implements Serializable {
	private int rage;

	public Warrior(String name, int health, int rage) {

		super(name, health);
		this.rage = rage;
		
	}
	@Override
	public  int getMaxHealth(){
		return FightClassesConfig.BASE_WARRIOR_HP;
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

	public int getRage(){
		return this.rage;
	}
	@Override
	public int getResourceValue(){
		int resourceValue = getRage();
		return resourceValue;
	}
	@Override
	public String getResourceName(){
		return "Rage";
	}

	@Override
	public CombatIntent attack() {
		CombatIntent intent = new CombatIntent();
		intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_WARRIOR_DMG, FightClassesConfig.BASE_MAX_WARRIOR_DMG);
		intent.selfResourceChange += FightClassesConfig.MIN_WARRIOR_SKILL_COST;
		intent.message = "tensed all muscles and hit";
		return intent;
			
		
	}
	
	@Override
	public CombatIntent defend() {
		CombatIntent intent = new CombatIntent();
		intent.defenseMod = 0.5;
		intent.selfResourceChange += FightClassesConfig.MIN_WARRIOR_SKILL_COST * 2;
		intent.message = "took a defensive stance";
		return intent;
	}
	
	@Override
	public CombatIntent special() {
		CombatIntent intent = new CombatIntent();
		if (this.rage >= FightClassesConfig.BASE_WARRIOR_SKILL_COST) {
			intent.damageValue =  resultDamage(FightClassesConfig.BASE_MIN_WARRIOR_DMG, FightClassesConfig.BASE_MAX_WARRIOR_DMG) * 2;
			intent.message = "hit with the maximum force available to him";
			intent.selfResourceChange -= FightClassesConfig.BASE_WARRIOR_SKILL_COST;
		} else {
			intent.selfResourceChange -= FightClassesConfig.MIN_WARRIOR_SKILL_COST * 2;;
			intent.message = "overexerted so much that couldn't lift the sword.";
			
		}

		return intent;
	}
	@Override
	public String[] getMySkillMenu() {
		return FightMenuConfig.FIGHT_MENU_CHOOSE_WARRIOR_SKILL;
	}

	@Override
	public  String[] getClassDescription(){
		return new String[] {
				"ATTACK  : " + FightClassesConfig.BASE_MIN_WARRIOR_DMG + "-" + FightClassesConfig.BASE_MAX_WARRIOR_DMG + " DMG | " + FightClassesConfig.MIN_WARRIOR_SKILL_COST + " (" + getResourceName() + ")",
				"BLOCK   : 50% DMG Reduction | +" + FightClassesConfig.MIN_WARRIOR_SKILL_COST * 2 + " " + getResourceName(),
				"SPECIAL : Double Damage | Cost: " + FightClassesConfig.BASE_WARRIOR_SKILL_COST + " "+ getResourceName(),
				"FAIL  : If " + getResourceName() + " < " + FightClassesConfig.BASE_WARRIOR_SKILL_COST + " -> -"+ FightClassesConfig.MIN_WARRIOR_SKILL_COST * 2 +" " + getResourceName() + " penalty",
				"STAMINA : Consistent damage with high physical defense"
		};
	}
	@Override
	public String[] getHistoryInfo(){
		return new String[]{
				"A master of close combat who thrives in the heat ",
				"of battle. The Warrior uses Rage as his primary fuel.",
				" Every standard attack or defensive move builds up ",
				"his adrenaline. Once he has enough, he can unleash ",
				"a devastating blow that deals double damage. He is ",
				"a tanky fighter who rewards aggressive play and steady momentum."
		};
	}
}
