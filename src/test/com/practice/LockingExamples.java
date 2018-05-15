package com.practice;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * Created by pbhattacharya on 9/25/16.
 */
public class LockingExamples {

    /**
     * 1. Write locks are higher priority
     * 2. Threads are allowed to read as long as no existing write locks present/no pending write requests
     * 3. Threads are allowed t
     */
    class ReadWriteLock {

	    int pendingWriteRequests, readers = 0;

	    boolean writeLockAcquired = false;


        public synchronized void lockRead() {
	        try {
		        while (pendingWriteRequests > 0 || writeLockAcquired) {
			        wait();
		        }
	        } catch (InterruptedException ie) {
		        ie.printStackTrace();
	        }
	        readers++;

        }

        public synchronized void unlockRead()  {
	        readers--;
	        notifyAll();
        }

        public synchronized void lockWrite()  {
	        pendingWriteRequests++;
	        try {
		        while (pendingWriteRequests > 0 || readers > 0) {
			        wait();
		        }
	        } catch (InterruptedException ie) {
		        ie.printStackTrace();
	        }
	        writeLockAcquired = true;
	        pendingWriteRequests--;
        }

        public synchronized void unlockWrite()  {
	        writeLockAcquired = false;
	        notifyAll();
        }

    }

    /**
     * 1. Writes are higher priority
     * 2. Acquire read lock if no writers/ no
     */
    class ReadWriteLockVersion2 {

        int readers = 0;
        int writers = 0;
        int pendingWriteRequests = 0;


        public synchronized void lockRead() {
            while(writers > 0 ||  pendingWriteRequests > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            readers++;
        }

        public synchronized void unlockRead() {
            readers--;
            notifyAll();
        }

        public synchronized void lockWrite() {
            pendingWriteRequests++;
            while(readers > 0 || writers > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pendingWriteRequests--;
            writers++;
        }

        public synchronized void unlockWrite() {
            writers--;
            notifyAll();
        }
    }

    private class Person {

        final ReadWriteLock readWriteLock = new ReadWriteLock();

        private int age;

        private String name;

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

	    int getAge()  {
	        int age = 0;
	        try {
		        readWriteLock.lockRead();
		        age = this.age;
	        } finally {
		        readWriteLock.unlockRead();
	        }
            return age;
        }

	    void setAge(int age)  {
	        try {
		        readWriteLock.lockWrite();
		        this.age = age;
	        } finally {
		        readWriteLock.unlockWrite();
	        }
        }

	    String getName() {
		    String name = "";
		    try {
			    readWriteLock.lockRead();
			    name = this.name;
		    } finally {
			    readWriteLock.unlockRead();
		    }
            return name;
        }

	    void setName(String name) {
		    try {
			    readWriteLock.lockWrite();
			    this.name = name;
		    } finally {
			    readWriteLock.unlockWrite();
		    }
        }

    }


	/**
	 * 1. Write locks are higher priority because reads are more frequent
	 * 2. Threads are allowed to read =>
	 *                          a. No existing write locks
	 *                          b. No pending write requests
	 *
	 * 3. Thread are allowed to witre =>
*                               a. No existing write locks
*                               b. No existing read locks
	 */
    @Test
    public void testReadWriteLock() {

        Random random = new Random();

        Person person = new Person(23, "Shiva");

        Executor readExecutor = Executors.newFixedThreadPool(2);

        Executor writeExecutor = Executors.newFixedThreadPool(2);

        Runnable readTask = () -> {
            //System.out.println("Reading age: " + person.getAge() + " name is: " + person.getName());
            person.getAge();
            person.getName();
        };

        Runnable writeTask = () -> {
            //System.out.println("Writing:");
            int age = random.nextInt(100);
            person.setAge(age);
            person.setName("Test" + age);
        };

        for(int i= 0; i< 2000; i++) {
            readExecutor.execute(readTask);
            writeExecutor.execute(writeTask);
        }

    }

    @Test
    public void testExecutor() throws ExecutionException, InterruptedException {

        List<String> list = new ArrayList<>(10);
        list.addAll(Arrays.asList("1","2","3"));
        listTest(list, val -> val.equals("4"));




//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        Callable<String> callable = () -> {
//            System.out.println("Calling now");
//            return "Bulla";
//        };
//
//        Future<String> future = executorService.submit(callable);
//
//        if(!future.isDone()) {
//            String s = future.get();
//            System.out.println("Getting future: " + s);
//        }

    }

    public void listTest(List<String> list, Predicate<String> p) {
        list.forEach(val -> {
            while(!p.test(val)) {
                System.out.println("still not correct value");
            }
        });
    }

    @Test
    public void testDistribution() {

        for(int i = 0; i < 300; i+=16) {
            System.out.println( i & 15);
        }

    }

    @Test
    public void testCountdownLatch() throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);
        for(int i =0; i<10; i++) {
            Runnable runnable = ()->{
                System.out.println("==============Before waiting===========");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("==============After waiting==============");
            };
            new Thread(runnable).start();
        }
        for(int i= 0; i<10; i++) {
            System.out.println("****** Now sleeping for 1 second ******");
            Thread.sleep(1000);
            latch.countDown();
        }

    }

    static class KeyPerson {
        Integer id;
        String name;

        KeyPerson(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KeyPerson person = (KeyPerson) o;

            if (id != null ? !id.equals(person.id) : person.id != null) return false;
            return name != null ? name.equals(person.name) : person.name == null;

        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    @Test
    public void testHashMap() {
        HashMap<KeyPerson, String> map = new HashMap<>();
        KeyPerson person = new KeyPerson(1);
        map.put(person,"value is 1");
        person.setId(2);
        assert map.containsKey(person);
    }

    @Test
    public void testRegexForInteger() {
        String s = "0";
        System.out.println(s.matches("\\d{1,2}"));
    }

    @Test
    public void testBoolean() {
    	boolean var = false;

		System.out.println(String.valueOf(var));
	}

	@Test
	public void testHashMapAgain() {
		Map<String, String> map = new HashMap<>();

		map.put("2","testing 2");
		map.put("3","testing 3");

		// String val = map.containsKey("4") ? map.get("4") : map.put("4", "Testing 4");

		String val = map.put("4", "Testing 4");

		System.out.println("val: " + val);

	}

	@Test
	public void testCallable() {
		System.out.printf("Parent thread id: " + Thread.currentThread().getId());

		Callable<Integer> callable = () -> {
			System.out.printf("Child thread id: " + Thread.currentThread().getId());
			return 1;
		};

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<Integer> future = executorService.submit(callable);

		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}


	Map<String, String> ccMap = new ConcurrentHashMap<>();


	/**
	 * Testing concurrentHashMap
	 */
	@Test
	public void testConcurrentHashMap() {
		final CountDownLatch startLatch = new CountDownLatch(1);

		Callable<Integer> callable = () -> {
			startLatch.await();
			String testValue;
			if (ccMap.containsKey("testKey")) {
				testValue = ccMap.get("testKey");
			} else {
				testValue = "testVal";
				ccMap.put("testKey", testValue);
			}
			System.out.println("Test value is: " + testValue);
			return 1;
		};

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for(int i=0; i < 5000; i++) {
			executorService.submit(callable);
		}
		// Open the gate
		startLatch.countDown();
	}



}
