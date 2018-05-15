package com.practice.functional;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by pbhattacharya on 4/12/18.
 */
public class LockWaitTest {

	static class Person {
		final ReentrantLock lock = new ReentrantLock();

		int age;
		String name;

		Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		void setAge(int age) throws InterruptedException {
			lock.tryLock(10000, TimeUnit.MILLISECONDS);
			try {
				Thread.sleep(100);
				this.age = age;
			} finally {
				lock.unlock();
			}

		}

		void setName(String name) throws InterruptedException {
			lock.tryLock(10000, TimeUnit.MILLISECONDS);
			try {
				Thread.sleep(100);
				this.name = name;
			} finally {
				lock.unlock();
			}
		}

		int getAge() throws InterruptedException {
			while(lock.isLocked()) {
				Thread.sleep(10);
			}
			return this.age;

		}

		String getName() throws InterruptedException {
			while(lock.isLocked()) {
				Thread.sleep(10);
			}
			return this.name;
		}
	}

	@Test
	public void testLockNotify() throws InterruptedException {
		final Person person = new Person(20, "Ram");
		final Random rand = new Random();
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch workerLatch = new CountDownLatch(20);

		Runnable writeRunnable = () -> {
			try {
				startLatch.await();
				System.out.println("Updating person");
				person.setAge(rand.nextInt(100));
				person.setName("abc-"+ rand.nextInt(15));
				workerLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable readRunnable = () -> {
			try {
				startLatch.await();
				System.out.println("Name is: " + person.getName());
				System.out.println("Age is: " + person.getAge());
				workerLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread readThread = new Thread(readRunnable);
			readThread.start();

			Thread writeThread = new Thread(writeRunnable);
			writeThread.start();
		}

		startLatch.countDown();
		workerLatch.await();

	}


}
