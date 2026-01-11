package arena.dialogs;

import java.util.ArrayList;
import java.util.List;

public class SystemMessages {
	private SystemMessages(){};
	public static final String PRESS_ENTER = "====== Press [ENTER] to continue... ========";
//	public static final String[] SYSTEM_MESSAGES = {
//			"=======Press [ENTER] to continue...=========",
//	};
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
