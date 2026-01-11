package arena.helpers;

public class UnSlowPrinter {
	public  static void oneLetterPrint(String arrStr, int daley){
		for (char c : arrStr.toCharArray()) {
			System.out.print(c);
			System.out.flush();
			UnSlow.slowFunc(daley);
		}
		System.out.print("\n");
	}
}
