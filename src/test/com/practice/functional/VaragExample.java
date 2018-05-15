package com.practice.functional;

import java.util.Arrays;

/**
 * Created by pbhattacharya on 11/29/17.
 */
public class VaragExample {

	public static void main(String[] args) {
		printSomething(Arrays.asList("something1","something2","something3"));


	}

	public static void printSomething(Object... values) {
		for(Object value :  values) {
			System.out.println("Print: " + value);
		}
	}



}
