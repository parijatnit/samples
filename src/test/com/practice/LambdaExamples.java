package com.practice;

import javafx.util.Pair;
import junit.framework.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pbhattacharya on 5/9/16.
 */
public class LambdaExamples {

	@Test
	public void testExample() throws Exception {
		int n = 2000;
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch workerLatch = new CountDownLatch(n);
		AtomicInteger a = new AtomicInteger(0);
		for (int i = 0; i < n; i++) {
			Runnable runnable = () -> {
				try {
					startLatch.await();
					System.out.println("Testing concurrency, in test example, Task " + a.incrementAndGet() + " is running.");
					workerLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			new Thread(runnable).start();
		}
		startLatch.countDown();
		workerLatch.await();
	}

	@Test
	public void testRemoveDuplicates() {
		Integer[] arr = {23, 34, 45, 89, 23, 45, 100};
		Set<Integer> set = new TreeSet<>();
		Collections.addAll(set, arr);
		arr = set.toArray(new Integer[set.size()]);
		for (int val : arr) {
			System.out.print(val + ",");
		}
	}

	@Test
	public void testCustomSortingByLambda() {
		List<String> list = Arrays.asList("23", "34", "45", "89", "23", "45", "100", "This is a test", "124343");
		Collections.sort(list, (String text1, String text2) -> text2.length() - text1.length());

		list.forEach(System.out::println);
	}

	class ChainComparator<T> implements Comparator<T> {

		private List<Comparator<T>> comparators;

		public ChainComparator(Comparator<T>... comparators) {
			this.comparators = Arrays.asList(comparators);
		}


		@Override
		public int compare(T o1, T o2) {
			int result = 0;
			for (Comparator<T> comparator : comparators) {
				result = comparator.compare(o1, o2);
				if (result != 0) {
					return result;
				}
			}
			return result;
		}
	}


	static class Person {
		String name;
		int age;
		Double salary;
		String department;

		Person(String name, int age, Double salary, String department) {
			this.name = name;
			this.age = age;
			this.salary = salary;
			this.department = department;
		}

		public String getName() {
			return name;
		}

		public String getDepartment() {
			return department;
		}

		public Double getSalary() {
			return salary;
		}

		public int getAge() {
			return age;
		}
	}

//	@Test
//	public void testChainedSortingByLambda() {
//		List<Person> persons = Arrays.asList(new Person("Anikaet", 23, 21000.00), new Person("John", 21, 40000.00), new Person("Thomas", 35, 3000.00), new Person("Nilesh", 31, 3000.00));
//
//		Collections.sort(persons, new ChainComparator<Person>((Person p1, Person p2) -> p1.salary.compareTo(p2.salary),
//															  (Person p1, Person p2) -> p2.age - p1.age));
//
//		persons.forEach(person -> System.out.println("com.practice.Person name is: " + person.name + " age: " + person.age + " salary: " + person.salary));
//
//	}

	@Test
	public void testBinary() {
//        System.out.println("binary value: " + Integer.toBinaryString(7));
		System.out.println("binary value: " + Integer.toBinaryString(7 >>> 3));
		System.out.println("binary value: " + Integer.toBinaryString(-7 >> 3));
		System.out.println("binary value: " + Integer.toBinaryString(-7 >>> 3));
		//System.out.println("binary value: " + Integer.toBinaryString(-1));


		System.out.println("max value for integer: " + Math.pow(2, 31));

	}

	@Test
	public void testSortingHashMapByValues() {
		Map<String, Integer> map = new HashMap<>();

		map.put("abc", 212);
		map.put("testing", 100);
		map.put("add", 991);
		map.put("well", 91);


		TreeSet<Map.Entry<String, Integer>> sortedByValue = new TreeSet<>((Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) -> entry1.getValue() - entry2.getValue());
		sortedByValue.addAll(map.entrySet());

		List<String> list = new ArrayList<>(sortedByValue.size());

		sortedByValue.forEach(entry -> list.add(entry.getKey()));

		list.forEach(System.out::println);

	}

	@Test
	public void testBitWiseOperators() {
		long a = 8123667899989L;

		System.out.println(a ^ (a >>> 32));
		//System.out.println(a >>> 32);

	}


	@Test
	public void testLambda() throws InterruptedException {
		Operation sum = (a, b) -> (a + b);
		Operation minus = (int a, int b) -> (a - b);
		Operation multiply = (a, b) -> a * b;
		System.out.println(sum.operate(5, 4));
		System.out.println(minus.operate(7, 3));
		System.out.println(multiply.operate(7, 3));

		Comparator<String> stringLengthComparator = (a, b) -> a.length() - b.length();
		SortedSet<String> set = new TreeSet<>(stringLengthComparator);
		set.addAll(Arrays.asList("jhahjsdjh", "hhjcdjhd", "jdcjdjcdjcd"));

		//print
		set.forEach(System.out::println);

		Runnable runnable = () -> {
			System.out.println("Printing now: " + UUID.randomUUID().toString());
		};

		Thread th = new Thread(runnable);
		th.start();
		th.join();

		NewOperation operation = new NewOperation() {
			@Override
			int operate(int a, int b) {
				return 0;
			}

			@Override
			public void printShit() {
				System.out.println("Printing klpd");
			}

		};

		operation.printShit();
	}

	interface Operation {
		int operate(int a, int b);
	}

	abstract class NewOperation {
		abstract int operate(int a, int b);

		protected void printShit() {
			System.out.println("Print new");
		}
	}

	@Test
	public void testWaitNotify() throws InterruptedException {
		BlockingQueue<Integer> queue = new BlockingQueue<>(10);
		Random random = new Random();
		Runnable publisher = () -> {
			try {
				int count = 0;
				while (count < 260) {
					int publishVal = random.nextInt(1000000);
					System.out.println("Publishing: " + publishVal);
					queue.add(publishVal);
					count++;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread publisherThread = new Thread(publisher);

		Runnable consumer = () -> {
			try {
				int count = 0;
				while (count < 250) {
					System.out.println("Consuming: " + queue.remove());
					count++;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread consumerThread = new Thread(consumer);

		// start publishing and consuming
		publisherThread.start();
		consumerThread.start();

		publisherThread.join();
		consumerThread.join();

	}


	class BlockingQueue<T> {

		private int maxSize;

		private List<T> queue;

		public BlockingQueue(int maxSize) {
			this.maxSize = maxSize;
			queue = Collections.synchronizedList(new ArrayList<>(maxSize));
		}

		/**
		 * If some thread was waiting on empty queue notify
		 * If max size reached wait
		 * otherwise add
		 */
		public synchronized boolean add(T obj) throws InterruptedException {
			notify();
			if (maxSize == queue.size()) {
				System.out.println("Queue is full, producer is waiting.");
				wait();
			}
			return queue.add(obj);
		}

		public synchronized T remove() throws InterruptedException {
			notify();
			if (queue.isEmpty()) {
				System.out.println("Queue is empty, consumer is waiting.");
				wait();
			}
			return queue.remove(0);
		}
	}

	@Test
	public void sumBasedOnCondition() {
		List<Integer> list = Arrays.asList(1, 5, 89, 7, 9, 2, 3, 6, 8, 45, 22, 32);
		System.out.println(sum(list, n -> true));
		System.out.println(sum(list, n -> n % 2 == 0));
		System.out.println(sum(list, n -> n > 33));
	}

	public int sum(List<Integer> list, Predicate<Integer> predicate) {
		int total = 0;
		for (int value : list) {
			if (predicate.test(value)) {
				total += value;
			}
		}
		return total;
	}

	@Test
	public void testNumberOfProcessors() {
		System.out.println("Number of processors is: " + Runtime.getRuntime().availableProcessors());
	}

	@Test
	public void testOrderByFrequencyDesc() {
		int[] arr = {12, 13, 12, 12, 12, 13, 15, 17, 15, 19, 13, 19, 19, 18, 13, 15, 16, 17};
		Map<Integer, Integer> map = new HashMap<>();
		for (int val : arr) {
			if (map.containsKey(val)) {
				int count = map.get(val) + 1;
				map.put(val, count);
			} else {
				map.put(val, 1);
			}
		}
		Set<Map.Entry<Integer, Integer>> freqSet = new TreeSet<>((entry1, entry2) -> entry2.getValue() - entry1.getValue());
		freqSet.addAll(map.entrySet());
		freqSet.forEach(val -> {
			System.out.print(val + " ");
		});
	}

	@Test
	public void testRunningMedian() {
		// 2 heaps h1 (max-heap) and h2 (min-heap)
		// if (val >= h2.peek() h2.add()
		// else (val <= h1.peek()) h1.add
		//
		// if h1.size ~ h2.size > 1
		//      re-balance

		// max-heap
		Queue<Integer> h1 = new PriorityQueue<>(Comparator.reverseOrder());
		// min-heap
		Queue<Integer> h2 = new PriorityQueue<>();

		int[] stream = {13, 189, 23, 26, 1, 9, 89, 67, 87, 12, 1, 23, 45, 67, 81};

		for (int val : stream) {
			if (h1.isEmpty() || val <= h1.peek()) {
				h1.add(val);
			} else {
				h2.add(val);
			}

			// re-balance the heaps
			if (h1.size() - h2.size() > 1) {
				h2.add(h1.poll());
			} else if (h2.size() - h1.size() > 1) {
				h1.add(h2.poll());
			}

			int runningMedian;
			if (h1.size() == h2.size()) {
				runningMedian = (h1.peek() + h2.peek()) / 2;
			} else {
				Queue<Integer> mh = h1.size() - h2.size() == 1 ? h1 : h2;
				runningMedian = mh.peek();

			}
			System.out.println("Running median is: " + runningMedian);
		}


	}

	@Test
	public void testFormatting() {
		String str = String.format("Retry failed  and updating DB " +
										   //"request_id=\"%1$s\"," +
										   "retrycount=\"%2s\"," +
										   "status=\"%3s\",",
								   //"ip_address=\"%4s\" .",
								   1,
								   2);

		System.out.println(str);
	}

	@Test
	public void testPriorityBlockingQueue() throws InterruptedException {
		final int n = 100;
		PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(5, Collections.reverseOrder());
		CountDownLatch publisherLatch = new CountDownLatch(n);
		CountDownLatch consumerLatch = new CountDownLatch(1);

		Runnable publisher = () -> {
			Random rand = new Random();
			for (int i = 0; i < n; i++) {
				pbq.add(rand.nextInt(10));
				publisherLatch.countDown();
			}
		};

		Runnable consumer = () -> {
			while (!pbq.isEmpty()) {
				System.out.println("Polling started value is: " + pbq.poll());
			}
			consumerLatch.countDown();
		};
		new Thread(publisher).start();
		publisherLatch.await();

		System.out.println(" pbq size is: " + pbq.size());

		// start polling
		new Thread(consumer).start();
		consumerLatch.await();
	}

	@Test
	public void testExceptionLogging() {
		try {
			int i = 1 / 0;
		} catch (Exception ex) {
			System.out.println("Exception getMessage() is: " + ex.getMessage());
			ex.printStackTrace();
			System.out.println("Exception toString() is: " + ex.toString());
		}
	}

	@Test
	public void testCachingBasedOnTimeStamp() {
		final int CACHE_SIZE = 5;

		// get value-> 1. Remove from linkedlist
		//             2. Add to tail

		LinkedHashMap<Integer, String> lruCache = new LinkedHashMap<Integer, String>() {

			@Override
			public String get(Object key) {
				if (containsKey(key)) {
					String val = remove(key);
					put((Integer) key, val);
					return val;
				} else {
					return null;
				}
			}

			@Override
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > CACHE_SIZE;
			}
		};

		int[] arr = {1, 2, 3, 4, 5};

		for (int i : arr) {
			lruCache.put(i, "Test value" + i);
		}

		// reuse 1
		lruCache.get(1);
		lruCache.get(2);

		// put 2 more values
		lruCache.put(6, "Test value" + 6);
		lruCache.put(7, "Test value" + 7);

		lruCache.keySet().forEach(key -> System.out.println("Key is: " + key));
	}


	@Test
	public void testBoolean() {
		String s1 = "";
		String s2 = "test";
		String s3 = "true";
		String s4 = null;


		System.out.println("s1 val is : " + Boolean.valueOf(s1));
		System.out.println("s2 val is : " + Boolean.valueOf(s2));
		System.out.println("s2 val is : " + Boolean.valueOf(s3));
		System.out.println("s2 val is : " + Boolean.valueOf(s4));

	}


	@Test
	public void testOrderMapByValue() {
		String[] words = {"abc", "ab", "abc", "dert", "deer", "ght", "frty", "dert", "dert", "dert", "dert"};
		Map<String, Integer> wordCountMap = new HashMap<>();

		for (String word : words) {
			if (wordCountMap.containsKey(word)) {
				wordCountMap.put(word, wordCountMap.get(word) + 1);
			} else {
				wordCountMap.put(word, 1);
			}
		}

		// Order by value
		Set<Map.Entry<String, Integer>> setOrderedyCount = new TreeSet<Map.Entry<String, Integer>>(
				(entry1, entry2) -> {
					return entry1.getValue() - entry2.getValue();
				});


	}



	@Test
	public void testPrintStream() {
		List<Person> list = new ArrayList<>(Arrays.asList(new Person("Tom",23,23000.00,"Finance"), new Person("John",55, 780000.0,"PD"), new Person("Avik",32,56000.0,"Finance")));
//		Set<Integer> filteredList = list.parallelStream().filter(val -> val % 8 == 0).collect(Collectors.toCollection(TreeSet::new));
//		filteredList.forEach(System.out:: println);

		// joined
		String joined = list.stream().map(Person::getName).collect(Collectors.joining(","));
		System.out.println("joined: " +joined);


		// Total age
		int totalAge = list.stream().collect(Collectors.summingInt(Person::getAge));
		System.out.println("Total age: " + totalAge);

		// group by department
		Map<String, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getDepartment));
		System.out.println("Map is: " + map);


		//compute salaries by department
		Map<String, Integer> ageMap = list.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.summingInt(Person::getAge)));
		System.out.println("Map is: " +ageMap);

		List<Pair<Integer, Double>> newList = list.stream().map(p -> new Pair<>(p.age, p.salary)).collect(Collectors.toList());
		System.out.println("Done");

	}

	@Test
	public void testMostFrequentWord() {

	}


	@Test
	public void testNonUniqueNumber() {

	}

	@Test
	public void filterResults() {
		String s = "test2,uhj,test1,ujkh,ikl,test2,test3";
		List<String> values = Arrays.asList(s.split(","));
		String val2 = values.stream().filter(val -> val.startsWith("t")).collect(Collectors.toCollection(HashSet::new)).stream().sorted().collect(Collectors.joining(","));
		System.out.println(val2);
	}

	@Test
	public void testDateUpdate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateText = sdf.format(date);
		System.out.println("dateText: " + dateText);
	}

	@Test
	public void testDateParsing() throws ParseException {
		String plainDOB = "2018-02-06";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("Formatted date: " + sdf.parse(plainDOB)); ;
	}

	@Test
	public void testGroupByName() {
		List<Person> persons = new ArrayList<>(Arrays.asList(new Person("Tom", 25, 1000.20, "Finance"),
		                                                     new Person("Jerry", 31, 2500.80, "Engineering"),
		                                                     new Person("Kate", 37, 4500.80, "PM"),
		                                                     new Person("Tushar", 31, 7500.80, "Engineering")
		));


		List<Person> filteredPersons = persons.stream().filter(p -> p.name.startsWith("T")).collect(Collectors.toCollection(ArrayList::new));
		Assert.assertNotNull(filteredPersons);

		Map<String,Double> map =  persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.summingDouble(Person::getSalary)));
		System.out.println(map);
	}

	static class LRUMap<K,V> extends LinkedHashMap<K,V> {

		public static final int FIXED_SIZE = 4;

		LRUMap(int capacity) {
			super(capacity);
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<K,V> entry) {
			return size() > FIXED_SIZE;
		}
	}

	@Test
	public void testLRU() {
		List<Person> persons = new ArrayList<>(Arrays.asList(new Person("Tom", 25, 1000.20, "Finance"),
		                                                     new Person("Jerry", 31, 2500.80, "Engineering"),
		                                                     new Person("Kate", 37, 4500.80, "PM"),
		                                                     new Person("Tushar", 31, 7500.80, "Engineering")
		));

		Map<String,Person> lruMap = persons.stream().collect(Collectors.toMap(Person::getName,
		                                                                      person -> person,
		                                                                      (u, v) -> {
			                                                                      throw new IllegalStateException(String.format("Duplicate key %s", u));
		                                                                      },
		                                                                      () -> new LRUMap<>(4)));
		Assert.assertNotNull(lruMap);
		Person newPerson = new Person("Thomas", 25, 1000.20, "Finance");

		lruMap.put(newPerson.getName(), newPerson);
		Assert.assertNotNull(lruMap);
	}

	@Test
	public void testAFewMoreLambda() {
		HashSet<Integer> set = Stream.of(1,2,3,3).filter(val -> val >2).collect(Collectors.toCollection(HashSet::new));
		System.out.println("Done");
	}



}



