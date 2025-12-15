package arena.config;

public final class MenuConfig {

	private MenuConfig(){}// запрещаем создание объекта

	public static final int MEIN_MENU_DELAY = 10;
	public static final String MENU_GAP = "============================================";
	public static final String[] MAIN_MENU = {
		"=======Hello! Welcome to game Arena!========",
		"====================MENU====================",
		"=============1.Create Character=============",
		"=============2.Start Fight==================",
		"=============0.Exit========================="
	};
	public static final String[] MENU_CHOOSE_CLASS = {
		"=======Choose Your character class!=========",
		"====================MENU====================",
		"=============1.Create Mage==================",
		"=============2.Create Warrior===============",
		"=============9.Back to main menu============"
	};

	public static final String[] MENU_ENTER_NAME = {
		"=======Choose Your character Name!=========="
	};



}
