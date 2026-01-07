package arena.core.scene;

import java.util.List; // Добавляем импорт
import arena.helpers.UnSlowPrinter;
import arena.config.MenuConfig;

public class Narration {

	// Старая версия для массивов (пусть остается, чтобы ничего не сломать)
	public static void Narration(String[] arr) {
		for (String printedStr : arr) {
			UnSlowPrinter.oneLetterPrint(printedStr, MenuConfig.OUTPUT_MENU_DELAY);
		}
	}

	// Новая версия для списков (List)
	public static void Narration(List<String> list) {
		for (String printedStr : list) {
			UnSlowPrinter.oneLetterPrint(printedStr, MenuConfig.OUTPUT_MENU_DELAY);
		}
	}

	public static void Narration(String singleText) {
		UnSlowPrinter.oneLetterPrint(singleText, MenuConfig.OUTPUT_MENU_DELAY);
	}
}
