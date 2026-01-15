package arena.logic;

import arena.characters.Character;
import arena.config.MenuConfig;
import arena.core.system.SaveManager;
import arena.dialogs.SystemMessages;
import arena.helpers.UnInputInt;
import arena.helpers.UnInputStr;
import arena.helpers.UnSlow;
import arena.ui.Menu;

import arena.config.SystemConfig;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

public class Validation {

	public static boolean quantityFightersValid (ArrayList<Character> list, int minFighters){
		if (list.size() >= minFighters) {
			return true;
		}
		return false;
	}

	public static boolean characterNameValid(ArrayList<Character> list, String name) {
		if (name.trim().isEmpty()) {
			Menu.printStandardFrame(SystemMessages.ERR_EMPTY_NAME);
			return false;
		}

		if (isNameInParty(list, name) || isNameOnDisk(name)) {
			Menu.printStandardFrame(SystemMessages.ERR_NAME_EXIST);
			return false;
		}

		return true;
	}

	public static boolean isNameInParty(ArrayList<Character> list, String name) {
		for (Character c : list) {
			if (c.getName().equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	public static boolean isNameOnDisk(String name) {
		File file = new File(SystemConfig.SAVE_PATH + name + ".dat");
		return file.exists();
	}
	public static boolean isNameTaken(String inputName) {
		// 1. Формируем путь к потенциальному файлу (только имя + расширение)
		String filePath = SystemConfig.SAVE_PATH + inputName + ".dat";
		File file = new File(filePath);
		return file.exists();
	}



}
