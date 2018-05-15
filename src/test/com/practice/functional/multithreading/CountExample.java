package com.practice.functional.multithreading;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by pbhattacharya on 4/13/18.
 */
public class CountExample {

	private class Counter {
		int count = 0;

		ReentrantLock lock = new ReentrantLock();

		void increment() throws InterruptedException {
			// Do something else like logging garbage etc
			Thread.sleep(10);

			try {
				lock.lock();
				count = count + 1;
			} finally {
				lock.unlock();
			}
		}
	}

	@Test
	public void testCounter() throws InterruptedException {
		Counter counter = new Counter();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CountDownLatch workerLatch = new CountDownLatch(10000);

		IntStream.range(0, 10000).forEach(i -> executorService.submit(() -> {
			try {
				counter.increment();
				workerLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}));
		// wait till counter reaches 0
		workerLatch.await();

		executorService.shutdown();
		System.out.println("Counter value: " + counter.count);
	}

	@Test
	public void testUpdateAllTables() {
		String text = "ffaabc1b32352dbb6929c6560fd29f9f";
		System.out.println("len " + text.length());
	}

}
