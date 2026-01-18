package arena.dialogs;

import java.util.ArrayList;
import java.util.List;

public class SystemMessages {

	public static final String PRESS_ENTER = "====== Press [ENTER] to continue... ========";

	public static final String[] ERR_NOT_A_NUMBER = {
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"============= Incorrect input! =============",
			"============= Please enter a NUMBER ========"
	};

	public static final String[] ERR_QUANTITY_FIGHTERS = {
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"======= You must have min 2 fighters, ======",
			"======= please create fighters! ============"
	};

	public static final String[] ERR_STRING_INPUT = {
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"=== The input cannot be empty and must =====",
			"=== contain only letters. Please try again ="
	};

	public static final String[] ERR_EMPTY_NAME ={
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"=========== Name cannot be empty! ==========",
			"=========== Please try again. =============="
	};
	public static final String[] ERR_NAME_EXIST = {
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"===== Character name already exists! ======="
	};

	public static final String[] ERR_EMPTY_PARTY ={
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"======= Characters are not created! ========"
	};

	public static final String[] ERR_CHAR_EXIST ={
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"====== Character is already exists! ========"
	};
	public static final String[] ERR_PARTY_FULL ={
			"================== SYSTEM ==================",
			"================== ERROR ===================",
			"============== Party is full! =============="
	};


	public static String [] failInputMessage(String [] validOptions, int analiseInput){
		List<String> lines = new ArrayList<>();
		
		String failMessage;
		
		String menuNumers = "";
		
		if (validOptions.length == 0){
			lines.add("Fail!!! something went wrong!");
			return lines.toArray(new String[0]);
		}
		
		for (int i = 0; i < validOptions.length; i++) {
			
			if (i < validOptions.length - 1) {
				menuNumers += validOptions[i] + ", ";
			} else {
				
				menuNumers += validOptions[i];
			}
		}
		
		failMessage = String.format("Yours input: %d is wrong please input only %s.", analiseInput, menuNumers);
		
		lines.add(failMessage);
		
		return lines.toArray(new String[0]);
	}
}
