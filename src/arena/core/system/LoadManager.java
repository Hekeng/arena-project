package arena.core.system;

import java.io.*;

import arena.characters.Character;
public class LoadManager {
	public static Character loadCharacter(String filePath) {
		try {
			FileInputStream fileStream = new FileInputStream(filePath);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			// Читаем объект и принудительно приводим его к типу Character
			Character loadedCharacter = (Character) objectStream.readObject();

			objectStream.close();
			fileStream.close();

			System.out.println("====== Fighter " + loadedCharacter.getName() + " loaded successfully ======");
			return loadedCharacter;

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("[Error]: Failed to load character! " + e.getMessage());
			return null; // Если ошибка, возвращаем пустоту
		}
	}
	public static String[] getSavedFightersList() {
		File directory = new File("saves");

		// Проверяем, существует ли папка
		if (!directory.exists() || !directory.isDirectory()) {
			return new String[0]; // Возвращаем пустой массив, если папки нет
		}

		// Получаем список всех файлов, заканчивающихся на .dat
		File[] files = directory.listFiles((dir, name) -> name.endsWith(".dat"));

		if (files == null || files.length == 0) {
			return new String[0];
		}

		// Создаем массив строк для имен файлов
		String[] fileNames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getName(); // Например: "Mage_Hexen.dat"
		}

		return fileNames;
	}
}
