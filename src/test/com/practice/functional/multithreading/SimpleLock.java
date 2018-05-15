package com.practice.functional.multithreading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by pbhattacharya on 12/20/17.
 */
public class SimpleLock {

	public static void main(String[] args) throws InterruptedException {
		Person person = new Person(1, "Amol");
		Random random = new Random();

		final CountDownLatch latch = new CountDownLatch(1);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = () -> {
				try {
					latch.await();
					person.setAge(random.nextInt(40));
					System.out.println("Age is: " + person.getAge());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			Thread th = new Thread(runnable);
			th.start();
		}

		latch.countDown();
	}


	static class Person {

		private final Lock lock = new Lock();

		private Integer id;

		private String name;

		private Integer age;


		Person(Integer id, String name) {
			this.id = id;
			this.name = name;
		}


		public void setAge(Integer age) throws InterruptedException{
			lock.lock();
			this.age = age;
			lock.unlock();
		}

		public Integer getAge() {
			return age;
		}

		public Integer getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	static class Lock {

		private boolean isLocked = false;


		public synchronized void lock() throws InterruptedException {
			while (isLocked) {
				System.out.println("Waiting to acquire lock " + Thread.currentThread().getId());
				wait();
			}
			isLocked = true;
		}


		public synchronized void unlock() {
			isLocked = false;
			notifyAll();
		}

	}


}
