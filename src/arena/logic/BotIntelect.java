package arena.logic;

import arena.characters.Character;
import arena.characters.Mage;
import arena.characters.Warrior;
import arena.characters.Assassin;
import arena.helpers.UnRand;

public class BotIntelect {

	public static int botIntelect(Character ai, Character enemy) {
		// 1. ПРОВЕРКА ВЫЖИВАНИЯ
		// Если здоровья критически мало, бот с вероятностью 80% уйдет в защиту
		if (ai.getHealth() < 25 && UnRand.randomNumber(1, 100) <= 80) {
			return 2; // BLOCK
		}

		// 2. ВЫБОР ЛОГИКИ ПО КЛАССУ
		if (ai instanceof Mage) {
			return mageLogic(ai, enemy);
		} else if (ai instanceof Warrior) {
			return warriorLogic(ai, enemy);
		} else if (ai instanceof Assassin) {
			return assassinLogic(ai, enemy);
		}

		return 1; // Дефолтная атака, если что-то пошло не так
	}

	private static int mageLogic(Character ai, Character enemy) {
		// Если мало маны — восстанавливаемся (у Мага это Special)
		if (ai.getResourceValue() < 10) return 3;

		// Если маны много — жарим огнем
		return 1;
	}

	private static int warriorLogic(Character ai, Character enemy) {
		// У воина Special обычно дорогой, проверяем накопил ли он ярость
		if (ai.getResourceValue() >= 30) return 3;

		// В 20% случаев воин может решить просто встать в блок для накопления ярости
		if (UnRand.randomNumber(1, 5) == 1) return 2;

		return 1;
	}

	private static int assassinLogic(Character ai, Character enemy) {
		// ХИТРОСТЬ: Если у врага (например, Мага) много ресурсов, сжигаем их
		if (enemy.getResourceValue() > 30 && ai.getResourceValue() >= 20) {
			return 3; // Smoke Bomb (Burn resources)
		}

		// Если у врага осталось мало HP, ассасин пытается его добить атакой
		if (enemy.getHealth() < 20) return 1;

		// В остальное время — рандом между атакой и блоком (контрудар)
		return UnRand.randomNumber(1, 2);
	}
}