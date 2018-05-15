package com.practice.functional;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pbhattacharya on 7/20/17.
 */
public class StreamExample {

	private static final Random RAND = new Random();

	private static class City {
		public String name;
		public int temperature;

		City(String name, int temperature) {
			this.name = name;
			this.temperature = temperature;
		}
	}



	@Test
	public void testStreamFilter() {
		List<City> list = new ArrayList<>(Arrays.asList(new City("New York", -8),
		                                                new City("Chicago", -19),
		                                                new City("Denver", 17),
		                                                new City("Miami", 20),
		                                                new City("San Jose", 19),
		                                                new City("Tampa", 21),
		                                                new City("San Francisco", 19)));

		List<String> warm =  list.stream().filter(city -> city.temperature > 15).map(city -> city.name).collect(Collectors.toList());
		System.out.println("Done");
	}

	@Test
	public void testStreamMin() {
		List<City> list = new ArrayList<>(Arrays.asList(new City("New York", -8), new City("Chicago", -19), new City("Denver", 5), new City("Miami", 20), new City("San Jose", 19)));
		String coldestCity = list.stream().min(Comparator.comparing(city -> city.temperature)).get().name;
		System.out.println("Coldest city is : " + coldestCity);

	}

	@Test
	public void testStreamReduce() {
		List<String> list = new ArrayList<>(Arrays.asList("New York", "Chicago", "Denver"));

		String reduced = list.stream().reduce("Names of the cities are: ", (acc, city) -> acc + "-" + city);
		System.out.println("Value is: " + reduced);
	}

	@Test
	public void testVarags() {
		printIfNotNUll();
		printIfNotNUll();
		printIfNotNUll();
	}

	@Test
	public void testTrim() {
		String text =" abc" +
				"ffndm,nmd" +
				"jfdfndfnmd";

		System.out.printf("trimmed text is: " + text.trim());
	}


	@Test
	public void testStreamDuplicates() {
		List<Integer> list = new ArrayList<>(Arrays.asList(32, 64, 64, 50, 56, 56, 78, 100));
		Set<Integer> filteredSet = list.parallelStream().filter(val -> val > 50).collect(Collectors.toSet());
		filteredSet.forEach(System.out:: println);
	}

	@Test
	public void testWinterDestination() {
		List<City> list = new ArrayList<>(Arrays.asList(new City("New York", -8), new City("Chicago", -19), new City("Detroit", 14), new City("Denver", 17), new City("Miami", 20), new City("San Jose", 19), new City("Tampa", 21), new City("San Francisco", 19)));
		List<City> winterDestinations = list.stream().filter(city -> city.temperature >= 16).collect(Collectors.toList());
		System.out.println("Winter destination: " + winterDestinations.get(RAND.nextInt(winterDestinations.size())).name);
	}


	@Test
	public void testBase64() {
		List<String> list = Arrays.asList("abc","12233r33r4r","tyyshjfshjhgs","","0","54%45588",null);

		//list.forEach(val -> Base64.decodeBase64(encryptedTextBytes));


	}

	@Test
	public void testBooleanValueOf() {

		boolean bool = Boolean.valueOf("TRUE");
		System.out.println("bool: " + bool);

	}

	@Test
	public void testSomething() {
		Object[] arr = new Object[3];
		arr[0] = "trt";
		arr[1] = "123";
		for(int i =0; i< arr.length; i++) {
			Object val = arr[i];
			if(val instanceof String) {
				System.out.println("Not null");
			} else if(val == null) {
				System.out.println("Null");
			}

			System.out.println("value: " + arr[i]);
		}




	}



	@Test
	public void printIfNotNUll() {
		String value = "171663,171661,171660,171643,171638,171636,171626,171623,171622,171619,171587,171575,171572,171558,171547,171546,171541,171535,171526,171520,171514,171508,171505,171500,171498,171484,171479,171475,171473,171472,171471,171470,171460,171453,171400,171396,171391,171389,171386,171370,171368,171361,171350,171349,171347,171345,171344,171338,171336,171330,171326,171316,171314,171313,171306,171304,171296,171289,171283,171282,171278,171265,171260,171252,171230,171228,171223,171210,171208,171201,171200,171190,171187,171184,171176,171175,171173,171171,171166,171162,171160,171149,171148,171147,171136,171134,171126,171120,171119,171116,171114,171111,171107,171097,171079,171071,171070,171061,171060,171055,171051,171049,171039,171033,171008,171007,171004";
		System.out.println("value is: " + value.trim());


	}

	@Test
	public void testFileStream() throws IOException {
		File inFile = new File("/Users/pbhattacharya/Desktop/bankinfolog_backup_delete.sql");
		File outFile = new File("out.sql");
		try(
		InputStream in = new FileInputStream(inFile);
		OutputStream out = new FileOutputStream(outFile);
		) {
			byte[] bytes = new byte[8];
			int data = in.read(bytes);
			while (data != -1) {
				out.write(bytes);
				data = in.read(bytes);
			}
		}
	}

	@Test
	public void testNumberOfOccurrences() {
		int[] arr = {1,2,2,8,8,8,8,8,8,8,9,9,10,67,89,89,89,90};
		int val = 90;
		int index = binarySearch(arr, val, 0, arr.length -1);
		int count = (index == -1) ? 0 : 1;

		if(count != 0) {
			int l = index;
			int r = index;

			boolean goLeft = true;
			boolean goRight = true;

			while ((goLeft || goRight) && (l > 0 && r < (arr.length - 1))) {
				if (arr[--l] == val) {
					count++;
				} else {
					goLeft = false;
				}

				if (arr[++r] == val) {
					count++;
				} else {
					goRight = false;
				}

			}
		}

		System.out.println("Number of occurrences: " + count);

	}

	private int binarySearch(final int[] a, final int value, final int l, final int r) {
		if(l > r) {
			return -1;
		}

		// find the middle index
		int m = (l+r)/2;

		if(a[m] == value) {
			return m;
		} else if(a[m] < value) {
			return binarySearch(a, value, m+1, r);
		} else {
			return binarySearch(a, value, l, m - 1);
		}
	}


	@Test
	public void testWordCountSortedByCount() {
		String[] values = {"sea","dancing","this","well","well","well","life","ocean","life","life","life","this","this","this","this"};
		Map<String, Integer> map = new HashMap<>(values.length);

		for(String value : values) {
			Integer count = map.get(value);
			count = count == null ? 0 : count;
			map.put(value, ++count);
		}

		Set<Map.Entry<String, Integer>> set = map.entrySet();
		TreeSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>((obj1, obj2)-> {
			int diff = obj2.getValue() - obj1.getValue();
			if(diff == 0) {
				diff = obj2.getKey().compareTo(obj1.getKey());
			}
			return diff;
		});
		sortedSet.addAll(set);

		// print the results
		sortedSet.forEach(obj-> System.out.println(obj.getKey()));
	}

	@Test
	public void testByteArray() {
		int[] values = {1,2,3,4,5};

		for(int value : values) {
			System.out.println("value: " + value);
		}

	}

	/*
	 * A travel site recommends winter destinations from a list of major travel destinations.
	 *
	 * Write an program which will suggest a random winter destination received from weather channel,
	 * Selection criteria for winter destination: temperature > 55 degrees

		San Francisco     70
		Miami             72
		New York          20
		Chicago           25
		Maui              65
		Los Angeles       71
		Seattle           55
		Boston            18

	 */

	@Test
	public void printRandom() throws InterruptedException{
		List<Integer> list = new ArrayList<>(Arrays.asList(70,72,71, 73, 20,35));
		 ArrayList<Integer> filtered = list.stream().filter(val -> val > 55 && val %2 ==1).collect(Collectors.toCollection(ArrayList::new));

		final List<Integer> filteredList = Collections.synchronizedList(filtered);

		Runnable r1 = ()-> {
			int i = 0;
			while(i<100000) {
				try {
					Thread.sleep(1);
					filteredList.add(i);
					i++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();

		int i = 0;
		while(i<100000) {
			System.out.println(filteredList.get(i));
			i++;
		}


		t1.join();
	}

	@Test
	public void testHashTable() {
		Hashtable hashtable = new Hashtable<>(5);
		hashtable.put("1","abc");
		hashtable.put("2","def");
		hashtable.put("3","ijk");

		System.out.println(hashtable.get("1"));
		System.out.println(hashtable.get(null));

	}


	@Test
	public void tesRegex() {
		String testURI = "/FullService//indexbnnbb";
		String pattern = ".*(.js|.css|.png|.htm.jsf|.html.jsf|.jpg|.jpeg|.gif).*";

		System.out.println("Matches: " + testURI.matches(pattern));
	}

	@Test
	public void testCSV() {
		String csv = "103.15.250.0,12.148.72.0,12.149.172.0,12.157.155.0,12.179.132.0,12.52.79.32,165.193.231.128,173.240.168.128,173.240.172.128,173.29.22.107,198.31.208.0,199.102.144.0,199.102.148.0,199.16.136.0,199.187.156.0,199.187.157.0,204.119.100.0,206.108.40.0,208.188.32.192,208.29.163.0,208.74.44.0,212.179.202.0,216.52.25.0,35.162.229.134,35.163.188.31,35.165.118.39,52.42.167.220,52.42.93.5,64.34.20.0,65.162.137.0,65.204.229.0";

		String[] ips = csv.split(",");

		for(String val : ips) {
			System.out.println(val);
		}

	}

	@Test
	public void testCloning() {
		ArrayList<City> cities = new ArrayList<>(Arrays.asList(new City("San Francisco",18),new City("Chicago",10), new City("New York",5)));
		List<City> sorted = cities.stream().sorted((city1, city2)-> city1.temperature - city2.temperature).collect(Collectors.toList());
		System.out.println("This is cloning!!!");
	}

	class Philosopher extends Thread {
		private Integer id;
		private Object left;
		private Object right;
		private Random random;

		private static final int MAX = 5;


		Philosopher(Integer id, Object left, Object right) {
			this.id = id;
			this.left = left;
			this.right = right;
			random = new Random();
		}

		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(random.nextInt(MAX));
					synchronized (left) {
						synchronized (right) {
							System.out.println("Philosopher " + id + " eating now.");
							Thread.sleep(random.nextInt(MAX));
							System.out.println("Philosopher " + id + " done eating.");
						}
					}
				}
			} catch(Exception ie) {
				System.out.println("oops unwanted exception: " + ie.getMessage());
			}
		}
	}


	@Test
	public void testDeadLock() throws InterruptedException {
		Object chopStick1 = new Object();
		Object chopStick2 = new Object();
		Object chopStick3 = new Object();

		Philosopher p1 = new Philosopher(1, chopStick1, chopStick2);
		Philosopher p2 = new Philosopher(2, chopStick2, chopStick3);
		Philosopher p3 = new Philosopher(3, chopStick3, chopStick1);

		// Run all
		p1.start();
		p2.start();
		p3.start();

		// Join all
		p1.join();
		p2.join();
		p3.join();
	}


	@Test
	public void testStreaming() {
		List<City> cities = new ArrayList<>(Arrays.asList(new City("SF",18),new City("NY",2),new City("Miami",20), new City("Chicago",1)));
		Map<String, City> map = cities.stream().sorted((city1, city2) -> city1.temperature - city2.temperature).collect(Collectors.toMap(city -> city.name, city -> city));
		System.out.println("Crated map");
	}


	@Test
	public void testFrequency() {
		String text = "word,test,level,level,test,word,test,test,final";
		Map<String, Integer> map = new HashMap<>();
		String[] words = text.split(",");

		for(String word: words) {
			Integer count = map.get(word);
			if(count == null) {
				map.put(word, 1);
			} else {
				map.put(word, ++count);
			}
		}

		Set<Map.Entry<String, Integer>> set = map.entrySet();
		TreeSet<Map.Entry<String, Integer>> sortedSet = set.stream().sorted((entry1, entry2) -> entry1.getValue() - entry2.getValue()).collect(Collectors.toCollection(()-> new TreeSet<>((entry1, entry2) -> entry1.getValue() - entry2.getValue())));

		System.out.println("We are done");
	}



	@Test
	public void testFuture() {
		Random random = new Random();

		ExecutorService service = Executors.newFixedThreadPool(5);
		List<Future> futures = new ArrayList<>(5);

		for(int i=0; i<5; i++) {
			Callable<Integer> callable = () -> {
				Thread.sleep(1);
				return random.nextInt(100);
			};
			futures.add(service.submit(callable));
		}

		futures.forEach(f -> {
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});

	}

	@Test
	public void testCompletableFuture() throws Exception {
		String expectedValue = "the expected value";
		CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(expectedValue);
		Assert.assertEquals(alreadyCompleted.get(), expectedValue);
	}

	@Test
	public void testCompletableFutureRunAsync() throws Exception {
		System.out.println("Parent thread id: " + Thread.currentThread().getId());
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(100);
				System.out.println("From Thread: " + Thread.currentThread().getId() + " running supplied async task after pausing.");
				return "I will be printing in another thread.";
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}, executorService);


		CompletableFuture<Void> task2 = task1.thenAccept(s -> {
			System.out.println("From Thread: " + Thread.currentThread().getId() + " " + s);
		});

		Thread.sleep(200);
		Assert.assertTrue(task1.isDone());
		Assert.assertTrue(task2.isDone());
	}


	@Test
	public void joinValues() {
		String PREPARED_STATEMENT_PARAM_TOKEN = "{{CORRECT_NUMBER_OF_PARAMS}}";
		String prepStmtQuery = "Select personId from employees where companyId in ({{CORRECT_NUMBER_OF_PARAMS}})";
		List<String> paramsList = new ArrayList<>(Arrays.asList("123","6789","908766"));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paramsList.size(); i++) {
			sb.append("?");
			if (i != paramsList.size() - 1) {
				sb.append(",");
			}
		}
		System.out.println("Query is: " + prepStmtQuery.replace(PREPARED_STATEMENT_PARAM_TOKEN, sb.toString()));
	}


}
