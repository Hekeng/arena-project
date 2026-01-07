package arena.config;

// TODO: handle tie animation
// FIXME: remove hardcoded delay
// NOTE: later replace with tournament logic

public final class MenuConfig {

	private MenuConfig(){}// запрещаем создание объекта

	//DELAYS
	public static final int MEIN_MENU_DELAY = 5;
	public static final int OUTPUT_MENU_DELAY = 5;
	public static final String MENU_GAP = "============================================";
	public static final String[] MAIN_MENU = {
		"====== Hello! Welcome to game Arena! =======",
		"=================== MENU ===================",
		"============ 1.Create Character ============",
		"============ 2.Start Fight =================",
		"============ 0.Exit ========================"
	};
	public static final int CREATE_CHARACTER_ID = 1;

	public static final int START_FIGHT_ID = 2;

	public static final int CLASS_ID_BACK = 0;

	public static final int MAIN_MENU_PUNKTS_QUANTUTY =  MAIN_MENU.length - 2;

	public static final String[] MENU_CHOOSE_CLASS = {
		"====== Choose Your character class! ========",
		"=================== MENU ===================",
		"============ 1.Create Mage =================",
		"============ 2.Create Warrior ==============",
		"============ 0.Back to main menu ==========="
	};
	public static final int CHOOSE_CLASS_MENU_PUNKTS_QUANTUTY = MENU_CHOOSE_CLASS.length - 2;
	public static final int CLASS_ID_MAGE = 1;
	public static final int CLASS_ID_WARRIOR = 2;

	public static final String[] MENU_ENTER_NAME = {
		"====== Choose Your character Name! ========="
	};
	


}
