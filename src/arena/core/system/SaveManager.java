package arena.core.system;

import arena.characters.Character;

import arena.config.SystemConfig;
import java.io.*;

public class SaveManager {
	
	public static void saveCharacter(Character winner) {
		// 1. Сначала позаботимся о папке
		File directory = new File(SystemConfig.SAVE_PATH);
		if (!directory.exists()) {
			directory.mkdir();
		}
		try {

			String fileName = SystemConfig.SAVE_PATH + winner.getName() + ".dat";
			
			FileOutputStream fileStream = new FileOutputStream(fileName);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			
			objectStream.writeObject(winner);
			objectStream.close();
			
			System.out.println("====== Fighter " + winner.getName() + " saved ======");
			
		} catch (IOException e) {
			System.out.println("[Error]: Fail to save winner!!! " + e.getMessage());
		}
	}

}