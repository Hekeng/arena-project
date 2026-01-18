package arena.helpers;

public class UnSlow {
	public static void slowFunc (int delayMilliseconds){
		try {
			Thread.sleep(delayMilliseconds);
		}
		catch (InterruptedException e) {
			//
		}
	}
}
