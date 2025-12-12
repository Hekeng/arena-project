package arena;

public class Game {
	public static void main(String[] args) {
		System.out.println("TEST RUN V0.2");

		Warrior warrior = new Warrior("Leonid",100,20);
		Mage mage = new Mage("Vasiliy", 100, 40);

		//Воин атакует Мага
		mage.damage(15);
		boolean castSpellResult = false; // Переменная должна быть объявлена вне try-catch

		try {
			castSpellResult = mage.castSpell(20);
		} catch (CharacterNotAliveException e) {
			// Если Маг мертв, мы ловим ошибку, выводим сообщение, и castSpellResult остается false
			System.out.println("[GAME ERROR]: " + e.getMessage());
		}

		if (castSpellResult == true) {
			warrior.damage(40);
		} else{
			System.out.println("Cast is falls!");
		}


		System.out.println("Warrior " + warrior.getName() + " hath "+ warrior.getHealth() +" health");
		System.out.println("Mage " + mage.getName() + " hath "+ mage.getHealth() + " health & " + mage.getMana() + " mana");

		warrior.damage(100);
		System.out.println("Warrior " + warrior.getName() + " hath "+ warrior.getHealth() +" health");
		//warrior.die();

		// 2. Обернуть в try-catch, чтобы поймать IllegalStateException
		try {
			// Попытка убить второй раз
			warrior.die(); // <-- Здесь должно сработать throw!

		} catch (IllegalStateException e) {
			// Поймали ошибку
			System.out.println("[GAME LOG] Error: " + e.getMessage());
		}

		warrior.damage(10);

		System.out.println("Warrior " + warrior.getName() + " hath "+ warrior.getHealth() +" health");
	}
}

	// 	System.out.println("Character " + player.getName() + " hath "+ player.getHealth() +"health");
	// 	player.damage(50);

	// 	System.out.println("Character " + player.getName() + " hath "+ player.getHealth() +"health");
	// 	player.damage(60);

	// 	if (player.getIsAlive() == false) {
	// 	System.out.println("Character " + player.getName() + " hath "+ player.getHealth() +"health. He is dead!");
	// } else{
	// 	System.out.println("Character " + player.getName() + " hath "+ player.getHealth() +"health. He is live!");}