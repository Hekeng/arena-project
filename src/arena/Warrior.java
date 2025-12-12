package arena;

public class Warrior extends Character {
	private int armor; // Новое, уникальное поле

	// Конструктор Warrior
	public Warrior(String name, int health, int armor) {
		// 1. Вызов конструктора родителя (Character)
		super(name, health); // Передаем name и health в Character
		
		// 2. Инициализация своего собственного поля
		this.armor = armor;


	}
	@Override
	public void damage(int amount) {
		// 1. Вычислить фактический урон (Урон - Броня)
		int finalDamage = amount - this.armor;

		// 2. Гарантия, что урон не отрицательный
		if (finalDamage < 0) {
		finalDamage = 0;
		}

		// 3. Вызов родительского метода с вычисленным уроном
		super.damage(finalDamage); // Передаем результат
	}
}
