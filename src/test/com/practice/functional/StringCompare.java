package com.practice.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pbhattacharya on 12/21/17.
 */
public class StringCompare {

	@Test
	public void testCSV() {
		String csv1="EIN,EIN1,EIN2,COMPANYEIN,PREPAREREIN,LASTFEINSENT,FEIN,SUIEIN,PITEIN,LOCAL ID,LOCALEIN";
		String csv2 = "EIN,EIN1,EIN2,COMPANYEIN,PREPAREREIN,LASTFEINSENT,FEIN,SUIEIN,PITEIN,LOCALEIN,LOCAL ID";

		List<String> list1 = Arrays.asList(csv1.split(","));
		List<String> list2 = Arrays.asList(csv2.split(","));

		List<String> newList1 = list1.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
		List<String> newList2 = list2.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

		Assert.assertEquals(newList1,newList2);
	}



}
