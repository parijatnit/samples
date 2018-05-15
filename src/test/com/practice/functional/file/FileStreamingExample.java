package com.practice.functional.file;

import org.junit.Test;

import java.io.*;
import java.util.UUID;

/**
 * Created by pbhattacharya on 3/28/18.
 */
public class FileStreamingExample {

	@Test
	public void testStreaming() throws IOException {
		String dir = "/Users/pbhattacharya/Desktop/filetesting";
		File readFile = new File(dir + "/" + "sc.png");
		File copyFile = new File(dir + "/" + "sc_copy.png");

		BufferedInputStream buf = new BufferedInputStream(new FileInputStream(readFile));
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(copyFile));

		byte[] buffer = new byte[1024];

		while(buf.read(buffer) > 0) {
			os.write(buffer);
		}

		os.flush();
		os.close();
	}







}
