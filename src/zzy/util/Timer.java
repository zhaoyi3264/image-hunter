package zzy.util;

/**
 * A simple timer
 * 
 * @author Zhaoyi
 */
public class Timer {
	private static boolean started = false;
	private static long start;
	private static long end;

	/**
	 * Start the timer
	 */
	public static void start() {
		if (started)
			throw new IllegalStateException();
		started = true;
		start = System.currentTimeMillis();
	}

	/**
	 * Stop the timer
	 */
	public static void stop() {
		if (!started)
			throw new IllegalStateException();
		end = System.currentTimeMillis();
		started = false;
	}

	/**
	 * Return the double representation of the time span
	 * 
	 * @return the time in double
	 */
	public static double returnDoubleTime() {
		if (started)
			throw new IllegalStateException();
		return (end - start) / 1000.0;
	}

	/**
	 * Return the string representation of the time span
	 * 
	 * @return the time in string
	 */
	public static String returnStringTime() {
		if (started)
			throw new IllegalStateException();
		return String.format("%.2f", returnDoubleTime());
	}
}