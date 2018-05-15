package com.practice.functional;

import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pbhattacharya on 3/9/18.
 */
public class DateFormattingExample {

	@Test
	public void testDateFormatting() throws Exception {
		String date = "1979-12-12";

		Date newDate = null;
		if(StringUtils.isNotEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			newDate = sdf.parse(date);
		}

		Assert.assertNotNull(newDate);
	}

	@Test
	public void testCreateFileFromThread() throws Exception{
		String dir = "/Users/pbhattacharya/Desktop/filetesting";
		File file = new File(dir + "/" + "Main_" + UUID.randomUUID().toString());

		try(FileOutputStream os = new FileOutputStream(file)) {
			os.write("I am main thread".getBytes());
			os.flush();
			os.close();
		}

		ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.submit(() -> {
			File newFile = new File(dir + "/" + "Worker_" + UUID.randomUUID().toString());
			try(FileOutputStream os = new FileOutputStream(newFile)) {
				os.write("I am worker thread".getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


	}




}
