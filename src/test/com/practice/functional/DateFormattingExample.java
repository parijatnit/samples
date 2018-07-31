package com.practice.functional;

import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pbhattacharya on 3/9/18.
 */
public class DateFormattingExample {

	@Test
	public void testDateFormatting() throws Exception {
		String date = "1979-12-12";
		Date newDate;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		newDate = sdf.parse(date);
		assertNotNull(newDate);
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
