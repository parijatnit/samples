package com.practice.parameterized;

import junit.framework.Assert;
import junit.framework.TestCase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pbhattacharya on 4/24/18.
 */
@RunWith(JUnitParamsRunner.class)
public class ParametrizedExamples {


	@Test
	@Parameters({
			"aaa bbb aaa bbb aaa ddd, aaa, 3",
			"aaa aaa xyz, aaa, 2",
			"aaa, aaa, 1",
			",, 0",
	})
	public void testMostFrequentWord(String text, String expectedWord, int expectedCount) {
		// Split text by space create an array
		// For each word
		//      store into hashmap
		//      keep track of running max and the word
		String runningMaxWord = "";
		int runningMaxCount = 0;
		if(text!= null && text.trim().length() >0) {
			String[] words = text.split(" ");
			HashMap<String, Integer> map = new HashMap<>();
			for (String word : words) {
				Integer count = map.get(word);
				count = count == null ? 1 : count + 1;
				map.put(word, count);
				if (count > runningMaxCount) {
					runningMaxCount = count;
					runningMaxWord = word;
				}
			}
		}
		Assert.assertEquals(expectedCount, runningMaxCount);
		Assert.assertEquals(expectedWord, runningMaxWord);
	}


	@Test
	@Parameters({
			"aaa bbb aaa bbb aaa ddd, bbb, 2",
			"aaa aaa xyz, xyz, 1",
			"aaa,, 0",
			",, 0",
	})
	public void testSecondMostFrequentWord(String text, String expectedWord, int expectedCount) {
		// Split text by space create an array
		// For each word
		//      store into hashmap
		//      keep track of running max and the word
		String runningSecondMaxWord = "";
		int runningSecondMaxCount = 0;
		if(text!= null && text.trim().length() >0) {
			HashMap<String, Integer> map = new HashMap<>();
			List<String> words = Arrays.asList(text.split(" "));
			for (String word : words) {
				Integer count = map.get(word);
				count = count == null ? 1 : count + 1;
				map.put(word, count);
			}
			List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
			Collections.sort(list, (e1, e2) -> e2.getValue() - e1.getValue());
			if(list.size() >= 2) {
				runningSecondMaxWord = list.get(1).getKey();
				runningSecondMaxCount = list.get(1).getValue();
			}
		}
		Assert.assertEquals(expectedCount, runningSecondMaxCount);
		Assert.assertEquals(expectedWord, runningSecondMaxWord);
	}

	@Test
	@Parameters({
			"abc, cab, true",
			"word, dwor, true",
			"adcbnnbnbn, jhjhjhj, false",
			",,true",
			"a,a,true",
	})
	public void testRoundString(String word, String roundWord, boolean expectedResult) {
		String expectedRoundedWord = word;
		if(word != null && word.length() > 1) {
			expectedRoundedWord = word.charAt(word.length() - 1) + word.substring(0, word.length() - 1);
		}

		boolean actualResult = expectedRoundedWord != null && expectedRoundedWord.equals(roundWord);
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testBoolean() {
		String val = "on";
		Assert.assertFalse(Boolean.valueOf(val));
	}

	public Object sumParameters() {
		int  sum1 = 9;
		int[] input1 = {2, 7, 11, 15};
		int[] expected1 = {0,1};

		int  sum2 = 9;
		int[] input2 = {2, 7, 11, 15};
		int[] expected2 = {0,1};

		return $(
				$(sum1, input1, expected1),
				$(sum2, input2, expected2)
		);
	}

	class Entry {
		int value;
		int index;

		public Entry(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	@Test
	@Parameters(method = "sumParameters")
	public void testSum(
			int target, int[] nums, int[] expected) {
		// for each value
		//      if !map.contains(val)
		//          complement = sum - val
		//          map.put(complement, (val, index)
		int[] actual = null;
		Map<Integer, Integer> complementMap = new HashMap<>();
		for(int i = 0; i< nums.length; i++) {
			int currentValue = nums[i];
			if(!complementMap.containsKey(currentValue)) {
				int complement = target - currentValue;
				complementMap.put(complement, i);
			} else {
				actual = new int[2];
				actual[0] = complementMap.get(currentValue);
				actual[1] = i;
			}
		}
		assertArrayEquals(expected, actual);
		assertEquals(expected, actual);
	}


	public Object testAnythingParams() {
		int  sum1 = 9;

		Map<String, String> map1 = new HashMap<>();
		map1.put("key", "val");

		List<String> list1 = new ArrayList<>();
		list1.add("Ram");

		return $(
				$(sum1, map1, list1)
		);
	}

	@Test
	@Parameters(method = "testAnythingParams")
	public void testAnything(int sum, Map<String, String> map, List<String> list) {
		System.out.println("Just checking");
	}


	@Test
	@Parameters({
			"1234,false",
			"111,true",
			"-123,false",
	})
	public void testPalindrome(int number, boolean expected) {
		boolean actual = false;
		if(number > 0) {
			String text = String.valueOf(number);
			// Loop through
			//  compare a[i] and a[l-i-1]
			for(int i=0; i< text.length(); i++) {
				actual = text.charAt(i) == text.charAt(text.length() -i -1);
				if(!actual) {
					break;
				}
			}
			assertEquals(expected, actual);
		}
	}

	@Test
	@Parameters({
			"This is a test this is a test this,is",
			"This is is life life life this is this this,is"
	})

	// Find 2nd most frequent word, maintaining order
	public void testSecondMostFrequentWord(String paragraph, String expectedWord) {
		String actualWord = "";

		// Split paragraph, build array
		// For Each
		//      Put to map [Key -> Word, Value -> Count]
		// get list from map
		// sort the list
		// get 2nd most frequent

		String[] words = paragraph.split(" ");

		Map<String, Integer> map = new LinkedHashMap<>();
		for(String word: words) {
			String trimmedWord = word.trim().toLowerCase();
			Integer count = map.get(trimmedWord);
			count = count == null ? 1 : count + 1;
			map.put(trimmedWord, count);
		}

		List<Map.Entry<String, Integer>> entries = map.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue()).collect(Collectors.toList());
		if(entries.size() >= 2) {
			actualWord = entries.get(1).getKey();
		}

		assertEquals(expectedWord, actualWord);
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

//	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//	Output: 7 -> 0 -> 8
//	Explanation: 342 + 465 = 807.

	@Test
	public void testListSum(ListNode l1, ListNode l2) {
		ListNode head = null;
		ListNode current = null;
		ListNode prev = null;
		int carry = 0;
		while (l1 != null || l2 != null || carry>0) {

			if(l1!=null && l2 != null) {
				current = new ListNode((carry + l1.val + l2.val) % 10);
				carry = (carry + l1.val + l2.val)/10;
				l1 = l1.next;
				l2 = l2.next;
			} else if(l2 == null && l1 != null) {
				current = new ListNode((carry + l1.val) % 10);
				carry = (carry + l1.val)/10;
				l1 = l1.next;
			} else if(l2 != null ) {
				current = new ListNode((carry + l2.val) % 10);
				carry = (carry + l2.val)/10;
				l2 = l2.next;
			} else if(carry > 0){
				current = new ListNode(carry);
				carry = 0;
			}

			if(head == null) {
				head = current;
			} else {
				prev.next = current;
			}
			prev = current;
		}
		assertNotNull(head);
	}

	class Person {
		private String name;
		private boolean visited;
		private Set<Person> connections;

		public Person(String name) {
			this.name = name;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public void addConnection(Person person) {
			if(connections == null) {
				connections = new HashSet<>();
			}

			if(!connections.contains(person)) {
				connections.add(person);
			}
		}

		public Set<Person> getConnections() {
			return connections;
		}
	}

	class PersonWithLevel {
		private Person person;
		private int level;

		public PersonWithLevel(Person person, int level) {
			this.person = person;
			this.level = level;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
	}


	@Test
	public void traverseBFSTree(Person person) {
		// enQueue all direct connections
		// While queue NOT empty
		//      dequeue person
		//      print name
		//      enqueue all connections

		Deque<PersonWithLevel> queue = new ArrayDeque<>();
		if(person.getConnections() != null) {
			person.getConnections().forEach(p -> {
				queue.add(new PersonWithLevel(p, 1));
			});
		}

		while (!queue.isEmpty()) {
			PersonWithLevel personWithLevel = queue.getFirst();
			System.out.println("Name is: " + personWithLevel.getPerson().name + "level is: " + personWithLevel.level);

			if(personWithLevel.getPerson().getConnections() != null) {
				personWithLevel.getPerson().getConnections().forEach(p -> {
					queue.add(new PersonWithLevel(p, personWithLevel.getLevel() + 1));
				});
			}
		}
	}

	@Test
	public void testReverseLinkedList(ListNode head) {
		ListNode prev = null;
		ListNode current = head;

		while (current != null) {
			ListNode temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
	}

	@Test
	@Parameters({
			"abcabcbb,abc",
			"bbbbb,b",
			"pwwkew,wke",
			",",
			"c,c",
			"au,au",
	})
	public void testLongestSubString(String s, String expectedSubString) {
		// String runningMax
		// Loop through char[]
		// while (i<length)
			// For (j=i; j < length; j++)
			//    if set ==null
			//      set = a new set
			//    if set NOT contains char,
			//       s += ch
		    //       if(s.lenght > runningMax)
		    //              runningMax = s
			//    else
		    //       i++;
		    //       break

		String runningMax = s.length() > 0 ? String.valueOf(s.charAt(0)) : "";
		int i = 0;
		while (i< s.length()) {
			Set<Character> set = new HashSet<>();
			String currentMax = "";
			StringBuilder currentMaxBuilder = new StringBuilder();
			for(int j=i; j < s.length(); j++) {
				char ch = s.charAt(j);
				if(!set.contains(ch)) {
					currentMaxBuilder.append(ch);
					set.add(s.charAt(j));
					if(j == s.length() -1) {
						i++;
						currentMax = currentMaxBuilder.toString();
						if(currentMax.length() > runningMax.length()) {
							runningMax = currentMax;
						}
					}
				} else {
					i++;
					currentMax = currentMaxBuilder.toString();
					if(currentMax.length() > runningMax.length()) {
						runningMax = currentMax;
					}
					break;
				}
			}
		}

		assertEquals(expectedSubString, runningMax);

	}

	@Test
	@Parameters({
			"abcabcbb,3",
				"bbbbb,1",
				"pwwkew,3",
				",0",
				"c,1",
				"au,2"
	})
	public void testLongestSubStringv2(String s, int expectedLength) {
		// String runningMax
		// Loop through char[]
		// while (i<length)
		// For (j=i; j < length; j++)
		//    if set ==null
		//      set = a new set
		//    if set NOT contains char,
		//       s += ch
		//       if(s.lenght > runningMax)
		//              runningMax = s
		//    else
		//       i++;
		//       break

		int len = s.length();
		int max = 0, start = 0;
		HashMap<Character, Integer> chars = new HashMap<>();
		for (int i = 0; i < len; i++) {
			Character c = s.charAt(i);
			if (chars.containsKey(c)) {
				start = Math.max(start, chars.get(c) + 1);
			}
			max = Math.max(max, i - start + 1);
			chars.put(c, i);
		}
		assertEquals(expectedLength, max);

	}

	@Test
	@Parameters(method= "testSortedArrays")
	public void testMedianOfSortedArrays(int[] arr1, int[] arr2, double expectedMedian) {
		// create one max heap and min heap
		// Loop through th
	}

	public Object testSortedArrays() {
		int[]  arr1 = {1,3};
		int[]  arr2 = {2};
		double expectedMedian1 = 2;

		int[]  arr3 = {1,2};
		int[]  arr4 = {3,4};
		double expectedMedian2 = 2.5;


		return $(
				$(arr1, arr2, expectedMedian1),
				$(arr3, arr4, expectedMedian2)
		);
	}

	@Test
	@Parameters({
			"123,321",
			"-45,-54",
			"-12000,-21",
			"-120,-21",
			"-1,-1",
			"99,99",
			"0,0",
			"9,9",
			"10,1", "1534236469,0"
	})
	public void testReverseInteger(final int x, final int expectedValue) {
		// convert value to String
		// check 1st char if - append to sb
		// Loop reverse
		//      ignore char if zero continues from start
		//      if NOT ignore
		//          append to sb
		String s = String.valueOf(x);
		int endIndex = s.charAt(0) == '-' ? 1 : 0;
		StringBuilder sb = new StringBuilder();
		if (endIndex == 1) {
			sb.append('-');
		}

		int startIndex = s.length() - 1;
		boolean ignore = true;
		for (int i = startIndex; i >= endIndex; i--) {
			ignore = ignore && s.charAt(i) == '0' && s.length() != 1;
			if (!ignore) {
				sb.append(s.charAt(i));
			}
		}

		int actual = 0;
		try {
			actual = Integer.parseInt(sb.toString());
		} catch (NumberFormatException ne) {
			System.out.println("Integer value over-flowed returning 0");
		}
		assertEquals(expectedValue, actual);
	}

	@Test
	@Parameters(method = "testParams")
	public void testInsertionIndex(final int[] nums, final int target, final int expected)  {
		// index = 0
		// if a[] not empty
		//     l = 0, r = a[len -1]
		// while(r>=l)
		//    if(a[m] ==
		int actual = -1;
		if(nums.length > 0) {
			int l = 0;
			int r = nums.length -1;
			while(r>=l) {
				int m = (l+r)/2;
				if(nums[m] == target) {
					actual = m;
					break;
				} else if(target> nums[m]) {
					l = m+1;
				} else {
					r = m-1;
				}
			}

			if(l>=r && actual == -1) {
				if(r==-1 || target> nums[r]) {
					actual = r+1;
				} else if(target < nums[l]) {
					actual = (l==0) ? 0 : l-1;
				}
			}
		} else {
			actual = 0;
		}

		TestCase.assertEquals(expected, actual);
	}

	public Object testParams() {
		int[] nums1 = {1,2,5,8,10};
		int target1 = 3;
		int expected1 = 2;

		int[] nums2 = {1,2,5,8,10};
		int target2 = 5;
		int expected2 = 2;

		int[] nums3 = {1,2,5,8,10};
		int target3 = 11;
		int expected3 = 5;

		int[] nums4 = {1};
		int target4 = 2;
		int expected4 = 1;

		int[] nums5 = {5};
		int target5 = 2;
		int expected5 = 0;

		int[] nums6 = {};
		int target6 = 2;
		int expected6 = 0;

		return $(
				$(nums1, target1, expected1),
				$(nums2, target2, expected2),
				$(nums3, target3, expected3),
				$(nums4, target4, expected4),
				$(nums5, target5, expected5),
				$(nums6, target6, expected6)
		);

	}




}
