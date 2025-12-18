package arena.characters;

public class Character {

	// 1. Поля: Делаем их приватными!
	private String name;
	private int health;
	private boolean isAlive = true;
	
	// Конструктор (оставляем как есть)
	public Character(String name, int health) {
		this.name = name;
		this.health = health;
	}

	// 2. Геттер для имени (ЧТЕНИЕ)
	// Возвращает тип поля (String)
	public String getName(){
		return this.name;
	}

	public int getHealth(){
		return this.health;
	}

	public boolean getIsAlive(){
		return this.isAlive;
	}

	// 3. Сеттер для здоровья (ЗАПИСЬ)
	// Возвращаемый тип: void, так как метод ничего не возвращает, только меняет поле
	public void setHealth(int health) {
		// Мы используем 'this', чтобы различать поле класса (this.health)
		// и аргумент метода (health).
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
		String result = "Hero: " + name + "\n";

		result = result + "Class: " + this.getClass().getSimpleName() + "\n";

		// 2. Добавляем данные о здоровье
		result = result + " | HP: " + health + "\n";

		// 3. Добавляем статус
		if (isAlive) {
			result = result + " [Жив]" + "\n";
		} else {
			result = result + " [Мертв]" + "\n";
		}

		return result;
	}

}



