package arena.helpers;

import arena.config.MenuConfig;

public class UnSlowPrinter {
	public  static void oneLetterPrint(String arrStr, int daley){
		for (char c : arrStr.toCharArray()) {
			System.out.print(c);
			System.out.flush();
			unSlow.slowFunc(daley);
		}
		System.out.print("\n");
	}
}
