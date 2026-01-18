package arena.core.system;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import arena.characters.Character;
import arena.config.SystemConfig;

public class LoadManager {
	private static File[] getOnlyDatFiles() {
		File directory = new File(SystemConfig.SAVE_PATH);
		File[] allFiles = directory.listFiles();
		
		if (allFiles == null) return new File[0];
		
		List<File> filteredList = new ArrayList<>();
		for (File f : allFiles) {
			if (f.isFile() && f.getName().endsWith(".dat")) {
				filteredList.add(f);
			}
		}
		return filteredList.toArray(new File[0]);
	}
	
	public static String[] getSavedHeroesListDetailed() {
		ArrayList<String> menuRows = new ArrayList<>();
		File[] files = getOnlyDatFiles();
		
		for (File currentFile : files) {
			try {
				FileInputStream fileStream = new FileInputStream(currentFile);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);
				Character hero = (Character) objectStream.readObject();
				
				// Твоя строка со String.join
				String info = (menuRows.size() + 1) + ". " + String.join(" | ", hero.getPersonalInfo());
				menuRows.add(info);
				info = ("");
				
				objectStream.close();
			} catch (Exception e) {
				System.out.println("Could not read file: " + currentFile.getName());
			}
		}
		return menuRows.toArray(new String[0]);
	}
	
	public static Character loadCharacter(int choice) {
		File[] files = getOnlyDatFiles();
		
		if (choice <= 0 || choice > files.length) return null;
		
		File currentFile = files[choice - 1];
		Character hero = null;
		
		try {
			FileInputStream fileStream = new FileInputStream(currentFile);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			hero = (Character) objectStream.readObject();
			objectStream.close();
		} catch (Exception e) {
			System.out.println("Error loading character from: " + currentFile.getName());
		}
		return hero;
	}
}