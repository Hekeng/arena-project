package arena.characters;
import java.io.Serializable;
import arena.logic.CombatIntent;

public abstract class Character implements Serializable {
	boolean isAi = false;
	private String name;
	private int health;
	private boolean isAlive = true;
	private int winCount = 0;
	public Character(String name, int health) {
		this.name = name;
		this.health = health;
	}
	public String[] getPersonalInfo() {
		return new String[]{
				"Name: " + getName(), "Wins: " + getWinCount(),
				getHealth() + " HP | " + getResourceStatus()

		};
	}
	public String getName(){
		return this.name;
	}

	public int getHealth(){
		return this.health;
	}

	public boolean getIsAlive(){
		return this.isAlive;
	}

	public int getWinCount(){ return this.winCount; }
	public void setHealth(int health) {
		this.health = health;
	}
	private int accumulatedPoison = 0;
	public void addPoison(int amount) {
		this.accumulatedPoison += amount;
	}
	public int getPoisonValue() {
		return this.accumulatedPoison;
	}
	public void setPoisonValue(int amount) {
		this.accumulatedPoison = amount;
	}
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}

	public void setisAi(Boolean state){
		this.isAi = state;
	}
	public boolean getisAi() {
		return this.isAi;
	}

	public void addWin() {
		this.winCount++;
	}
	public abstract String[] getHistoryInfo();
	public void takeDamage(int amount) {

		this.health = this.health - amount;
		if (this.health <= 0) {
			this.health = 0;
			this.isAlive = false;
			System.out.println(this.getName() + " has fallen.");
		}
	}

	public abstract int getResourceValue();
	public abstract String getResourceName();

	public String getResourceStatus(){
		return getResourceName() + ": " + getResourceValue();
	}

	public abstract CombatIntent attack();
	public abstract CombatIntent defend();
	public abstract CombatIntent special();
	
	public abstract void changeMyResource(int amount);
	
	protected abstract int resultDamage(int min, int max);
	
	public abstract String[] getMySkillMenu();

	public abstract String[] getClassDescription();

	public abstract int getMaxHealth();
	
	public CombatIntent executeAction(int choice) {
		switch (choice) {
			case 1: return attack();
			case 2: return defend();
			case 3: return special();
			default: return new CombatIntent();
		}
	}

}



