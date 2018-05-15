package com.practice.functional;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by pbhattacharya on 12/13/17.
 */
public class ThreadLocalTesting {

	private static ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();


	public static boolean isAspectDisabled() {
		Boolean bool = booleanThreadLocal.get();
		return bool == null? false : booleanThreadLocal.get();
	}

	@Test
	public void testThreadLocal() {
		System.out.println("isAspectDisabled" + isAspectDisabled());

	}

	@Test
	public void testLinkedHashMap() {
		ConcurrentSkipListMap<Integer,String> sortedMap = new ConcurrentSkipListMap<>();

		sortedMap.put(3,"First");
		sortedMap.put(2,"Second");
		sortedMap.put(100,"Last");
		sortedMap.put(99,"Last");

		System.out.println("First key: " + sortedMap.firstKey());
		System.out.println("Last key: " + sortedMap.lastKey());


	}

	@Test
	public void testString() {
		String readQuery = String.format("SELECT DISTINCT TOP %1$d CompanyID FROM Companies WHERE CompanyID BETWEEN ? AND ? AND CompanyID % ?= ? ORDER BY CompanyID", 100);
		System.out.println("readQuery: " + readQuery);
	}



}
