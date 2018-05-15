package com.practice.functional;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created by pbhattacharya on 8/15/17.
 */
public class SpitOutSQL {


	// 12-345678, 345678
	@Test
	public void spitSQL() throws Exception{

		final String UPDATE_LEADS_SQL_FORMAT = "update Leads set fein='%1$s', FEINPlainText='%2$s' where companyid=0 AND personid=%3$s;";

		File file = new File("/Users/pbhattacharya/git/Examples/main/com/practice/functional/data.txt");
		Scanner scanner = new Scanner(file);

		while(scanner.hasNext()) {
			String values = scanner.nextLine();
			String[] arr = values.split(",");
			System.out.println(String.format(UPDATE_LEADS_SQL_FORMAT, arr[1],arr[1], arr[0]));
		}





	}






}
