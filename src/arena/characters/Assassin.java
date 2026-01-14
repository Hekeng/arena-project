package arena.characters;

import arena.config.FightClassesConfig;
import arena.config.FightMenuConfig;
import arena.helpers.UnRand;
import arena.logic.CombatIntent;

public class Assassin extends Character{

		private int stamina;
		public Assassin(String name, int health, int stamina) {

			super(name, health);
			this.stamina = stamina;
		}

		public int getStamina(){
			return this.stamina;
		}
	@Override
	public  int getMaxHealth(){
		return FightClassesConfig.BASE_ASSASSIN_HP;
	}
		@Override
		public int getResourceValue(){
			int resourceValue = getStamina();
			return resourceValue;
		}
		@Override
		public String getResourceName(){
			return "Stamina";
		}


		@Override
		protected int resultDamage(int min, int max){
			int resultDamage = UnRand.randomNumber(min, max);
			return resultDamage;
		}

		@Override
		public void changeMyResource(int amount){

			this.stamina += amount;
			if (this.stamina < 0) this.stamina = 0;
			if (this.stamina>=100) this.stamina = 100;

		}

		@Override
		public CombatIntent attack() {
			CombatIntent intent = new CombatIntent();
			if (this.stamina >= FightClassesConfig.MIN_ASSASSIN_SKILL_COST) {
				intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_ASSASSIN_DMG, FightClassesConfig.BASE_MAX_ASSASSIN_DMG);
				intent.selfResourceChange -= FightClassesConfig.MIN_ASSASSIN_SKILL_COST;
				intent.dotDamage += FightClassesConfig.BASE_MIN_ASSASSIN_DMG/2;
				intent.message = "lightning-fast jabbed with a knife...";
			} else {intent.damageValue = 0;
				intent.selfResourceChange += FightClassesConfig.MIN_ASSASSIN_SKILL_COST;
				intent.message = "The loser was left without Stamina";}

			return intent;
		}


		@Override
		public CombatIntent defend() {
			CombatIntent intent = new CombatIntent();
			intent.defenseMod = 0.7;
			intent.selfResourceChange += FightClassesConfig.BASE_ASSASSIN_SKILL_COST;
			intent.damageValue = resultDamage(FightClassesConfig.BASE_MIN_ASSASSIN_DMG, FightClassesConfig.BASE_MAX_ASSASSIN_DMG) / 2;
			intent.dotDamage += FightClassesConfig.BASE_MIN_ASSASSIN_DMG;
			intent.message = "picked up some fragments of an old shield nearby";
			return intent;
		}

		@Override
		public CombatIntent special() {
			CombatIntent intent = new CombatIntent();
			intent.defenseMod = 0.2;
			intent.selfResourceChange -= FightClassesConfig.BASE_ASSASSIN_SKILL_COST;
			intent.message = "used a smoke bomb";
			intent.targetResourceChange -= FightClassesConfig.MIN_ASSASSIN_SKILL_COST * 2;
			intent.dotDamage += FightClassesConfig.BASE_MIN_ASSASSIN_DMG/2;
			return intent;
		}

		@Override
		public String[] getMySkillMenu() {
			return FightMenuConfig.FIGHT_MENU_CHOOSE_ASSASSIN_SKILL;
		}
		@Override
		public String[] getClassDescription() {
			return new String[]{
					"ATTACK  : " + FightClassesConfig.MIN_ASSASSIN_SKILL_COST + "-" + FightClassesConfig.BASE_MAX_ASSASSIN_DMG + " DMG | Cost: " + FightClassesConfig.BASE_ASSASSIN_SKILL_COST + " " + getResourceName(),
					"PASSIVE : Every attack injects POISON (+" + FightClassesConfig.BASE_MIN_ASSASSIN_DMG/2 + " DOT per stack)",
					"PARRY   : 70% DMG Reduction | Counter-attack: 50% DMG + POISON",
					"SPECIAL : Smoke Bomb | 80% Dodge | Drain target " + (FightClassesConfig.BASE_ASSASSIN_SKILL_COST * 2) + " Res",
					"STYLE   : Low direct damage, but deadly over time (DOT focus)"
			};
		}


		@Override
		public String[] getHistoryInfo(){
			return new String[]{
			"A shadow that dances on the edge of a blade. ",
			"The Assassin doesn't believe in fair fights; ",
			"he relies on debilitating toxins and lethal ",
			"precision. Every strike carries a deadly ",
			"infection that slowly drains the life from ",
			"his prey. While he lacks the brute strength of",
			" a Warrior or the raw power of a Mage, his",
			" ability to evade attacks and strike from the",
			" mist makes him a nightmare for any opponent."
			};
		}


	}

