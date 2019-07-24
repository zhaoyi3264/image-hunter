package zzy.util;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;

public class Timer {
	private static boolean started = false;
	private static long start;
	private static long end;

	public static void start() {
		if (started)
			throw new IllegalStateException();
		started = true;
		start = System.currentTimeMillis();
	}

	public static void stop() {
		if (!started)
			throw new IllegalStateException();
		end = System.currentTimeMillis();
		started = false;
	}

	public static double returnDoubleTime() {
		if (started)
			throw new IllegalStateException();
		return (end - start) / 1000.0;
	}

	public static String returnStringTime() {
		if (started)
			throw new IllegalStateException();
		return String.format("%.2f", returnDoubleTime());
	}

	public static void startInterval(Thread parent, int seconds) {
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		executor.submit(new CountDownTimer(2));
//		executor.shutdown();
		Thread t = new Thread(() -> {
			long limit = System.currentTimeMillis() + seconds * 1000;
			while (System.currentTimeMillis() < limit) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			throw new IllegalStateException();
		});
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				parent.interrupt();
				throw new RuntimeException();
			}
		});

		t.start();
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		try {
			startInterval(Thread.currentThread(), 2);
		} catch (RuntimeException e) {
			System.out.println(System.currentTimeMillis());
		}
		while (true) {
			System.out.println(System.currentTimeMillis());
		}
	}
}

class CountDownTimer implements Callable<Object> {
	private long limit;

	public CountDownTimer(int seconds) {
		limit = System.currentTimeMillis() + seconds * 1000;
	}

	@Override
	public Object call() throws Exception {
		while (System.currentTimeMillis() < limit) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new Exception(e);
			}
		}
		throw new Exception();
	}
}