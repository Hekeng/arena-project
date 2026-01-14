package arena.characters;
import arena.config.FightClassesConfig;
import arena.helpers.UnRand;
import arena.logic.CombatIntent;
import arena.config.FightMenuConfig;

public class Mage extends Character{
	
	private int mana;
	public Mage(String name, int health, int mana) {

		super(name, health);
		// 2. Инициализация своего собственного поля
		this.mana = mana;
	}

	public int getMana(){
		return this.mana;
	}
	@Override
	public int getResourceValue(){
		 int resourceValue = getMana();
		return resourceValue;
	}
	@Override
	public String getResourceName(){
		return "Mana";
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
	public  int getMaxHealth(){
		return FightClassesConfig.BASE_MAGE_HP;
	}
	
	@Override
	public CombatIntent attack() {
		CombatIntent intent = new CombatIntent();
		if (this.mana >= FightClassesConfig.BASE_MAGE_SPELL_COST) {
			intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_MAGE_DMG, FightClassesConfig.BASE_MAX_MAGE_DMG);
			intent.selfResourceChange -= FightClassesConfig.BASE_MAGE_SPELL_COST;
			intent.message = "arson fireball and fiered in apponnent";
		} else {intent.damageValue = 0;
		intent.message = "The loser was left without Mana";}

		return intent;
	}
	
	
	@Override
	public CombatIntent defend() {
		CombatIntent intent = new CombatIntent();
		if (this.mana >= FightClassesConfig.BASE_MAGE_SPELL_COST) {
		intent.defenseMod = 0.1;
		intent.selfResourceChange -= FightClassesConfig.BASE_MAGE_SPELL_COST * 2;
		intent.message = "cast power schield";
		} else {
			intent.message = "The loser was left without Mana";
		}
		return intent;
	}
	
	@Override
	public CombatIntent special() {
		CombatIntent intent = new CombatIntent();
		intent.selfResourceChange += FightClassesConfig.BASE_MAGE_SPELL_COST * 3;
		intent.message = "concentrated energy and have mana";
		return intent;
	}
	
	@Override
	public String[] getMySkillMenu() {
		return FightMenuConfig.FIGHT_MENU_CHOOSE_MAGE_SKILL;
	}
	@Override
	public  String[] getClassDescription() {
		return new String[]{
				"ATTACK  : " + FightClassesConfig.BASE_MIN_MAGE_DMG + "-" + FightClassesConfig.BASE_MAX_MAGE_DMG + " DMG | " + FightClassesConfig.BASE_MAGE_SPELL_COST + " (" + getResourceName() + ")",
				"BLOCK   : 90% DMG Reduction | +" + FightClassesConfig.BASE_MAGE_SPELL_COST * 2 + " " + getResourceName(),
				"SPECIAL : Recharge Mana | Gives: " + FightClassesConfig.BASE_MAGE_SPELL_COST * 3 + " " + getResourceName(),
				"FAIL  : If " + getResourceName() + " < " + FightClassesConfig.BASE_MAGE_SPELL_COST + " -> Action skipped",
				"STAMINA : Averaged damage with high defense"
		};
	}


	@Override
	public String[] getHistoryInfo(){
		return new String[]{
				"A glass cannon capable of incinerating foes from afar.",
				"The Mage relies on Mana to perform any action, including ",
				"his incredibly powerful 'Power Shield' which negates ",
				"90% of damage. However, magic is taxing; his mana",
				" doesn't regenerate on its own. He must spend turns",
				" concentrating to restore his energy, making timing and",
				" resource management his key to victory."
		};
	}


}