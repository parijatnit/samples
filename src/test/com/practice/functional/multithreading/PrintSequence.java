package com.practice.functional.multithreading;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pbhattacharya on 9/27/17.
 */
public class PrintSequence {


	public static final Object lock = new Object();

	public static void printEven(AtomicInteger val) throws InterruptedException {
		synchronized (lock) {
			while (val.get() <= 100) {
				if (val.get() % 2 == 0) {
					System.out.println(val);
					val.incrementAndGet();
					lock.notify();
				} else {
					lock.wait();
				}
			}
		}
	}

	public static void printOdd(AtomicInteger val) throws InterruptedException {
		synchronized (lock) {
			while (val.get() < 100) {
				if (val.get() % 2 == 1) {
					System.out.println(val);
					val.incrementAndGet();
					lock.notify();
				} else {
					lock.wait();
				}
			}
		}
	}


	@Test
	public void testPrintSequence() throws Exception {
		final AtomicInteger val = new AtomicInteger(0);
		final Object lock = new Object();


		Runnable r1 = ()-> {
			try {
				synchronized (lock) {
					while (val.get() <= 100) {
						if (val.get() % 2 == 0) {
							System.out.println(val);
							val.incrementAndGet();
							lock.notify();
						} else {
							lock.wait();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread t1 = new Thread(r1);

		Runnable r2 = ()-> {
			try {
				synchronized (lock) {
					while (val.get() < 100) {
						if (val.get() % 2 == 1) {
							System.out.println(val);
							val.incrementAndGet();
							lock.notify();
						} else {
							lock.wait();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread t2 = new Thread(r2);

		// start both the threads
		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}








}
