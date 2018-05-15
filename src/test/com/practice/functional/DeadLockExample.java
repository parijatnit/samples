package com.practice.functional;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by pbhattacharya on 5/22/17.
 */
public class DeadLockExample {


	private static class Foo {

		private final Object lock1 = new Object();
		private final Object lock2 = new Object();

		void  doSomething1() throws InterruptedException	{
			synchronized (lock1) {
				Thread.sleep(10);
				synchronized (lock2) {
					System.out.println("Dead lock block: This log should not be shown.");
				}
			}
		}

		void doSomething2() throws InterruptedException	{
			synchronized (lock2) {
				Thread.sleep(10);
				synchronized (lock1) {
					System.out.println("Dead lock block: This log should not be shown.");
				}
			}
		}

	}


	@Test
	public void testDeadLock() throws Exception {
		final Foo testFoo = new Foo();

		Runnable runnable1 = () -> {
			try {
				System.out.println("Running runnable 1: ");
				testFoo.doSomething1();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Runnable runnable2 = () -> {
			try {
				System.out.println("Running runnable 2: ");
				testFoo.doSomething2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Thread t1 = new Thread(runnable1);
		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	@Test
	public void testEmptyStringBytes() {
		String text = "";
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		System.out.println("Bytes size is: " + bytes.length);
	}


	public static void main(String[] args) {
		// schedule shutdown
		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

		Runnable stopTask = new Runnable() {
			@Override
			public void run() {
				System.out.println("Came back from the dead");
			}
		};

		executorService.schedule(stopTask, 5, TimeUnit.SECONDS);
	}

	@Test
	public void testBoolean() {
		System.out.println("value is: " + Boolean.valueOf("test"));
		System.out.println("value is: " + Boolean.valueOf("null"));
		System.out.println("value is: " + Boolean.valueOf("bkfkjhfdfdshfhdksfhdsfjsdkhfsd"));
		System.out.println("value is: " + Boolean.valueOf("true"));
		System.out.println("value is: " + Boolean.valueOf("false"));
	}


}
