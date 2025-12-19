package arena.characters;
//Первичный баланс цифрами
//
//		HP: Warrior 100, Mage 80
//		Атака: Warrior 10–15, Mage 8–12
//		Защита: Warrior 50% урон, Mage 100% урон (тратит Mana)
//		Спешел: Warrior 2x урон (50 Rage), Mage +20 Mana или +15 HP (30 Mana)
//     +Cooldown???
//		Ресурс: Warrior Rage 0–100, Mage Mana 0–100, пассивное восстановление 5–10/ход
//		Это даст сбалансированные по длине боя значения, чтобы бой длился 3–5 ходов, а спешел можно использовать 1–2 раза.
public class Character {
	private String name;
	private int health;
	private boolean isAlive = true;
	
	public Character(String name, int health) {
		this.name = name;
		this.health = health;
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

	public void setHealth(int health) {
		this.health = health;
	}

	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}

	public void damage(int amount) {

		this.health = this.health - amount; // или this.health -= amount;

		// 2. Проверяем, умер ли персонаж
		if (this.health <= 0) {
			this.health = 0;         // Гарантируем, что здоровье не станет отрицательным
			this.isAlive = false;    // Меняем состояние напрямую
			System.out.println(this.getName() + " has fallen.");
		}
		// Логика работы с полями объекта (например, this.health)
	}
	public void die() {
		// 1. Условие: если isAlive уже false (персонаж мертв)
		if (this.health == 0 || this.isAlive == false) {
			throw new IllegalStateException("Character is already dead.");
		}
		this.isAlive = false;
		System.out.println(this.getName() + " has fallen.");
	}

	@Override
	public String toString() {
		// 1. Начинаем строку
		String result = "Hero: " + name + " | ";

		result = result + "Class: " + this.getClass().getSimpleName() + " | ";
		result = result + "Health: " + health + " | ";

		if (isAlive) {
			result = result + "Live" + " | \n";
		} else {
			result = result + "Dead" + " | \n";
		}

		return result;
	}

}



