package arena.characters;
import arena.config.FightClassesConfig;
import arena.helpers.UnRand;
import arena.logic.CombatIntent;
import arena.config.FightMenuConfig;

import java.util.Random;

public class Mage extends Character{
	
	private int mana; // Новое, уникальное поле

	// Конструктор Mage
	public Mage(String name, int health, int mana) {

		super(name, health);
		// 2. Инициализация своего собственного поля
		this.mana = mana;
	}

	public int getMana(){
		return this.mana;
	}
	
	@Override
	protected int resultDamage(int min, int max){
		int resultDamage1 = UnRand.randomNumber(min, max);
		int resultDamage2 = UnRand.randomNumber(min, max);
		int resultDamage = (resultDamage1 + resultDamage2) /2;
		
		return resultDamage;
	}
	
	@Override
	public void changeMyResource(int amount){
		
		this.mana += amount;
		if (this.mana < 0) this.mana = 0;
		if (this.mana>=100) this.mana = 100;
		
	}
	
	@Override
	public CombatIntent attack() {
		CombatIntent intent = new CombatIntent();
		if (this.mana >= FightClassesConfig.BASE_MAGE_SPELL_COST) {
			intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_MAGE_DMG, FightClassesConfig.BASE_MAX_MAGE_DMG);
			intent.selfResourceChange -= FightClassesConfig.BASE_MAGE_SPELL_COST;
			intent.message = "arson fireball and fiered in apponnent";
		} else {intent.damageValue = 0;
		intent.message = "Loschara ostalsia bez mani";}

		return intent;
	}
	
	
	@Override
	public CombatIntent defend() {
		CombatIntent intent = new CombatIntent();
		if (this.mana >= FightClassesConfig.BASE_MAGE_SPELL_COST) {
		intent.defenseMod = 0.1;
		intent.selfResourceChange -= FightClassesConfig.BASE_MAGE_SPELL_COST;
		intent.message = "cast power schield";
		} else {
			intent.message = "Loschara ostalsia bez mani";
		}
		return intent;
	}
	
	@Override
	public CombatIntent special() {
		CombatIntent intent = new CombatIntent();
		intent.selfResourceChange += 30;
		
		System.out.println(getName() + " consetrated energy and have mana");
		return intent;
	}
	
	@Override
	public String[] getMySkillMenu() {
		return FightMenuConfig.FIGHT_MENU_CHOOSE_MAGE_SKILL;
	}
	

}