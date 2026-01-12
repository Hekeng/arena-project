package arena.core.system;

import arena.characters.Character;

import arena.config.SystemConfig;
import java.io.*;

public class SaveManager {
	
	public static void saveCharacter(Character winner) {
		// 1. Сначала позаботимся о папке
		File directory = new File(SystemConfig.SAVE_PATH);
		if (!directory.exists()) {
			directory.mkdir(); // Создаст папку, если её нет
		}
		
		try {
			// 2. Формируем красивое имя пути: "saves/Mage_Hexen.dat"
			String fileName = SystemConfig.SAVE_PATH
					//+ winner.getClass().getSimpleName() + "_"
					+ winner.getName() + ".dat";
			
			FileOutputStream fileStream = new FileOutputStream(fileName);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			
			objectStream.writeObject(winner);
			objectStream.close();
			
			System.out.println("====== Fighter " + winner.getName() + " saved ======");
			
		} catch (IOException e) {
			System.out.println("[Error]: Fail to save winner!!! " + e.getMessage());
		}
	}
//	private static final String FILE_NAME = "arena_hall_of_fame.txt";
//
//	public static void saveResult(Character winner) {
//		// 'true' в FileWriter означает "дозаписать в конец файла", а не перезаписать
//		try (FileWriter fw = new FileWriter(FILE_NAME, true);
//		     PrintWriter out = new PrintWriter(fw)) {
//
//			out.println("Champion: " + winner.getName() + " | Wins: " + winner.getWinCount() + " | Health Left: " + winner.getHealth());
//			System.out.println("[SYSTEM]: Result saved to Hall of Fame.");
//
//		} catch (IOException e) {
//			System.err.println("[ERROR]: Could not save progress: " + e.getMessage());
//		}
//	}
}