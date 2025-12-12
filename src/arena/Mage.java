package arena;

public class Mage extends Character{
	
	private int mana; // Новое, уникальное поле

	// Конструктор Mage
	public Mage(String name, int health, int mana) {
		// 1. Вызов конструктора родителя (Character)
		super(name, health); // Передаем name и health в Character
		// 2. Инициализация своего собственного поля
		this.mana = mana;
	}

	public int getMana(){
		return this.mana;
	}

	public boolean castSpell(int manaCost)
			throws CharacterNotAliveException // <--- ДОБАВЬ ЭТУ ЧАСТЬ
	{
		// 1. ПЕРВАЯ ПРОВЕРКА: Статус жизни (важнее маны)
		if (!this.getIsAlive()) { // Используем ! для проверки, что isAlive == false
			// Бросаем наше проверяемое исключение
			throw new CharacterNotAliveException(this.getName() + " cannot cast, they are dead.");
		}

		// 2. ВТОРАЯ ПРОВЕРКА: Мана
		if (this.mana >= manaCost) {
			this.mana = this.mana - manaCost;
			return true;
		} else {
			return false;
		}
	}
}