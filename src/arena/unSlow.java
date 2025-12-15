package UniversalClasses;

public class unSlow {
	public static void slowFunc (int delayMilliseconds){
		try {
			Thread.sleep(delayMilliseconds);
		}
		catch (InterruptedException e) {
			// B учебных целях, мы можем проигнорировать
		}
	}
}
